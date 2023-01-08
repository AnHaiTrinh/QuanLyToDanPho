package com.se07.controller.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerThemMoiNhanKhauCanBoView implements Initializable {
    @FXML
    AnchorPane paneNewPeopleAdmin;
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
    public void setButtonAcceptNewPeopleAdmin(ActionEvent e) throws  IOException{
        controllerTrangChuView.setButtonPeopleAdmin(e);
    }
    public void setButtonCancelNewPeopleAdmin(ActionEvent e) throws IOException{
        controllerTrangChuView.setButtonPeopleAdmin(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paneNewPeopleAdmin.setVisible(true);
    }
}
