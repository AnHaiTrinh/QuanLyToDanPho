package com.se07.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminView {
    public Stage openWindow() {
        try{//
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 915, 603);
            Image image = new Image(AdminView.class.getResource("IconLogin.png").toString());
            stage.getIcons().add(image);
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
