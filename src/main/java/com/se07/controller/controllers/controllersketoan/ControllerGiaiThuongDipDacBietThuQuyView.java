package com.se07.controller.controllers.controllersketoan;

import com.se07.controller.services.DipTraoThuongService;
import com.se07.controller.services.ThongTinDipDacBietService;
import com.se07.model.models.ThongTinTraoThuongDipDacBiet;
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
    public void onPressedButtonXoaDipDacBietThuQuy(MouseEvent e) throws IOException{
        if(e.isPrimaryButtonDown()){

        }
    }
}
