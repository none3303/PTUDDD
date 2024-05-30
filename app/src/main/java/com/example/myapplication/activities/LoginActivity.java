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
import com.example.myapplication.dao.UserDAO;
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
            User user=userDAO.getUserByUsername(reqUsername);
            if(user!=null&&user.getPassword().equals(reqPassword)){
                Intent intent = new Intent(this, TestActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
            else {
                txtError.setText("Sai tên đăng nhập hoặc mặt khẩu");
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
    }
}