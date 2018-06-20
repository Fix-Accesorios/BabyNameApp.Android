
package com.example.christian.mycontactlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class ScreenSlidePagerActivity extends Fragment implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private List<String> listItem = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_screen_slide, container, false);

        viewPager = view.findViewById(R.id.pager);

        setData();
        return view;
    }
    private void setData(){
        listItem.add("Christian");
        listItem.add("Samantha");
        listItem.add("Eden");
        listItem.add("Marino");
        listItem.add("Christina");
        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(this.getContext(), fragmentManager, listItem);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setCurrentItem(0);
        setUiPageViewController();

    }
    private void setUiPageViewController(){

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
