package com.se07.controller.controllers;

import com.se07.controller.services.HoKhauService;
import com.se07.model.models.HoKhauModel;
import com.se07.util.ComponentVisibility;
import com.se07.util.MyDateStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Lớp controller điều khiển màn hình quản lý hộ khẩu của tổ trưởng
 */
public class ControllerHoKhauCanBoView extends ControllerCanBoView {
    @FXML
    TableColumn<HoKhauModel, String> tableColumnMaHoHoKhauCanBo, tableColumnHotenHoKhauCanBo, tableColumnDiaChiHoKhauCanBo;
    @FXML
    TableColumn<HoKhauModel, Date> tableColumnNgayLapHoKhauCanBo;
    @FXML
    TableView<HoKhauModel> tableViewTatCaHoKhauCanBo;
    @FXML
    ComboBox comboBoxTimKiemHoKhauCanBo;
    @FXML
    DatePicker datePickerTu, datePickerDen;
    @FXML
    TextField textFieldLocThongTinHoKhauCanBo;
    private ObservableList<String> listTimKiem = FXCollections.observableArrayList(
            "Tên chủ hộ", "Mã hộ khẩu", "Địa chỉ", "Ngày lập");
    final HoKhauService hoKhauService = new HoKhauService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        tableColumnMaHoHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("maHoKhau"));
        tableColumnHotenHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("chuHo"));
        tableColumnDiaChiHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("diaChi"));
        tableColumnNgayLapHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, Date>("ngayLap"));

        comboBoxTimKiemHoKhauCanBo.getItems().addAll(listTimKiem);
        comboBoxTimKiemHoKhauCanBo.getSelectionModel().selectFirst();
        ComponentVisibility.change(datePickerTu, false);
        ComponentVisibility.change(datePickerDen, false);

        tableViewTatCaHoKhauCanBo.setEditable(true);
        tableColumnHotenHoKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnDiaChiHoKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnNgayLapHoKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn(new MyDateStringConverter("yyyy-MM-dd")));

        displayAllHoKhauCanBo();
    }

    @Override
    public void onPressedButtonHoKhauCanBo(MouseEvent e) throws IOException {
    }

    /**
     * Phương thức được gọi khi lựa chọn trường tìm kiếm
     * Tùy vào trường được chọn sẽ hiển thị giao diện lựa chọn phù hợp
     *
     * @param e Sự kiện hành động bắt được
     */
    public void onSelectionComboBoxTimKiemHoKhauCanBo(ActionEvent e) {
        String truongTimKiem = String.valueOf(comboBoxTimKiemHoKhauCanBo.getValue());
        if (truongTimKiem.equals("Ngày lập")) {
            ComponentVisibility.change(textFieldLocThongTinHoKhauCanBo, false);
            ComponentVisibility.change(datePickerTu, true);
            ComponentVisibility.change(datePickerDen, true);
        } else {
            ComponentVisibility.change(textFieldLocThongTinHoKhauCanBo, true);
            ComponentVisibility.change(datePickerTu, false);
            ComponentVisibility.change(datePickerDen, false);
        }
    }

    /**
     * Phương thức được gọi khi nhấn phím trong ô tìm kiếm
     * Nếu phím ENTER được nhấn sẽ thực hiện lọc thông tin theo trường đã chọn
     *
     * @param keyEvent Sự kiện phím bắt được
     */
    public void onEnterPressedTrongOTimKiemHoKhauCanBo(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            locThongTinHoKhauCanBo();
        }
    }

    /**
     * Phương thức được gọi khi nhấn phím trong bảng hộ khẩu
     * Nếu phím DELETE được nhấn sẽ thực hiện xóa hộ khẩu
     *
     * @param keyEvent Sự kiện phím bắt được
     */
    public void onDeletePressedTrongBangHoKhauCanBo(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.DELETE) {
            xoaHoKhauCanBo();
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút tìm kiếm
     * Nếu chuột trái được nhấn sẽ thực hiện lọc thông tin hộ khẩu
     *
     * @param e Sự kiện chuột bắt được
     */
    public void onPressedButtonLocThongTinHoKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinHoKhauCanBo();
        }
    }

    /**
     * Phương thức đươc gọi khi nhấn nút thêm mới
     * Nếu chuột trái được nhấn sẽ chuyển sang màn hình thêm mới hộ khẩu
     *
     * @param e Sự kiện chuột bắt được
     * @throws IOException
     */
    public void onPressedButtonThemHoKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "ThemMoiHoKhauCanBoView.fxml");
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút tách hộ khẩu
     * Nếu chuột trái được nhấn sẽ chuyển sang màn hình tách hộ khẩu
     *
     * @param e Sự kiện chuột bắt được
     * @throws IOException
     */
    public void onPressedButtonTachHoKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "TachHoKhauCanBoView.fxml");
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút xóa
     * Nếu chuột trái được nhấn sẽ thực hiện xóa hộ khẩu
     *
     * @param e Sự kiện chuột bắt được
     */
    public void onPressedButtonXoaHoKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaHoKhauCanBo();
        }
    }

    /**
     * Phương thức hiển thị tất cả hộ khẩu
     */
    private void displayAllHoKhauCanBo() {
        ObservableList<HoKhauModel> hoKhauModelObservableList = hoKhauService.getAllHoKhau();
        tableViewTatCaHoKhauCanBo.setItems(hoKhauModelObservableList);
    }

    /**
     * Phương thức xử lý sự kiện khi một ô trong bảng được thay đổi
     *
     * @param event Sự kiện ô được thay đổi trong bảng
     */
    public void handleOnEditCommit(TableColumn.CellEditEvent<HoKhauModel, ?> event) {
        int column = event.getTablePosition().getColumn();
        HoKhauModel hoKhauModel = event.getRowValue();
        switch (column) {
            case 1:
                hoKhauModel.setChuHo((String) event.getNewValue());
                break;
            case 2:
                hoKhauModel.setDiaChi((String) event.getNewValue());
                break;
            case 3:
                Date ngayLapMoi = (Date) event.getNewValue();
                if (ngayLapMoi != null) {
                    hoKhauModel.setNgayLap(ngayLapMoi);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập ngày sinh hợp lệ đúng định dạng năm-tháng-ngày");
                    alert.showAndWait();
                    displayAllHoKhauCanBo();
                    return;
                }
                break;
        }
        updateHoKhauCanBo(hoKhauModel);
    }

    /**
     * Phương thức xử lý sự kiện hủy thay đổi một ô trong bảng
     *
     * @param event Sự kiện ô được thay đổi trong bảng
     */
    public void handleOnEditCancel(TableColumn.CellEditEvent<HoKhauModel, ?> event) {
        int column = event.getTablePosition().getColumn();
        HoKhauModel hoKhauModel = event.getRowValue();
        switch (column) {
            case 1:
                hoKhauModel.setChuHo((String) event.getOldValue());
                break;
            case 2:
                hoKhauModel.setDiaChi((String) event.getOldValue());
                break;
            case 3:
                hoKhauModel.setNgayLap((Date) event.getOldValue());
                break;
        }
    }

    /**
     * Phương thức cập nhật thông tin hộ khẩu
     *
     * @param hoKhauModel Lớp chứa thông tin của hộ khẩu muốn thay đổi
     */
    private void updateHoKhauCanBo(HoKhauModel hoKhauModel) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        if (hoKhauService.updateHoKhau(hoKhauModel)) {
            alert.setHeaderText("Sửa hộ khẩu thành công");
        } else {
            alert.setHeaderText("Sửa hộ khẩu không thành công");
        }
        if (alert.showAndWait().get() == ButtonType.OK) {
            displayAllHoKhauCanBo();
        }
    }

    /**
     * Phương thức xóa hộ khẩu được chọn trong bảng hiển thị
     * Nếu không có hộ khẩu nào được chọn sẽ thông báo cho người dùng
     */
    private void xoaHoKhauCanBo() {
        HoKhauModel hoKhauModel = tableViewTatCaHoKhauCanBo.getSelectionModel().getSelectedItem();
        if (hoKhauModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn hộ khẩu muốn xóa");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Bạn chắc chắn muốn xóa hộ khẩu?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Thông báo");
                if (hoKhauService.deleteHoKhau(hoKhauModel)) {
                    info.setHeaderText("Xóa thành công!");
                } else {
                    info.setHeaderText("Xóa không thành công!");
                }
                info.showAndWait();
                displayAllHoKhauCanBo();
            }
        }
    }

    /**
     * Phương thức tìm kiếm hộ khẩu theo trường tiêu chí đã chọn
     * Các trường tìm kiếm hợp lệ là Tên chủ hộ, Mã hộ khẩu, Địa chỉ, Ngày lập
     */
    private void locThongTinHoKhauCanBo() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemHoKhauCanBo.getValue());
        String cauHoi = textFieldLocThongTinHoKhauCanBo.getText();
        ObservableList<HoKhauModel> hoKhauModelObservableList = FXCollections.observableArrayList();
        switch (dieuKienKiemTra) {
            case "Địa chỉ":
                hoKhauModelObservableList = hoKhauService.getHoKhauByDiaChi(cauHoi);
                break;
            case "Tên chủ hộ":
                hoKhauModelObservableList = hoKhauService.getHoKhauByChuHo(cauHoi);
                break;
            case "Ngày lập":
                if (datePickerTu.getValue() != null && datePickerDen.getValue() != null) {
                    hoKhauModelObservableList = hoKhauService.getHoKhauByNgayLapBetween(
                            Date.from(datePickerTu.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                            Date.from(datePickerDen.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
                    alert.showAndWait();
                    displayAllHoKhauCanBo();
                    return;
                }
                break;
            case "Mã hộ khẩu":
                Optional<HoKhauModel> hoKhauModel = hoKhauService.getHoKhauByMaHoKhau(cauHoi);
                if (hoKhauModel.isPresent()) {
                    hoKhauModelObservableList.add(hoKhauModel.get());
                }
                break;
        }
        tableViewTatCaHoKhauCanBo.setItems(hoKhauModelObservableList);
    }
}
