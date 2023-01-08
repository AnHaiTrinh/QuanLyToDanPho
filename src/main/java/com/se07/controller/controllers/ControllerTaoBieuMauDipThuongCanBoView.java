package com.se07.controller.controllers;

import com.se07.view.LoginView;
import com.se07.view.Main;
import com.se07.view.TaoBieuMauDipThuongCanBoView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTaoBieuMauDipThuongCanBoView implements Initializable {
    ControllerTrangChuView  controllerTrangChuView = new ControllerTrangChuView();
    public void setButtonLogoutAdmin(){

    }
    public void setButtonHomeAdmin(ActionEvent e) throws IOException {
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setButtonXacNhanTaoMauDipThuongCanBo(ActionEvent e) throws IOException {
        controllerTrangChuView.setButtonGiaiThuongCanBo(e);
    }
    public void setButtonHuyTaoMauDipThuongCanBo(ActionEvent e) throws  IOException{
        controllerTrangChuView.setButtonGiaiThuongCanBo(e);
    }

}
