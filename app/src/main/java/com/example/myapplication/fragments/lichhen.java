package com.example.myapplication.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.CalendarView;
import com.example.myapplication.R;
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

        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.add_icon));

        calendarView.setEvents(events);

        return view;
    }
}
