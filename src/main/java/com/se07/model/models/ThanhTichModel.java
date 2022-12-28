package com.se07.model.models;

public class ThanhTichModel {
    private String maThanhTich;
    private String maNhanKhau;
    private int lop;
    private String truong;
    private String capThanhTich;
    private String kieuThanhTich;
    private String namHoc;
    private String maPhanThuong;
    private String xacNhan;

    public ThanhTichModel(String maThanhTich, String maNhanKhau, int lop, String truong, String capThanhTich,
                          String kieuThanhTich, String namHoc, String maPhanThuong, String xacNhan) {
        this.maThanhTich = maThanhTich;
        this.maNhanKhau = maNhanKhau;
        this.lop = lop;
        this.truong = truong;
        this.capThanhTich = capThanhTich;
        this.kieuThanhTich = kieuThanhTich;
        this.namHoc = namHoc;
        this.maPhanThuong = maPhanThuong;
        this.xacNhan = xacNhan;
    }

    public String getMaThanhTich() {
        return maThanhTich;
    }

    public void setMaThanhTich(String maThanhTich) {
        this.maThanhTich = maThanhTich;
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

    public String getXacNhan() {
        return xacNhan;
    }

    public void setXacNhan(String xacNhan) {
        this.xacNhan = xacNhan;
    }
}
