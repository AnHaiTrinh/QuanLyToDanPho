package com.se07.controller.controllers;

import com.se07.view.TrangChuCanBoView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGiaiThuongDacBietCanBoView implements Initializable {
    @FXML
    Pane paneTest;
    @FXML
    Button buttonGiaiThuongCanBo;
    ControllerTrangChuView controllerTrangChuView = new ControllerTrangChuView();

    public void setButtonLogoutAdmin() {

    }

    public void setButtonHomeAdmin(ActionEvent e) throws IOException {
        controllerTrangChuView.setButtonHomeAdmin(e);
    }

    public void setButtonPeopleAdmin(ActionEvent e) throws IOException {
        controllerTrangChuView.setButtonPeopleAdmin(e);
    }

    public void setButtonUserAdmin(ActionEvent e) throws IOException {
        controllerTrangChuView.setButtonUserAdmin(e);
    }

    public void setButtonGiaiThuongCanBo(ActionEvent e) throws IOException {
        controllerTrangChuView.setButtonGiaiThuongCanBo(e);
    }

    public void setButtonTaoBieuMauDipDacBietCanBoView(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("TaoBieuMauDipDacBietCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setButtonTaoBieuMauDipBTCanBo(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("TaoBieuMauDipThuongCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setButtonLocThongTinHoKhauCanBo() {

    }

    public void setTest() {

    }

    public void setButtonXemPhanThuongDipThuongCanBo(ActionEvent e) throws IOException {
        controllerTrangChuView.setButtonGiaiThuongCanBo(e);
    }

    public void setButtonXemPhanThuongDipDacBietCanBo(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("GiaiThuongThanhTichCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paneTest.setVisible(false);
        buttonGiaiThuongCanBo.setOnMouseMoved(mouseEvent -> {
            paneTest.setVisible(true);
        });
        paneTest.setOnMouseExited(mouseEvent -> {
            paneTest.setVisible(false);
        });
    }
}
