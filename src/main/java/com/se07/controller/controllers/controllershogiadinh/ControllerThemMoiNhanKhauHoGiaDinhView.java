package com.se07.controller.controllers.controllershogiadinh;

import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.NhanKhauService;
import com.se07.model.models.NhanKhauModel;
import com.se07.util.UserInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerThemMoiNhanKhauHoGiaDinhView extends ControllerHoGiaDinhView implements Initializable {
    @FXML
    TextField textFieldMaNhanKhauThemMoiNhanKhauHoGiaDinh, textFieldHoTenThemMoiNhanKhauHoGiaDinh,
            textFieldTonGiaoThemMoiNhanKhauHoGiaDinh, textFieldBietDanhThemMoiNhanKhauHoGiaDinh, textFieldMaHoKhauThemMoiNhanKhauHoGiaDinh;

    @FXML
    DatePicker datePickerNgaySinhThemMoiNhanKhauHoGiaDinh;
    @FXML
    ComboBox comboBoxGioiTinhThemMoiNhanKhauHoGiaDinh;
    
    final private ObservableList<String> listGioiTinh = FXCollections.observableArrayList("Nam", "Nữ","Khác");
    final HoKhauService hoKhauService = new HoKhauService();
    final String tinhTrang = "Chưa xác nhận";
    LocalDate today = LocalDate.now();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxGioiTinhThemMoiNhanKhauHoGiaDinh.getItems().addAll(listGioiTinh);
        comboBoxGioiTinhThemMoiNhanKhauHoGiaDinh.getSelectionModel().selectFirst();
        textFieldMaHoKhauThemMoiNhanKhauHoGiaDinh.setText(maHoKhauDangNhap);
        datePickerNgaySinhThemMoiNhanKhauHoGiaDinh.setValue(today);
    }
    public void onPressedButtonThemMoiNhanKhauHoGiaDinh(MouseEvent e) throws IOException {
        if(e.isPrimaryButtonDown()){
            themMoiNhanKhauHoGiaDinh();
        }
    }
    public void onPressedButtonHuyThemMoiNhanKhauHoGiaDinh(MouseEvent e) throws IOException{
        if(e.isPrimaryButtonDown()){
            huyThemMoiNhanKhauHoGiaDinh(e);
        }
    }
    public void themMoiNhanKhauHoGiaDinh() {
        NhanKhauService nhanKhauService = new NhanKhauService();
        if (textFieldHoTenThemMoiNhanKhauHoGiaDinh.getText().isBlank() || textFieldMaNhanKhauThemMoiNhanKhauHoGiaDinh.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        String maNhanKhau = textFieldMaNhanKhauThemMoiNhanKhauHoGiaDinh.getText();
        String gioiTinh = String.valueOf(comboBoxGioiTinhThemMoiNhanKhauHoGiaDinh.getValue());
        String maHoKhau = textFieldMaHoKhauThemMoiNhanKhauHoGiaDinh.getText();
        String hoTen = textFieldHoTenThemMoiNhanKhauHoGiaDinh.getText();
        String tonGiao = textFieldTonGiaoThemMoiNhanKhauHoGiaDinh.getText();
        String bietDanh = textFieldBietDanhThemMoiNhanKhauHoGiaDinh.getText();
        Date ngaysinh = new Date(datePickerNgaySinhThemMoiNhanKhauHoGiaDinh.getValue().toEpochDay());
        NhanKhauModel nhanKhauModel = new NhanKhauModel(maNhanKhau, maHoKhau, hoTen, bietDanh, ngaysinh, gioiTinh, tonGiao, tinhTrang, id);
        if (!nhanKhauService.getNhanKhauByMaNhanKhau(maNhanKhau).isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Mã nhân khẩu đã tồn tại");
            alert.showAndWait();
        } else {
            if (nhanKhauService.addNhanKhau(nhanKhauModel)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("");
                alert.setContentText("Bạn đã thêm thành công");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    textFieldMaNhanKhauThemMoiNhanKhauHoGiaDinh.setText("");
                    textFieldHoTenThemMoiNhanKhauHoGiaDinh.setText("");
                    textFieldBietDanhThemMoiNhanKhauHoGiaDinh.setText("");
                    textFieldTonGiaoThemMoiNhanKhauHoGiaDinh.setText("");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("");
                alert.setContentText("Thêm mới nhân khẩu thất bại!");
                alert.showAndWait();
            }
        }
    }
    public void huyThemMoiNhanKhauHoGiaDinh(Event e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "NhanKhauHoGiaDinhView.fxml");
        }
    }
}
