package com.se07.controller.controllers;

import com.se07.controller.services.HoKhauService;
import com.se07.model.models.HoKhauModel;
import com.se07.util.ComponentVisibility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerHoKhauCanBoView extends ControllerCanBoView {
    @FXML
    TableColumn<HoKhauModel, String> tableColumnMaHoHoKhauCanBo, tableColumnHotenHoKhauCanBo, tableColumnDiaChiHoKhauCanBo;
    @FXML
    TableColumn<HoKhauModel, Date> tableColumnNgayLapHoKhauCanBo;
    @FXML
    TableView<HoKhauModel> tableViewTatCaHoKhauCanBo;
    @FXML
    ComboBox comboBoxTimKiemHoKhauCanBo;
    @FXML
    DatePicker datePickerTu, datePickerDen;
    @FXML
    TextField textFieldLocThongTinHoKhauCanBo;
    private ObservableList<String> listTimKiem = FXCollections.observableArrayList(
            "Tên chủ hộ", "Mã hộ khẩu", "Địa chỉ", "Ngày lập");
    final HoKhauService hoKhauService = new HoKhauService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        comboBoxTimKiemHoKhauCanBo.getItems().addAll(listTimKiem);
        comboBoxTimKiemHoKhauCanBo.getSelectionModel().selectFirst();
        tableViewTatCaHoKhauCanBo.setEditable(true);
        ComponentVisibility.change(datePickerTu, false);
        ComponentVisibility.change(datePickerDen, false);
        tableColumnMaHoHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("maHoKhau"));
        tableColumnHotenHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("chuHo"));
        tableColumnDiaChiHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("diaChi"));
        tableColumnNgayLapHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, Date>("ngayLap"));
        tableColumnHotenHoKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnDiaChiHoKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnHotenHoKhauCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HoKhauModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<HoKhauModel, String> event) {
                HoKhauModel hoKhauModel = event.getRowValue();
                hoKhauModel.setChuHo(event.getNewValue());
                updateHoKhauCanBo(hoKhauModel);
            }
        });
        tableColumnDiaChiHoKhauCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HoKhauModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<HoKhauModel, String> event) {
                HoKhauModel hoKhauModel = event.getRowValue();
                hoKhauModel.setDiaChi(event.getNewValue());
                updateHoKhauCanBo(hoKhauModel);
            }
        });
        displayAllHoKhauCanBo();
    }

    @Override
    public void onPressedButtonHoKhauCanBo(MouseEvent e) throws IOException {
    }

    public void onSelectionComboBoxTimKiemHoKhauCanBo(ActionEvent e) {
        String truongTimKiem = String.valueOf(comboBoxTimKiemHoKhauCanBo.getValue());
        if (truongTimKiem.equals("Ngày lập")) {
            ComponentVisibility.change(textFieldLocThongTinHoKhauCanBo, false);
            ComponentVisibility.change(datePickerTu, true);
            ComponentVisibility.change(datePickerDen, true);
        } else {
            ComponentVisibility.change(textFieldLocThongTinHoKhauCanBo, true);
            ComponentVisibility.change(datePickerTu, false);
            ComponentVisibility.change(datePickerDen, false);
        }
    }

    public void onEnterPressedTrongOTimKiemHoKhauCanBo(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            locThongTinHoKhauCanBo();
        }
    }

    public void onDeletePressedTrongBangHoKhauCanBo(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.DELETE) {
            xoaHoKhauCanBo();
        }
    }

    public void onPressedButtonLocThongTinHoKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinHoKhauCanBo();
        }
    }

    public void onPressedButtonThemHoKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "ThemMoiHoKhauCanBoView.fxml");
        }
    }

    public void onPressedButtonTachHoKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "TachHoKhauCanBoView.fxml");
        }
    }

    public void onPressedButtonXoaHoKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaHoKhauCanBo();
        }
    }

    private void displayAllHoKhauCanBo() {
        ObservableList<HoKhauModel> hoKhauModelObservableList = hoKhauService.getAllHoKhau();
        tableViewTatCaHoKhauCanBo.setItems(hoKhauModelObservableList);
    }

    private void updateHoKhauCanBo(HoKhauModel hoKhauModel) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        if (hoKhauService.updateHoKhau(hoKhauModel)) {
            alert.setHeaderText("Sửa hộ khẩu thành công");
        } else {
            alert.setHeaderText("Sửa hộ khẩu không thành công");
        }
        if (alert.showAndWait().get() == ButtonType.OK) {
            displayAllHoKhauCanBo();
        }
    }

    private void xoaHoKhauCanBo() {
        HoKhauModel hoKhauModel = tableViewTatCaHoKhauCanBo.getSelectionModel().getSelectedItem();
        if (hoKhauModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn hộ khẩu muốn xóa");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Bạn chắc chắn muốn xóa hộ khẩu?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Thông báo");
                if (hoKhauService.deleteHoKhau(hoKhauModel)) {
                    info.setHeaderText("Xóa thành công!");
                } else {
                    info.setHeaderText("Xóa không thành công!");
                }
                info.showAndWait();
                displayAllHoKhauCanBo();
            }
        }
    }

    private void locThongTinHoKhauCanBo() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemHoKhauCanBo.getValue());
        String cauHoi = textFieldLocThongTinHoKhauCanBo.getText();
        ObservableList<HoKhauModel> hoKhauModelObservableList = FXCollections.observableArrayList();
        if (dieuKienKiemTra.equals("Địa chỉ")) {
            hoKhauModelObservableList = hoKhauService.getHoKhauByDiaChi(cauHoi);
        } else if (dieuKienKiemTra.equals("Tên chủ hộ")) {
            hoKhauModelObservableList = hoKhauService.getHoKhauByChuHo(cauHoi);
        } else if (dieuKienKiemTra.equals("Ngày lập")) {
            hoKhauModelObservableList = hoKhauService.getHoKhauByNgaySinhBetween(
                    Date.from(datePickerTu.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(datePickerDen.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        } else {
            Optional<HoKhauModel> hoKhauModel = hoKhauService.getHoKhauByMaHoKhau(cauHoi);
            if (hoKhauModel.isPresent()) {
                hoKhauModelObservableList.add(hoKhauModel.get());
            }
        }
        tableViewTatCaHoKhauCanBo.setItems(hoKhauModelObservableList);
    }
}
