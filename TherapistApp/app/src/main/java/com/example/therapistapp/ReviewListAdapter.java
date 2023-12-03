package com.example.therapistapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReviewListAdapter extends BaseAdapter
{

    ArrayList<Review> reviewsList;
    Context context;
    public ReviewListAdapter(Context c, ArrayList<Review> revList)
    {
        reviewsList = revList;
        context = c;
    }

    @Override
    public int getCount()
    {
        return reviewsList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return reviewsList.get(i);
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
            view = mInflator.inflate(R.layout.review_cell, null);
        }

        TextView reviewer = view.findViewById(R.id.tv_reviewcell_reviewer);
        TextView rating = view.findViewById(R.id.tv_reviewcell_rating);
        TextView comments = view.findViewById(R.id.tv_reviewcell_comments);

        reviewer.setText("Reviewed by " + reviewsList.get(i).getReviewer());
        rating.setText("Rating: " + reviewsList.get(i).getRating() + "/5");
        comments.setText("Comments: " + reviewsList.get(i).getComments());

        return view;
    }
}
