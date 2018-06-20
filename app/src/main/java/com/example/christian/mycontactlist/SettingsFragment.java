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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

/**
 * A fragment representing a list of Items.
 * interface.
 */
public class SettingsFragment extends Fragment {
    private TextView settingTextView;
    private String url ="http://www.google.com";
    // Instantiate the RequestQueue.
    private RequestQueue queue;
    // Request a string response from the provided URL.
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    settingTextView.setText("Response is: "+ response.substring(0,500));
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            settingTextView.setText("That didn't work!");
        }
    });
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        queue = Volley.newRequestQueue(this.getContext());
        settingTextView = (TextView) view.findViewById(R.id.settingTextView);
        queue.add(stringRequest);
        return view;
    }
}
