package com.se07.controller.controllers.controllerscanbo;

import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.TamTruService;
import com.se07.model.models.TamTruDisplayModel;
import com.se07.model.models.TamTruModel;
import com.se07.util.ComponentVisibility;
import com.se07.util.MyDateStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerTamTruCanBoView extends ControllerCanBoView {
    @FXML
    TableView<TamTruDisplayModel> tableViewTamTruCanBo;
    @FXML
    TableColumn<TamTruDisplayModel, String> tableComlumCCCDTamTruCanBo, tableComlumHoTenTamTruCanBo,
            tableComlumNoiTamTruCanBo, tableColumnLyDoTamTruCanBo, tableColumnTinhTrangTamTruCanBo;
    @FXML
    TableColumn<TamTruDisplayModel, Date> tableComlumTuNgayTamTruCanBo, tableComlumDenNgayTamTruCanBo;
    @FXML
    TextField textFieldLocThongTinTamTruCanBo;
    @FXML
    ComboBox comboBoxTimKiemTamTruCanBo, comboBoxTinhTrangTamTruCanBo, comboBoxNoiTamTruCanBo;
    @FXML
    DatePicker datePickerTu, datePickerDen;

    private final TamTruService tamTruService = new TamTruService();

    private final MyDateStringConverter dateStringConverter = new MyDateStringConverter("yyyy-MM-dd");

    final ObservableList<String> listTimKiem = FXCollections.observableArrayList(
            "CCCD", "Họ tên", "Nơi tạm trú", "Ngày", "Tình trạng");

    final ObservableList<String> listTinhTrang =
            FXCollections.observableArrayList("Chờ xác nhận", "Đã xác nhận", "Đã từ chối", "Chờ xóa");

    final ObservableList<String> listNoiTamTru = new HoKhauService().getAllDiaChi();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        tableComlumCCCDTamTruCanBo.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("CCCD"));
        tableComlumHoTenTamTruCanBo.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("hoTen"));
        tableComlumNoiTamTruCanBo.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("noiTamTru"));
        tableComlumTuNgayTamTruCanBo.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, Date>("tuNgay"));
        tableComlumDenNgayTamTruCanBo.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, Date>("denNgay"));
        tableColumnLyDoTamTruCanBo.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("lyDo"));
        tableColumnTinhTrangTamTruCanBo.setCellValueFactory(new PropertyValueFactory<TamTruDisplayModel, String>("tinhTrang"));

        comboBoxTimKiemTamTruCanBo.getItems().addAll(listTimKiem);
        comboBoxTimKiemTamTruCanBo.getSelectionModel().selectFirst();
        comboBoxNoiTamTruCanBo.getItems().addAll(listNoiTamTru);
        comboBoxNoiTamTruCanBo.getSelectionModel().selectFirst();
        comboBoxTinhTrangTamTruCanBo.getItems().addAll(listTinhTrang);
        comboBoxTinhTrangTamTruCanBo.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxNoiTamTruCanBo, false);
        ComponentVisibility.change(comboBoxTinhTrangTamTruCanBo, false);
        ComponentVisibility.change(datePickerTu, false);
        ComponentVisibility.change(datePickerDen, false);

        tableColumnTinhTrangTamTruCanBo.setEditable(true);
        tableComlumCCCDTamTruCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableComlumHoTenTamTruCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableComlumNoiTamTruCanBo.setCellFactory(t -> new ComboBoxTableCell<>(listNoiTamTru));
        tableComlumTuNgayTamTruCanBo.setCellFactory(TextFieldTableCell.forTableColumn(dateStringConverter));
        tableComlumDenNgayTamTruCanBo.setCellFactory(TextFieldTableCell.forTableColumn(dateStringConverter));
        tableColumnLyDoTamTruCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnTinhTrangTamTruCanBo.setCellFactory(t -> new ComboBoxTableCell<>(listTinhTrang));

        displayAllTamTruCanBo();
    }

    public void onSelectionComboBoxTimKiemTamTruCanBo(ActionEvent e) {
        ComponentVisibility.change(textFieldLocThongTinTamTruCanBo, false);
        ComponentVisibility.change(comboBoxNoiTamTruCanBo, false);
        ComponentVisibility.change(comboBoxTinhTrangTamTruCanBo, false);
        ComponentVisibility.change(datePickerTu, false);
        ComponentVisibility.change(datePickerDen, false);
        String truongTimKiem = String.valueOf(comboBoxTimKiemTamTruCanBo.getValue());
        if (truongTimKiem.equals("Ngày")) {
            ComponentVisibility.change(datePickerTu, true);
            ComponentVisibility.change(datePickerDen, true);
        } else if (truongTimKiem.equals("Tình trạng")) {
            ComponentVisibility.change(comboBoxTinhTrangTamTruCanBo, true);
        } else if (truongTimKiem.equals("Nơi tạm trú")) {
            ComponentVisibility.change(comboBoxNoiTamTruCanBo, true);
        } else {
            ComponentVisibility.change(textFieldLocThongTinTamTruCanBo, true);
        }
    }

    public void onPressedButtonLocThongTinTamTruCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinTamTruCanBo();
        }
    }

    public void onPressedButtonThemMoiTamTruCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "DangKyTamTruCanBoView.fxml");
        }
    }

    public void onPressedButtonXacNhanTamTruCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xacNhanTamTruCanBo();
        }
    }

    public void onPressedButtonTuChoiTamTruCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            tuChoiTamTruCanBo();
        }
    }

    public void onDeletePressedTrongBangTamTruCanBo(KeyEvent e) {
        if (e.getCode() == KeyCode.DELETE) {
            xoaTamTruCanBo();
        }
    }

    public void onPressedButtonXoaTamTruCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaTamTruCanBo();
        }
    }

    public void onEnterPressedTrongOTimKiemTamTruCanBo(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            locThongTinTamTruCanBo();
        }
    }

    public void handleOnEditCommit(TableColumn.CellEditEvent<TamTruDisplayModel, ?> event) {
        int column = event.getTablePosition().getColumn();
        TamTruDisplayModel tamTruDisplayModel = tableViewTamTruCanBo.getSelectionModel().getSelectedItem();
        switch (column) {
            case 0:
                tamTruDisplayModel.setCCCD((String) event.getNewValue());
                break;
            case 1:
                tamTruDisplayModel.setHoTen((String) event.getNewValue());
                break;
            case 2:
                tamTruDisplayModel.setNoiTamTru((String) event.getNewValue());
                break;
            case 3:
                Date tuNgaymoi = (Date) event.getNewValue();
                if (tuNgaymoi != null) {
                    tamTruDisplayModel.setTuNgay(tuNgaymoi);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập ngày hợp lệ đúng định dạng năm-tháng-ngày");
                    alert.showAndWait();
                    displayAllTamTruCanBo();
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
                    alert.setHeaderText("Vui lòng nhập ngày hợp lệ đúng định dạng năm-tháng-ngày");
                    alert.showAndWait();
                    displayAllTamTruCanBo();
                    return;
                }
                break;
            case 5:
                tamTruDisplayModel.setLyDo((String) event.getNewValue());
                break;
            case 6:
                tamTruDisplayModel.setTinhTrang((String) event.getNewValue());
                break;
        }
        updateTamTruCanBo(tamTruDisplayModel);
    }

    public void handleOnEditCancel(TableColumn.CellEditEvent<TamTruDisplayModel, ?> event) {
        int column = event.getTablePosition().getColumn();
        TamTruDisplayModel tamTruDisplayModel = tableViewTamTruCanBo.getSelectionModel().getSelectedItem();
        switch (column) {
            case 0:
                tamTruDisplayModel.setCCCD((String) event.getOldValue());
                break;
            case 1:
                tamTruDisplayModel.setHoTen((String) event.getOldValue());
                break;
            case 2:
                tamTruDisplayModel.setNoiTamTru((String) event.getOldValue());
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
            case 6:
                tamTruDisplayModel.setTinhTrang((String) event.getOldValue());
                break;
        }
    }

    public void onPressedButtonThoatTamTruCanBo(MouseEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "NhanKhauCanBoView.fxml");
        }
    }

    private void updateTamTruCanBo(TamTruDisplayModel tamTruDisplayModel) {
        TamTruModel tamTruModel = tamTruService.convertDisplayModelToModel(tamTruDisplayModel);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        if (tamTruService.updateTamTru(tamTruModel)) {
            alert.setHeaderText("Sửa trường hợp tạm vắng thành công");
        } else {
            alert.setHeaderText("Sửa trường hợp tạm vắng không thành công");
        }
        if (alert.showAndWait().get() == ButtonType.OK) {
            displayAllTamTruCanBo();
        }
    }

    private void locThongTinTamTruCanBo() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemTamTruCanBo.getValue());
        String cauHoi = textFieldLocThongTinTamTruCanBo.getText();
        ObservableList<TamTruDisplayModel> tamTruDisplayModelObservableList = FXCollections.observableArrayList();
        switch (dieuKienKiemTra) {
            case "CCCD":
                tamTruDisplayModelObservableList = tamTruService.getDisplayTamTruByCCCD(cauHoi);
                break;
            case "Họ tên":
                tamTruDisplayModelObservableList = tamTruService.getDisplayTamTruByHoTen(cauHoi);
                break;
            case "Nơi tạm trú":
                tamTruDisplayModelObservableList = tamTruService.getDisplayTamTruByNoiTamTru(
                        String.valueOf(comboBoxNoiTamTruCanBo.getValue()));
                break;
            case "Ngày":
                if (datePickerTu.getValue() != null && datePickerDen.getValue() != null) {
                    tamTruDisplayModelObservableList = tamTruService.getTamTruByNgayBetween(
                            Date.from(datePickerTu.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                            Date.from(datePickerDen.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
                    alert.showAndWait();
                    displayAllTamTruCanBo();
                    return;
                }
                break;
            case "Tình trạng":
                tamTruDisplayModelObservableList = tamTruService.getDisplayTamTruByTinhTrang(
                        String.valueOf(comboBoxTinhTrangTamTruCanBo.getValue()));
                break;
        }
        tableViewTamTruCanBo.setItems(tamTruDisplayModelObservableList);
    }

    private void xacNhanTamTruCanBo() {
        TamTruDisplayModel tamTruDisplayModel = tableViewTamTruCanBo.getSelectionModel().getSelectedItem();
        if (tamTruDisplayModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn xác nhận");
            alert.showAndWait();
        } else if (tamTruDisplayModel.getTinhTrang().equals("Đã xác nhận")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trường hợp đã được xác nhận");
            alert.showAndWait();
        } else if (tamTruDisplayModel.getTinhTrang().equals("Chờ xóa")) {
            xoaTamTruCanBo();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xác nhận trường hợp này?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                tamTruDisplayModel.setTinhTrang("Đã xác nhận");
                updateTamTruCanBo(tamTruDisplayModel);
            }
        }
    }

    private void tuChoiTamTruCanBo() {
        TamTruDisplayModel tamTruDisplayModel = tableViewTamTruCanBo.getSelectionModel().getSelectedItem();
        if (tamTruDisplayModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn từ chối");
            alert.showAndWait();
        } else if (tamTruDisplayModel.getTinhTrang().equals("Đã từ chối")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Nhân khẩu đã bị từ chối");
            alert.showAndWait();
        } else if (tamTruDisplayModel.getTinhTrang().equals("Chờ xóa")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn khôi phục trường hợp này?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                tamTruDisplayModel.setTinhTrang("Đã xác nhận");
                updateTamTruCanBo(tamTruDisplayModel);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn từ chối trường hợp này?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                tamTruDisplayModel.setTinhTrang("Đã từ chối");
                updateTamTruCanBo(tamTruDisplayModel);
            }
        }
    }

    private void xoaTamTruCanBo() {
        TamTruDisplayModel tamTruDisplayModel = tableViewTamTruCanBo.getSelectionModel().getSelectedItem();
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
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Thông báo");
                if (tamTruService.deleteTamTru(tamTruDisplayModel)) {
                    info.setHeaderText("Xóa thành công!");
                } else {
                    info.setHeaderText("Xóa không thành công!");
                }
                info.showAndWait();
                displayAllTamTruCanBo();
            }
        }
    }

    private void displayAllTamTruCanBo() {
        ObservableList<TamTruDisplayModel> listTamTru = tamTruService.getDisplayTamTru();
        tableViewTamTruCanBo.setItems(listTamTru);
    }
}
