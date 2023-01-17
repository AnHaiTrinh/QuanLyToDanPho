package com.se07.controller.controllers;

import com.se07.view.TrangChuCanBoView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.ResourceBundle;

public class ControllerTamVangCanBoView extends ControllerCanBoView {
    @FXML
    Button buttonLocThongTinHoKhauCanBo, buttonThemMoiTamVangCanBo, buttonXacNhanTamVangCanBo, buttonXoaTamVangCanBo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    public void onPressedButtonLocThongTinTamVangCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonThemMoiTamVangCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            loadThemMoiTamVangCanBo((Stage) ((Node) e.getSource()).getScene().getWindow());
        }
    }

    public void onPressedkButtonXacNhanTamVangCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onDeletePressedTrongBangTamVangCanBo(KeyEvent e) {
        if (e.getCode() == KeyCode.DELETE) {

        }
    }

    public void onPressedButtonTuChoiTamVangCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonXoaTamVangCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonThoatTamVangCanBo(MouseEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            loadNhanKhauCanBoView((Stage) ((Node) e.getSource()).getScene().getWindow());
        }
    }

    public void loadThemMoiTamVangCanBo(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("DangKyTamVangCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
