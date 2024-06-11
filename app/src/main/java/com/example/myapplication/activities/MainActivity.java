package com.example.myapplication.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.myapplication.R;
import com.example.myapplication.constants.BookingConstants;
import com.example.myapplication.dao.BookingDAO;
import com.example.myapplication.fragments.PersonalDetail;
import com.example.myapplication.fragments.datlich;
import com.example.myapplication.fragments.lichhen;
import com.example.myapplication.fragments.trangchu_sv;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.User;


public class MainActivity extends AppCompatActivity {

    ImageView imgTrangChuIcon;
    ImageView imgLichHenIcon;
    ImageView imgDatLichIcon;
    ImageView imgCaNhansv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Gọi phương thức để thiết lập widget
        WIDGET();

        // Hiển thị Fragment Trang chủ khi Activity được tạo
//        displayTrangChuFragment();
//        Booking booking1 = new Booking();
//        booking1.setDate("12/6/2024");
//        booking1.setTime("08:00");
//        booking1.setContent("Đặt lịch");
//        booking1.setStatus(BookingConstants.PENDING);
//        Booking booking2 = new Booking();
//        booking2.setDate("2023-06-10");
//        booking2.setTime("08:30");
//        booking2.setContent("Đặt lịch");
//        booking2.setStatus(BookingConstants.PENDING);
//        BookingDAO bookingDAO = new BookingDAO(this);
//        bookingDAO.addBooking(booking1);
//        bookingDAO.addBooking(booking2);
//        Log.e("TAG", "onCreate: " );

    }


    private void displayFragment(Fragment fragment, User user) {
        if (fragment instanceof PersonalDetail) {
            fragment = PersonalDetail.newInstance(user);
        } else if (fragment instanceof trangchu_sv) {
            fragment = trangchu_sv.newInstance(user);
        } else if (fragment instanceof lichhen) {
            fragment = lichhen.newInstance(user);
        } else if (fragment instanceof datlich) {
            fragment = datlich.newInstance(user);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.trangchuFragment, fragment);
        transaction.commit();
    }

    private void WIDGET() {
        User user = (User) getIntent().getSerializableExtra("user");
        imgTrangChuIcon = findViewById(R.id.btnTrangChusv);
        imgLichHenIcon = findViewById(R.id.btnLicHensv);
        imgDatLichIcon = findViewById(R.id.btnDatlichsv);
        imgCaNhansv = findViewById(R.id.btnCaNhansv);

        imgCaNhansv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCaNhanFragment(user);
            }
        });

        imgTrangChuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayTrangChuFragment(user);
            }
        });

        imgLichHenIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLichHenFragment(user);
            }
        });

        imgDatLichIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDatLichFragment(user);
            }
        });
    }

    private void displayTrangChuFragment(User user) {
        Fragment trangChuFragment = trangchu_sv.newInstance(user);
        displayFragment(trangChuFragment, user);
    }

    private void displayLichHenFragment(User user) {
        Fragment lichHenFragment = lichhen.newInstance(user);
        displayFragment(lichHenFragment, user);
    }

    private void displayDatLichFragment(User user) {
        Fragment datLichFragment = datlich.newInstance(user);
        displayFragment(datLichFragment, user);
    }

    private void displayCaNhanFragment(User user) {
        Fragment caNhanFragment = PersonalDetail.newInstance(user);
        displayFragment(caNhanFragment, user);
    }


}
