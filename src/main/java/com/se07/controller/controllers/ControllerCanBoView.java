package com.se07.controller.controllers;

import com.se07.util.SceneLoader;
import com.se07.util.UserInfo;
import com.se07.view.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class ControllerCanBoView implements Initializable {

    @FXML
    Button buttonDangXuatCanBo;
    @FXML
    AnchorPane anchorPaneChinhCanBo;

    final int id = UserInfo.getUserId();
    final String username = UserInfo.getUsername();

    final String tinhTrang = "Đã xác nhận";
    final SceneLoader sceneLoader = new SceneLoader();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPaneChinhCanBo.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                Stage stage = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo!");
                alert.setHeaderText("Bạn muốn đăng xuất ?");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    stage.close();
                    LoginView loginView = new LoginView();
                    loginView.openWindow();
                }
            }
        });
    }

    public void onPressedButtonTrangChuCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "TrangChuCanBoView.fxml");

        }
    }

    public void onPressedButtonNhanKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "NhanKhauCanBoView.fxml");
        }
    }

    public void onPressedButtonHoKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "HoKhauCanBoView.fxml");
        }
    }


    public void onPressedButtonGiaiThuongCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "GiaiThuongCanBoView.fxml");
        }
    }

    public void onPressedButtonDangXuatCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            dangXuatCanBoView((Stage) ((Node) e.getSource()).getScene().getWindow());
        }
    }

    private void dangXuatCanBoView(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo!");
        alert.setHeaderText("Bạn muốn đăng xuất ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
            LoginView loginView = new LoginView();
            loginView.openWindow();
        }
    }
}
