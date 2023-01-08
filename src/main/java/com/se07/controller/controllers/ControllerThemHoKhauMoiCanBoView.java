package com.se07.controller.controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ControllerThemHoKhauMoiCanBoView {
    ControllerTrangChuView controllerTrangChuView = new ControllerTrangChuView();
    public void setButtonCancelNewUserAdmin(){

    }
    public void setButtonAcceptNewUserAdmin(){

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
    public void setButtonLogoutAdmin(ActionEvent e) throws IOException{
        controllerTrangChuView.setButtonLogoutAdmin(e);
    }
}
