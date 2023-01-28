package com.se07.controller.services;

import com.se07.model.models.ThongTinThanhTichModel;
import com.se07.util.ConnectionDatabase;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
