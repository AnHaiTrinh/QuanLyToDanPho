package com.se07.controller.controllers.controllerslogin;

import com.se07.util.ConnectionDatabase;
import com.se07.view.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    CheckBox checkBoxAdmin, checkBoxUser, checkBoxTreasurer;
    @FXML
    BorderPane borderPaneMainAdmin;
    private Stage stage;

    public void onEscPressedLogin(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            exit();
        }
    }

    public void onEnterPressedLogin(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            validateLogin();
        }
    }

    public void onPressedButtonLogin(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            validateLogin();
        }
    }

    public void onPressedButtonCancelLogin(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            reset();
        }
    }

    private void reset() {
        PasswordFieldPassword.setText("");
        TextFieldUserName.setText("");
        LabelAlertLogin.setText("");
        checkBoxAdmin.setSelected(false);
        checkBoxTreasurer.setSelected(false);
        checkBoxUser.setSelected(false);
    }

    private void exit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Đăng xuất");
        alert.setHeaderText("Bạn thực sự muốn thoát");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) borderPaneMainAdmin.getScene().getWindow();
            stage.close();
        }
    }

    private void validateLogin() {
        if (TextFieldUserName.getText().isBlank() == false && PasswordFieldPassword.getText().isBlank() == false) {
            Connection connection = ConnectionDatabase.getConnection();
            String veritylogin = "select * from users where password = ? and username = ?";
            try {
                PreparedStatement statement = connection.prepareStatement(veritylogin);
                statement.setString(1, PasswordFieldPassword.getText());
                statement.setString(2, TextFieldUserName.getText());
                ResultSet rs = statement.executeQuery();
                if (rs.getRow() == 0) {
                    LabelAlertLogin.setText("Vui lòng nhập lại tên hoặc mật khẩu");
                }
                while (rs.next()) {
                    Integer role = rs.getInt("role");
                    if (rs.getRow() == 1) {
                        LabelAlertLogin.setText("");
                        if (role == 1 && checkBoxAdmin.isSelected() == true && checkBoxUser.isSelected() == false && checkBoxTreasurer.isSelected() == false) {
                            FileWriter fileWriter = new FileWriter("UserData.txt");
                            fileWriter.write(rs.getInt("ID") + "\n" + rs.getString("username") + "\n"
                                    + rs.getString("password"));
                            fileWriter.close();
                            TrangChuCanBoView trangChuCanBoView = new TrangChuCanBoView();
                            trangChuCanBoView.openWindow();
                            stage = (Stage) borderPaneMainAdmin.getScene().getWindow();
                            stage.close();
                        } else if (role == 2 && checkBoxAdmin.isSelected() == false && checkBoxUser.isSelected() == true && checkBoxTreasurer.isSelected() == false) {
                            FileWriter fileWriter = new FileWriter("UserData.txt");
                            fileWriter.write(rs.getInt("ID") + "\n" + rs.getString("username") + "\n"
                                    + rs.getString("password"));
                            fileWriter.close();
                            UserView userView = new UserView();
                            userView.openWindow();
                            stage = (Stage) borderPaneMainAdmin.getScene().getWindow();
                            stage.close();
                        } else if (role == 0 && checkBoxAdmin.isSelected() == false && checkBoxUser.isSelected() == false && checkBoxTreasurer.isSelected() == true) {
                            FileWriter fileWriter = new FileWriter("UserData.txt");
                            fileWriter.write(rs.getInt("ID") + "\n" + rs.getString("username") + "\n"
                                    + rs.getString("password"));
                            fileWriter.close();
                            TreasurerView treasurerView = new TreasurerView();
                            treasurerView.openWindow();
                            stage = (Stage) borderPaneMainAdmin.getScene().getWindow();
                            stage.close();
                        } else {
                            LabelAlertLogin.setText("vui lòng chọn đúng chức danh");
                        }
                    } else {
                        LabelAlertLogin.setText("Vui lòng nhập lại tên hoặc mật khẩu");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            LabelAlertLogin.setText("Vui lòng nhập tên hoặc mật khẩu");
        }
    }
}
