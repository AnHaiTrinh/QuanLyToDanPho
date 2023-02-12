package com.se07.model.models;

public class ThongTinTraoThuongThanhTich {
    private int idNhap;
    private String maNhanKhau;
    private String hoTen;
    private String capThanhTich;
    private String kieuThanhTich;
    private String tenPhanThuong;
    private Integer donGia;
    private Integer soLuong;
    private Integer thanhTien;

    public ThongTinTraoThuongThanhTich(int idNhap, String maNhanKhau, String hoTen, String capThanhTich, String kieuThanhTich) {
        this.idNhap = idNhap;
        this.maNhanKhau = maNhanKhau;
        this.hoTen = hoTen;
        this.capThanhTich = capThanhTich;
        this.kieuThanhTich = kieuThanhTich;
    }

    public ThongTinTraoThuongThanhTich(int idNhap, String maNhanKhau, String hoTen, String capThanhTich,
                                       String kieuThanhTich, String tenPhanThuong, Integer donGia, Integer soLuong) {
        this.idNhap = idNhap;
        this.maNhanKhau = maNhanKhau;
        this.hoTen = hoTen;
        this.capThanhTich = capThanhTich;
        this.kieuThanhTich = kieuThanhTich;
        this.tenPhanThuong = tenPhanThuong;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.thanhTien = donGia * soLuong;
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

    public String getTenPhanThuong() {
        return tenPhanThuong;
    }

    public void setTenPhanThuong(String tenPhanThuong) {
        this.tenPhanThuong = tenPhanThuong;
    }

    public Integer getDonGia() {
        return donGia;
    }

    public void setDonGia(Integer donGia) {
        this.donGia = donGia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Integer thanhTien) {
        this.thanhTien = thanhTien;
    }
}
