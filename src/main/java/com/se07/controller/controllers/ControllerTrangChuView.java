package com.se07.controller.controllers;

import com.se07.model.models.HoKhauModel;
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
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class ControllerTrangChuView implements Initializable{
    private ControllerHoKhauCanBoView hoKhauCanBoViewController;
    @FXML
    Button buttonLogoutAdmin;
    @FXML
    AnchorPane anchorPaneChinhCanBo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPaneChinhCanBo.setOnKeyPressed(keyEvent -> {
            if(KeyEvent.KEY_PRESSED.equals(KeyCode.ESCAPE)){
                System.out.println(1);
            }
        });
    }

    public void setButtonHomeAdmin(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TaoBieuMauDipThuongCanBoView.class.getResource("TrangChuCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setButtonPeopleAdmin(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TaoBieuMauDipThuongCanBoView.class.getResource("NhanKhauCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);

    }

    public void setButtonLogoutAdmin(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo!");
        alert.setHeaderText("Bạn muốn đăng xuất ?");
        if(alert.showAndWait().get()==ButtonType.OK){
            stage.close();
            LoginView loginView = new LoginView();
            loginView.openWindow();
        }
    }

    public void setButtonUserAdmin(ActionEvent e) throws IOException {
        System.out.println(1);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TaoBieuMauDipThuongCanBoView.class.getResource("HoKhauCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        hoKhauCanBoViewController = fxmlLoader.getController();
        hoKhauCanBoViewController.setButtonUserAdmin(e);
    }

    public void setButtonGiaiThuongCanBo(ActionEvent e) throws IOException {
        System.out.println(1);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TaoBieuMauDipThuongCanBoView.class.getResource("GiaiThuongDipDacBietCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void setMenuItemCreateAwardAdmin(ActionEvent e) throws  IOException{

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TaoBieuMauDipThuongCanBoView.class.getResource("TaoBieuMauDipThuongCanBoView.fxml"));
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
