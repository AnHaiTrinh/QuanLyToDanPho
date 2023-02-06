package com.se07.controller.controllers.controllershogiadinh;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGiaiThuongThanhTichHoGiaDinhView extends ControllerHoGiaDinhView {
    @FXML
    TextField textFieldLocThongTinThanhTichHoGiaDinh;
    @FXML
    ComboBox comboBoxTimKiemThanhTichHoGiaDinh;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    public void onPressedButtonThemMoiThongTinThanhTichHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "ThemMoiThongTinThanhTichHoGiaDinh.fxml");
        }
    }

    public void onPressedButtonLocThongTinThanhTichHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonXoaThongTinThanhTichHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonThoatThongTinThanhTichHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Bạn chắc chắn muốn thoát?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(),
                        "GiaiThuongHoGiaDinhView.fxml");
            }
        }
    }

    public void locThongTinThanhTichHoGiaDinh() {
    }
}
