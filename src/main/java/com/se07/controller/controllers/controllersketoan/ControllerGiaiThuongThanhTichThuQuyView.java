package com.se07.controller.controllers.controllersketoan;

import com.se07.model.models.ThongTinThanhTichDisplayModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGiaiThuongThanhTichThuQuyView extends ControllerThuQuyView implements Initializable {
    @FXML
    TableColumn tableColumnMaNhanKhauGiaiThuongThanhTichThuQuy, tableColumnHoTenGiaiThuongThanhTichThuQuy,
            tableColumnTenDipGiaiThuongThanhTichThuQuy, tableColumnNamGiaiThuongThanhTichThuQuy,
            tableColumnCapGiaiThuongThanhTichThuQuy, tableColumnKieuGiaiThuongThanhTichThuQuy;

    public void onPressedButtonChonPhanQuaThuQuy(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
        }
    }

    public void onEnterPressedTrongOTimKiemGiaiThuongThuQuy() {

    }

    public void onPressedButtonTraoThuongChoTatCaThanhTichThuQuy() {

    }
}
