package com.se07.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NhanKhauCanBoView {
    public Stage openWindow() {
        try{//
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("NhanKhauCanBoView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 695, 603);
            stage.setTitle("QUẢN LÝ NHÂN KHẨU");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            System.out.println(1);
            System.out.println(e.getMessage());
        }
        return null;
    }
}
