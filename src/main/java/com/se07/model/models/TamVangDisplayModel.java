package com.se07.model.models;

import java.util.Date;

public class TamVangDisplayModel {
    private int maTamVang;
    private String maNhanKhau;
    private String hoTen;
    private String noiTamVang;
    private Date tuNgay;
    private Date denNgay;
    private String lyDo;
    private String tinhTrang;

    public TamVangDisplayModel(String maNhanKhau, String hoTen, String noiTamVang, Date tuNgay, Date denNgay,
                               String lyDo, String tinhTrang) {
        this.maNhanKhau = maNhanKhau;
        this.hoTen = hoTen;
        this.noiTamVang = noiTamVang;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
        this.tinhTrang = tinhTrang;
    }

    public TamVangDisplayModel(int maTamVang, String maNhanKhau, String hoTen, String noiTamVang, Date tuNgay,
                               Date denNgay, String lyDo, String tinhTrang) {
        this.maTamVang = maTamVang;
        this.maNhanKhau = maNhanKhau;
        this.hoTen = hoTen;
        this.noiTamVang = noiTamVang;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
        this.tinhTrang = tinhTrang;
    }

    public int getMaTamVang() {
        return maTamVang;
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

    public String getNoiTamVang() {
        return noiTamVang;
    }

    public void setNoiTamVang(String noiTamVang) {
        this.noiTamVang = noiTamVang;
    }

    public Date getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(Date tuNgay) {
        this.tuNgay = tuNgay;
    }

    public Date getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(Date denNgay) {
        this.denNgay = denNgay;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
