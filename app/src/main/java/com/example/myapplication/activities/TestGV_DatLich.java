package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.dao.BookingDAO;
import com.example.myapplication.dao.UserDAO;

public class TestGV_DatLich extends AppCompatActivity {

    BookingDAO bookingDAO;
    UserDAO userDAO;
    Button button;
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

//        button =(Button) findViewById(R.id.sangTabDatLich);
//        button.setOnClickListener(v -> {
//            Intent intent = new Intent(TestGV_DatLich.this, GV_DatLichActivity.class);
//            startActivity(intent);
//        });
//        bookingDAO = new BookingDAO(this);
//        bookingDAO.addBooking(new Booking(1,"12","12","12", BookingConstants.ACCEPT,4,1));
//        bookingDAO.addBooking(new Booking(2,"12","12","12", BookingConstants.REJECT,4,1));
//        bookingDAO.addBooking(new Booking(3,"12","12","12", BookingConstants.PENDING,4,1));
//        userDAO = new UserDAO(this);
//        userDAO.addUser(new User("1","1","1","1","1","1","1","1","1","1","1","1",""));

    }

    @Override
    protected void onDestroy() {
        bookingDAO.close();
        userDAO.close();
        super.onDestroy();
    }
}