package com.example.myapplication.services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.constants.BookingConstants;
import com.example.myapplication.models.Booking;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<Booking> notificationList;
    private Context context;

    public NotificationAdapter(Context context, List<Booking> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Booking notification = notificationList.get(position);

        holder.notificationTime.setText(notification.getTime());

        if (BookingConstants.SUCCESS.equals(notification.getStatus())) {
            holder.notificationMessage.setText(BookingConstants.SUCCESS);
            holder.notificationSubtitle.setText("*Yêu cau den dung gio");
            holder.notificationMessage.setTextColor(context.getResources().getColor(R.color.green));
        } else if (BookingConstants.FAIL.equals(notification.getStatus())) {
            holder.notificationMessage.setText(BookingConstants.FAIL);
            holder.notificationSubtitle.setText("");
            holder.notificationMessage.setTextColor(context.getResources().getColor(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {

        TextView notificationMessage;
        TextView notificationSubtitle;
        TextView notificationTime;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationMessage = itemView.findViewById(R.id.notificationMessage);
            notificationSubtitle = itemView.findViewById(R.id.notificationSubtitle);
            notificationTime = itemView.findViewById(R.id.notificationTime);
        }
    }
}
