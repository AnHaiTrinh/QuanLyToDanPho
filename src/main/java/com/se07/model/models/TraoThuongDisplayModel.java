package com.se07.model.models;

public class TraoThuongDisplayModel {
    private int idNhap;
    private String tenPhanThuong;
    private double guaTri;
    private int soLuong;

    public TraoThuongDisplayModel(int idNhap, String tenPhanThuong, double guaTri, int soLuong) {
        this.idNhap = idNhap;
        this.tenPhanThuong = tenPhanThuong;
        this.guaTri = guaTri;
        this.soLuong = soLuong;
    }

    public int getIdNhap() {
        return idNhap;
    }

    public void setIdNhap(int idNhap) {
        this.idNhap = idNhap;
    }

    public String getTenPhanThuong() {
        return tenPhanThuong;
    }

    public void setTenPhanThuong(String tenPhanThuong) {
        this.tenPhanThuong = tenPhanThuong;
    }

    public double getGuaTri() {
        return guaTri;
    }

    public void setGuaTri(double guaTri) {
        this.guaTri = guaTri;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
