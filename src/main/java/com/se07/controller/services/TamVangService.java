package com.se07.controller.services;

import com.se07.model.models.TamVangDisplayModel;
import com.se07.model.models.TamVangModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;
import java.util.Optional;

/**
 * class thực hiện các phương thức CRUD với tam_vang
 */
public class TamVangService {

    /**
     *
     * @return tất cả bản ghi tạm vắng dưới dạng TamVangModel
     */
    public ObservableList<TamVangModel> getAllTamVang() {
        ObservableList<TamVangModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_vang";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangModel temp = new TamVangModel(
                        rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));
                listTamVang.add(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamVang;
    }

    /**
     *
     * @return tất cả bản ghi tạm vắng dưới dạng TamVangDisplayModel
     */
    public ObservableList<TamVangDisplayModel> getDisplayTamVang() {
        ObservableList<TamVangDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select nhan_khau.hoTen, tam_vang.noiTamVang, , tuNgay, denNgay, lydo, tam_vang.tinhTrang, tam_vang.idNguoiThucHien" +
                "from tam_vang, nhan_khau where tam_vang.maNhanKhau= nhan_khau.maNhanKhau";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel temp = new TamVangDisplayModel(
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     *
     * @param hoTen
     * @return thông tin tạm vắng theo họ tên
     */
    public ObservableList<TamVangDisplayModel> getDisplayTamVangByHoTen(String hoTen) {
        ObservableList<TamVangDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select nhan_khau.hoTen, tam_vang.noiTamVang, , tuNgay, denNgay, lydo, tam_vang.tinhTrang, tam_vang.idNguoiThucHien" +
                "from tam_vang, nhan_khau where tam_vang.maNhanKhau= nhan_khau.maNhanKhau and hoTen LIKE N'%"+ hoTen + "%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel temp = new TamVangDisplayModel(
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param noiTamVang
     * @return thông tin tạm vắng theo nơi tạm vắng
     */
    public ObservableList<TamVangDisplayModel> getDisplayTamVangByNoiTamVang(String noiTamVang) {
        ObservableList<TamVangDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select nhan_khau.hoTen, tam_vang.noiTamVang, , tuNgay, denNgay, lydo, tam_vang.tinhTrang, tam_vang.idNguoiThucHien" +
                "from tam_vang, nhan_khau where tam_vang.maNhanKhau= nhan_khau.maNhanKhau and" +
                " noiTamVang LIKE N'%"+ noiTamVang + "%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel temp = new TamVangDisplayModel(
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param low
     * @param high
     * @return thông tin tạm vắng với ngày bắt đầu tạm vắng nằm giữa low và high
     */
    public ObservableList<TamVangDisplayModel> getDisplayTamVangWhereTuNgayBetween(Date low, Date high) {
        ObservableList<TamVangDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select nhan_khau.hoTen, tam_vang.noiTamVang, , tuNgay, denNgay, lydo, tam_vang.tinhTrang, tam_vang.idNguoiThucHien" +
                "from tam_vang, nhan_khau where tam_vang.maNhanKhau= nhan_khau.maNhanKhau and tuNgay between ? and ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(low.getTime()));
            statement.setDate(2, new java.sql.Date(high.getTime()));
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel temp = new TamVangDisplayModel(
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;


    }

    /**
     *
     * @param low
     * @param high
     * @return thông tin tạm vắng với ngày kết thúc tạm vắng giữa ngày low và high
     */
    public ObservableList<TamVangDisplayModel> getDisplayTamVangWhereDenNgayBetween(Date low, Date high) {
        ObservableList<TamVangDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select nhan_khau.hoTen, tam_vang.noiTamVang, , tuNgay, denNgay, lydo, tam_vang.tinhTrang, tam_vang.idNguoiThucHien" +
                "from tam_vang, nhan_khau where tam_vang.maNhanKhau= nhan_khau.maNhanKhau and denNgay between ? and ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(low.getTime()));
            statement.setDate(2, new java.sql.Date(high.getTime()));
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel temp = new TamVangDisplayModel(
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;


    }

    /**
     *
     * @param tinhTrang
     * @return thông tin tạm vắng theo tình trạng
     */
    public ObservableList<TamVangDisplayModel> getDisplayTamVangByTinhTrang(String tinhTrang) {
        ObservableList<TamVangDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select nhan_khau.hoTen, tam_vang.noiTamVang, , tuNgay, denNgay, lydo, tam_vang.tinhTrang, tam_vang.idNguoiThucHien" +
                "from tam_vang, nhan_khau where tam_vang.maNhanKhau= nhan_khau.maNhanKhau and" +
                " tinhTrang LIKE N'"+ tinhTrang + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel temp = new TamVangDisplayModel(
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     *
     * @param id
     * @return thông tin tạm vắng theo id người thực hiện
     */
    public ObservableList<TamVangDisplayModel> getDisplayTamVangByIDNguoiThucHien(int id) {
        ObservableList<TamVangDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select nhan_khau.hoTen, tam_vang.noiTamVang, , tuNgay, denNgay, lydo, tam_vang.tinhTrang, tam_vang.idNguoiThucHien" +
                "from tam_vang, nhan_khau where tam_vang.maNhanKhau= nhan_khau.maNhanKhau and" +
                " idNguoiThucHien ='"+ id + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel temp = new TamVangDisplayModel(
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));

                list.add(temp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     *
     * @param maTamVang
     * @return thông tin tạm vắng dưới dạng TamVangModel theo mã tạm vắng
     */
    public Optional<TamVangModel> getTamVangByMaTamVang(String maTamVang) {
        Optional<TamVangModel> tamVangModel = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_vang where maTamVang = '" + maTamVang + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                TamVangModel temp = new TamVangModel(
                        rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("noiTamVang"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));
                tamVangModel = Optional.of(temp);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tamVangModel;
    }

    /**
     *
     * @param tamVangModel
     * @return thêm vào cơ sở dữ liệu một bản ghi tạm vắng
     */

    public boolean addTamVang(TamVangModel tamVangModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into tam_vang(maNhanKhau, noiTamVang, tuNgay, denNgay, lydo, " +
                "tinhTrang, idNguoiThucHien) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tamVangModel.getMaNhanKhau());
            statement.setNString(2, tamVangModel.getNoiTamVang());
            statement.setDate(3, new java.sql.Date(tamVangModel.getTuNgay().getTime()));
            statement.setDate(4, new java.sql.Date(tamVangModel.getDenNgay().getTime()));
            statement.setNString(5, tamVangModel.getLyDo());
            statement.setNString(6, tamVangModel.getTinhTrang());
            statement.setInt(7, tamVangModel.getIdNguoiThucHien());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param tamVangModel
     * @return cập nhật thông tin bản ghi tạm vắng
     */

    public boolean updateTamVang(TamVangModel tamVangModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update tam_vang set " +
                "maNhanKhau = ?, " +
                "noiTamVang = ?," +
                " tuNgay = ?, " +
                "denNgay = ?, lydo = ?, " +
                "tinhTrang = ?,  " +

                "idNguoiThucHien = ? where maTamVang = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tamVangModel.getMaNhanKhau());
            statement.setNString(2, tamVangModel.getNoiTamVang());
            statement.setDate(3, new java.sql.Date(tamVangModel.getTuNgay().getTime()));
            statement.setDate(4, new java.sql.Date(tamVangModel.getDenNgay().getTime()));
            statement.setNString(5, tamVangModel.getLyDo());
            statement.setNString(6, tamVangModel.getTinhTrang());
            statement.setInt(7, tamVangModel.getIdNguoiThucHien());
            statement.setInt(8, tamVangModel.getMaTamVang());
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param tamVangModel
     * @return xóa một bản ghi tạm vắng
     */

    public boolean deleteTamVang(TamVangModel tamVangModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from tam_vang where maTamVang = '" + tamVangModel.getMaTamVang() + "'";
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

    /**
     *
     * @return số lượng bản ghi tạm vắng
     */
    public int getTamVangCount() {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select count(*) from tam_vang where tinhTrang = N'Đã xác nhận'";
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