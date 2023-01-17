package com.se07.controller.controllers;

import com.se07.controller.services.NhanKhauService;
import com.se07.model.models.NhanKhauModel;
import com.se07.view.TrangChuCanBoView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerDangKyTamVangCanBoView extends ControllerCanBoView {
    @FXML
    AnchorPane anchorPaneChinhCanBo;
    LocalDate today = LocalDate.now();
    @FXML
    DatePicker datePickerTuNgayTamVangCanBo, datePickerDenNgayTamVangCanBo;
    @FXML
    TextField textFieldHoTenTamVangCanBo, textFieldLyDoTamVangCanBo, textFieldNoiTamVangCanBo;
    @FXML
    ComboBox comBoBoxMaNhanKhauTamVangCanBo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        datePickerTuNgayTamVangCanBo.setValue(today);
        datePickerDenNgayTamVangCanBo.setValue(today);
        List<String> list = (new NhanKhauService()).getAllMaNhanKhau();
        for (String a : list) {
            comBoBoxMaNhanKhauTamVangCanBo.getItems().add(a);
        }
        anchorPaneChinhCanBo.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                xacNhanDangKyTamVangCanBo();
            } else if (keyEvent.getCode() == KeyCode.Q) {
                try {
                    huyXacNhanDangKyTamVangCanBo(keyEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void onPressedButtonXacNhanDangKyTamVangCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xacNhanDangKyTamVangCanBo();
        }
    }

    public void onPressedButtonHuyXacNhanDangKyTamVangCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            huyXacNhanDangKyTamVangCanBo(e);
        }
    }

    public void onSelectioncomBoBoxMaNhanKhauTamVangCanBo(ActionEvent e) {
        String maNhanKhau = String.valueOf(comBoBoxMaNhanKhauTamVangCanBo.getValue());
        NhanKhauService nhanKhauService = new NhanKhauService();
        Optional<NhanKhauModel> hoKhauModel = nhanKhauService.getNhanKhauByMaNhanKhau(maNhanKhau);
        textFieldHoTenTamVangCanBo.setText(hoKhauModel.get().getHoTen());
    }

    public void xacNhanDangKyTamVangCanBo() {

    }

    public void huyXacNhanDangKyTamVangCanBo(Event e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("TamVangCanBo.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        }
    }


}
