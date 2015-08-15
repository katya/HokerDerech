package com.example.katya.hokerderech;

/**
 * Created by Katya on 8/14/15.
 */
public class Laboratory {
    String title;
    String x;
    String y;
    String group;
    String description;
    String time;

    public Laboratory( String title,
            String x,
            String y,
            String group,
            String description,
            String time)
    {
        this.title = title;
        this.x = x;
        this.y = y;
        this.group = group;
        this.description = description;
        this.time = time;

    }

    public String getName() {
        return title;
    }




}
