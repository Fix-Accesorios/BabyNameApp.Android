package com.example.christian.mycontactlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A fragment representing a list of Items.
 * interface.
 */
public class SettingsFragment extends Fragment {
    private TextView settingTextView;
    private String url = "http:/35.9.99.211:8081/names/1/100/";
    // Request a string response from the provided URL.
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    // Do something with response
                    //mTextView.setText(response.toString());

                    // Process the JSON
                    try {
                        // Loop through the array elements
                        for (int i = 0; i < response.length(); i++) {
                            // Get current json object
                            JSONObject name_obj = response.getJSONObject(i);

                            // Get the current student (json object) data
                            String name = name_obj.getString("name");
                            String gender = (name_obj.getInt("gender_id") == 1) ? "Boy" : "Female";

                            // Display the formatted json data in text view
                            settingTextView.append(name + " - " + gender);
                            settingTextView.append("\n");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Do something when error occurred
                    settingTextView.setText("This did not work.");
                }
            }
    );
    // Instantiate the RequestQueue.
    private RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        queue = Volley.newRequestQueue(this.getContext());
        settingTextView = (TextView) view.findViewById(R.id.settingTextView);
        queue.add(jsonArrayRequest);
        return view;
    }
}
