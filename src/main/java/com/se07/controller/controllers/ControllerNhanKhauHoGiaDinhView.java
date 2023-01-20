package com.se07.controller.controllers;

import com.se07.controller.services.NhanKhauService;
import com.se07.model.models.NhanKhauModel;
import com.se07.util.SceneLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerNhanKhauHoGiaDinhView extends ControllerHoGiaDinhView implements Initializable {
    @FXML
    TableView<NhanKhauModel> tableViewNhanKhauHoGiaDinh;
    @FXML
    TableColumn<NhanKhauModel, String> tableComlumIDNhanKhauHoGiaDinh, tableComlumIDHoKhauNhanKhauHoGiaDinh,
            tableComlumHoTenNhanKhauHoGiaDinh, tableComlumBietDanhNhanKhauHoGiaDinh, tableComlumGioiTinhNhanKhauHoGiaDinh,
            tableComlumTonGiaoNhanKhauHoGiaDinh, tableComlumTinhTrangNhanKhauHoGiaDinh;
    @FXML
    TableColumn<NhanKhauModel, Date> tableComlumNgaySinhNhanKhauHoGiaDinh;
    final NhanKhauService nhanKhauService = new NhanKhauService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewNhanKhauHoGiaDinh.setEditable(true);
        tableComlumIDNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("maNhanKhau"));
        tableComlumIDHoKhauNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("maHoKhau"));
        tableComlumHoTenNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("hoTen"));
        tableComlumBietDanhNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("bietDanh"));
        tableComlumNgaySinhNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, Date>("ngaySinh"));
        tableComlumGioiTinhNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("gioiTinh"));
        tableComlumTonGiaoNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("tonGiao"));
        //tableComlumTinhTrangNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("tinhTrang"));
        displayAllNhanKhauCoTrongHoGiaDinh();
    }
    public void onPressedButtonLocThongTinHoGiaDinh(){

    }
    public void onPressedButtonDangXuatHoGiaDinh(){

    }
    public void onPressedButtonThemMoiNhanKhauHoGiaDinh(MouseEvent e) throws IOException {
        if(e.isPrimaryButtonDown()){
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "ThemMoiNhanKhauHoGiaDinhView.fxml");
        }
    }
    public void onPressedButtonTamVangNhanKhauHoGiaDinh(MouseEvent e) throws IOException{
        if(e.isPrimaryButtonDown()){
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "TamVangHoGiaDinhView.fxml");
        }
    }
    public void onPressedButtonTamTruNhanKhauHoGiaDinh(MouseEvent e) throws IOException{
        if(e.isPrimaryButtonDown()){
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "TamTruHoGiaDinhView.fxml");
        }
    }
    public void onPressedButtonKhaiTuNhanKhauHoGiaDinh(){

    }
    public void displayAllNhanKhauCoTrongHoGiaDinh(){
        ObservableList<NhanKhauModel> nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauTrongHoKhau(maHoKhauDangNhap);
        tableViewNhanKhauHoGiaDinh.setItems(nhanKhauModelObservableList);
        System.out.println(nhanKhauModelObservableList);
        System.out.println(maHoKhauDangNhap);
    }
}
