package com.se07.controller.controllers;

import com.se07.controller.services.NhanKhauService;
import com.se07.controller.services.TamVangService;
import com.se07.model.models.NhanKhauModel;
import com.se07.model.models.TamVangDisplayModel;
import com.se07.model.models.TamVangModel;
import com.se07.util.ComponentVisibility;
import com.se07.util.MyDateStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
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
    ComboBox comboBoxTimKiemTamVangCanBo, comboBoxTinhTrangTamVangCanBo;
    @FXML
    TextField textFieldLocThongTinTamVangCanBo;
    @FXML
    DatePicker datePickerTu, datePickerDen;

    ObservableList<String> listTimKiem = FXCollections.observableArrayList(
            "Mã nhân khẩu", "Họ tên", "Nơi tạm vắng", "Ngày", "Tình trạng");
    final private ObservableList<String> listTinhTrang =
            FXCollections.observableArrayList("Chờ xác nhận", "Đã xác nhận", "Đã từ chối");
    final private TamVangService tamVangService = new TamVangService();
    final private NhanKhauService nhanKhauService = new NhanKhauService();

    LocalDate today = LocalDate.now();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        comboBoxTimKiemTamVangCanBo.getItems().addAll(listTimKiem);
        comboBoxTimKiemTamVangCanBo.getSelectionModel().selectFirst();
        comboBoxTinhTrangTamVangCanBo.getItems().addAll(listTinhTrang);
        comboBoxTinhTrangTamVangCanBo.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxTinhTrangTamVangCanBo, false);
        ComponentVisibility.change(datePickerTu, false);
        ComponentVisibility.change(datePickerDen, false);
        tableViewTamVangCanBo.setEditable(true);
        tableColumnMaNhanKhauTamVangCanBo.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("maNhanKhau"));
        tableColumnHoTenTamVangCanBo.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("hoTen"));
        tableColumnNoiTamVangCanBo.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("noiTamVang"));
        tableColumnTuNgayTamVangcanBo.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, Date>("tuNgay"));
        tableColumnDenNgayTamVangcanBo.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, Date>("denNgay"));
        tableColumnLyDoTamVangCanBo.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("lyDo"));
        tableColumnTinhTrangTamVangCanBo.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("tinhTrang"));
        tableColumnMaNhanKhauTamVangCanBo.setCellFactory(t -> new ComboBoxTableCell<>(nhanKhauService.getAllMaNhanKhau()));
        tableColumnMaNhanKhauTamVangCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TamVangDisplayModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TamVangDisplayModel, String> event) {
                TamVangDisplayModel tamVangDisplayModel = event.getRowValue();
                tamVangDisplayModel.setMaNhanKhau(event.getNewValue());
                updateTamVangCanBo(tamVangDisplayModel);
            }
        });
        tableColumnNoiTamVangCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnNoiTamVangCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TamVangDisplayModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TamVangDisplayModel, String> event) {
                TamVangDisplayModel tamVangDisplayModel = event.getRowValue();
                tamVangDisplayModel.setNoiTamVang(event.getNewValue());
                updateTamVangCanBo(tamVangDisplayModel);
            }
        });
        tableColumnLyDoTamVangCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnLyDoTamVangCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TamVangDisplayModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TamVangDisplayModel, String> event) {
                TamVangDisplayModel tamVangDisplayModel = event.getRowValue();
                tamVangDisplayModel.setLyDo(event.getNewValue());
                updateTamVangCanBo(tamVangDisplayModel);
            }
        });
        tableColumnTuNgayTamVangcanBo.setCellFactory(TextFieldTableCell.forTableColumn(new MyDateStringConverter("yyyy-MM-dd")));
        tableColumnTuNgayTamVangcanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TamVangDisplayModel, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TamVangDisplayModel, Date> event) {
                TamVangDisplayModel tamVangDisplayModel = event.getRowValue();
                Date tuNgayMoi = event.getNewValue();
                if (tuNgayMoi != null) {
                    tamVangDisplayModel.setTuNgay(tuNgayMoi);
                    updateTamVangCanBo(tamVangDisplayModel);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập ngày hợp lệ đúng định dạng năm-tháng-ngày");
                    alert.showAndWait();
                    displayAlltamVangCanBo();
                }
            }
        });
        tableColumnDenNgayTamVangcanBo.setCellFactory(TextFieldTableCell.forTableColumn(new MyDateStringConverter("yyyy-MM-dd")));
        tableColumnDenNgayTamVangcanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TamVangDisplayModel, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TamVangDisplayModel, Date> event) {
                TamVangDisplayModel tamVangDisplayModel = event.getRowValue();
                Date denNgayMoi = event.getNewValue();
                if (denNgayMoi != null) {
                    tamVangDisplayModel.setTuNgay(denNgayMoi);
                    updateTamVangCanBo(tamVangDisplayModel);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập ngày hợp lệ đúng định dạng năm-tháng-ngày");
                    alert.showAndWait();
                    displayAlltamVangCanBo();
                }
            }
        });
        tableColumnTinhTrangTamVangCanBo.setCellFactory(t -> new ComboBoxTableCell<>(listTinhTrang));
        tableColumnTinhTrangTamVangCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TamVangDisplayModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TamVangDisplayModel, String> event) {
                TamVangDisplayModel tamVangDisplayModel = event.getRowValue();
                tamVangDisplayModel.setTinhTrang(event.getNewValue());
                updateTamVangCanBo(tamVangDisplayModel);
            }
        });
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
                        "NhanKhauCanBoView.fxml");
            }
        }
    }

    public void onSelectionComboBoxTimKiemTamVangCanBo(ActionEvent e) {
        String truongTimKiem = String.valueOf(comboBoxTimKiemTamVangCanBo.getValue());
        if (truongTimKiem.equals("Ngày")) {
            ComponentVisibility.change(textFieldLocThongTinTamVangCanBo, false);
            ComponentVisibility.change(comboBoxTinhTrangTamVangCanBo, false);
            ComponentVisibility.change(datePickerTu, true);
            ComponentVisibility.change(datePickerDen, true);
        } else if (truongTimKiem.equals("Tình trạng")) {
            ComponentVisibility.change(textFieldLocThongTinTamVangCanBo, false);
            ComponentVisibility.change(comboBoxTinhTrangTamVangCanBo, true);
            ComponentVisibility.change(datePickerTu, false);
            ComponentVisibility.change(datePickerDen, false);
        } else {
            ComponentVisibility.change(textFieldLocThongTinTamVangCanBo, true);
            ComponentVisibility.change(comboBoxTinhTrangTamVangCanBo, false);
            ComponentVisibility.change(datePickerTu, false);
            ComponentVisibility.change(datePickerDen, false);
        }
    }

    private void displayAlltamVangCanBo() {
        ObservableList<TamVangDisplayModel> tamVangDisplayModelObservableList = tamVangService.getAllTamVangDisplay();
        tableViewTamVangCanBo.setItems(tamVangDisplayModelObservableList);
    }

    private void xacNhanTamVangCanBo() {
        TamVangDisplayModel tamVangDisplayModel = tableViewTamVangCanBo.getSelectionModel().getSelectedItem();
        if (tamVangDisplayModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn xác nhận");
            alert.showAndWait();
        } else if (tamVangDisplayModel.getTinhTrang() == "Đã xác nhận") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trường hợp đã được xác nhận");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xác nhận trường hợp này?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                tamVangDisplayModel.setTinhTrang("Đã xác nhận");
                TamVangModel tamVangModel = tamVangService.convertDisplayModelToModel(tamVangDisplayModel);
                tamVangService.updateTamVang(tamVangModel);
                displayAlltamVangCanBo();
            }
        }
    }

    private void tuChoiTamVangcanBo() {
        TamVangDisplayModel tamVangDisplayModel = tableViewTamVangCanBo.getSelectionModel().getSelectedItem();
        if (tamVangDisplayModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn từ chối");
            alert.showAndWait();
        } else if (tamVangDisplayModel.getTinhTrang().equals("Đã từ chối")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Nhân khẩu đã bị từ chối");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn từ chối nhân khẩu này?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                tamVangDisplayModel.setTinhTrang("Đã từ chối");
                TamVangModel tamVangModel = tamVangService.convertDisplayModelToModel(tamVangDisplayModel);
                tamVangService.updateTamVang(tamVangModel);
                displayAlltamVangCanBo();
            }
        }
    }

    private void xoaTamVangCanBo() {
        TamVangDisplayModel tamVangDisplayModel = tableViewTamVangCanBo.getSelectionModel().getSelectedItem();
        if (tamVangDisplayModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn xóa");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xóa trường hợp này!");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Thông báo");
                if (tamVangService.deleteTamVang(tamVangDisplayModel)) {
                    info.setHeaderText("Xóa thành công!");
                } else {
                    info.setHeaderText("Xóa không thành công!");
                }
                info.showAndWait();
                displayAlltamVangCanBo();
            }
        }
    }

    private void locThongTinTamVangCanBo() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemTamVangCanBo.getValue());
        String cauHoi = textFieldLocThongTinTamVangCanBo.getText();
        ObservableList<TamVangDisplayModel> tamVangDisplayModelObservableList = FXCollections.observableArrayList();
        switch (dieuKienKiemTra) {
            case "Mã nhân khẩu":
                tamVangDisplayModelObservableList = tamVangService.getTamVangByMaNhanKhau(cauHoi);
                break;
            case "Họ tên":
                tamVangDisplayModelObservableList = tamVangService.getTamVangByHoTen(cauHoi);
                break;
            case "Nơi tạm vắng":
                tamVangDisplayModelObservableList = tamVangService.getTamVangByNoiTamVang(cauHoi);
            case "Ngày":
                tamVangDisplayModelObservableList = tamVangService.getTamVangByNgayBetween(
                        Date.from(datePickerTu.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(datePickerDen.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                break;
            case "Tình trạng":
                tamVangDisplayModelObservableList = tamVangService.getTamVangBytinhTrang(
                        String.valueOf(comboBoxTinhTrangTamVangCanBo.getValue())
                );
                break;
        }
        tableViewTamVangCanBo.setItems(tamVangDisplayModelObservableList);
    }

    private void updateTamVangCanBo(TamVangDisplayModel tamVangDisplayModel) {
        TamVangModel tamVangModel = tamVangService.convertDisplayModelToModel(tamVangDisplayModel);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        if (tamVangService.updateTamVang(tamVangModel)) {
            alert.setHeaderText("Sửa trường hợp tạm vắng thành công");
        } else {
            alert.setHeaderText("Sửa trường hợp tạm vắng không thành công");
        }
        if (alert.showAndWait().get() == ButtonType.OK) {
            displayAlltamVangCanBo();
        }
    }
}
