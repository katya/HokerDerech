package com.example.katya.hokerderech;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Katya on 8/14/15.
 */
public class LabDBHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "HokerDerech";
    public static final String MAIN_TABLE = "LabResult";
    private static final int DB_VERSION = 1;

    private static final String CREATE_MAIN_TABLE =
            "CREATE TABLE " + MAIN_TABLE + " (" +
                    "_id INTEGER NOT NULL PRIMARY KEY, " +
                    "Title TEXT, "+
                    "Description TEXT, " +
                    "Group TEXT, " +
                    "XGraph TEXT, " +
                    "YGraph TEXT, " +
                    "Date TEXT)";

    private static final String INSERT_QUERY =
            "INSERT INTO " + MAIN_TABLE + " " +
                    "(Title, Description, Group, XGraph, YGraph, Date)" +
                    "VALUES " +
                    "('Pop Corn Lab', 'test popcorn', 'Red', '0', '60', '14/08/15'), " +
                    "('Pop Corn Lab', 'test popcorn', 'Red', '1', '50', '14/08/15'), " +
                    "('Pop Corn Lab', 'test popcorn', 'Red', '2', '40', '14/08/15'), " +
                    "('Pop Corn Lab', 'test popcorn', 'Red', '3', '30', '14/08/15'), " +
                    "('Pop Corn Lab', 'test popcorn', 'Red', '4', '20', '14/08/15'), " +
                    "('Pop Corn Lab', 'test popcorn', 'Red', '5', '10', '14/08/15'), " +
                    "('Pop Corn Lab', 'test popcorn', 'Red', '6', '0', '14/08/15'), " +
                    "('MilkTea Lab', 'test tea', 'Green', '0', '60', '14/08/15'), \" +)";


    LabDBHelper (Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MAIN_TABLE);
        db.execSQL(INSERT_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("NoticeDBHelper", "Upgrading database from version " + oldVersion
                + " to " + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + MAIN_TABLE);
        this.onCreate(db);

    }
}
