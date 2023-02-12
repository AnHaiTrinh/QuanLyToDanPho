package com.se07.model.models;

public class TraoThuongModel {
    private int idNhap;
    private int maPhanThuong;
    private int soLuong;

    public TraoThuongModel(int idNhap, int maPhanThuong, int soLuong) {
        this.idNhap = idNhap;
        this.maPhanThuong = maPhanThuong;
        this.soLuong = soLuong;
    }

    public int getIdNhap() {
        return idNhap;
    }

    public void setIdNhap(int idNhap) {
        this.idNhap = idNhap;
    }

    public int getMaPhanThuong() {
        return maPhanThuong;
    }

    public void setMaPhanThuong(int maPhanThuong) {
        this.maPhanThuong = maPhanThuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
