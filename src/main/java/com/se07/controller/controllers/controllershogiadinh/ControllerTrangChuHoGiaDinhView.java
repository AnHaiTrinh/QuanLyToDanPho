package com.se07.controller.controllers.controllershogiadinh;

import com.se07.controller.services.NhanKhauService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTrangChuHoGiaDinhView extends ControllerHoGiaDinhView{
    @FXML
    Label lableSoNhanKhauHoGiaDinh, lableNhanKhauTamTruHoGiaDinh, lableNhanKhauTamVangHoGiaDinh, lableTenNguoiDangNhapHoGiaDinh;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        lableTenNguoiDangNhapHoGiaDinh.setText("Xin chào, " + username);
        NhanKhauService nhanKhauService = new NhanKhauService();
        //int soNhanKhauTrongGiaDinh = nhanKhauService.get
        lableNhanKhauTamTruHoGiaDinh.setText("1");
        lableSoNhanKhauHoGiaDinh.setText("2");
        lableNhanKhauTamVangHoGiaDinh.setText("3");
    }
}
