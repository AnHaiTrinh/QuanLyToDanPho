package com.se07.controller.controllers;

import com.se07.util.ConnectionDatabase;
import com.se07.view.AdminView;
import com.se07.view.LoginView;
import com.se07.view.TreasurerView;
import com.se07.view.UserView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ControllerLoginView {
    @FXML
    Label LabelAlertLogin;
    @FXML
    TextField TextFieldUserName;
    @FXML
    PasswordField PasswordFieldPassword;
    @FXML
    Button buttonLogin;
    @FXML
    Button ButtonCancelLogin;
    @FXML
    CheckBox checkBoxAdmin, checkBoxUser, checkBoxTreasurer;
    @FXML
    BorderPane borderPaneMainAdmin;
    private Stage stage;

    public void keyPressedEscLogin(){
        borderPaneMainAdmin.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ESCAPE)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Đăng xuất");
                alert.setHeaderText("Bạn thực sự muốn đăng xuất");
                alert.setContentText("Bạn có muốn lưu trước khi thoát");
                if(alert.showAndWait().get() == ButtonType.OK) {
                    stage = (Stage) borderPaneMainAdmin.getScene().getWindow();
                    System.out.println("Bạn đã đăng xuất khỏi trái đất");
                    stage.close();
                }
            }
        });
    }

    public void keyPressedEnter(){
        TextFieldUserName.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                onButtonLogin();
            }
        });
        PasswordFieldPassword.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                onButtonLogin();
            }
        });
        checkBoxAdmin.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                onButtonLogin();
            }
        });
        checkBoxUser.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                onButtonLogin();
            }
        });
        checkBoxTreasurer.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                onButtonLogin();
            }
        });
    }

    public  void onButtonLogin(){
        if(TextFieldUserName.getText().isBlank()==false && PasswordFieldPassword.getText().isBlank()==false){
            validateLogin();
        }else{
            LabelAlertLogin.setText("Vui lòng nhập tên hoặc mật khẩu");
        }
    }
    public void onButtonCancelLogin(){
        PasswordFieldPassword.setText("");
        TextFieldUserName.setText("");
        LabelAlertLogin.setText("");
        checkBoxAdmin.setSelected(false);
        checkBoxTreasurer.setSelected(false);
        checkBoxUser.setSelected(false);
    }
    public void validateLogin(){
        Connection connection = ConnectionDatabase.getConnection();
        String veritylogin = "select * from dangnhap where passwordd='" + PasswordFieldPassword.getText() +
                "'and userd='"+ TextFieldUserName.getText() + "'";
       System.out.println(veritylogin);
        if(veritylogin.indexOf(';')>0){
            Alert alert =  new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText("Có lẽ bạn đang muốn hack hệ thống");
            alert.setContentText("Hãy làm điều gì có ích hơn nhé");
            if(alert.showAndWait().get()==ButtonType.OK){
                stage = (Stage) borderPaneMainAdmin.getScene().getWindow();
                System.out.println("Bạn đã đăng xuất khỏi trái đất");
                stage.close();
                LoginView loginView = new LoginView();
                loginView.openWindow();
            }
        }else {
            try{
                Statement statement  = connection.createStatement();
                ResultSet queryResult = statement.executeQuery(veritylogin);
                if(queryResult.getRow()==0){
                    System.out.println("F");
                    LabelAlertLogin.setText("Vui lòng nhập lại tên hoặc mật khẩu");
                }
                while (queryResult.next()){
               System.out.println(-1);
                System.out.println(queryResult.getString(2));

                    if(queryResult.getRow()==1){
                        if(TextFieldUserName.getText().equals("admin") && checkBoxAdmin.isSelected()==true && checkBoxUser.isSelected()==false && checkBoxTreasurer.isSelected()==false){
                            LabelAlertLogin.setText("");
                            AdminView adminView = new AdminView();
                            adminView.openWindow();
                            stage = (Stage) borderPaneMainAdmin.getScene().getWindow();
                            stage.close();
                        } else if (TextFieldUserName.getText().equals("user")&&checkBoxAdmin.isSelected()==false && checkBoxUser.isSelected()==true && checkBoxTreasurer.isSelected()==false) {
                            LabelAlertLogin.setText("");
                            UserView userView = new UserView();
                            userView.openWindow();
                            stage = (Stage) borderPaneMainAdmin.getScene().getWindow();
                            stage.close();
                        }else if(TextFieldUserName.getText().equals("treasurer") && checkBoxAdmin.isSelected()==false && checkBoxUser.isSelected()==false && checkBoxTreasurer.isSelected()==true){
                            LabelAlertLogin.setText("");
                            TreasurerView treasurerView = new TreasurerView();
                            treasurerView.openWindow();
                            stage = (Stage) borderPaneMainAdmin.getScene().getWindow();
                            stage.close();
                        }else {
                            LabelAlertLogin.setText("vui lòng chọn đúng chức danh");
                        }
                    }else{
                        LabelAlertLogin.setText("Vui lòng nhập lại tên hoặc mật khẩu");
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
