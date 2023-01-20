package com.se07.controller.controllers;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTamTruHoGiaDinhView extends ControllerHoGiaDinhView implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onPressedButtonThemMoiTamTruNhanKhauHoGiaDinh(MouseEvent e) throws IOException {
        if(e.isPrimaryButtonDown()){
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "DangKyTamTruHoGiaDinhView.fxml");
        }
    }
    public void onPressedButtonXoaTamTruNhanKhauHoGiaDinh(){

    }
    public void onPressedButtonThoatTamTruNhanKhauHoGiaDinh(){

    }
    public void onPressedButtonLocThongTinTamTruHoGiaDinh(){

    }
}
