package com.se07.model.models;

public class TraoThuongDisplayModel {
    private int idNhap;

    private String maNhanKhau;
    private String hoTen;
    private String tenDip;
    private int nam;
    private String tenPhanThuong;
    private int donGia;
    private int soLuong;
    private int thanhTien;

    public TraoThuongDisplayModel(int idNhap, String maNhanKhau, String hoTen, String tenDip, int nam,
                                  String tenPhanThuong, int donGia, int soLuong) {
        this.idNhap = idNhap;
        this.maNhanKhau = maNhanKhau;
        this.hoTen = hoTen;
        this.tenDip = tenDip;
        this.nam = nam;
        this.tenPhanThuong = tenPhanThuong;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.thanhTien = donGia * soLuong;
    }

    public int getIdNhap() {
        return idNhap;
    }

    public String getMaNhanKhau() {
        return maNhanKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getTenDip() {
        return tenDip;
    }

    public int getNam() {
        return nam;
    }

    public String getTenPhanThuong() {
        return tenPhanThuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getThanhTien() {
        return thanhTien;
    }
}
