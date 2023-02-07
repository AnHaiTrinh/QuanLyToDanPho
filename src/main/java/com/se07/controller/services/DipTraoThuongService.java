package com.se07.controller.services;

import com.se07.model.models.DipTraoThuongModel;
import com.se07.model.models.UserModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;

public class DipTraoThuongService {
    public ObservableList<DipTraoThuongModel> getAllDipTraoThuong() {
        ObservableList<DipTraoThuongModel> listDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from dip_trao_thuong";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                DipTraoThuongModel temp = new DipTraoThuongModel(rs.getInt("id"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getDate("ngayTao"),
                        rs.getDate("ngayKetThuc"),
                        rs.getNString("kieu"),
                        rs.getNString("ghiChu"));
                listDipTraoThuong.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDipTraoThuong;
    }

    public ObservableList<DipTraoThuongModel> getDipTraoThuongByTen(String tenDip) {
        ObservableList<DipTraoThuongModel> listDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from dip_trao_thuong where tenDip like N'%" + tenDip + "%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                DipTraoThuongModel temp = new DipTraoThuongModel(rs.getInt("id"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getDate("ngayTao"),
                        rs.getDate("ngayKetThuc"),
                        rs.getNString("kieu"),
                        rs.getNString("ghiChu"));
                listDipTraoThuong.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDipTraoThuong;
    }

    public ObservableList<DipTraoThuongModel> getAllDipTraoThuongByKieu(String kieu) {
        ObservableList<DipTraoThuongModel> listDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from dip_trao_thuong where kieu = N'" + kieu + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                DipTraoThuongModel temp = new DipTraoThuongModel(rs.getInt("id"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getDate("ngayTao"),
                        rs.getDate("ngayKetThuc"),
                        rs.getNString("kieu"),
                        rs.getNString("ghiChu"));
                listDipTraoThuong.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDipTraoThuong;
    }

    public ObservableList<DipTraoThuongModel> getAllDipTraoThuongByNam(int nam) {
        ObservableList<DipTraoThuongModel> listDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from dip_trao_thuong where nam = " + nam;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                DipTraoThuongModel temp = new DipTraoThuongModel(rs.getInt("id"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getDate("ngayTao"),
                        rs.getDate("ngayKetThuc"),
                        rs.getNString("kieu"),
                        rs.getNString("ghiChu"));
                listDipTraoThuong.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDipTraoThuong;
    }

    public ObservableList<DipTraoThuongModel> getAllDipTraoThuongNgayTaoBetween(Date tu, Date den) {
        ObservableList<DipTraoThuongModel> listDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from dip_trao_thuong where ngayTao between ? and ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(tu.getTime()));
            statement.setDate(2, new java.sql.Date(den.getTime()));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                DipTraoThuongModel temp = new DipTraoThuongModel(rs.getInt("id"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getDate("ngayTao"),
                        rs.getDate("ngayKetThuc"),
                        rs.getNString("kieu"),
                        rs.getNString("ghiChu"));
                listDipTraoThuong.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDipTraoThuong;
    }

    public ObservableList<DipTraoThuongModel> getAllDipTraoThuongNgayKetThucBetween(Date tu, Date den) {
        ObservableList<DipTraoThuongModel> listDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from dip_trao_thuong where ngayKetThuc between ? and ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(tu.getTime()));
            statement.setDate(2, new java.sql.Date(den.getTime()));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                DipTraoThuongModel temp = new DipTraoThuongModel(rs.getInt("id"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getDate("ngayTao"),
                        rs.getDate("ngayKetThuc"),
                        rs.getNString("kieu"),
                        rs.getNString("ghiChu"));
                listDipTraoThuong.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDipTraoThuong;
    }

    public boolean updateDipTraoThuong(DipTraoThuongModel dipTraoThuongModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update dip_trao_thuong " +
                "set tenDip = ?, " +
                "nam = ?, " +
                "ngayTao = ?, " +
                "ngayKetThuc = ?, " +
                "kieu = ?, " +
                "ghiChu = ? " +
                "where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNString(1, dipTraoThuongModel.getTenDip());
            statement.setInt(2, dipTraoThuongModel.getNam());
            statement.setDate(3, new java.sql.Date(dipTraoThuongModel.getNgayTao().getTime()));
            statement.setDate(4, new java.sql.Date(dipTraoThuongModel.getNgayKetThuc().getTime()));
            statement.setNString(5, dipTraoThuongModel.getKieu());
            statement.setNString(6, dipTraoThuongModel.getGhiChu());
            statement.setInt(7, dipTraoThuongModel.getId());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDipTraoThuong(DipTraoThuongModel dipTraoThuongModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from dip_trao_thuong where id = " + dipTraoThuongModel.getId();
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

    public boolean addDipTraoThuong(DipTraoThuongModel dipTraoThuongModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into dip_trao_thuong(tenDip, nam, ngayTao, ngayKetThuc, kieu, ghiChu) " +
                "values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNString(1, dipTraoThuongModel.getTenDip());
            statement.setInt(2, dipTraoThuongModel.getNam());
            statement.setDate(3, new java.sql.Date(dipTraoThuongModel.getNgayTao().getTime()));
            statement.setDate(4, new java.sql.Date(dipTraoThuongModel.getNgayKetThuc().getTime()));
            statement.setNString(5, dipTraoThuongModel.getKieu());
            statement.setNString(6, dipTraoThuongModel.getGhiChu());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<String> getAllTenTraoThuongThanhTich() {
        ObservableList<String> listTenDipThanhTich = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select distinct tenDip from dip_trao_thuong where kieu = N'Thành tích'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                listTenDipThanhTich.add(rs.getString("tenDip"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTenDipThanhTich;
    }

    public ObservableList<String> getAllTenTraoThuongDipDacBiet() {
        ObservableList<String> listTenDipThanhTich = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select distinct tenDip from dip_trao_thuong where kieu = N'Dịp đặc biệt'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                listTenDipThanhTich.add(rs.getString("tenDip"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTenDipThanhTich;
    }

    public ObservableList<Integer> getAllNamTraoThuongDipDacBiet() {
        ObservableList<Integer> listNamThanhTich = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select distinct nam from dip_trao_thuong where kieu = N'Dịp đặc biệt' order by nam desc";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                listNamThanhTich.add(rs.getInt("nam"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNamThanhTich;
    }

    public ObservableList<Integer> getAllNamTraoThuongThanhTich() {
        ObservableList<Integer> listNamThanhTich = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select distinct nam from dip_trao_thuong where kieu = N'Thành tích' order by nam desc";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                listNamThanhTich.add(rs.getInt("nam"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNamThanhTich;
    }

    public Optional<DipTraoThuongModel> getDipTraoThuongByTenAndNam(String tenDip, int nam) {
        Optional<DipTraoThuongModel> dipTraoThuongModel = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from dip_trao_thuong where tenDip = ? and nam = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNString(1, tenDip);
            statement.setInt(2, nam);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                DipTraoThuongModel temp = new DipTraoThuongModel(
                        rs.getInt("id"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getDate("ngayTao"),
                        rs.getDate("ngayKetThuc"),
                        rs.getNString("kieu"),
                        rs.getNString("ghiChu"));
                dipTraoThuongModel = Optional.of(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dipTraoThuongModel;
    }

    public ObservableList<Integer> getNamByTenDipThanhTich(String tenDip) {
        ObservableList<Integer> listNamThanhTich = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select distinct nam from dip_trao_thuong where kieu = N'Thành tích' and tenDip = N'" + tenDip + "' " +
                "order by nam desc";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                listNamThanhTich.add(rs.getInt("nam"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNamThanhTich;
    }

    public ObservableList<String> getTenDipThanhTichByNam(int nam) {
        ObservableList<String> listTenDipThanhTich = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select distinct tenDip from dip_trao_thuong where kieu = N'Thành tích' and nam = " + nam;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                listTenDipThanhTich.add(rs.getString("tenDip"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTenDipThanhTich;
    }

    public ObservableList<Integer> getNamByTenDipDacBiet(String tenDip) {
        ObservableList<Integer> listNamThanhTich = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select distinct nam from dip_trao_thuong where kieu = N'Dịp đặc biệt' and tenDip = N'" + tenDip + "' " +
                "order by nam desc";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                listNamThanhTich.add(rs.getInt("nam"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNamThanhTich;
    }

    public ObservableList<String> getTenDipDacBietByNam(int nam) {
        ObservableList<String> listTenDipThanhTich = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select distinct tenDip from dip_trao_thuong where kieu = N'Dịp đặc biệt' and nam = " + nam;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                listTenDipThanhTich.add(rs.getString("tenDip"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTenDipThanhTich;
    }

    public ObservableList<String> getAllTenNamDipTraoThuong() {
        ObservableList<String> listTenNamDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select tenDip, nam from dip_trao_thuong order by nam desc";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String temp = rs.getNString("tenDip") + " - " + rs.getInt("nam");
                listTenNamDipTraoThuong.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTenNamDipTraoThuong;
    }

    public ObservableList<String> getAllTenNamDipTraoThuongThanhTich() {
        ObservableList<String> listTenNamDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select tenDip, nam from dip_trao_thuong " +
                "where kieu = N'Thành tích' " +
                "order by nam desc";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String temp = rs.getNString("tenDip") + " - " + rs.getInt("nam");
                listTenNamDipTraoThuong.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTenNamDipTraoThuong;
    }

    public ObservableList<String> getAllTenNamDipDacBiet() {
        ObservableList<String> listTenNamDipTraoThuong = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select tenDip, nam from dip_trao_thuong " +
                "where kieu = N'Dịp đặc biệt' " +
                "order by nam desc";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String temp = rs.getNString("tenDip") + " - " + rs.getInt("nam");
                listTenNamDipTraoThuong.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTenNamDipTraoThuong;
    }
}