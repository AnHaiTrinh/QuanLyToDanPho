package com.se07.controller.controllers;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTaoBieuMauDipTraoThuongCanBoView extends ControllerCanBoView {
    @FXML
    GridPane gridPaneTaoBieuMauDipTraoThuongCanBo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        super.initialize(url, resourceBundle);
        gridPaneTaoBieuMauDipTraoThuongCanBo.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                xacNhanTaoBieuMauDipTraoThuongCanBo();
            } else if (keyEvent.getCode().equals(KeyCode.Q)) {
                try {
                    huyTaoBieuMauDipTraoThuongCanBo(keyEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void onPressedButtonXacNhanTaoBieuMauDipTraoThuongCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xacNhanTaoBieuMauDipTraoThuongCanBo();
        }
    }

    public void onPressedButtonHuyTaoBieuMauDipTraoThuongCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            huyTaoBieuMauDipTraoThuongCanBo(e);
        }
    }

    private void xacNhanTaoBieuMauDipTraoThuongCanBo() {
    }

    private void huyTaoBieuMauDipTraoThuongCanBo(Event e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "GiaiThuongCanBoView.fxml");
        }
    }
}
