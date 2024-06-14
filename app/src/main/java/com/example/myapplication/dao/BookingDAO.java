package com.example.myapplication.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.constants.BookingConstants;
import com.example.myapplication.database.MyDatabaseHelper;
import com.example.myapplication.models.Booking;

import java.util.ArrayList;
import java.util.List;


public class BookingDAO {
    private SQLiteDatabase db;
    private MyDatabaseHelper dbHelper;

    public BookingDAO(Context context) {
        dbHelper = MyDatabaseHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }



    public Booking getBookingById(int id) {
        Cursor cursor = db.query(BookingConstants.TABLE_BOOKING, new String[]{"id", BookingConstants.DATE, BookingConstants.TIME, BookingConstants.CONTENT, BookingConstants.STATUS, BookingConstants.RATING},
                "id" + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Booking booking = new Booking(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getDouble(5),
                        cursor.getInt(6)
                );
                cursor.close();
                return booking;
            }
            cursor.close();
        }
        return null;
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        Cursor cursor = db.query(BookingConstants.TABLE_BOOKING, null, null, null, null, null, null);

        if (cursor != null) {
            int idIndex = cursor.getColumnIndex("id");
            int dateIndex = cursor.getColumnIndex(BookingConstants.DATE);
            int timeIndex = cursor.getColumnIndex(BookingConstants.TIME);
            int contentIndex = cursor.getColumnIndex(BookingConstants.CONTENT);
            int statusIndex = cursor.getColumnIndex(BookingConstants.STATUS);
            int ratingIndex = cursor.getColumnIndex(BookingConstants.RATING);
            int userIdIndex = cursor.getColumnIndex("user_id");

            while (cursor.moveToNext()) {
                Booking booking = new Booking();

                if (idIndex != -1) booking.setId(cursor.getInt(idIndex));
                if (dateIndex != -1) booking.setDate(cursor.getString(dateIndex));
                if (timeIndex != -1) booking.setTime(cursor.getString(timeIndex));
                if (contentIndex != -1) booking.setContent(cursor.getString(contentIndex));
                if (statusIndex != -1) booking.setStatus(cursor.getString(statusIndex));
                if (ratingIndex != -1) booking.setRating(cursor.getFloat(ratingIndex));
                if (userIdIndex != -1) booking.setUserId(cursor.getInt(userIdIndex));

                bookings.add(booking);
            }
            cursor.close();
        }

        return bookings;
    }
    public List<Booking> getAllStatusBookings(String selectionArg) {
        List<Booking> bookings = new ArrayList<>();
        String[] columns = {"id", "date", "time", "content", "status", "rating", "userId"};
        String selection = "status = ?";
        String[] selectionArgs = { selectionArg };

        Cursor cursor = db.query(BookingConstants.TABLE_BOOKING, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Booking booking = new Booking();
                booking.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                booking.setDate(cursor.getString(cursor.getColumnIndexOrThrow(BookingConstants.DATE)));
                booking.setTime(cursor.getString(cursor.getColumnIndexOrThrow(BookingConstants.TIME)));
                booking.setContent(cursor.getString(cursor.getColumnIndexOrThrow(BookingConstants.CONTENT)));
                booking.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(BookingConstants.STATUS)));
                booking.setRating(cursor.getFloat(cursor.getColumnIndexOrThrow(BookingConstants.RATING)));
                booking.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow("userId")));
                bookings.add(booking);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return bookings;
    }

    public void deleteBooking(int bookingId) {
        db.delete(BookingConstants.TABLE_BOOKING, "id" + " = ?", new String[]{String.valueOf(bookingId)});
        db.close();
    }

    public int updateBooking(Booking booking) {
        ContentValues values = new ContentValues();
        values.put(BookingConstants.DATE, booking.getDate());
        values.put(BookingConstants.TIME, booking.getTime());
        values.put(BookingConstants.CONTENT, booking.getContent());
        values.put(BookingConstants.STATUS, booking.getStatus());
        values.put(BookingConstants.RATING, booking.getRating());
        return db.update(BookingConstants.TABLE_BOOKING, values, "id" + " = ?", new String[]{String.valueOf(booking.getId())});
    }

    public void close() {
        dbHelper.close();
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
    public List<Booking> getBookingsByDateAndUser(String date, int userId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM " + BookingConstants.TABLE_BOOKING + " WHERE date = ? AND userId = ?";
        Cursor cursor = db.rawQuery(query, new String[]{date, String.valueOf(userId)});

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
    public void resetData(){
        dbHelper.deleteAllDataFromTable(BookingConstants.TABLE_BOOKING);
    }



}

