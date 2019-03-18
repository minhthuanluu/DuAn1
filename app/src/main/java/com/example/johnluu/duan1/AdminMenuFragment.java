package com.example.johnluu.duan1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class AdminMenuFragment extends Fragment {

    public AdminMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_admin_menu, container, false);
        ImageView iv_theloai = view.findViewById(R.id.iv_theloai);

        iv_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheLoaiFragment theloaiFragment = new TheLoaiFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content,theloaiFragment,theloaiFragment.getTag())
                        .commit();
            }
        });
        return view;
    }


}
