package com.se07.controller.controllers.controllershogiadinh;

import com.se07.controller.controllers.MinhChungController;
import com.se07.controller.services.DipTraoThuongService;
import com.se07.controller.services.NhanKhauService;
import com.se07.controller.services.ThongTinThanhTichService;
import com.se07.model.models.DipTraoThuongModel;
import com.se07.model.models.ThongTinThanhTichDisplayModel;
import com.se07.model.models.ThongTinThanhTichModel;
import com.se07.util.ComponentVisibility;
import com.se07.util.MyIntegerStringConverter;
import com.se07.view.UserView;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerGiaiThuongThanhTichHoGiaDinhView extends ControllerHoGiaDinhView {
    @FXML
    TableView<ThongTinThanhTichDisplayModel> tableViewGiaiThuongThanhTichHoGiaDinh;
    @FXML
    TableColumn<ThongTinThanhTichDisplayModel, String> tableColumnMaNhanKhauThanhTichHoGiaDinh, tableColumnHoTenThanhTichHoGiaDinh,
            tableColumnTenDipThanhTichHoGiaDinh, tableColumnTruongThanhTichHoGiaDinh, tableColumnCapThanhTichHoGiaDinh,
            tableColumnKieuThanhTichHoGiaDinh, tableColumnMinhChungThanhTichHoGiaDinh, tableColumnTinhTrangThanhTichHoGiaDinh;
    @FXML
    TableColumn<ThongTinThanhTichDisplayModel, Integer> tableColumnNamThanhTichHoGiaDinh, tableColumnLopThanhTichHoGiaDinh;
    @FXML
    TextField textFieldLocThongTinThanhTichHoGiaDinh;
    @FXML
    ComboBox comboBoxTimKiemThanhTichHoGiaDinh, comboBoxMaNhanKhauThanhTichHoGiaDinh, comboBoxTenNamThanhTichHoGiaDinh,
            comboBoxCapThanhTichHoGiaDinh, comboBoxKieuThanhTichHoGiaDinh, comboBoxTinhTrangThanhTichHoGiaDinh;

    final private ThongTinThanhTichService thongTinThanhTichService = new ThongTinThanhTichService();
    final private DipTraoThuongService dipTraoThuongService = new DipTraoThuongService();
    final private MyIntegerStringConverter integerStringConverter = new MyIntegerStringConverter();

    final ObservableList<String> listMaNhanKhau = new NhanKhauService().getAllMaNhanKhauTrongHoKhau(maHoKhauDangNhap);

    final ObservableList<String> listTenNamDipTraoThuong = dipTraoThuongService.getAllTenNamDipTraoThuongThanhTich();

    final ObservableList<String> listTimKiem = FXCollections.observableArrayList("Họ tên", "Mã nhân khẩu",
            "Tên dịp", "Năm", "Tên - Năm", "Cấp thành tích", "Kiểu thành tích", "Tình trạng");
    final ObservableList<String> listCapThanhTich = FXCollections.observableArrayList(
            "Trường", "Quận/Huyện", "Tỉnh/Thành phố", "Quốc gia", "Quốc tế");

    final ObservableList<String> listKieuThanhTich = FXCollections.observableArrayList(
            "Học sinh khá", "Học sinh giỏi", "Học sinh xuất sắc",
            "Giải nhất", "Giải nhì", "Giải ba", "Giải khuyến khích",
            "Huy chương Vàng", "Huy chương Bạc", "Huy chương Đồng");

    final ObservableList<String> listTinhTrang =
            FXCollections.observableArrayList("Chờ xác nhận", "Đã xác nhận", "Đã từ chối", "Chờ xóa");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        tableColumnMaNhanKhauThanhTichHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        tableColumnHoTenThanhTichHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        tableColumnTenDipThanhTichHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("tenDip"));
        tableColumnNamThanhTichHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("nam"));
        tableColumnLopThanhTichHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("lop"));
        tableColumnTruongThanhTichHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("truong"));
        tableColumnCapThanhTichHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("capThanhTich"));
        tableColumnKieuThanhTichHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("kieuThanhTich"));
        tableColumnMinhChungThanhTichHoGiaDinh.setCellValueFactory(t -> new ReadOnlyStringWrapper("Minh chứng"));
        tableColumnTinhTrangThanhTichHoGiaDinh.setCellValueFactory(new PropertyValueFactory<>("tinhTrang"));

        comboBoxTimKiemThanhTichHoGiaDinh.getItems().addAll(listTimKiem);
        comboBoxTimKiemThanhTichHoGiaDinh.getSelectionModel().selectFirst();
        comboBoxMaNhanKhauThanhTichHoGiaDinh.getItems().addAll(listMaNhanKhau);
        comboBoxMaNhanKhauThanhTichHoGiaDinh.getSelectionModel().selectFirst();
        comboBoxTenNamThanhTichHoGiaDinh.getItems().addAll(listTenNamDipTraoThuong);
        comboBoxTenNamThanhTichHoGiaDinh.getSelectionModel().selectFirst();
        comboBoxCapThanhTichHoGiaDinh.getItems().addAll(listCapThanhTich);
        comboBoxCapThanhTichHoGiaDinh.getSelectionModel().selectFirst();
        comboBoxKieuThanhTichHoGiaDinh.getItems().addAll(listKieuThanhTich);
        comboBoxKieuThanhTichHoGiaDinh.getSelectionModel().selectFirst();
        comboBoxTinhTrangThanhTichHoGiaDinh.getItems().addAll(listTinhTrang);
        comboBoxTinhTrangThanhTichHoGiaDinh.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxMaNhanKhauThanhTichHoGiaDinh, false);
        ComponentVisibility.change(comboBoxTenNamThanhTichHoGiaDinh, false);
        ComponentVisibility.change(comboBoxCapThanhTichHoGiaDinh, false);
        ComponentVisibility.change(comboBoxKieuThanhTichHoGiaDinh, false);
        ComponentVisibility.change(comboBoxTinhTrangThanhTichHoGiaDinh, false);

        tableViewGiaiThuongThanhTichHoGiaDinh.setEditable(true);
        tableColumnMaNhanKhauThanhTichHoGiaDinh.setCellFactory(t -> new ComboBoxTableCell<>(listMaNhanKhau));
        tableColumnLopThanhTichHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn(integerStringConverter));
        tableColumnTruongThanhTichHoGiaDinh.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnCapThanhTichHoGiaDinh.setCellFactory(t -> new ComboBoxTableCell<>(listCapThanhTich));
        tableColumnKieuThanhTichHoGiaDinh.setCellFactory(t -> new ComboBoxTableCell<>(listKieuThanhTich));

        displayAllThongTinThanhTichHoGiaDinh();
    }

    public void onSelectionComboBoxTimKiemThanhTichHoGiaDinh(ActionEvent e) {
        ComponentVisibility.change(textFieldLocThongTinThanhTichHoGiaDinh, false);
        ComponentVisibility.change(comboBoxMaNhanKhauThanhTichHoGiaDinh, false);
        ComponentVisibility.change(comboBoxTenNamThanhTichHoGiaDinh, false);
        ComponentVisibility.change(comboBoxCapThanhTichHoGiaDinh, false);
        ComponentVisibility.change(comboBoxKieuThanhTichHoGiaDinh, false);
        ComponentVisibility.change(comboBoxTinhTrangThanhTichHoGiaDinh, false);
        String truongTimKiem = String.valueOf(comboBoxTimKiemThanhTichHoGiaDinh.getValue());
        switch (truongTimKiem) {
            case "Mã nhân khẩu":
                ComponentVisibility.change(comboBoxMaNhanKhauThanhTichHoGiaDinh, true);
                break;
            case "Tên - Năm":
                ComponentVisibility.change(comboBoxTenNamThanhTichHoGiaDinh, true);
                break;
            case "Cấp thành tích":
                ComponentVisibility.change(comboBoxCapThanhTichHoGiaDinh, true);
                break;
            case "Kiểu thành tích":
                ComponentVisibility.change(comboBoxKieuThanhTichHoGiaDinh, true);
                break;
            case "Tình trạng":
                ComponentVisibility.change(comboBoxTinhTrangThanhTichHoGiaDinh, true);
                break;
            default:
                ComponentVisibility.change(textFieldLocThongTinThanhTichHoGiaDinh, true);
                break;
        }
    }

    public void onPressedButtonThemMoiThongTinThanhTichHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "ThemMoiThongTinThanhTichHoGiaDinh.fxml");
        }
    }

    public void onPressedButtonLocThongTinThanhTichHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinThanhTichHoGiaDinh();
        }
    }

    public void onEnterPressedTrongOTimKiemThanhTichHoGiaDinh(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            locThongTinThanhTichHoGiaDinh();
        }
    }

    private void displayAllThongTinThanhTichHoGiaDinh() {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTich =
                thongTinThanhTichService.getAllThongTinThanhTichAndHoKhau(maHoKhauDangNhap);
        tableViewGiaiThuongThanhTichHoGiaDinh.setItems(listThongTinThanhTich);
    }

    public void onPressedButtonXoaThongTinThanhTichHoGiaDinh(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaThongTinThanhTichHoGiaDinh();
        }
    }

    public void onDeletePressedTrongBangThongTinThanhTichHoGiaDinh(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.DELETE)) {
            xoaThongTinThanhTichHoGiaDinh();
        }
    }

    private void xoaThongTinThanhTichHoGiaDinh() {
        ThongTinThanhTichDisplayModel thongTinThanhTichDisplayModel =
                tableViewGiaiThuongThanhTichHoGiaDinh.getSelectionModel().getSelectedItem();
        if (thongTinThanhTichDisplayModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn xóa");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xóa trường hợp này!");
            if (alert.showAndWait().get() == ButtonType.OK) {
                thongTinThanhTichDisplayModel.setTinhTrang("Chờ xóa");
                updateThongTinThanhTichHoGiaDinh(thongTinThanhTichDisplayModel);
            }
        }
    }

    public void onPressedTrongCotMinhChungThanhTichHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            String id = ((Node) e.getTarget()).getId();
            if (id != null && id.equals("tableColumnMinhChungThanhTichHoGiaDinh")) {
                ThongTinThanhTichDisplayModel thongTinThanhTichDisplayModel =
                        tableViewGiaiThuongThanhTichHoGiaDinh.getSelectionModel().getSelectedItem();
                File minhChungCu = thongTinThanhTichDisplayModel.getMinhChung();
                FXMLLoader fxmlLoader = new FXMLLoader(UserView.class.getResource("MinhChung.fxml"));
                Parent root = fxmlLoader.load();
                MinhChungController controller = fxmlLoader.getController();
                Image image = new Image(minhChungCu.toURI().toString());
                controller.imageViewMinhChung.setPreserveRatio(true);
                controller.imageViewMinhChung.setFitHeight(controller.imageViewMinhChung.getFitHeight());
                controller.imageViewMinhChung.setFitWidth(controller.imageViewMinhChung.getFitWidth());
                controller.imageViewMinhChung.setImage(image);
                Stage stage = new Stage();
                stage.initOwner(((Node) e.getSource()).getScene().getWindow());
                stage.setTitle("Minh chứng");
                stage.setScene(new Scene(root));
                stage.setOnCloseRequest(event -> {
                    File minhChungMoi = controller.fileMinhChung;
                    if (minhChungMoi != null) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Thông báo");
                        alert.setContentText("Bạn muốn lưu thay đổi ?");
                        if (alert.showAndWait().get() == ButtonType.OK) {
                            thongTinThanhTichDisplayModel.setMinhChung(minhChungMoi);
                            Path source = minhChungMoi.toPath();
                            Path dest = minhChungCu.toPath();
                            try {
                                Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            updateThongTinThanhTichHoGiaDinh(thongTinThanhTichDisplayModel);
                        }
                    }
                });
                stage.show();
            }
        }
    }

    public void onPressedButtonThoatThongTinThanhTichHoGiaDinh(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Bạn chắc chắn muốn thoát?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                sceneLoader.loadFxmlFileHoGiaDinh((Stage) ((Node) e.getSource()).getScene().getWindow(),
                        "GiaiThuongHoGiaDinhView.fxml");
            }
        }
    }

    public void handleOnEditCommit(TableColumn.CellEditEvent<ThongTinThanhTichDisplayModel, ?> event) {
        ThongTinThanhTichDisplayModel thongTinThanhTichDisplayModel = event.getRowValue();
        DipTraoThuongModel dipTraoThuongModel = dipTraoThuongService.getDipTraoThuongByTenAndNam(
                thongTinThanhTichDisplayModel.getTenDip(),
                thongTinThanhTichDisplayModel.getNam()).get();
        Date today = new Date();
        if (today.after(dipTraoThuongModel.getNgayTao()) && today.equals(dipTraoThuongModel.getNgayKetThuc())) {
            int column = event.getTablePosition().getColumn();
            switch (column) {
                case 0:
                    thongTinThanhTichDisplayModel.setMaNhanKhau((String) event.getNewValue());
                    break;
                case 4:
                    thongTinThanhTichDisplayModel.setTruong((String) event.getNewValue());
                    break;
                case 5:
                    int lopMoi = (int) event.getNewValue();
                    if (lopMoi > 0 && lopMoi <= 12) {
                        thongTinThanhTichDisplayModel.setLop(lopMoi);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText("Vui lòng nhập lớp hợp lệ (từ 1 - 12)");
                        alert.showAndWait();
                        displayAllThongTinThanhTichHoGiaDinh();
                        return;
                    }
                    break;
                case 6:
                    thongTinThanhTichDisplayModel.setCapThanhTich((String) event.getNewValue());
                    break;
                case 7:
                    thongTinThanhTichDisplayModel.setKieuThanhTich((String) event.getNewValue());
                    break;
            }
            thongTinThanhTichDisplayModel.setTinhTrang(tinhTrang);
            updateThongTinThanhTichHoGiaDinh(thongTinThanhTichDisplayModel);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Dịp thành tích chưa bắt đầu hoặc đã kết thúc!");
            alert.showAndWait();
        }
    }

    public void handleOnEditCancel(TableColumn.CellEditEvent<ThongTinThanhTichDisplayModel, ?> event) {
        ThongTinThanhTichDisplayModel thongTinThanhTichDisplayModel = event.getRowValue();
        int column = event.getTablePosition().getColumn();
        switch (column) {
            case 0:
                thongTinThanhTichDisplayModel.setMaNhanKhau((String) event.getOldValue());
                break;
            case 4:
                thongTinThanhTichDisplayModel.setTruong((String) event.getOldValue());
                break;
            case 5:
                thongTinThanhTichDisplayModel.setLop((int) event.getOldValue());
                break;
            case 6:
                thongTinThanhTichDisplayModel.setCapThanhTich((String) event.getOldValue());
                break;
            case 7:
                thongTinThanhTichDisplayModel.setKieuThanhTich((String) event.getOldValue());
                break;
        }
    }

    private void updateThongTinThanhTichHoGiaDinh(ThongTinThanhTichDisplayModel thongTinThanhTichDisplayModel) {
        ThongTinThanhTichModel thongTinThanhTichModel =
                thongTinThanhTichService.convertDisplayModelToModel(thongTinThanhTichDisplayModel);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        if (thongTinThanhTichService.updateThongTinThanhTich(thongTinThanhTichModel)) {
            alert.setHeaderText("Gửi yêu cầu thành công");
        } else {
            alert.setHeaderText("Gửi yêu cầu không thành công");
        }
        if (alert.showAndWait().get() == ButtonType.OK) {
            displayAllThongTinThanhTichHoGiaDinh();
        }
    }

    public void locThongTinThanhTichHoGiaDinh() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemThanhTichHoGiaDinh.getValue());
        String cauHoi = textFieldLocThongTinThanhTichHoGiaDinh.getText();
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTich = FXCollections.observableArrayList();
        switch (dieuKienKiemTra) {
            case "Mã nhân khẩu":
                listThongTinThanhTich = thongTinThanhTichService.getAllThongTinThanhTichByMaNhanKhau(
                        String.valueOf(comboBoxMaNhanKhauThanhTichHoGiaDinh.getValue())
                );
                break;
            case "Họ tên":
                listThongTinThanhTich = thongTinThanhTichService.getAllThongTinThanhTichByHoTen(cauHoi);
                break;
            case "Tên dịp":
                listThongTinThanhTich = thongTinThanhTichService.getAllThongTinThanhTichByTenDip(cauHoi);
                break;
            case "Năm":
                int nam = integerStringConverter.fromString(textFieldLocThongTinThanhTichHoGiaDinh.getText());
                if (nam == -1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập năm hợp lệ");
                    alert.showAndWait();
                    displayAllThongTinThanhTichHoGiaDinh();
                    textFieldLocThongTinThanhTichHoGiaDinh.requestFocus();
                    return;
                }
                listThongTinThanhTich = thongTinThanhTichService.getAllThongTinThanhTichByNamAndHoKhau(nam, maHoKhauDangNhap);
                break;
            case "Tên - Năm":
                String tenNam = String.valueOf(comboBoxTenNamThanhTichHoGiaDinh.getValue());
                int index = tenNam.indexOf(" - ");
                String tenDip = tenNam.substring(0, index);
                int namDip = Integer.parseInt(tenNam.substring(index + 3));
                int id = dipTraoThuongService.getDipTraoThuongByTenAndNam(tenDip, namDip).get().getId();
                listThongTinThanhTich = thongTinThanhTichService.getThongTinThanhTichByIdDipAndMaHoKhau(id, maHoKhauDangNhap);
                break;
            case "Cấp thành tích":
                String capThanhTich = String.valueOf(comboBoxCapThanhTichHoGiaDinh.getValue());
                listThongTinThanhTich = thongTinThanhTichService.getAllThongTinThanhTichByCapThanhTichAndMaHoKhau
                        (capThanhTich, maHoKhauDangNhap);
                break;
            case "Kiểu thành tích":
                String kieuThanhTich = String.valueOf(comboBoxKieuThanhTichHoGiaDinh.getValue());
                listThongTinThanhTich = thongTinThanhTichService.getAllThongTinThanhTichByKieuThanhTichAndMaHoKhau(kieuThanhTich, maHoKhauDangNhap);
                break;
            case "Tình trạng":
                String tinhTrang = String.valueOf(comboBoxTinhTrangThanhTichHoGiaDinh.getValue());
                listThongTinThanhTich = thongTinThanhTichService.getAllThongTinThanhTichByTinhTrangAndHoKhau(tinhTrang, maHoKhauDangNhap);
                break;
        }
        tableViewGiaiThuongThanhTichHoGiaDinh.setItems(listThongTinThanhTich);
    }
}
