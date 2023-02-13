package com.se07.controller.controllers.controllersketoan;

import com.se07.controller.services.DipTraoThuongService;
import com.se07.controller.services.ThongTinThanhTichService;
import com.se07.model.models.ThongTinTraoThuongThanhTich;
import com.se07.view.TreasurerView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
            ThongTinTraoThuongThanhTich thongTinTraoThuongThanhTich =
                    tableViewGiaiThuongThuQuy.getSelectionModel().getSelectedItem();
            if (thongTinTraoThuongThanhTich == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Vui lòng chọn trường hợp muốn trao quà");
                alert.showAndWait();
            } else {
                traoThuongThuQuy(-1);
            }
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
    public void onPressedButtonXoaThanhTichThuQuy(MouseEvent e) throws IOException{

    }

}
