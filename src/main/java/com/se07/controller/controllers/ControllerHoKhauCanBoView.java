package com.se07.controller.controllers;

import com.se07.controller.services.HoKhauService;
import com.se07.model.models.HoKhauModel;
import com.se07.view.TrangChuCanBoView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHoKhauCanBoView implements Initializable {
    @FXML
    private TableColumn<HoKhauModel, String> tableColumnMaHoHoKhauCanBo, tableColumnHotenHoKhauCanBo, tableColumnDiaChiHoKhauCanBo;
    @FXML
    private TableView tableViewTatCaHoKhauCanBo;
    @FXML
    private ComboBox comboBoxTimKiemHoKhauCanBo;
    @FXML
    private TextField textFieldLocThongTinHoKhauCanBo;
    private String[] truong={"Tên chủ hộ","Mã hộ khẩu","Địa chỉ","Tất cả"};
    ControllerTrangChuView controllerTrangChuView = new ControllerTrangChuView();
    HoKhauService hoKhauService = new HoKhauService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxTimKiemHoKhauCanBo.getItems().addAll(truong);
        comboBoxTimKiemHoKhauCanBo.getSelectionModel().selectFirst();
    }
    public  void setButtonLogoutAdmin(ActionEvent e) throws IOException{
        controllerTrangChuView.setButtonLogoutAdmin(e);
    }
    public void setNhanNutHoKhauCanBo(){

    }
    public void setNhanNutDeleteTrongBangHoKhauCanBo(){

    }
    public void setButtonLocThongTinHoKhauCanBo(){
        HoKhauService hoKhauService1 = new HoKhauService();
        String dieukienkiemtra = String.valueOf(comboBoxTimKiemHoKhauCanBo.getValue());
        String cauhoi = textFieldLocThongTinHoKhauCanBo.getText();
        if(dieukienkiemtra.equals("Địa chỉ")){
            ObservableList<HoKhauModel> hoKhauModelObservableList  = hoKhauService1.getHoKhauByDiaChi(cauhoi);
            tableColumnMaHoHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("maHoKhau"));
            tableColumnHotenHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("chuHo"));
            tableColumnDiaChiHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("diaChi"));
            tableViewTatCaHoKhauCanBo.setItems(hoKhauModelObservableList);
            System.out.println(hoKhauModelObservableList);
            System.out.println(cauhoi);
            System.out.println(dieukienkiemtra);
        } if(dieukienkiemtra.equals("Tên chủ hộ")){
            ObservableList<HoKhauModel> hoKhauModelObservableList  = hoKhauService1.getHoKhauByChuHo(cauhoi);
            tableColumnMaHoHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("maHoKhau"));
            tableColumnHotenHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("chuHo"));
            tableColumnDiaChiHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("diaChi"));
            tableViewTatCaHoKhauCanBo.setItems(hoKhauModelObservableList);
            System.out.println(hoKhauModelObservableList);
            System.out.println(cauhoi);
            System.out.println(dieukienkiemtra);
        }
    }
    public void setButtonHomeAdmin(ActionEvent e) throws IOException {
        ObservableList<HoKhauModel> hoKhauModelObservableList = hoKhauService.getAllHoKhau();
        System.out.println(hoKhauModelObservableList);
        tableColumnMaHoHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("maHoKhau"));
        tableColumnHotenHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("chuHo"));
        tableColumnDiaChiHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("diaChi"));
        tableViewTatCaHoKhauCanBo.setItems(hoKhauModelObservableList);
        System.out.println(11232423);
        controllerTrangChuView.setButtonHomeAdmin(e);
        System.out.println(1324);
    }
    public void setButtonPeopleAdmin(ActionEvent e) throws IOException{
        controllerTrangChuView.setButtonPeopleAdmin(e);
    }
    public void setButtonUserAdmin(ActionEvent e) throws IOException{
        ObservableList<HoKhauModel> hoKhauModelObservableList = hoKhauService.getAllHoKhau();

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        tableColumnMaHoHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("maHoKhau"));
        tableColumnHotenHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("chuHo"));
        tableColumnDiaChiHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("diaChi"));
        tableViewTatCaHoKhauCanBo.setItems(hoKhauModelObservableList);
    }
    public void setButtonGiaiThuongCanBo(ActionEvent e) throws IOException{
        controllerTrangChuView.setButtonGiaiThuongCanBo(e);
    }
    public void setButtonThemHoKhauCanBo(ActionEvent e) throws  IOException{
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("ThemHoKhauMoiCanBoView.fxml"));
        Scene scene =  new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void setButtonTachHoKhauCanBo(ActionEvent e) throws  IOException{
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("TachHoKhauCanBoView.fxml"));
        Scene scene =  new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void setButtonXoaHoKhauCanBo(){

    }


}
