package com.se07.model.models;

public class ThongTinDipDacBietModel {
    private int idNhap;
    private int idDip;
    private String maNhanKhau;
    private String tinhTrang;
    private int idNguoiThucHien;

    public ThongTinDipDacBietModel(int idDip, String maNhanKhau, String tinhTrang, int idNguoiThucHien) {
        this.idDip = idDip;
        this.maNhanKhau = maNhanKhau;
        this.tinhTrang = tinhTrang;
        this.idNguoiThucHien = idNguoiThucHien;
    }

    public ThongTinDipDacBietModel(int idNhap, int idDip, String maNhanKhau, String tinhTrang, int idNguoiThucHien) {
        this.idNhap = idNhap;
        this.idDip = idDip;
        this.maNhanKhau = maNhanKhau;
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
