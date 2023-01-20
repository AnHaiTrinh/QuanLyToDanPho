package com.se07.model.models;

import java.util.Date;

public class TamVangModel {
    private int maTamVang;
    private String maNhanKhau;
    private String noiTamVang;
    private Date tuNgay;
    private Date denNgay;
    private String lyDo;
    private String tinhTrang;
    private int idNguoiThucHien;

    public TamVangModel(String maNhanKhau, String noiTamVang, Date tuNgay, Date denNgay, String lyDo, String tinhTrang,
                        int idNguoiThucHien) {
        this.maNhanKhau = maNhanKhau;
        this.noiTamVang = noiTamVang;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
        this.tinhTrang = tinhTrang;
        this.idNguoiThucHien = idNguoiThucHien;
    }

    public TamVangModel(int maTamVang, String maNhanKhau, String noiTamVang, Date tuNgay,
                        Date denNgay, String lyDo, String tinhTrang, int idNguoiThucHien) {
        this.maTamVang = maTamVang;
        this.maNhanKhau = maNhanKhau;
        this.noiTamVang = noiTamVang;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
        this.tinhTrang = tinhTrang;
        this.idNguoiThucHien = idNguoiThucHien;
    }

    public int getMaTamVang() {
        return maTamVang;
    }

    public void setMaTamVang(int maTamVang) {
        this.maTamVang = maTamVang;
    }

    public String getMaNhanKhau() {
        return maNhanKhau;
    }

    public void setMaNhanKhau(String maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
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

    public int getIdNguoiThucHien() {
        return idNguoiThucHien;
    }

    public void setIdNguoiThucHien(int idNguoiThucHien) {
        this.idNguoiThucHien = idNguoiThucHien;
    }
}