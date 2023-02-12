package com.se07.controller.controllers.controllersketoan;

import com.se07.controller.services.PhanThuongService;
import com.se07.model.models.PhanThuongModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerThemLoaiPhanThuongThuQuyView {
    @FXML
    TextField textFieldTenGiaiThuong, textFieldDonGia;
    private final PhanThuongService phanThuongService = new PhanThuongService();

    public void onPressedButtonHuyThemMoiQuaThuQuy() {
        textFieldTenGiaiThuong.setText("");
        textFieldDonGia.setText("");
        textFieldTenGiaiThuong.requestFocus();
    }

    public void onPressedButtonXacNhanThemMoiQuaThuQuy() {
        String tenGiaiThuong = textFieldTenGiaiThuong.getText();
        String donGia = textFieldDonGia.getText();
        if (tenGiaiThuong.isBlank() || donGia.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng điền đủ thông tin");
            alert.showAndWait();
        } else {
            if (phanThuongService.getPhanThuongByTen(tenGiaiThuong).isPresent()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Tên phần thưởng đã tồn tại. Vui lòng đặt tên khác");
                alert.setContentText("Gợi ý: Có thể thêm dịp vào cuối phần thưởng");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    textFieldTenGiaiThuong.requestFocus();
                }
            } else {
                try {
                    PhanThuongModel phanThuongModel = new PhanThuongModel(tenGiaiThuong, Integer.valueOf(donGia));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Thông báo");
                    if (phanThuongService.addPhanThuong(phanThuongModel)) {
                        alert.setHeaderText("Thêm phần thưởng thành công");
                    } else {
                        alert.setHeaderText("Thêm phần thưởng thất bại");
                    }
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        textFieldTenGiaiThuong.setText("");
                        textFieldDonGia.setText("");
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập vào đơn giá hợp lệ");
                    alert.showAndWait();
                    textFieldDonGia.requestFocus();
                }
            }
        }
    }
}
