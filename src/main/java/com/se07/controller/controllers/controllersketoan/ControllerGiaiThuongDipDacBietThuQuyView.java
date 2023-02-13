package com.se07.controller.controllers.controllersketoan;

import com.se07.controller.services.DipTraoThuongService;
import com.se07.controller.services.PhanThuongService;
import com.se07.controller.services.ThongTinDipDacBietService;
import com.se07.controller.services.TraoThuongService;
import com.se07.model.models.ThongTinTraoThuongDipDacBiet;
import com.se07.model.models.TraoThuongModel;
import com.se07.view.TreasurerView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerGiaiThuongDipDacBietThuQuyView extends ControllerThuQuyView {

    String tenDip;
    int nam, idDip;
    @FXML
    Label labelTenDipTraoThuongDipDacBiet, labelNamTraoThuongDipDacBiet;
    @FXML
    TableView<ThongTinTraoThuongDipDacBiet> tableViewGiaiThuongThuQuy;
    @FXML
    TableColumn<ThongTinTraoThuongDipDacBiet, String> tableColumnMaNhanKhauGiaiThuongDipDacBietThuQuy,
            tableColumnHoTenGiaiThuongDipDacBietThuQuy, tableColumnTenGiaiThuongDipDacBietThuQuy;
    @FXML
    TableColumn<ThongTinTraoThuongDipDacBiet, Integer> tableColumnIDNhapGiaiThuongDipDacBietThuQuy,
            tableColumnDonGiaGiaiThuongDipDacBietThuQuy, tableColumnSoLuongGiaiThuongDipDacBietThuQuy,
            tableColumnThanhTienGiaiThuongDipDacBietThuQuy;
    private final TraoThuongService traoThuongService = new TraoThuongService();
    private final PhanThuongService phanThuongService = new PhanThuongService();

    public void onPressedButtonChonPhanQuaThuQuy(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.isPrimaryButtonDown()) {
            ThongTinTraoThuongDipDacBiet thongTinTraoThuongDipDacBiet =
                    tableViewGiaiThuongThuQuy.getSelectionModel().getSelectedItem();
            if (thongTinTraoThuongDipDacBiet == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Vui lòng chọn trường hợp muốn trao quà");
                alert.showAndWait();
            } else {
                traoThuongThuQuy(thongTinTraoThuongDipDacBiet.getIdNhap());
            }
        }
    }

    private void traoThuongThuQuy(int idNhap) throws IOException {
        FXMLLoader loader = new FXMLLoader(TreasurerView.class.getResource("TraoThuongThuQuyView.fxml"));
        Parent root = loader.load();
        ControllerTraoThuongThuQuyView controller = loader.getController();
        controller.tenDip = tenDip;
        controller.nam = nam;
        controller.idNhap = idNhap;
        controller.setLabel();
        Stage stage = (Stage) tableViewGiaiThuongThuQuy.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }


    public void onPressedButtonTraoThuongChoTatCaDipDacBietThuQuy(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.isPrimaryButtonDown()) {
            traoThuongThuQuy(-1);
        }
    }

    public void loadData() {
        tableColumnIDNhapGiaiThuongDipDacBietThuQuy.setCellValueFactory(new PropertyValueFactory<>("idNhap"));
        tableColumnMaNhanKhauGiaiThuongDipDacBietThuQuy.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        tableColumnHoTenGiaiThuongDipDacBietThuQuy.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        tableColumnTenGiaiThuongDipDacBietThuQuy.setCellValueFactory(new PropertyValueFactory<>("tenPhanThuong"));
        tableColumnDonGiaGiaiThuongDipDacBietThuQuy.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        tableColumnSoLuongGiaiThuongDipDacBietThuQuy.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        tableColumnThanhTienGiaiThuongDipDacBietThuQuy.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));

        labelTenDipTraoThuongDipDacBiet.setText("Tên dịp: " + tenDip);
        labelNamTraoThuongDipDacBiet.setText("Năm: " + nam);
        idDip = new DipTraoThuongService().getDipTraoThuongByTenAndNam(tenDip, nam).get().getId();
        tableViewGiaiThuongThuQuy.setItems(new ThongTinDipDacBietService().getAllThongTinDipDacBietAndTraoThuongByIdDip(idDip));
    }

    public void onPressedButtonXoaDipDacBietThuQuy(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            xoaTraoThuongDipDacBietThuQuy();
        }
    }

    private void xoaTraoThuongDipDacBietThuQuy() {
        ThongTinTraoThuongDipDacBiet thongTinTraoThuongDipDacBiet = tableViewGiaiThuongThuQuy.getSelectionModel().getSelectedItem();
        if (thongTinTraoThuongDipDacBiet == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn xóa");
            alert.showAndWait();
        } else if (thongTinTraoThuongDipDacBiet.getTenPhanThuong() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trường hợp chưa được trao thưởng");
            alert.showAndWait();
        } else {
            TraoThuongModel traoThuongModel = new TraoThuongModel(
                    thongTinTraoThuongDipDacBiet.getIdNhap(),
                    phanThuongService.getPhanThuongByTen(thongTinTraoThuongDipDacBiet.getTenPhanThuong()).get().getMaPhanThuong(),
                    thongTinTraoThuongDipDacBiet.getSoLuong()
            );
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xóa trường hợp này");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Thông báo");
                if (traoThuongService.deleteTraoThuongDipDacBiet(traoThuongModel)) {
                    info.setHeaderText("Xóa thành công");
                    thongTinTraoThuongDipDacBiet.setTenPhanThuong(null);
                    thongTinTraoThuongDipDacBiet.setDonGia(null);
                    thongTinTraoThuongDipDacBiet.setSoLuong(null);
                    thongTinTraoThuongDipDacBiet.setThanhTien(null);
                } else {
                    info.setHeaderText("Xóa không thành công");
                }
                info.showAndWait();
                tableViewGiaiThuongThuQuy.refresh();
            }
        }
    }
}
