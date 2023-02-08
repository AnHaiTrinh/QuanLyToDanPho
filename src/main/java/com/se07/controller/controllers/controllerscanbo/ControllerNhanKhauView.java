package com.se07.controller.controllers.controllerscanbo;

import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.NhanKhauService;
import com.se07.model.models.NhanKhauModel;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Lớp controller điều khiển màn hình quản lý nhân khẩu của tổ trưởng
 */
public class ControllerNhanKhauView extends ControllerCanBoView {
    @FXML
    TableView<NhanKhauModel> tableViewNhanKhauCanBo;
    @FXML
    TableColumn<NhanKhauModel, String> tableComlumIDNhanKhauCanBo, tableComlumIDHoKhauNhanKhauCanBo,
            tableComlumHoTenNhanKhauCanBo, tableComlumBietDanhNhanKhauCanBo, tableComlumGioiTinhNhanKhauCanBo,
            tableComlumTonGiaoNhanKhauCanBo, tableComlumTinhTrangNhanKhauCanBo;
    @FXML
    TableColumn<NhanKhauModel, Date> tableComlumNgaySinhNhanKhauCanBo;
    @FXML
    ComboBox comboBoxTimKiemNhanKhauCanBo, comboBoxGioiTinhNhanKhauCanBo, comboBoxTinhTrangNhanKhauCanBo;
    @FXML
    TextField textFieldLocThongTinNhanKhauCanBo;
    @FXML
    DatePicker datePickerTu, datePickerDen;
    final private ObservableList<String> listTimKiem = FXCollections.observableArrayList(
            "Mã nhân khẩu", "Mã hộ khẩu", "Họ tên", "Biệt danh", "Ngày sinh", "Giới tính", "Tôn giáo", "Tình trạng");

    private final ObservableList<String> listMaHoKhau = new HoKhauService().getAllMaHoKhau();
    final private ObservableList<String> listGioiTinh = FXCollections.observableArrayList("Nam", "Nữ");

    final private ObservableList<String> listTinhTrang =
            FXCollections.observableArrayList("Chờ xác nhận", "Đã xác nhận", "Đã từ chối");
    final private NhanKhauService nhanKhauService = new NhanKhauService();

    final private MyDateStringConverter dateStringConverter = new MyDateStringConverter("yyyy-MM-dd");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        tableComlumIDNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("maNhanKhau"));
        tableComlumIDHoKhauNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("maHoKhau"));
        tableComlumHoTenNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("hoTen"));
        tableComlumBietDanhNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("bietDanh"));
        tableComlumNgaySinhNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, Date>("ngaySinh"));
        tableComlumGioiTinhNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("gioiTinh"));
        tableComlumTonGiaoNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("tonGiao"));
        tableComlumTinhTrangNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("tinhTrang"));

        comboBoxTimKiemNhanKhauCanBo.getItems().addAll(listTimKiem);
        comboBoxTimKiemNhanKhauCanBo.getSelectionModel().selectFirst();
        comboBoxGioiTinhNhanKhauCanBo.getItems().addAll(listGioiTinh);
        comboBoxGioiTinhNhanKhauCanBo.getSelectionModel().selectFirst();
        comboBoxTinhTrangNhanKhauCanBo.getItems().addAll(listTinhTrang);
        comboBoxTinhTrangNhanKhauCanBo.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxGioiTinhNhanKhauCanBo, false);
        ComponentVisibility.change(comboBoxTinhTrangNhanKhauCanBo, false);
        ComponentVisibility.change(datePickerTu, false);
        ComponentVisibility.change(datePickerDen, false);

        tableViewNhanKhauCanBo.setEditable(true);
        tableComlumIDHoKhauNhanKhauCanBo.setCellFactory(t -> new ComboBoxTableCell(listMaHoKhau));
        tableComlumHoTenNhanKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableComlumBietDanhNhanKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableComlumNgaySinhNhanKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn(dateStringConverter));
        tableComlumGioiTinhNhanKhauCanBo.setCellFactory(t -> new ComboBoxTableCell<>(listGioiTinh));
        tableComlumTonGiaoNhanKhauCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableComlumTinhTrangNhanKhauCanBo.setCellFactory(t -> new ComboBoxTableCell<>(listTinhTrang));

        displayAllNhanKhauCanBo();
    }

    /**
     * Phương thức được gọi khi lựa chọn trường tìm kiếm
     * Tùy vào trường được chọn sẽ hiển thị giao diện lựa chọn phù hợp
     *
     * @param e Sự kiện hành động bắt được
     */
    public void onSelectionComboBoxTimKiemTamVangCanBo(ActionEvent e) {
        ComponentVisibility.change(textFieldLocThongTinNhanKhauCanBo, false);
        ComponentVisibility.change(comboBoxTinhTrangNhanKhauCanBo, false);
        ComponentVisibility.change(comboBoxGioiTinhNhanKhauCanBo, false);
        ComponentVisibility.change(datePickerTu, false);
        ComponentVisibility.change(datePickerDen, false);
        String truongTimKiem = String.valueOf(comboBoxTimKiemNhanKhauCanBo.getValue());
        if (truongTimKiem.equals("Ngày sinh")) {
            ComponentVisibility.change(datePickerTu, true);
            ComponentVisibility.change(datePickerDen, true);
        } else if (truongTimKiem.equals("Giới tính")) {
            ComponentVisibility.change(comboBoxGioiTinhNhanKhauCanBo, true);
        } else if (truongTimKiem.equals("Tình trạng")) {
            ComponentVisibility.change(comboBoxTinhTrangNhanKhauCanBo, true);
        } else {
            ComponentVisibility.change(textFieldLocThongTinNhanKhauCanBo, true);
        }
    }

    @Override
    public void onPressedButtonNhanKhauCanBo(MouseEvent e) {
    }

    /**
     * Phương thức được gọi khi nhấn phím trong ô tìm kiếm
     * Nếu phím ENTER được nhấn sẽ thực hiện lọc thông tin theo trường đã chọn
     *
     * @param keyEvent Sự kiện phím bắt được
     */
    public void onEnterPressedTrongOTimKiemNhanKhauCanBo(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            locThongTinNhanKhauCanBo();
        }
    }

    /**
     * Phương thức đươc gọi khi nhấn nút thêm mới
     * Nếu chuột trái được nhấn sẽ chuyển sang màn hình thêm mới nhân khẩu
     *
     * @param e Sự kiện chuột bắt được
     * @throws IOException
     */
    public void onPressedButtonThemMoiNhanKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "ThemMoiNhanKhauCanBoView.fxml");
        }
    }

    /**
     * Phương thức đươc gọi khi nhấn nút Tạm vắng
     * Nếu chuột trái được nhấn sẽ chuyển sang màn hình quản lý tạm vắng
     *
     * @param e Sự kiện chuột bắt được
     * @throws IOException
     */
    public void onPressedButtonTamVangCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "TamVangCanBo.fxml");
        }
    }

    /**
     * Phương thức đươc gọi khi nhấn nút tạm trú
     * Nếu chuột trái được nhấn sẽ chuyển sang màn hình quản lý tạm trú
     *
     * @param e Sự kiện chuột bắt được
     * @throws IOException
     */
    public void onPressedButtonTamTruCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "TamTruCanBo.fxml");
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút xác nhận
     * Nếu chuột trái được nhấn sẽ thực hiện xác nhận nhân khẩu
     *
     * @param e Sự kiện chuột bắt được
     */
    public void onPressedButtonXacNhanNhanKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xacNhanNhanKhauCanBo();
        }
    }

    /**
     * Phương thức được gọi khi nhấn phím trong bảng nhân khẩu
     * Nếu phím DELETE được nhấn sẽ thực hiện xóa nhân khẩu
     *
     * @param e Sự kiện phím bắt được
     */
    public void onDeletePressedTrongBangNhanKhauCanBo(KeyEvent e) {
        if (e.getCode() == KeyCode.DELETE) {
            xoaNhanKhauCanBo();
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút từ chối
     * Nếu chuột trái được nhấn sẽ thực hiện từ chối nhân khẩu
     *
     * @param e Sự kiện chuột bắt được
     */
    public void onPressedButtonTuChoiNhanKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            tuChoiNhanKhauCanBo();
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút xóa
     * Nếu chuột trái được nhấn sẽ thực hiện xóa nhân khẩu
     *
     * @param e Sự kiện chuột bắt được
     */
    public void onPressedButtonXoaNhanKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaNhanKhauCanBo();
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút tìm kiếm
     * Nếu chuột trái được nhấn sẽ thực hiện lọc thông tin nhân khẩu
     *
     * @param e Sự kiện chuột bắt được
     */
    public void onPressedButtonLocThongTinNhanKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinNhanKhauCanBo();
        }
    }

    /**
     * Phương thức hiển thị tất cả nhân khẩu
     */
    private void displayAllNhanKhauCanBo() {
        ObservableList<NhanKhauModel> nhanKhauModelObservableList = nhanKhauService.getAllNhanKhau();
        tableViewNhanKhauCanBo.setItems(nhanKhauModelObservableList);
    }

    /**
     * Phương thức từ chối nhân khẩu được chọn trong bảng hiển thị
     * Nếu không có nhân khẩu nào được chọn sẽ thông báo cho người dùng
     */
    private void tuChoiNhanKhauCanBo() {
        NhanKhauModel nhanKhauModel = tableViewNhanKhauCanBo.getSelectionModel().getSelectedItem();
        if (nhanKhauModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn nhân khẩu muốn từ chối");
            alert.showAndWait();
        } else if (nhanKhauModel.getTinhTrang().equals("Đã từ chối")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Nhân khẩu đã bị từ chối");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn từ chối nhân khẩu này?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                nhanKhauModel.setTinhTrang("Đã từ chối");
                nhanKhauService.updateNhanKhau(nhanKhauModel);
                displayAllNhanKhauCanBo();
            }
        }
    }

    /**
     * Phương thức xác nhận nhân khẩu được chọn trong bảng hiển thị
     * Nếu không có nhân khẩu nào được chọn sẽ thông báo cho người dùng
     */
    private void xacNhanNhanKhauCanBo() {
        NhanKhauModel nhanKhauModel = tableViewNhanKhauCanBo.getSelectionModel().getSelectedItem();
        if (nhanKhauModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn nhân khẩu muốn xác nhận");
            alert.showAndWait();
        } else if (nhanKhauModel.getTinhTrang().equals("Đã xác nhận")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Nhân khẩu đã được xác nhận");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xác nhận người này?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                nhanKhauModel.setTinhTrang("Đã xác nhận");
                nhanKhauService.updateNhanKhau(nhanKhauModel);
                displayAllNhanKhauCanBo();
            }
        }
    }

    /**
     * Phương thức xóa hộ khẩu được chọn trong bảng hiển thị
     * Nếu không có hộ khẩu nào được chọn sẽ thông báo cho người dùng
     */
    private void xoaNhanKhauCanBo() {
        NhanKhauModel nhanKhauModel = tableViewNhanKhauCanBo.getSelectionModel().getSelectedItem();
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
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Thông báo");
                if (nhanKhauService.deleteNhanKhau(nhanKhauModel)) {
                    info.setHeaderText("Xóa thành công!");
                } else {
                    info.setHeaderText("Xóa không thành công!");
                }
                info.showAndWait();
                displayAllNhanKhauCanBo();
            }
        }
    }

    /**
     * Phương thức tìm kiếm nhân khẩu theo trường tiêu chí đã chọn
     * Các trường tìm kiếm hợp lệ là Mã nhân khẩu, Mã hộ khẩu, Họ tên, Biệt danh, Ngày sinh, Giới tính, Tôn giáo, Tình trạng
     */
    private void locThongTinNhanKhauCanBo() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemNhanKhauCanBo.getValue());
        String cauHoi = textFieldLocThongTinNhanKhauCanBo.getText();
        ObservableList<NhanKhauModel> nhanKhauModelObservableList = FXCollections.observableArrayList();
        switch (dieuKienKiemTra) {
            case "Mã nhân khẩu":
                Optional<NhanKhauModel> nhanKhauModel = nhanKhauService.getNhanKhauByMaNhanKhau(cauHoi);
                if (nhanKhauModel.isPresent()) {
                    nhanKhauModelObservableList.add(nhanKhauModel.get());
                }
                break;
            case "Mã hộ khẩu":
                nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauTrongHoKhau(cauHoi);
                break;
            case "Họ tên":
                nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByTen(cauHoi);
                break;
            case "Biệt danh":
                nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByBietDanh(cauHoi);
                break;
            case "Ngày sinh":
                if (datePickerTu.getValue() != null && datePickerDen.getValue() != null) {
                    nhanKhauModelObservableList = nhanKhauService.getNhanKhauByNgaySinhBetween(
                            Date.from(datePickerTu.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                            Date.from(datePickerDen.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
                    alert.showAndWait();
                    displayAllNhanKhauCanBo();
                    return;
                }
                break;
            case "Giới tính":
                nhanKhauModelObservableList = nhanKhauService.getNhanKhauByGioiTinh(
                        String.valueOf(comboBoxGioiTinhNhanKhauCanBo.getValue())
                );
                break;
            case "Tôn giáo":
                nhanKhauModelObservableList = nhanKhauService.getNhanKhauByTonGiao(cauHoi);
                break;
            case "Tình trạng":
                nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByTinhTrang(
                        String.valueOf(comboBoxTinhTrangNhanKhauCanBo.getValue()));
                break;
        }
        tableViewNhanKhauCanBo.setItems(nhanKhauModelObservableList);
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
            case 1:
                nhanKhauModel.setMaHoKhau((String) event.getNewValue());
                break;
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
                    displayAllNhanKhauCanBo();
                    return;
                }
                break;
            case 5:
                nhanKhauModel.setGioiTinh((String) event.getNewValue());
                break;
            case 6:
                nhanKhauModel.setTonGiao((String) event.getNewValue());
                break;
            case 7:
                nhanKhauModel.setTinhTrang((String) event.getNewValue());
                break;
        }
        updateNhanKhauCanBo(nhanKhauModel);
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
            case 1:
                nhanKhauModel.setMaHoKhau((String) event.getOldValue());
                break;
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
            case 7:
                nhanKhauModel.setTinhTrang((String) event.getOldValue());
                break;
        }
    }

    /**
     * Phương thức cập nhật thông tin nhân khẩu
     *
     * @param nhanKhauModel Lớp chứa thông tin của nhân khẩu muốn thay đổi
     */
    private void updateNhanKhauCanBo(NhanKhauModel nhanKhauModel) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        if (nhanKhauService.updateNhanKhau(nhanKhauModel)) {
            alert.setHeaderText("Sửa hộ khẩu thành công");
        } else {
            alert.setHeaderText("Sửa hộ khẩu không thành công");
        }
        if (alert.showAndWait().get() == ButtonType.OK) {
            displayAllNhanKhauCanBo();
        }
    }
}
