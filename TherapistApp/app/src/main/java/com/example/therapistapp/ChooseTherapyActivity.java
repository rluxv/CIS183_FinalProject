package com.example.therapistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChooseTherapyActivity extends AppCompatActivity
{

    ListView lv_choosetherapy;
    TextView btn_choosetherapy_cancel;
    ArrayList<String> therapyTypeList;
    DatabaseHelper database;
    TherapyTypeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_therapy);
        lv_choosetherapy = findViewById(R.id.lv_choosetherapy);
        btn_choosetherapy_cancel = findViewById(R.id.btn_choosetherapy_cancel);
        database = new DatabaseHelper(this);
        therapyTypeList = database.getTherapyTypes();
        adapter = new TherapyTypeAdapter(this, therapyTypeList);
        lv_choosetherapy.setAdapter(adapter);
        cancelButtonEvent();
    }

    public void cancelButtonEvent()
    {
        btn_choosetherapy_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }
}