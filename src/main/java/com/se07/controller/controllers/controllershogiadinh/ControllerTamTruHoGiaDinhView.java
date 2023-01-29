package com.se07.controller.controllers.controllershogiadinh;

import com.se07.controller.services.TamTruService;
import com.se07.model.models.TamTruDisplayModel;
import com.se07.model.models.TamTruDisplayModel;
import com.se07.model.models.TamTruDisplayModel;
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

public class ControllerTamTruHoGiaDinhView extends ControllerHoGiaDinhView implements Initializable {
    @FXML
    TableView<TamTruDisplayModel> tableViewNhanKhauTamTruHoGiaDinh;
    @FXML
    TableColumn<TamTruDisplayModel,  String>  tableComlumMaTamTruNhanKhauHoGiaDinh, tableComlumCCCDNhanKhauHoGiaDinh, tableComlumHoTenNhanKhauHoGiaDinh,
            tableComlumNoiTamTruNhanKhauHoGiaDinh,tableComlumTinhTrangNhanKhauHoGiaDinh, tableComlumLyDoNhanKhauHoGiaDinh;
    @FXML
    TableColumn<TamTruDisplayModel, Date> tableComlumTuNgayNhanKhauHoGiaDinh, tableComlumDenNgayNhanKhauHoGiaDinh;
    private TamTruService tamTruService =  new TamTruService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableComlumMaTamTruNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel,  String>("maTamtru"));
        tableComlumCCCDNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("CCCD"));
        tableComlumHoTenNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("hoTen"));
        tableComlumNoiTamTruNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("noiTamTru"));
        tableComlumTuNgayNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, Date>("tuNgay"));
        tableComlumDenNgayNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, Date>("denNgay"));
        tableComlumLyDoNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("lyDo"));
        tableComlumTinhTrangNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("tinhTrang"));
        tableViewNhanKhauTamTruHoGiaDinh.setEditable(true);
        displayAllNhanKhauTamTruHoGiaDinh();
    }
    public void onPressedButtonThemMoiTamTruNhanKhauHoGiaDinh(MouseEvent e) throws IOException {
        if(e.isPrimaryButtonDown()){
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "DangKyTamTruNhanKhauHoGiaDinhView.fxml");
        }
    }
    public void onPressedButtonXoaTamTruNhanKhauHoGiaDinh(MouseEvent e) throws IOException {
        if(e.isPrimaryButtonDown()){
            xoaNhanKhauTamTruHoGiaDinh();
        }
    }
    public void onPressedButtonThoatTamTruNhanKhauHoGiaDinh(MouseEvent e) throws  IOException{
        if(e.isPrimaryButtonDown()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn xóa");
            if(alert.showAndWait().get()==ButtonType.OK){
                onPressedButtonNhanKhauHoGiaDinh(e);
            }
        }
    }
    public void onPressedButtonLocThongTinTamTruHoGiaDinh(){

    }
    public void displayAllNhanKhauTamTruHoGiaDinh(){
        ObservableList<TamTruDisplayModel> tamTruDisplayModels = tamTruService.getTamTruByMaChuHo(maHoKhauDangNhap);
        tableViewNhanKhauTamTruHoGiaDinh.setItems(tamTruDisplayModels);
    }
    public void xoaNhanKhauTamTruHoGiaDinh(){
        TamTruDisplayModel tamTruDisplayModel = tableViewNhanKhauTamTruHoGiaDinh.getSelectionModel().getSelectedItem();
        if (tamTruDisplayModel == null) {
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
                if (tamTruService.deleteTamTru(tamTruDisplayModel)) {
                    info.setHeaderText("Xóa thành công!");
                } else {
                    info.setHeaderText("Xóa không thành công!");
                }
                info.showAndWait();
                displayAllNhanKhauTamTruHoGiaDinh();
            }
        }
    }
}
