package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.myapplication.constants.BookingConstants;
import com.example.myapplication.database.MyDatabaseHelper;
import com.example.myapplication.models.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private SQLiteDatabase db;
    private MyDatabaseHelper dbHelper;

    public BookingDAO(Context context) {
        dbHelper = new MyDatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void addBooking(Booking booking) {
        ContentValues values = new ContentValues();
        values.put("date", booking.getDate());
        values.put("time", booking.getTime());
        values.put("content", booking.getContent());
        values.put("status", booking.getStatus());
        values.put("rating", booking.getRating());
        values.put("userId", booking.getUserId());
        db.insert(BookingConstants.TABLE_BOOKING, null, values);
    }
    public List<String> getTimeByDate(String date) {
        List<String> times = new ArrayList<>();
        String query = "SELECT DISTINCT time FROM " + BookingConstants.TABLE_BOOKING + " WHERE date = ?";
        Cursor cursor = db.rawQuery(query, new String[]{date});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
                times.add(time);
            }
        }
        return times;
    }
}

