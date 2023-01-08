package com.se07.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class View {
    private Pane pane;
    AnchorPane root = null;

    public View(Pane pane) {
        this.pane = pane;
    }
    public View(){

    }
    public Pane getPane(String fileName) {
        FXMLLoader fxmlLoader = new FXMLLoader(View.class.getResource(fileName+".fxml"));
        root = fxmlLoader.getRoot();
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
