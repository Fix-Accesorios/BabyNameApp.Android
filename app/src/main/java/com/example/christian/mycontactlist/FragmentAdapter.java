package com.example.christian.mycontactlist;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;

import java.lang.reflect.Array;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    private Context ctx;
    private List<String> data;
    private Fragment[] fragments;
    private int prevPos = 0;
    private int[] used_index;
    public FragmentAdapter(Context ctx, FragmentManager fm, List<String> data) {
        super(fm);
        this.ctx = ctx;
        this.data = data;
        fragments = new Fragment[data.size()];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        String items = data.get(position);
        ScreenSlidePageFragment screenSlidePageFragment = new ScreenSlidePageFragment();
        screenSlidePageFragment.setName(items);
        fragment = screenSlidePageFragment;
        if(fragments[position] == null){
            fragments[position] = fragment;
        }
        return fragments[position];
    }


    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }
}