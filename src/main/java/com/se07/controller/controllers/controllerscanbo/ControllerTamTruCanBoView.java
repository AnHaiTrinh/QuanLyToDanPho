package com.se07.controller.controllers.controllerscanbo;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTamTruCanBoView extends ControllerCanBoView {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    public void onPressedButtonLocThongTinTamTruCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonThemMoiTamTruCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "DangKyTamTruCanBoView.fxml");
        }
    }

    public void onPressedkButtonXacNhanTamTruCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonTuChoiTamTruCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onDeletePressedTrongBangTamTruCanBo(KeyEvent e) {
        if (e.getCode() == KeyCode.DELETE) {

        }
    }

    public void onPressedButtonXoaTamTruCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonThoatTamTruCanBo(MouseEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "NhanKhauCanBoView.fxml");
        }
    }
}
