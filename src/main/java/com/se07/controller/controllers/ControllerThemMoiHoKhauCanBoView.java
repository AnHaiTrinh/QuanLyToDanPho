package com.se07.controller.controllers;

import com.se07.controller.services.HoKhauService;
import com.se07.model.models.HoKhauModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Lớp controller điều khiển màn hình thêm mới hộ khẩu của tổ trưởng
 */
public class ControllerThemMoiHoKhauCanBoView extends ControllerCanBoView {
    LocalDate today = LocalDate.now();
    @FXML
    TextField textFieldMaChuHoThemMoiHoKhauCanBo, textFieldMaHoKhauThemMoiHoKhauCanBo, textFieldDiaChiThemMoiHoKhauCanBo;
    @FXML
    DatePicker datePickerNgayThanhLapThemMoiHoKhauCanBo;
    @FXML
    GridPane gridPaneThemMoiHoKhauCanBo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        gridPaneThemMoiHoKhauCanBo.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                dangKyHoKhauCanBo();
            } else if (keyEvent.getCode() == KeyCode.Q) {
                try {
                    huyDangKyHoKhauCanBo(keyEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        datePickerNgayThanhLapThemMoiHoKhauCanBo.setValue(today);
    }

    /**
     * Phương thức được gọi khi nhấn nút hủy
     * Nếu chuột trái được nhấn sẽ thực hiện hủy đăng ký hộ khẩu
     *
     * @param e Sự kiện chuột bắt được
     * @throws IOException
     */
    public void onPressedButtonHuyDangKyHoKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            huyDangKyHoKhauCanBo(e);
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút đăng ký
     * Nếu chuột trái được nhấn sẽ thực hiện đăng ký hộ khẩu
     *
     * @param e Sự kiện chuột bắt được
     */
    public void onPressedButtonDangKyHoKhauCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            dangKyHoKhauCanBo();
        }
    }

    /**
     * Phương thức lưu thông tin hộ khẩu người dùng đã nhập
     * Nếu các trường không được điền đầy đủ sẽ hiển thị thông báo
     * Nếu thành công sẽ lưu vào cơ sở dữ liệu và trở lại giao diện thêm mới hộ khẩu
     */
    private void dangKyHoKhauCanBo() {
        HoKhauService hoKhauService = new HoKhauService();
        if (textFieldMaHoKhauThemMoiHoKhauCanBo.getText().isBlank() ||
                textFieldDiaChiThemMoiHoKhauCanBo.getText().isBlank() ||
                datePickerNgayThanhLapThemMoiHoKhauCanBo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        String maHoKhau = textFieldMaHoKhauThemMoiHoKhauCanBo.getText();
        String maChuHo = textFieldMaChuHoThemMoiHoKhauCanBo.getText();
        Date ngayLap = Date.from(datePickerNgayThanhLapThemMoiHoKhauCanBo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String diaChi = textFieldDiaChiThemMoiHoKhauCanBo.getText();
        HoKhauModel hoKhauModel = new HoKhauModel(maHoKhau, maChuHo, diaChi, ngayLap, id);

        if (hoKhauService.getHoKhauByMaHoKhau(maHoKhau).isPresent()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Mã hộ khẩu đã tồn tại");
            alert.showAndWait();
        } else {
            if (hoKhauService.addHoKhau(hoKhauModel)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("");
                alert.setContentText("Bạn đã thêm thành công");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    textFieldMaHoKhauThemMoiHoKhauCanBo.setText("");
                    textFieldDiaChiThemMoiHoKhauCanBo.setText("");
                    textFieldMaChuHoThemMoiHoKhauCanBo.setText("");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Thông báo");
                alert.setHeaderText("");
                alert.setContentText("Thêm thất bại");
                alert.showAndWait();
            }
        }
    }

    /**
     * Phương thức hủy đăng ký hộ khẩu mới và trở về giao diện quản lý hộ khẩu
     *
     * @param e Sự kiện kích hoạt
     * @throws IOException
     */
    private void huyDangKyHoKhauCanBo(Event e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "HoKhauCanBoView.fxml");
        }
    }
}