package com.korvin.guild.game;

import android.app.Application;
import android.content.Intent;

import com.korvin.guild.event.Event;
import com.korvin.guild.game.ui.event.statistics.StatisticsActivity;
import com.korvin.guild.game.ui.event.story.StoryActivity;
import com.korvin.guild.game.ui.main.MainTabActivity;
import com.korvin.guild.storage.DataBaseHelper;
import com.korvin.guild.storage.Storage;
import com.korvin.guild.storage.imp.DataBaseHelperImp;
import com.korvin.guild.storage.imp.SaveRecord;

import static com.korvin.guild.server.Server.Server;

public class GameApplication extends Application {
    public static final String EVENT = "Event";
    private static GameApplication defaultGameApplication;
    private Preference preference;
    private Storage storage;
    private DataBaseHelper dataBase;

    public static GameApplication getDefaultGameApplication() {
        return defaultGameApplication;
    }

    public static Intent nextIntent() {
        return defaultGameApplication.getNextIntent();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.storage = new Storage();
        this.dataBase = new DataBaseHelperImp(getApplicationContext());
        this.preference = new Preference(this);
        defaultGameApplication = this;
        if (!preference.isFirstLaunch()) {
            long idAutoSave = preference.getIdAutoSave();
            loadSave(idAutoSave);
        }
    }

    public void loadSave(long idSave) {
        SaveRecord record = this.dataBase.getState(idSave);
        Server.setState(record.getState());
    }

    public long saveState(String name, long idSave) {
        return dataBase.saveState(Server.getState(), name, idSave);
    }


/*    public void init(String playerName, Sex sex, Race race) {
        server.initState(playerName, sex, race);
        long id = saveState(getResources().getString(R.string.autosave));
        this.setFirstLaunch(false, id);
    }*/

    public long saveState(String name) {

        return dataBase.saveState(Server.getState(), name);
    }

    public Intent getNextIntent() {
        Event nextEvent = Server.getNextEvent();
        Intent intent = null;
        switch (nextEvent.getEventType()) {
            case STORY:
                intent = new Intent(this, StoryActivity.class);
                break;
            case STATISTICS:
                intent = new Intent(this, StatisticsActivity.class);
                break;
            case END_DAY:
                intent = new Intent(this, MainTabActivity.class);
                break;
        }
        if (intent != null)
            intent.putExtra(EVENT, nextEvent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    public Storage getStorage() {
        return storage;
    }

    public DataBaseHelper getDataBase() {
        return dataBase;
    }

    public Preference getPreference() {
        return preference;
    }
}
