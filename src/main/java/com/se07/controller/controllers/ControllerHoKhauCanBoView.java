package com.se07.controller.controllers;

import com.se07.controller.services.HoKhauService;
import com.se07.model.models.HoKhauModel;
import com.se07.view.TrangChuCanBoView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerHoKhauCanBoView extends ControllerTrangChuView implements Initializable {
    @FXML
    private TableColumn<HoKhauModel, String> tableColumnMaHoHoKhauCanBo, tableColumnHotenHoKhauCanBo, tableColumnDiaChiHoKhauCanBo;
    @FXML
    private TableView<HoKhauModel> tableViewTatCaHoKhauCanBo;
    @FXML
    private ComboBox comboBoxTimKiemHoKhauCanBo;
    @FXML
    private TextField textFieldLocThongTinHoKhauCanBo;
    private String[] truong = {"Tên chủ hộ", "Mã hộ khẩu", "Địa chỉ"};
    HoKhauService hoKhauService = new HoKhauService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        comboBoxTimKiemHoKhauCanBo.getItems().addAll(truong);
        comboBoxTimKiemHoKhauCanBo.getSelectionModel().selectFirst();
        tableViewTatCaHoKhauCanBo.setEditable(true);
        tableColumnMaHoHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("maHoKhau"));
        tableColumnHotenHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("chuHo"));
        tableColumnDiaChiHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("diaChi"));
        tableColumnHotenHoKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnDiaChiHoKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnHotenHoKhauCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HoKhauModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<HoKhauModel, String> event) {
                HoKhauModel hoKhauModel = event.getRowValue();
                hoKhauModel.setChuHo(event.getNewValue());
                if (hoKhauService.updateHoKhau(hoKhauModel)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText("Sửa hộ khẩu thành công");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        displayAllHoKhauCanBo();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText("Sửa hộ khẩu không thành công");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        displayAllHoKhauCanBo();
                    }
                }
            }
        });
        tableColumnDiaChiHoKhauCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HoKhauModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<HoKhauModel, String> event) {
                HoKhauModel hoKhauModel = event.getRowValue();
                hoKhauModel.setDiaChi(event.getNewValue());
                if (hoKhauService.updateHoKhau(hoKhauModel)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText("Sửa hộ khẩu thành công");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        displayAllHoKhauCanBo();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText("Sửa hộ khẩu không thành công");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        displayAllHoKhauCanBo();
                    }
                }
            }
        });
    }

    public void setNhanNutDeleteTrongBangHoKhauCanBo(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.DELETE) {
            HoKhauModel hoKhauModel = tableViewTatCaHoKhauCanBo.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Bạn chắc chắn muốn xóa hộ khẩu?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                hoKhauService.deleteHoKhau(hoKhauModel);
                displayAllHoKhauCanBo();
            }
        }
    }

    public void setButtonLocThongTinHoKhauCanBo() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemHoKhauCanBo.getValue());
        String cauHoi = textFieldLocThongTinHoKhauCanBo.getText();
        ObservableList<HoKhauModel> hoKhauModelObservableList = FXCollections.observableArrayList();
        if (dieuKienKiemTra.equals("Địa chỉ")) {
            hoKhauModelObservableList = hoKhauService.getHoKhauByDiaChi(cauHoi);
        } else if (dieuKienKiemTra.equals("Tên chủ hộ")) {
            hoKhauModelObservableList = hoKhauService.getHoKhauByChuHo(cauHoi);
        } else {
            Optional<HoKhauModel> hoKhauModel = hoKhauService.getHoKhauByMaHoKhau(cauHoi);
            if (hoKhauModel.isPresent()) {
                hoKhauModelObservableList.add(hoKhauModel.get());
            }
        }
        tableViewTatCaHoKhauCanBo.setItems(hoKhauModelObservableList);
    }

    public void displayAllHoKhauCanBo() {
        ObservableList<HoKhauModel> hoKhauModelObservableList = hoKhauService.getAllHoKhau();
        tableViewTatCaHoKhauCanBo.setItems(hoKhauModelObservableList);
    }

    public void setButtonThemHoKhauCanBo(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("ThemHoKhauMoiCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setButtonTachHoKhauCanBo(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("TachHoKhauCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setButtonXoaHoKhauCanBo() {
        HoKhauModel hoKhauModel = tableViewTatCaHoKhauCanBo.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo!");
        alert.setHeaderText("Bạn chắc chắn muốn xóa hộ khẩu?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            hoKhauService.deleteHoKhau(hoKhauModel);
            displayAllHoKhauCanBo();
        }
    }

}
