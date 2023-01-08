package com.se07.controller.controllers;

import com.se07.view.TaoBieuMauDipThuongCanBoView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerGiaiThuongCanBoView {
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
    public void setButtonTaoBieuMauDipDacBietCanBoView(ActionEvent e) throws  IOException{
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TaoBieuMauDipThuongCanBoView.class.getResource("TaoBieuMauDipDacBietCanBoView.fxml"));
        Scene scene =  new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void setButtonTaoBieuMauDipBTCanBo(ActionEvent e) throws IOException{
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TaoBieuMauDipThuongCanBoView.class.getResource("TaoBieuMauDipThuongCanBoView.fxml"));
        Scene scene =  new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void setButtonLocThongTinHoKhauCanBo(){

    }

}
