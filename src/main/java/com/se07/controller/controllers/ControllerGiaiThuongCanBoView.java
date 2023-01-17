package com.se07.controller.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGiaiThuongCanBoView extends ControllerCanBoView {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    @Override
    public void onPressedButtonGiaiThuongCanBo(MouseEvent e) {
    }

    public void onPressedButtonTaoBieuMauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonGiaiThuongDipDacBietCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonGiaiThuongThanhTichCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonXoaDipTraoThuongCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonLocThongTinDipTraoThuongCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {

        }
    }

    public void onDeletePressedTrongBangDipTraoThuongCanBo(KeyEvent e) {
        if (e.getCode() == KeyCode.DELETE) {

        }
    }
}
