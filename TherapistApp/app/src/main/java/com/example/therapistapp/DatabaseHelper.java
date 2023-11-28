package com.example.therapistapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
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
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        //Create Users Table
        //istherapist 0 = false, 1 = true
        sqLiteDatabase.execSQL("CREATE TABLE " + USERS_TABLE_NAME + " (username PRIMARY KEY NOT NULL, fullname TEXT, password TEXT, istherapist INTEGER NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE " + THERAPIST_TABLE_NAME + " (username PRIMARY KEY NOT NULL, gender TEXT, age INTEGER, profession TEXT, location TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE " + REVIEWS_TABLE_NAME + " (therapistusername PRIMARY KEY NOT NULL, reviewerusername TEXT, rating INTEGER, comments TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE " + PROFESSION_TABLE_NAME + " (professionname PRIMARY KEY NOT NULL);");

        //create a dummy user
        sqLiteDatabase.execSQL("INSERT INTO USRSTABLENAME VALUES('admin','admin','admin','0');".replace("USRSTABLENAME", USERS_TABLE_NAME));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + THERAPIST_TABLE_NAME + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + REVIEWS_TABLE_NAME + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PROFESSION_TABLE_NAME + ";");
        onCreate(sqLiteDatabase);
    }

    public void createNewUser(User user)
    {
        int isTherapist = 0;
        if(user.isTherapist()) isTherapist = 1;

        String sqlExecStatement = "INSERT INTO " + USERS_TABLE_NAME + " VALUES('USR','PASS','FULLNAME','ISTHERAPIST');"
                .replace("USR", user.getUsername())
                .replace("PASS", user.getPassword())
                .replace("FULLNAME", user.getFullName())
                .replace("ISTHERAPIST", isTherapist + "");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL(sqlExecStatement);
        sqLiteDatabase.close();
    }

    public void createNewTherapist(Therapist therapist)
    {
        String sqlExecStatement = "INSERT INTO " + THERAPIST_TABLE_NAME + " VALUES('USR','GENDER','AGE','PROFESSION','LOCATION');"
                .replace("USR", therapist.getUser().getUsername())
                .replace("GENDER", therapist.getGender())
                .replace("AGE", therapist.getAge() + "")
                .replace("PROFESSION",  therapist.getProfession())
                .replace("LOCATION", therapist.getLocation());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL(sqlExecStatement);
        sqLiteDatabase.close();
    }
    @SuppressLint("Range")
    public boolean passwordMatches(String username, String password)
    {
        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME + " WHERE username = '" + username + "';";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
            String dbPassword = cursor.getString(cursor.getColumnIndex("password"));
            if(password.equals(dbPassword))
            {
                return true;
            }
        }
        return false;
    }
    @SuppressLint("Range")
    public String getFullName(String username)
    {
        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME + " WHERE username = '" + username + "';";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
            String fullname = cursor.getString(cursor.getColumnIndex("fullname"));
            return fullname;
        }
        return null;
    }

    @SuppressLint("Range")
    public boolean usernameExists(String usernametoCompare)
    {
        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME + " ORDER BY username;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String username;

        if(cursor.moveToFirst())
        {
            do
            {
                username = cursor.getString(cursor.getColumnIndex("username"));
                if(usernametoCompare.equalsIgnoreCase(username))
                {
                    db.close();
                    return true;
                }
            }
            while(cursor.moveToNext());
        }

        db.close();
        return false;
    }
}
