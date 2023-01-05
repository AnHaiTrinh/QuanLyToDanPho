package com.se07.model.models;

public class ThongTinThanhTichModel {
    private int idNhap;
    private String maNhanKhau;
    private int lop;
    private String truong;
    private String capThanhTich;
    private String kieuThanhTich;
    private String namHoc;
    private String maPhanThuong;
    private byte[] minhChung;
    private String tinhTrang;
    private int idNguoiThucHien;

    public ThongTinThanhTichModel(int idNhap, String maNhanKhau, int lop, String truong, String capThanhTich,
                                  String kieuThanhTich, String namHoc, String maPhanThuong, byte[] minhChung,
                                  String tinhTrang, int idNguoiThucHien) {
        this.idNhap = idNhap;
        this.maNhanKhau = maNhanKhau;
        this.lop = lop;
        this.truong = truong;
        this.capThanhTich = capThanhTich;
        this.kieuThanhTich = kieuThanhTich;
        this.namHoc = namHoc;
        this.maPhanThuong = maPhanThuong;
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

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public String getMaPhanThuong() {
        return maPhanThuong;
    }

    public void setMaPhanThuong(String maPhanThuong) {
        this.maPhanThuong = maPhanThuong;
    }

    public byte[] getMinhChung() {
        return minhChung;
    }

    public void setMinhChung(byte[] minhChung) {
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
