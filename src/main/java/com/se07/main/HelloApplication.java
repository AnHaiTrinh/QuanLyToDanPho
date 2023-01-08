package com.se07.main;

import com.se07.controller.controllers.ControllerMain;
import com.se07.controller.controllers.ControllerTrangChuView;
import com.se07.view.AdminView;
import com.se07.view.LoginView;
import com.se07.view.Main;
import com.se07.view.TrangChuCanBoView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Main main = new Main();
        main.openWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}