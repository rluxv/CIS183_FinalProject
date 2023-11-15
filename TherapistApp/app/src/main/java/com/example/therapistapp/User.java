package com.example.therapistapp;

public class User
{
    private String username;
    private String password;
    private boolean isTherapist;
    private String fullName;

    public User(String username, String password, boolean isTherapist, String fullName)
    {
        this.username = username;
        this.password = password;
        this.isTherapist = isTherapist;
        this.fullName = fullName;
    }

    public User()
    {

    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isTherapist()
    {
        return isTherapist;
    }

    public void setTherapist(boolean therapist)
    {
        isTherapist = therapist;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }


}
