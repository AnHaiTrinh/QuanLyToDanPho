package com.se07.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginView {
    public Stage openWindow() {
        try{
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("QUẢN LÝ NHÂN KHẨU");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }//
}
