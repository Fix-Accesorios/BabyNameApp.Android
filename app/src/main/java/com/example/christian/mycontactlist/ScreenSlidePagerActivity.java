package com.example.christian.mycontactlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ScreenSlidePagerActivity extends Fragment {
    private ViewPager mViewPager;
    private List<String> listItem = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;
    private String url = "http://192.168.1.111:8080/names/1/100";
    private Context ctx;
    // Request a string response from the provided URL.
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    listItem = new ArrayList<String>();
                    // Process the JSON
                    try {
                        // Loop through the array elements
                        for (int i = 0; i < response.length(); i++) {
                            // Get current json object
                            JSONObject name_obj = response.getJSONObject(i);

                            // Get the current student (json object) data
                            String name = name_obj.getString("name");
                            // String gender = (name_obj.getInt("gender_id") == 1) ? "Boy" : "Female";
                            listItem.add(name);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    setData();
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Do something when error occurred
                    CharSequence text = "Could not fetch names";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(ctx, text, duration);
                    toast.show();
                }
            }
    );
    private boolean isDisliked = false;
    private int mProgress = 0;
    ViewPager.OnPageChangeListener mPageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            if (position < mProgress) {
                isDisliked = true;
                sendToastEvent(position + 1, "Hated");
                mViewPager.setCurrentItem(mProgress + 1, true);
            } else {
                if (!isDisliked)
                    sendToastEvent(position - 1, "Loved");
                else
                    isDisliked = false;
                mProgress = position;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
    // Instantiate the RequestQueue.
    private RequestQueue queue;

    private void sendToastEvent(int position, String preText) {
        // Do something when error occurred
        CharSequence text = preText + " " + listItem.get(position);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(ctx, text, duration);
        toast.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_screen_slide, container, false);
        mViewPager = view.findViewById(R.id.pager);
        ctx = this.getContext();
        queue = Volley.newRequestQueue(this.getContext());
        queue.add(jsonArrayRequest);
        return view;
    }

    private void setData() {
        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(this.getContext(), fragmentManager, listItem);
        mViewPager.setAdapter(fragmentAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(mPageListener);
    }
}
