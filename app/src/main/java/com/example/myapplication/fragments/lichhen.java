package com.example.myapplication.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.myapplication.Adapter.Adapter;
import com.example.myapplication.R;
import com.example.myapplication.dao.BookingDAO;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class lichhen extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private static final String ARG_USER = "user";
    private User user;
    private RecyclerView recyclerView;
    private Adapter itemAdapter;

    public lichhen() {
        // Cần một constructor công khai rỗng
    }

    public static lichhen newInstance(User user) {
        lichhen fragment = new lichhen();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(ARG_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout cho fragment này
        View view = inflater.inflate(R.layout.fragment_lichhen, container, false);

        CalendarView calendarView = view.findViewById(R.id.calendarView);

        // Lấy RecyclerView từ layout
        recyclerView = view.findViewById(R.id.recyclerViewSV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Thiết lập listener cho CalendarView
        calendarView.setOnDayClickListener(eventDay -> {
            Calendar selectedDate = eventDay.getCalendar();
            String formattedDate = getFormattedDate(selectedDate);

            // Lấy danh sách booking theo ngày và userId
            BookingDAO bookingDAO = new BookingDAO(getContext());
            ArrayList<Booking> bookings = (ArrayList<Booking>) bookingDAO.getBookingsByDateAndUser(formattedDate, user.getId());

            // Cập nhật adapter với danh sách mới
            itemAdapter = new Adapter(bookings);
            recyclerView.setAdapter(itemAdapter);
        });

        // Khởi tạo sự kiện ban đầu nếu cần
        List<EventDay> events = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.add_icon));
        calendarView.setEvents(events);

        return view;
    }

    private String getFormattedDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String formattedMonth = (month < 10) ? "" +  month : String.valueOf(month);
        String formattedDay = (day < 10) ? "" + day : String.valueOf(day);

        return formattedDay + "/" + formattedMonth + "/" + year;
    }
}
