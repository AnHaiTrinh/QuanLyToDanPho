-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               Microsoft SQL Server 2022 (RTM) - 16.0.1000.6
-- Server OS:                    Linux (Ubuntu 20.04.5 LTS) <X64>
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for se07
USE se07;
use se07;
-- Dumping structure for table se07.users
CREATE TABLE  users (
                        ID INT NOT NULL,
                        username VARCHAR(30) NOT NULL ,
                        password VARCHAR(30) NOT NULL ,
                        role INT NOT NULL,
                        PRIMARY KEY (ID),
                        CONSTRAINT chk_role CHECK (([role]=(2) OR [role]=(1) OR [role]=(0)))
);

-- Dumping data for table se07.users: -1 rows
/*!40000 ALTER TABLE users DISABLE KEYS */;
INSERT INTO users (ID, username, password, role) VALUES
                                                     (1, 'admin', '123456', 1),
                                                     (2, 'hogiadinh1', '111111', 2),
                                                     (3, 'thuquy', 'password', 0),
                                                     (4, 'hogiadinh2', '666', 2),
                                                     (5, 'hogiadinh3', '1den9', 2),
                                                     (6, 'hogiadinh4', 'psswd', 2);

/*!40000 ALTER TABLE ho_gia_dinh_user ENABLE KEYS */;

-- Dumping structure for table se07.ho_khau
CREATE TABLE  ho_khau (
                          maHoKhau VARCHAR(30) NOT NULL ,
                          chuHo NVARCHAR(50) NOT NULL ,
                          diaChi NVARCHAR(100) NOT NULL ,
                          ngayLap DATE NOT NULL,
                          idNguoiThucHien INT NOT NULL,
                          PRIMARY KEY (maHoKhau),
                          CONSTRAINT ho_khau_FK1 FOREIGN KEY (idNguoiThucHien) REFERENCES users (ID)
);

/*!40000 ALTER TABLE ho_khau DISABLE KEYS */;
INSERT INTO ho_khau (maHoKhau, chuHo, diaChi, ngayLap, idNguoiThucHien) VALUES
                                                                            ('HK1', 'Trịnh Văn An', 'Số 1, Tạ Quang Bửu, Hai Bà trưng, Hà Nội', '2023-01-05', 1),
                                                                            ('HK2', 'Trần Thanh Duyên', 'Số 2, Tạ Quang Bửu, Hai Bà Trưng, Hà Nội', '2022-12-29', 1),
                                                                            ('HK3', 'Nguyễn Tiến Dũng', 'Số 4, Tạ Quang Bửu, Hai Bà Trưng, Hà Nội', '2023-01-03', 1),
                                                                            ('HK4', 'Nguyễn Tiến Đạt', 'Số 3, Tạ Quang Bửu, Hai Bà Trưng, Hà Nội', '2023-01-04', 5);
/*!40000 ALTER TABLE ho_khau ENABLE KEYS */;
-- Dumping structure for table se07.ho_gia_dinh_user
CREATE TABLE  ho_gia_dinh_user (
                                   ID INT NOT NULL,
                                   maHoKhau VARCHAR(30) NOT NULL ,

                                   PRIMARY KEY (ID, maHoKhau),
                                   CONSTRAINT ho_gia_dinh_user_FK1 FOREIGN KEY (ID) REFERENCES users (ID) ,
                                   CONSTRAINT ho_gia_dinh_user_FK2 FOREIGN KEY (maHoKhau) REFERENCES ho_khau (maHoKhau)
);

-- Dumping data for table se07.ho_gia_dinh_user: -1 rows
/*!40000 ALTER TABLE ho_gia_dinh_user DISABLE KEYS */;
INSERT INTO ho_gia_dinh_user (ID, maHoKhau) VALUES
                                                (2, 'HK1'),
                                                (4, 'HK2'),
                                                (5, 'HK3'),
                                                (6, 'HK4');
-- Dumping structure for table se07.khai_tu
CREATE TABLE  khai_tu (
                          soGiayKhaiTu VARCHAR(30) NOT NULL ,
                          tenNguoiKhai VARCHAR(30) NOT NULL ,
                          tenNguoiMat VARCHAR(30) NOT NULL ,
                          noiTamVang NVARCHAR(100) NOT NULL ,
                          ngayKhai DATE NOT NULL,
                          ngayChet DATE NOT NULL,
                          lydo NTEXT NOT NULL ,
                          tinhTrang NCHAR(15) NOT NULL DEFAULT N'Chờ xác nhận' ,
                          idNguoiThucHien INT NOT NULL,
                          PRIMARY KEY (soGiayKhaiTu),
                          CONSTRAINT khai_tu_FK1 FOREIGN KEY (idNguoiThucHien) REFERENCES users (ID)
);

-- Dumping data for table se07.khai_tu: -1 rows
/*!40000 ALTER TABLE khai_tu DISABLE KEYS */;
/*!40000 ALTER TABLE khai_tu ENABLE KEYS */;

-- Dumping structure for table se07.nhan_khau
CREATE TABLE  nhan_khau (
                            maNhanKhau VARCHAR(30) NOT NULL ,
                            maHoKhau VARCHAR(30) NOT NULL ,
                            hoTen NVARCHAR(50) NOT NULL ,
                            bietDanh NVARCHAR(50) NULL DEFAULT NULL ,
                            ngaySinh DATE NOT NULL,
                            gioiTinh NCHAR(3) NOT NULL ,
                            tonGiao NVARCHAR(20) NULL DEFAULT NULL ,
                            tinhTrang NCHAR(15) NOT NULL DEFAULT N'Chờ xác nhận' ,
                            idNguoiThucHien INT NOT NULL,

                            PRIMARY KEY (maNhanKhau),
                            CONSTRAINT nhan_khau_FK2 FOREIGN KEY (idNguoiThucHien) REFERENCES users (ID) ,
                            CONSTRAINT nhan_khau_FK1 FOREIGN KEY (maHoKhau) REFERENCES ho_khau (maHoKhau)
);

-- Dumping data for table se07.nhan_khau: -1 rows
/*!40000 ALTER TABLE nhan_khau DISABLE KEYS */;
INSERT INTO nhan_khau (maNhanKhau, maHoKhau, hoTen, bietDanh, ngaySinh, gioiTinh, tonGiao, tinhTrang, idNguoiThucHien) VALUES
                                                                                                                           ('NK1', 'HK1', 'Trịnh Văn An', NULL, '1974-12-07', 'Nam', NULL, 'Đã xác nhận    ', 1),
                                                                                                                           ('NK2', 'HK1', 'Trịnh Văn Quyết', NULL, '2000-04-05', 'Nam', NULL, 'Đã xác nhận    ', 2),
                                                                                                                           ('NK3', 'HK2', 'Trần Thanh Duyên', NULL, '1977-12-23', 'Nữ ', NULL, 'Đã xác nhận    ', 1),
                                                                                                                           ('NK4', 'HK2', 'Nguyễn Minh Quân', NULL, '1977-05-31', 'Nam', NULL, 'Đã xác nhận    ', 4),
                                                                                                                           ('NK5', 'HK3', 'Nguyễn Tiến Dũng', NULL, '1964-06-03', 'Nam', NULL, 'Đã xác nhận    ', 1),
                                                                                                                           ('NK6', 'HK4', 'Nguyễn Tiến Đạt', NULL, '1989-11-23', 'Nam', NULL, 'Đã xác nhận    ', 5),
                                                                                                                           ('NK7', 'HK4', 'Nguyễn Trà My', NULL, '1990-05-18', 'Nữ ', NULL, 'Đã xác nhận    ', 5),
                                                                                                                           ('NK8', 'HK4', 'Nguyễn Văn Nam', NULL, '2015-06-29', 'Nam', NULL, 'Đã xác nhận    ', 6),
                                                                                                                           ('NK9', 'HK4', 'Nguyễn Thị Huyền', NULL, '2018-04-13', 'Nữ ', NULL, 'Đã xác nhận    ', 6);
/*!40000 ALTER TABLE nhan_khau ENABLE KEYS */;

-- Dumping structure for table se07.phan_thuong
CREATE TABLE  phan_thuong (
                              maPhanThuong VARCHAR(30) NOT NULL ,
                              tenPhanThuong NVARCHAR(100) NOT NULL ,
                              giaTri REAL NOT NULL,
                              PRIMARY KEY (maPhanThuong),
                              CONSTRAINT chk_giaTri CHECK (([giaTri]>(0)))
);

-- Dumping data for table se07.phan_thuong: -1 rows
/*!40000 ALTER TABLE phan_thuong DISABLE KEYS */;
INSERT INTO phan_thuong (maPhanThuong, tenPhanThuong, giaTri) VALUES
                                                                  ('PT01', 'bim bim', 5000),
                                                                  ('PT02', 'vở ô ly', 20000),
                                                                  ('PT03', 'laptop', 20000000),
                                                                  ('PT04', 'kẹo mút', 2000),
                                                                  ('PT05', 'bánh quy', 50000),
                                                                  ('PT06', 'lì xì', 100000);
/*!40000 ALTER TABLE phan_thuong ENABLE KEYS */;

-- Dumping structure for table se07.tam_tru
CREATE TABLE  tam_tru (
                          maTamTru VARCHAR(30) NOT NULL ,
                          CCCD CHAR(12) NOT NULL ,
                          hoTen NVARCHAR(50) NOT NULL ,
                          noiTamTru NVARCHAR(100) NOT NULL ,
                          tuNgay DATE NOT NULL,
                          denNgay DATE NOT NULL,
                          lydo NTEXT NOT NULL ,
                          tinhTrang NCHAR(15) NOT NULL DEFAULT N'Chờ xác nhận' ,
                          idNguoiThucHien INT NOT NULL,
                          PRIMARY KEY (maTamTru),

                          CONSTRAINT tam_tru_FK1 FOREIGN KEY (idNguoiThucHien) REFERENCES users (ID)
);

-- Dumping data for table se07.tam_tru: -1 rows
/*!40000 ALTER TABLE tam_tru DISABLE KEYS */;
INSERT INTO tam_tru (maTamTru, CCCD, hoTen, noiTamTru, tuNgay, denNgay, lydo, tinhTrang, idNguoiThucHien) VALUES
                                                                                                              ('TT001', '087084000999', 'Nguyễn Đức Anh', 'Số 3, Tạ Quang Bứu, Hai Bà Trưng, Hà Nội', '2023-01-07', '2023-01-15', 'Lên Hà Nội chơi', 'Đã xác nhận    ', 5),
                                                                                                              ('TT002', '001088012345', 'Nguyễn Mỹ Linh', 'Số 2, Tạ Quang Bứu, Hai Bà Trưng, Hà Nội', '2023-01-31', '2023-07-31', 'Đến ở nhờ do sửa nhà', 'Chờ xác nhận   ', 4);
/*!40000 ALTER TABLE tam_tru ENABLE KEYS */;

-- Dumping structure for table se07.tam_vang
CREATE TABLE  tam_vang (
                           maTamVang VARCHAR(30) NOT NULL ,
                           maNhanKhau VARCHAR(30) NOT NULL ,
                           noiTamVang NVARCHAR(100) NOT NULL ,
                           tuNgay DATE NOT NULL,
                           denNgay DATE NOT NULL,
                           lydo NTEXT NOT NULL ,
                           tinhTrang NCHAR(15) NOT NULL DEFAULT N'Chờ xác nhận' ,
                           idNguoiThucHien INT NOT NULL,
                           PRIMARY KEY (maTamVang),

                           CONSTRAINT tam_vang_FK1 FOREIGN KEY (idNguoiThucHien) REFERENCES users (ID) ,
                           CONSTRAINT tam_vang_FK2 FOREIGN KEY (maNhanKhau) REFERENCES nhan_khau (maNhanKhau)
);

-- Dumping data for table se07.tam_vang: -1 rows
/*!40000 ALTER TABLE tam_vang DISABLE KEYS */;
INSERT INTO tam_vang (maTamVang, maNhanKhau, noiTamVang, tuNgay, denNgay, lydo, tinhTrang, idNguoiThucHien) VALUES
    ('TV001', 'NK2', 'Điện Biên', '2023-02-27', '2024-02-27', 'Đi nghĩa vụ quân sự', 'Chờ xác nhận   ', 2);
/*!40000 ALTER TABLE tam_vang ENABLE KEYS */;

-- Dumping structure for table se07.thong_tin_dip_dac_biet
CREATE TABLE  thong_tin_dip_dac_biet (
                                         idNhap INT NOT NULL,
                                         maNhanKhau VARCHAR(30) NOT NULL ,
                                         dipDacBiet NVARCHAR(50) NOT NULL ,
                                         nam INT NOT NULL,
                                         tinhTrang NCHAR(15) NULL DEFAULT N'Chờ xác nhận' ,
                                         idNguoiThucHien INT NOT NULL,
                                         PRIMARY KEY (idNhap),

                                         CONSTRAINT thong_tin_dip_dac_biet_FK2 FOREIGN KEY (idNguoiThucHien) REFERENCES users (ID) ,
                                         CONSTRAINT thong_tin_dip_dac_biet_FK1 FOREIGN KEY (maNhanKhau) REFERENCES nhan_khau (maNhanKhau)
);

-- Dumping data for table se07.thong_tin_dip_dac_biet: -1 rows
/*!40000 ALTER TABLE thong_tin_dip_dac_biet DISABLE KEYS */;
INSERT INTO thong_tin_dip_dac_biet (idNhap, maNhanKhau, dipDacBiet, nam, tinhTrang, idNguoiThucHien) VALUES
                                                                                                         (1, 'NK8', 'Trung Thu', 2022, 'Đã xác nhận    ', 6),
                                                                                                         (2, 'NK9', 'Trung Thu', 2022, 'Đã xác nhận    ', 6),
                                                                                                         (4, 'NK8', 'Năm mới', 2023, 'Chờ xác nhận   ', 6),
                                                                                                         (5, 'NK9', 'Năm mới', 2023, 'Đã xác nhận    ', 6);
/*!40000 ALTER TABLE thong_tin_dip_dac_biet ENABLE KEYS */;

-- Dumping structure for table se07.thong_tin_thanh_tich
CREATE TABLE  thong_tin_thanh_tich (
                                       idNhap INT NOT NULL,
                                       maNhanKhau VARCHAR(30) NOT NULL ,
                                       lop INT NOT NULL,
                                       truong NVARCHAR(100) NOT NULL ,
                                       capThanhTich NCHAR(20) NOT NULL ,
                                       kieuThanhTich NCHAR(20) NOT NULL ,
                                       namHoc CHAR(10) NOT NULL ,
                                       minhChung VARBINARY NOT NULL,
                                       tinhTrang NCHAR(15) NULL DEFAULT N'Chờ xác nhận' ,
                                       idNguoiThucHien INT NOT NULL,
                                       PRIMARY KEY (idNhap),

                                       CONSTRAINT thong_tin_thanh_tich_FH2 FOREIGN KEY (idNguoiThucHien) REFERENCES users (ID) ,
                                       CONSTRAINT thong_tin_thanh_tich_FK1 FOREIGN KEY (maNhanKhau) REFERENCES nhan_khau (maNhanKhau)
);

-- Dumping data for table se07.thong_tin_thanh_tich: -1 rows
/*!40000 ALTER TABLE thong_tin_thanh_tich DISABLE KEYS */;

-- Dumping structure for table se07.trao_thuong_dip_dac_biet
CREATE TABLE  trao_thuong_dip_dac_biet (
                                           idNhap INT NOT NULL,
                                           maPhanThuong VARCHAR(30) NOT NULL ,
                                           soLuong INT NOT NULL,

                                           PRIMARY KEY (idNhap, maPhanThuong),
                                           CONSTRAINT trao_thuong_dip_dac_biet_FK1 FOREIGN KEY (idNhap) REFERENCES thong_tin_dip_dac_biet (idNhap) ,
                                           CONSTRAINT trao_thuong_dip_dac_biet_FK2 FOREIGN KEY (maPhanThuong) REFERENCES phan_thuong (maPhanThuong) ,
                                           CONSTRAINT chk_soLuong CHECK (([soLuong]>(0)))
);

-- Dumping data for table se07.trao_thuong_dip_dac_biet: -1 rows
/*!40000 ALTER TABLE trao_thuong_dip_dac_biet DISABLE KEYS */;
INSERT INTO trao_thuong_dip_dac_biet (idNhap, maPhanThuong, soLuong) VALUES
                                                                         (1, 'PT01', 2),
                                                                         (1, 'PT04', 5),
                                                                         (2, 'PT01', 2),
                                                                         (2, 'PT04', 5),
                                                                         (5, 'PT06', 1);
/*!40000 ALTER TABLE trao_thuong_dip_dac_biet ENABLE KEYS */;

-- Dumping structure for table se07.trao_thuong_thanh_tich
CREATE TABLE  trao_thuong_thanh_tich (
                                         idNhap INT NOT NULL,
                                         maPhanThuong VARCHAR(30) NOT NULL ,
                                         soLuong INT NOT NULL,

                                         PRIMARY KEY (idNhap, maPhanThuong),
                                         CONSTRAINT trao_thuong_thanh_tich_FK1 FOREIGN KEY (idNhap) REFERENCES thong_tin_thanh_tich (idNhap) ,
                                         CONSTRAINT trao_thuong_thanh_tich_FK2 FOREIGN KEY (maPhanThuong) REFERENCES phan_thuong (maPhanThuong) ,
                                         CONSTRAINT check_soLuong CHECK (([soLuong]>(0)))
);

-- Dumping data for table se07.trao_thuong_thanh_tich: -1 rows
/*!40000 ALTER TABLE trao_thuong_thanh_tich DISABLE KEYS */;
INSERT INTO trao_thuong_thanh_tich (idNhap, maPhanThuong, soLuong) VALUES
    (1, 'PT02', 10);
/*!40000 ALTER TABLE trao_thuong_thanh_tich ENABLE KEYS */;

/*!40000 ALTER TABLE users ENABLE KEYS */;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
