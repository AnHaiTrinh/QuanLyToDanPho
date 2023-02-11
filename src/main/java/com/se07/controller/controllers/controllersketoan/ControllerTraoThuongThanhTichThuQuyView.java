package com.se07.controller.controllers.controllersketoan;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTraoThuongThanhTichThuQuyView extends ControllerThuQuyView implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onPressedButtonHuyChonQuaThanhTichThuQuy(){

    }
    public void onPressedButtonXacNhanChonQuaThanhTichThuQuy(){

    }
    public void onPressedButtonThemLoaiPhanThuongThanhTichThuQuy(MouseEvent e) throws IOException {
        if(e.isPrimaryButtonDown()){
            sceneLoader.loadFxmlFileThuQuy((Stage) ((Node) e.getSource()).getScene().getWindow(), "ThemLoaiPhanThuongThuQuyView.fxml");
        }
    }
}
