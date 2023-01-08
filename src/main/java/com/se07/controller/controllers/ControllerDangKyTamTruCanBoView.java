package com.se07.controller.controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ControllerDangKyTamTruCanBoView {
    public  void setButtonXacNhanDangKyTamTruCanBo(){

    }
    public void setButtonKhongXacNhanDangKyTamTruCanBo(){

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
}
