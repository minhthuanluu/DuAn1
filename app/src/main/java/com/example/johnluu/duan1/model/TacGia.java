package com.example.johnluu.duan1.model;

public class TacGia {
    public int _idtacgia;
    public String tentacgia;

    public TacGia() {
    }

    public TacGia(String tentacgia) {
        this.tentacgia = tentacgia;
    }

    public TacGia(int _idtacgia, String tentacgia) {
        this._idtacgia = _idtacgia;
        this.tentacgia = tentacgia;
    }
}
