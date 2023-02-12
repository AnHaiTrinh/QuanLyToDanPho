package com.se07.controller.controllers.controllersketoan;

import com.se07.controller.services.PhanThuongService;
import com.se07.view.TreasurerView;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

public class ControllerTraoThuongThuQuyView extends ControllerThuQuyView {

    public String tenDip;
    public int nam, idNhap;
    @FXML
    Label labelTenNamTraoThuongThuQuy, labelIDNhapTraoThuongThuQuy;
    @FXML
    ComboBox<String> comboBoxChonQua1, comboBoxChonQua2, comboBoxChonQua3, comboBoxChonQua4, comboBoxChonQua5;

    ComboBox<String>[] comboBoxChonQua;
    @FXML
    TextField textFieldChonSoLuong1, textFieldChonSoLuong2, textFieldChonSoLuong3, textFieldChonSoLuong4,
            textFieldChonSoLuong5;
    TextField[] textFieldChonSoLuong;

    private final ObservableList<String> listTenPhanThuong = new PhanThuongService().getAllTenPhanThuong();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        comboBoxChonQua = new ComboBox[]{comboBoxChonQua1, comboBoxChonQua2, comboBoxChonQua3, comboBoxChonQua4, comboBoxChonQua5};
        for (ComboBox comboBox : comboBoxChonQua) comboBox.setItems(listTenPhanThuong);
        textFieldChonSoLuong = new TextField[]{
                textFieldChonSoLuong1, textFieldChonSoLuong2, textFieldChonSoLuong3, textFieldChonSoLuong4, textFieldChonSoLuong5};
    }

    public void onPressedButtonXacNhanChonQuaThuQuy(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {
            xacNhanChonQuaThuQuy();
        }
    }

    private void xacNhanChonQuaThuQuy() {
    }

    private boolean kiemTraChonQuaThuQuy() {
        boolean kiemTra = false;
        HashSet<String> tenPhanThuong = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            String tenQua = comboBoxChonQua[i].getValue();
            String stringSoLuong = textFieldChonSoLuong[i].getText();
            int soLuong;
            if (tenQua == null) {
                if (!stringSoLuong.isBlank()) return false;
            } else {
                if (tenPhanThuong.contains(tenQua) || stringSoLuong.isBlank()) return false;
                else {
                    try {
                        soLuong = Integer.valueOf(stringSoLuong);
                    } catch (NumberFormatException numberFormatException) {
                        return false;
                    }
                    tenPhanThuong.add(tenQua);
                    kiemTra = true;
                }
            }
        }
        return kiemTra;
    }

    public void onPressedButtonThemLoaiPhanThuongThuQuy(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.isPrimaryButtonDown()) {
            Stage stage = new Stage();
            stage.initOwner(((Node) mouseEvent.getSource()).getScene().getWindow());
            stage.setTitle("Minh chứng");
            stage.setScene(new Scene(
                    new FXMLLoader(TreasurerView.class.getResource("ThemLoaiPhanThuongThuQuyView.fxml")).load()));
            stage.show();
        }
    }

    public void onPressedButtonHuyChonQuaThuQuy(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileThuQuy((Stage) ((Node) mouseEvent.getSource()).getScene().getWindow(),
                    "GiaiThuongThuQuyView.fxml");
        }
    }

    public void setLabel() {
        labelTenNamTraoThuongThuQuy.setText("Tên dịp - năm: " + tenDip + " - " + nam);
        labelIDNhapTraoThuongThuQuy.setText("ID Nhập: " + idNhap);
    }
}
