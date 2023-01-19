package com.se07.controller.controllers;

import com.se07.controller.services.TamVangService;
import com.se07.model.models.TamVangDisplayModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerTamVangCanBoView extends ControllerCanBoView {
    @FXML
    Button buttonLocThongTinTamVangCanBo, buttonThemMoiTamVangCanBo, buttonXacNhanTamVangCanBo, buttonTuChoiTamVangCanBo,
            buttonXoaTamVangCanBo, buttonThoatTamVangCanBo;
    @FXML
    TableView<TamVangDisplayModel> tableViewTamVangCanBo;
    @FXML
    TableColumn<TamVangDisplayModel, String> tableColumnMaNhanKhauTamVangCanBo, tableColumnHoTenTamVangCanBo,
            tableColumnNoiTamVangCanBo, tableColumnLyDoTamVangCanBo, tableColumnTinhTrangTamVangCanBo;
    @FXML
    TableColumn<TamVangDisplayModel, Date> tableColumnTuNgayTamVangcanBo, tableColumnDenNgayTamVangcanBo;
    @FXML
    ComboBox comboBoxTimKiemTamVangCanBo;
    @FXML
    TextField textFieldLocThongTinTamVangCanBo;

    final TamVangService tamVangService = new TamVangService();

    LocalDate today = LocalDate.now();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        displayAlltamVangCanBo();
    }

    public void onPressedButtonLocThongTinTamVangCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinTamVangCanBo();
        }
    }

    public void onPressedButtonThemMoiTamVangCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "DangKyTamVangCanBoView.fxml");
        }
    }

    public void onPressedkButtonXacNhanTamVangCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xacNhanTamVangCanBo();
        }
    }

    public void onDeletePressedTrongBangTamVangCanBo(KeyEvent e) {
        if (e.getCode() == KeyCode.DELETE) {
            xoaTamVangCanBo();
        }
    }

    public void onPressedButtonTuChoiTamVangCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            tuChoiTamVangcanBo();
        }
    }

    public void onPressedButtonXoaTamVangCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaTamVangCanBo();
        }
    }

    public void onPressedButtonThoatTamVangCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Bạn chắc chắn muốn thoát?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                        "tamVangCanBoView.fxml");
            }
        }
    }

    public void displayAlltamVangCanBo() {
    }

    public void xacNhanTamVangCanBo() {
    }

    public void tuChoiTamVangcanBo() {
    }

    public void xoaTamVangCanBo() {

    }

    public void locThongTinTamVangCanBo() {

    }

    public void updateTamVangCanBo(TamVangDisplayModel tamVangDisplayModel) {

    }
}
