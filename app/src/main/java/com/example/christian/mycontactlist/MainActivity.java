package com.example.christian.mycontactlist;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


public class MainActivity extends AppCompatActivity {

    private Fragment fragList = new Fragment();
    private Fragment fragNav = new Fragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        setDefaultView(savedInstanceState);
        initBottomNavMenu();
    }
    private void setDefaultView(Bundle savedInstanceState){
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            ScreenSlidePagerActivity second = new ScreenSlidePagerActivity();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.fragment_container, second).addToBackStack(null).commit();
        }
    }
    private void initBottomNavMenu() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.action_list) {
                    ContactListItemFragment first = new ContactListItemFragment();
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.fragment_container, first).addToBackStack(null).commit();
                }
                else if(id == R.id.action_search) {
                    ScreenSlidePagerActivity second = new ScreenSlidePagerActivity();
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.fragment_container, second).addToBackStack(null).commit();
                }
                else if(id == R.id.action_settings){
                    SettingsFragment third = new SettingsFragment();
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.fragment_container, third).addToBackStack(null).commit();
                }
                return true;
            }
        });
    }
}
