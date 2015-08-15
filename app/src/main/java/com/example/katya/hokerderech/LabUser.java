package com.example.katya.hokerderech;

import android.util.Log;

/**
 * Created by Katya on 8/15/15.
 */
public class LabUser {

    private String user_name;
    private String user_password;

    public LabUser(String name, String password){
        user_name = name;
        user_password = password;
        Log.v("Laboratory User:", "User Name: " + user_name);

    }

    public String  getName() {
        return user_name;
    }
    public boolean getAccess() {
       // student case:
        return false;
    }



}
