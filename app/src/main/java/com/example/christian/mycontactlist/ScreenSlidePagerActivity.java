package com.example.christian.mycontactlist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ScreenSlidePagerActivity extends Fragment {

    private ArrayList<GenderModel> al;
    private MyClassAdapter arrayAdapter;
    private int i;
    private SwipeFlingAdapterView flingContainer;
    private Context ctx;
    RequestQueue queue;
    String url = "https://murmuring-lake-56352.herokuapp.com/names";
    static void makeToast(Context ctx, String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private JsonArrayRequest GetNames(){
         return new JsonArrayRequest(
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
                                GenderModel model = new GenderModel();
                                // Get the current student (json object) data
                                String name = name_obj.getString("name");
                                String gender = (name_obj.getInt("gender_id") == 1) ? "Boy" : "Female";
                                int gender_id = name_obj.getInt("gender_id");
                                int id = name_obj.getInt("id");
                                model.Name = name;
                                model.Gender = gender;
                                model.GenderId = gender_id;
                                model.Id = id;
                                al.add(model);
                                // Display the formatted json data in text view

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred

                    }
                }

        );
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_screen_slide, container, false);
        queue = Volley.newRequestQueue(view.getContext());

        flingContainer = (SwipeFlingAdapterView) view.findViewById(R.id.frame);

        ctx = this.getContext();
        al = new ArrayList<>();

        if(isNetworkConnected()){
            queue.add(GetNames());
            arrayAdapter = new MyClassAdapter(this.getActivity(), inflater, al);
            flingContainer.setAdapter(arrayAdapter);


            flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
                @Override
                public void removeFirstObjectInAdapter() {
                    // this is the simplest way to delete an object from the Adapter (/AdapterView)
                    Log.d("LIST", "removed object!");
                    al.remove(0);
                    arrayAdapter.notifyDataSetChanged();
                }

                @Override
                public void onLeftCardExit(Object dataObject) {
                    //Do something on the left!
                    //You also have access to the original object.
                    //If you want to use it just cast it (String) dataObject
                    makeToast(ScreenSlidePagerActivity.this.getContext(), "Left!");
                }

                @Override
                public void onRightCardExit(Object dataObject) {
                    makeToast(ScreenSlidePagerActivity.this.getContext(), "Right!");
                }

                @Override
                public void onAdapterAboutToEmpty(int itemsInAdapter) {
                    // Ask for more data here
                    queue.add(GetNames());
                }

                @Override
                public void onScroll(float scrollProgressPercent) {
                    View view = flingContainer.getSelectedView();
//                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
//                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
                }
            });
            // Optionally add an OnItemClickListener
            flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
                @Override
                public void onItemClicked(int itemPosition, Object dataObject) {
                    makeToast(ScreenSlidePagerActivity.this.getContext(), "Clicked!");
                }
            });
        }else{
            makeToast(view.getContext(), "No Internet Access");
        }
        return view;
    }


}
