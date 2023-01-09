package com.se07.controller.services;
import com.se07.model.models.TamTruModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;
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
    public ObservableList<TamTruModel> getTamTruByCCCD(String CCCD) {
        ObservableList<TamTruModel> listTamTru = FXCollections.observableArrayList();
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

    public ObservableList<TamTruModel> getTamTruByHoTen(String hoTen) {
        ObservableList<TamTruModel> listTamTru = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where hoTen = N'" + hoTen + "'";
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

    public ObservableList<TamTruModel> getTamTruByNoiTamTru(String noiTamTru) {
        ObservableList<TamTruModel> listTamTru = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where noiTamTru = N'" + noiTamTru + "'";
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

    public ObservableList<TamTruModel> getTamTruStartWithTen(String ten) {
        ObservableList<TamTruModel> listTamTru = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where hoTen LIKE N'" + ten + "%'";
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

    public ObservableList<TamTruModel> getTamTruWhereTuNgayBetween(java.util.Date low, Date high) {
        ObservableList<TamTruModel> listTamTru = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where tuNgay between ? and ?";
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

    public ObservableList<TamTruModel> getTamTruWhereDenNgayBetween(java.util.Date low, Date high) {
        ObservableList<TamTruModel> listTamTru = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where denNgay between ? and ?";
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

    public ObservableList<TamTruModel> getTamTruByTinhTrang(String tinhTrang) {
        ObservableList<TamTruModel> listTamTru = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where tinhTrang = N'" + tinhTrang + "'";
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

    public ObservableList<TamTruModel> getTamTruByidNguoiThucHien(int idNguoiThucHien) {
        ObservableList<TamTruModel> listTamTru = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru where idNguoiThucHien = '" + idNguoiThucHien + "'";
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
        String query = "insert into tam_tru(maTamTru, CCCD, hoTen, noiTamTru, tuNgay, denNgay, lydo, " +
                "tinhTrang, idNguoiThucHien) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tamTruModel.getMaTamTru());
            statement.setString(2, tamTruModel.getCCCD());
            statement.setNString(3, tamTruModel.getHoTen());
            statement.setNString(4, tamTruModel.getNoiTamTru());
            statement.setDate(5, new java.sql.Date(tamTruModel.getTuNgay().getTime()));
            statement.setDate(6, new java.sql.Date(tamTruModel.getDenNgay().getTime()));
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

    public boolean updateTamTru(TamTruModel tamTruModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update tam_tru set " +
                "CCCD = ?, " +
                "hoTen = ?, " +
                "noiTamTru = ?," +
                " tuNgay = ?, " +
                "denNgay = ?, lydo = ?," +
                "tinhTrang = ?, " +
                "idNguoiThucHien = ? where maTamTru = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tamTruModel.getCCCD());
            statement.setNString(2, tamTruModel.getHoTen());
            statement.setNString(3, tamTruModel.getNoiTamTru());
            statement.setDate(4, new java.sql.Date(tamTruModel.getTuNgay().getTime()));
            statement.setDate(5, new java.sql.Date(tamTruModel.getDenNgay().getTime()));
            statement.setNString(6, tamTruModel.getLyDo());
            statement.setNString(7, tamTruModel.getTinhTrang());
            statement.setInt(8, tamTruModel.getIdNguoiThucHien());
            statement.setString(9, tamTruModel.getMaTamTru());
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

    public int getTamTruCount() {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select count(*) from tam_tru";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }







}