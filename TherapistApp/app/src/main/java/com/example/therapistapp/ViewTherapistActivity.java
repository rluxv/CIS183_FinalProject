package com.example.therapistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewTherapistActivity extends AppCompatActivity
{

    TextView tv_viewtherapist_fullname;
    TextView btn_viewtherapist_review, btn_viewtherapist_goback;

    ListView lv_reviews;
    Intent int_leavereview;
    ArrayList<Review> reviewArrayList;
    DatabaseHelper database;

    ReviewListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_therapist);
        tv_viewtherapist_fullname = findViewById(R.id.tv_viewtherapist_fullname);
        tv_viewtherapist_fullname.setText(Session.therapistBeingViewed.getUser().getFullName());
        btn_viewtherapist_review = findViewById(R.id.btn_viewtherapist_review);
        btn_viewtherapist_goback = findViewById(R.id.btn_viewtherapist_goback);
        lv_reviews = findViewById(R.id.lv_reviews);
        reviewTherapistButtonEvent();
        goBackButton();
        int_leavereview = new Intent(ViewTherapistActivity.this, LeaveReviewActivity.class);
        database = new DatabaseHelper(this);
        reviewArrayList = database.getReviews(Session.therapistBeingViewed);
        adapter = new ReviewListAdapter(this, reviewArrayList);
        lv_reviews.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1)
        {
            reviewArrayList = database.getReviews(Session.therapistBeingViewed);
            adapter = new ReviewListAdapter(this, reviewArrayList);
            lv_reviews.setAdapter(adapter);
        }
    }

    public void reviewTherapistButtonEvent()
    {
        btn_viewtherapist_review.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivityForResult(int_leavereview, 1);
            }
        });
    }

    public void goBackButton()
    {
        btn_viewtherapist_goback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
                Session.therapistBeingViewed = null;
            }
        });
    }
}