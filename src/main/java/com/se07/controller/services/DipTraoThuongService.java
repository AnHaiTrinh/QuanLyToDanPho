package com.se07.controller.services;

import com.se07.model.models.DipTraoThuongModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class DipTraoThuongService {
    public ObservableList<DipTraoThuongModel> getAllDipTraoThuong() {
        ObservableList<DipTraoThuongModel> listDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from dip_trao_thuong";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                DipTraoThuongModel temp = new DipTraoThuongModel(rs.getInt("id"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getDate("ngayTao"),
                        rs.getDate("ngayKetThuc"),
                        rs.getNString("kieu"),
                        rs.getNString("ghiChu"));
                listDipTraoThuong.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDipTraoThuong;
    }

    public ObservableList<DipTraoThuongModel> getDipTraoThuongByTen(String tenDip) {
        ObservableList<DipTraoThuongModel> listDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from dip_trao_thuong where tenDip like N'%" + tenDip + "%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                DipTraoThuongModel temp = new DipTraoThuongModel(rs.getInt("id"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getDate("ngayTao"),
                        rs.getDate("ngayKetThuc"),
                        rs.getNString("kieu"),
                        rs.getNString("ghiChu"));
                listDipTraoThuong.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDipTraoThuong;
    }

    public ObservableList<DipTraoThuongModel> getAllDipTraoThuongByKieu(String kieu) {
        ObservableList<DipTraoThuongModel> listDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from dip_trao_thuong where kieu = N'" + kieu + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                DipTraoThuongModel temp = new DipTraoThuongModel(rs.getInt("id"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getDate("ngayTao"),
                        rs.getDate("ngayKetThuc"),
                        rs.getNString("kieu"),
                        rs.getNString("ghiChu"));
                listDipTraoThuong.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDipTraoThuong;
    }

    public ObservableList<DipTraoThuongModel> getAllDipTraoThuongByNam(int nam) {
        ObservableList<DipTraoThuongModel> listDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from dip_trao_thuong where nam = " + nam;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                DipTraoThuongModel temp = new DipTraoThuongModel(rs.getInt("id"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getDate("ngayTao"),
                        rs.getDate("ngayKetThuc"),
                        rs.getNString("kieu"),
                        rs.getNString("ghiChu"));
                listDipTraoThuong.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDipTraoThuong;
    }

    public ObservableList<DipTraoThuongModel> getAllDipTraoThuongNgayTaoBetween(Date tu, Date den) {
        ObservableList<DipTraoThuongModel> listDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from dip_trao_thuong where ngayTao between ? and ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(tu.getTime()));
            statement.setDate(2, new java.sql.Date(den.getTime()));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                DipTraoThuongModel temp = new DipTraoThuongModel(rs.getInt("id"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getDate("ngayTao"),
                        rs.getDate("ngayKetThuc"),
                        rs.getNString("kieu"),
                        rs.getNString("ghiChu"));
                listDipTraoThuong.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDipTraoThuong;
    }

    public ObservableList<DipTraoThuongModel> getAllDipTraoThuongNgayKetThucBetween(Date tu, Date den) {
        ObservableList<DipTraoThuongModel> listDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from dip_trao_thuong where ngayKetThuc between ? and ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(tu.getTime()));
            statement.setDate(2, new java.sql.Date(den.getTime()));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                DipTraoThuongModel temp = new DipTraoThuongModel(rs.getInt("id"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getDate("ngayTao"),
                        rs.getDate("ngayKetThuc"),
                        rs.getNString("kieu"),
                        rs.getNString("ghiChu"));
                listDipTraoThuong.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDipTraoThuong;
    }

    public boolean updateDipTraoThuong(DipTraoThuongModel dipTraoThuongModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update dip_trao_thuong " +
                "set tenDip = ?, " +
                "nam = ?, " +
                "ngayTao = ?, " +
                "ngayKetThuc = ?, " +
                "kieu = ?, " +
                "ghiChu = ? " +
                "where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNString(1, dipTraoThuongModel.getTenDip());
            statement.setInt(2, dipTraoThuongModel.getNam());
            statement.setDate(3, new java.sql.Date(dipTraoThuongModel.getNgayTao().getTime()));
            statement.setDate(4, new java.sql.Date(dipTraoThuongModel.getNgayKetThuc().getTime()));
            statement.setNString(5, dipTraoThuongModel.getKieu());
            statement.setNString(6, dipTraoThuongModel.getGhiChu());
            statement.setInt(7, dipTraoThuongModel.getId());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDipTraoThuong(DipTraoThuongModel dipTraoThuongModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from dip_trao_thuong where id = " + dipTraoThuongModel.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addDipTraoThuong(DipTraoThuongModel dipTraoThuongModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into dip_trao_thuong(tenDip, nam, ngayTao, ngayKetThuc, kieu, ghiChu) " +
                "values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNString(1, dipTraoThuongModel.getTenDip());
            statement.setInt(2, dipTraoThuongModel.getNam());
            statement.setDate(3, new java.sql.Date(dipTraoThuongModel.getNgayTao().getTime()));
            statement.setDate(4, new java.sql.Date(dipTraoThuongModel.getNgayKetThuc().getTime()));
            statement.setNString(5, dipTraoThuongModel.getKieu());
            statement.setNString(6, dipTraoThuongModel.getGhiChu());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
