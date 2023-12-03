package com.example.therapistapp;

public class Review
{
    private String comments;
    private int rating;

    private String reviewer;

    public Review(String c, int r, String rvr)
    {
        comments = c;
        rating = r;
        reviewer = rvr;
    }

    public Review()
    {

    }

    public String getComments()
    {
        return comments;
    }

    public String getReviewer()
    {
        return reviewer;
    }

    public int getRating()
    {
        return rating;
    }

    public void setComments(String c)
    {
        comments = c;
    }

    public void setRating(int r)
    {
        rating = r;
    }

    public void setReviewer(String r)
    {
        reviewer = r;
    }
}
