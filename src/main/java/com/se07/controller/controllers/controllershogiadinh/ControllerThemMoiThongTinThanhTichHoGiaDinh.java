package com.se07.controller.controllers.controllershogiadinh;
import com.se07.controller.services.DipTraoThuongService;
import com.se07.controller.services.NhanKhauService;
import com.se07.controller.services.ThongTinThanhTichService;
import com.se07.model.models.NhanKhauModel;
import com.se07.model.models.ThongTinThanhTichModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerThemMoiThongTinThanhTichHoGiaDinh extends ControllerHoGiaDinhView {
    @FXML
    ComboBox comboBoxTenDipThanhTichHoGiaDinh, comboBoxNamThanhTichHoGiaDinh, comboBoxMaNhanKhauThanhTichHoGiaDinh,
            comboBoxCapThanhTichHoGiaDinh, comboBoxKieuThanhTichHoGiaDinh;
    @FXML
    TextField textFieldHoTenThanhTichHoGiaDinh, textFieldTruongThanhTichHoGiaDinh, textFieldLopThanhTichHoGiaDinh;
    @FXML
    Button buttonThemMinhChungThanhTichHoGiaDinh;

    private final NhanKhauService nhanKhauService = new NhanKhauService();

    private final DipTraoThuongService dipTraoThuongService = new DipTraoThuongService();

    private final ThongTinThanhTichService thongTinThanhTichService = new ThongTinThanhTichService();
    final ObservableList<String> listCapThanhTich = FXCollections.observableArrayList(
            "Trường", "Quận/Huyện", "Tỉnh/Thành phố", "Quốc gia", "Quốc tế");

    final ObservableList<String> listKieuThanhTich = FXCollections.observableArrayList(
            "Học sinh khá", "Học sinh giỏi", "Học sinh xuất sắc",
            "Giải nhất", "Giải nhì", "Giải ba", "Giải khuyến khích",
            "Huy chương Vàng", "Huy chương Bạc", "Huy chương Đồng");

    private File minhChung;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        anchorPaneChinhHoGiaDinh.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                themMoiThongTinThanhTichHoGiaDinh();
            } else if (keyEvent.getCode() == KeyCode.Q) {
                try {
                    huyThemMoiThongTinThanhTichHoGiaDinh(keyEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        comboBoxMaNhanKhauThanhTichHoGiaDinh.getItems().addAll(nhanKhauService.getAllMaNhanKhauTrongHoKhau(maHoKhauDangNhap));
        comboBoxTenDipThanhTichHoGiaDinh.getItems().addAll(dipTraoThuongService.getAllTenTraoThuongThanhTich());
        comboBoxNamThanhTichHoGiaDinh.getItems().addAll(dipTraoThuongService.getAllNamTraoThuongThanhTich());
        comboBoxCapThanhTichHoGiaDinh.getItems().addAll(listCapThanhTich);
        comboBoxKieuThanhTichHoGiaDinh.getItems().addAll(listKieuThanhTich);
    }

    public void onPressedButtonThemMinhChungThanhTichHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            themMinhChungThanhTichHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow());
        }
    }

    public void onSelectionComboBoxTenDipThanhTichHoGiaDinh(ActionEvent e) {
        String tenDip = String.valueOf(comboBoxTenDipThanhTichHoGiaDinh.getValue());
        ObservableList<Integer> listNam = dipTraoThuongService.getNamByTenDipThanhTich(tenDip);
        comboBoxNamThanhTichHoGiaDinh.getItems().clear();
        comboBoxNamThanhTichHoGiaDinh.getItems().addAll(listNam);
        comboBoxNamThanhTichHoGiaDinh.getSelectionModel().selectFirst();
    }

    public void onSelectionComboBoxMaNhanKhauThanhTichHoGiaDinh(ActionEvent e) {
        String maNhanKhau = String.valueOf(comboBoxMaNhanKhauThanhTichHoGiaDinh.getValue());
        NhanKhauModel nhanKhauModel = nhanKhauService.getNhanKhauByMaNhanKhau(maNhanKhau).get();
        textFieldHoTenThanhTichHoGiaDinh.setText(nhanKhauModel.getHoTen());
    }

    public void onPressedButtonHoanThanhThongTinThanhTichHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            themMoiThongTinThanhTichHoGiaDinh();
        }
    }

    public void onPressedButtonHuyThongTinThanhTichHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            huyThemMoiThongTinThanhTichHoGiaDinh(e);
        }
    }

    private void themMinhChungThanhTichHoGiaDinh(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File ảnh", "*.jpg", "*.png"));
        minhChung = fileChooser.showOpenDialog(stage);
        if (minhChung != null) {
            buttonThemMinhChungThanhTichHoGiaDinh.setText(minhChung.getName());
        }
    }

    private void themMoiThongTinThanhTichHoGiaDinh() {
        if (comboBoxTenDipThanhTichHoGiaDinh.getValue() == null || comboBoxNamThanhTichHoGiaDinh.getValue() == null ||
                comboBoxMaNhanKhauThanhTichHoGiaDinh.getValue() == null || textFieldLopThanhTichHoGiaDinh.getText().isBlank() ||
                textFieldTruongThanhTichHoGiaDinh.getText().isBlank() || minhChung == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        String tenDip = String.valueOf(comboBoxTenDipThanhTichHoGiaDinh.getValue());
        int nam = Integer.parseInt(String.valueOf(comboBoxNamThanhTichHoGiaDinh.getValue()));
        String maNhanKhau = String.valueOf(comboBoxMaNhanKhauThanhTichHoGiaDinh.getValue());
        int idDip = dipTraoThuongService.getDipTraoThuongByTenAndNam(tenDip, nam).get().getId();
        int lop = Integer.parseInt(textFieldLopThanhTichHoGiaDinh.getText());
        String truong = textFieldTruongThanhTichHoGiaDinh.getText();
        String kieuThanhTich = String.valueOf(comboBoxKieuThanhTichHoGiaDinh.getValue());
        String capThanhTich = String.valueOf(comboBoxCapThanhTichHoGiaDinh.getValue());
        ThongTinThanhTichModel thongTinThanhTichModel = new ThongTinThanhTichModel(idDip, maNhanKhau, lop, truong,
                capThanhTich, kieuThanhTich, minhChung, tinhTrang, id);
        if (thongTinThanhTichService.addThongTinThanhTich(thongTinThanhTichModel)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("");
            alert.setContentText("Bạn đã thêm thông tin thành công");
            if (alert.showAndWait().get() == ButtonType.OK) {
                buttonThemMinhChungThanhTichHoGiaDinh.setText("Thêm tệp");
                minhChung = null;
            }
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("");
        alert.setContentText("Thêm thông tin thất bại!");
        alert.showAndWait();
    }

    private void huyThemMoiThongTinThanhTichHoGiaDinh(Event e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "GiaiThuongThanhTichHoGiaDinhView.fxml");
        }
    }
}
