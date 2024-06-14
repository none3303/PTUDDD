package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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

    private ImageView imgTrangChuIcon;
    private ImageView imgLichHenIcon;
    private ImageView imgDatLichIcon;
    private ImageView imgThongBaoIcon;
    private ImageView imgCaNhansv;
    private TextView txtTenUser;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo các widget
        WIDGET();

        // Hiển thị Fragment Trang chủ và đặt trạng thái selected cho biểu tượng Trang chủ
        User user = (User) getIntent().getSerializableExtra("user");
        displayTrangChuFragment(user);
        imgTrangChuIcon.setSelected(true);

//        Booking booking = new Booking();
//        booking.setTime("09:00");
//        booking.setDate("12/6/2024");
//        booking.setUserId(1);
//        booking.setContent("test");
//        booking.setStatus(BookingConstants.ACCEPT);
//        BookingDAO bookingDAO = new BookingDAO(this);
//        bookingDAO.addBooking(booking);
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
        imgThongBaoIcon = findViewById(R.id.btnThongBaosv);
        imgCaNhansv = findViewById(R.id.btnCaNhansv);
        txtTenUser = findViewById(R.id.txtTenUser);
        btnLogout = findViewById(R.id.btnLogout);
        txtTenUser.setText("Hi, " + user.getFullName());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetIconsState();
                v.setSelected(true);
                int id = v.getId();
                if (id == R.id.btnTrangChusv) {
                    displayTrangChuFragment(user);
                } else if (id == R.id.btnLicHensv) {
                    displayLichHenFragment(user);
                } else if (id == R.id.btnDatlichsv) {
                    displayDatLichFragment(user);
                } else if (id == R.id.btnThongBaosv) {
                    // Thêm phương thức hiển thị fragment cho "Thông báo" tại đây
                } else if (id == R.id.btnCaNhansv) {
                    displayCaNhanFragment(user);
                }
            }
        };

        imgCaNhansv.setOnClickListener(listener);
        imgTrangChuIcon.setOnClickListener(listener);
        imgLichHenIcon.setOnClickListener(listener);
        imgDatLichIcon.setOnClickListener(listener);
        imgThongBaoIcon.setOnClickListener(listener);
    }

    private void resetIconsState() {
        imgTrangChuIcon.setSelected(false);
        imgLichHenIcon.setSelected(false);
        imgDatLichIcon.setSelected(false);
        imgThongBaoIcon.setSelected(false);
        imgCaNhansv.setSelected(false);
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
