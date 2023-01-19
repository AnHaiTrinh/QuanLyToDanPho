package com.se07.controller.controllers;

import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.NhanKhauService;
import com.se07.model.models.HoKhauModel;
import com.se07.model.models.NhanKhauModel;
import com.se07.util.MyDateStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.converter.DateStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerNhanKhauView extends ControllerCanBoView {
    @FXML
    TableView<NhanKhauModel> tableViewNhanKhauAdmin;
    @FXML
    TableColumn<NhanKhauModel, String> tableComlumIDNhanKhauCanBo, tableComlumIDHoKhauNhanKhauCanBo,
            tableComlumHoTenNhanKhauCanBo, tableComlumBietDanhNhanKhauCanBo, tableComlumGioiTinhNhanKhauCanBo,
            tableComlumTonGiaoNhanKhauCanBo, tableComlumTinhTrangNhanKhauCanBo;
    @FXML
    TableColumn<NhanKhauModel, Date> tableComlumNgaySinhNhanKhauCanBo;
    @FXML
    ComboBox comboBoxTimKiemHoKhauCanBo;
    @FXML
    TextField textFieldLocThongTinNhanKhauCanBo;
    final private String[] listTimKiem = {"Mã hộ khẩu", "Họ tên", "Biệt danh", "Tình trạng", "Mã nhân khẩu"};

    final private ObservableList<String> listGioiTinh = FXCollections.observableArrayList("Nam", "Nữ");

    final private ObservableList<String> listTinhTrang =
            FXCollections.observableArrayList("Chờ xác nhận", "Đã xác nhận", "Đã từ chối");
    final NhanKhauService nhanKhauService = new NhanKhauService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        comboBoxTimKiemHoKhauCanBo.getItems().addAll(listTimKiem);
        comboBoxTimKiemHoKhauCanBo.getSelectionModel().selectFirst();
        tableViewNhanKhauAdmin.setEditable(true);
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

    @Override
    public void onPressedButtonNhanKhauCanBo(MouseEvent e) {
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

    public void displayAllNhanKhauCanBo() {
        ObservableList<NhanKhauModel> hoKhauModelObservableList = nhanKhauService.getAllNhanKhau();
        tableViewNhanKhauAdmin.setItems(hoKhauModelObservableList);
    }

    public void tuChoiNhanKhauCanBo() {
        NhanKhauModel nhanKhauModel = tableViewNhanKhauAdmin.getSelectionModel().getSelectedItem();
        if (nhanKhauModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn nhân khẩu muốn từ chối");
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

    public void xacNhanNhanKhauCanBo() {
        NhanKhauModel nhanKhauModel = tableViewNhanKhauAdmin.getSelectionModel().getSelectedItem();
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

    public void xoaNhanKhauCanBo() {
        NhanKhauModel nhanKhauModel = tableViewNhanKhauAdmin.getSelectionModel().getSelectedItem();
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
                nhanKhauService.deleteNhanKhau(nhanKhauModel);
                displayAllNhanKhauCanBo();
            }
        }
    }

    public void locThongTinNhanKhauCanBo() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemHoKhauCanBo.getValue());
        String cauHoi = textFieldLocThongTinNhanKhauCanBo.getText();
        ObservableList<NhanKhauModel> nhanKhauModelObservableList = FXCollections.observableArrayList();
        if (dieuKienKiemTra.equals("Mã hộ khẩu")) {
            nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauTrongHoKhau(cauHoi);
        } else if (dieuKienKiemTra.equals("Họ tên")) {
            nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByTen(cauHoi);
        } else if (dieuKienKiemTra.equals("Biệt danh")) {
            nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByBietDanh(cauHoi);
        } else if (dieuKienKiemTra.equals("Tình trạng")) {
            nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByTinhTrang(cauHoi);
        } else {
            Optional<NhanKhauModel> nhanKhauModel = nhanKhauService.getNhanKhauByMaNhanKhau(cauHoi);
            if (nhanKhauModel.isPresent()) {
                nhanKhauModelObservableList.add(nhanKhauModel.get());
            }
        }
        tableViewNhanKhauAdmin.setItems(nhanKhauModelObservableList);
    }

    public void updateNhanKhauCanBo(NhanKhauModel nhanKhauModel) {
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
