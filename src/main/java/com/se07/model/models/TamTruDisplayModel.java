package com.se07.model.models;

import java.util.Date;

public class TamTruDisplayModel {
    private int maTamTru;
    private String CCCD;
    private String hoTen;
    private String noiTamTru;
    private Date tuNgay;
    private Date denNgay;
    private String lyDo;
    private String tinhTrang;

    public TamTruDisplayModel(int maTamTru, String CCCD, String hoTen, String noiTamTru, Date tuNgay, Date denNgay,
                              String lyDo, String tinhTrang) {
        this.maTamTru = maTamTru;
        this.CCCD = CCCD;
        this.hoTen = hoTen;
        this.noiTamTru = noiTamTru;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
        this.tinhTrang = tinhTrang;
    }

    public int getMaTamTru() {
        return maTamTru;
    }

    public void setMaTamTru(int maTamTru) {
        this.maTamTru = maTamTru;
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

    public String getNoiTamTru() {
        return noiTamTru;
    }

    public void setNoiTamTru(String noiTamTru) {
        this.noiTamTru = noiTamTru;
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
