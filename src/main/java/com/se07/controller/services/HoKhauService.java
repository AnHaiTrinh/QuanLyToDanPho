package com.se07.controller.services;

import com.se07.model.models.HoKhauModel;
import com.se07.model.models.NhanKhauModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HoKhauService {
    public ObservableList<HoKhauModel> getAllHoKhau() {
        ObservableList<HoKhauModel> listHoKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from ho_khau";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                HoKhauModel temp = new HoKhauModel(rs.getString("maHoKhau"), rs.getNString("chuHo"),
                        rs.getNString("diachi"), rs.getDate("ngayLap"),
                        rs.getInt("idNguoiThucHien"));
                listHoKhau.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHoKhau;
    }

    public Optional<HoKhauModel> getHoKhauByMaHoKhau(String maHoKhau) {
        Optional<HoKhauModel> hoKhauModel = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from ho_khau where maHoKhau = '" + maHoKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                HoKhauModel temp = new HoKhauModel(rs.getString("maHoKhau"), rs.getNString("chuHo"),
                        rs.getNString("diachi"), rs.getDate("ngayLap"),
                        rs.getInt("idNguoiThucHien"));
                hoKhauModel = Optional.of(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoKhauModel;

    }

    public boolean insertNewHoKhau(HoKhauModel hoKhauModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into ho_khau(maHoKhau, chuHo, diaChi, ngayLap, idNguoiThucHien) " +
                " values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, hoKhauModel.getMaHoKhau());
            statement.setString(2, hoKhauModel.getChuHo());
            statement.setString(3, hoKhauModel.getDiaChi());
            statement.setDate(4, new Date(hoKhauModel.getNgayLap().getTime()));
            statement.setInt(5, hoKhauModel.getIdNguoiThucHien());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateHoKhau(HoKhauModel hoKhauModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update ho_khau set chuHo = ?, diaChi = ?, ngayLap = ?, idNguoiThucHien = ?" +
                " where maHoKhau = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, hoKhauModel.getChuHo());
            statement.setString(2, hoKhauModel.getDiaChi());
            statement.setDate(3, new Date(hoKhauModel.getNgayLap().getTime()));
            statement.setInt(4, hoKhauModel.getIdNguoiThucHien());
            statement.setString(5, hoKhauModel.getMaHoKhau());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteHoKhau(HoKhauModel hoKhauModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from ho_khau " + "where maHoKhau = '" + hoKhauModel.getMaHoKhau() + "'";
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
