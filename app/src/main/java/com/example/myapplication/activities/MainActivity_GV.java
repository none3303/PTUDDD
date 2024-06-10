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

public class MainActivity_GV extends AppCompatActivity {

    private ImageView imgTrangChuIcon;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_gv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        WIDGET();
    }

    private void WIDGET() {
        imgTrangChuIcon = findViewById(R.id.btnTrangChuGv);

        // Thiết lập sự kiện click cho ImageView Trang chủ
        imgTrangChuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị Fragment Trang chủ khi ImageView Trang chủ được nhấp
                displayTrangChuFragment();
            }
        });
    }

    private void displayTrangChuFragment() {
        // Tạo mới Fragment Trang chủ
        Fragment trangChuFragment = new Fragment(); // Thay đổi Fragment này theo Fragment của bạn

        // Hiển thị Fragment Trang chủ
        displayFragment(trangChuFragment);
    }

    private void displayFragment(Fragment fragment) {
        // Lấy FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Bắt đầu giao dịch Fragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Thay thế Fragment hiện tại bằng Fragment mới
        transaction.replace(R.id.fragment_trangchu_gv, fragment);

        // Commit giao dịch
        transaction.commit();
    }
}
