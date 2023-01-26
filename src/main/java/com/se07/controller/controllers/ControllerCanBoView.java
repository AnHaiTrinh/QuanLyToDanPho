package com.se07.controller.controllers;

import com.se07.util.SceneLoader;
import com.se07.util.UserInfo;
import com.se07.view.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

/**
 * Lớp controller cơ sở để các lớp controller cho tổ trưởng khác kế thừa
 * Dùng để điều khiển các nút chung của các màn hình tổ trưởng (Trang chủ, Hộ khẩu, Nhân khẩu, Giải thưởng, Đăng xuất)
 */
public class ControllerCanBoView implements Initializable {

    @FXML
    Button buttonDangXuatCanBo;
    @FXML
    AnchorPane anchorPaneChinhCanBo;

    final int id = UserInfo.getUserId();
    final String username = UserInfo.getUsername();

    final String tinhTrang = "Đã xác nhận";
    final SceneLoader sceneLoader = new SceneLoader();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPaneChinhCanBo.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                Stage stage = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo!");
                alert.setHeaderText("Bạn muốn đăng xuất ?");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    stage.close();
                    LoginView loginView = new LoginView();
                    loginView.openWindow();
                }
            }
        });
    }

    /**
     * Phương thức được gọi khi nhấn nút Trang chủ
     * Nếu chuột trái được nhấn sẽ chuyển sang màn hình Trang chủ
     *
     * @param e Sự kiện chuột bắt được khi nhấn nút Trang chủ
     * @throws IOException
     */
    public void onPressedButtonTrangChuCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "TrangChuCanBoView.fxml");
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút Nhân khẩu
     * Nếu chuột trái được nhấn sẽ chuyển sang màn hình quản lý Nhân khẩu
     *
     * @param e Sự kiện chuột bắt được khi nhân nút Nhân Khẩu
     * @throws IOException
     */
    public void onPressedButtonNhanKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "NhanKhauCanBoView.fxml");
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút Hộ khẩu
     * Nếu chuột trái được nhấn sẽ chuyển sang màn hình quản lý Hộ khẩu
     *
     * @param e Sự kiện chuột bắt được khi nhân nút Hộ Khẩu
     * @throws IOException
     */
    public void onPressedButtonHoKhauCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "HoKhauCanBoView.fxml");
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút Giải thưởng
     * Nếu chuột trái được nhấn sẽ chuyển sang màn hình quản lý Giải thưởng
     *
     * @param e Sự kiện chuột bắt được khi nhân nút Giải thưởng
     * @throws IOException
     */
    public void onPressedButtonGiaiThuongCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(), "GiaiThuongCanBoView.fxml");
        }
    }

    /**
     * Phương thức được gọi khi nhấn nút Đăng xuất
     * Nếu chuột trái được nhấn sẽ đăng xuất về màn hình Đăng nhập
     *
     * @param e Sự kiện chuột bắt được khi nhân nút Đăng xuất
     * @throws IOException
     */
    public void onPressedButtonDangXuatCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            dangXuatCanBoView((Stage) ((Node) e.getSource()).getScene().getWindow());
        }
    }

    /**
     * Phương thức đăng xuất khỏi phần mềm và trở lại màn hình đăng nhập
     *
     * @param stage Màn hình hiện tại
     */
    private void dangXuatCanBoView(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo!");
        alert.setHeaderText("Bạn muốn đăng xuất ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
            LoginView loginView = new LoginView();
            loginView.openWindow();
        }
    }
}
