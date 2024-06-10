package com.example.myapplication.activities;

import android.os.Bundle;
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
import com.example.myapplication.fragments.datlich;
import com.example.myapplication.fragments.lichhen;
import com.example.myapplication.fragments.trangchu_sv;

public class MainActivity extends AppCompatActivity {

    ImageView imgTrangChuIcon;
    ImageView imgLichHenIcon;
    ImageView imgDatLichIcon;

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
        displayTrangChuFragment();
    }


    private void WIDGET() {
        imgTrangChuIcon = findViewById(R.id.btnTrangChusv);
        imgLichHenIcon = findViewById(R.id.btnLicHensv);
        imgDatLichIcon = findViewById(R.id.btnDatlichsv);


        // Thiết lập sự kiện click cho ImageView Trang chủ
        imgTrangChuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị Fragment Trang chủ khi ImageView Trang chủ được nhấp
                displayTrangChuFragment();
            }
        });

        // Thiết lập sự kiện click cho ImageView Lịch hẹn
        imgLichHenIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị Fragment Lịch hẹn khi ImageView Lịch hẹn được nhấp
                displayLichHenFragment();
            }
        });

        imgDatLichIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDatLichFragment();
            }
        });
    }

    // Phương thức hiển thị Fragment Trang chủ
    private void displayTrangChuFragment() {
        Fragment trangChuFragment = new trangchu_sv();
        displayFragment(trangChuFragment);
    }

    // Phương thức hiển thị Fragment Lịch hẹn
    private void displayLichHenFragment() {
        Fragment lichHenFragment = new lichhen();
        displayFragment(lichHenFragment);
    }
    private void displayDatLichFragment() {
        Fragment datLichFragment = new datlich();
        displayFragment(datLichFragment);
    }

    // Phương thức hiển thị Fragment
    private void displayFragment(Fragment fragment) {
        // Lấy FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Bắt đầu giao dịch Fragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Thay thế Fragment hiện tại bằng Fragment mới
        transaction.replace(R.id.trangchuFragment, fragment);

        // Commit giao dịch
        transaction.commit();
    }
}
