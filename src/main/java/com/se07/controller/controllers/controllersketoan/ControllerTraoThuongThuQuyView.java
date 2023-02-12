package com.se07.controller.controllers.controllersketoan;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTraoThuongThuQuyView extends ControllerThuQuyView {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    public void onPressedButtonXacNhanChonQuaDipDacBietThuQuy(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {

        }
    }

    public void onPressedButtonThemLoaiPhanThuongDipDacBietThuQuy(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileThuQuy((Stage) ((Node) mouseEvent.getSource()).getScene().getWindow(),
                    "ThemLoaiPhanThuongThuQuyView.fxml");
        }
    }

    public void onPressedButtonHuyChonQuaDipDacBietThuQuy(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileThuQuy((Stage) ((Node) mouseEvent.getSource()).getScene().getWindow(),
                    "GiaiThuongThuQuyView.fxml");
        }
    }
}
