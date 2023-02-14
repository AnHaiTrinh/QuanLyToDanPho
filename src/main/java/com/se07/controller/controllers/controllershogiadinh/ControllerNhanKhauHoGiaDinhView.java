package com.se07.controller.controllers.controllershogiadinh;

import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.NhanKhauService;
import com.se07.model.models.NhanKhauModel;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerNhanKhauHoGiaDinhView extends ControllerHoGiaDinhView implements Initializable {
    @FXML
    TableView<NhanKhauModel> tableViewNhanKhauHoGiaDinh;
    @FXML
    TableColumn<NhanKhauModel, String> tableComlumIDNhanKhauHoGiaDinh, tableComlumIDHoKhauNhanKhauHoGiaDinh,
            tableComlumHoTenNhanKhauHoGiaDinh, tableComlumBietDanhNhanKhauHoGiaDinh, tableComlumGioiTinhNhanKhauHoGiaDinh,
            tableComlumTonGiaoNhanKhauHoGiaDinh, tableComlumTinhTrangNhanKhauHoGiaDinh;
    @FXML
    TableColumn<NhanKhauModel, Date> tableComlumNgaySinhNhanKhauHoGiaDinh;
    @FXML
    ComboBox comboBoxTimKiemNhanKhauHoGiaDinh, comboBoxGioiTinhNhanKhauHoGiaDinh, comboBoxTinhTrangNhanKhauHoGiaDinh;
    @FXML
    TextField textFieldLocThongTinNhanKhauHoGiaDinh;
    final private ObservableList<String> listTimKiem = FXCollections.observableArrayList(
            "Mã nhân khẩu", "Họ tên", "Biệt danh", "Giới tính", "Tôn giáo", "Tình trạng");
    final private ObservableList<String> listGioiTinh = FXCollections.observableArrayList("Nam", "Nữ");

    final private ObservableList<String> listTinhTrang =
            FXCollections.observableArrayList("Chờ xác nhận", "Đã xác nhận", "Đã từ chối", "Chờ xóa");
    final NhanKhauService nhanKhauService = new NhanKhauService();

    final private MyDateStringConverter dateStringConverter = new MyDateStringConverter("yyyy-MM-dd");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        tableComlumIDNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("maNhanKhau"));
        tableComlumIDHoKhauNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("maHoKhau"));
        tableComlumHoTenNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("hoTen"));
        tableComlumBietDanhNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("bietDanh"));
        tableComlumNgaySinhNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, Date>("ngaySinh"));
        tableComlumGioiTinhNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("gioiTinh"));
        tableComlumTonGiaoNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("tonGiao"));
        tableComlumTinhTrangNhanKhauHoGiaDinh.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("tinhTrang"));

        comboBoxTimKiemNhanKhauHoGiaDinh.getItems().addAll(listTimKiem);
        comboBoxTimKiemNhanKhauHoGiaDinh.getSelectionModel().selectFirst();
        comboBoxGioiTinhNhanKhauHoGiaDinh.getItems().addAll(listGioiTinh);
        comboBoxGioiTinhNhanKhauHoGiaDinh.getSelectionModel().selectFirst();
        comboBoxTinhTrangNhanKhauHoGiaDinh.getItems().addAll(listTinhTrang);
        comboBoxTinhTrangNhanKhauHoGiaDinh.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxGioiTinhNhanKhauHoGiaDinh, false);
        ComponentVisibility.change(comboBoxTinhTrangNhanKhauHoGiaDinh, false);

        tableViewNhanKhauHoGiaDinh.setEditable(true);
        tableComlumHoTenNhanKhauHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn());
        tableComlumBietDanhNhanKhauHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn());
        tableComlumNgaySinhNhanKhauHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn(dateStringConverter));
        tableComlumGioiTinhNhanKhauHoGiaDinh.setCellFactory(t -> new ComboBoxTableCell<>(listGioiTinh));
        tableComlumTonGiaoNhanKhauHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn());

        tableViewNhanKhauHoGiaDinh.setItems(nhanKhauService.getAllNhanKhauTrongHoKhau(maHoKhauDangNhap));
    }

    /**
     * Phương thức được gọi khi lựa chọn trường tìm kiếm
     * Tùy vào trường được chọn sẽ hiển thị giao diện lựa chọn phù hợp
     *
     * @param e Sự kiện hành động bắt được
     */
    public void onSelectionComboBoxTimKiemTamVangHoGiaDinh(ActionEvent e) {
        String truongTimKiem = String.valueOf(comboBoxTimKiemNhanKhauHoGiaDinh.getValue());
        if (truongTimKiem.equals("Giới tính")) {
            ComponentVisibility.change(textFieldLocThongTinNhanKhauHoGiaDinh, false);
            ComponentVisibility.change(comboBoxTinhTrangNhanKhauHoGiaDinh, false);
            ComponentVisibility.change(comboBoxGioiTinhNhanKhauHoGiaDinh, true);

        } else if (truongTimKiem.equals("Tình trạng")) {
            ComponentVisibility.change(textFieldLocThongTinNhanKhauHoGiaDinh, false);
            ComponentVisibility.change(comboBoxTinhTrangNhanKhauHoGiaDinh, true);
            ComponentVisibility.change(comboBoxGioiTinhNhanKhauHoGiaDinh, false);

        } else {
            ComponentVisibility.change(textFieldLocThongTinNhanKhauHoGiaDinh, true);
            ComponentVisibility.change(comboBoxTinhTrangNhanKhauHoGiaDinh, false);
            ComponentVisibility.change(comboBoxGioiTinhNhanKhauHoGiaDinh, false);
        }
    }

    @Override
    public void onPressedButtonNhanKhauHoGiaDinh(MouseEvent e) {
    }

    /**
     * Phương thức được gọi khi nhấn phím trong ô tìm kiếm
     * Nếu phím ENTER được nhấn sẽ thực hiện lọc thông tin theo trường đã chọn
     *
     * @param keyEvent Sự kiện phím bắt được
     */
    public void onEnterPressedTrongOTimKiemNhanKhauHoGiaDinh(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            locThongTinNhanKhauHoGiaDinh();
        }
    }

    /**
     * Phương thức đươc gọi khi nhấn nút thêm mới
     * Nếu chuột trái được nhấn sẽ chuyển sang màn hình thêm mới nhân khẩu
     *
     * @param e Sự kiện chuột bắt được
     * @throws IOException
     */
    public void onPressedButtonThemMoiNhanKhauHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "ThemMoiNhanKhauHoGiaDinhView.fxml");
        }
    }

    /**
     * Phương thức đươc gọi khi nhấn nút Tạm vắng
     * Nếu chuột trái được nhấn sẽ chuyển sang màn hình quản lý tạm vắng
     *
     * @param e Sự kiện chuột bắt được
     * @throws IOException
     */
    public void onPressedButtonTamVangHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "TamVangHoGiaDinhView.fxml");
        }
    }

    /**
     * Phương thức đươc gọi khi nhấn nút tạm trú
     * Nếu chuột trái được nhấn sẽ chuyển sang màn hình quản lý tạm trú
     *
     * @param e Sự kiện chuột bắt được
     * @throws IOException
     */
    public void onPressedButtonTamTruHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(), "TamTruHoGiaDinhView.fxml");
        }
    }

    /**
     * Phương thức được gọi khi nhấn phím trong bảng nhân khẩu
     * Nếu phím DELETE được nhấn sẽ thực hiện xóa nhân khẩu
     *
     * @param e Sự kiện phím bắt được
     */
    public void onDeletePressedTrongBangNhanKhauHoGiaDinh(KeyEvent e) {
        if (e.getCode() == KeyCode.DELETE) {
            xoaNhanKhauHoGiaDinh();
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút xóa
     * Nếu chuột trái được nhấn sẽ thực hiện xóa nhân khẩu
     *
     * @param e Sự kiện chuột bắt được
     */
    public void onPressedButtonXoaNhanKhauHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaNhanKhauHoGiaDinh();
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút tìm kiếm
     * Nếu chuột trái được nhấn sẽ thực hiện lọc thông tin nhân khẩu
     *
     * @param e Sự kiện chuột bắt được
     */
    public void onPressedButtonLocThongTinNhanKhauHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinNhanKhauHoGiaDinh();
        }
    }

    /**
     * Phương thức xóa hộ khẩu được chọn trong bảng hiển thị
     * Nếu không có hộ khẩu nào được chọn sẽ thông báo cho người dùng
     */
    private void xoaNhanKhauHoGiaDinh() {
        NhanKhauModel nhanKhauModel = tableViewNhanKhauHoGiaDinh.getSelectionModel().getSelectedItem();
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
                nhanKhauModel.setTinhTrang("Chờ xóa");
                updateNhanKhauHoGiaDinh(nhanKhauModel);
            }
        }
    }

    /**
     * Phương thức tìm kiếm nhân khẩu theo trường tiêu chí đã chọn
     * Các trường tìm kiếm hợp lệ là Mã nhân khẩu, Mã hộ khẩu, Họ tên, Biệt danh, Ngày sinh, Giới tính, Tôn giáo, Tình trạng
     */
    private void locThongTinNhanKhauHoGiaDinh() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemNhanKhauHoGiaDinh.getValue());
        String cauHoi = textFieldLocThongTinNhanKhauHoGiaDinh.getText();
        ObservableList<NhanKhauModel> nhanKhauModelObservableList = FXCollections.observableArrayList();
        switch (dieuKienKiemTra) {
            case "Mã nhân khẩu":
                Optional<NhanKhauModel> nhanKhauModel = nhanKhauService.getNhanKhauByMaNhanKhauAndMaHoKhau(cauHoi, maHoKhauDangNhap);
                if (nhanKhauModel.isPresent()) {
                    nhanKhauModelObservableList.add(nhanKhauModel.get());
                }
                break;
            case "Họ tên":
                nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByTenAndMaHoKhau(cauHoi, maHoKhauDangNhap);
                break;
            case "Biệt danh":
                nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByBietDanhAndMaHoKhau(cauHoi, maHoKhauDangNhap);
                break;
            case "Giới tính":
                nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByGioiTinhAndMaHoKhau(
                        String.valueOf(comboBoxGioiTinhNhanKhauHoGiaDinh.getValue()), maHoKhauDangNhap
                );
                break;
            case "Tôn giáo":
                nhanKhauModelObservableList = nhanKhauService.getNhanKhauByTonGiaoAndMaHoKhau(cauHoi, maHoKhauDangNhap);
                break;
            case "Tình trạng":
                nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByTinhTrangAndMaHoKhau(
                        String.valueOf(comboBoxTinhTrangNhanKhauHoGiaDinh.getValue()), maHoKhauDangNhap);
                break;
        }
        tableViewNhanKhauHoGiaDinh.setItems(nhanKhauModelObservableList);
    }

    /**
     * Phương thức xử lý sự kiện khi một ô trong bảng được thay đổi
     *
     * @param event Sự kiện ô được thay đổi trong bảng
     */
    public void handleOnEditCommit(TableColumn.CellEditEvent<NhanKhauModel, ?> event) {
        int column = event.getTablePosition().getColumn();
        NhanKhauModel nhanKhauModel = event.getRowValue();
        switch (column) {
            case 2:
                nhanKhauModel.setHoTen((String) event.getNewValue());
                break;
            case 3:
                nhanKhauModel.setBietDanh((String) event.getNewValue());
                break;
            case 4:
                Date ngaySinhMoi = (Date) event.getNewValue();
                if (ngaySinhMoi != null) {
                    nhanKhauModel.setNgaySinh(ngaySinhMoi);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập ngày sinh hợp lệ đúng định dạng năm-tháng-ngày");
                    alert.showAndWait();
                    nhanKhauModel.setNgaySinh((Date) event.getOldValue());
                    tableViewNhanKhauHoGiaDinh.refresh();
                    return;
                }
                break;
            case 5:
                nhanKhauModel.setGioiTinh((String) event.getNewValue());
                break;
            case 6:
                nhanKhauModel.setTonGiao((String) event.getNewValue());
                break;
        }
        nhanKhauModel.setTinhTrang(tinhTrang);
        updateNhanKhauHoGiaDinh(nhanKhauModel);
    }

    /**
     * Phương thức xử lý sự kiện khi một ô trong bảng được thay đổi
     *
     * @param event Sự kiện ô được thay đổi trong bảng
     */
    public void handleOnEditCancel(TableColumn.CellEditEvent<NhanKhauModel, ?> event) {
        int column = event.getTablePosition().getColumn();
        NhanKhauModel nhanKhauModel = event.getRowValue();
        switch (column) {
            case 2:
                nhanKhauModel.setHoTen((String) event.getOldValue());
                break;
            case 3:
                nhanKhauModel.setBietDanh((String) event.getOldValue());
                break;
            case 4:
                nhanKhauModel.setNgaySinh((Date) event.getOldValue());
                break;
            case 5:
                nhanKhauModel.setGioiTinh((String) event.getOldValue());
                break;
            case 6:
                nhanKhauModel.setTonGiao((String) event.getOldValue());
                break;
        }
    }

    /**
     * Phương thức cập nhật thông tin nhân khẩu
     *
     * @param nhanKhauModel Lớp chứa thông tin của nhân khẩu muốn thay đổi
     */
    private void updateNhanKhauHoGiaDinh(NhanKhauModel nhanKhauModel) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        if (nhanKhauService.updateNhanKhau(nhanKhauModel)) {
            alert.setHeaderText("Gửi yêu cầu thành công");
        } else {
            alert.setHeaderText("Gửi yêu cầu không thành công");
        }
        if (alert.showAndWait().get() == ButtonType.OK) {
            tableViewNhanKhauHoGiaDinh.refresh();
        }
    }
}
