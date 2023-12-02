package com.example.therapistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewTherapistActivity extends AppCompatActivity
{

    TextView tv_viewtherapist_fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_therapist);
        tv_viewtherapist_fullname = findViewById(R.id.tv_viewtherapist_fullname);
        tv_viewtherapist_fullname.setText(Session.therapistBeingViewed.getUser().getFullName());
    }
}