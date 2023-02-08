package com.se07.controller.services;

import com.se07.model.models.TamVangDisplayModel;
import com.se07.model.models.TamVangModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;
import java.util.Optional;

public class TamVangService {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamVang;
    }


    public ObservableList<TamVangDisplayModel> getAllTamVangDisplay() {
        ObservableList<TamVangDisplayModel> tamVangDisplayModels = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.maTamVang, t.maNhanKhau, hoTen, noiTamVang, tuNgay, denNgay, lyDo, t.tinhTrang " +
                "from tam_vang t join nhan_khau n on t.maNhanKhau = n.maNhanKhau";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel tmp = new TamVangDisplayModel(rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"), rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"), rs.getDate("tuNgay"),
                        rs.getDate("denNgay"), rs.getNString("lyDo"),
                        rs.getNString("tinhTrang"));
                tamVangDisplayModels.add(tmp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tamVangDisplayModels;
    }

    public ObservableList<TamVangDisplayModel> getAllTamVangDisplayByHoKhau(String maHoKhau) {
        ObservableList<TamVangDisplayModel> tamVangDisplayModels = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.maTamVang, t.maNhanKhau, hoTen, noiTamVang, tuNgay, denNgay, lyDo, t.tinhTrang " +
                "from tam_vang t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "and n.maHoKhau ='" + maHoKhau + "' ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel tmp = new TamVangDisplayModel(rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"), rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"), rs.getDate("tuNgay"),
                        rs.getDate("denNgay"), rs.getNString("lyDo"),
                        rs.getNString("tinhTrang"));
                tamVangDisplayModels.add(tmp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tamVangDisplayModels;
    }

    public ObservableList<TamVangDisplayModel> getTamVangByMaNhanKhau(String maNhanKhau) {
        ObservableList<TamVangDisplayModel> tamVangDisplayModels = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.maTamVang, t.maNhanKhau, hoTen, noiTamVang, tuNgay, denNgay, lyDo, t.tinhTrang " +
                "from tam_vang t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "where n.maNhanKhau = '" + maNhanKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel tmp = new TamVangDisplayModel(rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"), rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"), rs.getDate("tuNgay"),
                        rs.getDate("denNgay"), rs.getNString("lyDo"),
                        rs.getNString("tinhTrang"));
                tamVangDisplayModels.add(tmp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tamVangDisplayModels;
    }

    public ObservableList<TamVangDisplayModel> getTamVangByMaNhanKhauAndHoKhau(String maNhanKhau, String maHoKhau) {
        ObservableList<TamVangDisplayModel> tamVangDisplayModels = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.maTamVang, t.maNhanKhau, hoTen, noiTamVang, tuNgay, denNgay, lyDo, t.tinhTrang " +
                "from (tam_vang t join nhan_khau n on t.maNhanKhau = n.maNhanKhau) " +
                "inner join ho_khau on ho_khau.maHoKhau = n.maHoKhau " +
                "where n.maNhanKhau = '" + maNhanKhau + "' and ho_khau.maHoKhau = '" + maHoKhau + "' ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel tmp = new TamVangDisplayModel(rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"), rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"), rs.getDate("tuNgay"),
                        rs.getDate("denNgay"), rs.getNString("lyDo"),
                        rs.getNString("tinhTrang"));
                tamVangDisplayModels.add(tmp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tamVangDisplayModels;
    }

    public ObservableList<TamVangDisplayModel> getTamVangByHoTen(String hoTen) {
        ObservableList<TamVangDisplayModel> tamVangDisplayModels = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.maTamVang, t.maNhanKhau, hoTen, noiTamVang, tuNgay, denNgay, lyDo, t.tinhTrang " +
                "from tam_vang t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "where n.hoTen like N'%" + hoTen + "%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel tmp = new TamVangDisplayModel(rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"), rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"), rs.getDate("tuNgay"),
                        rs.getDate("denNgay"), rs.getNString("lyDo"),
                        rs.getNString("tinhTrang"));
                tamVangDisplayModels.add(tmp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tamVangDisplayModels;
    }

    public ObservableList<TamVangDisplayModel> getTamVangByHoTenAndHoKhau(String hoTen, String maHoKhau) {
        ObservableList<TamVangDisplayModel> tamVangDisplayModels = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.maTamVang, t.maNhanKhau, hoTen, noiTamVang, tuNgay, denNgay, lyDo, t.tinhTrang " +
                "from tam_vang t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "inner join ho_khau h on h.maHoKhau = n.maHoKhau " +
                "where n.hoTen like N'% " + hoTen + "%'" +
                "and n.maHoKhau = '" + maHoKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel tmp = new TamVangDisplayModel(rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"), rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"), rs.getDate("tuNgay"),
                        rs.getDate("denNgay"), rs.getNString("lyDo"),
                        rs.getNString("tinhTrang"));
                tamVangDisplayModels.add(tmp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tamVangDisplayModels;
    }

    public ObservableList<TamVangDisplayModel> getTamVangByNoiTamVang(String noiTamVang) {
        ObservableList<TamVangDisplayModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.maTamVang, t.maNhanKhau, hoTen, noiTamVang, tuNgay, denNgay, lyDo, t.tinhTrang " +
                "from tam_vang t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "where t.noiTamVang like N'%" + noiTamVang + "%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel tmp = new TamVangDisplayModel(rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"), rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"), rs.getDate("tuNgay"),
                        rs.getDate("denNgay"), rs.getNString("lyDo"),
                        rs.getNString("tinhTrang"));
                listTamVang.add(tmp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamVang;
    }

    public ObservableList<TamVangDisplayModel> getTamVangByNoiTamVangAndHoKhau(String noiTamVang, String maHoKhau) {
        ObservableList<TamVangDisplayModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.maTamVang, t.maNhanKhau, hoTen, noiTamVang, tuNgay, denNgay, lyDo, t.tinhTrang " +
                "from tam_vang t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "where t.noiTamVang like N'%" + noiTamVang + "%'" +
                "and n.maHoKhau = '" + maHoKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel tmp = new TamVangDisplayModel(rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"), rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"), rs.getDate("tuNgay"),
                        rs.getDate("denNgay"), rs.getNString("lyDo"),
                        rs.getNString("tinhTrang"));
                listTamVang.add(tmp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamVang;
    }

    public ObservableList<TamVangDisplayModel> getTamVangBytinhTrang(String tinhTrang) {
        ObservableList<TamVangDisplayModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.maTamVang, t.maNhanKhau, hoTen, noiTamVang, tuNgay, denNgay, lyDo, t.tinhTrang " +
                "from tam_vang t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "where t.tinhTrang = N'" + tinhTrang + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel tmp = new TamVangDisplayModel(rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"), rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"), rs.getDate("tuNgay"),
                        rs.getDate("denNgay"), rs.getNString("lyDo"),
                        rs.getNString("tinhTrang"));
                listTamVang.add(tmp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamVang;
    }

    public ObservableList<TamVangDisplayModel> getTamVangBytinhTrangAndHoKhau(String tinhTrang, String maHoKhau) {
        ObservableList<TamVangDisplayModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.maTamVang, t.maNhanKhau, hoTen, noiTamVang, tuNgay, denNgay, lyDo, t.tinhTrang " +
                "from tam_vang t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "where t.tinhTrang = N'" + tinhTrang + "'" +
                "and n.maHoKhau ='" + maHoKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel tmp = new TamVangDisplayModel(rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"), rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"), rs.getDate("tuNgay"),
                        rs.getDate("denNgay"), rs.getNString("lyDo"),
                        rs.getNString("tinhTrang"));
                listTamVang.add(tmp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamVang;
    }

    public ObservableList<TamVangDisplayModel> getTamVangByNgayBetween(Date tu, Date den) {
        ObservableList<TamVangDisplayModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.maTamVang, t.maNhanKhau, hoTen, noiTamVang, tuNgay, denNgay, lyDo, t.tinhTrang " +
                "from tam_vang t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "where (t.tuNgay <= ?) and (t.denNgay => ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(den.getTime()));
            statement.setDate(2, new java.sql.Date(tu.getTime()));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TamVangDisplayModel tmp = new TamVangDisplayModel(rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"), rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"), rs.getDate("tuNgay"),
                        rs.getDate("denNgay"), rs.getNString("lyDo"),
                        rs.getNString("tinhTrang"));
                listTamVang.add(tmp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamVang;
    }

    public ObservableList<TamVangDisplayModel> getTamVangByNgayBetweenAndHoKhau(Date tu, Date den, String maHoKhau) {
        ObservableList<TamVangDisplayModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.maTamVang, t.maNhanKhau, hoTen, noiTamVang, tuNgay, denNgay, lyDo, t.tinhTrang " +
                "from tam_vang t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "where (t.tuNgay <= ?) and (t.denNgay => ?)" +
                "and n.maHoKhau ='" + maHoKhau + "'";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(den.getTime()));
            statement.setDate(2, new java.sql.Date(tu.getTime()));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TamVangDisplayModel tmp = new TamVangDisplayModel(rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"), rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"), rs.getDate("tuNgay"),
                        rs.getDate("denNgay"), rs.getNString("lyDo"),
                        rs.getNString("tinhTrang"));
                listTamVang.add(tmp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamVang;
    }

    public ObservableList<TamVangModel> getTamVangWhereTuNgayBetween(Date low, Date high) {
        ObservableList<TamVangModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_vang where tuNgay between ? and ?";
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTamVang;
    }

    public ObservableList<TamVangModel> getTamVangWhereDenNgayBetween(Date low, Date high) {
        ObservableList<TamVangModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_vang where denNgay between ? and ?";
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTamVang;
    }

    public ObservableList<TamVangModel> getTamVangByidNguoiThucHien(int id) {
        ObservableList<TamVangModel> listTamVang = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from tam_vang where idNguoiThucHien = '" + id + "'";
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTamVang;
    }

    public Optional<TamVangModel> getTamVangByMaTamVang(int maTamVang) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tamVangModel;
    }

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
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTamVang(TamVangModel tamVangModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from tam_vang where maTamVang = '" + tamVangModel.getMaTamVang() + "'";
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

    public boolean deleteTamVang(TamVangDisplayModel tamVangDisplayModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from tam_vang where maTamVang = '" + tamVangDisplayModel.getMaTamVang() + "'";
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

    public int getTamVangCount() {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select count(*) from tam_vang where tinhTrang = N'Đã xác nhận'";
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

    public TamVangModel convertDisplayModelToModel(TamVangDisplayModel tamVangDisplayModel) {
        TamVangModel tamVangModel = getTamVangByMaTamVang(tamVangDisplayModel.getMaTamVang()).get();
        return new TamVangModel(tamVangDisplayModel.getMaTamVang(), tamVangDisplayModel.getMaNhanKhau(),
                tamVangDisplayModel.getNoiTamVang(), tamVangDisplayModel.getTuNgay(), tamVangDisplayModel.getDenNgay(),
                tamVangDisplayModel.getLyDo(), tamVangDisplayModel.getTinhTrang(), tamVangModel.getIdNguoiThucHien());
    }

    public ObservableList<TamVangDisplayModel> getTamVangByMaChuHo(String maChuHo) {
        ObservableList<TamVangDisplayModel> tamVangDisplayModels = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select t.maTamVang, t.maNhanKhau, hoTen, noiTamVang, tuNgay, denNgay, lyDo, t.tinhTrang " +
                "from tam_vang t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "where n.maHoKhau ='" + maChuHo + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TamVangDisplayModel tmp = new TamVangDisplayModel(rs.getInt("maTamVang"),
                        rs.getString("maNhanKhau"), rs.getNString("hoTen"),
                        rs.getNString("noiTamVang"), rs.getDate("tuNgay"),
                        rs.getDate("denNgay"), rs.getNString("lyDo"),
                        rs.getNString("tinhTrang"));
                tamVangDisplayModels.add(tmp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tamVangDisplayModels;
    }

    public int countTamVangByMaHoKhau(String maHoKhau) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select count(*) from tam_vang t, nhan_khau n where t.maNhanKhau = n.maNhanKhau " +
                "and n.maHoKhau ='" + maHoKhau + "' ";
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


}