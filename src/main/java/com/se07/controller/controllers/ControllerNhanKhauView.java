package com.se07.controller.controllers;

import com.se07.controller.services.HoKhauService;
import com.se07.controller.services.NhanKhauService;
import com.se07.model.models.HoKhauModel;
import com.se07.model.models.NhanKhauModel;
import com.se07.view.TrangChuCanBoView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerNhanKhauView extends ControllerTrangChuView implements Initializable {
    @FXML
    AnchorPane anchorPaneChinhCanBo;

    @FXML
    TableView<NhanKhauModel>  tableViewNhanKhauAdmin;
    @FXML
    TableColumn<NhanKhauModel, String> tableComlumIDNhanKhauCanBo, tableComlumIDHoKhauNhanKhauCanBo, tableComlumHoTenNhanKhauCanBo, tableComlumBietDanhNhanKhauCanBo
            , tableComlumGioiTinhNhanKhauCanBo, tableComlumTonGiaoNhanKhauCanBo;
    @FXML
    TableColumn<NhanKhauModel, Date>  tableComlumNgaySinhNhanKhauCanBo;
    @FXML
    ComboBox comboBoxTimKiemHoKhauCanBo;
    @FXML
    TextField textFieldLocThongTinNhanKhauCanBo;
    private  String [] listTimKiem ={"Mã hộ khẩu","Họ tên","Biệt danh","Tình trạng","Mã nhân khẩu"};
    NhanKhauService nhanKhauService =  new NhanKhauService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        tableViewNhanKhauAdmin.setEditable(true);
        tableComlumIDNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("maNhanKhau"));
        tableComlumIDHoKhauNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("maHoKhau"));
        tableComlumHoTenNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel,String>("hoTen"));
        tableComlumBietDanhNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("bietDanh"));
        tableComlumNgaySinhNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, Date>("ngaySinh"));
        tableComlumGioiTinhNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("gioiTinh"));
        tableComlumTonGiaoNhanKhauCanBo.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("tonGiao"));
        displayAllNhanKhauCanBo();
        comboBoxTimKiemHoKhauCanBo.getItems().addAll(listTimKiem);
        comboBoxTimKiemHoKhauCanBo.getSelectionModel().selectFirst();
    }
    public void displayAllNhanKhauCanBo(){
        ObservableList<NhanKhauModel> hoKhauModelObservableList = nhanKhauService.getAllNhanKhau();
        tableViewNhanKhauAdmin.setItems(hoKhauModelObservableList);
    }


    public void setButtonNewPeopleAdmin(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("ThemMoiNhanKhauCanBoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setButtonTemporaryRegistrationPeopleAdmin(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("TamVangCanBo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setButtonAbsentRegistrationAdmin(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("TamTruCanBo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setButtonRemovePeopleAdmin(){
        NhanKhauModel nhanKhauModel = tableViewNhanKhauAdmin.getSelectionModel().getSelectedItem();
        if(nhanKhauModel.getMaNhanKhau().isBlank()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn nhân khẩu muốn khai tử");
            alert.showAndWait();
        }else {
            Alert  alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xóa người này!");
            if(alert.showAndWait().get()==ButtonType.OK){
                nhanKhauService.deleteNhanKhau(nhanKhauModel);
                displayAllNhanKhauCanBo();
            }
        }
    }

    public void setButtonLocThongTinHoKhauCanBo() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemHoKhauCanBo.getValue());
        String cauhoi = textFieldLocThongTinNhanKhauCanBo.getText();
        ObservableList<NhanKhauModel> nhanKhauModelObservableList = FXCollections.observableArrayList();
        //{"Mã hộ khẩu","Họ tên","Biệt danh","Tình trạng","Mã nhân khẩu"};
        if(dieuKienKiemTra.equals("Mã hộ khẩu")){
            nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauTrongHoKhau(cauhoi);
        }else if(dieuKienKiemTra.equals("Họ tên")){
            nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByTen(cauhoi);
        }else if(dieuKienKiemTra.equals("Biệt danh")){
            nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByBietDanh(cauhoi);
        }else if(dieuKienKiemTra.equals("Tình trạng")){
            nhanKhauModelObservableList = nhanKhauService.getAllNhanKhauByTinhTrang(cauhoi);
        }else{
            Optional<NhanKhauModel> nhanKhauModel = nhanKhauService.getNhanKhauByMaNhanKhau(cauhoi);
            if(nhanKhauModel.isPresent()){
                nhanKhauModelObservableList.add(nhanKhauModel.get());
            }
        }
        tableViewNhanKhauAdmin.setItems(nhanKhauModelObservableList);
    }
}
