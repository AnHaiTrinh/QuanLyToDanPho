package com.se07.model.models;

import java.util.Date;

public class TamTruModel {
    private int maTamTru;
    private String maHoKhau;
    private String CCCD;
    private String hoTen;
    private Date tuNgay;
    private Date denNgay;
    private String lyDo;
    private String tinhTrang;
    private int idNguoiThucHien;

    public TamTruModel(String maHoKhau, String CCCD, String hoTen, Date tuNgay, Date denNgay, String lyDo,
                       String tinhTrang, int idNguoiThucHien) {
        this.maHoKhau = maHoKhau;
        this.CCCD = CCCD;
        this.hoTen = hoTen;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
        this.tinhTrang = tinhTrang;
        this.idNguoiThucHien = idNguoiThucHien;
    }

    public TamTruModel(int maTamTru, String maHoKhau, String CCCD, String hoTen, Date tuNgay, Date denNgay,
                       String lyDo, String tinhTrang, int idNguoiThucHien) {
        this.maTamTru = maTamTru;
        this.maHoKhau = maHoKhau;
        this.CCCD = CCCD;
        this.hoTen = hoTen;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
        this.tinhTrang = tinhTrang;
        this.idNguoiThucHien = idNguoiThucHien;
    }

    public int getMaTamTru() {
        return maTamTru;
    }

    public void setMaTamTru(int maTamTru) {
        this.maTamTru = maTamTru;
    }

    public String getMaHoKhau() {
        return maHoKhau;
    }

    public void setMaHoKhau(String maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
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
