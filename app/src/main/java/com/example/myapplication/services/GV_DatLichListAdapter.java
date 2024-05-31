package com.example.myapplication.services;

import com.example.myapplication.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.User;

import java.util.ArrayList;
import java.util.List;

public class GV_DatLichListAdapter extends ArrayAdapter<Booking> {
    TextView tvNumber;
    TextView tvName;
    Button btnView;
    //ImageView ivIcon;
    private List<Booking> originalBooks;
    private List<Booking> filteredBooks;
    public GV_DatLichListAdapter(Context context, List<Booking> bookings) {
        super(context, 0, bookings);
        this.originalBooks = new ArrayList<>(bookings);
        this.originalBooks = new ArrayList<>(bookings);
    }
    @Override
    public int getCount() {
        return filteredBooks.size();
    }

    @Override
    public Booking getItem(int position) {
        return filteredBooks.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Booking booking = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_gv_datlich_custom_item_view, parent, false);
        }

        getWidth(convertView);

        tvNumber.setText(String.valueOf(position + 1));
        //ivIcon.setImageResource(user.getIconResId());
        UserDAO userDAO = new UserDAO(getContext());
        User user = userDAO.getUserByStudentId(String.valueOf(booking.getUserId()));
        tvName.setText(user.getFullName());

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Xem them" button click
                // Show dialog or perform other actions
            }
        });

        return convertView;
    }
    private void getWidth(View convertView){
        tvNumber = convertView.findViewById(R.id.tvNumber);
        //ivIcon = convertView.findViewById(R.id.ivIcon);  // for User icon
        tvName = convertView.findViewById(R.id.tvName);
        btnView = convertView.findViewById(R.id.btnXem);
    }

    public void filter(String text) {
        filteredBooks.clear();
        if (text.isEmpty()) {
            filteredBooks.addAll(originalBooks);
        } else {
            if(tryParseInt(text)){
                int ID = Integer.parseInt(text);
                for (Booking booking : originalBooks) {
                    if (booking.getUserId() == (ID)) {
                        filteredBooks.add(booking);
                    }
                }
            }else{
                Toast.makeText(getContext(), "Nhập mã sinh viên là số ", Toast.LENGTH_SHORT).show();
            }
        }
        notifyDataSetChanged();
    }

    private boolean tryParseInt(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
