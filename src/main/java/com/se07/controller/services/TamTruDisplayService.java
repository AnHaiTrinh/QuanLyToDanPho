package com.se07.controller.services;
import com.se07.model.models.TamTruDisplayModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class TamTruDisplayService {

    // tra ve cac ket qua hien thi cua tam tru
    public ObservableList<TamTruDisplayModel> getDisplayTamTru(){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang, tam_tru.idNguoiThucHien" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau";
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    // trả về kết quả hiện thị của tạm trú theo CCCD (Căn cước công dân)
    public ObservableList<TamTruDisplayModel> getDisplayTamTruByCCCD(String CCCD){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang, tam_tru.idNguoiThucHien" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau and CCCD LIKE '%"+ CCCD + "%'";
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

        // trả về kết quả hiển thị của tạm trú theo họ tên
    public ObservableList<TamTruDisplayModel> getDisplayTamTruByHoTen(String hoTen){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang, tam_tru.idNguoiThucHien" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau and hoTen LIKE N'%"+ hoTen + "%'";
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<TamTruDisplayModel> getDisplayTamTruByNoiTamTru(String noiTamTru){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang, tam_tru.idNguoiThucHien" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau and ho_khau.diaChi LIKE N'%"+ noiTamTru + "%'";
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    // trả về kết quả hiển thị của tạm trú mà tuNgay ở giữa 2 giá trị low và high
    public ObservableList<TamTruDisplayModel> getDisplayTamTruWhereTuNgayBetween(java.util.Date low, Date high){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang, tam_tru.idNguoiThucHien" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau and tuNgay between ? and ? ";
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
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


    // trả về kết quả hiển thị của tạm trú mà denNgay ở giữa 2 giá trị low và high
    public ObservableList<TamTruDisplayModel> getDisplayTamTruWhereDenNgayBetween(java.util.Date low, Date high){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang, tam_tru.idNguoiThucHien" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau and denNgay between ? and ? ";
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
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    //trả về kết quả hiển thị tạm trú theo tình trạng
    public ObservableList<TamTruDisplayModel> getDisplayTamTruByTinhTrang(String tinhTrang){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang, tam_tru.idNguoiThucHien" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau and tinhTrang = N'"+ tinhTrang + "'";
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //trả về kết quả hiển thị tạm trú theo id người thực hiện
    public ObservableList<TamTruDisplayModel> getDisplayTamTruByIDNguoiThucHien(int id){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang, tam_tru.idNguoiThucHien" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau and tam_tru.idNguoiThucHien = '"+ id + "'";
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}



