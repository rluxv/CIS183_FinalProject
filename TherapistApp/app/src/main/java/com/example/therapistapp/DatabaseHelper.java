package com.example.therapistapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "TherapistDB";
    public static final String THERAPIST_TABLE_NAME = "Therapists";
    public static final String USERS_TABLE_NAME = "Users";
    public static final String REVIEWS_TABLE_NAME = "Reviews";
    public static final String PROFESSION_TABLE_NAME = "Professions";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 6);
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

        String sqlExecStatement = "INSERT INTO " + USERS_TABLE_NAME + " VALUES('USR','FULLNAME','PASS','ISTHERAPIST');"
                .replace("USR", user.getUsername())
                .replace("PASS", user.getPassword())
                .replace("FULLNAME", user.getFullName())
                .replace("ISTHERAPIST", isTherapist + "");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL(sqlExecStatement);
        sqLiteDatabase.close();
    }

    public void updateUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateSQL = "UPDATE TABLENAME SET fullname = 'FULLNAME', password = 'PASSWD' WHERE username = 'USRNME';"
                .replace("TABLENAME", USERS_TABLE_NAME)
                .replace("FULLNAME", user.getFullName())
                .replace("USRNME", user.getUsername())
                .replace("PASSWD", user.getPassword());
        db.execSQL(updateSQL);
        db.close();
    }

    public void updateTherapist(Therapist therapist, String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateSQL = "UPDATE TABLENAME SET age = 'THERAG', gender = 'GNDR', profession = 'PROFS', location = 'LCTN' WHERE username = 'USRNME';"
                .replace("TABLENAME", THERAPIST_TABLE_NAME)
                .replace("USRNME", username)
                .replace("THERAG", therapist.getAge() + "")
                .replace("GNDR", therapist.getGender())
                .replace("PROFS", therapist.getProfession())
                .replace("LCTN", therapist.getLocation());
        db.execSQL(updateSQL);
        db.close();
    }

    public void deleteUser(User user)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + USERS_TABLE_NAME + " WHERE username = '" + user.getUsername() + "';");
        if(user.isTherapist())
        {
            sqLiteDatabase.execSQL("DELETE FROM " + THERAPIST_TABLE_NAME + " WHERE username = '" + user.getUsername() + "';");
        }
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
                Log.d("Usernameandpasswodmatch", "yes");
                db.close();
                return true;
            }
            else
            {
                Log.d("Usernameandpasswodmatch", "no");
                Log.d("pass entered " + password, "db password " + dbPassword);
            }
        }
        db.close();
        return false;
    }

    @SuppressLint("Range")
    public ArrayList<String> getTherapyTypes()
    {
        ArrayList<String> list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + THERAPIST_TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
            do
            {
                list.add(cursor.getString(cursor.getColumnIndex("profession")));
            }
            while(cursor.moveToNext());
        }
        db.close();

        return list;
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
            db.close();
            return fullname;
        }
        db.close();
        return null;
    }
    @SuppressLint("Range")
    public User getUser(String username)
    {
        User user = new User();
        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME + " WHERE username = '" + username + "';";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
            String fullname = cursor.getString(cursor.getColumnIndex("fullname"));
            String uname = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            int istherapistint = cursor.getInt(cursor.getColumnIndex("istherapist"));
            boolean isTherapist = false;
            if(istherapistint == 1) isTherapist = true;
            user = new User(uname, password, isTherapist, fullname);
            db.close();
            return user;
        }
        db.close();
        return user;
    }

    @SuppressLint("Range")
    public Therapist getTherapist(String username)
    {
        //        sqLiteDatabase.execSQL("CREATE TABLE " + THERAPIST_TABLE_NAME + " (username PRIMARY KEY NOT NULL, gender TEXT, age INTEGER, profession TEXT, location TEXT);");
        Therapist therapist = new Therapist();
        String selectQuery = "SELECT * FROM " + THERAPIST_TABLE_NAME + " WHERE username = '" + username + "';";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
            String gender = cursor.getString(cursor.getColumnIndex("gender"));
            User u = getUser(username);
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            String profession = cursor.getString(cursor.getColumnIndex("profession"));
            String location = cursor.getString(cursor.getColumnIndex("location"));
            therapist = new Therapist(u, gender, age, profession, location);
            db.close();
            return therapist;
        }
        return therapist;
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
