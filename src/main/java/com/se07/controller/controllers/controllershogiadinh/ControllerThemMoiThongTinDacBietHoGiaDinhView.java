package com.se07.controller.controllers.controllershogiadinh;

import com.se07.controller.services.DipTraoThuongService;
import com.se07.controller.services.NhanKhauService;
import com.se07.controller.services.ThongTinDipDacBietService;
import com.se07.model.models.NhanKhauModel;
import com.se07.model.models.ThongTinDipDacBietModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerThemMoiThongTinDacBietHoGiaDinhView extends ControllerHoGiaDinhView{

    @FXML
    ComboBox comboBoxTenDipDacBietHoGiaDinh, comboBoxNamDipDacBietHoGiaDinh, comboBoxMaNhanKhauDipDacBietHoGiaDinh;
    @FXML
    TextField textFieldHoTenDipDacBietHoGiaDinh;

    final private NhanKhauService nhanKhauService = new NhanKhauService();
    final private DipTraoThuongService dipTraoThuongService = new DipTraoThuongService();

    final private ThongTinDipDacBietService thongTinDipDacBietService = new ThongTinDipDacBietService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        comboBoxMaNhanKhauDipDacBietHoGiaDinh.getItems().addAll(nhanKhauService.getAllMaNhanKhauTrongHoKhau(maHoKhauDangNhap));
        comboBoxTenDipDacBietHoGiaDinh.getItems().addAll(dipTraoThuongService.getAllTenTraoThuongDipDacBiet());
        comboBoxNamDipDacBietHoGiaDinh.getItems().addAll(dipTraoThuongService.getAllNamTraoThuongDipDacBiet());
        comboBoxTenDipDacBietHoGiaDinh.getSelectionModel().selectFirst();
        comboBoxNamDipDacBietHoGiaDinh.getSelectionModel().selectFirst();
    }

    public void onSelectionComboBoxTenDipDacBietHoGiaDinh(ActionEvent e) {
        String tenDip = String.valueOf(comboBoxTenDipDacBietHoGiaDinh.getValue());
        ObservableList<Integer> listNam = dipTraoThuongService.getNamByTenDipDacBiet(tenDip);
        comboBoxNamDipDacBietHoGiaDinh.getItems().clear();
        comboBoxNamDipDacBietHoGiaDinh.getItems().addAll(listNam);
        comboBoxNamDipDacBietHoGiaDinh.getSelectionModel().selectFirst();
    }

    public void onSelectionComboBoxMaNhanKhauDipDacBietHoGiaDinh(ActionEvent e) {
        String maNhanKhau = String.valueOf(comboBoxMaNhanKhauDipDacBietHoGiaDinh.getValue());
        NhanKhauModel nhanKhauModel = nhanKhauService.getNhanKhauByMaNhanKhau(maNhanKhau).get();
        textFieldHoTenDipDacBietHoGiaDinh.setText(nhanKhauModel.getHoTen());
    }


    public void onPressedButtonHoanThanhThongTinDipDacBietHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            themMoiThongTinDipDacBietHoGiaDinh();
        }
    }

    public void onPressedButtonHuyThongTinDipDacBietHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            huyThemMoiThongTinDipDacBietHoGiaDinh(e);
        }
    }

    private void themMoiThongTinDipDacBietHoGiaDinh() {
        if (comboBoxTenDipDacBietHoGiaDinh.getValue() == null || comboBoxNamDipDacBietHoGiaDinh.getValue() == null ||
                comboBoxMaNhanKhauDipDacBietHoGiaDinh.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        String tenDip = String.valueOf(comboBoxTenDipDacBietHoGiaDinh.getValue());
        int nam = Integer.parseInt(String.valueOf(comboBoxNamDipDacBietHoGiaDinh.getValue()));
        String maNhanKhau = String.valueOf(comboBoxMaNhanKhauDipDacBietHoGiaDinh.getValue());
        int idDip = dipTraoThuongService.getDipTraoThuongByTenAndNam(tenDip, nam).get().getId();
        ThongTinDipDacBietModel thongTinDipDacBietModel = new ThongTinDipDacBietModel(idDip, maNhanKhau, tinhTrang, id);
        if (thongTinDipDacBietService.addThongTinDipDacBiet(thongTinDipDacBietModel)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("");
            alert.setContentText("Bạn đã thêm thông tin thành công");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("");
        alert.setContentText("Thêm thông tin thất bại!");
        alert.showAndWait();
    }

    private void huyThemMoiThongTinDipDacBietHoGiaDinh(Event e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "GiaiThuongDipDacBietHoGiaDinhView.fxml");
        }
    }
    
}
