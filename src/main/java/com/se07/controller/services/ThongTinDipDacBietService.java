package com.se07.controller.services;

import com.se07.model.models.ThongTinDipDacBietModel;
import com.se07.model.models.ThongTinDipDacBietDisplayModel;
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
    public ObservableList<ThongTinDipDacBietDisplayModel> getAllThongTinDipDacBiet() {
        ObservableList<ThongTinDipDacBietDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.idNhap, t.maNhanKhau, n.hoTen, d.tenDip, d.nam, t.tinhTrang" +
                " from thong_tin_dip_dac_biet t, nhan_khau n, dip_trao_thuong d " +
                "where t.maNhanKhau = n.maNhanKhau and t.idDip = d.id";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                ThongTinDipDacBietDisplayModel temp = new ThongTinDipDacBietDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("hoTen"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getNString("tinhTrang"));
                list.add(temp);
            }
            statement.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<ThongTinDipDacBietDisplayModel> getAllThongTinDipDacBietByMaNhanKhau(String maNhanKhau) {
        ObservableList<ThongTinDipDacBietDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.idNhap, t.maNhanKhau, n.hoTen, d.tenDip, d.nam, t.tinhTrang" +
                " from thong_tin_dip_dac_biet t, nhan_khau n, dip_trao_thuong d " +
                "where t.maNhanKhau = n.maNhanKhau and t.idDip = d.id" +
                "and t.maNhanKhau like '%"+maNhanKhau+"%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                ThongTinDipDacBietDisplayModel temp = new ThongTinDipDacBietDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("hoTen"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getNString("tinhTrang"));
                list.add(temp);
            }
            statement.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<ThongTinDipDacBietDisplayModel> getAllThongTinDipDacBietByHoTen(String hoTen) {
        ObservableList<ThongTinDipDacBietDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.idNhap, t.maNhanKhau, n.hoTen, d.tenDip, d.nam, t.tinhTrang" +
                " from thong_tin_dip_dac_biet t, nhan_khau n, dip_trao_thuong d " +
                "where t.maNhanKhau = n.maNhanKhau and t.idDip = d.id" +
                "and n.hoTen like N'%"+hoTen+"%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                ThongTinDipDacBietDisplayModel temp = new ThongTinDipDacBietDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("hoTen"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getNString("tinhTrang"));
                list.add(temp);
            }
            statement.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<ThongTinDipDacBietDisplayModel> getAllThongTinDipDacBietByTenDip(String tenDip) {
        ObservableList<ThongTinDipDacBietDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.idNhap, t.maNhanKhau, n.hoTen, d.tenDip, d.nam, t.tinhTrang" +
                " from thong_tin_dip_dac_biet t, nhan_khau n, dip_trao_thuong d " +
                "where t.maNhanKhau = n.maNhanKhau and t.idDip = d.id" +
                "and d.tenDip like N'%"+tenDip+"%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                ThongTinDipDacBietDisplayModel temp = new ThongTinDipDacBietDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("hoTen"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getNString("tinhTrang"));
                list.add(temp);
            }
            statement.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<ThongTinDipDacBietDisplayModel> getAllThongTinDipDacBietByNam(int nam) {
        ObservableList<ThongTinDipDacBietDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.idNhap, t.maNhanKhau, n.hoTen, d.tenDip, d.nam, t.tinhTrang" +
                " from thong_tin_dip_dac_biet t, nhan_khau n, dip_trao_thuong d " +
                "where t.maNhanKhau = n.maNhanKhau and t.idDip = d.id" +
                "and d.nam = '"+nam+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                ThongTinDipDacBietDisplayModel temp = new ThongTinDipDacBietDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("hoTen"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getNString("tinhTrang"));
                list.add(temp);
            }
            statement.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<ThongTinDipDacBietDisplayModel> getAllThongTinDipDacBietByTinhTrang(String tinhTrang) {
        ObservableList<ThongTinDipDacBietDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.idNhap, t.maNhanKhau, n.hoTen, d.tenDip, d.nam, t.tinhTrang" +
                " from thong_tin_dip_dac_biet t, nhan_khau n, dip_trao_thuong d " +
                "where t.maNhanKhau = n.maNhanKhau and t.idDip = d.id" +
                "and d.nam = '"+tinhTrang+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                ThongTinDipDacBietDisplayModel temp = new ThongTinDipDacBietDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("hoTen"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getNString("tinhTrang"));
                list.add(temp);
            }
            statement.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

//
    public Optional<ThongTinDipDacBietModel> getThongTinDipDacBietById(int id) {
        Optional<ThongTinDipDacBietModel> thongTinDipDacBietModel = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from thong_tin_dip_dac_biet where idNhap = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                ThongTinDipDacBietModel temp = new ThongTinDipDacBietModel(rs.getInt("idNhap"),
                        rs.getInt("idDip"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien"));
                thongTinDipDacBietModel = Optional.of(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thongTinDipDacBietModel;
    }



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
//
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public boolean updateThongTinDipDacBiet(ThongTinDipDacBietModel thongTinDipDacBietModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update thong_tin_dip_dac_biet set idDip =?, maNhanKhau = ?, tinhTrang = ?, " +
                "idNguoiThucHien = ? where idNhap = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, thongTinDipDacBietModel.getIdDip());
            statement.setString(2, thongTinDipDacBietModel.getMaNhanKhau());
            statement.setNString(3, thongTinDipDacBietModel.getTinhTrang());
            statement.setInt(4, thongTinDipDacBietModel.getIdNguoiThucHien());
            statement.setInt(5, thongTinDipDacBietModel.getIdNhap());
            statement.executeUpdate();
            statement.close();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteThongTinDipDacBiet(ThongTinDipDacBietModel thongTinDipDacBietModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from thong_tin_dip_dac_biet where idNhap = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, thongTinDipDacBietModel.getIdNhap());
            statement.executeUpdate();
            statement.close();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public ObservableList<ThongTinDipDacBietDisplayModel> getAllThongTinDipDacBietByMaHoKhau(String maHoKhau) {
        ObservableList<ThongTinDipDacBietDisplayModel> list = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.idNhap, t.maNhanKhau, n.hoTen, d.tenDip, d.nam, t.tinhTrang" +
                " from thong_tin_dip_dac_biet t, nhan_khau n, dip_trao_thuong d " +
                "where t.maNhanKhau = n.maNhanKhau and t.idDip = d.id " +
                "and n.maHoKhau = '"+maHoKhau+"'";
        System.out.println(query);
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                ThongTinDipDacBietDisplayModel temp = new ThongTinDipDacBietDisplayModel(
                        rs.getInt("idNhap"),
                        rs.getString("maNhanKhau"),
                        rs.getNString("hoTen"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getNString("tinhTrang"));
                list.add(temp);
            }
            statement.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
public ThongTinDipDacBietModel convertDisplayModelToModel (ThongTinDipDacBietDisplayModel displayModel){
    ThongTinDipDacBietModel temp = getThongTinDipDacBietById(displayModel.getIdNhap()).get();

    return new ThongTinDipDacBietModel(displayModel.getIdNhap(),
            temp.getIdDip(), displayModel.getMaNhanKhau(),
            displayModel.getTinhTrang(), temp.getIdNguoiThucHien());

}
}
