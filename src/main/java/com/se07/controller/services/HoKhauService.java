package com.se07.controller.services;

import com.se07.model.models.HoKhauModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Optional;

/**
 * Lớp cung cấp các phương thức truy vấn đến cơ sở dữ liệu liên quan đến hộ khẩu
 */
public class HoKhauService {
    /**
     * @return Tất cả các bản ghi hộ khẩu duới dạng HoKhauModel
     */
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHoKhau;
    }

    /**
     * @param maHoKhau Mã hộ khẩu muốn tìm kiếm
     * @return Hộ khẩu theo mã hộ khẩu nếu có, ngược lại trả về {@code Optional.empty()}
     */
    public Optional<HoKhauModel> getHoKhauByMaHoKhau(String maHoKhau) {
        Optional<HoKhauModel> hoKhauModel = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from ho_khau where maHoKhau = '" + maHoKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                HoKhauModel temp = new HoKhauModel(rs.getString("maHoKhau"), rs.getNString("chuHo"),
                        rs.getNString("diachi"), rs.getDate("ngayLap"),
                        rs.getInt("idNguoiThucHien"));
                hoKhauModel = Optional.of(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoKhauModel;
    }

    /**
     * @param chuHo Tên chủ hộ muốn tìm kiếm
     * @return Danh sách các hộ khẩu có tên chủ hộ giống chuỗi đầu vào
     */
    public ObservableList<HoKhauModel> getHoKhauByChuHo(String chuHo) {
        ObservableList<HoKhauModel> listHoKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from ho_khau where chuHo like N'%" + chuHo + "%'";
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHoKhau;
    }

    /**
     * @param diaChi Địa chỉ muốn tìm kiếm
     * @return Danh sách các hộ khẩu có địa chỉ giống chuỗi đầu vào
     */
    public ObservableList<HoKhauModel> getHoKhauByDiaChi(String diaChi) {
        ObservableList<HoKhauModel> listHoKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from ho_khau where diaChi LIKE N'%" + diaChi + "%'";
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHoKhau;
    }

    /**
     * Phương thức thêm mới hộ khẩu vào cơ sở dữ liệu
     *
     * @param hoKhauModel Đối tượng chứa thông tin hộ khẩu muốn thêm mới
     * @return {@code true} nếu thêm thành công ngược lại trả về {@code false}
     */
    public boolean addHoKhau(HoKhauModel hoKhauModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into ho_khau(maHoKhau, chuHo, diaChi, ngayLap, idNguoiThucHien) " +
                " values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, hoKhauModel.getMaHoKhau());
            statement.setNString(2, hoKhauModel.getChuHo());
            statement.setNString(3, hoKhauModel.getDiaChi());
            statement.setDate(4, new Date(hoKhauModel.getNgayLap().getTime()));
            statement.setInt(5, hoKhauModel.getIdNguoiThucHien());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Phương thức cập nhật hộ khẩu dựa vào mã hộ khẩu
     *
     * @param hoKhauModel Đối tượng chứa thông tin hộ khẩu muốn thay đổi
     * @return {@code true} nếu cập nhật thành công ngược lại trả về {@code false}
     */
    public boolean updateHoKhau(HoKhauModel hoKhauModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update ho_khau set ChuHo = ?, diaChi = ?, ngayLap = ?, idNguoiThucHien = ?" +
                " where maHoKhau = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNString(1, hoKhauModel.getChuHo());
            statement.setNString(2, hoKhauModel.getDiaChi());
            statement.setDate(3, new Date(hoKhauModel.getNgayLap().getTime()));
            statement.setInt(4, hoKhauModel.getIdNguoiThucHien());
            statement.setString(5, hoKhauModel.getMaHoKhau());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Phương thức xóa hộ khẩu dựa vào mã hộ khẩu
     *
     * @param hoKhauModel Đối tượng chứa thông tin hộ khẩu muốn xóa
     * @return {@code true} nếu xóa thành công ngược lại trả về {@code false}
     */
    public boolean deleteHoKhau(HoKhauModel hoKhauModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from ho_khau " + "where maHoKhau = '" + hoKhauModel.getMaHoKhau() + "'";
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

    /**
     * @return Số lượng hộ khẩu
     */
    public int getHoKhauCount() {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select count(*) from ho_khau";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            int count = rs.getInt(1);
            statement.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * @return Danh sách tất cả mã hộ khẩu
     */
    public ObservableList<String> getAllMaHoKhau() {
        ObservableList<String> listMaHoKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select maHoKhau from ho_khau";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                listMaHoKhau.add(rs.getString(1));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMaHoKhau;
    }

    /**
     * Phương thức trả về mã hộ khẩu dựa vào địa chỉ
     *
     * @param diaChi Địa chỉ của hộ khẩu cần tìm
     * @return Mã hộ khẩu ứng với địa chỉ tương ứng
     */
    public String getMaHoKhauByDiaChi(String diaChi) {
        String maHoKhau = null;
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select maHoKhau from ho_khau where diaChi = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, diaChi);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                maHoKhau = rs.getString(1);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maHoKhau;
    }

    /**
     * @param tu  Ngày đầu
     * @param den Ngày cuối
     * @return Danh sách hộ khẩu có ngày lập từ ngày {@code tu} đến ngày {@code den}
     */
    public ObservableList<HoKhauModel> getHoKhauByNgayLapBetween(java.util.Date tu, java.util.Date den) {
        ObservableList<HoKhauModel> hoKhauModelObservableList = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from ho_khau where ngayLap between ? and ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(tu.getTime()));
            statement.setDate(2, new java.sql.Date(den.getTime()));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                HoKhauModel temp = new HoKhauModel(rs.getString("maHoKhau"),
                        rs.getNString("chuHo"),
                        rs.getNString("diachi"),
                        rs.getDate("ngayLap"),
                        rs.getInt("idNguoiThucHien"));
                hoKhauModelObservableList.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoKhauModelObservableList;
    }
}