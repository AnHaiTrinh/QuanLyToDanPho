package com.se07.controller.controllers.controllersketoan;

import com.se07.util.SceneLoader;
import com.se07.view.LoginView;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerThuQuyView implements Initializable {
    final SceneLoader sceneLoader = new SceneLoader();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onPressedButtonTrangChuThuQuy(MouseEvent e) throws IOException {
        if(e.isPrimaryButtonDown()){
            sceneLoader.loadFxmlFileThuQuy((Stage) ((Node) e.getSource()).getScene().getWindow(), "TrangChuThuQuyView.fxml");
        }
    }
    public void onPressedButtonDangXuatThuQuy(MouseEvent e) throws IOException{
        if(e.isPrimaryButtonDown()){
            dangXuatThuQuyView((Stage) ((Node) e.getSource()).getScene().getWindow());
        }
    }
    public void onPressedButtonGiaiThuongThuQuy(MouseEvent e) throws IOException{
        if(e.isPrimaryButtonDown()){
            sceneLoader.loadFxmlFileThuQuy((Stage) ((Node) e.getSource()).getScene().getWindow(), "GiaiThuongThuQuyView.fxml");
        }
    }
    public void onPressedButtonThongKeThuQuy(MouseEvent e) throws IOException{
        if(e.isPrimaryButtonDown()){
            sceneLoader.loadFxmlFileThuQuy((Stage) ((Node) e.getSource()).getScene().getWindow(), "ThongKeThuQuyView.fxml");

        }
    }
    private void dangXuatThuQuyView(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo!");
        alert.setHeaderText("Bạn muốn đăng xuất ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
            LoginView loginView = new LoginView();
            loginView.openWindow();
        }
    }

}
