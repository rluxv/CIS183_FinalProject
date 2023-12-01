package com.example.therapistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HomepageActivity extends AppCompatActivity
{

    TextView tv_username, tv_fullname;
    Therapist therapist;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        tv_username = findViewById(R.id.tv_homepage_username);
        tv_fullname = findViewById(R.id.tv_homepage_fullname);

        tv_username.setText(Session.user.getUsername());
        tv_fullname.setText(Session.fullname);
        dbHelper = new DatabaseHelper(this);

        Log.d("test", "test");
        if(Session.user.isTherapist())
        {
            therapist = dbHelper.getTherapist(Session.user.getUsername());
            Log.d("Test", "" + therapist.getGender());
        }
    }
}