package com.se07.controller.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOError;
import java.io.IOException;

public class ControllerTaoBieuMauDipDacBietCanBoView extends ControllerTrangChuView{
    ControllerTrangChuView controllerTrangChuView = new ControllerTrangChuView();
    public void setButtonHuyTaoMauDacBietCanBo(ActionEvent e) throws IOException {
        controllerTrangChuView.setButtonGiaiThuongCanBo(e);
    }
    public void setButtonXacNhanTaoMauDacBietCanBo(ActionEvent e) throws IOException{
        controllerTrangChuView.setButtonGiaiThuongCanBo(e);
    }
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
}
