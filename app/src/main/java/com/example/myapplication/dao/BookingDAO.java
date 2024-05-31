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
        dbHelper = new MyDatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void addBooking(Booking booking, int userId) {
        ContentValues values = new ContentValues();
        values.put(BookingConstants.DATE, booking.getDate());
        values.put(BookingConstants.TIME, booking.getTime());
        values.put(BookingConstants.CONTENT, booking.getContent());
        values.put(BookingConstants.STATUS, booking.getStatus());
        values.put(BookingConstants.RATING, booking.getRating());
        db.insert(BookingConstants.TABLE_BOOKING, null, values);
        db.close();
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
        List<Booking> bookingList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + BookingConstants.TABLE_BOOKING;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Booking booking = new Booking(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getDouble(5),
                        cursor.getInt(6)
                );
                bookingList.add(booking);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return bookingList;
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
}

