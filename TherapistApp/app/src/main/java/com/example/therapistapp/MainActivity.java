package com.example.therapistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    TextView tvbtn_main_register;
    Intent registerUserActivity, loginActivity;
    TextView tvbtn_main_login;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvbtn_main_register = findViewById(R.id.tvbtn_main_register);
        registerUserActivity = new Intent(MainActivity.this, UserRegistration.class);
        loginActivity = new Intent(MainActivity.this, UserLogin.class);
        tvbtn_main_login = findViewById(R.id.tvbtn_main_login);
        registerButtonPress();
        loginButtonPressEvent();
    }

    public void registerButtonPress()
    {
        tvbtn_main_register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(registerUserActivity);
            }
        });
    }

    public void loginButtonPressEvent()
    {
        tvbtn_main_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(loginActivity);
            }
        });
    }
}