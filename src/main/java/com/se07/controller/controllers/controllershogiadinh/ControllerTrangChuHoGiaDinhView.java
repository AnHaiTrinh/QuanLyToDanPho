package com.se07.controller.controllers.controllershogiadinh;

import com.se07.controller.services.NhanKhauService;
import com.se07.controller.services.TamTruService;
import com.se07.controller.services.TamVangService;
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
        TamVangService tamVangService = new TamVangService();
        TamTruService tamTruService = new TamTruService();
        int soNhanKhau = nhanKhauService.countNhanKhauByMaHoKhau(maHoKhauDangNhap);
        int soNhanKhauTamVang = tamVangService.countTamVangByMaHoKhau(maHoKhauDangNhap);
        int soNhanKhauTamTru = tamTruService.countTamTruByMaHoKhau(maHoKhauDangNhap);
        lableNhanKhauTamTruHoGiaDinh.setText(String.valueOf(soNhanKhauTamTru));
        lableSoNhanKhauHoGiaDinh.setText(String.valueOf(soNhanKhau));
        lableNhanKhauTamVangHoGiaDinh.setText(String.valueOf(soNhanKhauTamVang));
    }
}
