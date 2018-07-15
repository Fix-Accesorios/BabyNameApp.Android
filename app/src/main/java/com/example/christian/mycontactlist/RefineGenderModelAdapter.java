package com.example.christian.mycontactlist;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RefineGenderModelAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList mSwipeCardArrayList;

    public RefineGenderModelAdapter(Context context, LayoutInflater layoutInflater, ArrayList<RefineGenderModel> swipeCardArraylist) {
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
    /**
     * Sets the values from the model to the fragment
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.refine_screen_fragment, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.slider_name_text);
            viewHolder.positiveCount = (TextView) convertView.findViewById(R.id.positive_count);
            viewHolder.negativeCount = (TextView) convertView.findViewById(R.id.negative_count);
            viewHolder.layout = (ConstraintLayout) convertView.findViewById(R.id.fragment_container_2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        RefineGenderModel sw = (RefineGenderModel) mSwipeCardArrayList.get(position);
        if(sw.GenderId == 1){
            viewHolder.layout.setBackground(mContext.getResources().getDrawable(R.drawable.border_boy));
        } else if (sw.GenderId == 2){
            viewHolder.layout.setBackground(mContext.getResources().getDrawable(R.drawable.border_girl));
        }else{
            viewHolder.layout.setBackground(mContext.getResources().getDrawable(R.drawable.border_both));
        }
        viewHolder.textView1.setText(sw.Name);
        viewHolder.positiveCount.setText(String.valueOf(sw.PositiveCount));
        viewHolder.negativeCount.setText(String.valueOf(sw.NegativeCount));
        viewHolder.textView1.setTextColor(this.mContext.getResources().getColor(R.color.colorTextWhite));

        return convertView;
    }
    /**
     * Holds the values for the fragment
     */
    private static class ViewHolder {
        public TextView textView1;
        public TextView positiveCount;
        public TextView negativeCount;
        public ConstraintLayout layout;
    }
}
