package com.example.therapistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FindTherapist extends AppCompatActivity
{

    TextView tv_findtherapist_type;
    TextView btn_findtherapst_cancel;
    ListView lv_choosetherapist;
    ArrayList<Therapist> therapistArrayList;
    TherapistAdapter adapter;
    DatabaseHelper database;
    Intent int_viewtherapistactivity;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_therapist);
        tv_findtherapist_type = findViewById(R.id.tv_findtherapist_type);
        tv_findtherapist_type.setText("Therapists in " + Session.therapyTypeToQuery + " Therapy");
        btn_findtherapst_cancel = findViewById(R.id.btn_findtherapst_cancel);
        lv_choosetherapist = findViewById(R.id.lv_choosetherapist);
        database = new DatabaseHelper(this);
        therapistArrayList = database.getTherapistsByProfession(Session.therapyTypeToQuery);
        for(int i = 0; i < therapistArrayList.size(); i++)
        {
            Log.d("Therapist", therapistArrayList.get(i).getUser().getFullName());
        }
        adapter = new TherapistAdapter(this, therapistArrayList, database);
        lv_choosetherapist.setAdapter(adapter);
        int_viewtherapistactivity = new Intent(FindTherapist.this, ViewTherapistActivity.class);

        btn_findtherapst_cancelPressEvent();
        listViewPressEvent();
    }

    public void btn_findtherapst_cancelPressEvent()
    {
        btn_findtherapst_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
                Session.therapyTypeToQuery = null;
            }
        });
    }

    public void listViewPressEvent()
    {
        lv_choosetherapist.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Session.therapistBeingViewed = therapistArrayList.get(i);
                startActivity(int_viewtherapistactivity);
            }
        });
    }
}