package com.se07.controller.controllers.controllershogiadinh;

import com.se07.controller.services.HoKhauService;
import com.se07.util.SceneLoader;
import com.se07.util.UserInfo;
import com.se07.view.LoginView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHoGiaDinhView implements Initializable {
    @FXML
    AnchorPane anchorPaneChinhHoGiaDinh;

    final int id = UserInfo.getUserId();
    final String tinhTrang = "Chờ xác nhận";

    final String username = UserInfo.getUsername();
    final SceneLoader sceneLoader = new SceneLoader();
    private HoKhauService hoKhauService = new HoKhauService();
    final String maHoKhauDangNhap = UserInfo.getMaHoKhau(id);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPaneChinhHoGiaDinh.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
                dangXuatHoGiaDinh((Stage) ((Node) keyEvent.getSource()).getScene().getWindow());
            }
        });
    }

    public void onPressedButtonTrangChuHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "TrangChuHoGiaDinhView.fxml");
        }
    }

    public void onPressedButtonNhanKhauHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "NhanKhauHoGiaDinhView.fxml");
        }
    }

    public void onPressedButtonGiaiThuongHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "GiaiThuongHoGiaDinhView.fxml");
        }
    }

    public void onPressedButtonDangXuatHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            dangXuatHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow());
        }
    }

    private void dangXuatHoGiaDinh(Stage stage) {
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