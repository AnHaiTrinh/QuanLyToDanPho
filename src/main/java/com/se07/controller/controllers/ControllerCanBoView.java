package com.se07.controller.controllers;

import com.se07.util.UserInfo;
import com.se07.view.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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
            loadTrangChuCanBoView((Stage) ((Node) e.getSource()).getScene().getWindow());

        }
    }

    public void onPressedButtonNhanKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            loadNhanKhauCanBoView((Stage) ((Node) e.getSource()).getScene().getWindow());
        }
    }

    public void onPressedButtonHoKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            loadHoKhauCanBoView((Stage) ((Node) e.getSource()).getScene().getWindow());
        }
    }


    public void onPressedButtonGiaiThuongCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            loadGiaiThuongCanBoView((Stage) ((Node) e.getSource()).getScene().getWindow());
        }
    }

    public void onPressedButtonDangXuatCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            dangXuatCanBoView((Stage) ((Node) e.getSource()).getScene().getWindow());
        }
    }

    public void loadTrangChuCanBoView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("TrangChuCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void loadNhanKhauCanBoView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("NhanKhauCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void loadHoKhauCanBoView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("HoKhauCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void loadGiaiThuongCanBoView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("GiaiThuongCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void dangXuatCanBoView(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo!");
        alert.setHeaderText("Bạn muốn đăng xuất ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
            LoginView loginView = new LoginView();
            loginView.openWindow();
        }
    }

//    public void setMenuItemCreateAwardAdmin(ActionEvent e) throws IOException {
//        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("TaoBieuMauDipThuongCanBoView.fxml"));
//        Scene scene1 = new Scene(fxmlLoader.load());
//        stage.setScene(scene1);
//    }

}
