package com.example.christian.mycontactlist;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScreenSlidePageFragment extends Fragment {
    private TextView slider_name_text;
    private String NAME;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        slider_name_text = (TextView) view.findViewById(R.id.slider_name_text);
        slider_name_text.setText(getName());
    }
    public String getName() {
        return NAME;
    }
    public void setName(String name){
        this.NAME = name;
    }
}
