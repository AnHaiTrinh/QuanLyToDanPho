package com.se07.model.models;

public class PhanThuongModel {
    private String maPhanThuong;
    private String tenPhanThuong;
    private double giaTri;

    public PhanThuongModel(String maPhanThuong, String tenPhanThuong, double giaTri) {
        this.maPhanThuong = maPhanThuong;
        this.tenPhanThuong = tenPhanThuong;
        this.giaTri = giaTri;
    }

    public String getMaPhanThuong() {
        return maPhanThuong;
    }

    public void setMaPhanThuong(String maPhanThuong) {
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

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }
}
