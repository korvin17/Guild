package com.korvin.guild.storage.imp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.korvin.guild.R;
import com.korvin.guild.server.State;
import com.korvin.guild.storage.DataBaseHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DataBaseHelperImp implements DataBaseHelper {

    public static final String COLUMN_NAME_ID = "_id";
    private static final String WHERE_ID = COLUMN_NAME_ID + "= ?";
    public static final String COLUMN_NAME_NAME = "name";
    private static final String TABLE_NAME_SAVE = "save";
    private static final String COLUMN_NAME_DATE = "date";
    private static final String[] SELECT_ALL_SAVE_COLUNM = new String[]{COLUMN_NAME_ID, COLUMN_NAME_NAME, COLUMN_NAME_DATE};
    private static final String COLUMN_NAME_SAVE = "save";
    private static final String[] SELECT_SAVE_DATA = new String[]{COLUMN_NAME_NAME, COLUMN_NAME_DATE, COLUMN_NAME_SAVE};
    private static final String DECS = " desc";
    private final Context context;
    private final DBHelper mDBHelper;

    public DataBaseHelperImp(Context context) {
        this.context = context;
        this.mDBHelper = new DBHelper(context);
    }

    @Override
    public long saveState(State state, String name) {
        return saveState(state, name, WRONG_ID);
    }

    public long saveState(State state, String name, long id) {
        SaveRecord saveRecord = new SaveRecord(name, new Date(), state, id);
        SQLiteDatabase base = mDBHelper.getWritableDatabase();
        ContentValues param = new ContentValues();
        param.put(COLUMN_NAME_NAME, saveRecord.getName());
        param.put(COLUMN_NAME_DATE, saveRecord.getDate().getTime());
        param.put(COLUMN_NAME_SAVE, saveRecord.getData());
        long idSave;
        if (saveRecord.getId() != WRONG_ID) {
            base.update(TABLE_NAME_SAVE, param, COLUMN_NAME_ID + " = ?", new String[]{Long.toString(saveRecord.getId())});
            idSave = id;
        } else
            idSave = base.insert(TABLE_NAME_SAVE, null, param);
        base.close();
        return idSave;
    }

    @Override
    public SaveRecord getState(long id) {
        SQLiteDatabase base = mDBHelper.getReadableDatabase();
        Cursor cursor = base.query(TABLE_NAME_SAVE, SELECT_SAVE_DATA, WHERE_ID, new String[]{Long.toString(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            int id_name = cursor.getColumnIndex(COLUMN_NAME_NAME);
            int id_date = cursor.getColumnIndex(COLUMN_NAME_DATE);
            int id_save = cursor.getColumnIndex(COLUMN_NAME_SAVE);

            String name = cursor.getString(id_name);
            Date date = new Date(cursor.getLong(id_date));
            byte[] blob = cursor.getBlob(id_save);
            SaveRecord saveRecord = new SaveRecord(name, date, blob, id);
            cursor.close();
            base.close();
            return saveRecord;
        } else {
            cursor.close();
            base.close();
            throw new RuntimeException("не найденно сохранение");
        }
    }

    @Override
    public List<SaveRecord> getAllStates(boolean includeNew) {
        SQLiteDatabase base = mDBHelper.getReadableDatabase();
        Cursor cursor = base.query(TABLE_NAME_SAVE, SELECT_ALL_SAVE_COLUNM, null, null, null, null, COLUMN_NAME_DATE + DECS);
        List<SaveRecord> states = new ArrayList<>(cursor.getCount() + 1);
        if (includeNew) {
            SaveRecord newSave = new SaveRecord(context.getString(R.string.new_save), new Date(), WRONG_ID);
            states.add(newSave);
        }
        if (cursor.moveToFirst()) {
            do {

                int id_id = cursor.getColumnIndex(COLUMN_NAME_ID);
                int id_name = cursor.getColumnIndex(COLUMN_NAME_NAME);
                int id_date = cursor.getColumnIndex(COLUMN_NAME_DATE);

                String name = cursor.getString(id_name);
                Date date = new Date(cursor.getLong(id_date));
                long id = cursor.getLong(id_id);
                states.add(new SaveRecord(name, date, id));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return states;
    }

    @Override
    public void close() {
        mDBHelper.close();
    }

    private class DBHelper extends SQLiteOpenHelper {
        private static final int version = 1;
        private static final String dbName = "db.sqlite";

        public DBHelper(Context context) {
            super(context, dbName, null, DBHelper.version);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_SAVE);
        }

        private static final String CREATE_TABLE_SAVE = "create table " + TABLE_NAME_SAVE + " ( " +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME_NAME + " TEXT," +
                COLUMN_NAME_DATE + " INTEGER," +
                COLUMN_NAME_SAVE + " BLOD" +
                ")";

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.delete(TABLE_NAME_SAVE, null, null);
        }


    }
}
