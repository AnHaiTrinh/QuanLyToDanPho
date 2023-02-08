package com.se07.controller.controllers.controllerscanbo;

import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.NhanKhauService;
import com.se07.model.models.NhanKhauModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Lớp controller điều khiển màn hình thêm mới nhân khẩu của tổ trưởng
 */
public class ControllerThemMoiNhanKhauCanBoView extends ControllerCanBoView {
    @FXML
    TextField textFieldMaNhanKhauThemMoiNhanKhauCanBo, textFieldHoTenThemMoiNhanKhauCanBo,
            textFieldTonGiaoThemMoiNhanKhauCanBo, textFieldBietDanhThemMoiNhanKhauCanBo;
    @FXML
    DatePicker datePickerNgaySinhThemMoiNhanKhauCanBo;
    @FXML
    ComboBox comboBoxGioiTinhThemMoiNhanKhauCanBo, comboBoxMaHoKhauThemMoiNhanKhauCanBo;
    final private ObservableList<String> listGioiTinh = FXCollections.observableArrayList("Nam", "Nữ","Khác");
    final HoKhauService hoKhauService = new HoKhauService();
    LocalDate today = LocalDate.now();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxGioiTinhThemMoiNhanKhauCanBo.getItems().addAll(listGioiTinh);
        comboBoxGioiTinhThemMoiNhanKhauCanBo.getSelectionModel().selectFirst();
        comboBoxMaHoKhauThemMoiNhanKhauCanBo.getItems().addAll(hoKhauService.getAllMaHoKhau());
        comboBoxMaHoKhauThemMoiNhanKhauCanBo.getSelectionModel().selectFirst();
        datePickerNgaySinhThemMoiNhanKhauCanBo.setValue(today);

        anchorPaneChinhCanBo.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                themMoiNhanKhauCanBo();
            } else if (keyEvent.getCode() == KeyCode.Q) {
                try {
                    huyThemMoiNhanKhauCanBo(keyEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Phương thức được gọi khi nhấn nút xác nhận
     * Nếu chuột trái được nhấn sẽ thực hiện thêm mới nhân khẩu
     *
     * @param e Sự kiện chuột bắt được
     */
    public void onPressedButtonThemMoiNhanKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            themMoiNhanKhauCanBo();
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút hủy
     * Nếu chuột trái được nhấn sẽ thực hiện hủy đăng ký nhân khẩu
     *
     * @param e Sự kiện chuột bắt được
     * @throws IOException
     */
    public void onPressedButtonHuyThemMoiNhanKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            huyThemMoiNhanKhauCanBo(e);
        }
    }

    /**
     * Phương thức lưu thông tin nhân khẩu người dùng đã nhập
     * Nếu các trường không được điền đầy đủ sẽ hiển thị thông báo
     * Nếu thành công sẽ lưu vào cơ sở dữ liệu và trở lại giao diện thêm mới nhân khẩu
     */
    private void themMoiNhanKhauCanBo() {
        NhanKhauService nhanKhauService = new NhanKhauService();
        if (textFieldHoTenThemMoiNhanKhauCanBo.getText().isBlank() || textFieldMaNhanKhauThemMoiNhanKhauCanBo.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        String maNhanKhau = textFieldMaNhanKhauThemMoiNhanKhauCanBo.getText();
        String gioiTinh = String.valueOf(comboBoxGioiTinhThemMoiNhanKhauCanBo.getValue());
        String maHoKhau = String.valueOf(comboBoxMaHoKhauThemMoiNhanKhauCanBo.getValue());
        String hoTen = textFieldHoTenThemMoiNhanKhauCanBo.getText();
        String tonGiao = textFieldTonGiaoThemMoiNhanKhauCanBo.getText();
        String bietDanh = textFieldBietDanhThemMoiNhanKhauCanBo.getText();
        Date ngaysinh = Date.from(datePickerNgaySinhThemMoiNhanKhauCanBo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        NhanKhauModel nhanKhauModel = new NhanKhauModel(maNhanKhau, maHoKhau, hoTen, bietDanh, ngaysinh, gioiTinh, tonGiao, tinhTrang, id);
        if (!nhanKhauService.getNhanKhauByMaNhanKhau(maNhanKhau).isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Mã nhân khẩu đã tồn tại");
            alert.showAndWait();
            textFieldMaNhanKhauThemMoiNhanKhauCanBo.requestFocus();
        } else {
            if (nhanKhauService.addNhanKhau(nhanKhauModel)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("");
                alert.setContentText("Bạn đã thêm thành công");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    textFieldMaNhanKhauThemMoiNhanKhauCanBo.setText("");
                    textFieldHoTenThemMoiNhanKhauCanBo.setText("");
                    textFieldBietDanhThemMoiNhanKhauCanBo.setText("");
                    textFieldTonGiaoThemMoiNhanKhauCanBo.setText("");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("");
                alert.setContentText("Thêm mới nhân khẩu thất bại!");
                alert.showAndWait();
            }
        }
    }

    /**
     * Phương thức hủy thêm mới nhân khẩu và trở về giao diện quản lý nhân khẩu
     *
     * @param e Sự kiện kích hoạt
     * @throws IOException
     */
    private void huyThemMoiNhanKhauCanBo(Event e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "NhanKhauCanBoView.fxml");
        }
    }
}
