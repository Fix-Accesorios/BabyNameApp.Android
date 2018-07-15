package com.example.christian.mycontactlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private Fragment fragList = new Fragment();
    private Fragment fragNav = new Fragment();
    static void makeToast(Context ctx, String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * Checks if user is logged in by looking for the token in the fs
     * @return
     */
    protected boolean checkForLogin() {
        String filename = "api_token.txt";
        File directory = getFilesDir();
        File file = null;
        try{
            file = new File(directory, filename);
        }
        catch (Exception e){
            file = null;
            e.printStackTrace();
        }
        if(file != null){
            boolean good = true;
            //Read text from file
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
            }
            catch (IOException e) {
                //You'll need to add proper error handling here
                good = false;
            }
            return good;
        }else{
            return false;
        }



    }


    @Override
    /**
     * If the token exists, the main activity will be loaded
     * otherwise the authenication activity will be started
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        boolean isLoggedin = checkForLogin();
        if(isLoggedin){
            setDefaultView(savedInstanceState);
            initBottomNavMenu();
        }else{
            StartAuthActivity();
        }
    }

    /**
     * If no token, this gets started to get a token
     */
    private void StartAuthActivity(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }

    /**
     * Sets the fragment default view
     * @param savedInstanceState
     */
    private void setDefaultView(Bundle savedInstanceState) {
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            ExploreFragment second = new ExploreFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.fragment_container, second).addToBackStack(null).commit();
        }
    }

    /**
     * Controls for the bottom navigation view
     */
    private void initBottomNavMenu() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_explore) {
                    ExploreFragment exploreFragment = new ExploreFragment();
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.fragment_container, exploreFragment).addToBackStack(null).commit();
                }
                else if(id == R.id.action_refine) {
                    RefineFragment refineFragment = new RefineFragment();
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.fragment_container, refineFragment).addToBackStack(null).commit();
                }
                else if (id == R.id.action_settings) {
                    SettingsFragment settingsFragment = new SettingsFragment();
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.fragment_container, settingsFragment).addToBackStack(null).commit();
                }
                return true;
            }
        });
    }
}
