package com.se07.controller.controllers.controllershogiadinh;

import com.se07.controller.services.NhanKhauService;
import com.se07.model.models.HoKhauModel;
import com.se07.model.models.NhanKhauModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
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
    @FXML
    ComboBox comboBoxTimKiemHoGiaDinh;
    @FXML
    TextField textFieldLocThongTinHoGiaDinh;
    final NhanKhauService nhanKhauService = new NhanKhauService();
    private ObservableList<String> listTimKiem = FXCollections.observableArrayList(
            "Tên chủ hộ", "Mã hộ khẩu", "Địa chỉ", "Ngày lập");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxTimKiemHoGiaDinh.getItems().addAll(listTimKiem);
        tableViewNhanKhauHoGiaDinh.setEditable(true);
        tableComlumIDNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("maNhanKhau"));
        tableComlumIDHoKhauNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("maHoKhau"));
        tableComlumHoTenNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("hoTen"));
        tableComlumBietDanhNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("bietDanh"));
        tableComlumNgaySinhNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, Date>("ngaySinh"));
        tableComlumGioiTinhNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("gioiTinh"));
        tableComlumTonGiaoNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("tonGiao"));
        tableComlumTinhTrangNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("tinhTrang"));
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
