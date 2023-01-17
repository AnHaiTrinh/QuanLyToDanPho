package com.se07.model.models;

public class ThongTinDipDacBietDisplayModel {
    private int idNhap;
    private String maNhanKhau;
    private String hoTen;
    private String tenDip;
    private int nam;
    private String tinhTrang;

    public ThongTinDipDacBietDisplayModel(int idNhap, String maNhanKhau, String hoTen, String tenDip,
                                          int nam, String tinhTrang) {
        this.idNhap = idNhap;
        this.maNhanKhau = maNhanKhau;
        this.hoTen = hoTen;
        this.tenDip = tenDip;
        this.nam = nam;
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

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
