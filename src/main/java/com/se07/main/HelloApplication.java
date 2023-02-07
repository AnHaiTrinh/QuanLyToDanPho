package com.se07.main;


import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.TamTruService;
import com.se07.controller.services.TamVangService;
import com.se07.controller.services.ThongTinDipDacBietService;
import com.se07.model.models.ThongTinDipDacBietDisplayModel;
import com.se07.model.models.ThongTinDipDacBietModel;
import com.se07.view.LoginView;
import com.se07.view.TrangChuCanBoView;
import com.se07.view.UserView;
import javafx.application.Application;

import javafx.collections.ObservableList;
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
        File userInfo = new File("UserData.txt");
        userInfo.delete();
        File dir = new File("./");
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