package com.se07.controller.controllers;

import com.se07.model.models.NhanKhauModel;
import com.se07.view.TrangChuCanBoView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerNhanKhauView extends ControllerTrangChuView implements Initializable {
    @FXML
    AnchorPane anchorPaneChinhCanBo;

    @FXML
    TableColumn tableColumnId, tableColumnHoTen, tableColumnBietDanh, tableColumnNgaySinh, tableColumnGioiTinh,
            tableColumnTonGiao, tableColumnDiaChi, tableColumnTinhTrang;
    @FXML
    TableView<NhanKhauModel> tableViewTatCaNhanKhauCanBo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    public void setButtonLogoutAdmin() {
    }

    public void setButtonNewPeopleAdmin(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("ThemMoiNhanKhauCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setButtonTemporaryRegistrationPeopleAdmin(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("DangKyTamVangCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setButtonAbsentRegistrationAdmin(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("DangKyTamTruCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setButtonRemovePeopleAdmin() {

    }

    public void setButtonLocThongTinHoKhauCanBo() {

    }
}
