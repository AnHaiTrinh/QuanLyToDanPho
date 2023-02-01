package com.se07.controller.services;

import com.se07.model.models.NhanKhauModel;
import com.se07.util.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Optional;

/**
 * Lớp cung cấp các phương thức truy vấn đến cơ sở dữ liệu liên quan đến nhân khẩu
 */
public class NhanKhauService {
    /**
     * @return Danh sách tất cả các nhân khẩu
     */
    public ObservableList<NhanKhauModel> getAllNhanKhau() {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }

    /**
     * @param maHoKhau Mã hộ khẩu muốn tìm kiếm
     * @return Danh sách nhân khẩu trong hộ khẩu có mã hộ khẩu như đầu vào
     */
    public ObservableList<NhanKhauModel> getAllNhanKhauTrongHoKhau(String maHoKhau) {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where maHoKhau = '" + maHoKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }

    /**
     * @param tenNhanKhau Họ tên nhân khẩu muốn tìm kiếm
     * @return Danh sách nhân khẩu có họ tên giống đầu vào
     */
    public ObservableList<NhanKhauModel> getAllNhanKhauByTen(String tenNhanKhau) {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where hoTen like N'%" + tenNhanKhau + "%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }

    /**
     * @param bietDanh Biệt danh muốn tìm kiếm
     * @return Danh sách các nhân khẩu có biệt danh giống như đầu vào
     */
    public ObservableList<NhanKhauModel> getAllNhanKhauByBietDanh(String bietDanh) {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where bietDanh like N'%" + bietDanh + "%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }

    /**
     * @param tinhTrang Tình trạng muốn tìm kiếm
     * @return Danh sách các nhân khẩu có tình trạng như đầu vào
     */
    public ObservableList<NhanKhauModel> getAllNhanKhauByTinhTrang(String tinhTrang) {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where tinhTrang = N'" + tinhTrang + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }

    /**
     * @param maNhanKhau Mã nhân khẩu muốn tìm kiếm
     * @return Nhân khẩu theo mã nhân khẩu nếu có, ngược lại trả về {@code Optinal.empty()}
     */
    public Optional<NhanKhauModel> getNhanKhauByMaNhanKhau(String maNhanKhau) {
        Optional<NhanKhauModel> nhanKhauModel = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where maNhanKhau = '" + maNhanKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                nhanKhauModel = Optional.of(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanKhauModel;
    }

    /**
     * Phương thức thêm mới nhân khẩu vào cơ sở dữ liệu
     *
     * @param nhanKhauModel Đối tượng chứa thông tin nhân khẩu muốn thêm mới
     * @return {@code true} nếu thêm thành công ngược lại trả về {@code false}
     */
    public boolean addNhanKhau(NhanKhauModel nhanKhauModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "insert into nhan_khau(maNhanKhau, maHoKhau, hoTen, bietdanh, ngaySinh, gioiTinh, tonGiao, " +
                "tinhTrang, idNguoiThucHien) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nhanKhauModel.getMaNhanKhau());
            statement.setString(2, nhanKhauModel.getMaHoKhau());
            statement.setNString(3, nhanKhauModel.getHoTen());
            statement.setNString(4, nhanKhauModel.getBietDanh());
            statement.setDate(5, new Date(nhanKhauModel.getNgaySinh().getTime()));
            statement.setNString(6, nhanKhauModel.getGioiTinh());
            statement.setNString(7, nhanKhauModel.getTonGiao());
            statement.setNString(8, nhanKhauModel.getTinhTrang());
            statement.setInt(9, nhanKhauModel.getIdNguoiThucHien());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Phương thức cập nhật nhân khẩu dựa vào mã nhân khẩu
     *
     * @param nhanKhauModel Đối tượng chứa thông tin nhân khẩu muốn thay đổi
     * @return {@code true} nếu cập nhật thành công ngược lại trả về {@code false}
     */
    public boolean updateNhanKhau(NhanKhauModel nhanKhauModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "update nhan_khau set maHoKhau = ?, hoTen = ?, bietdanh = ?, ngaySinh = ?, gioiTinh = ?, " +
                "tonGiao = ?, tinhTrang = ?, idNguoiThucHien = ? where maNhanKhau = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nhanKhauModel.getMaHoKhau());
            statement.setNString(2, nhanKhauModel.getHoTen());
            statement.setNString(3, nhanKhauModel.getBietDanh());
            statement.setDate(4, new Date(nhanKhauModel.getNgaySinh().getTime()));
            statement.setNString(5, nhanKhauModel.getGioiTinh());
            statement.setNString(6, nhanKhauModel.getTonGiao());
            statement.setNString(7, nhanKhauModel.getTinhTrang());
            statement.setInt(8, nhanKhauModel.getIdNguoiThucHien());
            statement.setString(9, nhanKhauModel.getMaNhanKhau());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Phương thức xóa nhân khẩu dựa vào mã nhân khẩu
     *
     * @param nhanKhauModel Đối tượng chứa thông tin nhân khẩu muốn xóa
     * @return {@code true} nếu xóa thành công ngược lại trả về {@code false}
     */
    public boolean deleteNhanKhau(NhanKhauModel nhanKhauModel) {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "delete from nhan_khau where maNhanKhau = '" + nhanKhauModel.getMaNhanKhau() + "'";
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
     * @return Số lượng nhân khẩu đã xác nhận
     */
    public int getNhanKhauCount() {
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select count(*) from nhan_khau where tinhTrang = N'Đã xác nhận'";
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
     * @return Danh sách tất cả mã nhân khẩu
     */
    public ObservableList<String> getAllMaNhanKhau() {
        ObservableList<String> listMaNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select maNhanKhau from nhan_khau";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                listMaNhanKhau.add(rs.getString(1));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMaNhanKhau;
    }

    /**
     * @param tu  Ngày đầu
     * @param den Ngày cuối
     * @return Danh sách nhân khẩu có ngày sinh từ ngày {@code tu} đến ngày {@code den}
     */
    public ObservableList<NhanKhauModel> getNhanKhauByNgaySinhBetween(java.util.Date tu, java.util.Date den) {
        ObservableList<NhanKhauModel> nhanKhauModelObservableList = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where ngaySinh between ? and ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(tu.getTime()));
            statement.setDate(2, new java.sql.Date(den.getTime()));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                nhanKhauModelObservableList.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanKhauModelObservableList;
    }

    /**
     * @param gioiTinh Giới tính muốn tìm kiếm
     * @return Danh sách nhân khẩu có giới tính như đầu vào
     */
    public ObservableList<NhanKhauModel> getNhanKhauByGioiTinh(String gioiTinh) {
        ObservableList<NhanKhauModel> nhanKhauModelObservableList = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where gioiTinh = N'" + gioiTinh + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                nhanKhauModelObservableList.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanKhauModelObservableList;
    }

    /**
     * @param tonGiao Tôn giáo muốn tìm kiếm. Nếu để trống sẽ tìm tất cả nhân khẩu không có tôn giáo
     * @return Danh sách nhân khẩu có tôn giáo giống đầu vào
     */
    public ObservableList<NhanKhauModel> getNhanKhauByTonGiao(String tonGiao) {
        ObservableList<NhanKhauModel> nhanKhauModelObservableList = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query;
        if (tonGiao.isBlank()) {
            query = "select * from nhan_khau where tonGiao is null or tonGiao = N''";
        } else {
            query = "select * from nhan_khau where tonGiao like N'%" + tonGiao + "%'";
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                nhanKhauModelObservableList.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanKhauModelObservableList;
    }
    public ObservableList<String> getAllMaNhanKhauTrongHoKhau(String maHoKhau) {
        ObservableList<String> listMaNhanKhauTrongHoKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where maHoKhau = '" + maHoKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String ans = rs.getString("maNhanKhau");
                listMaNhanKhauTrongHoKhau.add(ans);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMaNhanKhauTrongHoKhau;
    }



    public Optional<NhanKhauModel> getNhanKhauByMaNhanKhauAndMaHoKhau(String maNhanKhau, String maHoKhau) {
        Optional<NhanKhauModel> nhanKhauModel = Optional.empty();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where maNhanKhau = '" + maNhanKhau + "'" +
                "and maHoKhau ='"+maHoKhau+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                nhanKhauModel = Optional.of(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanKhauModel;
    }

    public ObservableList<NhanKhauModel> getAllNhanKhauByTenAndMaHoKhau(String tenNhanKhau, String maHoKhau) {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where hoTen like N'%" + tenNhanKhau + "%'" +
                "and maHoKhau ='"+maHoKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }

    public ObservableList<NhanKhauModel> getAllNhanKhauByBietDanhAndMaHoKhau(String bietDanh, String maHoKhau) {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where bietDanh like N'%" + bietDanh + "%'" +
                "and maHoKhau ='"+maHoKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }

    public ObservableList<NhanKhauModel> getNhanKhauByTonGiaoAndMaHoKhau(String tonGiao, String maHoKhau) {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where tonGiao like N'%" + tonGiao + "%'" +
                "and maHoKhau ='"+maHoKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }

    public ObservableList<NhanKhauModel> getAllNhanKhauByTinhTrangAndMaHoKhau(String tinhTrang, String maHoKhau) {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where tinhTrang = N'" + tinhTrang + "'" +
                "and maHoKhau ='"+maHoKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }

    public ObservableList<NhanKhauModel> getNhanKhauByNoiTamVangAndMaHoKhau(String noiTamVang, String maHoKhau) {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau join tam_vang on tam_vang.maNhanKhau = nhan_khau.maNhanKhau" +
                "where noiTamVang like N'%" + noiTamVang + "%'" +
                "and maHoKhau ='"+maHoKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }

    public ObservableList<NhanKhauModel> getAllNhanKhauByNgaySinhAndMaHoKhau(java.util.Date ngaySinh, String maHoKhau) {
        ObservableList<NhanKhauModel> nhanKhauModelObservableList = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where ngaySinh = ? and maHoKhau ='"+maHoKhau+"'";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(ngaySinh.getTime()));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                nhanKhauModelObservableList.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanKhauModelObservableList;
    }

    public ObservableList<NhanKhauModel> getAllNhanKhauByGioiTinhAndMaHoKhau(String gioiTinh, String maHoKhau) {
        ObservableList<NhanKhauModel> listNhanKhau = FXCollections.observableArrayList();
        Connection connection = ConnectionDatabase.getConnection();
        String query = "select * from nhan_khau where gioiTinh = N'" + gioiTinh + "'" +
                "and maHoKhau ='"+maHoKhau + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                NhanKhauModel temp = new NhanKhauModel(rs.getString("maNhanKhau"),
                        rs.getString("maHoKhau"), rs.getNString("hoTen"),
                        rs.getNString("bietDanh"), rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"), rs.getNString("tonGiao"),
                        rs.getNString("tinhTrang"), rs.getInt("idNguoiThucHien"));
                listNhanKhau.add(temp);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanKhau;
    }











}