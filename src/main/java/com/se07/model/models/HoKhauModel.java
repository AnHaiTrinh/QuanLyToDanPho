package com.se07.model.models;

import java.util.Date;

public class HoKhauModel {
    private String maHoKhau;
    private String chuHo;
    private String diaChi;
    private Date ngayLap;
    private int idNguoiThucHien;

    public HoKhauModel(String maHoKhau, String chuHo, String diaChi, Date ngayLap, int idNguoiThucHien) {
        this.maHoKhau = maHoKhau;
        this.chuHo = chuHo;
        this.diaChi = diaChi;
        this.ngayLap = ngayLap;
        this.idNguoiThucHien = idNguoiThucHien;
    }

    public String getMaHoKhau() {
        return maHoKhau;
    }

    public void setMaHoKhau(String maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    public String getChuHo() {
        return chuHo;
    }

    public void setChuHo(String chuHo) {
        this.chuHo = chuHo;
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

    public int getIdNguoiThucHien() {
        return idNguoiThucHien;
    }

    public void setIdNguoiThucHien(int idNguoiThucHien) {
        this.idNguoiThucHien = idNguoiThucHien;
    }
}
