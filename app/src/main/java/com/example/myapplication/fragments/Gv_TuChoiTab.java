package com.example.myapplication.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.User;
import com.example.myapplication.services.GV_DatLichListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Gv_TuChoiTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Gv_TuChoiTab extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView listView;
    EditText searchEditText;
    Button searchButton;
    private GV_DatLichListAdapter adapter;

    public Gv_TuChoiTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Gv_DangChoTab.
     */
    // TODO: Rename and change types and number of parameters
    public static Gv_TuChoiTab newInstance(String param1, String param2) {
        Gv_TuChoiTab fragment = new Gv_TuChoiTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gv__chap_nhan_tab, container, false);
        getWidth(view);

        List<Booking> bookings = new ArrayList<>();

        //Query here
        // ...
        adapter = new GV_DatLichListAdapter(getActivity(), bookings);
        listView.setAdapter(adapter);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString();
                adapter.filter(query);
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = searchEditText.getText().toString();
                adapter.filter(query);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        registerForContextMenu(listView);

        return view;
    }
    public void getWidth(View view){
        listView = (ListView)view.findViewById(R.id.ListView);
        searchEditText = view.findViewById(R.id.searchEditText);
        searchButton = view.findViewById(R.id.searchButton);
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.gv_datlich_tuchoi_listview_item_select_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Booking selectedBooking = adapter.getItem(info.position);
        if(item.getItemId() == R.id.menu_retrieve) {
            //thêm vào list chờ và xóa khỏi list này. (thay đổi status của booking để query đúng)
            return true;
        }else {
            return super.onContextItemSelected(item);
        }
    }
}