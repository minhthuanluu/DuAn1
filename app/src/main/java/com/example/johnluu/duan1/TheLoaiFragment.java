package com.example.johnluu.duan1;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.johnluu.duan1.adapter.TheLoaiAdapter;
import com.example.johnluu.duan1.database.TheLoaiDAO;
import com.example.johnluu.duan1.model.TheLoai;

import java.util.ArrayList;

public class TheLoaiFragment extends Fragment {
    TheLoai tl;
    TheLoaiDAO tldao;
    TheLoaiAdapter theLoaiAdapter;
    RecyclerView rv_theloai;
    ArrayList<TheLoai> dstl;
    public TheLoaiFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Thể Loại");
        View v = inflater.inflate(R.layout.fragment_the_loai, container, false);

        FloatingActionButton fab_themtheloai = v.findViewById(R.id.fab_themtheloai);
        rv_theloai=v.findViewById(R.id.rv_theloaisach);

        capnhatgiaodien_theloai();

        fab_themtheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inf = getLayoutInflater();
                final View vi = inf.inflate(R.layout.dialog_them_the_loai,null);
                final EditText et_tentheloai = vi.findViewById(R.id.et_tentheloai_themTheLoaidialog);
                Button bt_them_theloai = vi.findViewById(R.id.bt_them_theloai);
                Button bt_huy = vi.findViewById(R.id.bt_huy);

                builder.setView(vi);
                final AlertDialog a = builder.create();

                bt_them_theloai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tentheloai = et_tentheloai.getText().toString();

                            if(tentheloai.isEmpty()){
                                Toast.makeText(getContext(), "Tên Thể Loại không được rỗng!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                            tl = new TheLoai(tentheloai);
                            tldao = new TheLoaiDAO(getContext());
                            tldao.ThemTheLoai(tl);
                            capnhatgiaodien_theloai();
                            a.dismiss();
                        }

                    }
                });

                bt_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a.dismiss();
                    }
                });

                a.show();
            }
        });

        return v;
    }

    public void capnhatgiaodien_theloai(){
        tldao = new TheLoaiDAO(getActivity());
        dstl = tldao.xemDSTheLoai();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_theloai.setLayoutManager(layoutManager);
        theLoaiAdapter = new TheLoaiAdapter(getActivity(),dstl);
        rv_theloai.setAdapter(theLoaiAdapter);
    }

}
