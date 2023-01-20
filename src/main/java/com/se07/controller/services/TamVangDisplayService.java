package com.se07.controller.services;
import com.se07.model.models.TamVangDisplayModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class TamVangDisplayService {

    // tra ve toàn bộ ket qua hien thi cua tạm vắng
    public ObservableList<TamVangDisplayModel> getDisplayTamVang() {
        ObservableList<TamVangDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select nhan_khau.hoTen, tam_vang.noiTamVang, , tuNgay, denNgay, lydo, tam_vang.tinhTrang, tam_vang.idNguoiThucHien" +
                "from tam_vang, nhan_khau where tam_vang.maNhanKhau= nhan_khau.maNhanKhau";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel temp = new TamVangDisplayModel(
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // trả về kết quả hiển thị tạm vắng theo họ tên
    public ObservableList<TamVangDisplayModel> getDisplayTamVangByHoTen(String hoTen) {
        ObservableList<TamVangDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select nhan_khau.hoTen, tam_vang.noiTamVang, , tuNgay, denNgay, lydo, tam_vang.tinhTrang, tam_vang.idNguoiThucHien" +
                "from tam_vang, nhan_khau where tam_vang.maNhanKhau= nhan_khau.maNhanKhau and hoTen LIKE N'%"+ hoTen + "%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel temp = new TamVangDisplayModel(
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // trả về kết quả hiển thị tạm vắng theo nơi tạm vắng
    public ObservableList<TamVangDisplayModel> getDisplayTamVangByNoiTamVang(String noiTamVang) {
        ObservableList<TamVangDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select nhan_khau.hoTen, tam_vang.noiTamVang, , tuNgay, denNgay, lydo, tam_vang.tinhTrang, tam_vang.idNguoiThucHien" +
                "from tam_vang, nhan_khau where tam_vang.maNhanKhau= nhan_khau.maNhanKhau and" +
                " noiTamVang LIKE N'%"+ noiTamVang + "%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel temp = new TamVangDisplayModel(
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // trả về kết quả hiển thị tạm vắng với từ ngày nằm giữa low và high
    public ObservableList<TamVangDisplayModel> getDisplayTamVangWhereTuNgayBetween(Date low, Date high) {
        ObservableList<TamVangDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select nhan_khau.hoTen, tam_vang.noiTamVang, , tuNgay, denNgay, lydo, tam_vang.tinhTrang, tam_vang.idNguoiThucHien" +
                "from tam_vang, nhan_khau where tam_vang.maNhanKhau= nhan_khau.maNhanKhau and tuNgay between ? and ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(low.getTime()));
            statement.setDate(2, new java.sql.Date(high.getTime()));
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel temp = new TamVangDisplayModel(
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;


    }

    // trả về kết quả hiển thị tạm vắng với đến ngày nằm giữa low và high
    public ObservableList<TamVangDisplayModel> getDisplayTamVangWhereDenNgayBetween(Date low, Date high) {
        ObservableList<TamVangDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select nhan_khau.hoTen, tam_vang.noiTamVang, , tuNgay, denNgay, lydo, tam_vang.tinhTrang, tam_vang.idNguoiThucHien" +
                "from tam_vang, nhan_khau where tam_vang.maNhanKhau= nhan_khau.maNhanKhau and denNgay between ? and ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(low.getTime()));
            statement.setDate(2, new java.sql.Date(high.getTime()));
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel temp = new TamVangDisplayModel(
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;


    }
    // trả về kết quả hiển thị tạm vắng theo tình trạng
    public ObservableList<TamVangDisplayModel> getDisplayTamVangByTinhTrang(String tinhTrang) {
        ObservableList<TamVangDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select nhan_khau.hoTen, tam_vang.noiTamVang, , tuNgay, denNgay, lydo, tam_vang.tinhTrang, tam_vang.idNguoiThucHien" +
                "from tam_vang, nhan_khau where tam_vang.maNhanKhau= nhan_khau.maNhanKhau and" +
                " tinhTrang LIKE N'"+ tinhTrang + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel temp = new TamVangDisplayModel(
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // trả về kết quả hiển thị tạm vắng theo id người thực hiện
    public ObservableList<TamVangDisplayModel> getDisplayTamVangByIDNguoiThucHien(int id) {
        ObservableList<TamVangDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select nhan_khau.hoTen, tam_vang.noiTamVang, , tuNgay, denNgay, lydo, tam_vang.tinhTrang, tam_vang.idNguoiThucHien" +
                "from tam_vang, nhan_khau where tam_vang.maNhanKhau= nhan_khau.maNhanKhau and" +
                " idNguoiThucHien ='"+ id + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel temp = new TamVangDisplayModel(
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}