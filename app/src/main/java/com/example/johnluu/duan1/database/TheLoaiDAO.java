package com.example.johnluu.duan1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.johnluu.duan1.SQLite.DBHelper;
import com.example.johnluu.duan1.model.TheLoai;

import java.util.ArrayList;

public class TheLoaiDAO {
    SQLiteDatabase db;
    DBHelper dbh;
    Context c;

    public TheLoaiDAO(Context c) {
        this.c=c;
        this.dbh=new DBHelper(c);
    }

    public void ThemTheLoai(TheLoai tl){
        dbh=new DBHelper(c);
        db = dbh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tentheloai",tl.tentheloai);
        db.insert("theloai",null,values);
    }

    public ArrayList<TheLoai> xemDSTheLoai(){
        ArrayList<TheLoai> dstheloai = new ArrayList<TheLoai>();
        dbh = new DBHelper(c);
        db = dbh.getReadableDatabase();
        Cursor c = db.rawQuery("select * from theloai",null);
        if(c.moveToFirst()){
            do{
                int _idtheloai = c.getInt(0);
                String tentheloai = c.getString(1);
                TheLoai theloai = new TheLoai(_idtheloai,tentheloai);
                dstheloai.add(theloai);
            }while(c.moveToNext());
        }
        return dstheloai;
    }

    public void xoaTheLoai(int _id){
        db = dbh.getWritableDatabase();
        db.delete("theloai","_idtheloai=?",new String[]{_id+""});
    }

    public void suaTheLoai(TheLoai tl){
        db = dbh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tentheloai",tl.tentheloai);
        db.update("theloai",values,"_id = ?",new String[]{tl._idtheloai+""});
    }
}
