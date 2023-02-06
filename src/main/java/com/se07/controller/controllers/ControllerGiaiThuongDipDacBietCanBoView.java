package com.se07.controller.controllers;

import com.se07.controller.services.DipTraoThuongService;
import com.se07.controller.services.NhanKhauService;
import com.se07.controller.services.ThongTinDipDacBietService;
import com.se07.model.models.DipTraoThuongModel;
import com.se07.model.models.ThongTinDipDacBietDisplayModel;
import com.se07.model.models.ThongTinDipDacBietModel;
import com.se07.util.ComponentVisibility;
import com.se07.util.MyIntegerStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerGiaiThuongDipDacBietCanBoView extends ControllerCanBoView {
    @FXML
    ComboBox comboBoxTimKiemDipDacBietCanBo, comboBoxTenNamDipDacBietCanBo,
            comboBoxMaNhanKhauDipDacBietCanBo, comboBoxTinhTrangDipDacBietCanBo;
    @FXML
    TableView<ThongTinDipDacBietDisplayModel> tableViewDipDacBietCanBo;
    @FXML
    TableColumn<ThongTinDipDacBietDisplayModel, String> tableColumnMaNhanKhauDipDacBietCanBo,
            tableColumnHoTenDipDacBietCanBo, tableColumnTenDipDacBietCanBo, tableColumnTinhTrangDipDacBietCanBo;
    @FXML
    TableColumn<ThongTinDipDacBietDisplayModel, Integer> tableColumnNamDipDacBietCanBo;
    @FXML
    TextField textFieldLocThongTinDipDacBietCanBo;

    private final DipTraoThuongService dipTraoThuongService = new DipTraoThuongService();
    private final ThongTinDipDacBietService thongTinDipDacBietService = new ThongTinDipDacBietService();
    final private MyIntegerStringConverter integerStringConverter = new MyIntegerStringConverter();
    final ObservableList<String> listTimKiem = FXCollections.observableArrayList(
            "Mã nhân khẩu", "Họ tên", "Tên dịp", "Năm", "Tên - Năm", "Tình trạng");
    final ObservableList<String> listTenNamDipTraoThuong = dipTraoThuongService.getAllTenNamDipDacBiet();
    final ObservableList<String> listMaNhanKhau = new NhanKhauService().getAllMaNhanKhau();
    final ObservableList<String> listTinhTrang =
            FXCollections.observableArrayList("Chờ xác nhận", "Đã xác nhận", "Đã từ chối");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        tableColumnMaNhanKhauDipDacBietCanBo.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        tableColumnHoTenDipDacBietCanBo.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        tableColumnTenDipDacBietCanBo.setCellValueFactory(new PropertyValueFactory<>("tenDip"));
        tableColumnNamDipDacBietCanBo.setCellValueFactory(new PropertyValueFactory<>("nam"));
        tableColumnTinhTrangDipDacBietCanBo.setCellValueFactory(new PropertyValueFactory<>("tinhTrang"));

        comboBoxTimKiemDipDacBietCanBo.getItems().addAll(listTimKiem);
        comboBoxTimKiemDipDacBietCanBo.getSelectionModel().selectFirst();
        comboBoxTenNamDipDacBietCanBo.getItems().addAll(listTenNamDipTraoThuong);
        comboBoxTenNamDipDacBietCanBo.getSelectionModel().selectFirst();
        comboBoxMaNhanKhauDipDacBietCanBo.getItems().addAll(listMaNhanKhau);
        comboBoxMaNhanKhauDipDacBietCanBo.getSelectionModel().selectFirst();
        comboBoxTinhTrangDipDacBietCanBo.getItems().addAll(listTinhTrang);
        comboBoxTinhTrangDipDacBietCanBo.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxTenNamDipDacBietCanBo, false);
        ComponentVisibility.change(comboBoxMaNhanKhauDipDacBietCanBo, false);
        ComponentVisibility.change(comboBoxTinhTrangDipDacBietCanBo, false);

        tableViewDipDacBietCanBo.setEditable(true);
        tableColumnMaNhanKhauDipDacBietCanBo.setCellFactory(t -> new ComboBoxTableCell<>(listMaNhanKhau));
        tableColumnTinhTrangDipDacBietCanBo.setCellFactory(t -> new ComboBoxTableCell<>(listTinhTrang));

        displayAllThongTinDipDacBietCanBo();
    }

    public void onSelectionComboBoxTimKiemDipDacBietCanBo(ActionEvent e) {
        String truongTimKiem = String.valueOf(comboBoxTimKiemDipDacBietCanBo.getValue());
        ComponentVisibility.change(textFieldLocThongTinDipDacBietCanBo, false);
        ComponentVisibility.change(comboBoxTenNamDipDacBietCanBo, false);
        ComponentVisibility.change(comboBoxMaNhanKhauDipDacBietCanBo, false);
        ComponentVisibility.change(comboBoxTinhTrangDipDacBietCanBo, false);
        switch (truongTimKiem) {
            case "Mã nhân khẩu":
                ComponentVisibility.change(comboBoxMaNhanKhauDipDacBietCanBo, true);
                break;
            case "Tên - năm":
                ComponentVisibility.change(comboBoxTenNamDipDacBietCanBo, true);
                break;
            case "Tình trạng":
                ComponentVisibility.change(comboBoxTinhTrangDipDacBietCanBo, true);
                break;
            default:
                ComponentVisibility.change(textFieldLocThongTinDipDacBietCanBo, true);
                break;
        }
    }

    public void onPressedButtonThemMoiThongTinDipDacBietCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "ThemMoiThongTinDipDacBietCanBo.fxml");
        }
    }

    public void onPressedButtonLocThongTinDipDacBietCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinDipDacBietCanBo();
        }
    }

    public void onEnterPressedTrongOTimKiemDipDacBietCanBo(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            locThongTinDipDacBietCanBo();
        }
    }

    public void onPressedButtonPheDuyetThongTinDipDacBietCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xacNhanThongTinDipDacBietCanBo();
        }
    }

    private void xacNhanThongTinDipDacBietCanBo() {
        ThongTinDipDacBietDisplayModel thongTinDipDacBietDisplayModel =
                tableViewDipDacBietCanBo.getSelectionModel().getSelectedItem();
        if (thongTinDipDacBietDisplayModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn xác nhận");
            alert.showAndWait();
        } else if (thongTinDipDacBietDisplayModel.getTinhTrang() == "Đã xác nhận") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trường hợp đã được xác nhận");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xác nhận trường hợp này?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                thongTinDipDacBietDisplayModel.setTinhTrang("Đã xác nhận");
                updateThongTinDipDacBietCanBo(thongTinDipDacBietDisplayModel);
                displayAllThongTinDipDacBietCanBo();
            }
        }
    }

    public void onPressedButtonTuChoiThongTinDipDacBietCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            tuChoiThongTinDipDacBietCanBo();
        }
    }

    private void tuChoiThongTinDipDacBietCanBo() {
        ThongTinDipDacBietDisplayModel thongTinDipDacBietDisplayModel =
                tableViewDipDacBietCanBo.getSelectionModel().getSelectedItem();
        if (thongTinDipDacBietDisplayModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn xác nhận");
            alert.showAndWait();
        } else if (thongTinDipDacBietDisplayModel.getTinhTrang() == "Đã từ chối") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trường hợp đã bị từ chối");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn từ chối trường hợp này?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                thongTinDipDacBietDisplayModel.setTinhTrang("Đã từ chối");
                updateThongTinDipDacBietCanBo(thongTinDipDacBietDisplayModel);
                displayAllThongTinDipDacBietCanBo();
            }
        }
    }

    public void onDeletePressedTrongBangThongTinDipDacBietCanBo(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.DELETE)) {
            xoaThongTinDipDacBietCanBo();
        }
    }

    public void onPressedButtonXoaThongTinDipDacBietCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaThongTinDipDacBietCanBo();
        }
    }

    private void xoaThongTinDipDacBietCanBo() {
    }
//        ThongTinDipDacBietDisplayModel thongTinDipDacBietDisplayModel =
//                tableViewDipDacBietCanBo.getSelectionModel().getSelectedItem();
//        if (thongTinDipDacBietDisplayModel == null) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Thông báo");
//            alert.setHeaderText("Vui lòng chọn trường hợp muốn xóa");
//            alert.showAndWait();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Thông báo");
//            alert.setHeaderText("Bạn chắc chắn muốn xóa trường hợp này!");
//            if (alert.showAndWait().get() == ButtonType.OK) {
//                Alert info = new Alert(Alert.AlertType.INFORMATION);
//                info.setTitle("Thông báo");
//                if (thongTinDipDacBietService.deleteThongTinDipDacBiet(thongTinDipDacBietDisplayModel)) {
//                    info.setHeaderText("Xóa thành công!");
//                } else {
//                    info.setHeaderText("Xóa không thành công!");
//                }
//                info.showAndWait();
//                displayAllThongTinDipDacBietCanBo();
//            }
//        }
//    }

    public void onPressedButtonThoatThongTinDipDacBietCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Bạn chắc chắn muốn thoát?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                        "GiaiThuongCanBoView.fxml");
            }
        }
    }

    public void locThongTinDipDacBietCanBo() {
    }

    //        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemDipDacBietCanBo.getValue());
//        String cauHoi = textFieldLocThongTinDipDacBietCanBo.getText();
//        ObservableList<ThongTinDipDacBietDisplayModel> listThongTinDipDacBiet = FXCollections.observableArrayList();
//        switch (dieuKienKiemTra) {
//            case "Mã nhân khẩu":
//                listThongTinDipDacBiet = thongTinDipDacBietService.getAllThongTinDipDacBietByMaNhanKhau(
//                        String.valueOf(comboBoxMaNhanKhauDipDacBietCanBo.getValue())
//                );
//                break;
//            case "Họ tên":
//                listThongTinDipDacBiet = thongTinDipDacBietService.getAllThongTinDipDacBietByHoTen(cauHoi);
//                break;
//            case "Tên dịp":
//                listThongTinDipDacBiet = thongTinDipDacBietService.getAllThongTinDipDacBietByTenDip(cauHoi);
//                break;
//            case "Năm":
//                int nam = integerStringConverter.fromString(cauHoi);
//                if (nam == -1) {
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Thông báo");
//                    alert.setHeaderText("Vui lòng nhập năm hợp lệ");
//                    alert.showAndWait();
//                    displayAllThongTinDipDacBietCanBo();
//                    textFieldLocThongTinDipDacBietCanBo.requestFocus();
//                    return;
//                }
//                listThongTinDipDacBiet = thongTinDipDacBietService.getAllThongTinDipDacBietByNam(nam);
//                break;
//            case "Tên - năm":
//                String tenNam = String.valueOf(comboBoxTenNamDipDacBietCanBo.getValue());
//                int index = tenNam.indexOf(" - ");
//                String tenDip = tenNam.substring(0, index);
//                int namDip = Integer.parseInt(tenNam.substring(index + 3));
//                int id = dipTraoThuongService.getDipTraoThuongByTenAndNam(tenDip, namDip).get().getId();
//                listThongTinDipDacBiet = thongTinDipDacBietService.getAllThongTinDipDacBietById(id);
//                break;
//            case "Tình trạng":
//                listThongTinDipDacBiet = thongTinDipDacBietService.getAllThongTinDipDacBietByTinhTrang(
//                        String.valueOf(comboBoxTinhTrangDipDacBietCanBo.getValue())
//                );
//                break;
//        }
//        tableViewDipDacBietCanBo.setItems(listThongTinDipDacBiet);
//    }
//
    private void displayAllThongTinDipDacBietCanBo() {
    }

    //        ObservableList<ThongTinDipDacBietDisplayModel> thongTinDipDacBietDisplayModels =
//                thongTinDipDacBietService.getAllThongTinDipDacBiet();
//        tableViewDipDacBietCanBo.setItems(thongTinDipDacBietDisplayModels);
//    }
//
    private void updateThongTinDipDacBietCanBo(ThongTinDipDacBietDisplayModel thongTinDipDacBietDisplayModel) {
    }
//        ThongTinDipDacBietModel thongTinDipDacBietModel =
//                thongTinDipDacBietService.convertDisplayModelToModel(thongTinDipDacBietDisplayModel);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Thông báo!");
//        if (thongTinDipDacBietService.updateThongTinDipDacBiet(thongTinDipDacBietModel)) {
//            alert.setHeaderText("Sửa thông tin thành công");
//        } else {
//            alert.setHeaderText("Sửa thông tin không thành công");
//        }
//        if (alert.showAndWait().get() == ButtonType.OK) {
//            displayAllThongTinDipDacBietCanBo();
//        }
//    }

    public void handleOnEditCommit(TableColumn.CellEditEvent<ThongTinDipDacBietDisplayModel, ?> event) {
        ThongTinDipDacBietDisplayModel thongTinDipDacBietDisplayModel = event.getRowValue();
        DipTraoThuongModel dipTraoThuongModel = dipTraoThuongService.getDipTraoThuongByTenAndNam(
                thongTinDipDacBietDisplayModel.getTenDip(),
                thongTinDipDacBietDisplayModel.getNam()).get();
        Date today = new Date();
        if (today.after(dipTraoThuongModel.getNgayTao()) && today.equals(dipTraoThuongModel.getNgayKetThuc())) {
            int column = event.getTablePosition().getColumn();
            switch (column) {
                case 0:
                    thongTinDipDacBietDisplayModel.setMaNhanKhau((String) event.getNewValue());
                    break;
                case 4:
                    thongTinDipDacBietDisplayModel.setTinhTrang((String) event.getNewValue());
                    break;
            }
            updateThongTinDipDacBietCanBo(thongTinDipDacBietDisplayModel);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Dịp thành tích chưa bắt đầu hoặc đã kết thúc!");
            alert.showAndWait();
        }
    }

    public void handleOnEditCancel(TableColumn.CellEditEvent<ThongTinDipDacBietDisplayModel, ?> event) {
        ThongTinDipDacBietDisplayModel thongTinDipDacBietDisplayModel = event.getRowValue();
        int column = event.getTablePosition().getColumn();
        switch (column) {
            case 0:
                thongTinDipDacBietDisplayModel.setMaNhanKhau((String) event.getOldValue());
                break;
            case 4:
                thongTinDipDacBietDisplayModel.setTinhTrang((String) event.getOldValue());
                break;
        }
    }
}
