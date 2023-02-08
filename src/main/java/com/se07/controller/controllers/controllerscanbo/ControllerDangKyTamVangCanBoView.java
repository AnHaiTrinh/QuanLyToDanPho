package com.se07.controller.controllers.controllerscanbo;

import com.se07.controller.services.NhanKhauService;
import com.se07.controller.services.TamVangService;
import com.se07.model.models.NhanKhauModel;
import com.se07.model.models.TamVangModel;
import com.se07.view.TrangChuCanBoView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerDangKyTamVangCanBoView extends ControllerCanBoView {
    @FXML
    GridPane gridPaneDangKyTamVangCanBo;
    LocalDate today = LocalDate.now();
    @FXML
    DatePicker datePickerTuNgayTamVangCanBo, datePickerDenNgayTamVangCanBo;
    @FXML
    TextField textFieldHoTenTamVangCanBo, textFieldLyDoTamVangCanBo, textFieldNoiTamVangCanBo;
    @FXML
    ComboBox comBoBoxMaNhanKhauTamVangCanBo;

    final private NhanKhauService nhanKhauService = new NhanKhauService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        datePickerTuNgayTamVangCanBo.setValue(today);
        datePickerDenNgayTamVangCanBo.setValue(today.plusDays(7));
        comBoBoxMaNhanKhauTamVangCanBo.getItems().addAll(new NhanKhauService().getAllMaNhanKhau());
        textFieldHoTenTamVangCanBo.setEditable(false);
        gridPaneDangKyTamVangCanBo.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                xacNhanDangKyTamVangCanBo();
            } else if (keyEvent.getCode() == KeyCode.Q) {
                try {
                    huyXacNhanDangKyTamVangCanBo(keyEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void onPressedButtonXacNhanDangKyTamVangCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xacNhanDangKyTamVangCanBo();
        }
    }

    public void onPressedButtonHuyXacNhanDangKyTamVangCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            huyXacNhanDangKyTamVangCanBo(e);
        }
    }

    public void onSelectionComBoBoxMaNhanKhauTamVangCanBo(ActionEvent e) {
        String maNhanKhau = String.valueOf(comBoBoxMaNhanKhauTamVangCanBo.getValue());
        Optional<NhanKhauModel> hoKhauModel = nhanKhauService.getNhanKhauByMaNhanKhau(maNhanKhau);
        textFieldHoTenTamVangCanBo.setText(hoKhauModel.get().getHoTen());
    }

    private void xacNhanDangKyTamVangCanBo() {
        TamVangService tamVangService = new TamVangService();
        if (textFieldLyDoTamVangCanBo.getText().isBlank() || textFieldNoiTamVangCanBo.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        String maNhanKhau = String.valueOf(comBoBoxMaNhanKhauTamVangCanBo.getValue());
        String noiTamVang = textFieldNoiTamVangCanBo.getText();
        Date tuNgay = Date.from(datePickerTuNgayTamVangCanBo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date denNgay = Date.from(datePickerDenNgayTamVangCanBo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String lyDo = textFieldLyDoTamVangCanBo.getText();
        TamVangModel tamVangModel = new TamVangModel(maNhanKhau, noiTamVang, tuNgay, denNgay, lyDo, tinhTrang, id);
        if (tamVangService.addTamVang(tamVangModel)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("");
            alert.setContentText("Bạn đã đăng ký thành công");
            if (alert.showAndWait().get() == ButtonType.OK) {
                textFieldNoiTamVangCanBo.setText("");
                textFieldLyDoTamVangCanBo.setText("");
            }
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("");
        alert.setContentText("Đăng ký tạm vắng thất bại!");
        alert.showAndWait();
    }

    private void huyXacNhanDangKyTamVangCanBo(Event e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("TamVangCanBo.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        }
    }
}
