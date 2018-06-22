//package com.example.christian.mycontactlist;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SliderPage extends FragmentActivity {
//    private List<String> listItem = new ArrayList<>();
//    private FragmentAdapter fragmentAdapter;
//    private String url = "http://192.168.1.111:8080/names/1/100";
//    private Context ctx;
//    /**
//     * The number of pages (wizard steps) to show in this demo.
//     */
//    private static final int NUM_PAGES = 5;
//
//    /**
//     * The pager widget, which handles animation and allows swiping horizontally to access previous
//     * and next wizard steps.
//     */
//    private ViewPager mPager;
//
//    /**
//     * The pager adapter, which provides the pages to the view pager widget.
//     */
//    private PagerAdapter mPagerAdapter;
//    ViewPager.OnPageChangeListener mPageListener = new ViewPager.OnPageChangeListener() {
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
//        @Override
//        public void onPageSelected(int position) {
//        }
//        @Override
//        public void onPageScrollStateChanged(int state) { }
//    };
//    // Request a string response from the provided URL.
//    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//            new Response.Listener<JSONArray>() {
//                @Override
//                public void onResponse(JSONArray response) {
//                    listItem = new ArrayList<String>();
//                    // Process the JSON
//                    try {
//                        // Loop through the array elements
//                        for (int i = 0; i < response.length(); i++) {
//                            // Get current json object
//                            JSONObject name_obj = response.getJSONObject(i);
//
//                            // Get the current student (json object) data
//                            String name = name_obj.getString("name");
//                            // String gender = (name_obj.getInt("gender_id") == 1) ? "Boy" : "Female";
//                            listItem.add(name);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    setData();
//                }
//            },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    // Do something when error occurred
//                    CharSequence text = "Could not fetch names";
//                    int duration = Toast.LENGTH_SHORT;
//                    Toast toast = Toast.makeText(ctx, text, duration);
//                    toast.show();
//                }
//            }
//    );
//    // Instantiate the RequestQueue.
//    private RequestQueue queue;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_screen_slide);
//        queue = Volley.newRequestQueue(this.getApplicationContext());
//        queue.add(jsonArrayRequest);
//    }
//
//
//    public void setData(){
//        // Instantiate a ViewPager and a PagerAdapter.
//        mPager = (ViewPager) findViewById(R.id.pager);
//        FragmentManager fragmentManager = this.getSupportFragmentManager();
//        fragmentAdapter = new FragmentAdapter(this.getApplicationContext(), fragmentManager, listItem);
//        mPager.setAdapter(fragmentAdapter);
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (mPager.getCurrentItem() == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            super.onBackPressed();
//        } else {
//            // Otherwise, select the previous step.
//            mPager.setCurrentItem(mPager.getCurrentItem() + 1);
//        }
//    }
//
//}