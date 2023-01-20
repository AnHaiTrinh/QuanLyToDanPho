package com.se07.controller.controllers;

import com.se07.controller.services.HoKhauService;
import com.se07.util.SceneLoader;
import com.se07.util.UserInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHoGiaDinhView implements Initializable {
    @FXML
    AnchorPane  anchorPaneChinhHoGiaDinh;

    final int id = UserInfo.getUserId();

    final String username = UserInfo.getUsername();
    final SceneLoader sceneLoader = new SceneLoader();
    private  HoKhauService hoKhauService = new HoKhauService();
    final String maHoKhauDangNhap = hoKhauService.getMaHoKhauByIdChuHo(id);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onPressedButtonTrangChuHoGiaDinh(MouseEvent e) throws IOException{
        if(e.isPrimaryButtonDown()){
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "TrangChuHoGiaDinhView.fxml");
        }
        System.out.println(1);
    }
    public void onPressedButtonNhanKhauHoGiaDinh(MouseEvent e) throws IOException{
        if(e.isPrimaryButtonDown()){
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "NhanKhauHoGiaDinhView.fxml");
        }
        System.out.println(2);
    }
    public void onPressedButtonGiaiThuongHoGiaDinh(MouseEvent e) throws IOException{
        System.out.println(3);
    }
    public void onPressedButtonTraCuuThongTInPhanThuongHoGiaDinh(){
        System.out.println(4);
    }
    public void onPressedButtonNhapThongTinPhanThuongHoGiaDinh(){

    }
    public void onPressedButtonDangXuatHoGiaDinh(){

    }


}
