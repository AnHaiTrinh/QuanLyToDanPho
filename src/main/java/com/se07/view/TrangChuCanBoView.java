package com.se07.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Lớp hiển thị giao diện của tổ trưởng sau khi đăng nhập thành công
 */
public class TrangChuCanBoView {
    public Stage stage;

    /**
     * Phương thức hiển thị màn hình trang chủ của tổ trưởng
     */
    public void openWindow() {
        try {
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("TrangChuCanBoView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 915, 603);
            stage.setTitle("QUẢN LÝ NHÂN KHẨU");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
