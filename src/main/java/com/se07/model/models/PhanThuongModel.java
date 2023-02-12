package com.se07.model.models;

public class PhanThuongModel {
    private int maPhanThuong;
    private String tenPhanThuong;
    private int giaTri;

    public PhanThuongModel(int maPhanThuong, String tenPhanThuong, int giaTri) {
        this.maPhanThuong = maPhanThuong;
        this.tenPhanThuong = tenPhanThuong;
        this.giaTri = giaTri;
    }

    public PhanThuongModel(String tenPhanThuong, int giaTri) {
        this.tenPhanThuong = tenPhanThuong;
        this.giaTri = giaTri;
    }

    public int getMaPhanThuong() {
        return maPhanThuong;
    }

    public void setMaPhanThuong(int maPhanThuong) {
        this.maPhanThuong = maPhanThuong;
    }

    public String getTenPhanThuong() {
        return tenPhanThuong;
    }

    public void setTenPhanThuong(String tenPhanThuong) {
        this.tenPhanThuong = tenPhanThuong;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }
}
