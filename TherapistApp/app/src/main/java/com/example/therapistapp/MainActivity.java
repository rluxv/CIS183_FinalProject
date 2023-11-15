package com.example.therapistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    Button btn_register;
    Intent registerUserActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_register = findViewById(R.id.btn_main_register);
        registerUserActivity = new Intent(MainActivity.this, UserRegistration.class);

        registerButtonPress();
    }

    public void registerButtonPress()
    {
        btn_register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(registerUserActivity);
            }
        });
    }
}