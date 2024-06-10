package com.example.myapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.MyDatabaseHelper;

public class BookingDAO {
    private SQLiteDatabase db;
    private MyDatabaseHelper dbHelper;

    public BookingDAO(Context context) {
        dbHelper = new MyDatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
}
