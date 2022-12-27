package com.se07.model.models;

public class DipDacBiet {
    private String maDipDacBiet;
    private String maNhanKhau;
    private String maPhanThuong;
    private String dipDacBiet;
    private int nam;
    private String xacNhan;

    public DipDacBiet(String maDipDacBiet, String maNhanKhau, String maPhanThuong, String dipDacBiet, int nam, String xacNhan) {
        this.maDipDacBiet = maDipDacBiet;
        this.maNhanKhau = maNhanKhau;
        this.maPhanThuong = maPhanThuong;
        this.dipDacBiet = dipDacBiet;
        this.nam = nam;
        this.xacNhan = xacNhan;
    }

    public String getMaDipDacBiet() {
        return maDipDacBiet;
    }

    public void setMaDipDacBiet(String maDipDacBiet) {
        this.maDipDacBiet = maDipDacBiet;
    }

    public String getMaNhanKhau() {
        return maNhanKhau;
    }

    public void setMaNhanKhau(String maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
    }

    public String getMaPhanThuong() {
        return maPhanThuong;
    }

    public void setMaPhanThuong(String maPhanThuong) {
        this.maPhanThuong = maPhanThuong;
    }

    public String getDipDacBiet() {
        return dipDacBiet;
    }

    public void setDipDacBiet(String dipDacBiet) {
        this.dipDacBiet = dipDacBiet;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public String getXacNhan() {
        return xacNhan;
    }

    public void setXacNhan(String xacNhan) {
        this.xacNhan = xacNhan;
    }
}
