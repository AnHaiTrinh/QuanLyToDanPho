package com.se07.controller.controllers;

import com.se07.view.LoginView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAdminView implements Initializable {
    @FXML
    AnchorPane paneUserAdmin, paneLeftAdmin, paneAbsentRegistrationAdmin, paneRightAdmin, paneNewUserAdmin, paneSplitUserAdmin;
    @FXML

    Button buttonLogoutAdmin, buttonCancelNewPeopleAdmin;
    @FXML
    AnchorPane anchorPaneAdminMax, panePeopleAdmin, paneMidPeopleAdmin, paneNewPeopleAdmin, paneTemporaryRegistrationPeopleAdmin;
    @FXML
    AnchorPane anchorPaneConfirmAwardAdmin, anchorPaneCheckAwardAdmin, anchorPaneCreateFormAwardAdmin;
    @FXML
    Label lableHelloAdmin;
    Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lableHelloAdmin.setText("");
        setVisibleAllFalse();
        anchorPaneAdminMax.setVisible(true);
        paneLeftAdmin.setVisible(true);
        paneRightAdmin.setVisible(true);
    }
    public void setVisibleAllFalse(){
        paneLeftAdmin.setVisible(false);
        paneRightAdmin.setVisible(false);
        paneUserAdmin.setVisible(false);
        paneAbsentRegistrationAdmin.setVisible(false);
        paneNewUserAdmin.setVisible(false);
        paneSplitUserAdmin.setVisible(false);
        panePeopleAdmin.setVisible(false);
        paneMidPeopleAdmin.setVisible(false);
        paneNewPeopleAdmin.setVisible(false);
        paneTemporaryRegistrationPeopleAdmin.setVisible(false);
        anchorPaneConfirmAwardAdmin.setVisible(false);
        anchorPaneCheckAwardAdmin.setVisible(false);
        anchorPaneCreateFormAwardAdmin.setVisible(false);
    }
    public void setButtonAcceptTemporaryRegistrationPeopleAdmin(){

    }
    public void setButtonAcceptSplitUserAdmin(){

    }
    public void setButtonAcceptAbsentRegistrationAdmin(){

    }
    public void setMenuItemCreateAwardAdmin(){
        setVisibleAllFalse();
        paneLeftAdmin.setVisible(true);
        anchorPaneCreateFormAwardAdmin.setVisible(true);
    }
    public void setMenuItemConfirmAwardAdmin(){
        setVisibleAllFalse();
        paneLeftAdmin.setVisible(true);
        anchorPaneConfirmAwardAdmin.setVisible(true);
    }
    public void setMenuItemCheckAwardAdmin(){
        setVisibleAllFalse();
        paneLeftAdmin.setVisible(true);
        anchorPaneCheckAwardAdmin.setVisible(true);
    }
    public void setButtonAbsentRegistrationAdmin(){
        setVisibleAllFalse();
        paneLeftAdmin.setVisible(true);
        paneAbsentRegistrationAdmin.setVisible(true);
    }
    public void setButtonCancelAbsentRegistrationAdmin(){
        setButtonPeopleAdmin();
    }
    public void setButtonCancelTemporaryRegistrationPeopleAdmin(){
        setButtonPeopleAdmin();
    }
    public void setButtonTemporaryRegistrationPeopleAdmin(){
        setVisibleAllFalse();
        paneLeftAdmin.setVisible(true);
        paneTemporaryRegistrationPeopleAdmin.setVisible(true);
    }
    public void setButtonCancelNewPeopleAdmin(){
        setButtonPeopleAdmin();
    }
    public void setButtonUserAdmin(){
        setVisibleAllFalse();
        paneLeftAdmin.setVisible(true);
        paneUserAdmin.setVisible(true);
    }
    public void setButtonNewPeopleAdmin(){
        setVisibleAllFalse();
        paneLeftAdmin.setVisible(true);
        paneNewPeopleAdmin.setVisible(true);

    }
    public void keyPressedEscAdmin(){
        anchorPaneAdminMax.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ESCAPE)){
                setButtonLogoutAdmin();
            }
        });
    }
    public void  setButtonHomeAdmin(){
        setVisibleAllFalse();
        paneLeftAdmin.setVisible(true);
        paneRightAdmin.setVisible(true);
    }
    public  void setButtonPeopleAdmin(){
        setVisibleAllFalse();
        paneLeftAdmin.setVisible(true);
        panePeopleAdmin.setVisible(true);
        paneMidPeopleAdmin.setVisible(true);
        paneRightAdmin.setVisible(true);

    }
    public void setButtonRemovePeopleAdmin() {
        System.out.println("ban da xoa thanh cong");
    }
    public void setButtonNewUserAdmin(){
        setVisibleAllFalse();
        paneLeftAdmin.setVisible(true);
        paneNewUserAdmin.setVisible(true);
    }
    public void setButtonSplitUserAdmin(){
        setVisibleAllFalse();
        paneLeftAdmin.setVisible(false);
        paneSplitUserAdmin.setVisible(true);
    }
    public void setButtonRemoveUserAdmin(){

    }
    public void setButtonCancelNewUserAdmin(){
        setButtonUserAdmin();
    }
    public void setButtonCancelSplitUserAdmin(){
        setButtonUserAdmin();
    }
    public void setButtonAcceptNewPeopleAdmin(){

    }
    public void setButtonCancelImportFormAwardAdmin(){
    }
    public void setButtonCreateFormAwardAdmin(){
        setButtonHomeAdmin();
    }
    public void setButtonLogoutAdmin(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Đăng xuất");
        alert.setHeaderText("Bạn thực sự muốn đăng xuất");
        alert.setContentText("Bạn có muốn lưu trước khi thoát");
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) anchorPaneAdminMax.getScene().getWindow();
            System.out.println("Bạn đã đăng xuất khỏi trái đất");
            stage.close();
            LoginView loginView = new LoginView();
            loginView.openWindow();
        }

    }


}
