package com.example.johnluu.duan1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.johnluu.duan1.SQLite.DBHelper;
import com.example.johnluu.duan1.model.Sach;

import java.util.ArrayList;

public class SachDAO {
    SQLiteDatabase db;
    Context c;
    DBHelper dbh;

    public SachDAO(Context c) {
        this.c=c;
        dbh=new DBHelper(c);
    }

    public void ThemSach(Sach s){
        db = dbh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_idtheloai",s._idtheloai);
        values.put("_idtacgia",s._idtacgia);
        values.put("tieude",s.tieude);
        values.put("hinhanh",s.hinhanh);
        db.insert("sach",null,values);
    }

    public ArrayList<Sach> xemDSSach(){
        ArrayList<Sach> dssach = new ArrayList<Sach>();
        dbh = new DBHelper(c);
        db = dbh.getReadableDatabase();
        Cursor c = db.rawQuery("select * from sach",null);
        if(c.moveToFirst()){
            do{
                int _idtheloai = c.getInt(0);
                int _idtacgia = c.getInt(1);
                String tieude = c.getString(2);
                String hinhanh = c.getString(3);
                Sach sach = new Sach(_idtheloai,_idtacgia,tieude,hinhanh);
                dssach.add(sach);
            }while(c.moveToNext());
        }
        return dssach;
    }

    public void xoaSach(int _id){
        db = dbh.getWritableDatabase();
        db.delete("theloai","_idsach=?",new String[]{_id+""});
    }

    public void suaSach(Sach s){
        db = dbh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_idtheloai",s._idtheloai);
        values.put("_idtacgia",s._idtacgia);
        values.put("tieude",s.tieude);
        values.put("hinhanh",s.hinhanh);
        db.update("sach",values,"_idsach= ?",new String[]{s._idsach+""});
    }
}
