package com.se07.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Lớp hiển thị giao diện đăng nhập đăng nhập
 */
public class LoginView {
    /**
     * Phương thức hiển thị màn hình đăng nhập
     */
    public void openWindow() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("QUẢN LÝ NHÂN KHẨU");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
