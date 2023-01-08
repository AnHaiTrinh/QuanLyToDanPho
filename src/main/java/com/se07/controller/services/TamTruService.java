package com.se07.controller.services;
import com.se07.model.models.HoKhauModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Optional;

public class TamTruService {
    public ObservableList<TamTruModel> getAllTamTru() {
        ObservableList<TamTruModel> listTamTru = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruModel temp = new TamTruModel(
                    rs.getString("maTamTru"), 
                    rs.getString("CCCD"),
                    rs.getNString("hoTen"),
                    rs.getNString("noiTamTru"),
                    rs.getDate("tuNgay"),
                    rs.getDate("denNgay"),
                    rs.getString("lydo"), 
                    rs.getNString("tinhTrang"),
                    rs.getInt("idNguoiThucHien"));
                listTamTru.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamTru;
    }
    public Optional<TamTruModel> getTamTruByCCCD(String CCCD) {
        Optional<TamTruModel> listTamTru = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where CCCD = '" + CCCD + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruModel temp = new TamTruModel(
                    rs.getString("maTamTru"), 
                    rs.getString("CCCD"),
                    rs.getNString("hoTen"),
                    rs.getNString("noiTamTru"),
                    rs.getDate("tuNgay"),
                    rs.getDate("denNgay"),
                    rs.getString("lydo"), 
                    rs.getNString("tinhTrang"),
                    rs.getInt("idNguoiThucHien"));
                listTamTru.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamTru;
    }

    public Optional<TamTruModel> getTamTruByHoTen(String hoTen) {
        Optional<TamTruModel> listTamTru = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where hoTen =  N'" + hoTen + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruModel temp = new TamTruModel(
                    rs.getString("maTamTru"), 
                    rs.getString("CCCD"),
                    rs.getNString("hoTen"),
                    rs.getNString("noiTamTru"),
                    rs.getDate("tuNgay"),
                    rs.getDate("denNgay"),
                    rs.getString("lydo"), 
                    rs.getNString("tinhTrang"),
                    rs.getInt("idNguoiThucHien"));
                listTamTru.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamTru;
    }

    public Optional<TamTruModel> getTamTruStartWithTen(String hoTen) {
        Optional<TamTruModel> listTamTru = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where hoTen LIKE N'" + hoTen + "%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruModel temp = new TamTruModel(
                    rs.getString("maTamTru"), 
                    rs.getString("CCCD"),
                    rs.getNString("hoTen"),
                    rs.getNString("noiTamTru"),
                    rs.getDate("tuNgay"),
                    rs.getDate("denNgay"),
                    rs.getString("lydo"), 
                    rs.getNString("tinhTrang"),
                    rs.getInt("idNguoiThucHien"));
                listTamTru.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamTru;
    }

    public Optional<TamTruModel> getTamTruByNoiTamTru(String noiTamTru) {
        Optional<TamTruModel> listTamTru = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where noiTamTru =  N'" + noiTamTru + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruModel temp = new TamTruModel(
                    rs.getString("maTamTru"), 
                    rs.getString("CCCD"),
                    rs.getNString("hoTen"),
                    rs.getNString("noiTamTru"),
                    rs.getDate("tuNgay"),
                    rs.getDate("denNgay"),
                    rs.getString("lydo"), 
                    rs.getNString("tinhTrang"),
                    rs.getInt("idNguoiThucHien"));
                listTamTru.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamTru;
    }

    public Optional<TamTruModel> getTamTruByTuNgay(Date tuNgay) {
        Optional<TamTruModel> listTamTru = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where tuNgay =  ?";
        java.sql.Date tuNgaySQL = new java.sql.Date(tuNgay.getTime());
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, tuNgaySQL);
            statement.executeUpdate();
            }
            catch (Exception e) {
                e.printStackTrace();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruModel temp = new TamTruModel(
                    rs.getString("maTamTru"), 
                    rs.getString("CCCD"),
                    rs.getNString("hoTen"),
                    rs.getNString("noiTamTru"),
                    rs.getDate("tuNgay"),
                    rs.getDate("denNgay"),
                    rs.getString("lydo"), 
                    rs.getNString("tinhTrang"),
                    rs.getInt("idNguoiThucHien"));
                listTamTru.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamTru;
    }

    public Optional<TamTruModel> getTamTruByDenNgay(Date denNgay) {
        Optional<TamTruModel> listTamTru = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where denNgay =  ?";
        java.sql.Date denNgaySQL = new java.sql.Date(denNgay.getTime());
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, denNgaySQL);
            statement.executeUpdate();
            }
            catch (Exception e) {
                e.printStackTrace();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruModel temp = new TamTruModel(
                    rs.getString("maTamTru"), 
                    rs.getString("CCCD"),
                    rs.getNString("hoTen"),
                    rs.getNString("noiTamTru"),
                    rs.getDate("tuNgay"),
                    rs.getDate("denNgay"),
                    rs.getString("lydo"), 
                    rs.getNString("tinhTrang"),
                    rs.getInt("idNguoiThucHien"));
                listTamTru.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamTru;
    }

    public Optional<TamTruModel> getTamTruByTinhTrang(String tinhTrang) {
        Optional<TamTruModel> listTamTru = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where tinhTrang =  '" + tinhTrang + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruModel temp = new TamTruModel(
                    rs.getString("maTamTru"), 
                    rs.getString("CCCD"),
                    rs.getNString("hoTen"),
                    rs.getNString("noiTamTru"),
                    rs.getDate("tuNgay"),
                    rs.getDate("denNgay"),
                    rs.getString("lydo"),                
                    rs.getNString("tinhTrang"),
                    rs.getInt("idNguoiThucHien"));
                listTamTru.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamTru;
    }

    public Optional<TamTruModel> getTamTruByIDNguoiThucHien(int id) {
        Optional<TamTruModel> listTamTru = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where idNguoiThucHien =  '" + id + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruModel temp = new TamTruModel(
                    rs.getString("maTamTru"), 
                    rs.getString("CCCD"),
                    rs.getNString("hoTen"),
                    rs.getNString("noiTamTru"),
                    rs.getDate("tuNgay"),
                    rs.getDate("denNgay"),
                    rs.getString("lydo"),                
                    rs.getNString("tinhTrang"),
                    rs.getInt("idNguoiThucHien"));
                listTamTru.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamTru;
    }

    public Optional<TamTruModel> getTamTruByMaTamTru(String maTamTru) {
        Optional<TamTruModel> tamTruModel = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where maTamTru = '" + maTamTru + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                TamTruModel temp = new TamTruModel(
                    rs.getString("maTamTru"), 
                    rs.getString("CCCD"),
                    rs.getNString("hoTen"),
                    rs.getNString("noiTamTru"),
                    rs.getDate("tuNgay"),
                    rs.getDate("denNgay"),
                    rs.getString("lydo"),                
                    rs.getNString("tinhTrang"),
                    rs.getInt("idNguoiThucHien"));
                tamTruModel = Optional.of(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tamTruModel;
    }

    public boolean addTamTru(TamTruModel tamTruModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into tam_tru(maTamTru, CCCD, hoTen, noiTamTru, tuNgay, denNgay, lydo " +
                "tinhTrang, idNguoiThucHien) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tamTruModel.getMaTamTru());
            statement.setNString(2, tamTruModel.getCCCD());
            statement.setNString(3, tamTruModel.getHoTen());
            statement.setNString(4, tamTruModel.getNoiTamTru());
            statement.setDate(5, new Date(tamTruModel.getTuNgay().getTime()));
            statement.setDate(6, tamTruModel.getDenNgay().getTime());
            statement.setNString(7, tamTruModel.getLyDo());
            statement.setNString(8, tamTruModel.getTinhTrang());
            statement.setInt(9, tamTruModel.getIdNguoiThucHien());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateNhanKhau(TamTruModel tamTruModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update tam_tru set maTamTru = ?, CCCD = ?, hoTen = ?, noiTamTru = ?, tuNgay = ?,denNgay = ?, lydo = ?  " + "tinhTrang = ?, idNguoiThucHien = ? where maNhanKhau = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tamTruModel.getMaTamTru());
            statement.setNString(2, tamTruModel.getCCCD());
            statement.setNString(3, tamTruModel.getHoTen());
            statement.setNString(4, tamTruModel.getNoiTamTru());
            statement.setDate(5, new Date(tamTruModel.getTuNgay().getTime()));
            statement.setDate(6, tamTruModel.getDenNgay().getTime());
            statement.setNString(7, tamTruModel.getLyDo());
            statement.setNString(8, tamTruModel.getTinhTrang());
            statement.setInt(9, tamTruModel.getIdNguoiThucHien());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTamTru(TamTruModel tamTruModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from tam_tru where maTamTru = '" + tamTruModel.getMaTamTru() + "'";
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
