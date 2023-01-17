package com.se07.controller.controllers;

import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.NhanKhauService;
import com.se07.model.models.NhanKhauModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerThemMoiNhanKhauCanBoView extends ControllerCanBoView {
    @FXML
    TextField textFieldMaNhanKhauThemMoiNhanKhauCanBo, textFieldHoTenThemMoiNhanKhauCanBo,
            textFieldTonGiaoThemMoiNhanKhauCanBo, textFieldBietDanhThemMoiNhanKhauCanBo;
    @FXML
    DatePicker datePickerNgaySinhThemMoiNhanKhauCanBo;
    @FXML
    ComboBox comboBoxGioiTinhThemMoiNhanKhauCanBo, comboBoxMaHoKhauThemMoiNhanKhauCanBo;
    private String[] listGioiTinh = {"Nam", "Nữ", "Không rõ"};
    HoKhauService hoKhauService = new HoKhauService();
    final LocalDate today = LocalDate.now();

    final String tinhTrang = "Đã xác nhận";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxGioiTinhThemMoiNhanKhauCanBo.getItems().addAll(listGioiTinh);
        comboBoxGioiTinhThemMoiNhanKhauCanBo.getSelectionModel().selectFirst();
        List<String> list = hoKhauService.getAllMaHoKhau();
        for (String a : list) {
            comboBoxMaHoKhauThemMoiNhanKhauCanBo.getItems().add(a);
        }
        comboBoxMaHoKhauThemMoiNhanKhauCanBo.getSelectionModel().selectFirst();
        datePickerNgaySinhThemMoiNhanKhauCanBo.setValue(today);
        anchorPaneChinhCanBo.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                themMoiNhanKhauCanBo();
            } else if (keyEvent.getCode() == KeyCode.Q) {
                try {
                    huyThemMoiNhanKhauCanBo(keyEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void onPressedButtonThemMoiNhanKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            themMoiNhanKhauCanBo();
        }
    }

    public void onPressedButtonHuyThemMoiNhanKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            huyThemMoiNhanKhauCanBo(e);
        }
    }

    public void themMoiNhanKhauCanBo() {
        NhanKhauService nhanKhauService = new NhanKhauService();
        if (textFieldHoTenThemMoiNhanKhauCanBo.getText().isBlank() || textFieldMaNhanKhauThemMoiNhanKhauCanBo.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        String maNhanKhau = textFieldMaNhanKhauThemMoiNhanKhauCanBo.getText();
        String gioiTinh = String.valueOf(comboBoxGioiTinhThemMoiNhanKhauCanBo.getValue());
        String maHoKhau = String.valueOf(comboBoxMaHoKhauThemMoiNhanKhauCanBo.getValue());
        String hoTen = textFieldHoTenThemMoiNhanKhauCanBo.getText();
        String tonGiao = textFieldTonGiaoThemMoiNhanKhauCanBo.getText();
        String bietDanh = textFieldBietDanhThemMoiNhanKhauCanBo.getText();
        Date ngaysinh = new Date(datePickerNgaySinhThemMoiNhanKhauCanBo.getValue().toEpochDay());
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
                    textFieldMaNhanKhauThemMoiNhanKhauCanBo.setText("");
                    textFieldHoTenThemMoiNhanKhauCanBo.setText("");
                    textFieldBietDanhThemMoiNhanKhauCanBo.setText("");
                    textFieldTonGiaoThemMoiNhanKhauCanBo.setText("");
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

    public void huyThemMoiNhanKhauCanBo(Event e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "NhanKhauCanBoView.fxml");
        }
    }
}
