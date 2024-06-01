package com.example.myapplication.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.services.GV_DatLichAdapter;
import com.google.android.material.tabs.TabLayout;

public class GV_DatLichActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gv_dat_lich);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ViewPager viewPager = findViewById(R.id.viewPager);
        GV_DatLichAdapter adapter = new GV_DatLichAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        //set up viewpager with tablayout
        tabLayout.setupWithViewPager(viewPager);
        setColor(tabLayout,0,"Đang chờ",true);
        setColor(tabLayout,1,"Chấp nhận",false);
        setColor(tabLayout,2,"Từ chối",false);
        TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                setColor(tabLayout, position, tab.getText().toString(),true);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setColor(tabLayout, tab.getPosition(), tab.getText().toString(),false);
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        };
        tabLayout.addOnTabSelectedListener(tabSelectedListener);

    }
    public void setColor(TabLayout tabLayout,int position, String text,boolean isActive){
        SpannableString spannableString = new SpannableString(text);
        if(isActive){
            switch (position){
                case 0:
                    spannableString.setSpan(new ForegroundColorSpan(Color.rgb(155,135,12)), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    break;
                case 1:
                    spannableString.setSpan(new ForegroundColorSpan(Color.GREEN), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    break;
                case 2:
                    spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    break;
                default:
                    break;
            }
        }else{
            spannableString.setSpan(new ForegroundColorSpan(Color.GRAY), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        tabLayout.getTabAt(position).setText(spannableString);
    }



}