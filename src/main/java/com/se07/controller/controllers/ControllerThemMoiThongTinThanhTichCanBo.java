package com.se07.controller.controllers;

import com.se07.controller.services.DipTraoThuongService;
import com.se07.controller.services.NhanKhauService;
import com.se07.controller.services.ThongTinThanhTichService;
import com.se07.model.models.NhanKhauModel;
import com.se07.model.models.ThongTinThanhTichModel;
import com.se07.util.MyIntegerStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerThemMoiThongTinThanhTichCanBo extends ControllerCanBoView {
    @FXML
    GridPane gridPaneThemMoiThongTinThanhTichCanBo;
    @FXML
    ComboBox comboBoxTenDipThanhTichCanBo, comboBoxNamThanhTichCanBo, comboBoxMaNhanKhauThanhTichCanBo,
            comboBoxCapThanhTichCanBo, comboBoxKieuThanhTichCanBo;
    @FXML
    TextField textFieldHoTenThanhTichCanBo, textFieldTruongThanhTichCanBo, textFieldLopThanhTichCanBo;
    @FXML
    Button buttonThemMinhChungThanhTichCanBo;

    private final NhanKhauService nhanKhauService = new NhanKhauService();

    private final DipTraoThuongService dipTraoThuongService = new DipTraoThuongService();

    private final ThongTinThanhTichService thongTinThanhTichService = new ThongTinThanhTichService();

    private final MyIntegerStringConverter integerStringConverter = new MyIntegerStringConverter();
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

        gridPaneThemMoiThongTinThanhTichCanBo.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                themMoiThongTinThanhTichCanBo();
            } else if (keyEvent.getCode() == KeyCode.Q) {
                try {
                    huyThemMoiThongTinThanhTichCanBo(keyEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        comboBoxMaNhanKhauThanhTichCanBo.getItems().addAll(nhanKhauService.getAllMaNhanKhau());
        comboBoxTenDipThanhTichCanBo.getItems().addAll(dipTraoThuongService.getAllTenTraoThuongThanhTich());
        comboBoxNamThanhTichCanBo.getItems().addAll(dipTraoThuongService.getAllNamTraoThuongThanhTich());
        comboBoxCapThanhTichCanBo.getItems().addAll(listCapThanhTich);
        comboBoxKieuThanhTichCanBo.getItems().addAll(listKieuThanhTich);
    }

    public void onPressedButtonThemMinhChungThanhTichCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            themMinhChungThanhTichCanBo((Stage) ((Node) e.getSource()).getScene().getWindow());
        }
    }

    public void onSelectionComboBoxTenDipThanhTichCanBo(ActionEvent e) {
        String tenDip = String.valueOf(comboBoxTenDipThanhTichCanBo.getValue());
        ObservableList<Integer> listNam = dipTraoThuongService.getNamByTenDipThanhTich(tenDip);
        comboBoxNamThanhTichCanBo.getItems().clear();
        comboBoxNamThanhTichCanBo.getItems().addAll(listNam);
        comboBoxNamThanhTichCanBo.getSelectionModel().selectFirst();
    }

    public void onSelectionComboBoxMaNhanKhauThanhTichCanBo(ActionEvent e) {
        String maNhanKhau = String.valueOf(comboBoxMaNhanKhauThanhTichCanBo.getValue());
        NhanKhauModel nhanKhauModel = nhanKhauService.getNhanKhauByMaNhanKhau(maNhanKhau).get();
        textFieldHoTenThanhTichCanBo.setText(nhanKhauModel.getHoTen());
    }

    public void onPressedButtonHoanThanhThongTinThanhTichCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            themMoiThongTinThanhTichCanBo();
        }
    }

    public void onPressedButtonHuyThongTinThanhTichCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            huyThemMoiThongTinThanhTichCanBo(e);
        }
    }

    private void themMinhChungThanhTichCanBo(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File ảnh", "*.jpg", "*.png"));
        minhChung = fileChooser.showOpenDialog(stage);
        if (minhChung != null) {
            buttonThemMinhChungThanhTichCanBo.setText(minhChung.getName());
        }
    }

    private void themMoiThongTinThanhTichCanBo() {
        if (comboBoxTenDipThanhTichCanBo.getValue() == null || comboBoxNamThanhTichCanBo.getValue() == null ||
                comboBoxMaNhanKhauThanhTichCanBo.getValue() == null || textFieldLopThanhTichCanBo.getText().isBlank() ||
                textFieldTruongThanhTichCanBo.getText().isBlank() || minhChung == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        int lop = integerStringConverter.fromString(textFieldLopThanhTichCanBo.getText());
        if (lop <= 0 || lop > 12) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập lớp hợp lệ (từ 1 - 12)");
            alert.showAndWait();
            textFieldLopThanhTichCanBo.requestFocus();
            return;
        }
        String tenDip = String.valueOf(comboBoxTenDipThanhTichCanBo.getValue());
        int nam = Integer.parseInt(String.valueOf(comboBoxNamThanhTichCanBo.getValue()));
        String maNhanKhau = String.valueOf(comboBoxMaNhanKhauThanhTichCanBo.getValue());
        int idDip = dipTraoThuongService.getDipTraoThuongByTenAndNam(tenDip, nam).get().getId();
        String truong = textFieldTruongThanhTichCanBo.getText();
        String kieuThanhTich = String.valueOf(comboBoxKieuThanhTichCanBo.getValue());
        String capThanhTich = String.valueOf(comboBoxCapThanhTichCanBo.getValue());
        ThongTinThanhTichModel thongTinThanhTichModel = new ThongTinThanhTichModel(idDip, maNhanKhau, lop, truong,
                capThanhTich, kieuThanhTich, minhChung, tinhTrang, id);
        if (thongTinThanhTichService.addThongTinThanhTich(thongTinThanhTichModel)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("");
            alert.setContentText("Bạn đã thêm thông tin thành công");
            if (alert.showAndWait().get() == ButtonType.OK) {
                buttonThemMinhChungThanhTichCanBo.setText("Thêm tệp");
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

    private void huyThemMoiThongTinThanhTichCanBo(Event e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "GiaiThuongThanhTichCanBoView.fxml");
        }
    }
}