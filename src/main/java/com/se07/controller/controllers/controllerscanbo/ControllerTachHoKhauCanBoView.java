package com.se07.controller.controllers.controllerscanbo;

import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.NhanKhauService;
import com.se07.model.models.HoKhauModel;
import com.se07.model.models.NhanKhauModel;
import com.se07.util.SceneLoader;
import com.se07.util.UserInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerTachHoKhauCanBoView implements Initializable {
    @FXML
    TextField textFieldMaHoKhau, textFieldChuHo, textFieldDiaChi,
            textFieldMaHoKhauMoi, textFieldChuHoMoi, textFieldDiaChiMoi;
    @FXML
    TableView<NhanKhauModel> tableViewHoKhauCu, tableViewHoKhauMoi;
    @FXML
    TableColumn<NhanKhauModel, String> tableColumnMaNhanKhauCu, tableColumnHoTenCu,
            tableColumnMaNhanKhauMoi, tableColumnHoTenMoi;

    final int id = UserInfo.getUserId();
    private final HoKhauService hoKhauService = new HoKhauService();
    private final NhanKhauService nhanKhauService = new NhanKhauService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnMaNhanKhauCu.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        tableColumnMaNhanKhauMoi.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        tableColumnHoTenCu.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        tableColumnHoTenMoi.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
    }

    public void onEnterPressedTrongOTimKiemMaHoKhau(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            timKiemMaHoKhau();
        }
    }

    public void onPressedButtonOKMaHoKhau(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {
            timKiemMaHoKhau();
        }
    }

    private void timKiemMaHoKhau() {
        String maHoKhau = textFieldMaHoKhau.getText();
        Optional<HoKhauModel> hoKhauModel = hoKhauService.getHoKhauByMaHoKhau(maHoKhau);
        if (hoKhauModel.isPresent()) {
            textFieldChuHo.setText(hoKhauModel.get().getChuHo());
            textFieldDiaChi.setText(hoKhauModel.get().getDiaChi());
            ObservableList<NhanKhauModel> nhanKhauModels = nhanKhauService.getAllNhanKhauTrongHoKhau(maHoKhau);
            tableViewHoKhauCu.setItems(nhanKhauModels);
            tableViewHoKhauMoi.setItems(FXCollections.observableArrayList());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Mã hộ khẩu không tồn tại");
            alert.showAndWait();
        }
    }

    public void onPressedButtonChapNhanTachHoKhauCanBo(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {
            tachHoKhau();
        }
    }

    private void tachHoKhau() {
        if (textFieldMaHoKhauMoi.getText().isBlank() || textFieldChuHoMoi.getText().isBlank() ||
                textFieldDiaChiMoi.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đủ các trường thông tin cho hộ");
            alert.showAndWait();
        } else if (tableViewHoKhauMoi.getItems().size() == 0 || tableViewHoKhauCu.getItems().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Hộ khẩu không được để trống");
            alert.showAndWait();
        } else {
            String hoTen = textFieldChuHoMoi.getText();
            if (!tableViewHoKhauCu.getItems().contains(hoTen)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Chủ hộ không có trong danh sách hộ khẩu mới!");
                alert.showAndWait();
                return;
            }
            String maHoKhauMoi = textFieldMaHoKhauMoi.getText();
            if (hoKhauService.getHoKhauByMaHoKhau(maHoKhauMoi).isPresent()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Mã hộ khẩu đã tồn tại!");
                alert.showAndWait();
                return;
            }
            HoKhauModel hoKhauModel = new HoKhauModel(maHoKhauMoi, hoTen, textFieldDiaChiMoi.getText(),
                    Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()), id);
            boolean error = false;
            if (hoKhauService.addHoKhau(hoKhauModel)) {
                for (NhanKhauModel nhanKhauModel : tableViewHoKhauMoi.getItems()) {
                    nhanKhauModel.setMaHoKhau(maHoKhauMoi);
                    if (!nhanKhauService.updateNhanKhau(nhanKhauModel)) {
                        error = true;
                        break;
                    }
                }
            } else {
                error = true;
            }
            if (error) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Tách hộ khẩu không thành công");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Tách hộ khẩu thành công");
                alert.showAndWait();
            }
        }
    }

    public void onPressedButtonKhongChapNhanTachHoKhauCanBo(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn thoát");
            if (alert.showAndWait().get() == ButtonType.OK) {
                new SceneLoader().loadFxmlFileCanBo((Stage) ((Node) mouseEvent.getSource()).getScene().getWindow(),
                        "NhanKhauCanBoView.fxml");
            }
        }
    }

    public void onPressedButtonChuyenNhanKhau(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {
            chuyenNhanKhauCuSangMoi();
        }
    }

    private void chuyenNhanKhauCuSangMoi() {
        NhanKhauModel nhanKhauModel = tableViewHoKhauCu.getSelectionModel().getSelectedItem();
        if (nhanKhauModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn nhân khẩu muốn chuyển sang hộ khẩu mới");
            alert.showAndWait();
        } else if (nhanKhauModel.getHoTen() == textFieldChuHo.getText()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể chuyển chủ hộ sang hộ mới");
            alert.showAndWait();
        } else {
            tableViewHoKhauCu.getItems().remove(nhanKhauModel);
            tableViewHoKhauMoi.getItems().add(nhanKhauModel);
            tableViewHoKhauCu.refresh();
            tableViewHoKhauMoi.refresh();
        }
    }

    public void onPressedButtonHuyChuyenNhanKhau(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {
            chuyenNhanKhauMoiVeCu();
        }
    }

    private void chuyenNhanKhauMoiVeCu() {
        NhanKhauModel nhanKhauModel = tableViewHoKhauMoi.getSelectionModel().getSelectedItem();
        if (nhanKhauModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn nhân khẩu muốn chuyển về hộ khẩu cũ");
            alert.showAndWait();
        } else {
            tableViewHoKhauMoi.getItems().remove(nhanKhauModel);
            tableViewHoKhauCu.getItems().add(nhanKhauModel);
            tableViewHoKhauCu.refresh();
            tableViewHoKhauMoi.refresh();
        }
    }
}
