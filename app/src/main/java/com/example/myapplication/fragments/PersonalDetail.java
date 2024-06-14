package com.example.myapplication.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.constants.UserConstants;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.models.User;

public class PersonalDetail extends Fragment {

    private static final String ARG_USER = "user";
    private User user;

    private TextView txtFullname, txtStudentIdCard, txtBirth, txtGender, txtPlace, txtIdCard, txtSDT, txtEmail, txtAddress,txtRating;
    private ImageView imgStar;
    public PersonalDetail() {
        // Required empty public constructor
    }

    public static PersonalDetail newInstance(User user) {
        PersonalDetail fragment = new PersonalDetail();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(ARG_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_detail, container, false);
        initViews(view);
        initUser();
        return view;
    }

    private void initViews(View view) {
        txtFullname = view.findViewById(R.id.txtFullname);
        txtStudentIdCard = view.findViewById(R.id.txtStudentIdCard);
        txtBirth = view.findViewById(R.id.txtBirth);
        txtGender = view.findViewById(R.id.txtGender);
        txtPlace = view.findViewById(R.id.txtPlace);
        txtIdCard = view.findViewById(R.id.txtIdCard);
        txtSDT = view.findViewById(R.id.txtSDT);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtAddress = view.findViewById(R.id.txtAddress);
        txtRating=view.findViewById(R.id.txtRating);
        imgStar=view.findViewById(R.id.imgStar);
    }

    private void initUser() {
        if (user != null) {
            txtFullname.setText(user.getFullName());
            txtStudentIdCard.setText(user.getStudentCode());
            txtBirth.setText(user.getDateOfBirth());
            txtGender.setText(user.getGender());
            txtPlace.setText(user.getPlaceOfBirth());
            txtIdCard.setText(user.getIdCard());
            txtSDT.setText(user.getPhone());
            txtEmail.setText(user.getEmail());
            txtAddress.setText(user.getAddress());
            if(user.getRole().equals(UserConstants.ROLE_STUDENT)){
                txtRating.setText("");
                imgStar.setVisibility(View.INVISIBLE);
            }
            else{
                UserDAO userDAO=new UserDAO(this.getContext());
                txtRating.setText(userDAO.calculateAverageRatingForUser()+"");
                imgStar.setVisibility(View.VISIBLE);
            }
        }
    }
}
