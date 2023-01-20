package com.se07.model.models;

import java.io.File;

public class ThongTinThanhTichDisplayModel {
    private int idNhap;
    private String maNhanKhau;
    private String hoTen;
    private String tenDip;
    private int nam;
    private int lop;
    private String truong;
    private String capThanhTich;
    private String kieuThanhTich;
    private File minhChung;
    private String tinhTrang;

    public ThongTinThanhTichDisplayModel(int idNhap, String maNhanKhau, String hoTen, String tenDip, int nam, int lop,
                                         String truong, String capThanhTich, String kieuThanhTich,
                                         File minhChung, String tinhTrang) {
        this.idNhap = idNhap;
        this.maNhanKhau = maNhanKhau;
        this.hoTen = hoTen;
        this.tenDip = tenDip;
        this.nam = nam;
        this.lop = lop;
        this.truong = truong;
        this.capThanhTich = capThanhTich;
        this.kieuThanhTich = kieuThanhTich;
        this.minhChung = minhChung;
        this.tinhTrang = tinhTrang;
    }

    public int getIdNhap() {
        return idNhap;
    }

    public void setIdNhap(int idNhap) {
        this.idNhap = idNhap;
    }

    public String getMaNhanKhau() {
        return maNhanKhau;
    }

    public void setMaNhanKhau(String maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTenDip() {
        return tenDip;
    }

    public void setTenDip(String tenDip) {
        this.tenDip = tenDip;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public int getLop() {
        return lop;
    }

    public void setLop(int lop) {
        this.lop = lop;
    }

    public String getTruong() {
        return truong;
    }

    public void setTruong(String truong) {
        this.truong = truong;
    }

    public String getCapThanhTich() {
        return capThanhTich;
    }

    public void setCapThanhTich(String capThanhTich) {
        this.capThanhTich = capThanhTich;
    }

    public String getKieuThanhTich() {
        return kieuThanhTich;
    }

    public void setKieuThanhTich(String kieuThanhTich) {
        this.kieuThanhTich = kieuThanhTich;
    }

    public File getMinhChung() {
        return minhChung;
    }

    public void setMinhChung(File minhChung) {
        this.minhChung = minhChung;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
