package com.se07.controller.controllers;

import com.se07.view.TaoBieuMauDipThuongCanBoView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerHoKhauCanBoView {
    ControllerTrangChuView controllerTrangChuView = new ControllerTrangChuView();
    public  void setButtonLogoutAdmin(ActionEvent e) throws IOException{
        controllerTrangChuView.setButtonLogoutAdmin(e);
    }
    public void setNhanNutHoKhauCanBo(){

    }
    public void setNhanNutDeleteTrongBangHoKhauCanBo(){

    }
    public void setButtonLocThongTinHoKhauCanBo(){

    }
    public void setButtonHomeAdmin(ActionEvent e) throws IOException {
        controllerTrangChuView.setButtonHomeAdmin(e);
    }
    public void setButtonPeopleAdmin(ActionEvent e) throws IOException{
        controllerTrangChuView.setButtonPeopleAdmin(e);
    }
    public void setButtonUserAdmin(){

    }
    public void setButtonGiaiThuongCanBo(ActionEvent e) throws IOException{
        controllerTrangChuView.setButtonGiaiThuongCanBo(e);
    }
    public void setButtonThemHoKhauCanBo(ActionEvent e) throws  IOException{
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TaoBieuMauDipThuongCanBoView.class.getResource("ThemHoKhauMoiCanBoView.fxml"));
        Scene scene =  new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void setButtonTachHoKhauCanBo(ActionEvent e) throws  IOException{
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TaoBieuMauDipThuongCanBoView.class.getResource("TachHoKhauCanBoView.fxml"));
        Scene scene =  new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void setButtonXoaHoKhauCanBo(){

    }

}
