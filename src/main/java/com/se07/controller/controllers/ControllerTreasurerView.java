package com.se07.controller.controllers;

import com.se07.view.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerTreasurerView {
    @FXML
    Button buttonLogoutTreasurer;
    @FXML
    AnchorPane anchorPaneTreasurerMax;
    @FXML
    Label lableShowAwardTreasurer;
    private Stage stage;

    public void keyPressedEscTreasurer(){
        anchorPaneTreasurerMax.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ESCAPE)){
                setButtonLogoutTreasurer();
            }
        });
    }
    public void setButtonAwardTreasurer(){
        lableShowAwardTreasurer.setText("Phần thưởng của bạn là c");
    }
    public void setButtonLogoutTreasurer(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Đăng xuất");
        alert.setHeaderText("Bạn thực sự muốn đăng xuất");
        alert.setContentText("Bạn có muốn lưu trước khi thoát");
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) anchorPaneTreasurerMax.getScene().getWindow();
            System.out.println("Bạn đã đăng xuất khỏi trái đất");
            stage.close();
            LoginView loginView = new LoginView();
            loginView.openWindow();
        }
//
    }
}
