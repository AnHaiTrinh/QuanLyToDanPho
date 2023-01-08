package com.se07.controller.controllers;

import com.se07.view.LoginView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMain implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void openWindow(Stage stage) {
        try{//
            FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource(".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 915, 603);
            stage.setTitle("QUẢN LÝ NHÂN KHẨU");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            System.out.println(1);
            System.out.println(e.getMessage());
        }
    }
}
