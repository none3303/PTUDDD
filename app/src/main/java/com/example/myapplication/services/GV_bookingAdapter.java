package com.example.myapplication.services;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.fragments.Gv_acceptTab;
import com.example.myapplication.fragments.Gv_pendingTab;
import com.example.myapplication.fragments.Gv_rejectTab;

public class GV_bookingAdapter extends FragmentStatePagerAdapter {
    public GV_bookingAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Gv_pendingTab();
            case 1:
                return new Gv_acceptTab();
            case 2:
                return new Gv_rejectTab();
        }
        return null;
    }

}
