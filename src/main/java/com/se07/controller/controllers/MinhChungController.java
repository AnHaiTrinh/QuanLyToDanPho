package com.se07.controller.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;

public class MinhChungController {

    @FXML
    BorderPane borderPaneMinhChung;
    @FXML
    ImageView imageViewMinhChung;
    @FXML
    Button buttonThayDoiFile;
    File fileMinhChung;


    public void onPressedButtonThayDoiTep(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            thayDoiTep();
        }
    }

    private void thayDoiTep() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File ảnh", "*.png", "*.jpg"));
        File minhChung = fileChooser.showOpenDialog(buttonThayDoiFile.getScene().getWindow());
        if (minhChung != null) {
            fileMinhChung = minhChung;
            buttonThayDoiFile.setText(minhChung.getName());
            imageViewMinhChung.setPreserveRatio(true);
            imageViewMinhChung.setFitHeight(imageViewMinhChung.getFitWidth());
            imageViewMinhChung.setFitWidth(imageViewMinhChung.getFitHeight());
            imageViewMinhChung.setImage(new Image(minhChung.getPath()));
        }
    }
}
