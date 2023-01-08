package com.se07.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main {
    public Stage stage;
    public Stage openWindow() {
        try{//
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("TrangChuCanBoView.fxml"));
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
        return null;
    }
    public void closeWindow() {
        stage = (Stage) this.stage.getScene().getWindow();
        stage.close();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
