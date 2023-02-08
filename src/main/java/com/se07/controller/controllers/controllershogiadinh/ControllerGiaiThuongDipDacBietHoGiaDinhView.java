package com.se07.controller.controllers.controllershogiadinh;
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

public class ControllerGiaiThuongDipDacBietHoGiaDinhView extends ControllerHoGiaDinhView {
    @FXML
    ComboBox comboBoxTimKiemDipDacBietHoGiaDinh, comboBoxTenNamDipDacBietHoGiaDinh,
            comboBoxMaNhanKhauDipDacBietHoGiaDinh, comboBoxTinhTrangDipDacBietHoGiaDinh;
    @FXML
    TableView<ThongTinDipDacBietDisplayModel> tableViewDipDacBietHoGiaDinh;
    @FXML
    TableColumn<ThongTinDipDacBietDisplayModel, String> tableColumnMaNhanKhauDipDacBietHoGiaDinh,
            tableColumnHoTenDipDacBietHoGiaDinh, tableColumnTenDipDacBietHoGiaDinh, tableColumnTinhTrangDipDacBietHoGiaDinh;
    @FXML
    TableColumn<ThongTinDipDacBietDisplayModel, Integer> tableColumnNamDipDacBietHoGiaDinh;
    @FXML
    TextField textFieldLocThongTinDipDacBietHoGiaDinh;

    private final DipTraoThuongService dipTraoThuongService = new DipTraoThuongService();
    private final ThongTinDipDacBietService thongTinDipDacBietService = new ThongTinDipDacBietService();
    final private MyIntegerStringConverter integerStringConverter = new MyIntegerStringConverter();
    final ObservableList<String> listTimKiem = FXCollections.observableArrayList(
            "Mã nhân khẩu", "Họ tên", "Tên dịp", "Năm", "Tên - Năm", "Tình trạng");
    final ObservableList<String> listTenNamDipTraoThuong = dipTraoThuongService.getAllTenNamDipDacBiet();
    final ObservableList<String> listMaNhanKhau = new NhanKhauService().getAllMaNhanKhauTrongHoKhau(maHoKhauDangNhap);
    final ObservableList<String> listTinhTrang =
            FXCollections.observableArrayList("Chờ xác nhận", "Đã xác nhận", "Đã từ chối");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        tableColumnMaNhanKhauDipDacBietHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        tableColumnHoTenDipDacBietHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        tableColumnTenDipDacBietHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("tenDip"));
        tableColumnNamDipDacBietHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("nam"));
        tableColumnTinhTrangDipDacBietHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("tinhTrang"));

        comboBoxTimKiemDipDacBietHoGiaDinh.getItems().addAll(listTimKiem);
        comboBoxTimKiemDipDacBietHoGiaDinh.getSelectionModel().selectFirst();
        comboBoxTenNamDipDacBietHoGiaDinh.getItems().addAll(listTenNamDipTraoThuong);
        comboBoxTenNamDipDacBietHoGiaDinh.getSelectionModel().selectFirst();
        comboBoxMaNhanKhauDipDacBietHoGiaDinh.getItems().addAll(listMaNhanKhau);
        comboBoxMaNhanKhauDipDacBietHoGiaDinh.getSelectionModel().selectFirst();
        comboBoxTinhTrangDipDacBietHoGiaDinh.getItems().addAll(listTinhTrang);
        comboBoxTinhTrangDipDacBietHoGiaDinh.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxTenNamDipDacBietHoGiaDinh, false);
        ComponentVisibility.change(comboBoxMaNhanKhauDipDacBietHoGiaDinh, false);
        ComponentVisibility.change(comboBoxTinhTrangDipDacBietHoGiaDinh, false);

        tableViewDipDacBietHoGiaDinh.setEditable(true);
        tableColumnMaNhanKhauDipDacBietHoGiaDinh.setCellFactory(t -> new ComboBoxTableCell<>(listMaNhanKhau));
        tableColumnTinhTrangDipDacBietHoGiaDinh.setCellFactory(t -> new ComboBoxTableCell<>(listTinhTrang));

        displayAllThongTinDipDacBietHoGiaDinh();
    }

    public void onSelectionComboBoxTimKiemDipDacBietHoGiaDinh(ActionEvent e) {
        String truongTimKiem = String.valueOf(comboBoxTimKiemDipDacBietHoGiaDinh.getValue());
        ComponentVisibility.change(textFieldLocThongTinDipDacBietHoGiaDinh, false);
        ComponentVisibility.change(comboBoxTenNamDipDacBietHoGiaDinh, false);
        ComponentVisibility.change(comboBoxMaNhanKhauDipDacBietHoGiaDinh, false);
        ComponentVisibility.change(comboBoxTinhTrangDipDacBietHoGiaDinh, false);
        switch (truongTimKiem) {
            case "Mã nhân khẩu":
                ComponentVisibility.change(comboBoxMaNhanKhauDipDacBietHoGiaDinh, true);
                break;
            case "Tên - năm":
                ComponentVisibility.change(comboBoxTenNamDipDacBietHoGiaDinh, true);
                break;
            case "Tình trạng":
                ComponentVisibility.change(comboBoxTinhTrangDipDacBietHoGiaDinh, true);
                break;
            default:
                ComponentVisibility.change(textFieldLocThongTinDipDacBietHoGiaDinh, true);
                break;
        }
    }

    public void onPressedButtonThemMoiThongTinDipDacBietHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "ThemMoiThongTinDipDacBietHoGiaDinhView.fxml");
        }
    }

    public void onPressedButtonLocThongTinDipDacBietHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinDipDacBietHoGiaDinh();
        }
    }

    public void onEnterPressedTrongOTimKiemDipDacBietHoGiaDinh(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            locThongTinDipDacBietHoGiaDinh();
        }
    }
    public void onDeletePressedTrongBangThongTinDipDacBietHoGiaDinh(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.DELETE)) {
            xoaThongTinDipDacBietHoGiaDinh();
        }
    }

    public void onPressedButtonXoaThongTinDipDacBietHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaThongTinDipDacBietHoGiaDinh();
        }
    }

    private void xoaThongTinDipDacBietHoGiaDinh() {
        ThongTinDipDacBietDisplayModel thongTinDipDacBietDisplayModel =
                tableViewDipDacBietHoGiaDinh.getSelectionModel().getSelectedItem();
        if (thongTinDipDacBietDisplayModel == null) {
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
                if (thongTinDipDacBietService.deleteThongTinDipDacBiet(thongTinDipDacBietDisplayModel)) {
                    info.setHeaderText("Xóa thành công!");
                } else {
                    info.setHeaderText("Xóa không thành công!");
                }
                info.showAndWait();
                displayAllThongTinDipDacBietHoGiaDinh();
            }
        }
    }
    public void locThongTinDipDacBietHoGiaDinh() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemDipDacBietHoGiaDinh.getValue());
        String cauHoi = textFieldLocThongTinDipDacBietHoGiaDinh.getText();
        ObservableList<ThongTinDipDacBietDisplayModel> listThongTinDipDacBiet = FXCollections.observableArrayList();
        switch (dieuKienKiemTra) {
            case "Mã nhân khẩu":
                listThongTinDipDacBiet = thongTinDipDacBietService.getAllThongTinDipDacBietByMaNhanKhauAndMaHoKhau(
                        String.valueOf(comboBoxMaNhanKhauDipDacBietHoGiaDinh.getValue()), maHoKhauDangNhap
                );
                break;
            case "Họ tên":
                listThongTinDipDacBiet = thongTinDipDacBietService.getAllThongTinDipDacBietByHoTenAndMaHoKhau(cauHoi, maHoKhauDangNhap);
                break;
            case "Tên dịp":
                listThongTinDipDacBiet = thongTinDipDacBietService.getAllThongTinDipDacBietByTenDipAndMaHoKhau(cauHoi, maHoKhauDangNhap);
                break;
            case "Năm":
                int nam = integerStringConverter.fromString(cauHoi);
                if (nam == -1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập năm hợp lệ");
                    alert.showAndWait();
                    displayAllThongTinDipDacBietHoGiaDinh();
                    textFieldLocThongTinDipDacBietHoGiaDinh.requestFocus();
                    return;
                }
                listThongTinDipDacBiet = thongTinDipDacBietService.getAllThongTinDipDacBietByNamAndByMaHoKhau(nam, maHoKhauDangNhap);
                break;
            case "Tên - Năm":
                System.out.println(1111);
                String tenNam = String.valueOf(comboBoxTenNamDipDacBietHoGiaDinh.getValue());
                int index = tenNam.indexOf(" - ");
                String tenDip = tenNam.substring(0, index);
                int namDip = Integer.parseInt(tenNam.substring(index + 3));
                int id = dipTraoThuongService.getDipTraoThuongByTenAndNam(tenDip, namDip).get().getId();
                listThongTinDipDacBiet = thongTinDipDacBietService.getAllThongTinDipDacBietByIdAndMaHoKhau(id, maHoKhauDangNhap);
                break;
            case "Tình trạng":
                listThongTinDipDacBiet = thongTinDipDacBietService.getAllThongTinDipDacBietByTinhTrangAndMaHoKhau(
                        String.valueOf(comboBoxTinhTrangDipDacBietHoGiaDinh.getValue()), maHoKhauDangNhap
                );
                break;
        }
        tableViewDipDacBietHoGiaDinh.setItems(listThongTinDipDacBiet);
    }

    private void displayAllThongTinDipDacBietHoGiaDinh() {
        ObservableList<ThongTinDipDacBietDisplayModel> thongTinDipDacBietDisplayModels =
                thongTinDipDacBietService.getAllThongTinDipDacBietByMaHoKhau(maHoKhauDangNhap);
        tableViewDipDacBietHoGiaDinh.setItems(thongTinDipDacBietDisplayModels);
    }

    private void updateThongTinDipDacBietHoGiaDinh(ThongTinDipDacBietDisplayModel thongTinDipDacBietDisplayModel) {
        ThongTinDipDacBietModel thongTinDipDacBietModel =
                thongTinDipDacBietService.convertDisplayModelToModel(thongTinDipDacBietDisplayModel);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        if (thongTinDipDacBietService.updateThongTinDipDacBiet(thongTinDipDacBietModel)) {
            alert.setHeaderText("Sửa thông tin thành công");
        } else {
            alert.setHeaderText("Sửa thông tin không thành công");
        }
        if (alert.showAndWait().get() == ButtonType.OK) {
            displayAllThongTinDipDacBietHoGiaDinh();
        }
    }

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
            updateThongTinDipDacBietHoGiaDinh(thongTinDipDacBietDisplayModel);
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


    public void onPressedButtonThoatThongTinDipDacBietHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn thoát?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(),
                        "GiaiThuongHoGiaDinhView.fxml");
            }
        }
    }
}