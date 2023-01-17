package com.se07.controller.controllers;

import com.se07.controller.services.HoKhauService;
import com.se07.model.models.HoKhauModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerThemMoiHoKhauCanBoView extends ControllerCanBoView {
    LocalDate today = LocalDate.now();
    @FXML
    TextField textFieldMaChuHoThemMoiHoKhauCanBo, textFieldMaHoKhauThemMoiHoKhauCanBo, textFieldDiaChiThemMoiHoKhauCanBo;
    @FXML
    DatePicker datePickerNgayThanhLapThemMoiHoKhauCanBo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        anchorPaneChinhCanBo.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                dangKyHoKhauCanBo();
            } else if (keyEvent.getCode() == KeyCode.Q) {
                try {
                    huyDangKyHoKhauCanBo(keyEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        datePickerNgayThanhLapThemMoiHoKhauCanBo.setValue(today);
    }

    public void onPressedButtonHuyDangKyHoKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            huyDangKyHoKhauCanBo(e);
        }
    }

    public void onPressedButtonDangKyHoKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            dangKyHoKhauCanBo();
        }
    }

    public void dangKyHoKhauCanBo() {
        HoKhauService hoKhauService = new HoKhauService();
        if (textFieldMaHoKhauThemMoiHoKhauCanBo.getText().isBlank() ||
                textFieldDiaChiThemMoiHoKhauCanBo.getText().isBlank() ||
                datePickerNgayThanhLapThemMoiHoKhauCanBo.getValue() == null) {
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
        HoKhauModel hoKhauModel = new HoKhauModel(maHoKhau, maChuHo, diaChi, ngayLap, id);

        if (hoKhauService.getHoKhauByMaHoKhau(maHoKhau).isPresent()) {
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

    public void huyDangKyHoKhauCanBo(Event e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "HoKhauCanBoView.fxml");
        }
    }
}
