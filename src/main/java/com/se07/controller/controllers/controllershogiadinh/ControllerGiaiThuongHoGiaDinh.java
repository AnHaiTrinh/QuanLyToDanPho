package com.se07.controller.controllers.controllershogiadinh;
import com.se07.controller.services.DipTraoThuongService;
import com.se07.model.models.DipTraoThuongModel;
import com.se07.util.ComponentVisibility;
import com.se07.util.MyDateStringConverter;
import com.se07.util.MyIntegerStringConverter;
import com.se07.view.UserView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class ControllerGiaiThuongHoGiaDinh extends ControllerHoGiaDinhView implements Initializable {
    @FXML
    ComboBox comboBoxTimKiemGiaiThuongHoGiaDinh, comboBoxKieuGiaiThuongHoGiaDinh;
    @FXML
    TableView<DipTraoThuongModel> tableViewGiaiThuongHoGiaDinh;
    @FXML
    TableColumn<DipTraoThuongModel, Integer> tableColumnIDGiaiThuongHoGiaDinh, tableColumnNamGiaiThuongHoGiaDinh;
    @FXML
    TableColumn<DipTraoThuongModel, String> tableColumnTenDipGiaiThuongHoGiaDinh, tableColumnKieuGiaiThuongHoGiaDinh,
            tableColumnGhiChuGiaiThuongHoGiaDinh;
    @FXML
    TableColumn<DipTraoThuongModel, Date> tableColumnNgayTaoGiaiThuongHoGiaDinh, tableColumnNgayKetThucGiaiThuongHoGiaDinh;
    @FXML
    TextField textFieldLocThongTinGiaiThuongHoGiaDinh;

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

        tableColumnIDGiaiThuongHoGiaDinh.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, Integer>("id"));
        tableColumnTenDipGiaiThuongHoGiaDinh.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, String>("tenDip"));
        tableColumnKieuGiaiThuongHoGiaDinh.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, String>("kieu"));
        tableColumnNamGiaiThuongHoGiaDinh.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, Integer>("nam"));
        tableColumnNgayTaoGiaiThuongHoGiaDinh.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, Date>("ngayTao"));
        tableColumnNgayKetThucGiaiThuongHoGiaDinh.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, Date>("ngayKetThuc"));
        tableColumnGhiChuGiaiThuongHoGiaDinh.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, String>("ghiChu"));

        comboBoxTimKiemGiaiThuongHoGiaDinh.getItems().addAll(listTimKiem);
        comboBoxTimKiemGiaiThuongHoGiaDinh.getSelectionModel().selectFirst();
        comboBoxKieuGiaiThuongHoGiaDinh.getItems().addAll(listKieuGiaiThuong);
        comboBoxKieuGiaiThuongHoGiaDinh.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxKieuGiaiThuongHoGiaDinh, false);
        ComponentVisibility.change(datePickerTu, false);
        ComponentVisibility.change(datePickerDen, false);

        tableViewGiaiThuongHoGiaDinh.setEditable(true);
        tableColumnTenDipGiaiThuongHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnNamGiaiThuongHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn(integerStringConverter));
        tableColumnNgayKetThucGiaiThuongHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn(dateStringConverter));
        tableColumnGhiChuGiaiThuongHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn());

        displayAllDipTraoThuongHoGiaDinh();
    }

    @Override
    public void onPressedButtonGiaiThuongHoGiaDinh(MouseEvent e) {
    }

    public void onPressedButtonGiaiThuongDipDacBietHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "GiaiThuongDipDacBietHoGiaDinhView.fxml");
        }
    }

    public void onPressedButtonGiaiThuongThanhTichHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "GiaiThuongThanhTichHoGiaDinhView.fxml");
        }
    }

    public void onPressedButtonXoaDipTraoThuongHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaDipTraoThuongHoGiaDinh();
        }
    }

    public void onPressedButtonLocThongTinDipTraoThuongHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinDipTraoThuongHoGiaDinh();
        }
    }

    public void onDeletePressedTrongBangDipTraoThuongHoGiaDinh(KeyEvent e) {
        if (e.getCode() == KeyCode.DELETE) {
            xoaDipTraoThuongHoGiaDinh();
        }
    }

    public void onPressedTrongCotIdGiaiThuongHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
            String id = ((Node) e.getTarget()).getId();
            if (id != null && id.equals("tableColumnIDGiaiThuongHoGiaDinh")) {
                DipTraoThuongModel dipTraoThuongModel = tableViewGiaiThuongHoGiaDinh.getSelectionModel().getSelectedItem();
                if (dipTraoThuongModel.getKieu().equals("Dịp đặc biệt")) {
                    FXMLLoader loader = new FXMLLoader(UserView.class.getResource("GiaiThuongDipDacBietHoGiaDinhView.fxml"));
                    Parent root = loader.load();
                    ControllerGiaiThuongDipDacBietHoGiaDinhView controller = loader.getController();
                    ComboBox comboBoxTenNamDipDacBietHoGiaDinh = controller.comboBoxTenNamDipDacBietHoGiaDinh,
                            comboBoxTimKiemDipDacBietHoGiaDinh = controller.comboBoxTimKiemDipDacBietHoGiaDinh;
                    comboBoxTenNamDipDacBietHoGiaDinh.getSelectionModel().select(dipTraoThuongModel.getTenDip() + " - " + dipTraoThuongModel.getNam());
                    comboBoxTimKiemDipDacBietHoGiaDinh.getSelectionModel().select("Tên - Năm");
                    ComponentVisibility.change(controller.textFieldLocThongTinDipDacBietHoGiaDinh, false);
                    ComponentVisibility.change(comboBoxTenNamDipDacBietHoGiaDinh, true);
                    controller.locThongTinDipDacBietHoGiaDinh();
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } else {
                    FXMLLoader loader = new FXMLLoader(UserView.class.getResource("GiaiThuongThanhTichHoGiaDinhView.fxml"));
                    Parent root = loader.load();
                    ControllerGiaiThuongThanhTichHoGiaDinhView controller = loader.getController();
                    ComboBox comboBoxTenNamThanhTichHoGiaDinh = controller.comboBoxTenNamThanhTichHoGiaDinh,
                            comboBoxTiTimKiemThanhTichHoGiaDinh = controller.comboBoxTimKiemThanhTichHoGiaDinh;
                    comboBoxTenNamThanhTichHoGiaDinh.getSelectionModel().select(dipTraoThuongModel.getTenDip() + " - " + dipTraoThuongModel.getNam());
                    comboBoxTiTimKiemThanhTichHoGiaDinh.getSelectionModel().select("Tên - Năm");
                    ComponentVisibility.change(controller.textFieldLocThongTinThanhTichHoGiaDinh, false);
                    ComponentVisibility.change(comboBoxTenNamThanhTichHoGiaDinh, true);
                    controller.locThongTinThanhTichHoGiaDinh();
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                }
            }
        }
    }

    public void onEnterPressedTrongOTimKiemGiaiThuongHoGiaDinh(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            locThongTinDipTraoThuongHoGiaDinh();
        }
    }

    public void onSelectionComboBoxTimKiemGiaiThuongHoGiaDinh(ActionEvent e) {
        String truongTimKiem = String.valueOf(comboBoxTimKiemGiaiThuongHoGiaDinh.getValue());
        if (truongTimKiem.equals("Kiểu")) {
            ComponentVisibility.change(textFieldLocThongTinGiaiThuongHoGiaDinh, false);
            ComponentVisibility.change(comboBoxKieuGiaiThuongHoGiaDinh, true);
            ComponentVisibility.change(datePickerTu, false);
            ComponentVisibility.change(datePickerDen, false);
        } else if (truongTimKiem.equals("Ngày tạo") || truongTimKiem.equals("Ngày kết thúc")) {
            ComponentVisibility.change(textFieldLocThongTinGiaiThuongHoGiaDinh, false);
            ComponentVisibility.change(comboBoxKieuGiaiThuongHoGiaDinh, false);
            ComponentVisibility.change(datePickerTu, true);
            ComponentVisibility.change(datePickerDen, true);
        } else {
            ComponentVisibility.change(textFieldLocThongTinGiaiThuongHoGiaDinh, true);
            ComponentVisibility.change(comboBoxKieuGiaiThuongHoGiaDinh, false);
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
                    displayAllDipTraoThuongHoGiaDinh();
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
                    displayAllDipTraoThuongHoGiaDinh();
                    return;
                }
                break;
            case 6:
                dipTraoThuongModel.setGhiChu((String) event.getNewValue());
                break;
        }
        updateDipTraoThuongHoGiaDinh(dipTraoThuongModel);
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

    private void updateDipTraoThuongHoGiaDinh(DipTraoThuongModel dipTraoThuongModel) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        if (dipTraoThuongService.updateDipTraoThuong(dipTraoThuongModel)) {
            alert.setHeaderText("Sửa dịp trao thưởng thành công");
        } else {
            alert.setHeaderText("Sửa dịp trao thưởng không thành công");
        }
        if (alert.showAndWait().get() == ButtonType.OK) {
            displayAllDipTraoThuongHoGiaDinh();
        }
    }


    private void displayAllDipTraoThuongHoGiaDinh() {
        ObservableList<DipTraoThuongModel> dipTraoThuongList = dipTraoThuongService.getAllDipTraoThuong();
        tableViewGiaiThuongHoGiaDinh.setItems(dipTraoThuongList);
    }

    private void xoaDipTraoThuongHoGiaDinh() {
        DipTraoThuongModel dipTraoThuongModel = tableViewGiaiThuongHoGiaDinh.getSelectionModel().getSelectedItem();
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
                displayAllDipTraoThuongHoGiaDinh();
            }
        }
    }

    private void locThongTinDipTraoThuongHoGiaDinh() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemGiaiThuongHoGiaDinh.getValue());
        String cauHoi = textFieldLocThongTinGiaiThuongHoGiaDinh.getText();
        ObservableList<DipTraoThuongModel> dipTraoThuongModelObservableList = FXCollections.observableArrayList();
        switch (dieuKienKiemTra) {
            case "Tên":
                dipTraoThuongModelObservableList = dipTraoThuongService.getDipTraoThuongByTenAndMaHoKhau(cauHoi, maHoKhauDangNhap);
                break;
            case "Kiểu":
                dipTraoThuongModelObservableList = dipTraoThuongService.getAllDipTraoThuongByKieuAndMaHoKhau(
                        String.valueOf(comboBoxKieuGiaiThuongHoGiaDinh.getValue()),maHoKhauDangNhap);
                break;
            case "Năm":
                int nam = integerStringConverter.fromString(textFieldLocThongTinGiaiThuongHoGiaDinh.getText());
                if (nam == -1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập năm hợp lệ");
                    alert.showAndWait();
                    displayAllDipTraoThuongHoGiaDinh();
                    textFieldLocThongTinGiaiThuongHoGiaDinh.requestFocus();
                } else {
                    dipTraoThuongModelObservableList = dipTraoThuongService.getAllDipTraoThuongByNamAndMaHoKhau(nam, maHoKhauDangNhap);
                }
                break;
            case "Ngày tạo":
                if (datePickerTu.getValue() != null && datePickerDen.getValue() != null) {
                    dipTraoThuongModelObservableList = dipTraoThuongService.getAllDipTraoThuongNgayTaoBetweenAndMaHoKhau(
                            Date.from(datePickerTu.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                            Date.from(datePickerDen.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), maHoKhauDangNhap);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
                    alert.showAndWait();
                    displayAllDipTraoThuongHoGiaDinh();
                    return;
                }
                break;
            case "Ngày kết thúc":
                if (datePickerTu.getValue() != null && datePickerDen.getValue() != null) {
                    dipTraoThuongModelObservableList = dipTraoThuongService.getAllDipTraoThuongNgayKetThucBetweenAndMaHoKhau(
                            Date.from(datePickerTu.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                            Date.from(datePickerDen.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), maHoKhauDangNhap);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
                    alert.showAndWait();
                    displayAllDipTraoThuongHoGiaDinh();
                    return;
                }
                break;
        }
        tableViewGiaiThuongHoGiaDinh.setItems(dipTraoThuongModelObservableList);
    }

}
