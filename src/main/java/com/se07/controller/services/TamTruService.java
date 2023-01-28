package com.se07.controller.services;

import com.se07.model.models.TamTruDisplayModel;
import com.se07.model.models.TamTruModel;

import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;
import java.util.Optional;

/**
 * class thực hiện các phương thức CRUD với tam_tru
 */
public class TamTruService {

    /**
     *
     * @return tất cả bản ghi tạm trú dưới dạng TamTruModel
     */
    public ObservableList<TamTruModel> getAllTamTru() {
        ObservableList<TamTruModel> listTamTru = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_tru";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruModel temp = new TamTruModel(
                        rs.getInt("maTamTru"),
                        rs.getString("maHoKhau"),
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));
                listTamTru.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamTru;
    }

    /**
     *
     * @return tất cả bản ghi tạm trú dưới dạng  TamTruDisplayModel
     */
    public ObservableList<TamTruDisplayModel> getDisplayTamTru(){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select maTamTru, CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang," +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau";
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getInt("maTamTru"),
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"));

                list.add(temp);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param CCCD
     * @return thông tin tạm trú theo căn cước công dân
     */
    public ObservableList<TamTruDisplayModel> getDisplayTamTruByCCCD(String CCCD){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select maTamTru ,CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau and CCCD LIKE '%"+ CCCD + "%'";
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getInt("maTamTru"),
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"));

                list.add(temp);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param hoTen
     * @return thông tin tạm trú theo họ tên
     */
    public ObservableList<TamTruDisplayModel> getDisplayTamTruByHoTen(String hoTen){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select maTamTru,CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau and hoTen LIKE N'%"+ hoTen + "%'";
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getInt("maTamTru"),
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"));

                list.add(temp);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param noiTamTru
     * @return thông tin tạm trú theo nơi tạm trú
     */

    public ObservableList<TamTruDisplayModel> getDisplayTamTruByNoiTamTru(String noiTamTru){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select maTamTru ,CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau and ho_khau.diaChi LIKE N'%"+ noiTamTru + "%'";
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getInt("maTamTru"),
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"));

                list.add(temp);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param low
     * @param high
     * @return thông tin tạm trú với ngày bắt đầu tạm trú trong khoảng từ low dến high
     */
    public ObservableList<TamTruDisplayModel> getDisplayTamTruWhereTuNgayBetween(java.util.Date low, Date high){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select maTamTru, CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau and tuNgay between ? and ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(low.getTime()));
            statement.setDate(2, new java.sql.Date(high.getTime()));
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getInt("maTamTru"),
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"));

                list.add(temp);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }



    /**
     *
     * @param low
     * @param high
     * @return thông tin tạm trú với ngày kết thúc tạm trú trong khoảng từ low dến high
     */
    public ObservableList<TamTruDisplayModel> getDisplayTamTruWhereDenNgayBetween(java.util.Date low, Date high){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select maTamTru, CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau and denNgay between ? and ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(low.getTime()));
            statement.setDate(2, new java.sql.Date(high.getTime()));
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getInt("maTamTru"),
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"));

                list.add(temp);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param tinhTrang
     * @return thông tin tạm trú theo tình trạng
     */
    public ObservableList<TamTruDisplayModel> getDisplayTamTruByTinhTrang(String tinhTrang){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select maTamTru, CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau and tinhTrang = N'"+ tinhTrang + "'";
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getInt("maTamTru"),
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"));

                list.add(temp);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param tu
     * @param den
     * @return các bản ghi tạm trú có ngày tạm trú nằm trong khoảng từ tu đến den
     */
    public ObservableList<TamTruDisplayModel> getTamTruByNgayBetween(Date tu, Date den) {
        ObservableList<TamTruDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select maTamTru, CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang" +
                "from tam_tru t, ho_khau" +
                "where (tam_tru.maHoKhau= ho_khau.maHoKhau) and (t.tuNgay < ?) and (t.denNgay between > ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(den.getTime()));
            statement.setDate(2, new java.sql.Date(tu.getTime()));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getInt("maTamTru"),
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"));

                list.add(temp);



            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param id
     * @return thông tin tạm trú theo id người thực hiện
     */
    public ObservableList<TamTruDisplayModel> getDisplayTamTruByIDNguoiThucHien(int id){
        ObservableList<TamTruDisplayModel> list= FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query= "select CCCD, tam_tru.hoTen, ho_khau.diaChi as noiTamTru, tuNgay, denNgay, lydo, tam_tru.tinhTrang" +
                "from tam_tru, ho_khau where tam_tru.maHoKhau= ho_khau.maHoKhau and tam_tru.idNguoiThucHien = '"+ id + "'";
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamTruDisplayModel temp = new TamTruDisplayModel(
                        rs.getInt("maTamTru"),
                        rs.getString("CCCD"),
                        rs.getNString("hoTen"),
                        rs.getNString("noiTamTru"),
                        rs.getDate("tuNgay"),
                        rs.getDate("denNgay"),
                        rs.getNString("lydo"),
                        rs.getNString("tinhTrang"));

                list.add(temp);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


    /**
     *
     * @param tamTruModel
     * @return thêm một bản ghi tạm trú
     */
    public boolean addTamTru(TamTruModel tamTruModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into tam_tru(maHoKhau, CCCD, hoTen, tuNgay, denNgay, lydo, tinhTrang, idNguoiThucHien)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tamTruModel.getMaHoKhau());
            statement.setString(2, tamTruModel.getCCCD());
            statement.setNString(3, tamTruModel.getHoTen());
            statement.setDate(4, new java.sql.Date(tamTruModel.getTuNgay().getTime()));
            statement.setDate(5, new java.sql.Date(tamTruModel.getDenNgay().getTime()));
            statement.setNString(6, tamTruModel.getLyDo());
            statement.setNString(7, tamTruModel.getTinhTrang());
            statement.setInt(8, tamTruModel.getIdNguoiThucHien());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param tamTruModel
     * @return cập nhật một bản ghi tạm trú
     */

    public boolean updateTamTru(TamTruModel tamTruModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update tam_tru set " +
                "maHoKhau = ?," +
                "CCCD = ?, " +
                "hoTen = ?, " +
                "tuNgay = ?, " +
                "denNgay = ?, " +
                "lydo = ?," +
                "tinhTrang = ?, " +
                "idNguoiThucHien = ? where maTamTru = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNString(1, tamTruModel.getMaHoKhau());
            statement.setString(2, tamTruModel.getCCCD());
            statement.setNString(3, tamTruModel.getHoTen());
            statement.setDate(4, new java.sql.Date(tamTruModel.getTuNgay().getTime()));
            statement.setDate(5, new java.sql.Date(tamTruModel.getDenNgay().getTime()));
            statement.setNString(6, tamTruModel.getLyDo());
            statement.setNString(7, tamTruModel.getTinhTrang());
            statement.setInt(8, tamTruModel.getIdNguoiThucHien());
            statement.setInt(9, tamTruModel.getMaTamTru());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param tamTruModel
     * @return xóa một bản ghi tạm trú
     */
    public boolean deleteTamTru(TamTruModel tamTruModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from tam_tru where maTamTru = '" + tamTruModel.getMaTamTru() + "'";
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
     *
     * @return số lượng bản ghi tạm trú
     */

    public int getTamTruCount() {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select count(*) from tam_tru where tinhTrang = N'Đã xác nhận'";
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