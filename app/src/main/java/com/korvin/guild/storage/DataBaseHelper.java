package com.korvin.guild.storage;


import com.korvin.guild.server.State;
import com.korvin.guild.storage.imp.SaveRecord;

import java.util.List;


/**
 * Created by Korvin on 01.05.2015.
 */
public interface DataBaseHelper {
    int WRONG_ID = -1;

    long saveState(State state, String name);

    long saveState(State state, String name, long id);

    SaveRecord getState(long id);

    List<SaveRecord> getAllStates(boolean includeNew);

    void close();
}
