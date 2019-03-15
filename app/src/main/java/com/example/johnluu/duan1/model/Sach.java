package com.example.johnluu.duan1.model;

public class Sach {
    public int _idsach;
    public int _idtheloai;
    public int _idtacgia;
    public String tieude;
    public String hinhanh;

    public Sach() {
    }

    public Sach(int _idtheloai, int _idtacgia, String tieude, String hinhanh) {
        this._idtheloai = _idtheloai;
        this._idtacgia = _idtacgia;
        this.tieude = tieude;
        this.hinhanh = hinhanh;
    }

    public Sach(int _idsach, int _idtheloai, int _idtacgia, String tieude, String hinhanh) {
        this._idsach = _idsach;
        this._idtheloai = _idtheloai;
        this._idtacgia = _idtacgia;
        this.tieude = tieude;
        this.hinhanh = hinhanh;
    }
}
