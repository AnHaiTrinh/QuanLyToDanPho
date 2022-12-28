package com.se07.model.models;

import java.util.Date;

public class DinhChinhModel {
    private String maDinhChinh;
    private String thongtinThayDoi;
    private String thayDoiTu;
    private String thayDoiThanh;
    private int idNguoiThayDoi;
    private Date ngayThayDoi;

    public DinhChinhModel(String maDinhChinh, String thongtinThayDoi, String thayDoiTu, String thayDoiThanh,
                          int idNguoiThayDoi, Date ngayThayDoi) {
        this.maDinhChinh = maDinhChinh;
        this.thongtinThayDoi = thongtinThayDoi;
        this.thayDoiTu = thayDoiTu;
        this.thayDoiThanh = thayDoiThanh;
        this.idNguoiThayDoi = idNguoiThayDoi;
        this.ngayThayDoi = ngayThayDoi;
    }

    public String getMaDinhChinh() {
        return maDinhChinh;
    }

    public void setMaDinhChinh(String maDinhChinh) {
        this.maDinhChinh = maDinhChinh;
    }

    public String getThongtinThayDoi() {
        return thongtinThayDoi;
    }

    public void setThongtinThayDoi(String thongtinThayDoi) {
        this.thongtinThayDoi = thongtinThayDoi;
    }

    public String getThayDoiTu() {
        return thayDoiTu;
    }

    public void setThayDoiTu(String thayDoiTu) {
        this.thayDoiTu = thayDoiTu;
    }

    public String getThayDoiThanh() {
        return thayDoiThanh;
    }

    public void setThayDoiThanh(String thayDoiThanh) {
        this.thayDoiThanh = thayDoiThanh;
    }

    public int getIdNguoiThayDoi() {
        return idNguoiThayDoi;
    }

    public void setIdNguoiThayDoi(int idNguoiThayDoi) {
        this.idNguoiThayDoi = idNguoiThayDoi;
    }

    public Date getNgayThayDoi() {
        return ngayThayDoi;
    }

    public void setNgayThayDoi(Date ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }
}
