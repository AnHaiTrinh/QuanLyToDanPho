package com.se07.controller.controllers.controllerscanbo;

import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.TamTruService;
import com.se07.model.models.TamTruModel;
import com.se07.view.TrangChuCanBoView;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerDangKyTamTruCanBoView extends ControllerCanBoView {
    @FXML
    TextField textFieldCCCDTamTruCanBo, textFieldHoTenTamTruCanBo, textFieldLyDoTamTruCanBo;
    @FXML
    ComboBox comboBoxNoiTamTruCanBo;
    @FXML
    DatePicker datePickerTuNgayTamTruCanBo, datePickerDenNgayTamTruCanBo;
    @FXML
    GridPane gridPaneDangKyTamTruCanBo;
    LocalDate today = LocalDate.now();

    final String tinhTrang = "Đã xác nhận";

    final private TamTruService tamTruService = new TamTruService();
    final private HoKhauService hoKhauService = new HoKhauService();

    final ObservableList<String> listNoiTamTru = hoKhauService.getAllDiaChi();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        gridPaneDangKyTamTruCanBo.setOnKeyPressed((keyEvent) -> {
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
        comboBoxNoiTamTruCanBo.getItems().addAll(listNoiTamTru);
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

    public void huyXacNhanDangKyTamTruCanBo(Event e) throws IOException {
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

    public void xacNhanDangKyTamTruCanBo() {
        if (textFieldCCCDTamTruCanBo.getText().isBlank() ||
                textFieldHoTenTamTruCanBo.getText().isBlank() ||
                textFieldLyDoTamTruCanBo.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        String maHoKhau = hoKhauService.getMaHoKhauByDiaChi(String.valueOf(comboBoxNoiTamTruCanBo.getValue()));
        String cccd = textFieldCCCDTamTruCanBo.getText();
        String hoTen = textFieldHoTenTamTruCanBo.getText();
        Date tuNgay = Date.from(datePickerTuNgayTamTruCanBo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date denNgay = Date.from(datePickerDenNgayTamTruCanBo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String lyDo = textFieldLyDoTamTruCanBo.getText();
        TamTruModel tamTruModel = new TamTruModel(maHoKhau, cccd, hoTen, tuNgay, denNgay, lyDo, tinhTrang, id);
        if (tamTruService.addTamTru(tamTruModel)) {
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