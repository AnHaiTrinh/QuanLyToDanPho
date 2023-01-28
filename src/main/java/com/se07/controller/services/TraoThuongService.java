package com.se07.controller.services;
import com.se07.model.models.TraoThuongDisplayModel;
import com.se07.model.models.TraoThuongModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Statement;
import java.sql.*;
import java.util.Date;
import java.util.Optional;

public class TraoThuongService {

    /**
     *
     * @return tất cả bản ghi trao thưởng thành tích
     */

    public ObservableList<TraoThuongDisplayModel> getAllTraoThuongThanhTich() {
        ObservableList<TraoThuongDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, tenPhanThuong, giaTri, soLuong " +
                "from trao_thuong_thanh_tich, phan_thuong" +
                "where trao_thuong_thanh_tich.maPhanThuong = phan_thuong.maPhanThuong ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TraoThuongDisplayModel temp = new TraoThuongDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getNString("tenPhanThuong"),
                        rs.getDouble("giaTri"),
                        rs.getInt("soLuong"));
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
     * @param idNhap
     * @return các bản ghi trao thưởng thành tích theo id nhập
     */
    public ObservableList<TraoThuongDisplayModel> getTraoThuongThanhTichByIDNhap(int idNhap) {
        ObservableList<TraoThuongDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, tenPhanThuong, giaTri, soLuong " +
                "from trao_thuong_thanh_tich, phan_thuong" +
                "where trao_thuong_thanh_tich.maPhanThuong = phan_thuong.maPhanThuong " +
                "and idNhap = '"  + idNhap + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TraoThuongDisplayModel temp = new TraoThuongDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getNString("tenPhanThuong"),
                        rs.getDouble("giaTri"),
                        rs.getInt("soLuong"));
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
     * @param tenPhanThuong
     * @return các bản ghi trao thưởng thành tích theo tên phần thưởng
     */
    public ObservableList<TraoThuongDisplayModel> getTraoThuongThanhTichByTenPhanThuong(String tenPhanThuong) {
        ObservableList<TraoThuongDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, tenPhanThuong, giaTri, soLuong " +
                "from trao_thuong_thanh_tich, phan_thuong" +
                "where trao_thuong_thanh_tich.maPhanThuong = phan_thuong.maPhanThuong " +
                "and tenPhanThuong like '%"+ tenPhanThuong +"%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TraoThuongDisplayModel temp = new TraoThuongDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getNString("tenPhanThuong"),
                        rs.getDouble("giaTri"),
                        rs.getInt("soLuong"));
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
     * @param maHoKhau
     * @return các bản ghi trao thưởng thành tích theo hộ khẩu
     */
    public ObservableList<TraoThuongDisplayModel> getTraoThuongThanhTichByHoKhau(String maHoKhau) {
        ObservableList<TraoThuongDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();

        String query = " select giaTri, soLuong " +
                "from trao_thuong_thanh_tich, phan_thuong, thong_tin_thanh_tich, nhan_khau " +
                "where trao_thuong_thanh_tich.maPhanThuong = phan_thuong.maPhanThuong" +
                "and thong_tin_thanh_tich.idNhap = trao_thuong_thanh_tich.idNhap" +
                "and thong_tin_thanh_tich.maNhanKhau =nhan_khau.maNhanKhau " +
                "and nhan_khau.maHoKhau = '"+ maHoKhau +"' ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TraoThuongDisplayModel temp = new TraoThuongDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getNString("tenPhanThuong"),
                        rs.getDouble("giaTri"),
                        rs.getInt("soLuong"));
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
     * @param traoThuongModel
     * @return thêm một bản ghi trao thưởng thành tích
     */
    public boolean addTraoThuongThanhTich(TraoThuongModel traoThuongModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into trao_thuong_thanh_tich(idNhap, maPhanThuong, soLuong) values (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, traoThuongModel.getIdNhap());
            statement.setString(2, traoThuongModel.getMaPhanThuong());
            statement.setInt(3, traoThuongModel.getSoLuong());
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
     * @param traoThuongModel
     * @return cập nhật một bản ghi trao thưởng thành tích
     */

    public boolean updateTraoThuongThanhTich(TraoThuongModel traoThuongModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update trao_thuong_thanh_tich set " +
                "maPhanThuong = ?," +
                " soLuong = ?, " +
                "where idNhap = ?";
        try {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, traoThuongModel.getMaPhanThuong());
            statement.setInt(2, traoThuongModel.getSoLuong());
            statement.setInt(3, traoThuongModel.getIdNhap());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param traoThuong
     * @return xóa một bản ghi trao thưởng thành tích
     */

    public boolean deleteTraoThuongThanhTich(TraoThuongModel traoThuong) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from trao_thuong_thanh_tich where idNhap = '" + traoThuong.getIdNhap() + "' and maPhanThuong = '"+traoThuong.getMaPhanThuong() + "'  ";
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
     * @return tất cả bản ghi trao thưởng dịp đặc biệt
     */

    public ObservableList<TraoThuongDisplayModel> getAllTraoThuongDipDacBiet() {
        ObservableList<TraoThuongDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, tenPhanThuong, giaTri, soLuong " +
                "from trao_thuong_dip_dac_biet, phan_thuong" +
                "where trao_thuong_dip_dac_biet.maPhanThuong = phan_thuong.maPhanThuong ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TraoThuongDisplayModel temp = new TraoThuongDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getNString("tenPhanThuong"),
                        rs.getDouble("giaTri"),
                        rs.getInt("soLuong"));
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
     * @param tenPhanThuong
     * @return các bản ghi trao thưởng dịp đặc biệt theo tên phần thưởng
     */
    public ObservableList<TraoThuongDisplayModel> getTraoThuongDipDacBietByTenPhanThuong(String tenPhanThuong) {
        ObservableList<TraoThuongDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, tenPhanThuong, giaTri, soLuong " +
                "from trao_thuong_dip_dac_biet, phan_thuong" +
                "where trao_thuong_dip_dac_biet.maPhanThuong = phan_thuong.maPhanThuong "
                +
                "and tenPhanThuong like '%"+ tenPhanThuong +"%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TraoThuongDisplayModel temp = new TraoThuongDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getNString("tenPhanThuong"),
                        rs.getDouble("giaTri"),
                        rs.getInt("soLuong"));
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
     * @param idNhap
     * @return các bản ghi trao thưởng dịp đặc biệt theo id nhập
     */

    public ObservableList<TraoThuongDisplayModel> getTraoThuongDipDacBietByIDNhap(int idNhap) {
        ObservableList<TraoThuongDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, tenPhanThuong, giaTri, soLuong " +
                "from trao_thuong_dip_dac_biet, phan_thuong" +
                "where trao_thuong_dip_dac_biet.maPhanThuong = phan_thuong.maPhanThuong "
                +
                "and idNhap like '"+ idNhap +"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TraoThuongDisplayModel temp = new TraoThuongDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getNString("tenPhanThuong"),
                        rs.getDouble("giaTri"),
                        rs.getInt("soLuong"));
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
     * @param maHoKhau
     * @return các bản ghi trao thưởng dịp đặc biệt theo hộ khẩu
     */

    public ObservableList<TraoThuongDisplayModel> getTraoThuongDipDacBietByHoKhau(String maHoKhau) {
        ObservableList<TraoThuongDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = " select idNhap, tenPhanThuong, giaTri, soLuong " +
                "from trao_thuong_thanh_tich, phan_thuong, thong_tin_thanh_tich, nhan_khau " +
                "where trao_thuong_thanh_tich.maPhanThuong = phan_thuong.maPhanThuong" +
                "and thong_tin_thanh_tich.idNhap = trao_thuong_thanh_tich.idNhap" +
                "and thong_tin_thanh_tich.maNhanKhau =nhan_khau.maNhanKhau " +
                "and nhan_khau.maHoKhau = '"+ maHoKhau +"' ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TraoThuongDisplayModel temp = new TraoThuongDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getNString("tenPhanThuong"),
                        rs.getDouble("giaTri"),
                        rs.getInt("soLuong"));
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
     * @param traoThuongModel
     * @return thêm một bản ghi trao thưởng dịp đặc biệt
     */
    public boolean addTraoThuongDipDacBiet(TraoThuongModel traoThuongModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into trao_thuong_dip_dac_biet(idNhap, maPhanThuong, soLuong) values (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, traoThuongModel.getIdNhap());
            statement.setString(2, traoThuongModel.getMaPhanThuong());
            statement.setInt(3, traoThuongModel.getSoLuong());
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
     * @param traoThuongModel
     * @return cập nhật một bản ghi trao thưởng dịp đặc biệt
     */

    public boolean updateTraoThuongDipDacBiet(TraoThuongModel traoThuongModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update trao_thuong_dip_dac_biet set " +
                "maPhanThuong = ?," +
                " soLuong = ?, " +
                "where idNhap = ?";
        try {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, traoThuongModel.getMaPhanThuong());
            statement.setInt(2, traoThuongModel.getSoLuong());
            statement.setInt(3, traoThuongModel.getIdNhap());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param traoThuong
     * @return xóa một bản ghi trao thưởng dịp đặc biệt
     */

    public boolean deleteTraoThuongDipDacBiet(TraoThuongModel traoThuong) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from trao_thuong_dip_dac_biet where idNhap = '" + traoThuong.getIdNhap() + "' and maPhanThuong = '"+traoThuong.getMaPhanThuong() + "'  ";
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
     * @param namHoc
     * @return tổng giá trị phần thưởng thành tích được trao theo năm học
     */

    public double getAllGiaTriTraoThuongThanhTichByNamHoc(String namHoc) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = " select giaTri, soLuong " +
                "from trao_thuong_thanh_tich, phan_thuong, thong_tin_thanh_tich, nhan_khau " +
                "where trao_thuong_thanh_tich.maPhanThuong = phan_thuong.maPhanThuong" +
                "and thong_tin_thanh_tich.idNhap = trao_thuong_thanh_tich.idNhap" +
                "and thong_tin_thanh_tich.maNhanKhau =nhan_khau.maNhanKhau " +
                "and thong_tin_thanh_tich.namHoc = '"+namHoc+"'"

                ;

        double result = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                result +=  rs.getDouble("giaTri") * rs.getInt("soLuong");

            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     *
     * @param maHoKhau
     * @return tổng giá trị các phần quà trao thưởng thành tích mà một hộ khẩu nhận được
     */
    public double getGiaTriTraoThuongThanhTichByHoKhau(String maHoKhau) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = " select giaTri, soLuong " +
                "from trao_thuong_thanh_tich, phan_thuong, thong_tin_thanh_tich, nhan_khau " +
                "where trao_thuong_thanh_tich.maPhanThuong = phan_thuong.maPhanThuong" +
                "and thong_tin_thanh_tich.idNhap = trao_thuong_thanh_tich.idNhap" +
                "and thong_tin_thanh_tich.maNhanKhau =nhan_khau.maNhanKhau " +
                "and nhan_khau.maHoKhau = '"+ maHoKhau +"' ";

        double result = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                result +=  rs.getDouble("giaTri") * rs.getInt("soLuong");

            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public double getAllGiaTriTraoThuongDipDacBietByNam(int nam) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select giaTri, soLuong " +
                "from trao_thuong_dip_dac_biet, phan_thuong, thong_tin_dip_dac_biet, nhan_khau " +
                "where trao_thuong_dip_dac_biet.maPhanThuong = phan_thuong.maPhanThuong" +
                "and thong_tin_dip_dac_biet.idNhap = trao_thuong_dip_dac_biet.idNhap" +
                "and thong_tin_dip_dac_biet.maNhanKhau =nhan_khau.maNhanKhau " +
                "and thong_tin_dip_dac_biet.nam ='"+nam+"'" ;

        double result = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                result +=  rs.getDouble("giaTri") * rs.getInt("soLuong");

            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }




    public double getGiaTriTraoThuongDipDacBietByHoKhau(String maHoKhau ) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = " select giaTri, soLuong " +
                "from trao_thuong_dip_dac_biet, phan_thuong, thong_tin_dip_dac_biet, nhan_khau " +
                "where trao_thuong_dip_dac_biet.maPhanThuong = phan_thuong.maPhanThuong" +
                "and thong_tin_dip_dac_biet.idNhap = trao_thuong_dip_dac_biet.idNhap" +
                "and thong_tin_dip_dac_biet.maNhanKhau =nhan_khau.maNhanKhau " +
                "and nhan_khau.maHoKhau = '"+ maHoKhau +"' ";

        double result = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                result +=  rs.getDouble("giaTri") * rs.getInt("soLuong");

            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }





}





