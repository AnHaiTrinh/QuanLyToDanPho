package com.se07.controller.services;

import com.se07.model.models.ThongTinThanhTichDisplayModel;
import com.se07.model.models.ThongTinThanhTichModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.Map;
import java.util.Optional;

public class ThongTinThanhTichService {


    /**
     * @param thongTinThanhTichModel
     * @return thêm một bản ghi thông tin thành tích
     */
    public boolean addThongTinThanhTich(ThongTinThanhTichModel thongTinThanhTichModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into thong_tin_thanh_tich(idDip, maNhanKhau, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, tinhTrang, idNguoiThucHien) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        FileInputStream minhChung = null;
        try {
            minhChung = new FileInputStream(thongTinThanhTichModel.getMinhChung());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, thongTinThanhTichModel.getIdDip());
            statement.setString(2, thongTinThanhTichModel.getMaNhanKhau());
            statement.setInt(3, thongTinThanhTichModel.getLop());
            statement.setNString(4, thongTinThanhTichModel.getTruong());
            statement.setNString(5, thongTinThanhTichModel.getCapThanhTich());
            statement.setNString(6, thongTinThanhTichModel.getKieuThanhTich());
            statement.setBinaryStream(7, minhChung);
            statement.setNString(8, thongTinThanhTichModel.getTinhTrang());
            statement.setInt(9, thongTinThanhTichModel.getIdNguoiThucHien());
            statement.executeUpdate();
            statement.close();
            minhChung.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param thongTinThanhTichModel
     * @return cập nhật một bản ghi thông tin thành tích
     */
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
        FileInputStream minhChung = null;
        try {
            minhChung = new FileInputStream(thongTinThanhTichModel.getMinhChung());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, thongTinThanhTichModel.getMaNhanKhau());
            statement.setInt(2, thongTinThanhTichModel.getLop());
            statement.setNString(3, thongTinThanhTichModel.getTruong());
            statement.setNString(4, thongTinThanhTichModel.getCapThanhTich());
            statement.setNString(5, thongTinThanhTichModel.getKieuThanhTich());
            statement.setBinaryStream(6, minhChung);
            statement.setNString(7, thongTinThanhTichModel.getTinhTrang());
            statement.setInt(8, thongTinThanhTichModel.getIdNguoiThucHien());
            statement.setInt(9, thongTinThanhTichModel.getIdNhap());
            statement.executeUpdate();
            statement.close();
            minhChung.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param thongTinThanhTichDisplayModel
     * @return chuyển từ dạng ThongTinThanhTichDisplayModel sang ThongTinThanhTichModel
     */

    public ThongTinThanhTichModel convertDisplayModelToModel(ThongTinThanhTichDisplayModel thongTinThanhTichDisplayModel) {
        ThongTinThanhTichModel thongTinThanhTichModel = getThongTinThanhTichByIdNhap(thongTinThanhTichDisplayModel.getIdNhap()).get();
        return new ThongTinThanhTichModel(thongTinThanhTichDisplayModel.getIdNhap(), thongTinThanhTichModel.getIdDip(),
                thongTinThanhTichDisplayModel.getMaNhanKhau(), thongTinThanhTichDisplayModel.getLop(),
                thongTinThanhTichDisplayModel.getTruong(), thongTinThanhTichDisplayModel.getCapThanhTich(),
                thongTinThanhTichDisplayModel.getKieuThanhTich(), thongTinThanhTichDisplayModel.getMinhChung(),
                thongTinThanhTichDisplayModel.getTinhTrang(), thongTinThanhTichModel.getIdNguoiThucHien());
    }

    /**
     * @param ìdNhap
     * @return bản ghi thông tin thành tích theo id nhập
     */

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
                        new File(idDip + "_" + idNhap + ".jpg"),
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

    /**
     * @param thongTinThanhTichDisplayModel
     * @return xóa một bản ghi thông tin thành tích
     */

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

    /**
     * @return tất cả bản ghi thông tin thành tích
     */

    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTich() {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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

    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTichAndHoKhau(String maHoKhau) {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id " +
                "and n.maHoKhau='"+maHoKhau+"' ";
        System.out.println(query);
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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

    /**
     * @param maNhanKhau
     * @return các bản ghi thông tin thành tích theo mã nhân khẩu
     */
    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTichByMaNhanKhau(String maNhanKhau) {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id  " +
                "where t.maNhanKhau = '" + maNhanKhau + "' ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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

    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTichByMaNhanKhauAndHoKhau(String maNhanKhau, String maHoKhau) {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id  " +
                "where t.maNhanKhau = '" + maNhanKhau + "' " +
                "where t.maNhanKhau= '"+maHoKhau+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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

    /**
     * @param hoTen
     * @return các bản ghi thông tin thành tích theo họ tên
     */
    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTichByHoTen(String hoTen) {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id " +
                "where hoTen like N'%" + hoTen + "%' ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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

    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTichByHoTenAndHoKhau(String hoTen, String maHoKhau) {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id " +
                "where hoTen like N'%" + hoTen + "%' " +
                "and n.maHoKhau ='"+maHoKhau+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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

    /**
     * @param tenDip
     * @return các bản ghi thông tin thành tích theo tên dịp
     */
    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTichByTenDip(String tenDip) {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id " +
                "where tenDip like N'%" + tenDip + "%' ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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

    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTichByTenDipAndHoKhau(String tenDip, String maHoKhau) {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id " +
                "where tenDip like N'%" + tenDip + "%'" +
                "and n.maHoKhau='"+maHoKhau+"' ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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

    /**
     * @param nam
     * @return các bản ghi thông tin thành tích theo năm
     */

    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTichByNam(int nam) {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id " +
                "where nam =  '" + nam + "' ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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

    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTichByNamAndHoKhau(int nam, String maHoKhau) {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id " +
                "where nam =  '" + nam + "' " +
                "and n.maHoKhau = '"+maHoKhau+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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

    /**
     * @param lop
     * @return các bản ghi thông tin thành tích theo lớp
     */

    /**
     * @param capThanhTich
     * @return bản ghi thông tin thành tích theo cấp thành tích
     */
    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTichByCapThanhTich(String capThanhTich) {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id " +
                "where capThanhTich like  N'%" + capThanhTich + "%' ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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


    /**
     * @param kieuThanhTich
     * @return các bản ghi thông tin thành tích theo kiểu thành tích
     */
    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTichByKieuThanhTich(String kieuThanhTich) {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id " +
                "where kieuThanhTich like  N'%" + kieuThanhTich + "%' ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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

    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTichByTinhTrang(String tinhTrang) {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id " +
                "where kieuThanhTich =  N'" + tinhTrang + "' ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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

    public ObservableList<ThongTinThanhTichDisplayModel> getAllThongTinThanhTichByTinhTrangAndHoKhau(String tinhTrang, String maHoKhau) {
        ObservableList<ThongTinThanhTichDisplayModel> listThongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id " +
                "where kieuThanhTich =  N'" + tinhTrang + "' " +
                "and n.maHoKhau = '"+maHoKhau+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");

                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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

    public ObservableList<ThongTinThanhTichDisplayModel> getThongTinThanhTichByIdDip(int id) {
        ObservableList<ThongTinThanhTichDisplayModel> thongTinThanhTichDisplayModel = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select idNhap, t.idDip, t.maNhanKhau, hoTen, tenDip, nam, lop, truong, capThanhTich, kieuThanhTich, " +
                "minhChung, t.tinhTrang " +
                "from thong_tin_thanh_tich t join nhan_khau n on t.maNhanKhau = n.maNhanKhau " +
                "join dip_trao_thuong d on t.idDip = d.id " +
                "where idDip = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int idNhap = rs.getInt("idNhap");
                int idDip = rs.getInt("idDip");
                File file = new File(idDip + "_" + idNhap + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    Blob blob = rs.getBlob("minhChung");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    fos.write(data);
                    fos.close();
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
                thongTinThanhTichDisplayModel.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thongTinThanhTichDisplayModel;
    }
}
