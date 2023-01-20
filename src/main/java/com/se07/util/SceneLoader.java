package com.se07.util;

import com.se07.view.TrangChuCanBoView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoader {
    public void loadFxmlFileCanBo(Stage stage, String filename) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource(filename));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
