package com.korvin.guild.game.ui.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.korvin.guild.R;
import com.korvin.guild.game.GameApplication;
import com.korvin.guild.storage.imp.SaveRecord;

import java.util.List;

import static com.korvin.guild.server.Server.Server;

public class MainTabActivity extends ActionBarActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link android.support.v4.view.ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    public boolean save() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final List<SaveRecord> allStates = GameApplication.getDefaultGameApplication().getDataBase().getAllStates(true);
        final Activity activity = this;
        final String[] names = new String[allStates.size()];
        for (int i = 0; i < allStates.size(); i++) {
            names[i] = allStates.get(i).getName();
        }
        builder.setItems(names, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle(R.string.edit_name);
                    final EditText input = new EditText(activity);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    input.setLayoutParams(lp);
                    builder.setView(input);
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GameApplication.getDefaultGameApplication().saveState(input.getText().toString());
                        }
                    });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                } else {
                    String name = names[which];
                    long id = allStates.get(which - 1).getId();
                    GameApplication.getDefaultGameApplication().saveState(name, id);
                }
            }
        });

        builder.setCancelable(true);
        builder.show();
        return true;
    }


    public boolean load() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final List<SaveRecord> allStates = GameApplication.getDefaultGameApplication().getDataBase().getAllStates(false);
        final String[] names = new String[allStates.size()];
        for (int i = 0; i < allStates.size(); i++) {
            names[i] = allStates.get(i).getName();
        }

        //builder.setTitle(R.string.main_tab_load_title);
        builder.setItems(names, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                long id = allStates.get(which).getId();
                GameApplication.getDefaultGameApplication().loadSave(id);
                recreate();
            }
        });
        builder.setCancelable(true);
        builder.show();
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.main_tab_settings:
                return true;
            case R.id.main_tab_save:
                return save();
            case R.id.main_tab_load:
                return load();
            case R.id.main_tab_next_day:
                Server.Server.initNextDay();
                Intent intent = GameApplication.nextIntent();
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_tab, menu);
        return true;
    }

}
