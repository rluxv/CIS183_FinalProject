package com.example.therapistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LeaveReviewActivity extends AppCompatActivity
{

    TextView btn_review_postreview,btn_review_cancel;
    EditText et_review_comments;
    NumberPicker np_review_rating;
    Therapist therapist;
    DatabaseHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_review);
        btn_review_cancel = findViewById(R.id.btn_review_cancel);
        btn_review_postreview = findViewById(R.id.btn_review_postreview);
        et_review_comments = findViewById(R.id.et_review_comments);
        np_review_rating = findViewById(R.id.np_review_rating);
        np_review_rating.setMaxValue(5);
        np_review_rating.setMinValue(1);
        database = new DatabaseHelper(this);

        therapist = Session.therapistBeingViewed;

        postReviewButton();
        cancelButton();

    }

    public void postReviewButton()
    {
        btn_review_postreview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(et_review_comments.getText().toString().equals(""))
                {
                    et_review_comments.setError("Cannot be left blank.");
                }
                else
                {
                    database.saveReview(Session.user, therapist, et_review_comments.getText().toString(), np_review_rating.getValue());
                    Intent intent = new Intent();
                    setResult(1, intent);
                    finish();
                }
            }
        });
    }

    public void cancelButton()
    {
        btn_review_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }
}