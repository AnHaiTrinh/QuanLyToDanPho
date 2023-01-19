package com.se07.controller.controllers;

import com.se07.controller.services.TamTruService;
import com.se07.model.models.TamTruDisplayModel;
import com.se07.view.TrangChuCanBoView;
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
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerDangKyTamTruCanBoView extends ControllerCanBoView {
    @FXML
    AnchorPane anchorPaneChinhCanBo;
    @FXML
    TextField textFieldCCCDTamTruCanBo, textFieldHoTenTamTruCanBo, textFieldLyDoTamTruCanBo;
    @FXML
    ComboBox comboBoxNoiTamTruCanBo;
    @FXML
    DatePicker datePickerTuNgayTamTruCanBo, datePickerDenNgayTamTruCanBo;

    LocalDate today = LocalDate.now();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        anchorPaneChinhCanBo.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                xacNhanDangKyTamTruCanBo();
            } else if (keyEvent.getCode() == KeyCode.Q) {
                try {
                    huyXacNhanDangKyTamTruCanBo(keyEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        comboBoxNoiTamTruCanBo.getItems().add("Số 1, Tạ Quang Bửu, Hai Bà trưng, Hà Nội");
        comboBoxNoiTamTruCanBo.getSelectionModel().selectFirst();
        datePickerTuNgayTamTruCanBo.setValue(today);
        datePickerDenNgayTamTruCanBo.setValue(today.plusDays(7));
    }

    public void onPressedButtonXacNhanDangKyTamTruCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xacNhanDangKyTamTruCanBo();
        }
    }


    public void onPressedButtonHuyXacNhanDangKyTamTruCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            huyXacNhanDangKyTamTruCanBo(e);
        }
    }

    private void huyXacNhanDangKyTamTruCanBo(Event e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("TamTruCanBo.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        }
    }

    private void xacNhanDangKyTamTruCanBo() {
        TamTruService tamTruService = new TamTruService();
        if (textFieldCCCDTamTruCanBo.getText().isBlank() ||
                textFieldHoTenTamTruCanBo.getText().isBlank() ||
                textFieldLyDoTamTruCanBo.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        String cccd = textFieldCCCDTamTruCanBo.getText();
        String hoTen = textFieldHoTenTamTruCanBo.getText();
        String noiTamTru = String.valueOf(comboBoxNoiTamTruCanBo.getValue());
        Date tuNgay = new Date(datePickerTuNgayTamTruCanBo.getValue().toEpochDay());
        Date denNgay = new Date(datePickerDenNgayTamTruCanBo.getValue().toEpochDay());
        String lyDo = textFieldLyDoTamTruCanBo.getText();
        TamTruDisplayModel tamTruDisplayModel = new TamTruDisplayModel(cccd, hoTen, noiTamTru, tuNgay, denNgay, lyDo, tinhTrang, id);
        if (tamTruService.addTamtru(tamTruDisplayModel)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("");
            alert.setContentText("Bạn đã đăng ký thành công");
            if (alert.showAndWait().get() == ButtonType.OK) {
                textFieldCCCDTamTruCanBo.setText("");
                textFieldHoTenTamTruCanBo.setText("");
                textFieldLyDoTamTruCanBo.setText("");
            }
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("");
        alert.setContentText("Đăng ký tạm trú thất bại!");
        alert.showAndWait();
    }
}
