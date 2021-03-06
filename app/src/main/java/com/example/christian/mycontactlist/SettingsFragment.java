package com.example.christian.mycontactlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;

/**
 * A fragment representing a list of Items.
 * interface.
 */
public class SettingsFragment extends Fragment {
    private TextView settingTextView;
    private Button logOutBtn;
    private Button aboutBtn;
    private View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        v = view;

        GetElements(view);
        SetEvents();
        return view;
    }

    /**
     * Gets the elements from the fragment
     * @param view
     */
    private void GetElements(View view){
        logOutBtn = (Button) view.findViewById(R.id.logOutBtn);
        aboutBtn = (Button) view.findViewById(R.id.aboutBtn);
    }

    /**
     * Sets click events for the view
     */
    private void SetEvents(){
        SetLogOutBtn();
        SetAboutBtn();
    }

    /**
     * Deletes the api token from the file system
     * @param v
     * @return
     */
    protected boolean deleteApiToken(View v) {
        String filename = "api_token.txt";
        File dir = v.getContext().getFilesDir();
        File file = new File(dir, filename);
        return file.delete();
    }

    /**
     * Restarts activity to check for authentication
     */
    private void RestartActivity(){
        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        startActivity(intent);
    }

    /**
     * invokes the about activity
     */
    private void StartAboutActivity(){
        Intent intent = new Intent(this.getActivity(), AboutActivity.class);
        startActivity(intent);
    }

    /**
     * Logs the user out the application
     */
    private void SetLogOutBtn() {
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean deleted = deleteApiToken(v);
                if(deleted){
                    RestartActivity();
                }
            }
        });
    }

    /**
     * lets the user view the about screen
     */
    private void SetAboutBtn() {
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StartAboutActivity();
            }
        });
    }

}
