package com.korvin.guild.game.ui.event.story;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.korvin.guild.R;
import com.korvin.guild.event.story.StoryEvent;
import com.korvin.guild.event.story.StoryItem;
import com.korvin.guild.game.GameApplication;

import static com.korvin.guild.game.GameApplication.EVENT;

public class StoryActivity extends ActionBarActivity implements StoryChoiceFragment.OnFragmentInteractionListener {
    private StoryEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        event = (StoryEvent) getIntent().getSerializableExtra(EVENT);
        if (savedInstanceState == null) {
            StoryItem storyItem = event.getStoryItem();
            Fragment newFragment = StoryChoiceFragment.newInstance(storyItem);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.layout, newFragment).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_story, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_end) {
            Intent intent = GameApplication.nextIntent();
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionSelected(Object way) {
        event.selectedOption(way);
        nextPage();
    }

    private void nextPage() {
        StoryItem storyItem = event.getStoryItem();
        Fragment newFragment = StoryChoiceFragment.newInstance(storyItem);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.layout, newFragment);
        ft.commit();
    }

    @Override
    public void onNextPage() {
        event.selectedNextPage();
        nextPage();
    }

}
