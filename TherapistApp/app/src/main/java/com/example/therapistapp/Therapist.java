package com.example.therapistapp;

public class Therapist
{
    private User user;
    private String gender;
    private int age;
    private String profession;
    private String location;

    public Therapist()
    {

    }

    public Therapist(User u, String g, int a, String p, String l)
    {
        user = u;
        gender = g;
        age = a;
        profession = p;
        location = l;
    }

    public User getUser()
    {
        return user;
    }

    public String getGender()
    {
        return gender;
    }

    public int getAge()
    {
        return age;
    }

    public String getProfession()
    {
        return profession;
    }

    public String getLocation()
    {
        return location;
    }

    public void setUser(User u)
    {
        user = u;
    }

    public void setGender(String g)
    {
        gender = g;
    }

    public void setAge(int a)
    {
        age = a;
    }

    public void setProfession(String p)
    {
        profession = p;
    }

    public void setLocation(String loc)
    {
        location = loc;
    }
}
