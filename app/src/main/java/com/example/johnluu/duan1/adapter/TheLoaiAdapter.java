package com.example.johnluu.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
    ArrayList<TheLoai> dstl = new ArrayList<TheLoai>();
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
                            int id=dstl.get(position)._idtheloai;
                            deleteItem(position,id);
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
                    final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(c);
                    LayoutInflater inf = ((Activity)c).getLayoutInflater();
                    final View vi = inf.inflate(R.layout.dialog_sua_the_loai,null);
                    final TextView tv_id_suaTheLoaidialog = vi.findViewById(R.id.tv_id_suaTheLoaidialog);
                    final EditText et_tentheloai_suaTheLoaidialog = vi.findViewById(R.id.et_tentheloai_suaTheLoaidialog);
                    Button bt_sua_theloai = vi.findViewById(R.id.bt_sua_theloai);
                    Button bt_huy = vi.findViewById(R.id.bt_huy);
                    builder.setView(vi);
                    final android.app.AlertDialog a = builder.create();


                    TheLoai tl = dstl.get(position);
                    final TheLoaiDAO tldao = new TheLoaiDAO(c);
                    tldao.xemDSTheLoai();
                    tv_id_suaTheLoaidialog.setText(tl._idtheloai+"");
                    et_tentheloai_suaTheLoaidialog.setText(tl.tentheloai);

                    bt_sua_theloai.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int _id = Integer.parseInt(tv_id_suaTheLoaidialog.getText().toString());
                            String tentheloai = et_tentheloai_suaTheLoaidialog.getText().toString();

                            editItem(position,_id,tentheloai);
                            a.dismiss();
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

    public void deleteItem(int position,int _id){
        dstl.remove(position);
        notifyItemRemoved(position);
        TheLoaiDAO tldao = new TheLoaiDAO(c);
        tldao.xoaTheLoai(_id);
        tldao.xemDSTheLoai();
    }

    public void editItem(int position,int _id,String tentheloai) {
        TheLoaiDAO tldao = new TheLoaiDAO(c);
        TheLoai tl = new TheLoai(_id, tentheloai);
        tldao.suaTheLoai(tl);
        tldao.xemDSTheLoai();
        notifyItemInserted(position);
    }
}
