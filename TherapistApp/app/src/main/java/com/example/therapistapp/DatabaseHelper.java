package com.example.therapistapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "TherapistDB";
    public static final String THERAPIST_TABLE_NAME = "Therapists";
    public static final String USERS_TABLE_NAME = "Users";
    public static final String REVIEWS_TABLE_NAME = "Reviews";
    public static final String PROFESSION_TABLE_NAME = "Professions";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        //Create Users Table
        //istherapist 0 = false, 1 = true
        sqLiteDatabase.execSQL("CREATE TABLE " + USERS_TABLE_NAME + " (username PRIMARY KEY NOT NULL, fullname TEXT, password TEXT, istherapist INTEGER NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE " + REVIEWS_TABLE_NAME + " (therapistusername PRIMARY KEY NOT NULL, reviewerusername TEXT, rating INTEGER, comments TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
}
