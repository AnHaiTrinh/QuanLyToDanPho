package com.se07.controller.controllers.controllershogiadinh;

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

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class ControllerThongKeHoGiaDinhView extends ControllerHoGiaDinhView {
    @FXML
    TableView<TraoThuongDisplayModel> tableViewThongKeHoGiaDinh;
    @FXML
    TableColumn<TraoThuongDisplayModel, String> tableColumnMaNhanKhauThongKeHoGiaDinh, tableColumnHoTenThongKeHoGiaDinh,
            tableColumnDipThongKeHoGiaDinh, tableColumnTenQuaThongKeHoGiaDinh;
    @FXML
    TableColumn<TraoThuongDisplayModel, Integer> tableColumnNamThongKeHoGiaDinh, tableColumnSoLuongThongKeHoGiaDinh, tableColumnDonGiaThongKeHoGiaDinh, tableColumnThanhTienThongKeHoGiaDinh;
    @FXML
    Label labelTongGiaTriThongKeHoGiaDinh;
    @FXML
    ComboBox comboBoxGiaTriThongKeHoGiaDinh, comboBoxThongKeGiaiThuongHoGiaDinh;

    private final ObservableList<String> listThongKe =
            FXCollections.observableArrayList("Tất cả", "Mã nhân khẩu", "Kiểu");
    private final TraoThuongService traoThuongService = new TraoThuongService();

    private final ObservableList<String> listMaNhanKhau = new NhanKhauService().getAllMaNhanKhauTrongHoKhau(maHoKhauDangNhap);
    private final ObservableList<String> listKieuDip = FXCollections.observableArrayList("Thành tích", "Dịp đặc biệt");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        tableColumnMaNhanKhauThongKeHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        tableColumnHoTenThongKeHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        tableColumnDipThongKeHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("tenDip"));
        tableColumnNamThongKeHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("nam"));
        tableColumnTenQuaThongKeHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("tenPhanThuong"));
        tableColumnDonGiaThongKeHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        tableColumnSoLuongThongKeHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        tableColumnThanhTienThongKeHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));

        comboBoxThongKeGiaiThuongHoGiaDinh.getItems().addAll(listThongKe);
        comboBoxThongKeGiaiThuongHoGiaDinh.getSelectionModel().selectFirst();

        tableViewThongKeHoGiaDinh.setItems(traoThuongService.getAllTraoThuongDisplayModelByMaHoKhau(maHoKhauDangNhap));
        setLabelTongGiaTriThongKeHoGiaDinh();
    }

    public void onPressedButtonThongKeGiaiThuongHoGiaDinh(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {
            thongKeGiaiThuongHoGiaDinh();
        }
    }

    private void thongKeGiaiThuongHoGiaDinh() {
        String kieuThongKe = String.valueOf(comboBoxThongKeGiaiThuongHoGiaDinh.getValue());
        String cauHoi = String.valueOf(comboBoxGiaTriThongKeHoGiaDinh.getValue());
        ObservableList<TraoThuongDisplayModel> traoThuongDisplayModelObservableList = FXCollections.observableArrayList();
        switch (kieuThongKe) {
            case "Tất cả":
                traoThuongDisplayModelObservableList = traoThuongService.getAllTraoThuongDisplayModelByMaHoKhau(maHoKhauDangNhap);
                break;
            case "Mã nhân khẩu":
                traoThuongDisplayModelObservableList = traoThuongService.getAllTraoThuongDisplayModelByMaNhanKhau(cauHoi);
                break;
            case "Kiểu":
                traoThuongDisplayModelObservableList = traoThuongService.getAllTraoThuongDisplayModelByKieu(cauHoi);
                break;
        }
        tableViewThongKeHoGiaDinh.setItems(traoThuongDisplayModelObservableList);
        setLabelTongGiaTriThongKeHoGiaDinh();
    }

    public void onSelectionComboBoxThongKeGiaiThuongHoGiaDinh(ActionEvent e) {
        String kieuThongKe = String.valueOf(comboBoxThongKeGiaiThuongHoGiaDinh.getValue());
        switch (kieuThongKe) {
            case "Tất cả":
                comboBoxGiaTriThongKeHoGiaDinh.setItems(FXCollections.observableArrayList());
                break;
            case "Mã nhân khẩu":
                comboBoxGiaTriThongKeHoGiaDinh.setItems(listMaNhanKhau);
                comboBoxGiaTriThongKeHoGiaDinh.getSelectionModel().selectFirst();
                break;
            case "Kiểu":
                comboBoxGiaTriThongKeHoGiaDinh.setItems(listKieuDip);
                comboBoxGiaTriThongKeHoGiaDinh.getSelectionModel().selectFirst();
                break;
        }
    }

    public void onEnterPressedTrongOThongKeGiaiThuongHoGiaDinh(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            thongKeGiaiThuongHoGiaDinh();
        }
    }

    private void setLabelTongGiaTriThongKeHoGiaDinh() {
        int tongGiaTriGiaiThuong = 0;
        for (TraoThuongDisplayModel traoThuongDisplayModel : tableViewThongKeHoGiaDinh.getItems())
            tongGiaTriGiaiThuong += traoThuongDisplayModel.getThanhTien();
        labelTongGiaTriThongKeHoGiaDinh.setText("Tổng giá trị giải thưởng: " +
                NumberFormat.getInstance().format(tongGiaTriGiaiThuong));
    }
}
