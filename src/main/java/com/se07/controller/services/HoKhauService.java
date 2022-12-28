package com.se07.controller.services;

import com.se07.model.models.HoKhauModel;
import com.se07.model.models.UserModel;
import com.se07.util.ConnectionDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HoKhauService {
    public List<HoKhauModel> getAllHoKhau() {
        List<HoKhauModel> listHoKhau = new ArrayList<>();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from ho_khau";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                HoKhauModel temp = new HoKhauModel(rs.getString("maHoKhau"), rs.getString("maChuHo"),
                        rs.getString("diachi"), rs.getDate("ngayLap"),
                        rs.getInt("nguoiThucHien"));
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
                HoKhauModel temp = new HoKhauModel(rs.getString("maHoKhau"), rs.getString("maChuHo"),
                        rs.getString("diachi"), rs.getDate("ngayLap"),
                        rs.getInt("nguoiThucHien"));
                hoKhauModel = Optional.of(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoKhauModel;

    }

    public boolean insertNewUser(UserModel userModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into user(ID, username, password, role) values (" + "";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                HoKhauModel temp = new HoKhauModel(rs.getString("maHoKhau"), rs.getString("maChuHo"),
                        rs.getString("diachi"), rs.getDate("ngayLap"),
                        rs.getInt("nguoiThucHien"));
                hoKhauModel = Optional.of(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean insertNewHoKhau(HoKhauModel hoKhauModel) {

    }

    public boolean updateHoKhau(HoKhauModel hoKhauModel) {

    }

    public boolean deleteHoKhau(HoKhauModel ho) {

    }

    public boolean tachHoKhau() {

    }
}
