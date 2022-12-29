package com.se07.controller.services;

import com.se07.model.models.HoKhauModel;
import com.se07.model.models.NhanKhauModel;
import com.se07.util.ConnectionDatabase;

import java.sql.*;
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

    public boolean insertNewHoKhau(HoKhauModel hoKhauModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into ho_khau(maHoKhau, maChuHo, diaChi, ngayLap, nguoiThucHien) " +
                " values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, hoKhauModel.getMaHoKhau());
            statement.setString(2, hoKhauModel.getMaChuHo());
            statement.setString(3, hoKhauModel.getDiaChi());
            statement.setDate(4, new Date(hoKhauModel.getNgayLap().getTime()));
            statement.setInt(5, hoKhauModel.getNguoiThucHien());
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
        String query = "update ho_khau set maChuHo = ?, diaChi = ?, ngayLap = ?, nguoiThucHien = ?" +
                " where maHoKhau = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, hoKhauModel.getMaChuHo());
            statement.setString(2, hoKhauModel.getDiaChi());
            statement.setDate(3, new Date(hoKhauModel.getNgayLap().getTime()));
            statement.setInt(4, hoKhauModel.getNguoiThucHien());
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
