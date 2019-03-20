package com.example.johnluu.duan1;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.johnluu.duan1.adapter.TheLoaiAdapter;
import com.example.johnluu.duan1.database.TheLoaiDAO;
import com.example.johnluu.duan1.model.TheLoai;

import java.io.File;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class TheLoaiFragment extends Fragment {
    TheLoai tl;
    TheLoaiDAO tldao;
    TheLoaiAdapter theLoaiAdapter;
    ListView lv_theloaisach;
    ArrayList<TheLoai> dstl = new ArrayList<TheLoai>();
    public TheLoaiFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Thể Loại");
        View v = inflater.inflate(R.layout.fragment_the_loai, container, false);

        FloatingActionButton fab_themtheloai = v.findViewById(R.id.fab_themtheloai);
        lv_theloaisach=v.findViewById(R.id.lv_theloaisach);

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


    /////////////////////////////////////------------------Lấy file từ thiết bị
//   final int PICKFILE_RESULT_CODE=1;
//
//    public void brow(){
//        Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
//        fileintent.setType("*/*");
//        try {
//            startActivityForResult(fileintent, PICKFILE_RESULT_CODE);
//        } catch (ActivityNotFoundException e) {
//            Log.e("tag", "No activity can handle picking a file. Showing alternatives.");
//        }
//    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // TODO Fix no activity available
//        if (data == null)
//            return;
//        switch (requestCode) {
//            case PICKFILE_RESULT_CODE:
//                if (resultCode == RESULT_OK) {
//                    String FilePath = data.getData().getPath();
//
//                    //FilePath is your file as a string
//                    Toast.makeText(getContext(), FilePath+"", Toast.LENGTH_SHORT).show();
//                }
//        }
//    }
    public void capnhatgiaodien_theloai(){
        tldao = new TheLoaiDAO(getActivity());
        dstl = tldao.xemDSTheLoai();
        theLoaiAdapter = new TheLoaiAdapter(getActivity(),dstl,lv_theloaisach);
        lv_theloaisach.setAdapter(theLoaiAdapter);
    }
}
