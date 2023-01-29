package com.se07.controller.controllers.controllerscanbo;

import com.se07.controller.services.DipTraoThuongService;
import com.se07.model.models.DipTraoThuongModel;
import com.se07.util.ComponentVisibility;
import com.se07.util.MyDateStringConverter;
import com.se07.util.MyIntegerStringConverter;
import com.se07.view.TrangChuCanBoView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import java.util.ResourceBundle;

public class ControllerGiaiThuongCanBoView extends ControllerCanBoView {
    @FXML
    ComboBox comboBoxTimKiemGiaiThuongCanBo, comboBoxKieuGiaiThuongCanBo;
    @FXML
    TableView<DipTraoThuongModel> tableViewGiaiThuongCanBo;
    @FXML
    TableColumn<DipTraoThuongModel, Integer> tableColumnIDGiaiThuongCanBo, tableColumnNamGiaiThuongCanBo;
    @FXML
    TableColumn<DipTraoThuongModel, String> tableColumnTenDipGiaiThuongCanBo, tableColumnKieuGiaiThuongCanBo,
            tableColumnGhiChuGiaiThuongCanBo;
    @FXML
    TableColumn<DipTraoThuongModel, Date> tableColumnNgayTaoGiaiThuongCanBo, tableColumnNgayKetThucGiaiThuongCanBo;
    @FXML
    TextField textFieldLocThongTinGiaiThuongCanBo;

    @FXML
    DatePicker datePickerTu, datePickerDen;

    final ObservableList<String> listTimKiem = FXCollections.observableArrayList(
            "Tên", "Kiểu", "Năm", "Ngày tạo", "Ngày kết thúc");

    final ObservableList<String> listKieuGiaiThuong = FXCollections.observableArrayList("Dịp đặc biệt", "Thành tích");

    final private DipTraoThuongService dipTraoThuongService = new DipTraoThuongService();

    final private MyDateStringConverter dateStringConverter = new MyDateStringConverter("yyyy-MM-dd");
    final private MyIntegerStringConverter integerStringConverter = new MyIntegerStringConverter();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        tableColumnIDGiaiThuongCanBo.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, Integer>("id"));
        tableColumnTenDipGiaiThuongCanBo.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, String>("tenDip"));
        tableColumnKieuGiaiThuongCanBo.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, String>("kieu"));
        tableColumnNamGiaiThuongCanBo.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, Integer>("nam"));
        tableColumnNgayTaoGiaiThuongCanBo.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, Date>("ngayTao"));
        tableColumnNgayKetThucGiaiThuongCanBo.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, Date>("ngayKetThuc"));
        tableColumnGhiChuGiaiThuongCanBo.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, String>("ghiChu"));

        comboBoxTimKiemGiaiThuongCanBo.getItems().addAll(listTimKiem);
        comboBoxTimKiemGiaiThuongCanBo.getSelectionModel().selectFirst();
        comboBoxKieuGiaiThuongCanBo.getItems().addAll(listKieuGiaiThuong);
        comboBoxKieuGiaiThuongCanBo.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxKieuGiaiThuongCanBo, false);
        ComponentVisibility.change(datePickerTu, false);
        ComponentVisibility.change(datePickerDen, false);

        tableViewGiaiThuongCanBo.setEditable(true);
        tableColumnTenDipGiaiThuongCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnNamGiaiThuongCanBo.setCellFactory(TextFieldTableCell.forTableColumn(integerStringConverter));
        tableColumnNgayKetThucGiaiThuongCanBo.setCellFactory(TextFieldTableCell.forTableColumn(dateStringConverter));
        tableColumnGhiChuGiaiThuongCanBo.setCellFactory(TextFieldTableCell.forTableColumn());

        displayAllDipTraoThuongCanBo();
    }

    @Override
    public void onPressedButtonGiaiThuongCanBo(MouseEvent e) {
    }

    public void onPressedButtonTaoBieuMauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "TaoBieuMauDipTraoThuongCanBo.fxml");
        }
    }

    public void onPressedButtonGiaiThuongDipDacBietCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "GiaiThuongDipDacBietCanBoView.fxml");
        }
    }

    public void onPressedButtonGiaiThuongThanhTichCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "GiaiThuongThanhTichCanBoView.fxml");
        }
    }

    public void onPressedButtonXoaDipTraoThuongCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaDipTraoThuongCanBo();
        }
    }

    public void onPressedButtonLocThongTinDipTraoThuongCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinDipTraoThuongCanBo();
        }
    }

    public void onDeletePressedTrongBangDipTraoThuongCanBo(KeyEvent e) {
        if (e.getCode() == KeyCode.DELETE) {
            xoaDipTraoThuongCanBo();
        }
    }

    public void onPressedTrongCotIdGiaiThuongCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
            String id = ((Node) e.getTarget()).getId();
            if (id != null && id.equals("tableColumnIDGiaiThuongCanBo")) {
                DipTraoThuongModel dipTraoThuongModel = tableViewGiaiThuongCanBo.getSelectionModel().getSelectedItem();
                if (dipTraoThuongModel.getKieu().equals("Dịp đặc biệt")) {
                    FXMLLoader loader = new FXMLLoader(TrangChuCanBoView.class.getResource("GiaiThuongDipDacBietCanBoView.fxml"));
                    Parent root = loader.load();
                    ControllerGiaiThuongDipDacBietCanBoView controller = loader.getController();
                    controller.textFieldLocThongTinDipDacBietCanBo.setText(String.valueOf(dipTraoThuongModel.getId()));
                    controller.comboBoxTimKiemDipDacBietCanBo.getSelectionModel().select("ID");
                    controller.locThongTinDipDacBietCanBo();
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } else {
                    FXMLLoader loader = new FXMLLoader(TrangChuCanBoView.class.getResource("GiaiThuongThanhTichCanBoView.fxml"));
                    ControllerGiaiThuongThanhTichCanBoView controller = loader.getController();
                    controller.textFieldLocThongTinThanhTichCanBo.setText(String.valueOf(dipTraoThuongModel.getId()));
                    controller.comboBoxTimKiemThanhTichCanBo.getSelectionModel().select("ID");
                    controller.locThongTinThanhTichCanBo();
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);
                }
            }
        }
    }

    public void onEnterPressedTrongOTimKiemGiaiThuongCanBo(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            locThongTinDipTraoThuongCanBo();
        }
    }

    public void onSelectionComboBoxTimKiemGiaiThuongCanBo(ActionEvent e) {
        String truongTimKiem = String.valueOf(comboBoxTimKiemGiaiThuongCanBo.getValue());
        if (truongTimKiem.equals("Kiểu")) {
            ComponentVisibility.change(textFieldLocThongTinGiaiThuongCanBo, false);
            ComponentVisibility.change(comboBoxKieuGiaiThuongCanBo, true);
            ComponentVisibility.change(datePickerTu, false);
            ComponentVisibility.change(datePickerDen, false);
        } else if (truongTimKiem.equals("Ngày tạo") || truongTimKiem.equals("Ngày kết thúc")) {
            ComponentVisibility.change(textFieldLocThongTinGiaiThuongCanBo, false);
            ComponentVisibility.change(comboBoxKieuGiaiThuongCanBo, false);
            ComponentVisibility.change(datePickerTu, true);
            ComponentVisibility.change(datePickerDen, true);
        } else {
            ComponentVisibility.change(textFieldLocThongTinGiaiThuongCanBo, true);
            ComponentVisibility.change(comboBoxKieuGiaiThuongCanBo, false);
            ComponentVisibility.change(datePickerTu, false);
            ComponentVisibility.change(datePickerDen, false);
        }
    }

    public void handleOnEditCommit(TableColumn.CellEditEvent<DipTraoThuongModel, ?> event) {
        int column = event.getTablePosition().getColumn();
        DipTraoThuongModel dipTraoThuongModel = event.getRowValue();
        switch (column) {
            case 1:
                dipTraoThuongModel.setTenDip((String) event.getNewValue());
                break;
            case 3:
                int namMoi = (int) event.getNewValue();
                if (namMoi >= 0) {
                    dipTraoThuongModel.setNam(namMoi);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập năm hợp lệ");
                    alert.showAndWait();
                    displayAllDipTraoThuongCanBo();
                    return;
                }
                break;
            case 5:
                Date ngayKetThucMoi = (Date) event.getNewValue();
                if (ngayKetThucMoi != null) {
                    dipTraoThuongModel.setNgayKetThuc(ngayKetThucMoi);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập ngày sinh hợp lệ đúng định dạng năm-tháng-ngày");
                    alert.showAndWait();
                    displayAllDipTraoThuongCanBo();
                    return;
                }
                break;
            case 6:
                dipTraoThuongModel.setGhiChu((String) event.getNewValue());
                break;
        }
        updateDipTraoThuongCanBo(dipTraoThuongModel);
    }

    public void handleOnEditCancel(TableColumn.CellEditEvent<DipTraoThuongModel, ?> event) {
        int column = event.getTablePosition().getColumn();
        DipTraoThuongModel dipTraoThuongModel = event.getRowValue();
        switch (column) {
            case 1:
                dipTraoThuongModel.setTenDip((String) event.getOldValue());
                break;
            case 3:
                dipTraoThuongModel.setNam((int) event.getOldValue());
                break;
            case 5:
                dipTraoThuongModel.setNgayKetThuc((Date) event.getOldValue());
                break;
            case 6:
                dipTraoThuongModel.setGhiChu((String) event.getOldValue());
                break;
        }
    }

    private void updateDipTraoThuongCanBo(DipTraoThuongModel dipTraoThuongModel) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        if (dipTraoThuongService.updateDipTraoThuong(dipTraoThuongModel)) {
            alert.setHeaderText("Sửa dịp trao thưởng thành công");
        } else {
            alert.setHeaderText("Sửa dịp trao thưởng không thành công");
        }
        if (alert.showAndWait().get() == ButtonType.OK) {
            displayAllDipTraoThuongCanBo();
        }
    }


    private void displayAllDipTraoThuongCanBo() {
        ObservableList<DipTraoThuongModel> dipTraoThuongList = dipTraoThuongService.getAllDipTraoThuong();
        tableViewGiaiThuongCanBo.setItems(dipTraoThuongList);
    }

    private void xoaDipTraoThuongCanBo() {
        DipTraoThuongModel dipTraoThuongModel = tableViewGiaiThuongCanBo.getSelectionModel().getSelectedItem();
        if (dipTraoThuongModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn dịp trao thưởng muốn xóa");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xóa dịp này!");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Thông báo");
                if (dipTraoThuongService.deleteDipTraoThuong(dipTraoThuongModel)) {
                    info.setHeaderText("Xóa thành công!");
                } else {
                    info.setHeaderText("Xóa không thành công!");
                }
                info.showAndWait();
                displayAllDipTraoThuongCanBo();
            }
        }
    }

    private void locThongTinDipTraoThuongCanBo() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemGiaiThuongCanBo.getValue());
        String cauHoi = textFieldLocThongTinGiaiThuongCanBo.getText();
        ObservableList<DipTraoThuongModel> dipTraoThuongModelObservableList = FXCollections.observableArrayList();
        switch (dieuKienKiemTra) {
            case "Tên":
                dipTraoThuongModelObservableList = dipTraoThuongService.getDipTraoThuongByTen(cauHoi);
                break;
            case "Kiểu":
                dipTraoThuongModelObservableList = dipTraoThuongService.getAllDipTraoThuongByKieu(
                        String.valueOf(comboBoxKieuGiaiThuongCanBo.getValue()));
                break;
            case "Năm":
                int nam = integerStringConverter.fromString(textFieldLocThongTinGiaiThuongCanBo.getText());
                if (nam == -1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập năm hợp lệ");
                    alert.showAndWait();
                    displayAllDipTraoThuongCanBo();
                    textFieldLocThongTinGiaiThuongCanBo.requestFocus();
                } else {
                    dipTraoThuongModelObservableList = dipTraoThuongService.getAllDipTraoThuongByNam(nam);
                }
                break;
            case "Ngày tạo":
                if (datePickerTu.getValue() != null && datePickerDen.getValue() != null) {
                    dipTraoThuongModelObservableList = dipTraoThuongService.getAllDipTraoThuongNgayTaoBetween(
                            Date.from(datePickerTu.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                            Date.from(datePickerDen.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
                    alert.showAndWait();
                    displayAllDipTraoThuongCanBo();
                    return;
                }
                break;
            case "Ngày kết thúc":
                if (datePickerTu.getValue() != null && datePickerDen.getValue() != null) {
                    dipTraoThuongModelObservableList = dipTraoThuongService.getAllDipTraoThuongNgayKetThucBetween(
                            Date.from(datePickerTu.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                            Date.from(datePickerDen.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
                    alert.showAndWait();
                    displayAllDipTraoThuongCanBo();
                    return;
                }
                break;
        }
        tableViewGiaiThuongCanBo.setItems(dipTraoThuongModelObservableList);
    }
}