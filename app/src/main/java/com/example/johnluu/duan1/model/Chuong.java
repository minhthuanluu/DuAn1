package com.example.johnluu.duan1.model;

public class Chuong {
    public int _idchuong;
    public int _idsach;
    public String duongdan;
    public String tenchuong;
    public String audio;

    public Chuong() {
    }

    public Chuong(int _idsach, String duongdan, String audio) {
        this._idsach = _idsach;
        this.duongdan = duongdan;
        this.audio = audio;
    }

    public Chuong(int _idchuong, int _idsach, String duongdan, String audio) {
        this._idchuong = _idchuong;
        this._idsach = _idsach;
        this.duongdan = duongdan;
        this.audio = audio;
    }
}
