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

public class NhanKhauService {
    public ObservableList<NhanKhauModel> getAllNhanKhau() {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }

    public ObservableList<NhanKhauModel> getAllNhanKhauTrongHoKhau(String maHoKhau) {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where maHoKhau = '" + maHoKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }

    public ObservableList<NhanKhauModel> getAllNhanKhauByTen(String tenNhanKhau) {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where hoTen like N'%" + tenNhanKhau + "%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }


    public ObservableList<NhanKhauModel> getAllNhanKhauByBietDanh(String bietDanh) {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where bietDanh like N'%" + bietDanh + "%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }


    public ObservableList<NhanKhauModel> getAllNhanKhauByTinhTrang(String tinhTrang) {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where tinhTrang = N'" + tinhTrang + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }

    public Optional<NhanKhauModel> getNhanKhauByMaNhanKhau(String maNhanKhau) {
        Optional<NhanKhauModel> nhanKhauModel = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where maNhanKhau = '" + maNhanKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                nhanKhauModel = Optional.of(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanKhauModel;
    }

    public boolean addNhanKhau(NhanKhauModel nhanKhauModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into nhan_khau(maNhanKhau, maHoKhau, hoTen, bietdanh, ngaySinh, gioiTinh, tonGiao, " +
                "tinhTrang, idNguoiThucHien) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nhanKhauModel.getMaNhanKhau());
            statement.setString(2, nhanKhauModel.getMaHoKhau());
            statement.setNString(3, nhanKhauModel.getHoTen());
            statement.setNString(4, nhanKhauModel.getBietDanh());
            statement.setDate(5, new Date(nhanKhauModel.getNgaySinh().getTime()));
            statement.setNString(6, nhanKhauModel.getGioiTinh());
            statement.setNString(7, nhanKhauModel.getTonGiao());
            statement.setNString(8, nhanKhauModel.getTinhTrang());
            statement.setInt(9, nhanKhauModel.getIdNguoiThucHien());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateNhanKhau(NhanKhauModel nhanKhauModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update nhan_khau set maHoKhau = ?, hoTen = ?, bietdanh = ?, ngaySinh = ?, gioiTinh = ?, " +
                "tonGiao = ?, tinhTrang = ?, idNguoiThucHien = ? where maNhanKhau = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nhanKhauModel.getMaHoKhau());
            statement.setNString(2, nhanKhauModel.getHoTen());
            statement.setNString(3, nhanKhauModel.getBietDanh());
            statement.setDate(4, new Date(nhanKhauModel.getNgaySinh().getTime()));
            statement.setNString(5, nhanKhauModel.getGioiTinh());
            statement.setNString(6, nhanKhauModel.getTonGiao());
            statement.setNString(7, nhanKhauModel.getTinhTrang());
            statement.setInt(8, nhanKhauModel.getIdNguoiThucHien());
            statement.setString(9, nhanKhauModel.getMaNhanKhau());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNhanKhau(NhanKhauModel nhanKhauModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from nhan_khau where maNhanKhau = '" + nhanKhauModel.getMaNhanKhau() + "'";
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

    public int getNhanKhauCount() {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select count(*) from nhan_khau where tinhTrang = N'Đã xác nhận'";
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
    public List<String> getAllMaNhanKhau(){
        List<String> listMaNhanKhau = new ArrayList<>();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select maNhanKhau from nhan_khau";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                listMaNhanKhau.add(rs.getString(1));
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMaNhanKhau;
    }


}