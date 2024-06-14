package com.example.myapplication.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.constants.BookingConstants;
import com.example.myapplication.database.MyDatabaseHelper;
import com.example.myapplication.models.Booking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
        String query = "SELECT DISTINCT time FROM " + BookingConstants.TABLE_BOOKING + " WHERE date = ? AND status = ?";


        Cursor cursor = db.rawQuery(query, new String[]{date, BookingConstants.ACCEPT});

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
                    times.add(time);
                }
            } finally {

            }
        }
        return times;
    }

    public List<Booking> getBookingsByDateAndUser(String date, int userId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM " + BookingConstants.TABLE_BOOKING + " WHERE date = ? AND userId = ? AND status = ?";
        Cursor cursor = db.rawQuery(query, new String[]{date, String.valueOf(userId), "Chấp nhận"});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Booking booking = new Booking();
                booking.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                booking.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow("userId")));
                booking.setDate(cursor.getString(cursor.getColumnIndexOrThrow("date")));
                booking.setTime(cursor.getString(cursor.getColumnIndexOrThrow("time")));
                booking.setContent(cursor.getString(cursor.getColumnIndexOrThrow("content")));
                booking.setStatus(cursor.getString(cursor.getColumnIndexOrThrow("status")));
                bookings.add(booking);
            }
        }
        return bookings;
    }
    public List<Booking> getBookingsByDate(String date) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM " + BookingConstants.TABLE_BOOKING + " WHERE date = ? AND status = ?";
        Cursor cursor = db.rawQuery(query, new String[]{date, "Chấp nhận"});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Booking booking = new Booking();
                booking.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                booking.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow("userId")));
                booking.setDate(cursor.getString(cursor.getColumnIndexOrThrow("date")));
                booking.setTime(cursor.getString(cursor.getColumnIndexOrThrow("time")));
                booking.setContent(cursor.getString(cursor.getColumnIndexOrThrow("content")));
                booking.setStatus(cursor.getString(cursor.getColumnIndexOrThrow("status")));
                bookings.add(booking);
            }
        }
        return bookings;
    }

    public List<String> getDistinctBookingDatesFromMonth(int userId) {
        List<String> distinctDates = new ArrayList<>();

        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dbSdf = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng cho cơ sở dữ liệu

        // Tính ngày đầu tháng và cuối tháng
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String startDate = dbSdf.format(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDate = dbSdf.format(calendar.getTime());

        String query = "SELECT DISTINCT date FROM " + BookingConstants.TABLE_BOOKING + " WHERE date >= ? AND date <= ? AND userId = ? AND status = ?";
        Cursor cursor = db.rawQuery(query, new String[]{startDate, endDate, String.valueOf(userId), "Chấp nhận"});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String dbDate = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                distinctDates.add(dbDate);
            }
        }

        return distinctDates;
    }
    public List<String> getDistinctBookingDatesFromMonth2() {
        List<String> distinctDates = new ArrayList<>();

        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dbSdf = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng cho cơ sở dữ liệu

        // Tính ngày đầu tháng và cuối tháng
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String startDate = dbSdf.format(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDate = dbSdf.format(calendar.getTime());

        String query = "SELECT DISTINCT date FROM " + BookingConstants.TABLE_BOOKING + " WHERE date >= ? AND date <= ? AND status = ?";
        Cursor cursor = db.rawQuery(query, new String[]{startDate, endDate, "Chấp nhận"});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String dbDate = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                distinctDates.add(dbDate);
            }
        }

        return distinctDates;
    }

    public List<String> getDistinctBookingDatesFromMonth3() {
        List<String> distinctDates = new ArrayList<>();

        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dbSdf = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng cho cơ sở dữ liệu

        // Tính ngày đầu tháng và cuối tháng
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String startDate = dbSdf.format(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDate = dbSdf.format(calendar.getTime());

        String query = "SELECT date FROM " + BookingConstants.TABLE_BOOKING + " WHERE date >= ? AND date <= ? AND status = ?";
        Cursor cursor = db.rawQuery(query, new String[]{startDate, endDate, "Chấp nhận"});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String dbDate = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                distinctDates.add(dbDate);
            }
        }

        return distinctDates;
    }

    public List<String> getDistinctBookingDatesFromMonth4(int userId) {
        List<String> distinctDates = new ArrayList<>();

        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dbSdf = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng cho cơ sở dữ liệu

        // Tính ngày đầu tháng và cuối tháng
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String startDate = dbSdf.format(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDate = dbSdf.format(calendar.getTime());

        String query = "SELECT date FROM " + BookingConstants.TABLE_BOOKING + " WHERE date >= ? AND date <= ? AND userId = ? AND status = ?";
        Cursor cursor = db.rawQuery(query, new String[]{startDate, endDate, String.valueOf(userId), "Chấp nhận"});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String dbDate = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                distinctDates.add(dbDate);
            }
        }

        return distinctDates;
    }

    public List<Booking> getCompletedBookingsByUserId(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM " + BookingConstants.TABLE_BOOKING + " WHERE userId = ? AND status = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId), BookingConstants.SUCCESS});

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    Booking booking = new Booking();
                    booking.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    booking.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow("userId")));
                    booking.setDate(cursor.getString(cursor.getColumnIndexOrThrow("date")));
                    booking.setTime(cursor.getString(cursor.getColumnIndexOrThrow("time")));
                    booking.setContent(cursor.getString(cursor.getColumnIndexOrThrow("content")));
                    booking.setStatus(cursor.getString(cursor.getColumnIndexOrThrow("status")));
                    booking.setRating(cursor.getFloat(cursor.getColumnIndexOrThrow("rating")));
                    bookings.add(booking);
                }
            } finally {
                cursor.close();
            }
        }
        return bookings;
    }

    public void updateBookingStatus(int bookingId, String status) {
        ContentValues values = new ContentValues();
        values.put("status", status);
        int rowsAffected = db.update(BookingConstants.TABLE_BOOKING, values, "id = ?", new String[]{String.valueOf(bookingId)});
        if (rowsAffected > 0) {
            Log.i("BookingDAO", "Booking status updated successfully.");
        } else {
            Log.e("BookingDAO", "Error updating booking status.");
        }
    }
    public void updateBookingRating(int bookingId, float rating) {
        ContentValues values = new ContentValues();
        values.put("rating", rating);
        int rowsAffected = db.update(BookingConstants.TABLE_BOOKING, values, "id = ?", new String[]{String.valueOf(bookingId)});
        if (rowsAffected > 0) {
            Log.i("BookingDAO", "Booking rating updated successfully.");
        } else {
            Log.e("BookingDAO", "Error updating booking rating.");
        }
    }

}

