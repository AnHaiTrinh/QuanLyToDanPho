package com.se07.controller.controllers.controllersketoan;

import com.se07.controller.services.TraoThuongService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class ControllerTrangChuThuQuyView extends ControllerThuQuyView {
    @FXML
    Label lableTenNguoiDangNhapThuQuy, labelSoNhanKhauTraoThuong, labelSoPhanThuongDaTrao, labelTongGiaTriPhanThuong;
    final private TraoThuongService traoThuongService = new TraoThuongService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        lableTenNguoiDangNhapThuQuy.setText("Xin chào, " + username);
        labelSoNhanKhauTraoThuong.setText(String.valueOf(traoThuongService.getSoNhanKhauTraoThuong()));
        labelSoPhanThuongDaTrao.setText(String.valueOf(
                traoThuongService.getSoPhanThuongThanhTich() +
                        traoThuongService.getSoPhanThuongDipDacBiet()));
        labelTongGiaTriPhanThuong.setText(NumberFormat.getInstance().format(
                traoThuongService.getGiaTriTraoThuongThanhTich() +
                        traoThuongService.getGiaTriTraoThuongDipDacBiet()));
    }
}
