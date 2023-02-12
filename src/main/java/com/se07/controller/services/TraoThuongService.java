package com.se07.controller.services;

import com.se07.model.models.TraoThuongDisplayModel;
import com.se07.model.models.TraoThuongModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Statement;
import java.sql.*;

public class TraoThuongService {
    public int getSoNhanKhauTraoThuong() {
        int count = -1;
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select count(*) from " +
                "(select maNhanKhau from thong_tin_thanh_tich " +
                "where idNhap in (select idNhap from trao_thuong_thanh_tich) " +
                "union " +
                "select maNhanKhau from thong_tin_dip_dac_biet " +
                "where idNhap in (select idNhap from trao_thuong_dip_dac_biet)) as tmp";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) count = rs.getInt(1);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }


    public int getSoPhanThuongThanhTich() {
        int count = -1;
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select sum(soluong) from trao_thuong_thanh_tich";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) count = rs.getInt(1);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getSoPhanThuongDipDacBiet() {
        int count = -1;
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select sum(soluong) from trao_thuong_dip_dac_biet";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) count = rs.getInt(1);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getGiaTriTraoThuongThanhTich() {
        int count = -1;
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select sum(soluong * giaTri) " +
                "from trao_thuong_thanh_tich t join phan_thuong pt on t.maPhanThuong = pt.maPhanThuong";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) count = rs.getInt(1);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getGiaTriTraoThuongDipDacBiet() {
        int count = -1;
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select sum(soluong * giaTri) " +
                "from trao_thuong_dip_dac_biet t join phan_thuong pt on t.maPhanThuong = pt.maPhanThuong";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) count = rs.getInt(1);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public ObservableList<TraoThuongDisplayModel> getAllTraoThuongDisplayModel() {
        ObservableList<TraoThuongDisplayModel> traoThuongDisplayModelObservableList =
                FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "with n as (select maNhanKhau, hoTen from nhan_khau), " +
                "pt as (select maPhanThuong, tenPhanThuong, giaTri from phan_thuong)" +
                "select tt.idNhap, tt.maNhanKhau, n.hoTen, dtt.tenDip, dtt.nam, pt.tenPhanThuong, pt.giaTri, t.soLuong " +
                "from trao_thuong_dip_dac_biet t join thong_tin_dip_dac_biet tt on t.idNhap = tt.idNhap " +
                "join dip_trao_thuong dtt on tt.idDip = dtt.id " +
                "join n on tt.maNhanKhau = n.maNhanKhau " +
                "join pt on t.maPhanThuong = pt.maPhanThuong " +
                "union " +
                "select tt.idNhap, tt.maNhanKhau, n.hoTen, dtt.tenDip, dtt.nam, pt.tenPhanThuong, pt.giaTri, t.soLuong " +
                "from trao_thuong_thanh_tich t join thong_tin_thanh_tich tt on t.idNhap = tt.idNhap " +
                "join dip_trao_thuong dtt on tt.idDip = dtt.id " +
                "join n on tt.maNhanKhau = n.maNhanKhau " +
                "join pt on t.maPhanThuong = pt.maPhanThuong ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TraoThuongDisplayModel temp = new TraoThuongDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("hoTen"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getNString("tenPhanThuong"),
                        rs.getInt("giaTri"),
                        rs.getInt("soLuong")
                );
                traoThuongDisplayModelObservableList.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return traoThuongDisplayModelObservableList;
    }

    public ObservableList<TraoThuongDisplayModel> getAllTraoThuongDisplayModelByMaHoKhau(String maHoKhau) {
        ObservableList<TraoThuongDisplayModel> traoThuongDisplayModelObservableList =
                FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "with n as " +
                "(select maNhanKhau, hoTen " +
                "from nhan_khau join ho_khau hk on nhan_khau.maHoKhau = hk.maHoKhau " +
                "where hk.maHoKhau = '" + maHoKhau + "'), " +
                "pt as (select maPhanThuong, tenPhanThuong, giaTri from phan_thuong)" +
                "select tt.idNhap, tt.maNhanKhau, n.hoTen, dtt.tenDip, dtt.nam, pt.tenPhanThuong, pt.giaTri, t.soLuong " +
                "from trao_thuong_dip_dac_biet t join thong_tin_dip_dac_biet tt on t.idNhap = tt.idNhap " +
                "join dip_trao_thuong dtt on tt.idDip = dtt.id " +
                "join n on tt.maNhanKhau = n.maNhanKhau " +
                "join pt on t.maPhanThuong = pt.maPhanThuong " +
                "union " +
                "select tt.idNhap, tt.maNhanKhau, n.hoTen, dtt.tenDip, dtt.nam, pt.tenPhanThuong, pt.giaTri, t.soLuong " +
                "from trao_thuong_thanh_tich t join thong_tin_thanh_tich tt on t.idNhap = tt.idNhap " +
                "join dip_trao_thuong dtt on tt.idDip = dtt.id " +
                "join n on tt.maNhanKhau = n.maNhanKhau " +
                "join pt on t.maPhanThuong = pt.maPhanThuong ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TraoThuongDisplayModel temp = new TraoThuongDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("hoTen"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getNString("tenPhanThuong"),
                        rs.getInt("giaTri"),
                        rs.getInt("soLuong")
                );
                traoThuongDisplayModelObservableList.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return traoThuongDisplayModelObservableList;
    }


    public ObservableList<TraoThuongDisplayModel> getAllTraoThuongDisplayModelByMaNhanKhau(String maNhanKhau) {
        ObservableList<TraoThuongDisplayModel> traoThuongDisplayModelObservableList =
                FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "with n as (select maNhanKhau, hoTen from nhan_khau where maNhanKhau = '" + maNhanKhau + "'), " +
                "pt as (select maPhanThuong, tenPhanThuong, giaTri from phan_thuong)" +
                "select tt.idNhap, tt.maNhanKhau, n.hoTen, dtt.tenDip, dtt.nam, pt.tenPhanThuong, pt.giaTri, t.soLuong " +
                "from trao_thuong_dip_dac_biet t join thong_tin_dip_dac_biet tt on t.idNhap = tt.idNhap " +
                "join dip_trao_thuong dtt on tt.idDip = dtt.id " +
                "join n on tt.maNhanKhau = n.maNhanKhau " +
                "join pt on t.maPhanThuong = pt.maPhanThuong " +
                "union " +
                "select tt.idNhap, tt.maNhanKhau, n.hoTen, dtt.tenDip, dtt.nam, pt.tenPhanThuong, pt.giaTri, t.soLuong " +
                "from trao_thuong_thanh_tich t join thong_tin_thanh_tich tt on t.idNhap = tt.idNhap " +
                "join dip_trao_thuong dtt on tt.idDip = dtt.id " +
                "join n on tt.maNhanKhau = n.maNhanKhau " +
                "join pt on t.maPhanThuong = pt.maPhanThuong ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TraoThuongDisplayModel temp = new TraoThuongDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("hoTen"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getNString("tenPhanThuong"),
                        rs.getInt("giaTri"),
                        rs.getInt("soLuong")
                );
                traoThuongDisplayModelObservableList.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return traoThuongDisplayModelObservableList;
    }

    public ObservableList<TraoThuongDisplayModel> getAllTraoThuongDisplayModelByKieu(String kieu) {
        ObservableList<TraoThuongDisplayModel> traoThuongDisplayModelObservableList =
                FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query;
        if (kieu.equals("Dịp đặc biệt")) {
            query = "with n as (select maNhanKhau, hoTen from nhan_khau), " +
                    "pt as (select maPhanThuong, tenPhanThuong, giaTri from phan_thuong)" +
                    "select tt.idNhap, tt.maNhanKhau, n.hoTen, dtt.tenDip, dtt.nam, pt.tenPhanThuong, pt.giaTri, t.soLuong " +
                    "from trao_thuong_dip_dac_biet t join thong_tin_dip_dac_biet tt on t.idNhap = tt.idNhap " +
                    "join dip_trao_thuong dtt on tt.idDip = dtt.id " +
                    "join n on tt.maNhanKhau = n.maNhanKhau " +
                    "join pt on t.maPhanThuong = pt.maPhanThuong ";
        } else {
            query = "with n as (select maNhanKhau, hoTen from nhan_khau), " +
                    "pt as (select maPhanThuong, tenPhanThuong, giaTri from phan_thuong)" +
                    "select tt.idNhap, tt.maNhanKhau, n.hoTen, dtt.tenDip, dtt.nam, pt.tenPhanThuong, pt.giaTri, t.soLuong " +
                    "from trao_thuong_thanh_tich t join thong_tin_thanh_tich tt on t.idNhap = tt.idNhap " +
                    "join dip_trao_thuong dtt on tt.idDip = dtt.id " +
                    "join n on tt.maNhanKhau = n.maNhanKhau " +
                    "join pt on t.maPhanThuong = pt.maPhanThuong ";
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TraoThuongDisplayModel temp = new TraoThuongDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("hoTen"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getNString("tenPhanThuong"),
                        rs.getInt("giaTri"),
                        rs.getInt("soLuong")
                );
                traoThuongDisplayModelObservableList.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return traoThuongDisplayModelObservableList;
    }

    public boolean addTraoThuongDipDacBiet(TraoThuongModel traoThuongModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into trao_thuong_dip_dac_biet(idNhap, maPhanThuong, soLuong) values (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, traoThuongModel.getIdNhap());
            statement.setInt(2, traoThuongModel.getMaPhanThuong());
            statement.setInt(3, traoThuongModel.getSoLuong());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTraoThuongDipDacBiet(TraoThuongModel traoThuongModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from trao_thuong_dip_dac_biet " +
                "where idNhap = ? and maPhanThuong = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, traoThuongModel.getIdNhap());
            statement.setInt(2, traoThuongModel.getMaPhanThuong());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTraoThuongThanhTich(TraoThuongModel traoThuongModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from trao_thuong_thanh_tich " +
                "where idNhap = ? and maPhanThuong = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, traoThuongModel.getIdNhap());
            statement.setInt(2, traoThuongModel.getMaPhanThuong());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}





