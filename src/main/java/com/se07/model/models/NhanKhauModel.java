package com.se07.model.models;

import java.util.Date;

public class NhanKhauModel {
    private String maNhanKhau;
    private String maHoKhau;
    private String hoTen;
    private String bietDanh;
    private Date ngaySinh;
    private String gioiTinh;
    private String thuongTru;
    private String tonGiao;
    private double xacNhan;

    public NhanKhauModel(String maNhanKhau, String maHoKhau, String hoTen, String bietDanh, Date ngaySinh,
                         String gioiTinh, String thuongTru, String tonGiao, double xacNhan) {
        this.maNhanKhau = maNhanKhau;
        this.maHoKhau = maHoKhau;
        this.hoTen = hoTen;
        this.bietDanh = bietDanh;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.thuongTru = thuongTru;
        this.tonGiao = tonGiao;
        this.xacNhan = xacNhan;
    }

    public String getMaNhanKhau() {
        return maNhanKhau;
    }

    public void setMaNhanKhau(String maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
    }

    public String getMaHoKhau() {
        return maHoKhau;
    }

    public void setMaHoKhau(String maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getBietDanh() {
        return bietDanh;
    }

    public void setBietDanh(String bietDanh) {
        this.bietDanh = bietDanh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getThuongTru() {
        return thuongTru;
    }

    public void setThuongTru(String thuongTru) {
        this.thuongTru = thuongTru;
    }

    public String getTonGiao() {
        return tonGiao;
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao = tonGiao;
    }

    public double isXacNhan() {
        return xacNhan;
    }

    public void setXacNhan(double xacNhan) {
        this.xacNhan = xacNhan;
    }
}
