package com.example.johnluu.duan1.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context c) {
        super(c, "khosachviet", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         /*
            create table theloai
            (
                _idtheloai integer primary key autoincrement,
                tentheloai text
            )

            create table sach
            (
                _idsach integer primary key autoincrement,
                _idtheloai integer,
                _idtacgia integer,
                tieude text,
                hinhanh text
            )

            create table tacgia
            (
                _idtacgia integer primary key autoincrement,
                tentacgia text
            )

            create table chuong
            (
                _idchuong integer primary key autoincrement,
                _idsach integer,
                tenchuong text,
                duongdan text,
                audio text
            )
        */

        String sql = "create table theloai" +
                "(" +
                "_idtheloai integer primary key autoincrement," +
                "tentheloai text" +
                ")";
        db.execSQL(sql);

        String sql2 = "create table sach" +
                "(" +
                "_idsach integer primary key autoincrement," +
                "_idtheloai integer," +
                "_idtacgia integer," +
                "tieude text," +
                "hinhanh text" +
                ")";
        db.execSQL(sql2);

        String sql3 = "create table tacgia" +
                "(" +
                "_idtacgia integer primary key autoincrement," +
                "tentacgia text" +
                ")";
        db.execSQL(sql3);

        String sql4 = "create table chuong" +
                "(" +
                "_idchuong integer primary key autoincrement," +
                "_idsach integer," +
                "duongdan text," +
                "tenchuong text,"+
                "audio text" +
                ")";
        db.execSQL(sql4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists theloai");
        db.execSQL("drop table if exists sach");
        db.execSQL("drop table if exists tacgia");
        db.execSQL("drop table if exists chuong");
    }
}
