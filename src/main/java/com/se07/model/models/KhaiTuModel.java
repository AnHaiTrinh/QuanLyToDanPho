package com.se07.model.models;

import java.util.Date;

public class KhaiTuModel {
    private String soGiayKhaiTu;
    private String tenNguoiKhai;
    private String tenNguoiMat;
    private Date ngayKhai;
    private Date ngayMat;
    private String lyDo;
    private String tinhTrang;
    private int idNguoiThucHien;

    public KhaiTuModel(String soGiayKhaiTu, String tenNguoiKhai, String tenNguoiMat, Date ngayKhai, Date ngayMat,
                       String lyDo, String tinhTrang, int idNguoiThucHien) {
        this.soGiayKhaiTu = soGiayKhaiTu;
        this.tenNguoiKhai = tenNguoiKhai;
        this.tenNguoiMat = tenNguoiMat;
        this.ngayKhai = ngayKhai;
        this.ngayMat = ngayMat;
        this.lyDo = lyDo;
        this.tinhTrang = tinhTrang;
        this.idNguoiThucHien = idNguoiThucHien;
    }

    public String getSoGiayKhaiTu() {
        return soGiayKhaiTu;
    }

    public void setSoGiayKhaiTu(String soGiayKhaiTu) {
        this.soGiayKhaiTu = soGiayKhaiTu;
    }

    public String getTenNguoiKhai() {
        return tenNguoiKhai;
    }

    public void setTenNguoiKhai(String tenNguoiKhai) {
        this.tenNguoiKhai = tenNguoiKhai;
    }

    public String getTenNguoiMat() {
        return tenNguoiMat;
    }

    public void setTenNguoiMat(String tenNguoiMat) {
        this.tenNguoiMat = tenNguoiMat;
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
