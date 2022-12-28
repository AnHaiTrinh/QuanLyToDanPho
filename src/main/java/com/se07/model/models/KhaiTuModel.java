package com.se07.model.models;

import java.util.Date;

public class KhaiTuModel {
    private String soGiayKhaiTu;
    private String maNguoiKhai;
    private String getMaNguoiChet;
    private Date ngayKhai;
    private Date ngayMat;
    private String lyDo;

    public KhaiTuModel(String soGiayKhaiTu, String maNguoiKhai, String getMaNguoiChet, Date ngayKhai, Date ngayMat, String lyDo) {
        this.soGiayKhaiTu = soGiayKhaiTu;
        this.maNguoiKhai = maNguoiKhai;
        this.getMaNguoiChet = getMaNguoiChet;
        this.ngayKhai = ngayKhai;
        this.ngayMat = ngayMat;
        this.lyDo = lyDo;
    }

    public String getSoGiayKhaiTu() {
        return soGiayKhaiTu;
    }

    public void setSoGiayKhaiTu(String soGiayKhaiTu) {
        this.soGiayKhaiTu = soGiayKhaiTu;
    }

    public String getMaNguoiKhai() {
        return maNguoiKhai;
    }

    public void setMaNguoiKhai(String maNguoiKhai) {
        this.maNguoiKhai = maNguoiKhai;
    }

    public String getGetMaNguoiChet() {
        return getMaNguoiChet;
    }

    public void setGetMaNguoiChet(String getMaNguoiChet) {
        this.getMaNguoiChet = getMaNguoiChet;
    }

    public Date getNgayKhai() {
        return ngayKhai;
    }

    public void setNgayKhai(Date ngayKhai) {
        this.ngayKhai = ngayKhai;
    }

    public Date getNgayMat() {
        return ngayMat;
    }

    public void setNgayMat(Date ngayMat) {
        this.ngayMat = ngayMat;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }
}
