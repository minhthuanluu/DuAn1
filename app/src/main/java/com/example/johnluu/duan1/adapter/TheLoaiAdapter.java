package com.example.johnluu.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.johnluu.duan1.R;
import com.example.johnluu.duan1.TheLoaiFragment;
import com.example.johnluu.duan1.database.SachDAO;
import com.example.johnluu.duan1.database.TheLoaiDAO;
import com.example.johnluu.duan1.model.Sach;
import com.example.johnluu.duan1.model.TheLoai;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class TheLoaiAdapter extends BaseAdapter {
    Context c;
    ArrayList<TheLoai> dstl = new ArrayList<TheLoai>();
    ListView lv;
    public TheLoaiAdapter(Context c, ArrayList<TheLoai> dstl,ListView lv){
        this.c=c;
        this.dstl=dstl;
        this.lv=lv;
    }
    @Override
    public int getCount() {
        return dstl.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inf = ((Activity)c).getLayoutInflater();
        view = inf.inflate(R.layout.one_item_theloai,null);
        TextView tv_idtheloai_one_item = view.findViewById(R.id.tv_idtheloai_one_item);
        TextView tv_tentheloai_one_item = view.findViewById(R.id.tv_tentheloai_one_item);
        ImageView iv_option_one_item_theloai = view.findViewById(R.id.iv_option_one_item_theloai);

            TheLoai tl = dstl.get(i);
            tv_idtheloai_one_item.setText(tl._idtheloai+"");
            tv_tentheloai_one_item.setText(tl.tentheloai);

            iv_option_one_item_theloai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showMultiPopup(view,i);
                }
            });

        return view;
    }

    public void showMultiPopup(View view, final int position) {
        PopupMenu popup = new PopupMenu(c, view);
        popup.inflate(R.menu.del_edit_function);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.xoa_item:
                        final AlertDialog.Builder dialog = new AlertDialog.Builder(c);
                        dialog.setTitle("Thông báo");
                        dialog.setMessage("Bạn có muốn xóa thể loại này không?");
                        dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                TheLoai tl = dstl.get(position);
                                int _id = tl._idtheloai;

                                TheLoaiDAO tlsdao = new TheLoaiDAO(c);
                                tlsdao.xoaTheLoai(_id);
                                capnhatgiaodien_theloai();
                            }
                        });
                        dialog.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });
                        dialog.show();
                        break;
                    case R.id.sua_item:
                        final AlertDialog.Builder builder = new AlertDialog.Builder(c);
                        LayoutInflater inf = ((Activity)c).getLayoutInflater();
                        final View view= inf.inflate(R.layout.dialog_sua_the_loai,null);
                        builder.setView(view);

                        final AlertDialog a = builder.create();

                        final TheLoai tl = dstl.get(position);
                        final TextView tv_id = view.findViewById(R.id.tv_id_suaTheLoaidialog);
                        final EditText et_tentl = view.findViewById(R.id.et_tentheloai_suaTheLoaidialog);
                        Button bt_sua = view.findViewById(R.id.bt_sua_theloai);
                        Button bt_huy = view.findViewById(R.id.bt_huy_theloai);

                        tv_id.setText(tl._idtheloai+"");
                        et_tentl.setText(tl.tentheloai);

                        bt_sua.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int _id = Integer.parseInt(tv_id.getText().toString());
                                String tentl = et_tentl.getText().toString();

                                TheLoaiDAO tldao = new TheLoaiDAO(c);
                                dstl = tldao.xemDSTheLoai();

                                TheLoai tlsachupdate = new TheLoai(_id,tentl);
                                tldao.suaTheLoai(tlsachupdate);
                                capnhatgiaodien_theloai();

                                a.cancel();
                            }
                        });

                        bt_huy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                a.cancel();
                            }
                        });


                        a.show();

                    default:

                        return true;
                }
                return false;
            }

        });
        popup.show();
    }

    public void capnhatgiaodien_theloai(){
        TheLoaiDAO tldao = new TheLoaiDAO(c);
        dstl = tldao.xemDSTheLoai();
        TheLoaiAdapter theLoaiAdapter = new TheLoaiAdapter(c,dstl,lv);
        lv.setAdapter(theLoaiAdapter);
    }


}
