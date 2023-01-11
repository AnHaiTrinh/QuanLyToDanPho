package com.se07.controller.controllers;

import com.se07.controller.services.HoKhauService;
import com.se07.model.models.HoKhauModel;
import com.se07.util.UserInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerThemHoKhauMoiCanBoView extends ControllerTrangChuView implements Initializable {
    LocalDate today = LocalDate.now();

    final int idNguoiThucHien = UserInfo.getUserId();
    @FXML
    TextField textFieldMaChuHoThemMoiHoKhauCanBo, textFieldMaHoKhauThemMoiHoKhauCanBo, textFieldDiaChiThemMoiHoKhauCanBo;
    @FXML
    DatePicker datePickerNgayThanhLapThemMoiHoKhauCanBo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePickerNgayThanhLapThemMoiHoKhauCanBo.setValue(today);
    }

    public void setButtonCancelNewUserAdmin(ActionEvent e) throws IOException {
        super.setButtonUserAdmin(e);
    }

    public void setButtonAcceptNewUserAdmin() {
        HoKhauService hoKhauService = new HoKhauService();
        if (textFieldMaHoKhauThemMoiHoKhauCanBo.getText().isBlank() ||
                textFieldDiaChiThemMoiHoKhauCanBo.getText().isBlank() || datePickerNgayThanhLapThemMoiHoKhauCanBo.getValue().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        String maHoKhau = textFieldMaHoKhauThemMoiHoKhauCanBo.getText();
        String maChuHo = textFieldMaChuHoThemMoiHoKhauCanBo.getText();
        Date ngayLap = new Date(datePickerNgayThanhLapThemMoiHoKhauCanBo.getValue().toEpochDay());
        String diaChi = textFieldDiaChiThemMoiHoKhauCanBo.getText();
        HoKhauModel hoKhauModel = new HoKhauModel(maHoKhau, maChuHo, diaChi, ngayLap, idNguoiThucHien);

        if (!hoKhauService.getHoKhauByMaHoKhau(maHoKhau).isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Mã hộ khẩu đã tồn tại");
            alert.showAndWait();
        } else {
            if (hoKhauService.addHoKhau(hoKhauModel)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("");
                alert.setContentText("Bạn đã thêm thành công");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    textFieldMaHoKhauThemMoiHoKhauCanBo.setText("");
                    textFieldDiaChiThemMoiHoKhauCanBo.setText("");
                    textFieldMaChuHoThemMoiHoKhauCanBo.setText("");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Thông báo");
                alert.setHeaderText("");
                alert.setContentText("Thêm thất bại");
                alert.showAndWait();
            }
        }
    }
}
