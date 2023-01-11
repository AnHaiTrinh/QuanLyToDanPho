package com.se07.controller.controllers;

import com.se07.controller.services.NhanKhauService;
import com.se07.controller.services.TamTruService;
import com.se07.controller.services.TamVangService;
import com.se07.util.UserInfo;
import com.se07.view.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class ControllerTrangChuView implements Initializable {

    @FXML
    Button buttonLogoutAdmin;
    @FXML
    AnchorPane anchorPaneChinhCanBo;
    @FXML
    Label lableTenNguoiDangNhap, lableSoNhanKhau, lableSoHoKhau, lableNhanKhauTamTru, lableNhanKhauTamVang;
    final int id = UserInfo.getUserId();

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
        if (this.getClass() == ControllerTrangChuView.class) {
            lableTenNguoiDangNhap.setText("Xin chào, " + UserInfo.getUsername());
            lableSoNhanKhau.setText(String.valueOf(new NhanKhauService().getNhanKhauCount()));
            lableSoHoKhau.setText(String.valueOf(new NhanKhauService().getNhanKhauCount()));
            lableNhanKhauTamTru.setText(String.valueOf(new TamTruService().getTamTruCount()));
            lableNhanKhauTamVang.setText(String.valueOf(new TamVangService().getTamVangCount()));
        }
    }


    public void setButtonHomeAdmin(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("TrangChuCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setButtonPeopleAdmin(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("NhanKhauCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setButtonLogoutAdmin(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo!");
        alert.setHeaderText("Bạn muốn đăng xuất ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
            LoginView loginView = new LoginView();
            loginView.openWindow();
        }
    }

    public void setButtonUserAdmin(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("HoKhauCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        ControllerHoKhauCanBoView hoKhauCanBoViewController = fxmlLoader.getController();
        hoKhauCanBoViewController.displayAllHoKhauCanBo();
    }

    public void setButtonGiaiThuongCanBo(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("GiaiThuongDipDacBietCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setMenuItemCreateAwardAdmin(ActionEvent e) throws IOException {

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("TaoBieuMauDipThuongCanBoView.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load());
        stage.setScene(scene1);
    }

    public void setButtonNewPeopleAdmin() {

    }

    public void setButtonTemporaryRegistrationPeopleAdmin() {
    }

    public void setButtonAbsentRegistrationAdmin() {
    }

    public void setButtonRemovePeopleAdmin() {
    }

    public void setNhanNutHoKhauCanBo() {

    }

    public void setNhanNutDeleteTrongBangHoKhauCanBo() {

    }

    public void setButtonLocThongTinHoKhauCanBo() {
    }

    public void setButtonNewUserAdmin() {
    }

    public void setButtonSplitUserAdmin() {
    }

    public void setButtonRemoveUserAdmin() {
    }

}
