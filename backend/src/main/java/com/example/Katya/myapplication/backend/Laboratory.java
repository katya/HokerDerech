package com.example.Katya.myapplication.backend;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Katya on 8/14/15.
 */
public class Laboratory {
    private String title;
    private String x;
    private String y;
    private String group;
    private String description;
    private String time;
    private String[] count;

    public Laboratory() {
    }

    public Laboratory( String title,
                       String description,
                       String x,
                       String y,
                       String[] count,
                       String group,
                       String time)
    {
        this.title = title;
        this.x = x;
        this.y = y;
        this.group = group;
        this.description = description;
        this.time = time;
        this.count = count;
        Arrays.fill(this.count, "empty");

    }

    public String getName() {
        return title;
    }

    public String getTitleX() {return x;}
    public String getTitleY() {return y;}
    public String[] getTable() {return count;}



}
