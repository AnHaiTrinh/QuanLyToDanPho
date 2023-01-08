package com.se07.controller.controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ControllerTachHoKhauCanBoView {
    ControllerTrangChuView controllerTrangChuView = new ControllerTrangChuView();
    public void setButtonChapNhanTachHoKhauCanBo(ActionEvent e) throws IOException {
        controllerTrangChuView.setButtonUserAdmin(e);
    }
    public void setButtonKhongChapNhanTachHoKhauCanBo(){

    }


}
