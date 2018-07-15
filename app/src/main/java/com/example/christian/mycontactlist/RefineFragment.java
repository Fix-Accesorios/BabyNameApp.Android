package com.example.christian.mycontactlist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RefineFragment extends Fragment {

    private ArrayList<RefineGenderModel> al;
    private RefineGenderModelAdapter arrayAdapter;
    private int i;
    private SwipeFlingAdapterView flingContainer;
    private Context ctx;
    private ImageButton boyBtn;
    private ImageButton girlBtn;
    private ImageButton bothBtn;
    String getGender = "3";
    private ConstraintLayout mainContainer;
    RequestQueue queue;
    String url = "https://murmuring-lake-56352.herokuapp.com/refine";
    //String url = "http://172.18.47.49:8080/refine";
    static void makeToast(Context ctx, String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }
    private void clearNames(){
        al.clear();
        arrayAdapter.notifyDataSetChanged();
    }
    SwipeFlingAdapterView.onFlingListener onFlingListener = new SwipeFlingAdapterView.onFlingListener() {
        @Override
        public void removeFirstObjectInAdapter() {
            // this is the simplest way to delete an object from the Adapter (/AdapterView)
            al.remove(0);
            arrayAdapter.notifyDataSetChanged();
        }

        @Override
        public void onLeftCardExit(Object dataObject) {
            //Do something on the left!
            //You also have access to the original object.
            //If you want to use it just cast it (String) dataObject
            RefineGenderModel model = (RefineGenderModel)dataObject;
            if(isNetworkConnected()){
                queue.add(SetNameVote(model, "2"));
            }else{
                makeToast(RefineFragment.this.getContext(), "No Internet Access");
            }
        }

        @Override
        public void onRightCardExit(Object dataObject) {

            RefineGenderModel model = (RefineGenderModel)dataObject;
            if(isNetworkConnected()){
                queue.add(SetNameVote(model, "1"));
            }else{
                makeToast(RefineFragment.this.getContext(), "No Internet Access");
            }
        }

        @Override
        public void onAdapterAboutToEmpty(int itemsInAdapter) {
            // Ask for more data here
            if(isNetworkConnected()){
                if(itemsInAdapter == 1){
                    queue.add(GetNames());
                }
                //makeToast(RefineFragment.this.ctx, String.valueOf(itemsInAdapter));

            }else{
                makeToast(RefineFragment.this.getContext(), "No Internet Access");
            }

        }

        @Override
        public void onScroll(float scrollProgressPercent) {
            View view = flingContainer.getSelectedView();
//                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
//                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
        }
    };
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    protected String getToken() {
        String filename = "api_token.txt";
        File directory = getContext().getFilesDir();
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
            return text.toString();
        }else{
            return "";
        }



    }


    private StringRequest SetNameVote(RefineGenderModel model, String direction){
        String post_url = "https://murmuring-lake-56352.herokuapp.com/refine";
        //String post_url = "http://172.18.47.49:8080/refine";
        post_url += "/" + String.valueOf(model.Id);
        post_url += "/" + direction;
        final String token = getToken();
        return new StringRequest(Request.Method.POST, post_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        makeToast(getView().getContext(), "Could not post name vote...");
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("x-access-token", token);
                return headers;
            }
        };
    }



    private JsonArrayRequest GetNames(){
        String get_url = url + "/" + getGender;
        makeToast(this.ctx, "fetching");
        final String token = getToken();
        return new JsonArrayRequest(
                Request.Method.GET,
                get_url,
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
                                RefineGenderModel model = new RefineGenderModel();
                                // Get the current student (json object) data
                                String name = name_obj.getString("name");
                                String gender = (name_obj.getInt("gender_id") == 1) ? "Boy" : "Female";
                                int gender_id = name_obj.getInt("gender_id");
                                int id = name_obj.getInt("id");
                                int name_id = name_obj.getInt("name_id");
                                int user_id = name_obj.getInt("user_id");
                                int pCount = name_obj.getInt("positive_count");
                                int nCount = name_obj.getInt("negative_count");
                                model.Name = name;
                                model.Gender = gender;
                                model.GenderId = gender_id;
                                model.UserId = user_id;
                                model.NameId = name_id;
                                model.NegativeCount = nCount;
                                model.PositiveCount = pCount;
                                model.Id = id;
                                al.add(model);
                                // Display the formatted json data in text view

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //onFlingListener.removeFirstObjectInAdapter();
                        arrayAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred

                    }
                }

        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("x-access-token", token);
                return headers;
            }
        };
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.refine_container_fragment, container, false);
        queue = Volley.newRequestQueue(view.getContext());

        flingContainer = (SwipeFlingAdapterView) view.findViewById(R.id.frame);

        ctx = this.getContext();
        al = new ArrayList<>();

        boyBtn = (ImageButton) view.findViewById(R.id.boyBtn);
        girlBtn = (ImageButton) view.findViewById(R.id.girlBtn);
        bothBtn = (ImageButton) view.findViewById(R.id.mixBtn);
        mainContainer = (ConstraintLayout) getActivity().findViewById(R.id.main_activity_container);
        if(isNetworkConnected()){
            arrayAdapter = new RefineGenderModelAdapter(this.getActivity(), inflater, al);
            flingContainer.setAdapter(arrayAdapter);
            flingContainer.setFlingListener(onFlingListener);
            queue.add(GetNames());
        }else{
            makeToast(view.getContext(), "No Internet Access");
        }
        SetEvents();
        return view;
    }

    private void SetEvents(){
        SetBoyBtnClick();
        SetGirlBtnClick();
        SetMixBtnClick();
    }
    private void SetBoyBtnClick(){
        boyBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(getContext().getResources() != null) {
                    clearNames();
                    mainContainer.setBackground(getActivity().getResources().getDrawable(R.drawable.gradient_blue));
                    getGender = "1";
                    if (isNetworkConnected()) {
                        queue.add(GetNames());
                    } else {
                        makeToast(getContext(), "No Internet Access");
                    }
                }
            }
        });
    }
    private void SetGirlBtnClick(){
        girlBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(getContext().getResources() != null) {
                    clearNames();
                    mainContainer.setBackground(getActivity().getResources().getDrawable(R.drawable.gradient_pink));
                    getGender = "2";
                    if (isNetworkConnected()) {
                        queue.add(GetNames());
                    } else {
                        makeToast(getContext(), "No Internet Access");
                    }
                }
            }
        });
    }
    private void SetMixBtnClick(){
        bothBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(getContext().getResources() != null){
                    clearNames();
                    mainContainer.setBackground(getContext().getResources().getDrawable(R.drawable.gradient_mix));
                    getGender = "3";
                    if(isNetworkConnected()){
                        queue.add(GetNames());
                    }else{
                        makeToast(getContext(), "No Internet Access");
                    }
                }
            }
        });
    }

}
