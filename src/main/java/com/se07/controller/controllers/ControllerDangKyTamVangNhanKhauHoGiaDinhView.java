package com.se07.controller.controllers;
import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.NhanKhauService;
import com.se07.controller.services.TamVangService;
import com.se07.model.models.NhanKhauModel;
import com.se07.model.models.TamVangModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerDangKyTamVangNhanKhauHoGiaDinhView extends ControllerHoGiaDinhView implements Initializable {
    LocalDate today  = LocalDate.now();
    @FXML
    TextField textFieldHoTenDangKyTamVangHoGiaDinh, textFieldNoiTamVangDangKyTamVangHoGiaDinh, textFieldLyDoDangKyTamVangHoGiaDinh, textFieldMaTamVangDangKyTamVangHoGiaDinh;
    @FXML
    DatePicker  datePickerTuNgayDangKyTamVangHoGiaDinh, datePickerDenNgayDangKyTamVangHoGiaDinh;
    @FXML
    ComboBox    comBoBoxMaNhanKhauDangKyTamVangHoGiaDinh;
    private final String tinhTrang ="chưa xác nhận";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePickerTuNgayDangKyTamVangHoGiaDinh.setValue(today);
        datePickerDenNgayDangKyTamVangHoGiaDinh.setValue(today.plusDays(7));
        comBoBoxMaNhanKhauDangKyTamVangHoGiaDinh.getItems().addAll(new NhanKhauService().getAllMaNhanKhauTrongHoKhau(new HoKhauService().getMaHoKhauByIdChuHo(id)));
        comBoBoxMaNhanKhauDangKyTamVangHoGiaDinh.getSelectionModel().selectFirst();
        textFieldHoTenDangKyTamVangHoGiaDinh.setEditable(false);
    }
    public void onPressedButtonDangKyTamVangNhanKhauHoGiaDinh(MouseEvent e) throws IOException {
        if(e.isPrimaryButtonDown()){
            xacNhanDangKyTamVangHoGiaDinh();
        }
    }
    public void onPressedButtonHuyDangKyTamVangNhanKhauHoGiaDinh(){

    }
    public void onSelectionComBoBoxMaNhanKhauDangKyTamVangHoGiaDinh(ActionEvent e){
        String maNhanKhau = String.valueOf(comBoBoxMaNhanKhauDangKyTamVangHoGiaDinh.getValue());
        NhanKhauService nhanKhauService = new NhanKhauService();
        Optional<NhanKhauModel> nhanKhauModelOptional = nhanKhauService.getNhanKhauByMaNhanKhau(maNhanKhau);
        textFieldHoTenDangKyTamVangHoGiaDinh.setText(nhanKhauModelOptional.get().getHoTen());
    }
    public void xacNhanDangKyTamVangHoGiaDinh() {
        TamVangService tamVangService = new TamVangService();
        if (textFieldLyDoDangKyTamVangHoGiaDinh.getText().isBlank() || textFieldNoiTamVangDangKyTamVangHoGiaDinh.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        String maNhanKhau = String.valueOf(comBoBoxMaNhanKhauDangKyTamVangHoGiaDinh.getValue());
        String noiTamVang = textFieldNoiTamVangDangKyTamVangHoGiaDinh.getText();
        Date tuNgay = new Date(datePickerTuNgayDangKyTamVangHoGiaDinh.getValue().toEpochDay());
        Date denNgay = new Date(datePickerDenNgayDangKyTamVangHoGiaDinh.getValue().toEpochDay());
        String lyDo = textFieldLyDoDangKyTamVangHoGiaDinh.getText();
        int maTamVang = Integer.valueOf(textFieldMaTamVangDangKyTamVangHoGiaDinh.getText());
        TamVangModel tamVangModel = new TamVangModel(maNhanKhau, noiTamVang, tuNgay, denNgay, lyDo, tinhTrang, id);
        if (tamVangService.addTamVang(tamVangModel)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("");
            alert.setContentText("Bạn đã đăng ký thành công");
            if (alert.showAndWait().get() == ButtonType.OK) {
                textFieldNoiTamVangDangKyTamVangHoGiaDinh.setText("");
                textFieldLyDoDangKyTamVangHoGiaDinh.setText("");
            }
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("");
        alert.setContentText("Đăng ký tạm vắng thất bại!");
        alert.showAndWait();
    }
}
