package com.se07.controller.controllers.controllerscanbo;


import com.se07.controller.services.DipTraoThuongService;
import com.se07.model.models.DipTraoThuongModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

public class ControllerTaoBieuMauDipTraoThuongCanBoView extends ControllerCanBoView {
    @FXML
    GridPane gridPaneTaoBieuMauDipTraoThuongCanBo;
    @FXML
    TextField textFieldTenDipTraoThuongCanBo, textFieldNamDipTraoThuongCanBo, textFieldGhiChuDipTraoThuongCanBo;
    @FXML
    DatePicker datePickerNgayTaoDipTraoThuongCanBo, datePickerNgayKetThucDipTraoThuongCanBo;
    @FXML
    ComboBox comboBoxKieuDipTraoThuongCanBo;

    final ObservableList<String> listKieuGiaiThuong = FXCollections.observableArrayList("Dịp đặc biệt", "Thành tích");
    final LocalDate today = LocalDate.now();
    private final DipTraoThuongService dipTraoThuongService = new DipTraoThuongService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        gridPaneTaoBieuMauDipTraoThuongCanBo.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                xacNhanTaoBieuMauDipTraoThuongCanBo();
            } else if (keyEvent.getCode().equals(KeyCode.Q)) {
                try {
                    huyTaoBieuMauDipTraoThuongCanBo(keyEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        textFieldNamDipTraoThuongCanBo.setText(String.valueOf(today.getYear()));
        comboBoxKieuDipTraoThuongCanBo.getItems().addAll(listKieuGiaiThuong);
        comboBoxKieuDipTraoThuongCanBo.getSelectionModel().selectFirst();
        datePickerNgayTaoDipTraoThuongCanBo.setValue(today);
        datePickerNgayKetThucDipTraoThuongCanBo.setValue(today.plusDays(7));
    }

    public void onPressedButtonXacNhanTaoBieuMauDipTraoThuongCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xacNhanTaoBieuMauDipTraoThuongCanBo();
        }
    }

    public void onPressedButtonHuyTaoBieuMauDipTraoThuongCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            huyTaoBieuMauDipTraoThuongCanBo(e);
        }
    }

    private void xacNhanTaoBieuMauDipTraoThuongCanBo() {
        if (textFieldTenDipTraoThuongCanBo.getText().isBlank() || textFieldNamDipTraoThuongCanBo.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        int nam = Integer.valueOf(textFieldNamDipTraoThuongCanBo.getText());
        if (nam < 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập năm hợp lệ");
            alert.showAndWait();
            textFieldNamDipTraoThuongCanBo.requestFocus();
            return;
        }
        String tenDip = textFieldTenDipTraoThuongCanBo.getText();
        String kieu = String.valueOf(comboBoxKieuDipTraoThuongCanBo.getValue());
        Date ngayTao = Date.from(datePickerNgayTaoDipTraoThuongCanBo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date ngayKetThuc = Date.from(datePickerNgayKetThucDipTraoThuongCanBo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String ghiChu = textFieldGhiChuDipTraoThuongCanBo.getText();
        DipTraoThuongModel dipTraoThuongModel = new DipTraoThuongModel(tenDip, nam, ngayTao, ngayKetThuc, kieu, ghiChu);
        if (dipTraoThuongService.addDipTraoThuong(dipTraoThuongModel)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("");
            alert.setContentText("Bạn đã thêm thành công");
            if (alert.showAndWait().get() == ButtonType.OK) {
                textFieldTenDipTraoThuongCanBo.setText("");
                textFieldNamDipTraoThuongCanBo.setText("");
                textFieldGhiChuDipTraoThuongCanBo.setText("");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("");
            alert.setContentText("Thêm mới nhân khẩu thất bại!");
            alert.showAndWait();
        }
    }

    private void huyTaoBieuMauDipTraoThuongCanBo(Event e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "GiaiThuongCanBoView.fxml");
        }
    }
}
