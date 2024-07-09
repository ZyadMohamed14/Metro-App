package com.example.metroapp;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.metroapp.R;

import java.util.List;

public class MetroAdapter extends BaseAdapter implements SpinnerAdapter {

    private Context context;
    private List<String> stationsList;
    private static final int TYPE_LINE = 0;
    private static final int TYPE_STATION = 1;

    public MetroAdapter(Context context, List<String> stationsList) {
        this.context = context;
        this.stationsList = stationsList;
    }

    @Override
    public int getCount() {
        return stationsList.size();
    }

    @Override
    public Object getItem(int position) {
        return stationsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 6;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
return getCustomView(position,convertView,parent);


    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item, parent, false);
            holder = new ViewHolder();
            holder.headerTextView = view.findViewById(R.id.header);
            holder.itemTextView = view.findViewById(R.id.item);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        String station = stationsList.get(position);
        if(station.equals("MetroLine1")||station.equals("MetroLine2")||station.equals("MetroLine3")||station.equals("Please Select")||station.equals("CarioUniversityBranch")){
          //  holder.headerTextView.setText(station);
        //   holder.headerTextView.setVisibility(View.VISIBLE);
           holder.itemTextView.setTextSize(20);
           holder.itemTextView.setTypeface(null, Typeface.BOLD);
            holder.itemTextView.setText(station);
            return view;
        }

        holder.itemTextView.setText(station);
        holder.itemTextView.setTextSize(18);
        holder.itemTextView.setTypeface(null, Typeface.NORMAL);
         //   holder.itemTextView.setVisibility(View.VISIBLE); // Assuming you want header initially gone




        return view;
    }

    private static class ViewHolder {
        TextView headerTextView;
        TextView itemTextView;
    }
}