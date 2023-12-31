package com.example.therapistapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TherapistAdapter extends BaseAdapter
{

    ArrayList<Therapist> therapyTypeList;
    Context context;
    DatabaseHelper database;
    public TherapistAdapter(Context c, ArrayList<Therapist> therList, DatabaseHelper db)
    {
        therapyTypeList = therList;
        context = c;
        database = db;
    }

    @Override
    public int getCount()
    {
        return therapyTypeList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return therapyTypeList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view == null)
        {
            LayoutInflater mInflator = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflator.inflate(R.layout.therapist_cell, null);
        }
        TextView therapistname = view.findViewById(R.id.tv_therapistcell_name);
        TextView rating = view.findViewById(R.id.tv_therapistcell_rating);
        float ratingNum = database.getRating(therapyTypeList.get(i));
        rating.setText(ratingNum + "/5");
        therapistname.setText(therapyTypeList.get(i).getUser().getFullName());

        return view;
    }
}
