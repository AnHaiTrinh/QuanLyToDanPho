package com.se07.controller.controllers.controllershogiadinh;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerTamVangHoGiaDinhView extends ControllerHoGiaDinhView implements Initializable {
    @FXML
    Button buttonLocThongTinTamVangHoGiaDinh, buttonThemMoiTamVangHoGiaDinh,
            buttonXoaTamVangHoGiaDinh, buttonThoatTamVangHoGiaDinh;
    @FXML
    TableView<TamVangDisplayModel> tableViewTamVangHoGiaDinh;
    @FXML
    TableColumn<TamVangDisplayModel, String> tableColumnMaNhanKhauTamVangHoGiaDinh, tableColumnHoTenTamVangHoGiaDinh,
            tableColumnNoiTamVangHoGiaDinh, tableColumnLyDoTamVangHoGiaDinh, tableColumnTinhTrangTamVangHoGiaDinh;
    @FXML
    TableColumn<TamVangDisplayModel, Date> tableColumnTuNgayTamVangHoGiaDinh, tableColumnDenNgayTamVangHoGiaDinh;
    @FXML
    ComboBox comboBoxTimKiemTamVangHoGiaDinh, comboBoxTinhTrangTamVangHoGiaDinh;
    @FXML
    TextField textFieldLocThongTinTamVangHoGiaDinh;

    final ObservableList<String> listTimKiem = FXCollections.observableArrayList(
            "Mã nhân khẩu", "Họ tên", "Nơi tạm vắng", "Tình trạng");
    final private ObservableList<String> listTinhTrang =
            FXCollections.observableArrayList("Chờ xác nhận", "Đã xác nhận", "Đã từ chối", "Chờ xóa");
    final private TamVangService tamVangService = new TamVangService();
    final private NhanKhauService nhanKhauService = new NhanKhauService();
    final ObservableList<String> listMaNhanKhau = nhanKhauService.getAllMaNhanKhau();

    final private MyDateStringConverter dateStringConverter = new MyDateStringConverter("yyyy-MM-dd");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        tableColumnMaNhanKhauTamVangHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("maNhanKhau"));
        tableColumnHoTenTamVangHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("hoTen"));
        tableColumnNoiTamVangHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("noiTamVang"));
        tableColumnTuNgayTamVangHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, Date>("tuNgay"));
        tableColumnDenNgayTamVangHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, Date>("denNgay"));
        tableColumnLyDoTamVangHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("lyDo"));
        tableColumnTinhTrangTamVangHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamVangDisplayModel, String>("tinhTrang"));

        comboBoxTimKiemTamVangHoGiaDinh.getItems().addAll(listTimKiem);
        comboBoxTimKiemTamVangHoGiaDinh.getSelectionModel().selectFirst();
        comboBoxTinhTrangTamVangHoGiaDinh.getItems().addAll(listTinhTrang);
        comboBoxTinhTrangTamVangHoGiaDinh.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxTinhTrangTamVangHoGiaDinh, false);

        tableViewTamVangHoGiaDinh.setEditable(true);

        tableColumnMaNhanKhauTamVangHoGiaDinh.setCellFactory(t -> new ComboBoxTableCell(listMaNhanKhau));
        tableColumnNoiTamVangHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnLyDoTamVangHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnTuNgayTamVangHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn(dateStringConverter));
        tableColumnDenNgayTamVangHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn(dateStringConverter));

        tableViewTamVangHoGiaDinh.setItems(tamVangService.getAllTamVangDisplayByHoKhau(maHoKhauDangNhap));
    }

    public void onPressedButtonLocThongTinTamVangHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinTamVangHoGiaDinh();
        }
    }

    public void onPressedButtonThemMoiTamVangHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "DangKyTamVangHoGiaDinhView.fxml");
        }
    }

    public void onDeletePressedTrongBangTamVangHoGiaDinh(KeyEvent e) {
        if (e.getCode() == KeyCode.DELETE) {
            xoaTamVangHoGiaDinh();
        }
    }

    public void onPressedButtonXoaTamVangHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaTamVangHoGiaDinh();
        }
    }

    public void onPressedButtonThoatTamVangHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Bạn chắc chắn muốn thoát?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(),
                        "NhanKhauHoGiaDinhView.fxml");
            }
        }
    }

    public void onSelectionComboBoxTimKiemTamVangHoGiaDinh(ActionEvent e) {
        String truongTimKiem = String.valueOf(comboBoxTimKiemTamVangHoGiaDinh.getValue());
        if (truongTimKiem.equals("Ngày")) {
            ComponentVisibility.change(textFieldLocThongTinTamVangHoGiaDinh, false);
            ComponentVisibility.change(comboBoxTinhTrangTamVangHoGiaDinh, false);

        } else if (truongTimKiem.equals("Tình trạng")) {
            ComponentVisibility.change(textFieldLocThongTinTamVangHoGiaDinh, false);
            ComponentVisibility.change(comboBoxTinhTrangTamVangHoGiaDinh, true);

        } else {
            ComponentVisibility.change(textFieldLocThongTinTamVangHoGiaDinh, true);
            ComponentVisibility.change(comboBoxTinhTrangTamVangHoGiaDinh, false);
        }
    }

    private void xoaTamVangHoGiaDinh() {
        TamVangDisplayModel tamVangDisplayModel = tableViewTamVangHoGiaDinh.getSelectionModel().getSelectedItem();
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
                tamVangDisplayModel.setTinhTrang("Chờ xóa");
                updateTamVangHoGiaDinh(tamVangDisplayModel);
            }
        }
    }

    private void locThongTinTamVangHoGiaDinh() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemTamVangHoGiaDinh.getValue());
        String cauHoi = textFieldLocThongTinTamVangHoGiaDinh.getText();
        ObservableList<TamVangDisplayModel> tamVangDisplayModelObservableList = FXCollections.observableArrayList();
        switch (dieuKienKiemTra) {
            case "Mã nhân khẩu":
                tamVangDisplayModelObservableList = tamVangService.getTamVangByMaNhanKhauAndHoKhau(cauHoi, maHoKhauDangNhap);
                break;
            case "Họ tên":
                tamVangDisplayModelObservableList = tamVangService.getTamVangByHoTenAndHoKhau(cauHoi, maHoKhauDangNhap);
                break;
            case "Nơi tạm vắng":
                tamVangDisplayModelObservableList = tamVangService.getTamVangByNoiTamVangAndHoKhau(cauHoi, maHoKhauDangNhap);
                break;
            case "Tình trạng":
                tamVangDisplayModelObservableList = tamVangService.getTamVangBytinhTrangAndHoKhau(
                        String.valueOf(comboBoxTinhTrangTamVangHoGiaDinh.getValue()), maHoKhauDangNhap);
                break;
        }
        tableViewTamVangHoGiaDinh.setItems(tamVangDisplayModelObservableList);
    }

    public void handleOnEditCommit(TableColumn.CellEditEvent<TamVangDisplayModel, ?> event) {
        int column = event.getTablePosition().getColumn();
        TamVangDisplayModel tamVangDisplayModel = event.getRowValue();
        switch (column) {
            case 0:
                tamVangDisplayModel.setMaNhanKhau((String) event.getNewValue());
                break;
            case 2:
                tamVangDisplayModel.setNoiTamVang((String) event.getNewValue());
                break;
            case 3:
                Date tuNgayMoi = (Date) event.getNewValue();
                if (tuNgayMoi != null) {
                    tamVangDisplayModel.setTuNgay(tuNgayMoi);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập ngày sinh hợp lệ đúng định dạng năm-tháng-ngày");
                    alert.showAndWait();
                    tamVangDisplayModel.setTuNgay((Date) event.getOldValue());
                    tableViewTamVangHoGiaDinh.refresh();
                    return;
                }
                break;
            case 4:
                Date denNgayMoi = (Date) event.getNewValue();
                if (denNgayMoi != null) {
                    tamVangDisplayModel.setDenNgay(denNgayMoi);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập ngày sinh hợp lệ đúng định dạng năm-tháng-ngày");
                    alert.showAndWait();
                    tamVangDisplayModel.setDenNgay((Date) event.getOldValue());
                    tableViewTamVangHoGiaDinh.refresh();
                    return;
                }
                break;
            case 5:
                tamVangDisplayModel.setLyDo((String) event.getNewValue());
                break;
        }
        tamVangDisplayModel.setTinhTrang(tinhTrang);
        updateTamVangHoGiaDinh(tamVangDisplayModel);
    }

    public void handleOnEditCancel(TableColumn.CellEditEvent<TamVangDisplayModel, ?> event) {
        int column = event.getTablePosition().getColumn();
        TamVangDisplayModel tamVangDisplayModel = event.getRowValue();
        switch (column) {
            case 0:
                tamVangDisplayModel.setMaNhanKhau((String) event.getOldValue());
                break;
            case 2:
                tamVangDisplayModel.setNoiTamVang((String) event.getOldValue());
                break;
            case 3:
                tamVangDisplayModel.setTuNgay((Date) event.getOldValue());
                break;
            case 4:
                tamVangDisplayModel.setDenNgay((Date) event.getOldValue());
                break;
            case 5:
                tamVangDisplayModel.setLyDo((String) event.getOldValue());
                break;
        }
    }

    private void updateTamVangHoGiaDinh(TamVangDisplayModel tamVangDisplayModel) {
        TamVangModel tamVangModel = tamVangService.convertDisplayModelToModel(tamVangDisplayModel);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        if (tamVangService.updateTamVang(tamVangModel)) {
            alert.setHeaderText("Gửi yêu cầu thành công");
        } else {
            alert.setHeaderText("Gửi yêu cầu không thành công");
        }
        if (alert.showAndWait().get() == ButtonType.OK) {
            tableViewTamVangHoGiaDinh.refresh();
        }
    }
}
