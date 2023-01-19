package com.se07.model.models;

import java.io.File;

public class ThongTinThanhTichModel {
    private int idNhap;
    private int idDip;
    private String maNhanKhau;
    private int lop;
    private String truong;
    private String capThanhTich;
    private String kieuThanhTich;
    private File minhChung;
    private String tinhTrang;
    private int idNguoiThucHien;

    public ThongTinThanhTichModel(int idDip, String maNhanKhau, int lop, String truong, String capThanhTich,
                                  String kieuThanhTich, File minhChung, String tinhTrang, int idNguoiThucHien) {
        this.idDip = idDip;
        this.maNhanKhau = maNhanKhau;
        this.lop = lop;
        this.truong = truong;
        this.capThanhTich = capThanhTich;
        this.kieuThanhTich = kieuThanhTich;
        this.minhChung = minhChung;
        this.tinhTrang = tinhTrang;
        this.idNguoiThucHien = idNguoiThucHien;
    }

    public ThongTinThanhTichModel(int idNhap, int idDip, String maNhanKhau, int lop, String truong, String capThanhTich,
                                  String kieuThanhTich, File minhChung, String tinhTrang, int idNguoiThucHien) {
        this.idNhap = idNhap;
        this.idDip = idDip;
        this.maNhanKhau = maNhanKhau;
        this.lop = lop;
        this.truong = truong;
        this.capThanhTich = capThanhTich;
        this.kieuThanhTich = kieuThanhTich;
        this.minhChung = minhChung;
        this.tinhTrang = tinhTrang;
        this.idNguoiThucHien = idNguoiThucHien;
    }

    public int getIdNhap() {
        return idNhap;
    }

    public void setIdNhap(int idNhap) {
        this.idNhap = idNhap;
    }

    public int getIdDip() {
        return idDip;
    }

    public void setIdDip(int idDip) {
        this.idDip = idDip;
    }

    public String getMaNhanKhau() {
        return maNhanKhau;
    }

    public void setMaNhanKhau(String maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
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

    public int getIdNguoiThucHien() {
        return idNguoiThucHien;
    }

    public void setIdNguoiThucHien(int idNguoiThucHien) {
        this.idNguoiThucHien = idNguoiThucHien;
    }
}
