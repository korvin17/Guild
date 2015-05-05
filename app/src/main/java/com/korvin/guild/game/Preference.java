package com.korvin.guild.game;

import android.content.SharedPreferences;

import com.korvin.guild.storage.DataBaseHelper;

import static android.content.Context.MODE_PRIVATE;

public class Preference {
    private static final String PREFERENCE_IS_FIRST = "is_first";
    private static final String PREFERENCE_ID_AUTO_SAVE = "auto_save id";
    private static final String PREFERENCES = "game preferences";
    private final SharedPreferences preferences;

    public Preference(GameApplication application) {
        preferences = application.getSharedPreferences(PREFERENCES, MODE_PRIVATE);
    }

    public boolean isFirstLaunch() {
        return this.preferences.getBoolean(PREFERENCE_IS_FIRST, true);
    }

    public void setFirstLaunch(long autoSaveID) {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putBoolean(PREFERENCE_IS_FIRST, false);
        editor.putLong(PREFERENCE_ID_AUTO_SAVE, autoSaveID);
        editor.commit();
    }

    public long getIdAutoSave() {
        return this.preferences.getLong(PREFERENCE_ID_AUTO_SAVE, DataBaseHelper.WRONG_ID);
    }
}