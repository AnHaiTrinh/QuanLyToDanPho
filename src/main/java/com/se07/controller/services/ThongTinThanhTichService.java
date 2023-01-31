package com.se07.controller.services;

import com.se07.model.models.ThongTinThanhTichDisplayModel;
import com.se07.model.models.ThongTinThanhTichModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.Optional;

public class ThongTinThanhTichService {
    public boolean addThongTinThanhTich(ThongTinThanhTichModel thongTinThanhTichModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into thong_tin_thanh_tich(idDip, maNhanKhau, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, tinhTrang, idNguoiThucHien) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, thongTinThanhTichModel.getIdDip());
            statement.setString(2, thongTinThanhTichModel.getMaNhanKhau());
            statement.setInt(3, thongTinThanhTichModel.getLop());
            statement.setNString(4, thongTinThanhTichModel.getTruong());
            statement.setNString(5, thongTinThanhTichModel.getCapThanhTich());
            statement.setNString(6, thongTinThanhTichModel.getKieuThanhTich());
            statement.setBinaryStream(7, new FileInputStream(thongTinThanhTichModel.getMinhChung()));
            statement.setNString(8, thongTinThanhTichModel.getTinhTrang());
            statement.setInt(9, thongTinThanhTichModel.getIdNguoiThucHien());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateThongTinThanhTich(ThongTinThanhTichModel thongTinThanhTichModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update thong_tin_thanh_tich " +
                "set maNhanKhau = ?, " +
                "lop = ?, " +
                "truong = ?, " +
                "capThanhTich = ?, " +
                "kieuThanhTich = ?, " +
                "minhChung = ?, " +
                "tinhTrang = ?, " +
                "idNguoiThucHien = ? " +
                "where idNhap = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, thongTinThanhTichModel.getMaNhanKhau());
            statement.setInt(2, thongTinThanhTichModel.getLop());
            statement.setNString(3, thongTinThanhTichModel.getTruong());
            statement.setNString(4, thongTinThanhTichModel.getCapThanhTich());
            statement.setNString(5, thongTinThanhTichModel.getKieuThanhTich());
            statement.setBinaryStream(6, new FileInputStream(thongTinThanhTichModel.getMinhChung()));
            statement.setNString(7, thongTinThanhTichModel.getTinhTrang());
            statement.setInt(8, thongTinThanhTichModel.getIdNguoiThucHien());
            statement.setInt(9, thongTinThanhTichModel.getIdNhap());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ThongTinThanhTichModel convertDisplayModelToModel(ThongTinThanhTichDisplayModel thongTinThanhTichDisplayModel) {
        ThongTinThanhTichModel thongTinThanhTichModel = getThongTinThanhTichByIdNhap(thongTinThanhTichDisplayModel.getIdNhap()).get();
        return new ThongTinThanhTichModel(thongTinThanhTichDisplayModel.getIdNhap(), thongTinThanhTichModel.getIdDip(),
                thongTinThanhTichDisplayModel.getMaNhanKhau(), thongTinThanhTichDisplayModel.getLop(),
                thongTinThanhTichDisplayModel.getTruong(), thongTinThanhTichDisplayModel.getCapThanhTich(),
                thongTinThanhTichDisplayModel.getKieuThanhTich(), thongTinThanhTichDisplayModel.getMinhChung(),
                thongTinThanhTichDisplayModel.getTinhTrang(), thongTinThanhTichModel.getIdNguoiThucHien());
    }

    public Optional<ThongTinThanhTichModel> getThongTinThanhTichByIdNhap(int ìdNhap) {
        Optional<ThongTinThanhTichModel> thongTinThanhTichModel = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from thong_tin_thanh_tich where idNhap = " + ìdNhap;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");
                ThongTinThanhTichModel temp = new ThongTinThanhTichModel(
                        idNhap,
                        idDip,
                        rs.getString("maNhanKhau"),
                        rs.getInt("lop"),
                        rs.getNString("truong"),
                        rs.getNString("capThanhTich"),
                        rs.getNString("kieuThanhTich"),
                        new File("src/main/resources/" + idDip + "_" + idNhap + ".jpg"),
                        rs.getNString("tinhTrang"),
                        rs.getInt("idNguoiThucHien")
                );
                thongTinThanhTichModel = Optional.of(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thongTinThanhTichModel;
    }

    public boolean deleteThongTinThanhTich(ThongTinThanhTichDisplayModel thongTinThanhTichDisplayModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from thong_tin_thanh_tich where idNhap = " + thongTinThanhTichDisplayModel.getIdNhap();
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

    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTich() {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File("src/main/resources/" + idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                }
                ThongTinThanhTichDisplayModel temp = new ThongTinThanhTichDisplayModel(
                        idNhap,
                        rs.getString("maNhanKhau"),
                        rs.getNString("hoTen"),
                        rs.getNString("tenDip"),
                        rs.getInt("nam"),
                        rs.getInt("lop"),
                        rs.getNString("truong"),
                        rs.getNString("capThanhTich"),
                        rs.getNString("kieuThanhTich"),
                        file,
                        rs.getNString("tinhTrang"));
                listThongTinThanhTichDisplayModel.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listThongTinThanhTichDisplayModel;
    }
}
