package com.example.therapistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class UserRegistration extends AppCompatActivity
{

    ImageView imgbtn_registration_cancelregister;
    EditText et_registration_username, et_registration_password, et_registration_fullname;
    Switch sw_registration_istherapist;
    DatabaseHelper database;

    EditText et_registration_therapistage;
    EditText et_registration_therapistgender;
    EditText et_registration_therapistprofession;
    EditText et_registration_therapistlocation;

    TextView tvbtn_registration_register;
    Intent int_homepage;
    private boolean registeringTherapist;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        imgbtn_registration_cancelregister = findViewById(R.id.imgbtn_registration_cancelregister);
        et_registration_fullname = findViewById(R.id.et_registration_fullname);
        et_registration_username = findViewById(R.id.et_registration_username);
        et_registration_password = findViewById(R.id.et_registration_password);
        et_registration_therapistage = findViewById(R.id.et_registration_therapistage);
        et_registration_therapistgender = findViewById(R.id.et_registration_therapistgender);
        et_registration_therapistprofession = findViewById(R.id.et_registration_therapistprofession);
        et_registration_therapistlocation = findViewById(R.id.et_registration_therapistlocation);
        tvbtn_registration_register = findViewById(R.id.tvbtn_registration_register);
        int_homepage = new Intent(UserRegistration.this, HomepageActivity.class);
        sw_registration_istherapist = findViewById(R.id.sw_registration_istherapist);
        database =  new DatabaseHelper(this);
        registeringTherapist = false;

        registerButtonPress();
        cancelButtonPress();
        therapistSwitchPressEvent();
    }

    public void registerButtonPress()
    {
        tvbtn_registration_register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Register Button Press", "Success");
                if(canRegister())
                {
                    registerUser();
                }
            }
        });
    }

    public void cancelButtonPress()
    {
        imgbtn_registration_cancelregister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    public void therapistSwitchPressEvent()
    {
        sw_registration_istherapist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(sw_registration_istherapist.isChecked())
                {
                    registeringTherapist = true;
                    //
                    updateView();
                }
                else
                {
                    registeringTherapist = false;
                    updateView();
                }
            }
        });
    }

    public void updateView()
    {

    }

    public boolean canRegister()
    {
        boolean returnStatement = true;
        if(et_registration_password.getText().toString().equals(""))
        {
            et_registration_password.setError("Field cannot be left blank.");
            returnStatement = false;
        }
        if(et_registration_username.getText().toString().equals(""))
        {
            et_registration_username.setError("Field cannot be left blank.");
            returnStatement = false;
        }
        else // check if username exists
        {
            String username = et_registration_username.getText().toString();
            if(database.usernameExists(username))
            {
                et_registration_username.setError("A user with this username already exists.");
                returnStatement = false;
            }
        }
        if(et_registration_fullname.getText().toString().equals(""))
        {
            et_registration_fullname.setError("Field cannot be left blank.");
            returnStatement = false;
        }
        return returnStatement;
    }

    public void registerUser()
    {
        String username = et_registration_username.getText().toString();
        String password = et_registration_password.getText().toString();
        String fullname = et_registration_fullname.getText().toString();
        boolean isTherapist = false; //this will be implemented later on
        User user = new User(username, password, isTherapist, fullname);
        database.createNewUser(user);
        if(registeringTherapist)
        {
            int therapistAge = Integer.parseInt(et_registration_therapistage.getText().toString());
            String therapistProfession = et_registration_therapistprofession.getText().toString();
            String therapistLocation = et_registration_therapistlocation.getText().toString();
            String therapistGender = et_registration_therapistgender.getText().toString();
            Therapist therapist = new Therapist(user, therapistGender, therapistAge, therapistProfession, therapistLocation);
            database.createNewTherapist(therapist);
        }
        Session.username = username;
        Session.fullname = fullname;
        startActivity(int_homepage);
    }
}