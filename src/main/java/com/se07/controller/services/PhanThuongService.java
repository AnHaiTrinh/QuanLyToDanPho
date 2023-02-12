package com.se07.controller.services;

import com.se07.model.models.PhanThuongModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class PhanThuongService {
    public Optional<PhanThuongModel> getPhanThuongByTen(String tenPhanThuong) {
        Optional<PhanThuongModel> phanThuongModel = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from phan_thuong where tenPhanThuong = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNString(1, tenPhanThuong);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                PhanThuongModel temp = new PhanThuongModel(
                        rs.getInt("maPhanThuong"),
                        rs.getNString("tenPhanThuong"),
                        rs.getInt("giaTri"));
                phanThuongModel = Optional.of(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phanThuongModel;
    }

    public boolean addPhanThuong(PhanThuongModel phanThuongModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into phan_thuong(tenPhanThuong, giaTri) values(?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNString(1, phanThuongModel.getTenPhanThuong());
            statement.setDouble(2, phanThuongModel.getGiaTri());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<String> getAllTenPhanThuong() {
        ObservableList<String> listTenPhanThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select tenPhanThuong from phan_thuong";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                listTenPhanThuong.add(rs.getNString("tenPhanThuong"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTenPhanThuong;
    }
}
