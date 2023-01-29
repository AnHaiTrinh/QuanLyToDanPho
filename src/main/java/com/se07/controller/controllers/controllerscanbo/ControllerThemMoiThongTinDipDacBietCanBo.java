package com.se07.controller.controllers.controllerscanbo;

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

public class ControllerThemMoiThongTinDipDacBietCanBo extends ControllerCanBoView {
    @FXML
    ComboBox comboBoxTenDipDacBietCanBo, comboBoxNamDipDacBietCanBo, comboBoxMaNhanKhauDipDacBietCanBo;
    @FXML
    TextField textFieldHoTenDipDacBietCanBo;

    final private NhanKhauService nhanKhauService = new NhanKhauService();
    final private DipTraoThuongService dipTraoThuongService = new DipTraoThuongService();

    final private ThongTinDipDacBietService thongTinDipDacBietService = new ThongTinDipDacBietService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        anchorPaneChinhCanBo.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                themMoiThongTinDipDacBietCanBo();
            } else if (keyEvent.getCode() == KeyCode.Q) {
                try {
                    huyThemMoiThongTinDipDacBietCanBo(keyEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        comboBoxMaNhanKhauDipDacBietCanBo.getItems().addAll(nhanKhauService.getAllMaNhanKhau());
        comboBoxTenDipDacBietCanBo.getItems().addAll(dipTraoThuongService.getAllTenTraoThuongDipDacBiet());
        comboBoxNamDipDacBietCanBo.getItems().addAll(dipTraoThuongService.getAllNamTraoThuongDipDacBiet());
    }

    public void onSelectionComboBoxTenDipDacBietCanBo(ActionEvent e) {
        String tenDip = String.valueOf(comboBoxTenDipDacBietCanBo.getValue());
        ObservableList<Integer> listNam = dipTraoThuongService.getNamByTenDipDacBiet(tenDip);
        comboBoxNamDipDacBietCanBo.getItems().clear();
        comboBoxNamDipDacBietCanBo.getItems().addAll(listNam);
        comboBoxNamDipDacBietCanBo.getSelectionModel().selectFirst();
    }

    public void onSelectionComboBoxMaNhanKhauDipDacBietCanBo(ActionEvent e) {
        String maNhanKhau = String.valueOf(comboBoxMaNhanKhauDipDacBietCanBo.getValue());
        NhanKhauModel nhanKhauModel = nhanKhauService.getNhanKhauByMaNhanKhau(maNhanKhau).get();
        textFieldHoTenDipDacBietCanBo.setText(nhanKhauModel.getHoTen());
    }


    public void onPressedButtonHoanThanhThongTinDipDacBietCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            themMoiThongTinDipDacBietCanBo();
        }
    }

    public void onPressedButtonHuyThongTinDipDacBietCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            huyThemMoiThongTinDipDacBietCanBo(e);
        }
    }

    private void themMoiThongTinDipDacBietCanBo() {
        if (comboBoxTenDipDacBietCanBo.getValue() == null || comboBoxNamDipDacBietCanBo.getValue() == null ||
                comboBoxMaNhanKhauDipDacBietCanBo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        String tenDip = String.valueOf(comboBoxTenDipDacBietCanBo.getValue());
        int nam = Integer.parseInt(String.valueOf(comboBoxNamDipDacBietCanBo.getValue()));
        String maNhanKhau = String.valueOf(comboBoxMaNhanKhauDipDacBietCanBo.getValue());
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

    private void huyThemMoiThongTinDipDacBietCanBo(Event e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn chắc chắn muốn thoát?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "GiaiThuongDipDacBietCanBoView.fxml");
        }
    }
}
