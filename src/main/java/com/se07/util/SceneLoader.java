package com.se07.util;

import com.se07.view.TrangChuCanBoView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Lớp tiện ích giúp tải các màn hình mới từ file fxml
 */
public class SceneLoader {
    /**
     * Phương thức tải các màn hình của tổ trưởng
     *
     * @param stage    Màn hình hiện tại
     * @param filename File .fxml muốn tải
     * @throws IOException
     */
    public void loadFxmlFileCanBo(Stage stage, String filename) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource(filename));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void loadFxmlFileHoGiaDinh(Stage stage, String filename) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource(filename));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
