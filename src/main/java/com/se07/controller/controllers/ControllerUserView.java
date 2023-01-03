package com.se07.controller.controllers;

import com.se07.view.LoginView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerUserView implements Initializable {

    @FXML
    AnchorPane paneRightUser, anchorPaneUserMax, paneNewPeopleUser, paneLeftUser,paneSplitUserUser;
    @FXML
    Button buttonLogoutUser;
    @FXML
    AnchorPane anchorPaneImportAwardUser, anchorPaneCheckAwardUser;
    private Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paneNewPeopleUser.setVisible(false);
        paneSplitUserUser.setVisible(false);
        anchorPaneCheckAwardUser.setVisible(false);
        anchorPaneImportAwardUser.setVisible(false);
    }
    public void keyPressedEscUser(){
        anchorPaneUserMax.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ESCAPE)){
                setButtonLogoutUser();
            }
        });
    }
    public void setButtonAcceptTemporaryRegistrationPeopleUser(){

    }
    public void setButtonAcceptSplitUserUser(){

    }
    public void setMenuItemImportInformationAwardUser(){
        paneRightUser.setVisible(false);
        anchorPaneCheckAwardUser.setVisible(false);
        anchorPaneImportAwardUser.setVisible(true);
    }
    public void setMenuItemCheckInformationAwardUser(){
        paneRightUser.setVisible(false);
        anchorPaneImportAwardUser.setVisible(false);
        anchorPaneCheckAwardUser.setVisible(true);
    }
    public void setMenuButtonAwardUser(){

    }
    public void setButtonCancelImportFormAwardUser(){
        anchorPaneImportAwardUser.setVisible(false);
        paneRightUser.setVisible(true);
    }
    public void setButtonCreateImportFormAwardUser(){
        anchorPaneImportAwardUser.setVisible(false);
        paneRightUser.setVisible(true);
    }
    public void setButtonPeopleUser(){
        anchorPaneImportAwardUser.setVisible(false);
        anchorPaneCheckAwardUser.setVisible(false);
        paneRightUser.setVisible(true);
    }
    public void setButtonCancelSplitUserUser(){
        paneSplitUserUser.setVisible(false);
    }
    public void setButtonNewPeopleUser(){
        paneLeftUser.setVisible(true);
        paneRightUser.setVisible(false);
        paneNewPeopleUser.setVisible(true);
    }
    public void setButtonCancelTemporaryRegistrationPeopleUser(){
        paneNewPeopleUser.setVisible(false);
        paneLeftUser.setVisible(true);
        paneRightUser.setVisible(true);
    }
    public void setButtonSplitUserUser(){
        paneSplitUserUser.setVisible(true);
    }
    public void setButtonHomeUser(){
        paneSplitUserUser.setVisible(false);
        paneRightUser.setVisible(true);
    }
    public void setButtonLogoutUser() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Đăng xuất");
        alert.setHeaderText("Bạn thực sự muốn đăng xuất");
        alert.setContentText("Bạn có muốn lưu trước khi thoát");
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) anchorPaneUserMax.getScene().getWindow();
            System.out.println("Bạn đã đăng xuất khỏi trái đất");
            stage.close();
            LoginView loginView = new LoginView();
            loginView.openWindow();
        }

    }
}
