package com.se07.model.models;

import java.util.Date;

public class HoKhauModel {
    private String maHoKhau;
    private String maChuHo;
    private String diaChi;
    private Date ngayLap;
    private int nguoiThucHien;

    public HoKhauModel(String maHoKhau, String maChuHo, String diaChi, Date ngayLap, int nguoiThucHien) {
        this.maHoKhau = maHoKhau;
        this.maChuHo = maChuHo;
        this.diaChi = diaChi;
        this.ngayLap = ngayLap;
        this.nguoiThucHien = nguoiThucHien;
    }

    public String getMaHoKhau() {
        return maHoKhau;
    }

    public void setMaHoKhau(String maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    public String getMaChuHo() {
        return maChuHo;
    }

    public void setMaChuHo(String maChuHo) {
        this.maChuHo = maChuHo;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getNguoiThucHien() {
        return nguoiThucHien;
    }

    public void setNguoiThucHien(int nguoiThucHien) {
        this.nguoiThucHien = nguoiThucHien;
    }
}
