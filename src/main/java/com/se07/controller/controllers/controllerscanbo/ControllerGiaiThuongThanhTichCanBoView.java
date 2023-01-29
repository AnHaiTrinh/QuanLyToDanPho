package com.se07.controller.controllers.controllerscanbo;

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

public class ControllerGiaiThuongThanhTichCanBoView extends ControllerCanBoView {
    @FXML
    TextField textFieldLocThongTinThanhTichCanBo;
    @FXML
    ComboBox comboBoxTimKiemThanhTichCanBo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    public void onPressedButtonThemMoiThongTinThanhTichCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "ThemMoiThongTinThanhTichCanBo.fxml");
        }
    }

    public void onPressedButtonLocThongTinThanhTichCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonPheDuyetThongTinThanhTichCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonTuChoiThongTinThanhTichCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonXoaThongTinThanhTichCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonThoatThongTinThanhTichCanBo(MouseEvent e) throws IOException {
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

    public void locThongTinThanhTichCanBo() {
    }
}
