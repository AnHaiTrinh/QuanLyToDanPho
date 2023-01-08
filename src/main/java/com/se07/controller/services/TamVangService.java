package com.se07.controller.services;
import com.se07.model.models.TamVangModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;
import java.util.Optional;

public class TamVangService {
    public ObservableList<TamVangModel> getAllTamVang() {
        ObservableList<TamVangModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_vang";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangModel temp = new TamVangModel(
                        rs.getString("maTamVang"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));
                listTamVang.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamVang;
    }

    public ObservableList<TamVangModel> getTamVangByMaNhanKhau(String maNhanKhau) {
        ObservableList<TamVangModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_vang where maNhanKhau = '" + maNhanKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangModel temp = new TamVangModel(
                        rs.getString("maTamVang"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));
                listTamVang.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamVang;
    }

    public ObservableList<TamVangModel> getTamVangByNoiTamVang(String noiTamVang) {
        ObservableList<TamVangModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_vang where maNhanKhau = N'" + noiTamVang + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangModel temp = new TamVangModel(
                        rs.getString("maTamVang"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));
                listTamVang.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamVang;
    }

    public ObservableList<TamVangModel> getTamVangBytinhTrang(String tinhTrang) {
        ObservableList<TamVangModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_vang where tinhTrang = '" + tinhTrang + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangModel temp = new TamVangModel(
                        rs.getString("maTamVang"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));
                listTamVang.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamVang;
    }

    public ObservableList<TamVangModel> getTamVangWhereTuNgayBetween(java.util.Date low, Date high) {
        ObservableList<TamVangModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_vang where tuNgay between ? and ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(low.getTime()));
            statement.setDate(2, new java.sql.Date(high.getTime()));
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();}
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangModel temp = new TamVangModel(
                        rs.getString("maTamVang"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));
                listTamVang.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTamVang;
    }

    public ObservableList<TamVangModel> getTamVangWhereDenNgayBetween(java.util.Date low, Date high) {
        ObservableList<TamVangModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_vang where denNgay between ? and ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(low.getTime()));
            statement.setDate(2, new java.sql.Date(high.getTime()));
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();}
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangModel temp = new TamVangModel(
                        rs.getString("maTamVang"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));
                listTamVang.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTamVang;
    }

    public ObservableList<TamVangModel> getTamVangByidNguoiThucHien(int id) {
        ObservableList<TamVangModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_vang where tinhTrang = '" + id + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangModel temp = new TamVangModel(
                        rs.getString("maTamVang"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));
                listTamVang.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamVang;
    }

    public Optional<TamVangModel> getTamVangByMaTamVang(String maTamVang) {
        Optional<TamVangModel> tamVangModel = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_vang where maTamVang = '" + maTamVang + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                TamVangModel temp = new TamVangModel(
                        rs.getString("maTamVang"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));
                tamVangModel = Optional.of(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tamVangModel;
    }
    public boolean addTamVang(TamVangModel tamVangModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into tam_vang(maTamVang, maNhanKhau, noiTamVang, tuNgay, denNgay, lydo, " +
                "tinhTrang, idNguoiThucHien) values ( ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tamVangModel.getMaTamVang());
            statement.setString(2, tamVangModel.getMaNhanKhau());
            statement.setNString(3, tamVangModel.getNoiTamVang());
            statement.setDate(4, new java.sql.Date(tamVangModel.getTuNgay().getTime()));
            statement.setDate(5, new java.sql.Date(tamVangModel.getDenNgay().getTime()));
            statement.setNString(6, tamVangModel.getLyDo());
            statement.setNString(7, tamVangModel.getTinhTrang());
            statement.setInt(8, tamVangModel.getIdNguoiThucHien());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTamVang(TamVangModel tamVangModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update tam_vang set maTamVang = ?, " +
                "maNhanKhau = ?, " +
                "noiTamVang = ?," +
                " tuNgay = ?, " +
                "denNgay = ?, " +
                "tinhTrang = ?, " +
                "idNguoiThucHien = ? where maTamVang = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tamVangModel.getMaTamVang());
            statement.setString(2, tamVangModel.getMaNhanKhau());
            statement.setNString(3, tamVangModel.getNoiTamVang());
            statement.setDate(4, new java.sql.Date(tamVangModel.getTuNgay().getTime()));
            statement.setDate(5, new java.sql.Date(tamVangModel.getDenNgay().getTime()));
            statement.setNString(6, tamVangModel.getLyDo());
            statement.setNString(7, tamVangModel.getTinhTrang());
            statement.setInt(8, tamVangModel.getIdNguoiThucHien());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTamVang(TamVangModel tamVangModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from tam_vang where maTamVang = '" + tamVangModel.getMaTamVang() + "'";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}