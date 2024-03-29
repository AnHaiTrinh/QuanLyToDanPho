package com.se07.controller.controllers.controllersketoan;

import com.se07.controller.services.DipTraoThuongService;
import com.se07.controller.services.PhanThuongService;
import com.se07.controller.services.ThongTinThanhTichService;
import com.se07.controller.services.TraoThuongService;
import com.se07.model.models.ThongTinThanhTichDisplayModel;
import com.se07.model.models.ThongTinTraoThuongDipDacBiet;
import com.se07.model.models.ThongTinTraoThuongThanhTich;
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

public class ControllerGiaiThuongThanhTichThuQuyView extends ControllerThuQuyView {

    String tenDip;
    int nam, idDip;
    @FXML
    Label labelTenDipTraoThuongThanhTich, labelNamTraoThuongThanhTich;
    @FXML
    TableView<ThongTinTraoThuongThanhTich> tableViewGiaiThuongThuQuy;
    @FXML
    TableColumn<ThongTinTraoThuongThanhTich, String> tableColumnMaNhanKhauGiaiThuongThanhTichThuQuy,
            tableColumnHoTenGiaiThuongThanhTichThuQuy, tableColumnTenGiaiThuongThanhTichThuQuy,
            tableColumnKieuThanhTichThuQuy, tableColumnCapThanhTichThuQuy;
    private final TraoThuongService traoThuongService = new TraoThuongService();
    private final PhanThuongService phanThuongService = new PhanThuongService();
    @FXML
    TableColumn<ThongTinTraoThuongThanhTich, Integer> tableColumnIDNhapGiaiThuongThanhTichThuQuy,
            tableColumnDonGiaGiaiThuongThanhTichThuQuy, tableColumnSoLuongGiaiThuongThanhTichThuQuy,
            tableColumnThanhTienGiaiThuongThanhTichThuQuy;

    public void onPressedButtonChonPhanQuaThuQuy(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.isPrimaryButtonDown()) {
            ThongTinTraoThuongThanhTich thongTinTraoThuongThanhTich =
                    tableViewGiaiThuongThuQuy.getSelectionModel().getSelectedItem();
            if (thongTinTraoThuongThanhTich == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Vui lòng chọn trường hợp muốn trao quà");
                alert.showAndWait();
            } else {
                if (thongTinTraoThuongThanhTich.getTenPhanThuong() != null) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Trường hợp đã được trao thưởng. Bạn có muốn tiếp tục?");
                    alert.setContentText("Thông tin về lần trao thưởng cũ sẽ bị xóa");
                    if (alert.showAndWait().get() == ButtonType.CANCEL) return;
                }
                traoThuongThuQuy(thongTinTraoThuongThanhTich.getIdNhap());
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


    public void onPressedButtonTraoThuongChoTatCaThanhTichThuQuy(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn có muốn tiếp tục?");
            alert.setContentText("Thông tin về lần trao thưởng cũ sẽ bị xóa");
            if (alert.showAndWait().get() == ButtonType.CANCEL) return;
            traoThuongThuQuy(-1);
        }
    }

    public void loadData() {
        tableColumnIDNhapGiaiThuongThanhTichThuQuy.setCellValueFactory(new PropertyValueFactory<>("idNhap"));
        tableColumnMaNhanKhauGiaiThuongThanhTichThuQuy.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        tableColumnHoTenGiaiThuongThanhTichThuQuy.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        tableColumnTenGiaiThuongThanhTichThuQuy.setCellValueFactory(new PropertyValueFactory<>("tenPhanThuong"));
        tableColumnKieuThanhTichThuQuy.setCellValueFactory(new PropertyValueFactory<>("kieuThanhTich"));
        tableColumnCapThanhTichThuQuy.setCellValueFactory(new PropertyValueFactory<>("capThanhTich"));
        tableColumnDonGiaGiaiThuongThanhTichThuQuy.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        tableColumnSoLuongGiaiThuongThanhTichThuQuy.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        tableColumnThanhTienGiaiThuongThanhTichThuQuy.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));

        labelTenDipTraoThuongThanhTich.setText("Tên dịp: " + tenDip);
        labelNamTraoThuongThanhTich.setText("Năm: " + nam);
        idDip = new DipTraoThuongService().getDipTraoThuongByTenAndNam(tenDip, nam).get().getId();
        tableViewGiaiThuongThuQuy.setItems(new ThongTinThanhTichService().getAllThongTinThanhTichAndTraoThuongByIdDip(idDip));
    }

    public void onPressedButtonXoaThanhTichThuQuy(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            xoaTraoThuongThanhTichThuQuy();
        }
    }

    private void xoaTraoThuongThanhTichThuQuy() {
        ThongTinTraoThuongThanhTich thongTinThanhTichDisplayModel = tableViewGiaiThuongThuQuy.getSelectionModel().getSelectedItem();
        if (thongTinThanhTichDisplayModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn xóa");
            alert.showAndWait();
        } else if (thongTinThanhTichDisplayModel.getTenPhanThuong() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trường hợp chưa được trao thưởng");
            alert.showAndWait();
        } else {
            TraoThuongModel traoThuongModel = new TraoThuongModel(
                    thongTinThanhTichDisplayModel.getIdNhap(),
                    phanThuongService.getPhanThuongByTen(thongTinThanhTichDisplayModel.getTenPhanThuong()).get().getMaPhanThuong(),
                    thongTinThanhTichDisplayModel.getSoLuong()
            );
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xóa trường hợp này");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Thông báo");
                if (traoThuongService.deleteTraoThuongDipDacBiet(traoThuongModel)) {
                    info.setHeaderText("Xóa thành công");
                    thongTinThanhTichDisplayModel.setTenPhanThuong(null);
                    thongTinThanhTichDisplayModel.setDonGia(null);
                    thongTinThanhTichDisplayModel.setSoLuong(null);
                    thongTinThanhTichDisplayModel.setThanhTien(null);
                } else {
                    info.setHeaderText("Xóa không thành công");
                }
                info.showAndWait();
                tableViewGiaiThuongThuQuy.refresh();
            }
        }
    }
}