package com.se07.main;


import com.se07.view.LoginView;

import javafx.application.Application;

import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginView loginView = new LoginView();
        loginView.openWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}