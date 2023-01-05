package com.se07.model.models;

import java.util.Date;

public class NhanKhauModel {
    private String maNhanKhau;
    private String maHoKhau;
    private String hoTen;
    private String bietDanh;
    private Date ngaySinh;
    private String gioiTinh;
    private String tonGiao;
    private String tinhTrang;
    private String idNguoiThucHien;

    public NhanKhauModel(String maNhanKhau, String maHoKhau, String hoTen, String bietDanh, Date ngaySinh,
                         String gioiTinh, String tonGiao, String tinhTrang, String idNguoiThucHien) {
        this.maNhanKhau = maNhanKhau;
        this.maHoKhau = maHoKhau;
        this.hoTen = hoTen;
        this.bietDanh = bietDanh;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.tonGiao = tonGiao;
        this.tinhTrang = tinhTrang;
        this.idNguoiThucHien = idNguoiThucHien;
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

    public String getTonGiao() {
        return tonGiao;
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao = tonGiao;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getIdNguoiThucHien() {
        return idNguoiThucHien;
    }

    public void setIdNguoiThucHien(String idNguoiThucHien) {
        this.idNguoiThucHien = idNguoiThucHien;
    }
}
