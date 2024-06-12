package com.example.myapplication.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapplication.R;
import com.example.myapplication.models.User;

public class trangchu_sv extends Fragment {
    private static final String ARG_USER = "user";
    private User user;

    public trangchu_sv() {
        // Required empty public constructor
    }

    public static trangchu_sv newInstance(User user) {
        trangchu_sv fragment = new trangchu_sv();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trangchu_sv, container, false);
    }

    // Use the User object as needed within this fragment
}
