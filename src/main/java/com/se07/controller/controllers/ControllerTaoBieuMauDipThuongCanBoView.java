package com.se07.controller.controllers;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerTaoBieuMauDipThuongCanBoView implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


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



    public void setButtonXacNhanTaoMauDipThuongCanBo(ActionEvent e) throws IOException {
        controllerTrangChuView.setButtonGiaiThuongCanBo(e);
    }
    public void setButtonHuyTaoMauDipThuongCanBo(ActionEvent e) throws  IOException{
        controllerTrangChuView.setButtonGiaiThuongCanBo(e);
    }

}
