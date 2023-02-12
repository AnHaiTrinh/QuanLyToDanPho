package com.se07.controller.controllers.controllersketoan;

import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.NhanKhauService;
import com.se07.controller.services.TraoThuongService;
import com.se07.model.models.TraoThuongDisplayModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class ControllerThongKeThuQuyView extends ControllerThuQuyView {
    @FXML
    TableView<TraoThuongDisplayModel> tableViewThongKeThuQuy;
    @FXML
    TableColumn<TraoThuongDisplayModel, String> tableColumnMaNhanKhauThongKeThuQuy, tableColumnHoTenThongKeThuQuy,
            tableColumnDipThongKeThuQuy, tableColumnTenQuaThongKeThuQuy;
    @FXML
    TableColumn<TraoThuongDisplayModel, Integer> tableColumnNamThongKeThuQuy, tableColumnSoLuongThongKeThuQuy;
    @FXML
    TableColumn<TraoThuongDisplayModel, Double> tableColumnDonGiaThongKeThuQuy, tableColumnThanhTienThongKeThuQuy;
    @FXML
    Label labelTongGiaTriThongKeThuQuy;
    @FXML
    ComboBox comboBoxGiaTriThongKeThuQuy, comboBoxThongKeGiaiThuongThuQuy;

    private final ObservableList<String> listThongKe =
            FXCollections.observableArrayList("Tất cả", "Mã nhân khẩu", "Mã hộ khẩu", "Kiểu");
    private final TraoThuongService traoThuongService = new TraoThuongService();
    private final ObservableList<String> listMaHoKhau = new HoKhauService().getAllMaHoKhau();

    private final ObservableList<String> listMaNhanKhau = new NhanKhauService().getAllMaNhanKhau();
    private final ObservableList<String> listKieuDip = FXCollections.observableArrayList("Thành tích", "Dịp đặc biệt");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        tableColumnMaNhanKhauThongKeThuQuy.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        tableColumnHoTenThongKeThuQuy.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        tableColumnDipThongKeThuQuy.setCellValueFactory(new PropertyValueFactory<>("tenDip"));
        tableColumnNamThongKeThuQuy.setCellValueFactory(new PropertyValueFactory<>("nam"));
        tableColumnTenQuaThongKeThuQuy.setCellValueFactory(new PropertyValueFactory<>("tenPhanThuong"));
        tableColumnDonGiaThongKeThuQuy.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        tableColumnSoLuongThongKeThuQuy.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        tableColumnThanhTienThongKeThuQuy.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));

        comboBoxThongKeGiaiThuongThuQuy.getItems().addAll(listThongKe);
        comboBoxThongKeGiaiThuongThuQuy.getSelectionModel().selectFirst();

        tableViewThongKeThuQuy.setItems(traoThuongService.getAllTraoThuongDisplayModel());
        setLabelTongGiaTriThongKeThuQuy();
    }

    @Override
    public void onPressedButtonThongKeThuQuy(MouseEvent e) throws IOException {
    }

    public void onPressedButtonThongKeGiaiThuongThuQuy(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {
            thongKeGiaiThuongThuQuy();
        }
    }

    private void thongKeGiaiThuongThuQuy() {
        String kieuThongKe = String.valueOf(comboBoxThongKeGiaiThuongThuQuy.getValue());
        String cauHoi = String.valueOf(comboBoxGiaTriThongKeThuQuy.getValue());
        ObservableList<TraoThuongDisplayModel> traoThuongDisplayModelObservableList = FXCollections.observableArrayList();
        switch (kieuThongKe) {
            case "Tất cả":
                traoThuongDisplayModelObservableList = traoThuongService.getAllTraoThuongDisplayModel();
                break;
            case "Mã hộ khẩu":
                traoThuongDisplayModelObservableList = traoThuongService.getAllTraoThuongDisplayModelByMaHoKhau(cauHoi);
                break;
            case "Mã nhân khẩu":
                traoThuongDisplayModelObservableList = traoThuongService.getAllTraoThuongDisplayModelByMaNhanKhau(cauHoi);
                break;
            case "Kiểu":
                traoThuongDisplayModelObservableList = traoThuongService.getAllTraoThuongDisplayModelByKieu(cauHoi);
                break;
        }
        tableViewThongKeThuQuy.setItems(traoThuongDisplayModelObservableList);
        setLabelTongGiaTriThongKeThuQuy();
    }

    public void onSelectionComboBoxThongKeGiaiThuongThuQuy(ActionEvent e) {
        String kieuThongKe = String.valueOf(comboBoxThongKeGiaiThuongThuQuy.getValue());
        switch (kieuThongKe) {
            case "Tất cả":
                comboBoxGiaTriThongKeThuQuy.setItems(FXCollections.observableArrayList());
                break;
            case "Mã hộ khẩu":
                comboBoxGiaTriThongKeThuQuy.setItems(listMaHoKhau);
                comboBoxGiaTriThongKeThuQuy.getSelectionModel().selectFirst();
                break;
            case "Mã nhân khẩu":
                comboBoxGiaTriThongKeThuQuy.setItems(listMaNhanKhau);
                comboBoxGiaTriThongKeThuQuy.getSelectionModel().selectFirst();
                break;
            case "Kiểu":
                comboBoxGiaTriThongKeThuQuy.setItems(listKieuDip);
                comboBoxGiaTriThongKeThuQuy.getSelectionModel().selectFirst();
                break;
        }
    }

    public void onEnterPressedTrongOThongKeGiaiThuongThuQuy(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            thongKeGiaiThuongThuQuy();
        }
    }

    private void setLabelTongGiaTriThongKeThuQuy() {
        double tongGiaTriGiaiThuong = 0D;
        for (TraoThuongDisplayModel traoThuongDisplayModel : tableViewThongKeThuQuy.getItems())
            tongGiaTriGiaiThuong += traoThuongDisplayModel.getThanhTien();
        labelTongGiaTriThongKeThuQuy.setText("Tổng giá trị giải thưởng: " +
                NumberFormat.getInstance().format(tongGiaTriGiaiThuong));
    }
}
