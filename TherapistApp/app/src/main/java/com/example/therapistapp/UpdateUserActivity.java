package com.example.therapistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateUserActivity extends AppCompatActivity
{

    TextView btn_updateuser_confirmupdate;
    TextView btn_updateuser_cancel;
    TextView btn_updateuser_deleteuser;
    TextView tv_update_username, tv_therapist;

    EditText et_update_fullname, et_update_password, et_update_therapistage, et_update_therapistgender, et_update_therapistprofession, et_update_therapistlocation;

    DatabaseHelper database;
    Therapist therapist;

    Intent int_main;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        btn_updateuser_confirmupdate = findViewById(R.id.btn_updateuser_confirmupdate);
        btn_updateuser_cancel = findViewById(R.id.btn_updateuser_cancel);
        tv_update_username = findViewById(R.id.tv_update_username);
        btn_updateuser_deleteuser = findViewById(R.id.btn_updateuser_deleteuser);
        tv_therapist = findViewById(R.id.tv_therapist);
        et_update_fullname = findViewById(R.id.et_update_fullname);
        et_update_password = findViewById(R.id.et_update_password);
        et_update_therapistage = findViewById(R.id.et_update_therapistage);
        et_update_therapistgender = findViewById(R.id.et_update_therapistgender);
        et_update_therapistprofession = findViewById(R.id.et_update_therapistprofession);
        et_update_therapistlocation = findViewById(R.id.et_update_therapistlocation);
        database = new DatabaseHelper(this);
        int_main = new Intent(UpdateUserActivity.this, MainActivity.class);

        et_update_fullname.setText(Session.user.getFullName());
        et_update_password.setText(Session.user.getPassword());

        if(!Session.user.isTherapist())
        {
            tv_therapist.setVisibility(View.INVISIBLE);
            et_update_therapistage.setVisibility(View.INVISIBLE);
            et_update_therapistgender.setVisibility(View.INVISIBLE);
            et_update_therapistprofession.setVisibility(View.INVISIBLE);
            et_update_therapistlocation.setVisibility(View.INVISIBLE);
        }
        else
        {
            therapist = database.getTherapist(Session.user.getUsername());
            et_update_therapistage.setText(therapist.getAge() + "");
            et_update_therapistgender.setText(therapist.getGender());
            et_update_therapistprofession.setText(therapist.getProfession());
            et_update_therapistlocation.setText(therapist.getLocation());
        }


        cancelButtonEvent();
        updateConfirmButtonEvent();
        deleteUserButtonEvent();

        tv_update_username.setText("Updating " + Session.user.getUsername());
    }

    public void cancelButtonEvent()
    {
        btn_updateuser_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent();
                setResult(1, intent);
                finish();
            }
        });
    }

    public void updateConfirmButtonEvent()
    {
        btn_updateuser_confirmupdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(Session.user.isTherapist())
                {
                    therapist.setAge(Integer.parseInt(et_update_therapistage.getText().toString()));
                    therapist.setLocation(et_update_therapistlocation.getText().toString());
                    therapist.setGender(et_update_therapistgender.getText().toString());
                    therapist.setProfession(et_update_therapistprofession.getText().toString());
                    database.updateTherapist(therapist, Session.user.getUsername());
                }
                Session.user.setPassword(et_update_password.getText().toString());
                Session.user.setFullName(et_update_fullname.getText().toString());

                database.updateUser(Session.user);

                Intent intent = new Intent();
                setResult(1, intent);
                finish();
            }
        });
    }

    public void deleteUserButtonEvent()
    {
        btn_updateuser_deleteuser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                database.deleteReviews(Session.user);
                database.deleteUser(Session.user);
                Session.user = null;
                Session.username = null;
                Session.fullname = null;
                startActivity(int_main);
            }
        });
    }
}