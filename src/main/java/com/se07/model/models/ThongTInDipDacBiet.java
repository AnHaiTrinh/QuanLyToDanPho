package com.se07.model.models;

public class ThongTInDipDacBiet {
    private int idNhap;
    private String maNhanKhau;
    private String dipdacBiet;
    private int nam;
    private String tinhTrang;
    private int idNguoiNhap;

    public ThongTInDipDacBiet(int idNhap, String maNhanKhau, String dipdacBiet, int nam, String tinhTrang,
                              int idNguoiNhap) {
        this.idNhap = idNhap;
        this.maNhanKhau = maNhanKhau;
        this.dipdacBiet = dipdacBiet;
        this.nam = nam;
        this.tinhTrang = tinhTrang;
        this.idNguoiNhap = idNguoiNhap;
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

    public String getDipdacBiet() {
        return dipdacBiet;
    }

    public void setDipdacBiet(String dipdacBiet) {
        this.dipdacBiet = dipdacBiet;
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

    public int getIdNguoiNhap() {
        return idNguoiNhap;
    }

    public void setIdNguoiNhap(int idNguoiNhap) {
        this.idNguoiNhap = idNguoiNhap;
    }
}
