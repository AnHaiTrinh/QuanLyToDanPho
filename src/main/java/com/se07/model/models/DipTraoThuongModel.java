package com.se07.model.models;

import java.util.Date;

public class DipTraoThuongModel {
    private int id;
    private String tenDip;
    private int nam;
    private Date ngayTao;
    private Date ngayKetThuc;
    private String kieu;
    private String ghiChu;

    public DipTraoThuongModel(int id, String tenDip, int nam, Date ngayTao, Date ngayKetThuc, String kieu, String ghiChu) {
        this.id = id;
        this.tenDip = tenDip;
        this.nam = nam;
        this.ngayTao = ngayTao;
        this.ngayKetThuc = ngayKetThuc;
        this.kieu = kieu;
        this.ghiChu = ghiChu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDip() {
        return tenDip;
    }

    public void setTenDip(String tenDip) {
        this.tenDip = tenDip;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getKieu() {
        return kieu;
    }

    public void setKieu(String kieu) {
        this.kieu = kieu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
