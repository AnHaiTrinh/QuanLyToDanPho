package com.se07.controller.controllers;

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

public class ControllerGiaiThuongDipDacBietCanBoView extends ControllerCanBoView {
    @FXML
    ComboBox comboBoxTimKiemDipDacBietCanBo;
    @FXML
    TextField textFieldLocThongTinDipDacBietCanBo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    public void onPressedButtonThemMoiThongTinDipDacBietCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "ThemMoiThongTinDipDacBietCanBo.fxml");
        }
    }

    public void onPressedButtonLocThongTinDipDacBietCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonPheDuyetThongTinDipDacBietCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonTuChoiThongTinDipDacBietCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonXoaThongTinDipDacBietCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonThoatThongTinDipDacBietCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Bạn chắc chắn muốn thoát?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                        "GiaiThuongCanBoView.fxml");
            }
        }
    }

    public void locThongTinDipDacBietCanBo() {

    }
}
