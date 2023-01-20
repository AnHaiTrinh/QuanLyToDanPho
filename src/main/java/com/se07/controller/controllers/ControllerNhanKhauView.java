package com.se07.controller.controllers;

import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.NhanKhauService;
import com.se07.model.models.NhanKhauModel;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerNhanKhauView extends ControllerCanBoView {
    @FXML
    TableView<NhanKhauModel> tableViewNhanKhauCanBo;
    @FXML
    TableColumn<NhanKhauModel, String> tableComlumIDNhanKhauCanBo, tableComlumIDHoKhauNhanKhauCanBo,
            tableComlumHoTenNhanKhauCanBo, tableComlumBietDanhNhanKhauCanBo, tableComlumGioiTinhNhanKhauCanBo,
            tableComlumTonGiaoNhanKhauCanBo, tableComlumTinhTrangNhanKhauCanBo;
    @FXML
    TableColumn<NhanKhauModel, Date> tableComlumNgaySinhNhanKhauCanBo;
    @FXML
    ComboBox comboBoxTimKiemNhanKhauCanBo, comboBoxGioiTinhNhanKhauCanBo, comboBoxTinhTrangNhanKhauCanBo;
    @FXML
    TextField textFieldLocThongTinNhanKhauCanBo;
    @FXML
    DatePicker datePickerTu, datePickerDen;
    final private ObservableList<String> listTimKiem = FXCollections.observableArrayList(
            "Mã nhân khẩu", "Mã hộ khẩu", "Họ tên", "Biệt danh", "Ngày sinh", "Giới tính", "Tôn giáo", "Tình trạng");

    final private ObservableList<String> listGioiTinh = FXCollections.observableArrayList("Nam", "Nữ");

    final private ObservableList<String> listTinhTrang =
            FXCollections.observableArrayList("Chờ xác nhận", "Đã xác nhận", "Đã từ chối");
    final NhanKhauService nhanKhauService = new NhanKhauService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        comboBoxTimKiemNhanKhauCanBo.getItems().addAll(listTimKiem);
        comboBoxTimKiemNhanKhauCanBo.getSelectionModel().selectFirst();
        comboBoxGioiTinhNhanKhauCanBo.getItems().addAll(listGioiTinh);
        comboBoxGioiTinhNhanKhauCanBo.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxGioiTinhNhanKhauCanBo, false);
        comboBoxTinhTrangNhanKhauCanBo.getItems().addAll(listTinhTrang);
        comboBoxTinhTrangNhanKhauCanBo.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxTinhTrangNhanKhauCanBo, false);
        tableViewNhanKhauCanBo.setEditable(true);
        ComponentVisibility.change(datePickerTu, false);
        ComponentVisibility.change(datePickerDen, false);
        tableComlumIDNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("maNhanKhau"));
        tableComlumIDHoKhauNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("maHoKhau"));
        tableComlumHoTenNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("hoTen"));
        tableComlumBietDanhNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("bietDanh"));
        tableComlumNgaySinhNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, Date>("ngaySinh"));
        tableComlumGioiTinhNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("gioiTinh"));
        tableComlumTonGiaoNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("tonGiao"));
        tableComlumTinhTrangNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("tinhTrang"));
        tableComlumIDHoKhauNhanKhauCanBo.setCellFactory(t -> new ComboBoxTableCell(new HoKhauService().getAllMaHoKhau()));
        tableComlumIDHoKhauNhanKhauCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanKhauModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanKhauModel, String> event) {
                NhanKhauModel nhanKhauModel = event.getRowValue();
                nhanKhauModel.setMaHoKhau(event.getNewValue());
                updateNhanKhauCanBo(nhanKhauModel);
            }
        });
        tableComlumHoTenNhanKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableComlumHoTenNhanKhauCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanKhauModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanKhauModel, String> event) {
                NhanKhauModel nhanKhauModel = event.getRowValue();
                nhanKhauModel.setHoTen(event.getNewValue());
                updateNhanKhauCanBo(nhanKhauModel);
            }
        });
        tableComlumBietDanhNhanKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableComlumBietDanhNhanKhauCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanKhauModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanKhauModel, String> event) {
                NhanKhauModel nhanKhauModel = event.getRowValue();
                nhanKhauModel.setBietDanh(event.getNewValue());
                updateNhanKhauCanBo(nhanKhauModel);
            }
        });
        tableComlumNgaySinhNhanKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn(new MyDateStringConverter("yyyy-MM-dd")));
        tableComlumNgaySinhNhanKhauCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanKhauModel, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanKhauModel, Date> event) {
                NhanKhauModel nhanKhauModel = event.getRowValue();
                Date ngaySinhMoi = event.getNewValue();
                if (ngaySinhMoi != null) {
                    nhanKhauModel.setNgaySinh(ngaySinhMoi);
                    updateNhanKhauCanBo(nhanKhauModel);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập ngày sinh hợp lệ đúng định dạng năm-tháng-ngày");
                    alert.showAndWait();
                    displayAllNhanKhauCanBo();
                }
            }
        });
        tableComlumGioiTinhNhanKhauCanBo.setCellFactory(t -> new ComboBoxTableCell<>(listGioiTinh));
        tableComlumGioiTinhNhanKhauCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanKhauModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanKhauModel, String> event) {
                NhanKhauModel nhanKhauModel = event.getRowValue();
                nhanKhauModel.setGioiTinh(event.getNewValue());
                updateNhanKhauCanBo(nhanKhauModel);
            }
        });
        tableComlumTonGiaoNhanKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableComlumTonGiaoNhanKhauCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanKhauModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanKhauModel, String> event) {
                NhanKhauModel nhanKhauModel = event.getRowValue();
                nhanKhauModel.setTonGiao(event.getNewValue());
                updateNhanKhauCanBo(nhanKhauModel);
            }
        });
        tableComlumTinhTrangNhanKhauCanBo.setCellFactory(t -> new ComboBoxTableCell<>(listTinhTrang));
        tableComlumTinhTrangNhanKhauCanBo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanKhauModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanKhauModel, String> event) {
                NhanKhauModel nhanKhauModel = event.getRowValue();
                nhanKhauModel.setTinhTrang(event.getNewValue());
                updateNhanKhauCanBo(nhanKhauModel);
            }
        });
        displayAllNhanKhauCanBo();
    }

    public void onSelectionComboBoxTimKiemTamVangCanBo(ActionEvent e) {
        String truongTimKiem = String.valueOf(comboBoxTimKiemNhanKhauCanBo.getValue());
        if (truongTimKiem.equals("Ngày sinh")) {
            ComponentVisibility.change(textFieldLocThongTinNhanKhauCanBo, false);
            ComponentVisibility.change(comboBoxTinhTrangNhanKhauCanBo, false);
            ComponentVisibility.change(comboBoxGioiTinhNhanKhauCanBo, false);
            ComponentVisibility.change(datePickerTu, true);
            ComponentVisibility.change(datePickerDen, true);
        } else if (truongTimKiem.equals("Giới tính")) {
            ComponentVisibility.change(textFieldLocThongTinNhanKhauCanBo, false);
            ComponentVisibility.change(comboBoxTinhTrangNhanKhauCanBo, false);
            ComponentVisibility.change(comboBoxGioiTinhNhanKhauCanBo, true);
            ComponentVisibility.change(datePickerTu, false);
            ComponentVisibility.change(datePickerDen, false);
        } else if (truongTimKiem.equals("Tình trạng")) {
            ComponentVisibility.change(textFieldLocThongTinNhanKhauCanBo, false);
            ComponentVisibility.change(comboBoxTinhTrangNhanKhauCanBo, true);
            ComponentVisibility.change(comboBoxGioiTinhNhanKhauCanBo, false);
            ComponentVisibility.change(datePickerTu, false);
            ComponentVisibility.change(datePickerDen, false);
        } else {
            ComponentVisibility.change(textFieldLocThongTinNhanKhauCanBo, true);
            ComponentVisibility.change(comboBoxTinhTrangNhanKhauCanBo, false);
            ComponentVisibility.change(comboBoxGioiTinhNhanKhauCanBo, false);
            ComponentVisibility.change(datePickerTu, false);
            ComponentVisibility.change(datePickerDen, false);
        }
    }

    @Override
    public void onPressedButtonNhanKhauCanBo(MouseEvent e) {
    }

    public void onEnterPressedTrongOTimKiemNhanKhauCanBo(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            locThongTinNhanKhauCanBo();
        }
    }

    public void onPressedButtonThemMoiNhanKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "ThemMoiNhanKhauCanBoView.fxml");
        }
    }

    public void onPressedButtonTamVangCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "TamVangCanBo.fxml");
        }
    }

    public void onPressedButtonTamTruCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "TamTruCanBo.fxml");
        }
    }

    public void onPressedButtonXacNhanNhanKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xacNhanNhanKhauCanBo();
        }
    }

    public void onDeletePressedTrongBangNhanKhauCanBo(KeyEvent e) {
        if (e.getCode() == KeyCode.DELETE) {
            xoaNhanKhauCanBo();
        }
    }

    public void onPressedButtonTuChoiNhanKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            tuChoiNhanKhauCanBo();
        }
    }

    public void onPressedButtonXoaNhanKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaNhanKhauCanBo();
        }
    }

    public void onPressedButtonLocThongTinNhanKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinNhanKhauCanBo();
        }
    }

    private void displayAllNhanKhauCanBo() {
        ObservableList<NhanKhauModel> hoKhauModelObservableList = nhanKhauService.getAllNhanKhau();
        tableViewNhanKhauCanBo.setItems(hoKhauModelObservableList);
    }

    private void tuChoiNhanKhauCanBo() {
        NhanKhauModel nhanKhauModel = tableViewNhanKhauCanBo.getSelectionModel().getSelectedItem();
        if (nhanKhauModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn nhân khẩu muốn từ chối");
            alert.showAndWait();
        } else if (nhanKhauModel.getTinhTrang().equals("Đã từ chối")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Nhân khẩu đã bị từ chối");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn từ chối nhân khẩu này?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                nhanKhauModel.setTinhTrang("Đã từ chối");
                nhanKhauService.updateNhanKhau(nhanKhauModel);
                displayAllNhanKhauCanBo();
            }
        }
    }

    private void xacNhanNhanKhauCanBo() {
        NhanKhauModel nhanKhauModel = tableViewNhanKhauCanBo.getSelectionModel().getSelectedItem();
        if (nhanKhauModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn nhân khẩu muốn xác nhận");
            alert.showAndWait();
        } else if (nhanKhauModel.getTinhTrang().equals("Đã xác nhận")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Nhân khẩu đã được xác nhận");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xác nhận người này?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                nhanKhauModel.setTinhTrang("Đã xác nhận");
                nhanKhauService.updateNhanKhau(nhanKhauModel);
                displayAllNhanKhauCanBo();
            }
        }
    }

    private void xoaNhanKhauCanBo() {
        NhanKhauModel nhanKhauModel = tableViewNhanKhauCanBo.getSelectionModel().getSelectedItem();
        if (nhanKhauModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn nhân khẩu muốn xóa");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xóa người này!");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Thông báo");
                if (nhanKhauService.deleteNhanKhau(nhanKhauModel)) {
                    info.setHeaderText("Xóa thành công!");
                } else {
                    info.setHeaderText("Xóa không thành công!");
                }
                info.showAndWait();
                displayAllNhanKhauCanBo();
            }
        }
    }

    private void locThongTinNhanKhauCanBo() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemNhanKhauCanBo.getValue());
        String cauHoi = textFieldLocThongTinNhanKhauCanBo.getText();
        ObservableList<NhanKhauModel> nhanKhauModelObservableList = FXCollections.observableArrayList();
        switch (dieuKienKiemTra) {
            case "Mã nhân khẩu":
                Optional<NhanKhauModel> nhanKhauModel = nhanKhauService.getNhanKhauByMaNhanKhau(cauHoi);
                if (nhanKhauModel.isPresent()) {
                    nhanKhauModelObservableList.add(nhanKhauModel.get());
                }
                break;
            case "Mã hộ khẩu":
                nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauTrongHoKhau(cauHoi);
                break;
            case "Họ tên":
                nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByTen(cauHoi);
                break;
            case "Biệt danh":
                nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByBietDanh(cauHoi);
                break;
            case "Ngày sinh":
                nhanKhauModelObservableList = nhanKhauService.getNhanKhauByNgaySinhBetween(
                        Date.from(datePickerTu.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(datePickerDen.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                break;
            case "Giới tính":
                nhanKhauModelObservableList = nhanKhauService.getNhanKhauByGioiTinh(
                        String.valueOf(comboBoxGioiTinhNhanKhauCanBo.getValue())
                );
                break;
            case "Tôn giáo":
                nhanKhauModelObservableList = nhanKhauService.getNhanKhauByTonGiao(cauHoi);
                break;
            case "Tình trạng":
                nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByTinhTrang(
                        String.valueOf(comboBoxTinhTrangNhanKhauCanBo.getValue())
                );
                break;
        }
        tableViewNhanKhauCanBo.setItems(nhanKhauModelObservableList);
    }

    private void updateNhanKhauCanBo(NhanKhauModel nhanKhauModel) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        if (nhanKhauService.updateNhanKhau(nhanKhauModel)) {
            alert.setHeaderText("Sửa hộ khẩu thành công");
        } else {
            alert.setHeaderText("Sửa hộ khẩu không thành công");
        }
        if (alert.showAndWait().get() == ButtonType.OK) {
            displayAllNhanKhauCanBo();
        }
    }
}
