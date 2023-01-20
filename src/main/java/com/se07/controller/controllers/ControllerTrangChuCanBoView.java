package com.se07.controller.controllers;

import com.se07.controller.services.NhanKhauService;
import com.se07.controller.services.TamTruService;
import com.se07.controller.services.TamVangService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTrangChuCanBoView extends ControllerCanBoView {
    @FXML
    Label lableTenNguoiDangNhap, lableSoNhanKhau, lableSoHoKhau, lableNhanKhauTamTru, lableNhanKhauTamVang;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        lableTenNguoiDangNhap.setText("Xin chào, " + username);
        lableSoNhanKhau.setText(String.valueOf(new NhanKhauService().getNhanKhauCount()));
        lableSoHoKhau.setText(String.valueOf(new NhanKhauService().getNhanKhauCount()));
        lableNhanKhauTamTru.setText(String.valueOf(new TamTruService().getTamTruCount()));
        lableNhanKhauTamVang.setText(String.valueOf(new TamVangService().getTamVangCount()));
    }
}
