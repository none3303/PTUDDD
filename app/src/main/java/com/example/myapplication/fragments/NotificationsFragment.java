//package com.example.myapplication.fragments;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.myapplication.R;
//import com.example.myapplication.dao.BookingDAO;
//import com.example.myapplication.dao.UserDAO;
//import com.example.myapplication.models.Booking;
//import com.example.myapplication.services.NotificationAdapter;
//
//import java.util.List;
//
//public class NotificationsFragment extends Fragment {
//
//    private RecyclerView recyclerViewNotifications;
//    private NotificationAdapter notificationAdapter;
//    private List<Booking> notificationList;
//
//    private BookingDAO bookingDAO;
//    private UserDAO userDAO;
//    public NotificationsFragment() {
//        // Required empty public constructor
//    }
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_notification);
//    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_notification, container, false);
//
//        recyclerViewNotifications = view.findViewById(R.id.recyclerViewNotifications);
//        recyclerViewNotifications.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        bookingDAO = new BookingDAO(this.getContext());
//        userDAO = new UserDAO(this.getContext());
//
////        int userId = getUserId();
//////        if (userId == 12) {
////            // Add notifications with status "ACCEPT"
////            notificationList.addAll(bookingDAO.getAllStatusBookingsByUserId(BookingConstants.ACCEPT, 12));
////            // Add notifications with status "REJECT"
////            notificationList.addAll(bookingDAO.getAllStatusBookingsByUserId(BookingConstants.REJECT, 12));
//////        }
//
//        // Set the adapter
//        notificationAdapter = new NotificationAdapter(getContext(), notificationList);
//        recyclerViewNotifications.setAdapter(notificationAdapter);
//
//        return view;
//    }
//
//    // Method to get userId (replace with actual implementation)
////    private int getUserId() {
////        // Example: get username from shared preferences or passed arguments
////        String username = getUsername();
////        if (username != null) {
////            User user = userDAO.getUserByUsername(username);
////            if (user != null) {
////                return user.getId();
////            }
////        }
////        return -1; // Placeholder value, replace with actual logic
////    }
////
////    // Method to get username (replace with actual implementation)
////    private String getUsername() {
////        // Example: get username from shared preferences
////        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
////        return sharedPreferences.getString("USERNAME", null);
////    }
//}
