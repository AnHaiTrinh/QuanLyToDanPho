package com.se07.controller.controllers.controllershogiadinh;

import com.se07.controller.services.TamTruService;
import com.se07.model.models.*;
import com.se07.model.models.TamTruDisplayModel;
import com.se07.util.ComponentVisibility;
import com.se07.util.MyDateStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerTamTruHoGiaDinhView extends ControllerHoGiaDinhView implements Initializable {
    @FXML
    Button buttonLocThongTinTamTruHoGiaDinh, buttonThemMoiTamTruHoGiaDinh,
            buttonXoaTamTruHoGiaDinh, buttonThoatTamTruHoGiaDinh;
    @FXML
    TableView<TamTruDisplayModel> tableViewTamTruHoGiaDinh;
    @FXML
    TableColumn<TamTruDisplayModel, String> tableColumnCCCDTamTruHoGiaDinh, tableColumnHoTenTamTruHoGiaDinh,
            tableColumnNoiTamTruHoGiaDinh, tableColumnLyDoTamTruHoGiaDinh, tableColumnTinhTrangTamTruHoGiaDinh;
    @FXML
    TableColumn<TamTruDisplayModel, Date> tableColumnTuNgayTamTruHoGiaDinh, tableColumnDenNgayTamTruHoGiaDinh;
    @FXML
    ComboBox comboBoxTimKiemTamTruHoGiaDinh, comboBoxTinhTrangTamTruHoGiaDinh;
    @FXML
    TextField textFieldLocThongTinTamTruHoGiaDinh;

    final ObservableList<String> listTimKiem = FXCollections.observableArrayList(
            "Họ tên", "CCCD", "Tình trạng");
    final private ObservableList<String> listTinhTrang =
            FXCollections.observableArrayList("Chờ xác nhận", "Đã xác nhận", "Đã từ chối", "Chờ xóa");
    final private TamTruService tamTruService = new TamTruService();
    private MyDateStringConverter dateStringConverter = new MyDateStringConverter("yyyy-MM-dd");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        tableColumnCCCDTamTruHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("CCCD"));
        tableColumnHoTenTamTruHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("hoTen"));
        tableColumnNoiTamTruHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("noiTamTru"));
        tableColumnTuNgayTamTruHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, Date>("tuNgay"));
        tableColumnDenNgayTamTruHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, Date>("denNgay"));
        tableColumnLyDoTamTruHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("lyDo"));
        tableColumnTinhTrangTamTruHoGiaDinh.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("tinhTrang"));
        comboBoxTimKiemTamTruHoGiaDinh.getItems().addAll(listTimKiem);
        comboBoxTimKiemTamTruHoGiaDinh.getSelectionModel().selectFirst();
        comboBoxTinhTrangTamTruHoGiaDinh.getItems().addAll(listTinhTrang);
        comboBoxTinhTrangTamTruHoGiaDinh.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxTinhTrangTamTruHoGiaDinh, false);

        tableViewTamTruHoGiaDinh.setEditable(true);
        tableColumnCCCDTamTruHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnHoTenTamTruHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnLyDoTamTruHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnTuNgayTamTruHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn(dateStringConverter));
        tableColumnDenNgayTamTruHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn(dateStringConverter));

        tableViewTamTruHoGiaDinh.setItems(tamTruService.getTamTruByMaChuHo(maHoKhauDangNhap));
    }

    public void onPressedButtonLocThongTinTamTruHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinTamTruHoGiaDinh();
        }
    }

    public void onPressedButtonThemMoiTamTruHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "DangKyTamTruNhanKhauHoGiaDinhView.fxml");
        }
    }

    public void onDeletePressedTrongBangTamTruHoGiaDinh(KeyEvent e) {
        if (e.getCode() == KeyCode.DELETE) {
            xoaTamTruHoGiaDinh();
        }
    }

    public void onPressedButtonXoaTamTruHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaTamTruHoGiaDinh();
        }
    }

    public void onPressedButtonThoatTamTruHoGiaDinh(MouseEvent e) throws IOException {
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

    public void onSelectionComboBoxTimKiemTamTruHoGiaDinh(ActionEvent e) {
        String truongTimKiem = String.valueOf(comboBoxTimKiemTamTruHoGiaDinh.getValue());
        if (truongTimKiem.equals("Ngày")) {
            ComponentVisibility.change(textFieldLocThongTinTamTruHoGiaDinh, false);
            ComponentVisibility.change(comboBoxTinhTrangTamTruHoGiaDinh, false);

        } else if (truongTimKiem.equals("Tình trạng")) {
            ComponentVisibility.change(textFieldLocThongTinTamTruHoGiaDinh, false);
            ComponentVisibility.change(comboBoxTinhTrangTamTruHoGiaDinh, true);

        } else {
            ComponentVisibility.change(textFieldLocThongTinTamTruHoGiaDinh, true);
            ComponentVisibility.change(comboBoxTinhTrangTamTruHoGiaDinh, false);
        }
    }

    private void xoaTamTruHoGiaDinh() {
        TamTruDisplayModel tamTruDisplayModel = tableViewTamTruHoGiaDinh.getSelectionModel().getSelectedItem();
        if (tamTruDisplayModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn xóa");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xóa trường hợp này!");
            if (alert.showAndWait().get() == ButtonType.OK) {
                tamTruDisplayModel.setTinhTrang("Chờ xóa");
                updateTamTruHoGiaDinh(tamTruDisplayModel);
            }
        }
    }

    private void locThongTinTamTruHoGiaDinh() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemTamTruHoGiaDinh.getValue());
        String cauHoi = textFieldLocThongTinTamTruHoGiaDinh.getText();
        ObservableList<TamTruDisplayModel> TamTruDisplayModelObservableList = FXCollections.observableArrayList();
        switch (dieuKienKiemTra) {
            case "CCCD":
                TamTruDisplayModelObservableList = tamTruService.getDisplayTamTruByCCCDandHoKhau(cauHoi, maHoKhauDangNhap);
                break;
            case "Họ tên":
                TamTruDisplayModelObservableList = tamTruService.getDisplayTamTruByHoTenAndMaHoKhau(cauHoi, maHoKhauDangNhap);
                break;
//            case "Nơi tạm vắng":
//                TamTruDisplayModelObservableList = tamTruService.getDisplayTamTruByNoiTamTruAndMaHoKhau(cauHoi, maHoKhauDangNhap);
            case "Tình trạng":
                TamTruDisplayModelObservableList = tamTruService.getDisplayTamTruByTinhTrangAndMaHoKhau(
                        String.valueOf(comboBoxTinhTrangTamTruHoGiaDinh.getValue()), maHoKhauDangNhap
                );
                break;
        }
        tableViewTamTruHoGiaDinh.setItems(TamTruDisplayModelObservableList);
    }

    public void handleOnEditCommit(TableColumn.CellEditEvent<TamTruDisplayModel, ?> event) {
        int column = event.getTablePosition().getColumn();
        TamTruDisplayModel tamTruDisplayModel = event.getRowValue();
        switch (column) {
            case 0:
                tamTruDisplayModel.setCCCD((String) event.getNewValue());
                tamTruDisplayModel.setTinhTrang(tinhTrang);
                break;
            case 1:
                tamTruDisplayModel.setHoTen((String) event.getNewValue());
                break;
            case 3:
                Date tuNgayMoi = (Date) event.getNewValue();
                if (tuNgayMoi != null) {
                    tamTruDisplayModel.setTuNgay(tuNgayMoi);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập ngày sinh hợp lệ đúng định dạng năm-tháng-ngày");
                    alert.showAndWait();
                    tamTruDisplayModel.setTuNgay((Date) event.getOldValue());
                    tableViewTamTruHoGiaDinh.refresh();
                    return;
                }
                break;
            case 4:
                Date denNgayMoi = (Date) event.getNewValue();
                if (denNgayMoi != null) {
                    tamTruDisplayModel.setDenNgay(denNgayMoi);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập ngày sinh hợp lệ đúng định dạng năm-tháng-ngày");
                    alert.showAndWait();
                    tamTruDisplayModel.setDenNgay((Date) event.getOldValue());
                    tableViewTamTruHoGiaDinh.refresh();
                    return;
                }
                break;
            case 5:
                tamTruDisplayModel.setLyDo((String) event.getNewValue());
                break;
        }
        tamTruDisplayModel.setTinhTrang(tinhTrang);
        updateTamTruHoGiaDinh(tamTruDisplayModel);
    }

    public void handleOnEditCancel(TableColumn.CellEditEvent<TamTruDisplayModel, ?> event) {
        int column = event.getTablePosition().getColumn();
        TamTruDisplayModel tamTruDisplayModel = event.getRowValue();
        switch (column) {
            case 0:
                tamTruDisplayModel.setCCCD((String) event.getOldValue());
                break;
            case 1:
                tamTruDisplayModel.setHoTen((String) event.getOldValue());
                break;
            case 3:
                tamTruDisplayModel.setTuNgay((Date) event.getOldValue());
                break;
            case 4:
                tamTruDisplayModel.setDenNgay((Date) event.getOldValue());
                break;
            case 5:
                tamTruDisplayModel.setLyDo((String) event.getOldValue());
                break;
        }
    }

    private void updateTamTruHoGiaDinh(TamTruDisplayModel tamTruDisplayModel) {
        TamTruModel tamTruModel = tamTruService.convertDisplayModelToModel(tamTruDisplayModel);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        if (tamTruService.updateTamTru(tamTruModel)) {
            alert.setHeaderText("Gửi yêu cầu thành công");
        } else {
            alert.setHeaderText("Gửi yêu cầu không thành công");
        }
        if (alert.showAndWait().get() == ButtonType.OK) {
            tableViewTamTruHoGiaDinh.refresh();
        }
    }
}