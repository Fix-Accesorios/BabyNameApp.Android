package com.example.christian.mycontactlist;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyClassAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList mSwipeCardArrayList;

    public MyClassAdapter(Context context, LayoutInflater layoutInflater, ArrayList<GenderModel> swipeCardArraylist) {
        this.mContext = context;
        this.mLayoutInflater = layoutInflater;
        this.mSwipeCardArrayList = swipeCardArraylist;
    }

    @Override
    public int getCount() {
        return mSwipeCardArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSwipeCardArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.fragment_screen_slide_page, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.slider_name_text);
            viewHolder.layout = (ConstraintLayout) convertView.findViewById(R.id.fragment_container_2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GenderModel sw = (GenderModel) mSwipeCardArrayList.get(position);
        if(sw.GenderId == 1){
            viewHolder.layout.setBackground(mContext.getResources().getDrawable(R.drawable.border_boy));
        } else if (sw.GenderId == 2){
            viewHolder.layout.setBackground(mContext.getResources().getDrawable(R.drawable.border_girl));
        }else{
            viewHolder.layout.setBackground(mContext.getResources().getDrawable(R.drawable.border_both));
        }
        viewHolder.textView1.setText(sw.Name);
        viewHolder.textView1.setTextColor(this.mContext.getResources().getColor(R.color.colorTextWhite));

        return convertView;
    }

    private static class ViewHolder {
        public TextView textView1;
        public android.support.constraint.ConstraintLayout layout;
    }
}
