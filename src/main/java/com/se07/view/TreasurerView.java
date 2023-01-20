package com.se07.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TreasurerView {
    private Stage stage;
    public Stage openWindow() {
        try{
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("treasurer.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 915, 603);
            Image image = new Image(LoginView.class.getResource("IconLogin.png").toString());
            stage.getIcons().add(image);
            stage.setTitle("QUẢN LÝ NHÂN KHẨU");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;//
    }
}
