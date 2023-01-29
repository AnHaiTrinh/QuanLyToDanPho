package com.se07.controller.controllers.controllershogiadinh;

import com.se07.controller.services.TamVangService;
import com.se07.model.models.NhanKhauModel;
import com.se07.model.models.TamVangDisplayModel;
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

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerTamVangHoGiaDinhView extends ControllerHoGiaDinhView implements Initializable {
    TamVangService tamVangService = new TamVangService();
    @FXML
    TableColumn<TamVangDisplayModel, String> tableComlumMaTamVangNhanKhauHoGiaDinh, tableComlumMaNhanKhauNhanKhauHoGiaDinh, tableComlumHoTenNhanKhauHoGiaDinh,
            tableComlumNoiTamVangNhanKhauHoGiaDinh,  tableComlumLyDoNhanKhauHoGiaDinh,tableComlumTinhTrangNhanKhauHoGiaDinh;
    @FXML
    TableColumn<TamVangDisplayModel, Date> tableComlumDenNgayNhanKhauHoGiaDinh,tableComlumTuNgayNhanKhauHoGiaDinh;
    @FXML
    TableView<TamVangDisplayModel> tableViewTamVangHoGiaDinh;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableComlumMaTamVangNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("maTamVang"));
        tableComlumMaNhanKhauNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("maNhanKhau"));
        tableComlumHoTenNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("hoTen"));
        tableComlumNoiTamVangNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("noiTamVang"));
        tableComlumTuNgayNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, Date>("tuNgay"));
        tableComlumDenNgayNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, Date>("denNgay"));
        tableComlumLyDoNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("lyDo"));
        tableComlumTinhTrangNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("tinhTrang"));
        tableViewTamVangHoGiaDinh.setEditable(true);
        displayAllNhanKhauTamVangHoGiaDinh();
    }
    public void onPressedButtonThemMoiTamVangNhanKhauHoGiaDinh(MouseEvent e) throws IOException {
        if(e.isPrimaryButtonDown()){
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "DangKyTamVangHoGiaDinhView.fxml");
        }
    }
    public void onPressedButtonXoaTamVangNhanKhauHoGiaDinh(MouseEvent e) throws IOException{
        if(e.isPrimaryButtonDown()){
            xoaNhanKhauHoGiaDinh();
        }
    }
    public void onPressedButtonThoatTamVangNhanKhauHoGiaDinh(MouseEvent e) throws IOException{
        if(e.isPrimaryButtonDown()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn muốn thoát khỏi màn hình tạm vắng ?");
            if(alert.showAndWait().get()==ButtonType.OK){
                onPressedButtonNhanKhauHoGiaDinh(e);
            }
        }
    }
    public void onPressedButtonLocThongTinTamVangHoGiaDinh(){

    }
    public void displayAllNhanKhauTamVangHoGiaDinh(){
        ObservableList<TamVangDisplayModel> tamVangDisplayModels = tamVangService.getTamVangByMaChuHo(maHoKhauDangNhap);
        tableViewTamVangHoGiaDinh.setItems(tamVangDisplayModels);
    }
    public void xoaNhanKhauHoGiaDinh(){
        TamVangDisplayModel tamVangDisplayModel = tableViewTamVangHoGiaDinh.getSelectionModel().getSelectedItem();
        if (tamVangDisplayModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn xóa");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xóa trường hợp này!");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Thông báo");
                if (tamVangService.deleteTamVang(tamVangDisplayModel)) {
                    info.setHeaderText("Xóa thành công!");
                } else {
                    info.setHeaderText("Xóa không thành công!");
                }
                info.showAndWait();
                displayAllNhanKhauTamVangHoGiaDinh();
            }
        }
    }
}
