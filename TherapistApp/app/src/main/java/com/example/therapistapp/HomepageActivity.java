package com.example.therapistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomepageActivity extends AppCompatActivity
{

    TextView tv_username, tv_fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        tv_username = findViewById(R.id.tv_homepage_username);
        tv_fullname = findViewById(R.id.tv_homepage_fullname);

        tv_username.setText(Session.username);
        tv_fullname.setText(Session.fullname);
    }
}