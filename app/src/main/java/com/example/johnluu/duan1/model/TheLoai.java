package com.example.johnluu.duan1.model;

public class TheLoai {
    public int _idtheloai;
    public String tentheloai;

    public TheLoai() {
    }

    public TheLoai(int _idtheloai, String tentheloai) {
        this._idtheloai = _idtheloai;
        this.tentheloai = tentheloai;
    }

    public TheLoai(String tentheloai) {
        this.tentheloai = tentheloai;
    }
}
