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
        getWidth();
        login.setOnClickListener(v -> {
            String reqUsername=editUsername.getText().toString();
            String reqPassword=editPassword.getText().toString();
            try{
                User user=userDAO.getUserByUsername(reqUsername);
                if(user!=null&&user.getPassword().equals(reqPassword)){
<<<<<<< HEAD
                    Intent intent = new Intent(this, TestGV_DatLich.class);
=======
                    Intent intent;
                    if (user.getRole().equals(UserConstants.ROLE_STUDENT)){
                        intent = new Intent(this, MainActivity.class);
                    } else {
                        intent = new Intent(this, MainActivity.class);
                    }
>>>>>>> origin/main
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
        userDAO.resetData();
        //sinh vien
        User tanh=new User();
        tanh.setUsername("tanh123");
        tanh.setPassword("123");
        tanh.setFullName("Do Tien Anh");
        tanh.setIdCard("2021600552");
        tanh.setPhone("0865923203");
        tanh.setStudentCode("2021600552");
        tanh.setGender(UserConstants.GENDER_MALE);
        tanh.setEmail("dtienanh1213@gmail.com");
        tanh.setDateOfBirth("19/09/2003");
        tanh.setAddress("Ha Noi");
        tanh.setPlaceOfBirth("Ha Noi");
        tanh.setRole(UserConstants.ROLE_STUDENT);
        //giang vien
        User gv=new User();
        gv.setUsername("nhungnt");
        gv.setPassword("123");
        gv.setFullName("Nguyễn Thị Nhung");
        gv.setIdCard("2021600552");
        gv.setPhone("0865923203");
        gv.setStudentCode("2021600552");
        gv.setGender(UserConstants.GENDER_FEMALE);
        gv.setEmail("dtienanh1213@gmail.com");
        gv.setDateOfBirth("19/09/2003");
        gv.setAddress("Ha Noi");
        gv.setPlaceOfBirth("Ha Noi");
        gv.setRole(UserConstants.ROLE_TEACHER);
        userDAO.addUser(tanh);
        userDAO.addUser(gv);
        //booking

        Booking booking1=new Booking();
        Booking booking2=new Booking();
        Booking booking3=new Booking();
        Booking booking4=new Booking();

        booking1.setRating(5);
        booking2.setRating(4.5);
        booking3.setRating(4);
        booking4.setRating(4);
        BookingDAO bookingDAO=new BookingDAO(this);
        bookingDAO.resetData();
        bookingDAO.addBooking(booking1);
        bookingDAO.addBooking(booking2);
        bookingDAO.addBooking(booking3);
        bookingDAO.addBooking(booking4);

    }
}