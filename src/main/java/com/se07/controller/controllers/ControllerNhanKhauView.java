package com.se07.controller.controllers;

import com.se07.view.TaoBieuMauDipThuongCanBoView;
import com.se07.view.TrangChuCanBoView;
import com.se07.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerNhanKhauView implements Initializable {
    @FXML
    AnchorPane anchorPaneChinhCanBo;
    ControllerTrangChuView controllerTrangChuView  =  new ControllerTrangChuView();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void setButtonLogoutAdmin(){
    }
    public void setButtonNewPeopleAdmin(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TaoBieuMauDipThuongCanBoView.class.getResource("ThemMoiNhanKhauCanBoView.fxml"));
        Scene scene =  new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void setButtonTemporaryRegistrationPeopleAdmin(ActionEvent e) throws IOException{
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TaoBieuMauDipThuongCanBoView.class.getResource("DangKyTamVangCanBoView.fxml"));
        Scene scene =  new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void setButtonAbsentRegistrationAdmin(ActionEvent e) throws IOException{
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TaoBieuMauDipThuongCanBoView.class.getResource("DangKyTamTruCanBoView.fxml"));
        Scene scene =  new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void setButtonRemovePeopleAdmin(){

    }
    public void setButtonHomeAdmin(ActionEvent e) throws IOException{
        controllerTrangChuView.setButtonHomeAdmin(e);
    }
    public void setButtonPeopleAdmin(ActionEvent e) throws IOException{
        controllerTrangChuView.setButtonPeopleAdmin(e);
    }
    public void setButtonUserAdmin(ActionEvent e) throws IOException{
        controllerTrangChuView.setButtonUserAdmin(e);
    }
    public void setButtonGiaiThuongCanBo(ActionEvent e) throws IOException{
        controllerTrangChuView.setButtonGiaiThuongCanBo(e);
    }
}
