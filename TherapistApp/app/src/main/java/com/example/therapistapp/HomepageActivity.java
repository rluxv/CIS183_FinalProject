package com.example.therapistapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class HomepageActivity extends AppCompatActivity
{

    TextView tv_username, tv_fullname;
    Therapist therapist;
    DatabaseHelper dbHelper;
    TextView btn_homepage_findtherapist, btn_homepage_updateuser, btn_homepage_logout;

    Intent int_findtherapistactivity;
    Intent int_mainactivity;
    Intent int_updateuseractivity;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        tv_username = findViewById(R.id.tv_homepage_username);
        tv_fullname = findViewById(R.id.tv_homepage_fullname);
        btn_homepage_findtherapist = findViewById(R.id.btn_homepage_findtherapist);
        btn_homepage_updateuser = findViewById(R.id.btn_homepage_updateuser);
        btn_homepage_logout = findViewById(R.id.btn_homepage_logout);

        tv_username.setText(Session.user.getUsername());
        tv_fullname.setText(Session.fullname);
        dbHelper = new DatabaseHelper(this);
        int_findtherapistactivity = new Intent(HomepageActivity.this, ChooseTherapyActivity.class);
        int_mainactivity = new Intent(HomepageActivity.this, MainActivity.class);
        int_updateuseractivity = new Intent(HomepageActivity.this, UpdateUserActivity.class);
        Log.d("test", "test");
        if(Session.user.isTherapist())
        {
            therapist = dbHelper.getTherapist(Session.user.getUsername());
            Log.d("Test", "" + therapist.getGender());
        }

        findTherapistButtonEvent();
        logoutButtonEvent();
        updateUserButtonEvent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1)
        {
            tv_username.setText(Session.user.getUsername());
            tv_fullname.setText(Session.user.getFullName());
        }
    }

    public void findTherapistButtonEvent()
    {
        btn_homepage_findtherapist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(int_findtherapistactivity);
            }
        });
    }

    public void logoutButtonEvent()
    {
        btn_homepage_logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Session.user = null;
                Session.username = null;
                Session.fullname = null;
                startActivity(int_mainactivity);
            }
        });
    }

    public void updateUserButtonEvent()
    {
        btn_homepage_updateuser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivityForResult(int_updateuseractivity, 1);
            }
        });
    }
}