package com.se07.model.models;

import java.util.Date;

public class TamVangModel {
    private String maTamVang;
    private String maNhanKhau;
    private String noiThuongTru;
    private Date tuNgay;
    private Date denNgay;
    private String lyDo;
    private int idNguoiNhap;

    public TamVangModel(String maTamVang, String maNhanKhau, String noiThuongTru, Date tuNgay, Date denNgay, String lyDo,
                       int idNguoiNhap) {
        this.maTamVang = maTamVang;
        this.maNhanKhau = maNhanKhau;
        this.noiThuongTru = noiThuongTru;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
        this.idNguoiNhap = idNguoiNhap;
    }

    public String getMaTamVang() {
        return maTamVang;
    }

    public void setMaTamVang(String maTamVang) {
        this.maTamVang = maTamVang;
    }

    public String getMaNhanKhau() {
        return maNhanKhau;
    }

    public void setMaNhanKhau(String maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
    }

    public String getNoiThuongTru() {
        return noiThuongTru;
    }

    public void setNoiThuongTru(String noiThuongTru) {
        this.noiThuongTru = noiThuongTru;
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

    public int getIdNguoiNhap() {
        return idNguoiNhap;
    }

    public void setIdNguoiNhap(int idNguoiNhap) {
        this.idNguoiNhap = idNguoiNhap;
    }
}
