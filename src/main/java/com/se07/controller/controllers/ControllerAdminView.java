package com.se07.controller.controllers;

import com.se07.controller.services.HoKhauService;
import com.se07.model.models.HoKhauModel;
import com.se07.view.LoginView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    @FXML
    DatePicker datePickerNgayThanhLapThemMoiHoKhauCanBo;
    @FXML
    TextField textFieldMaHoKhauThemMoiHoKhauCanBo, textFieldMaChuHoThemMoiHoKhauCanBo,textFieldDiaChiThemMoiHoKhauCanBo;
    @FXML
    TableColumn<HoKhauModel, String> tableColumnMaHoHoKhauCanBo, tableColumnHotenHoKhauCanBo, tableColumnDiaChiHoKhauCanBo;
    @FXML
    TableView tableViewTatCaHoKhauCanBo;
    @FXML
    TextField textFieldLocThongTinHoKhauCanBo;
    @FXML
            Button buttonLocThongTinHoKhauCanBo, buttonAcceptNewUserAdmin;
    @FXML
            AnchorPane paneThongTinHoKhauCanBo;
    Stage stage;
    LocalDate today = LocalDate.now();
    HoKhauService hoKhauService = new HoKhauService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lableHelloAdmin.setText("");
        setVisibleAllFalse();
        anchorPaneAdminMax.setVisible(true);
        paneLeftAdmin.setVisible(true);
        paneRightAdmin.setVisible(true);
    }
    public void setAllButtonEnterFalse(){
        buttonAcceptNewUserAdmin.setDefaultButton(false);
        buttonLocThongTinHoKhauCanBo.setDefaultButton(false);
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
    public  void setButtonAcceptNewUserAdmin(){
        if(textFieldMaHoKhauThemMoiHoKhauCanBo.getText().isBlank()||
                textFieldDiaChiThemMoiHoKhauCanBo.getText().isBlank()||datePickerNgayThanhLapThemMoiHoKhauCanBo.getValue().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ các trường");
            alert.showAndWait();
            return;
        }
        String maHoKhau = textFieldMaHoKhauThemMoiHoKhauCanBo.getText();
        String maChuHo = textFieldMaChuHoThemMoiHoKhauCanBo.getText();
        Date ngayLap = new Date(datePickerNgayThanhLapThemMoiHoKhauCanBo.getValue().toEpochDay());
        String diaChi = textFieldDiaChiThemMoiHoKhauCanBo.getText();
        HoKhauModel hoKhauModel = new HoKhauModel(maHoKhau, maChuHo, diaChi, ngayLap, 1);

        if(!hoKhauService.getHoKhauByMaHoKhau(maHoKhau).isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Mã hộ khẩu đã tồn tại");
            alert.showAndWait();
        }
        else {
            if(hoKhauService.insertNewHoKhau(hoKhauModel)){
                System.out.println("oki");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("");
                alert.setContentText("Bạn đã thêm thành công");
                if(alert.showAndWait().get()==ButtonType.OK){
                    textFieldMaHoKhauThemMoiHoKhauCanBo.setText("");
                    textFieldDiaChiThemMoiHoKhauCanBo.setText("");
                    textFieldMaChuHoThemMoiHoKhauCanBo.setText("");
                }
            }else{
                Alert alert =new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Thông báo");
                alert.setHeaderText("");
                alert.setContentText("Thêm thất bại");
                alert.showAndWait();
            }
        }
    }
    public void setButtonLocThongTinHoKhauCanBo(){
        ObservableList<HoKhauModel> hoKhauModelObservableListSearch= FXCollections.observableArrayList();
        String answer = textFieldLocThongTinHoKhauCanBo.getText();
        if(answer==null) {
            setButtonUserAdmin();
        }else {
            ObservableList<HoKhauModel> hoKhauModelObservableList = hoKhauService.getAllHoKhau();
            for (HoKhauModel hoKhauModel: hoKhauModelObservableList) {
                String check  = (String) (hoKhauModel.getMaHoKhau()+hoKhauModel.getDiaChi()+hoKhauModel.getChuHo());
                if(check.indexOf(answer)>=0){
                    hoKhauModelObservableListSearch.add(hoKhauModel);
                }
            }
            tableViewTatCaHoKhauCanBo.setItems(hoKhauModelObservableListSearch);
        }
    }

    public void setNhanNutThemHoKhauMoiCanBo(){
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
        setAllButtonEnterFalse();
        buttonLocThongTinHoKhauCanBo.setDefaultButton(true);
        setVisibleAllFalse();
        paneLeftAdmin.setVisible(true);
        paneUserAdmin.setVisible(true);
        ObservableList<HoKhauModel> hoKhauModelObservableList = hoKhauService.getAllHoKhau();
        tableColumnMaHoHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("maHoKhau"));
        tableColumnHotenHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("chuHo"));
        tableColumnDiaChiHoKhauCanBo.setCellValueFactory(new PropertyValueFactory<HoKhauModel, String>("diaChi"));
        tableViewTatCaHoKhauCanBo.setItems(hoKhauModelObservableList);
        System.out.println("\n");
        System.out.println(hoKhauModelObservableList);
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
        datePickerNgayThanhLapThemMoiHoKhauCanBo.setValue(today);
        setAllButtonEnterFalse();
        buttonAcceptNewUserAdmin.setDefaultButton(true);
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
        HoKhauModel hoKhauModel = (HoKhauModel) tableViewTatCaHoKhauCanBo.getSelectionModel().getSelectedItem();
        if(hoKhauModel!=null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText("Bạn đang chắc chắn muốn chuyển hộ khẩu");
            if(alert.showAndWait().get()==ButtonType.OK){
                hoKhauService.deleteHoKhau(hoKhauModel);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Thông báo");
                alert1.setHeaderText("Bạn đã chuyển thành công");
                alert1.showAndWait();
                setButtonUserAdmin();
            }
        }else {

            Alert alert =  new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText("Vui lòng chọn hộ chuyển đi");
            alert.showAndWait();
        }
    }
    public void setNhanNutDeleteTrongBangHoKhauCanBo(){
        tableViewTatCaHoKhauCanBo.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.DELETE)){
                setButtonRemoveUserAdmin();
            }
        });
    }
    public void setNhanNutHoKhauCanBo(){
        paneUserAdmin.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.N) ){
                System.out.println(1);
                setButtonNewUserAdmin();
            }
        });
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
