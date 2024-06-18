package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;
import com.example.myapplication.constants.BookingConstants;
import com.example.myapplication.models.Booking;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Gv_acceptTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Gv_acceptTab extends GV_BaseBookingTab{

    public Gv_acceptTab() {
        // Required empty public constructor
    }

    public static Gv_acceptTab newInstance(String param1, String param2) {
        Gv_acceptTab fragment = new Gv_acceptTab();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    protected List<Booking> getBookings() {
        return bookingDAO.getAllStatusBookings(BookingConstants.ACCEPT);
    }

    @Override
    protected int getContextMenuResource() {
        return R.menu.gv_datlich_chapnhan_listview_item_select_context_menu;
    }

    @Override
    protected boolean handleContextItemSelected(MenuItem item, Booking selectedBooking) {
        if(item.getItemId() == R.id.menu_complete){
            updateBookingStatus(selectedBooking, BookingConstants.FINISH);
            return true;
        }else if(item.getItemId() == R.id.menu_fail){
            updateBookingStatus(selectedBooking, BookingConstants.FAIL);
            return true;
        }else{
            return false;
        }
    }
    private void updateBookingStatus(Booking booking, String status) {
        booking.setStatus(status);
        bookingDAO.updateBooking(booking);
        setupListView();
    }
}