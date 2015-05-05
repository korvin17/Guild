package com.korvin.guild.game.ui.event.story;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.korvin.guild.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class StoryActivityFragment extends Fragment {

    public StoryActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_story, container, false);
    }
}
