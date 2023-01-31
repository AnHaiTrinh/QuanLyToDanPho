package com.se07.main;


import com.se07.view.LoginView;

import javafx.application.Application;

import javafx.stage.Stage;


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginView loginView = new LoginView();
        loginView.openWindow();
    }

    @Override
    public void stop() throws Exception {
        File userInfo = new File("src/main/resources/UserData.txt");
        userInfo.delete();
        File dir = new File("src/main/resources");
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg");
            }
        });

        for (File file : files) {
            file.delete();
        }
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}