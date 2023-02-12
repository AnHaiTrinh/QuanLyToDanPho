package com.se07.controller.controllers.controllersketoan;

import com.se07.controller.services.DipTraoThuongService;
import com.se07.model.models.DipTraoThuongModel;
import com.se07.util.ComponentVisibility;
import com.se07.util.MyDateStringConverter;
import com.se07.util.MyIntegerStringConverter;
import com.se07.view.TreasurerView;
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

public class ControllerGiaiThuongThuQuyView extends ControllerThuQuyView {
    @FXML
    ComboBox comboBoxTimKiemGiaiThuongThuQuy, comboBoxKieuGiaiThuongThuQuy, comboBoxTenNamGiaiThuongThuQuy;
    @FXML
    TableView<DipTraoThuongModel> tableViewGiaiThuongThuQuy;
    @FXML
    TableColumn<DipTraoThuongModel, Integer> tableColumnIDGiaiThuongThuQuy, tableColumnNamGiaiThuongThuQuy;
    @FXML
    TableColumn<DipTraoThuongModel, String> tableColumnTenDipGiaiThuongThuQuy, tableColumnKieuGiaiThuongThuQuy,
            tableColumnGhiChuGiaiThuongThuQuy;
    @FXML
    TableColumn<DipTraoThuongModel, Date> tableColumnNgayTaoGiaiThuongThuQuy, tableColumnNgayKetThucGiaiThuongThuQuy;
    @FXML
    TextField textFieldLocThongTinGiaiThuongThuQuy;

    @FXML
    DatePicker datePickerTu, datePickerDen;

    private final ObservableList<String> listTimKiem = FXCollections.observableArrayList(
            "Tên dịp", "Năm", "Tên - Năm", "Kiểu", "Ngày tạo", "Ngày kết thúc");

    final ObservableList<String> listKieuGiaiThuong = FXCollections.observableArrayList("Dịp đặc biệt", "Thành tích");

    final private DipTraoThuongService dipTraoThuongService = new DipTraoThuongService();

    final ObservableList<String> listTenNamDipTraoThuong = dipTraoThuongService.getAllTenNamDipTraoThuong();
    final private MyIntegerStringConverter integerStringConverter = new MyIntegerStringConverter();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        tableColumnIDGiaiThuongThuQuy.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, Integer>("id"));
        tableColumnTenDipGiaiThuongThuQuy.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, String>("tenDip"));
        tableColumnKieuGiaiThuongThuQuy.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, String>("kieu"));
        tableColumnNamGiaiThuongThuQuy.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, Integer>("nam"));
        tableColumnNgayTaoGiaiThuongThuQuy.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, Date>("ngayTao"));
        tableColumnNgayKetThucGiaiThuongThuQuy.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, Date>("ngayKetThuc"));
        tableColumnGhiChuGiaiThuongThuQuy.setCellValueFactory(new PropertyValueFactory<DipTraoThuongModel, String>("ghiChu"));

        comboBoxTimKiemGiaiThuongThuQuy.getItems().addAll(listTimKiem);
        comboBoxTimKiemGiaiThuongThuQuy.getSelectionModel().selectFirst();
        comboBoxKieuGiaiThuongThuQuy.getItems().addAll(listKieuGiaiThuong);
        comboBoxKieuGiaiThuongThuQuy.getSelectionModel().selectFirst();
        comboBoxTenNamGiaiThuongThuQuy.getItems().addAll(listTenNamDipTraoThuong);
        comboBoxTenNamGiaiThuongThuQuy.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxTenNamGiaiThuongThuQuy, false);
        ComponentVisibility.change(comboBoxKieuGiaiThuongThuQuy, false);
        ComponentVisibility.change(datePickerTu, false);
        ComponentVisibility.change(datePickerDen, false);

        tableViewGiaiThuongThuQuy.setItems(dipTraoThuongService.getAllDipTraoThuong());
    }

    @Override
    public void onPressedButtonGiaiThuongThuQuy(MouseEvent e) throws IOException {
    }

    public void onEnterPressedTrongOTimKiemGiaiThuongThuQuy(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            locThongTinTraoThuongThuQuy();
        }
    }

    public void onPressedButtonLocThongTinDipTraoThuongThuQuy(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {
            locThongTinTraoThuongThuQuy();
        }
    }

    private void locThongTinTraoThuongThuQuy() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemGiaiThuongThuQuy.getValue());
        String cauHoi = textFieldLocThongTinGiaiThuongThuQuy.getText();
        ObservableList<DipTraoThuongModel> dipTraoThuongModelObservableList = FXCollections.observableArrayList();
        switch (dieuKienKiemTra) {
            case "Tên dịp":
                dipTraoThuongModelObservableList = dipTraoThuongService.getDipTraoThuongByTen(cauHoi);
                break;
            case "Kiểu":
                dipTraoThuongModelObservableList = dipTraoThuongService.getAllDipTraoThuongByKieu(
                        String.valueOf(comboBoxKieuGiaiThuongThuQuy.getValue()));
                break;
            case "Năm":
                int nam = integerStringConverter.fromString(textFieldLocThongTinGiaiThuongThuQuy.getText());
                if (nam == -1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập năm hợp lệ");
                    alert.showAndWait();
                    textFieldLocThongTinGiaiThuongThuQuy.requestFocus();
                    return;
                } else {
                    dipTraoThuongModelObservableList = dipTraoThuongService.getAllDipTraoThuongByNam(nam);
                }
                break;
            case "Tên - Năm":
                String tenNam = String.valueOf(comboBoxTenNamGiaiThuongThuQuy.getValue());
                int index = tenNam.indexOf(" - ");
                String tenDip = tenNam.substring(0, index);
                int namDip = Integer.parseInt(tenNam.substring(index + 3));
                dipTraoThuongModelObservableList.add(dipTraoThuongService.getDipTraoThuongByTenAndNam(tenDip, namDip).get());
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
                    return;
                }
                break;
        }
        tableViewGiaiThuongThuQuy.setItems(dipTraoThuongModelObservableList);
    }

    public void onSelectionComboBoxTimKiemGiaiThuongThuQuy(ActionEvent e) {
        ComponentVisibility.change(textFieldLocThongTinGiaiThuongThuQuy, false);
        ComponentVisibility.change(comboBoxKieuGiaiThuongThuQuy, false);
        ComponentVisibility.change(comboBoxTenNamGiaiThuongThuQuy, false);
        ComponentVisibility.change(datePickerTu, false);
        ComponentVisibility.change(datePickerDen, false);
        String truongTimKiem = String.valueOf(comboBoxTimKiemGiaiThuongThuQuy.getValue());
        if (truongTimKiem.equals("Kiểu")) {
            ComponentVisibility.change(comboBoxKieuGiaiThuongThuQuy, true);
        } else if (truongTimKiem.equals("Ngày tạo") || truongTimKiem.equals("Ngày kết thúc")) {
            ComponentVisibility.change(datePickerTu, true);
            ComponentVisibility.change(datePickerDen, true);
        } else if (truongTimKiem.equals("Tên - Năm")) {
            ComponentVisibility.change(comboBoxTenNamGiaiThuongThuQuy, true);
        } else {
            ComponentVisibility.change(textFieldLocThongTinGiaiThuongThuQuy, true);
        }
    }

    public void onPressedButtonTraoThuongThuQuy(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            DipTraoThuongModel dipTraoThuongModel = tableViewGiaiThuongThuQuy.getSelectionModel().getSelectedItem();
            if (dipTraoThuongModel == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Vui lòng chọn dịp để trao thưởng");
                alert.showAndWait();
            } else {
                if (dipTraoThuongModel.getKieu().equals("Dịp đặc biệt")) {
                    FXMLLoader loader = new FXMLLoader(TreasurerView.class.getResource("GiaiThuongDipDacBietThuQuyView.fxml"));
                    Parent root = loader.load();
                    ControllerGiaiThuongDipDacBietThuQuyView controller = loader.getController();
                    controller.tenDip = dipTraoThuongModel.getTenDip();
                    controller.nam = dipTraoThuongModel.getNam();
                    controller.loadData();
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                }
                if (dipTraoThuongModel.getKieu().equals("Thành tích")) {
                    FXMLLoader loader = new FXMLLoader(TreasurerView.class.getResource("GiaiThuongThanhTichThuQuyView.fxml"));
                    Parent root = loader.load();
                    ControllerGiaiThuongThanhTichThuQuyView controller = loader.getController();
                    controller.tenDip = dipTraoThuongModel.getTenDip();
                    controller.nam = dipTraoThuongModel.getNam();
                    controller.loadData();
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                }
            }
        }
    }
}
