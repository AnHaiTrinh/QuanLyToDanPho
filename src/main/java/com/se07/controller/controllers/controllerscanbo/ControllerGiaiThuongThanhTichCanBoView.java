package com.se07.controller.controllers.controllerscanbo;

import com.se07.controller.controllers.MinhChungController;
import com.se07.controller.services.DipTraoThuongService;
import com.se07.controller.services.NhanKhauService;
import com.se07.controller.services.ThongTinThanhTichService;
import com.se07.model.models.DipTraoThuongModel;
import com.se07.model.models.ThongTinThanhTichDisplayModel;
import com.se07.model.models.ThongTinThanhTichModel;
import com.se07.util.ComponentVisibility;
import com.se07.util.MyIntegerStringConverter;
import com.se07.view.TrangChuCanBoView;
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

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerGiaiThuongThanhTichCanBoView extends ControllerCanBoView {
    @FXML
    TableView<ThongTinThanhTichDisplayModel> tableViewGiaiThuongThanhTichCanBo;
    @FXML
    TableColumn<ThongTinThanhTichDisplayModel, String> tableColumnMaNhanKhauThanhTichCanBo, tableColumnHoTenThanhTichCanBo,
            tableColumnTenDipThanhTichCanBo, tableColumnTruongThanhTichCanBo, tableColumnCapThanhTichCanBo,
            tableColumnKieuThanhTichCanBo, tableColumnMinhChungThanhTichCanBo, tableColumnTinhTrangThanhTichCanBo;
    @FXML
    TableColumn<ThongTinThanhTichDisplayModel, Integer> tableColumnNamThanhTichCanBo, tableColumnLopThanhTichCanBo;
    @FXML
    TextField textFieldLocThongTinThanhTichCanBo;
    @FXML
    ComboBox comboBoxTimKiemThanhTichCanBo, comboBoxMaNhanKhauThanhTichCanBo, comboBoxTenNamThanhTichCanBo,
            comboBoxCapThanhTichCanBo, comboBoxKieuThanhTichCanBo, comboBoxTinhTrangThanhTichCanBo;

    final private ThongTinThanhTichService thongTinThanhTichService = new ThongTinThanhTichService();
    final private DipTraoThuongService dipTraoThuongService = new DipTraoThuongService();
    final private MyIntegerStringConverter integerStringConverter = new MyIntegerStringConverter();

    final ObservableList<String> listMaNhanKhau = new NhanKhauService().getAllMaNhanKhau();

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
        tableColumnMaNhanKhauThanhTichCanBo.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        tableColumnHoTenThanhTichCanBo.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        tableColumnTenDipThanhTichCanBo.setCellValueFactory(new PropertyValueFactory<>("tenDip"));
        tableColumnNamThanhTichCanBo.setCellValueFactory(new PropertyValueFactory<>("nam"));
        tableColumnLopThanhTichCanBo.setCellValueFactory(new PropertyValueFactory<>("lop"));
        tableColumnTruongThanhTichCanBo.setCellValueFactory(new PropertyValueFactory<>("truong"));
        tableColumnCapThanhTichCanBo.setCellValueFactory(new PropertyValueFactory<>("capThanhTich"));
        tableColumnKieuThanhTichCanBo.setCellValueFactory(new PropertyValueFactory<>("kieuThanhTich"));
        tableColumnMinhChungThanhTichCanBo.setCellValueFactory(t -> new ReadOnlyStringWrapper("Minh chứng"));
        tableColumnTinhTrangThanhTichCanBo.setCellValueFactory(new PropertyValueFactory<>("tinhTrang"));

        comboBoxTimKiemThanhTichCanBo.getItems().addAll(listTimKiem);
        comboBoxTimKiemThanhTichCanBo.getSelectionModel().selectFirst();
        comboBoxMaNhanKhauThanhTichCanBo.getItems().addAll(listMaNhanKhau);
        comboBoxMaNhanKhauThanhTichCanBo.getSelectionModel().selectFirst();
        comboBoxTenNamThanhTichCanBo.getItems().addAll(listTenNamDipTraoThuong);
        comboBoxTenNamThanhTichCanBo.getSelectionModel().selectFirst();
        comboBoxCapThanhTichCanBo.getItems().addAll(listCapThanhTich);
        comboBoxCapThanhTichCanBo.getSelectionModel().selectFirst();
        comboBoxKieuThanhTichCanBo.getItems().addAll(listKieuThanhTich);
        comboBoxKieuThanhTichCanBo.getSelectionModel().selectFirst();
        comboBoxTinhTrangThanhTichCanBo.getItems().addAll(listTinhTrang);
        comboBoxTinhTrangThanhTichCanBo.getSelectionModel().selectFirst();
        ComponentVisibility.change(comboBoxMaNhanKhauThanhTichCanBo, false);
        ComponentVisibility.change(comboBoxTenNamThanhTichCanBo, false);
        ComponentVisibility.change(comboBoxCapThanhTichCanBo, false);
        ComponentVisibility.change(comboBoxKieuThanhTichCanBo, false);
        ComponentVisibility.change(comboBoxTinhTrangThanhTichCanBo, false);

        tableViewGiaiThuongThanhTichCanBo.setEditable(true);
        tableColumnMaNhanKhauThanhTichCanBo.setCellFactory(t -> new ComboBoxTableCell<>(listMaNhanKhau));
        tableColumnLopThanhTichCanBo.setCellFactory(TextFieldTableCell.forTableColumn(integerStringConverter));
        tableColumnTruongThanhTichCanBo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnCapThanhTichCanBo.setCellFactory(t -> new ComboBoxTableCell<>(listCapThanhTich));
        tableColumnKieuThanhTichCanBo.setCellFactory(t -> new ComboBoxTableCell<>(listKieuThanhTich));
        tableColumnTinhTrangThanhTichCanBo.setCellFactory(t -> new ComboBoxTableCell<>(listTinhTrang));

        displayAllThongTinThanhTichCanBo();
    }

    public void onSelectionComboBoxTimKiemThanhTichCanBo(ActionEvent e) {
        ComponentVisibility.change(textFieldLocThongTinThanhTichCanBo, false);
        ComponentVisibility.change(comboBoxMaNhanKhauThanhTichCanBo, false);
        ComponentVisibility.change(comboBoxTenNamThanhTichCanBo, false);
        ComponentVisibility.change(comboBoxCapThanhTichCanBo, false);
        ComponentVisibility.change(comboBoxKieuThanhTichCanBo, false);
        ComponentVisibility.change(comboBoxTinhTrangThanhTichCanBo, false);
        String truongTimKiem = String.valueOf(comboBoxTimKiemThanhTichCanBo.getValue());
        switch (truongTimKiem) {
            case "Mã nhân khẩu":
                ComponentVisibility.change(comboBoxMaNhanKhauThanhTichCanBo, true);
                break;
            case "Tên - Năm":
                ComponentVisibility.change(comboBoxTenNamThanhTichCanBo, true);
                break;
            case "Cấp thành tích":
                ComponentVisibility.change(comboBoxCapThanhTichCanBo, true);
                break;
            case "Kiểu thành tích":
                ComponentVisibility.change(comboBoxKieuThanhTichCanBo, true);
                break;
            case "Tình trạng":
                ComponentVisibility.change(comboBoxTinhTrangThanhTichCanBo, true);
                break;
            default:
                ComponentVisibility.change(textFieldLocThongTinThanhTichCanBo, true);
                break;
        }
    }

    public void onPressedButtonThemMoiThongTinThanhTichCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                    "ThemMoiThongTinThanhTichCanBo.fxml");
        }
    }

    public void onPressedButtonLocThongTinThanhTichCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            locThongTinThanhTichCanBo();
        }
    }

    public void onEnterPressedTrongOTimKiemThanhTichCanBo(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            locThongTinThanhTichCanBo();
        }
    }

    public void onPressedButtonPheDuyetThongTinThanhTichCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xacNhanThongTinThanhTichCanBo();
        }
    }

    private void xacNhanThongTinThanhTichCanBo() {
        ThongTinThanhTichDisplayModel thongTinThanhTichDisplayModel =
                tableViewGiaiThuongThanhTichCanBo.getSelectionModel().getSelectedItem();
        if (thongTinThanhTichDisplayModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn xác nhận");
            alert.showAndWait();
        } else if (thongTinThanhTichDisplayModel.getTinhTrang().equals("Đã xác nhận")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trường hợp đã được xác nhận");
            alert.showAndWait();
        } else if (thongTinThanhTichDisplayModel.getTinhTrang().equals("Chờ xóa")) {
            xoaThongTinThanhTichCanBo();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn xác nhận trường hợp này?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                thongTinThanhTichDisplayModel.setTinhTrang("Đã xác nhận");
                updateThongTinThanhTichCanBo(thongTinThanhTichDisplayModel);
            }
        }
    }

    private void displayAllThongTinThanhTichCanBo() {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTich =
                thongTinThanhTichService.getAllThongTinThanhTich();
        tableViewGiaiThuongThanhTichCanBo.setItems(listThongTinThanhTich);
    }

    public void onPressedButtonTuChoiThongTinThanhTichCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            tuChoiThongTinThanhTichCanBo();
        }
    }

    private void tuChoiThongTinThanhTichCanBo() {
        ThongTinThanhTichDisplayModel thongTinThanhTichDisplayModel =
                tableViewGiaiThuongThanhTichCanBo.getSelectionModel().getSelectedItem();
        if (thongTinThanhTichDisplayModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn trường hợp muốn xác nhận");
            alert.showAndWait();
        } else if (thongTinThanhTichDisplayModel.getTinhTrang().equals("Đã từ chối")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Trường hợp đã bị từ chối");
            alert.showAndWait();
        } else if (thongTinThanhTichDisplayModel.getTinhTrang().equals("Chờ xóa")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn khôi phục trường hợp này?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                thongTinThanhTichDisplayModel.setTinhTrang("Đã xác nhận");
                updateThongTinThanhTichCanBo(thongTinThanhTichDisplayModel);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn chắc chắn muốn từ chối trường hợp này?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                thongTinThanhTichDisplayModel.setTinhTrang("Đã từ chối");
                updateThongTinThanhTichCanBo(thongTinThanhTichDisplayModel);
            }
        }
    }

    public void onPressedButtonXoaThongTinThanhTichCanBo(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            xoaThongTinThanhTichCanBo();
        }
    }

    public void onDeletePressedTrongBangThongTinThanhTichCanBo(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.DELETE)) {
            xoaThongTinThanhTichCanBo();
        }
    }

    private void xoaThongTinThanhTichCanBo() {
        ThongTinThanhTichDisplayModel thongTinThanhTichDisplayModel =
                tableViewGiaiThuongThanhTichCanBo.getSelectionModel().getSelectedItem();
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
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Thông báo");
                if (thongTinThanhTichService.deleteThongTinThanhTich(thongTinThanhTichDisplayModel)) {
                    info.setHeaderText("Xóa thành công!");
                } else {
                    info.setHeaderText("Xóa không thành công!");
                }
                info.showAndWait();
                displayAllThongTinThanhTichCanBo();
            }
        }
    }

    public void onPressedTrongCotMinhChungThanhTichCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            String id = ((Node) e.getTarget()).getId();
            if (id != null && id.equals("tableColumnMinhChungThanhTichCanBo")) {
                ThongTinThanhTichDisplayModel thongTinThanhTichDisplayModel =
                        tableViewGiaiThuongThanhTichCanBo.getSelectionModel().getSelectedItem();
                File minhChungCu = thongTinThanhTichDisplayModel.getMinhChung();
                FXMLLoader fxmlLoader = new FXMLLoader(TrangChuCanBoView.class.getResource("MinhChung.fxml"));
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
                            updateThongTinThanhTichCanBo(thongTinThanhTichDisplayModel);
                        }
                    }
                });
                stage.show();
            }
        }
    }

    public void onPressedButtonThoatThongTinThanhTichCanBo(MouseEvent e) throws IOException {
        if (e.isPrimaryButtonDown()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Bạn chắc chắn muốn thoát?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                sceneLoader.loadFxmlFileCanBo((Stage) ((Node) e.getSource()).getScene().getWindow(),
                        "GiaiThuongCanBoView.fxml");
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
                        displayAllThongTinThanhTichCanBo();
                        return;
                    }
                    break;
                case 6:
                    thongTinThanhTichDisplayModel.setCapThanhTich((String) event.getNewValue());
                    break;
                case 7:
                    thongTinThanhTichDisplayModel.setKieuThanhTich((String) event.getNewValue());
                    break;
                case 9:
                    thongTinThanhTichDisplayModel.setTinhTrang((String) event.getNewValue());
                    break;
            }
            updateThongTinThanhTichCanBo(thongTinThanhTichDisplayModel);
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
            case 9:
                thongTinThanhTichDisplayModel.setTinhTrang((String) event.getOldValue());
                break;
        }
    }

    private void updateThongTinThanhTichCanBo(ThongTinThanhTichDisplayModel thongTinThanhTichDisplayModel) {
        ThongTinThanhTichModel thongTinThanhTichModel =
                thongTinThanhTichService.convertDisplayModelToModel(thongTinThanhTichDisplayModel);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        if (thongTinThanhTichService.updateThongTinThanhTich(thongTinThanhTichModel)) {
            alert.setHeaderText("Sửa thông tin thành công");
        } else {
            alert.setHeaderText("Sửa thông tin không thành công");
        }
        if (alert.showAndWait().get() == ButtonType.OK) {
            displayAllThongTinThanhTichCanBo();
        }
    }

    public void locThongTinThanhTichCanBo() {
        String dieuKienKiemTra = String.valueOf(comboBoxTimKiemThanhTichCanBo.getValue());
        String cauHoi = textFieldLocThongTinThanhTichCanBo.getText();
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTich = FXCollections.observableArrayList();
        switch (dieuKienKiemTra) {
            case "Mã nhân khẩu":
                listThongTinThanhTich = thongTinThanhTichService.getAllThongTinThanhTichByMaNhanKhau(
                        String.valueOf(comboBoxMaNhanKhauThanhTichCanBo.getValue())
                );
                break;
            case "Họ tên":
                listThongTinThanhTich = thongTinThanhTichService.getAllThongTinThanhTichByHoTen(cauHoi);
                break;
            case "Tên dịp":
                listThongTinThanhTich = thongTinThanhTichService.getAllThongTinThanhTichByTenDip(cauHoi);
                break;
            case "Năm":
                int nam = integerStringConverter.fromString(textFieldLocThongTinThanhTichCanBo.getText());
                if (nam == -1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vui lòng nhập năm hợp lệ");
                    alert.showAndWait();
                    displayAllThongTinThanhTichCanBo();
                    textFieldLocThongTinThanhTichCanBo.requestFocus();
                    return;
                }
                listThongTinThanhTich = thongTinThanhTichService.getAllThongTinThanhTichByNam(nam);
                break;
            case "Tên - Năm":
                String tenNam = String.valueOf(comboBoxTenNamThanhTichCanBo.getValue());
                int index = tenNam.indexOf(" - ");
                String tenDip = tenNam.substring(0, index);
                int namDip = Integer.parseInt(tenNam.substring(index + 3));
                int id = dipTraoThuongService.getDipTraoThuongByTenAndNam(tenDip, namDip).get().getId();
                listThongTinThanhTich = thongTinThanhTichService.getThongTinThanhTichByIdDip(id);
                break;
            case "Cấp thành tích":
                String capThanhTich = String.valueOf(comboBoxCapThanhTichCanBo.getValue());
                listThongTinThanhTich = thongTinThanhTichService.getAllThongTinThanhTichByCapThanhTich(capThanhTich);
                break;
            case "Kiểu thành tích":
                String kieuThanhTich = String.valueOf(comboBoxKieuThanhTichCanBo.getValue());
                listThongTinThanhTich = thongTinThanhTichService.getAllThongTinThanhTichByKieuThanhTich(kieuThanhTich);
                break;
            case "Tình trạng":
                String tinhTrang = String.valueOf(comboBoxTinhTrangThanhTichCanBo.getValue());
                listThongTinThanhTich = thongTinThanhTichService.getAllThongTinThanhTichByTinhTrang(tinhTrang);
                break;
        }
        tableViewGiaiThuongThanhTichCanBo.setItems(listThongTinThanhTich);
    }
}
