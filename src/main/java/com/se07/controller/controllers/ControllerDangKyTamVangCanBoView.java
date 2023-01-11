package com.se07.controller.controllers;

import com.se07.controller.services.NhanKhauService;
import com.se07.controller.services.TamVangService;
import com.se07.model.models.TamVangModel;
import com.se07.view.TrangChuCanBoView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerDangKyTamVangCanBoView extends  ControllerTrangChuView implements Initializable{
    LocalDate today = LocalDate.now();
    @FXML
    DatePicker datePickerTuNgayTamVangCanBo,  datePickerDenNgayTamVangCanBo;
    @FXML
    TextField textFieldMaTamVangTamVangCanBo, textFieldHoTenTamVangCanBo,
            textFieldNoiTamVangTamVangCanBo, textFieldLyDoTamVangCanBo;
    @FXML
    ComboBox comBoBoxMaNhanKhauTamVangCanBo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePickerTuNgayTamVangCanBo.setValue(today);
        datePickerDenNgayTamVangCanBo.setValue(today);
        List<String> list = (new NhanKhauService()).getAllMaNhanKhau();
        for (String  a: list) {
            comBoBoxMaNhanKhauTamVangCanBo.getItems().add(a);
        }
        comBoBoxMaNhanKhauTamVangCanBo.getSelectionModel().selectFirst();
    }
    public  void setButtonXacNhanDangKyTamVangCanBo(){
        TamVangService tamVangService = new TamVangService();
        if(textFieldHoTenTamVangCanBo.getText().isBlank()||textFieldLyDoTamVangCanBo.getText().isBlank()||textFieldMaTamVangTamVangCanBo.getText().isBlank()||textFieldNoiTamVangTamVangCanBo.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đủ các trường");
            alert.showAndWait();
        }else{
            String maNhanKhau = String.valueOf(comBoBoxMaNhanKhauTamVangCanBo.getValue());
            String maTamVang = textFieldMaTamVangTamVangCanBo.getText();
            String noiTamVang = textFieldNoiTamVangTamVangCanBo.getText();
            String lyDo = textFieldLyDoTamVangCanBo.getText();
            Date tuNgay = new Date(datePickerTuNgayTamVangCanBo.getValue().toEpochDay());
            Date denNgay =  new Date(datePickerDenNgayTamVangCanBo.getValue().toEpochDay());
            String tinhTrang = "Đã xác nhận";
            TamVangModel tamVangModel  = new TamVangModel(maTamVang, maNhanKhau,noiTamVang, tuNgay, denNgay, lyDo,tinhTrang, id);
            if(tamVangService.addTamVang(tamVangModel)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Bạn đã thêm thành công");
                if(alert.showAndWait().get()== ButtonType.OK){
                    textFieldMaTamVangTamVangCanBo.setText("");
                    textFieldNoiTamVangTamVangCanBo.setText("");
                    textFieldLyDoTamVangCanBo.setText("");
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Thêm thất bại");
            }
        }
    }
    public void setButtonKhongXacNhanDangKyTamVangCanBo(ActionEvent e) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn muốn hủy đăng ký tạm vắng");
        if(alert.showAndWait().get()==ButtonType.OK){
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("TamVangCanBo.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        }
    }


}
