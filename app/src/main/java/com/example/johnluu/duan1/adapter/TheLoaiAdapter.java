package com.example.johnluu.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnluu.duan1.R;
import com.example.johnluu.duan1.TheLoaiFragment;
import com.example.johnluu.duan1.database.TheLoaiDAO;
import com.example.johnluu.duan1.model.TheLoai;

import java.util.ArrayList;

import static com.example.johnluu.duan1.R.id.xoa_item;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.TheLoaiViewHolder>{
    Context c;
    ArrayList<TheLoai> dstl;
    public TheLoaiAdapter(Context c,ArrayList<TheLoai> dstl) {
        this.c=c;
        this.dstl=dstl;
    }

    @NonNull
    @Override
    public TheLoaiAdapter.TheLoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(c);
        View v = inf.inflate(R.layout.one_item_theloai,parent,false);
        return new TheLoaiViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiAdapter.TheLoaiViewHolder holder, final int position) {
        holder.tv_idtheloai_one_item.setText(dstl.get(position)._idtheloai+"");
        holder.tv_tentheloai_one_item.setText(dstl.get(position).tentheloai);

        holder.iv_option_one_item_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMultiPopup(view,position);
            }
        });
    }


    public void showMultiPopup(View view, final int position) {
        PopupMenu popup = new PopupMenu(c, view);
        popup.inflate(R.menu.del_edit_function);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                case R.id.xoa_item:
                AlertDialog.Builder dialog = new AlertDialog.Builder(c);
                dialog.setTitle("Thông báo");
                dialog.setMessage("Bạn có muốn xóa thể loại này không?");
                dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        deleteItem(i);
                        Toast.makeText(c, "Thể Loại đã được xóa", Toast.LENGTH_SHORT).show();
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
//                    final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(c);
//                    LayoutInflater inf = ((Activity)c).getLayoutInflater();
//                    final View vi = inf.inflate(R.layout.dialog_sua_the_loai,null);
//                    TextView tv_id_themTheLoaidialog = vi.findViewById(R.id.tv_id_themTheLoaidialog);
//                    EditText
//                    builder.setView(vi);
//                    final android.app.AlertDialog a = builder.create();
//
//                    TheLoai tl = dstl.get(position);
//
//
//                    a.show();
                }
                return false;
            }

        });
        popup.show();
    }

    @Override
    public int getItemCount() {
        return dstl.size();
    }

    public class TheLoaiViewHolder extends RecyclerView.ViewHolder{
        TextView tv_idtheloai_one_item;
        TextView tv_tentheloai_one_item;
        ImageView iv_option_one_item_theloai;
        public TheLoaiViewHolder(View itemView) {
            super(itemView);
            tv_idtheloai_one_item=itemView.findViewById(R.id.tv_idtheloai_one_item);
            tv_tentheloai_one_item=itemView.findViewById(R.id.tv_tentheloai_one_item);
            iv_option_one_item_theloai=itemView.findViewById(R.id.iv_option_one_item_theloai);
        }
    }

    public void deleteItem(int position){
        TheLoaiDAO theloaiDAO = new TheLoaiDAO(c);
        theloaiDAO.xoaTheLoai(dstl.get(position)._idtheloai);
        notifyItemRemoved(position);
        dstl = theloaiDAO.xemDSTheLoai();
    }
}
