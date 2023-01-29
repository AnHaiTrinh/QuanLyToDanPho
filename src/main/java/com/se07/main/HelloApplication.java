package com.se07.main;


import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.TamTruService;
import com.se07.controller.services.TamVangService;
import com.se07.view.LoginView;
import com.se07.view.TrangChuCanBoView;
import com.se07.view.UserView;
import javafx.application.Application;

import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        UserView userView = new UserView();
        userView.openWindow();
    }

    @Override
    public void stop() throws Exception {
        File userInfo = new File("src/main/resources/UserData.txt");
        userInfo.delete();
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}