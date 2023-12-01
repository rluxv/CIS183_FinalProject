package com.example.therapistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UserLogin extends AppCompatActivity
{

    EditText et_login_username, et_login_password;
    TextView tvbtn_login_login,tvbtn_login_signup;

    Intent int_registration;
    Intent int_homepage;

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        et_login_password = findViewById(R.id.et_login_password);
        et_login_username = findViewById(R.id.et_login_username);
        tvbtn_login_login = findViewById(R.id.tvbtn_login_login);
        tvbtn_login_signup = findViewById(R.id.tvbtn_login_signup);
        int_registration = new Intent(UserLogin.this, UserRegistration.class);
        int_homepage = new Intent(UserLogin.this, HomepageActivity.class);
        db = new DatabaseHelper(this);

        loginButtonEvent();
        signUpButtonEvent();
    }

    private void loginButtonEvent()
    {
        tvbtn_login_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(textboxesFilled())
                {
                    login();
                }
            }
        });
    }

    public boolean textboxesFilled()
    {
        boolean returnStatement = true;
        if(et_login_password.getText().toString().equalsIgnoreCase(""))
        {
            returnStatement = false;
            et_login_password.setError("Cannot be left blank.");
        }
        if(et_login_username.getText().toString().equalsIgnoreCase(""))
        {
            returnStatement = false;
            et_login_username.setError("Cannot be left blank.");
        }
        return returnStatement;
    }
    public void login()
    {
        String username = et_login_username.getText().toString();
        String password = et_login_password.getText().toString();
        if(db.usernameExists(username))
        {
            Log.d("dbusernameexists", "yes");
            if(db.passwordMatches(username, password))
            {
                Log.d("Login Success", "Successful Login");
                Session.user = db.getUser(username);
                Session.username = username;
                Session.fullname = db.getFullName(username);
                startActivity(int_homepage);
            }
        }
        else
        {
            et_login_username.setError("The username cannot be found.");
        }
    }

    private void signUpButtonEvent()
    {
        tvbtn_login_signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(int_registration);
            }
        });
    }
}