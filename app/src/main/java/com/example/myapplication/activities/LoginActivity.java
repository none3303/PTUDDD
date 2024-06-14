package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.constants.BookingConstants;
import com.example.myapplication.constants.UserConstants;
import com.example.myapplication.dao.BookingDAO;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.User;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    UserDAO userDAO;
    EditText editUsername, editPassword;
    TextView txtError;
    AppCompatButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        login.setOnClickListener(v -> {
            String reqUsername=editUsername.getText().toString();
            String reqPassword=editPassword.getText().toString();
            try{
                User user=userDAO.getUserByUsername(reqUsername);
                if(user!=null&&user.getPassword().equals(reqPassword)){
                    Intent intent;
                    if (user.getRole().equals(UserConstants.ROLE_STUDENT)){
                        intent = new Intent(this, MainActivity.class);
                    } else {
                        intent = new Intent(this, MainActivity_GV.class);
                    }
                    intent.putExtra("user",user);
                    startActivity(intent);
                }
                else {
                    txtError.setText("Sai tên đăng nhập hoặc mặt khẩu");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        });
    }
    public void getWidth(){
        editUsername=findViewById(R.id.txtUsername);
        editPassword=findViewById(R.id.txtPassword);
        txtError=findViewById(R.id.txtError);
        login=findViewById(R.id.btnSubmit);
    }
    public void init(){
        getWidth();
        userDAO=new UserDAO(this);
        // Chay 1 lan xong comment lai
//        User tan=new User();
//        tan.setUsername("tank123");
//        tan.setPassword("123");
//        tan.setFullName("Do Tien Anh");
//        tan.setIdCard("2021600552");
//        tan.setPhone("0865923203");
//        tan.setStudentCode("2021600552");
//        tan.setGender(UserConstants.GENDER_MALE);
//        tan.setEmail("dtienanh1213@gmail.com");
//        tan.setDateOfBirth("19/09/2003");
//        tan.setAddress("Ha Noi");
//        tan.setPlaceOfBirth("Ha Noi");
//        tan.setRole(UserConstants.ROLE_STUDENT);
//        userDAO.addUser(tan);
//        BookingDAO booking = new BookingDAO(this);
//        Booking bk = new Booking();
//        bk.setId(20);
//        bk.setDate("16-06-2024");
//        bk.setTime("10:30");
//        bk.setContent("Lấy học bổng");
//        bk.setStatus(BookingConstants.ACCEPT);
//        bk.setUserId(2021600552);
//        booking.addBooking(bk);

    }
}