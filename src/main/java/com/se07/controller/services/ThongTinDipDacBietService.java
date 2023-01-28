package com.se07.controller.services;

import com.se07.model.models.ThongTinDipDacBietModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class ThongTinDipDacBietService {
    public boolean addThongTinDipDacBiet(ThongTinDipDacBietModel thongTinDipDacBietModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into thong_tin_dip_dac_biet(idDip, maNhanKhau, tinhTrang, idNguoiThucHien) " +
                "values(?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, thongTinDipDacBietModel.getIdDip());
            statement.setString(2, thongTinDipDacBietModel.getMaNhanKhau());
            statement.setNString(3, thongTinDipDacBietModel.getTinhTrang());
            statement.setInt(4, thongTinDipDacBietModel.getIdNguoiThucHien());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
//    public ObservableList<ThongTinDipDacBietModel> getAllThongTinDipDacBiet() {
//        ObservableList<ThongTinDipDacBietModel> list = FXCollections.observableArrayList();
//        Connection connection = ConnectionDatabase.getConnection();
//        String query = "select * from thong_tin_dip_dac_biet";
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                ThongTinDipDacBietModel temp = new ThongTinDipDacBietModel(rs.getInt("idNhap"),
//                        rs.getString("maNhanKhau"), rs.getNString("dipDacBiet"),
//                        rs.getInt("nam"), rs.getNString("tinhTrang"),
//                        rs.getInt("idNguoiThucHien"));
//                list.add(temp);
//            }
//            statement.close();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public ObservableList<ThongTinDipDacBietModel> getThongTinDipDacBietByDip(String dip) {
//        ObservableList<ThongTinDipDacBietModel> list = FXCollections.observableArrayList();
//        Connection connection = ConnectionDatabase.getConnection();
//        String query = "select * from thong_tin_dip_dac_biet where dipDacBiet like N%'" + dip + "%'";
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                ThongTinDipDacBietModel temp = new ThongTinDipDacBietModel(rs.getInt("idNhap"),
//                        rs.getString("maNhanKhau"), rs.getNString("dipDacBiet"),
//                        rs.getInt("nam"), rs.getNString("tinhTrang"),
//                        rs.getInt("idNguoiThucHien"));
//                list.add(temp);
//            }
//            statement.close();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public ObservableList<ThongTinDipDacBietModel> getThongTinDipDacBietByNam(int namLow, int namHigh) {
//        ObservableList<ThongTinDipDacBietModel> list = FXCollections.observableArrayList();
//        Connection connection = ConnectionDatabase.getConnection();
//        String query = "select * from thong_tin_dip_dac_biet where nam between " + namLow + " and " + namHigh;
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                ThongTinDipDacBietModel temp = new ThongTinDipDacBietModel(rs.getInt("idNhap"),
//                        rs.getString("maNhanKhau"), rs.getNString("dipDacBiet"),
//                        rs.getInt("nam"), rs.getNString("tinhTrang"),
//                        rs.getInt("idNguoiThucHien"));
//                list.add(temp);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public ObservableList<ThongTinDipDacBietModel> getThongTinDipDacBietByMaNhanKhau(String maNhanKhau) {
//        ObservableList<ThongTinDipDacBietModel> list = FXCollections.observableArrayList();
//        Connection connection = ConnectionDatabase.getConnection();
//        String query = "select * from thong_tin_dip_dac_biet where maNhanKhau = '" + maNhanKhau + "'";
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                ThongTinDipDacBietModel temp = new ThongTinDipDacBietModel(rs.getInt("idNhap"),
//                        rs.getString("maNhanKhau"), rs.getNString("dipDacBiet"),
//                        rs.getInt("nam"), rs.getNString("tinhTrang"),
//                        rs.getInt("idNguoiThucHien"));
//                list.add(temp);
//            }
//            statement.close();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public ObservableList<ThongTinDipDacBietModel> getThongTinDipDacBietByTinhTrang(String tinhTrang) {
//        ObservableList<ThongTinDipDacBietModel> list = FXCollections.observableArrayList();
//        Connection connection = ConnectionDatabase.getConnection();
//        String query = "select * from thong_tin_dip_dac_biet where tinhTrang =  N'" + tinhTrang + "%'";
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                ThongTinDipDacBietModel temp = new ThongTinDipDacBietModel(rs.getInt("idNhap"),
//                        rs.getString("maNhanKhau"), rs.getNString("dipDacBiet"),
//                        rs.getInt("nam"), rs.getNString("tinhTrang"),
//                        rs.getInt("idNguoiThucHien"));
//                list.add(temp);
//            }
//            statement.close();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public ObservableList<ThongTinDipDacBietModel> getThongTinDipDacBietByMaHoKhau(String maHoKhau) {
//        ObservableList<ThongTinDipDacBietModel> list = FXCollections.observableArrayList();
//        Connection connection = ConnectionDatabase.getConnection();
//        String query = "select thong_tin_dip_dac_biet.* from thong_tin_dip_dac_biet " +
//                "inner join nhan_khau using maNhanKhau " +
//                "where maHoKhau = " + maHoKhau;
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                ThongTinDipDacBietModel temp = new ThongTinDipDacBietModel(rs.getInt("idNhap"),
//                        rs.getString("maNhanKhau"), rs.getNString("dipDacBiet"),
//                        rs.getInt("nam"), rs.getNString("tinhTrang"),
//                        rs.getInt("idNguoiThucHien"));
//                list.add(temp);
//            }
//            statement.close();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public Optional<ThongTinDipDacBietModel> getThongTinDipDacBietById(int id) {
//        Optional<ThongTinDipDacBietModel> thongTinDipDacBietModel = Optional.empty();
//        Connection connection = ConnectionDatabase.getConnection();
//        String query = "select * from thong_tin_dip_dac_biet where idNhap = " + id;
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            if (rs.next()) {
//                ThongTinDipDacBietModel temp = new ThongTinDipDacBietModel(rs.getInt("idNhap"),
//                        rs.getString("maNhanKhau"), rs.getNString("dipDacBiet"),
//                        rs.getInt("nam"), rs.getNString("tinhTrang"),
//                        rs.getInt("idNguoiThucHien"));
//                thongTinDipDacBietModel = Optional.of(temp);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return thongTinDipDacBietModel;
//    }
//
//    public boolean addThongTinDipDacBiet(ThongTinDipDacBietModel thongTinDipDacBietModel) {
//        Connection connection = ConnectionDatabase.getConnection();
//        String query = "insert into thong_tin_dip_dac_biet (maNhanKhau, dipDacBiet, nam, tinhTrang, idNguoiThucHien) " +
//                "values(?, ?, ?, ?, ?)";
//        try {
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, thongTinDipDacBietModel.getMaNhanKhau());
//            statement.setNString(2, thongTinDipDacBietModel.getDipdacBiet());
//            statement.setInt(3, thongTinDipDacBietModel.getNam());
//            statement.setNString(4, thongTinDipDacBietModel.getTinhTrang());
//            statement.setInt(5, thongTinDipDacBietModel.getIdNguoiThucHien());
//            statement.executeUpdate();
//            statement.close();
//            connection.close();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean updateThongTinDipDacBiet(ThongTinDipDacBietModel thongTinDipDacBietModel) {
//        Connection connection = ConnectionDatabase.getConnection();
//        String query = "update thong_tin_dip_dac_biet set maNhanKhau = ?, dipDacBiet = ?, nam = ?, tinhTrang = ?, " +
//                "idNguoiThucHien = ? where idNhap = ? ";
//        try {
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, thongTinDipDacBietModel.getMaNhanKhau());
//            statement.setNString(2, thongTinDipDacBietModel.getDipdacBiet());
//            statement.setInt(3, thongTinDipDacBietModel.getNam());
//            statement.setNString(4, thongTinDipDacBietModel.getTinhTrang());
//            statement.setInt(5, thongTinDipDacBietModel.getIdNguoiThucHien());
//            statement.setInt(6, thongTinDipDacBietModel.getIdNhap());
//            statement.executeUpdate();
//            statement.close();
//            connection.close();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean deleteThongTinDipDacBiet(ThongTinDipDacBietModel thongTinDipDacBietModel) {
//        Connection connection = ConnectionDatabase.getConnection();
//        String query = "delete from thong_tin_dip_dac_biet where idNhap = ? ";
//        try {
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setInt(1, thongTinDipDacBietModel.getIdNhap());
//            statement.executeUpdate();
//            statement.close();
//            connection.close();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
