package com.example.therapistapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TherapyTypeAdapter extends BaseAdapter
{

    ArrayList<String> therapyTypeList;
    Context context;
    public TherapyTypeAdapter(Context c, ArrayList<String> therList)
    {
        therapyTypeList = therList;
        context = c;
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
            view = mInflator.inflate(R.layout.therapy_cell, null);
        }
        TextView therapytype = view.findViewById(R.id.tv_thercell_type);
        therapytype.setText(therapyTypeList.get(i) + " Therapy");

        return view;
    }
}
