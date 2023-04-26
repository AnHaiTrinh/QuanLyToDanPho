-- --------------------------------------------------------
-- Host: 127.0.0.1
-- Server version:Microsoft SQL Server 2022 (RTM) - 16.0.1000.6
-- Server OS:Linux (Ubuntu 20.04.5 LTS) <X64>
-- HeidiSQL Version: 12.1.0.6537
-- --------------------------------------------------------
CREATE DATABASE se07;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE se07;
-- Dumping database structure for se07
USE se07;
-- Dumping structure for table se07.users
CREATE TABLE users
(
    ID       INT IDENTITY (1, 1) NOT NULL,
    username VARCHAR(30)         NOT NULL,
    password VARCHAR(30)         NOT NULL,
    role     INT                 NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT chk_role CHECK (role IN (0, 1, 2))
);

-- Dumping data for table se07.users: -1 rows
/*!40000 ALTER TABLE users DISABLE KEYS */;
INSERT INTO users (username, password, role)
VALUES ('admin', '123456', 1),
       ('hogiadinh1', '111111', 2),
       ('thuquy', 'password', 0),
       ('hogiadinh2', '666', 2),
       ('hogiadinh3', '1den9', 2),
       ('hogiadinh4', 'psswd', 2);

/*!40000 ALTER TABLE ho_gia_dinh_user ENABLE KEYS */;

-- Dumping structure for table se07.ho_khau
CREATE TABLE ho_khau
(
    maHoKhau        VARCHAR(30)   NOT NULL,
    chuHo           NVARCHAR(50)  NOT NULL,
    diaChi          NVARCHAR(100) NOT NULL,
    ngayLap         DATE          NOT NULL,
    idNguoiThucHien INT           NOT NULL,
    PRIMARY KEY (maHoKhau),
    CONSTRAINT ho_khau_FK1 FOREIGN KEY (idNguoiThucHien) REFERENCES users (ID)
);

/*!40000 ALTER TABLE ho_khau DISABLE KEYS */;
INSERT INTO ho_khau (maHoKhau, chuHo, diaChi, ngayLap, idNguoiThucHien)
VALUES ('HK1', N'Trịnh Văn An', N'Số 1, Tạ Quang Bửu, Hai Bà trưng, Hà Nội', '2023-01-05', 1),
       ('HK2', N'Trần Thanh Duyên', N'Số 2, Tạ Quang Bửu, Hai Bà Trưng, Hà Nội', '2022-12-29', 1),
       ('HK3', N'Nguyễn Tiến Dũng', N'Số 4, Tạ Quang Bửu, Hai Bà Trưng, Hà Nội', '2023-01-03', 1),
       ('HK4', N'Nguyễn Tiến Đạt', N'Số 3, Tạ Quang Bửu, Hai Bà Trưng, Hà Nội', '2023-01-04', 5);
/*!40000 ALTER TABLE ho_khau ENABLE KEYS */;
-- Dumping structure for table se07.ho_gia_dinh_user
CREATE TABLE ho_gia_dinh_user
(
    ID       INT         NOT NULL,
    maHoKhau VARCHAR(30) NOT NULL,

    PRIMARY KEY (ID, maHoKhau),
    CONSTRAINT ho_gia_dinh_user_FK1 FOREIGN KEY (ID) REFERENCES users (ID),
    CONSTRAINT ho_gia_dinh_user_FK2 FOREIGN KEY (maHoKhau) REFERENCES ho_khau (maHoKhau)
);

-- Dumping data for table se07.ho_gia_dinh_user: -1 rows
/*!40000 ALTER TABLE ho_gia_dinh_user DISABLE KEYS */;
INSERT INTO ho_gia_dinh_user (ID, maHoKhau)
VALUES (2, 'HK1'),
       (4, 'HK2'),
       (5, 'HK3'),
       (6, 'HK4');

-- Dumping structure for table se07.nhan_khau
CREATE TABLE nhan_khau
(
    maNhanKhau      VARCHAR(30)  NOT NULL,
    maHoKhau        VARCHAR(30)  NOT NULL,
    hoTen           NVARCHAR(50) NOT NULL,
    bietDanh        NVARCHAR(50) NULL     DEFAULT NULL,
    ngaySinh        DATE         NOT NULL,
    gioiTinh        NVARCHAR(3)  NOT NULL,
    tonGiao         NVARCHAR(20) NULL     DEFAULT NULL,
    tinhTrang       NVARCHAR(15) NOT NULL DEFAULT N'Chờ xác nhận',
    idNguoiThucHien INT          NOT NULL,

    PRIMARY KEY (maNhanKhau),
    CONSTRAINT nhan_khau_FK2 FOREIGN KEY (idNguoiThucHien) REFERENCES users (ID),
    CONSTRAINT nhan_khau_FK1 FOREIGN KEY (maHoKhau) REFERENCES ho_khau (maHoKhau)
);

-- Dumping data for table se07.nhan_khau: -1 rows
/*!40000 ALTER TABLE nhan_khau DISABLE KEYS */;
INSERT INTO nhan_khau (maNhanKhau, maHoKhau, hoTen, bietDanh, ngaySinh, gioiTinh, tonGiao, tinhTrang, idNguoiThucHien)
VALUES ('NK1', 'HK1', N'Trịnh Văn An', NULL, '1974-12-07', N'Nam', NULL, N'Đã xác nhận', 1),
       ('NK2', 'HK1', N'Trịnh Văn Quyết', NULL, '2000-04-05', N'Nam', NULL, N'Đã xác nhận', 2),
       ('NK3', 'HK2', N'Trần Thanh Duyên', NULL, '1977-12-23', N'Nữ', NULL, N'Đã xác nhận', 1),
       ('NK4', 'HK2', N'Nguyễn Minh Quân', NULL, '1977-05-31', N'Nam', NULL, N'Đã xác nhận', 4),
       ('NK5', 'HK3', N'Nguyễn Tiến Dũng', NULL, '1964-06-03', N'Nam', NULL, N'Đã xác nhận', 1),
       ('NK6', 'HK4', N'Nguyễn Tiến Đạt', NULL, '1989-11-23', N'Nam', NULL, N'Đã xác nhận', 5),
       ('NK7', 'HK4', N'Nguyễn Trà My', NULL, '1990-05-18', N'Nữ', NULL, N'Đã xác nhận', 5),
       ('NK8', 'HK4', N'Nguyễn Văn Nam', NULL, '2015-06-29', N'Nam', NULL, N'Đã xác nhận', 6),
       ('NK9', 'HK4', N'Nguyễn Thị Huyền', NULL, '2018-04-13', N'Nữ', NULL, N'Đã xác nhận', 6);

-- Dumping structure for table se07.phan_thuong
CREATE TABLE phan_thuong
(
    maPhanThuong  INT IDENTITY (1, 1) NOT NULL,
    tenPhanThuong NVARCHAR(100)       NOT NULL,
    giaTri        INT                 NOT NULL,
    PRIMARY KEY (maPhanThuong),
    CONSTRAINT chk_giaTri CHECK (giaTri > 0)
);

-- Dumping data for table se07.phan_thuong: -1 rows
/*!40000 ALTER TABLE phan_thuong DISABLE KEYS */;
INSERT INTO phan_thuong (tenPhanThuong, giaTri)
VALUES (N'bim bim', 5000),
       (N'vở ô ly', 20000),
       (N'laptop', 20000000),
       (N'kẹo mút', 2000),
       (N'bánh quy', 50000),
       (N'lì xì', 100000);
/*!40000 ALTER TABLE phan_thuong ENABLE KEYS */;

-- Dumping structure for table se07.tam_tru
CREATE TABLE tam_tru
(
    maTamTru        INT IDENTITY (1,1),
    maHoKhau        VARCHAR(30)   NOT NULL,
    CCCD            VARCHAR(12)   NOT NULL,
    hoTen           NVARCHAR(50)  NOT NULL,
    tuNgay          DATE          NOT NULL,
    denNgay         DATE          NOT NULL,
    lyDo            NVARCHAR(500) NOT NULL,
    tinhTrang       NVARCHAR(15)  NOT NULL DEFAULT N'Chờ xác nhận',
    idNguoiThucHien INT           NOT NULL,
    PRIMARY KEY (maTamTru),
    CONSTRAINT tam_tru_FK1 FOREIGN KEY (idNguoiThucHien) REFERENCES users (ID),
    CONSTRAINT tam_tru_FK2 FOREIGN KEY (maHoKhau) REFERENCES ho_khau (maHoKhau) ON DELETE CASCADE
);

-- Dumping data for table se07.tam_tru: -1 rows
/*!40000 ALTER TABLE tam_tru DISABLE KEYS */;
INSERT INTO tam_tru (maHoKhau, CCCD, hoTen, tuNgay, denNgay, lyDo, tinhTrang, idNguoiThucHien)
VALUES ('HK1', '087084000999', N'Nguyễn Đức Anh', '2023-01-07', '2023-01-15', N'Lên Hà Nội chơi', N'Đã xác nhận', 5),
       ('HK2', '001088012345', N'Nguyễn Mỹ Linh', '2023-01-31', '2023-07-31', N'Đến ở nhờ do sửa nhà', N'Chờ xác nhận',
        4);
/*!40000 ALTER TABLE tam_tru ENABLE KEYS */;

-- Dumping structure for table se07.tam_vang
CREATE TABLE tam_vang
(
    maTamVang       INT IDENTITY (1,1),
    maNhanKhau      VARCHAR(30)   NOT NULL,
    noiTamVang      NVARCHAR(100) NOT NULL,
    tuNgay          DATE          NOT NULL,
    denNgay         DATE          NOT NULL,
    lydo            NVARCHAR(500) NOT NULL,
    tinhTrang       NVARCHAR(15)  NOT NULL DEFAULT N'Chờ xác nhận',
    idNguoiThucHien INT           NOT NULL,
    PRIMARY KEY (maTamVang),

    CONSTRAINT tam_vang_FK1 FOREIGN KEY (idNguoiThucHien) REFERENCES users (ID),
    CONSTRAINT tam_vang_FK2 FOREIGN KEY (maNhanKhau) REFERENCES nhan_khau (maNhanKhau) ON DELETE CASCADE
);

-- Dumping data for table se07.tam_vang: -1 rows
/*!40000 ALTER TABLE tam_vang DISABLE KEYS */;
INSERT INTO tam_vang (maNhanKhau, noiTamVang, tuNgay, denNgay, lydo, tinhTrang, idNguoiThucHien)
VALUES ('NK2', N'Điện Biên', '2023-02-27', '2024-02-27', N'Đi nghĩa vụ quân sự', N'Chờ xác nhận', 2);
/*!40000 ALTER TABLE tam_vang ENABLE KEYS */;

CREATE TABLE dip_trao_thuong
(
    id          INT IDENTITY (1,1) PRIMARY KEY,
    tenDip      NVARCHAR(30) NOT NULL,
    nam         INT          NOT NULL,
    ngayTao     DATE         NOT NULL,
    ngayKetThuc DATE         NOT NULL,
    kieu        NVARCHAR(20) NOT NULL,
    ghiChu      NVARCHAR(100),
    CONSTRAINT chk_kieu CHECK (kieu IN (N'Thành tích', N'Dịp đặc biệt'))
);

INSERT INTO dip_trao_thuong (tenDip, nam, ngayTao, ngayKetThuc, kieu)
VALUES (N'Trung Thu', 2022, '2022-10-07', '2022-10-21', N'Dịp đặc biệt'),
       (N'Năm mới', 2023, '2022-12-01', '2022-12-31', N'Dịp đặc biệt'),
       (N'Thành tích học tập kỳ I', 2022, '2022-12-20', '2023-01-10', N'Thành tích');

-- Dumping structure for table se07.thong_tin_dip_dac_biet
CREATE TABLE thong_tin_dip_dac_biet
(
    idNhap          INT IDENTITY (1, 1) NOT NULL,
    idDip           INT                 NOT NULL,
    maNhanKhau      VARCHAR(30)         NOT NULL,
    tinhTrang       NVARCHAR(15)        NULL DEFAULT N'Chờ xác nhận',
    idNguoiThucHien INT                 NOT NULL,
    PRIMARY KEY (idNhap),
    CONSTRAINT thong_tin_dip_dac_biet_FK1 FOREIGN KEY (maNhanKhau) REFERENCES nhan_khau (maNhanKhau) ON DELETE CASCADE,
    CONSTRAINT thong_tin_dip_dac_biet_FK2 FOREIGN KEY (idDip) REFERENCES dip_trao_thuong (id) ON DELETE CASCADE,
    CONSTRAINT thong_tin_dip_dac_biet_FK3 FOREIGN KEY (idNguoiThucHien) REFERENCES users (ID),
);

-- Dumping data for table se07.thong_tin_dip_dac_biet: -1 rows
/*!40000 ALTER TABLE thong_tin_dip_dac_biet DISABLE KEYS */;
INSERT INTO thong_tin_dip_dac_biet (maNhanKhau, idDip, tinhTrang, idNguoiThucHien)
VALUES ('NK8', 1, N'Đã xác nhận', 6),
       ('NK9', 1, N'Đã xác nhận', 6),
       ('NK8', 2, N'Chờ xác nhận', 6),
       ('NK9', 2, N'Đã xác nhận', 6);
/*!40000 ALTER TABLE thong_tin_dip_dac_biet ENABLE KEYS */;

-- Dumping structure for table se07.thong_tin_thanh_tich
CREATE TABLE thong_tin_thanh_tich
(
    idNhap          INT IDENTITY (1, 1) NOT NULL,
    idDip           INT                 NOT NULL,
    maNhanKhau      VARCHAR(30)         NOT NULL,
    lop             INT                 NOT NULL,
    truong          NVARCHAR(100)       NOT NULL,
    capThanhTich    NVARCHAR(20)        NOT NULL,
    kieuThanhTich   NVARCHAR(20)        NOT NULL,
    minhChung       VARBINARY(MAX)      NOT NULL,
    tinhTrang       NVARCHAR(15)        NULL DEFAULT N'Chờ xác nhận',
    idNguoiThucHien INT                 NOT NULL,
    PRIMARY KEY (idNhap),
    CONSTRAINT thong_tin_thanh_tich_FK3 FOREIGN KEY (idDip) REFERENCES dip_trao_thuong (id) ON DELETE CASCADE,
    CONSTRAINT thong_tin_thanh_tich_FK2 FOREIGN KEY (idNguoiThucHien) REFERENCES users (ID),
    CONSTRAINT thong_tin_thanh_tich_FK1 FOREIGN KEY (maNhanKhau) REFERENCES nhan_khau (maNhanKhau) ON DELETE CASCADE
);

-- Dumping data for table se07.thong_tin_thanh_tich: -1 rows
/*!40000 ALTER TABLE thong_tin_thanh_tich DISABLE KEYS */;

-- Dumping structure for table se07.trao_thuong_dip_dac_biet
CREATE TABLE trao_thuong_dip_dac_biet
(
    idNhap       INT NOT NULL,
    maPhanThuong INT NOT NULL,
    soLuong      INT NOT NULL,

    PRIMARY KEY (idNhap, maPhanThuong),
    CONSTRAINT trao_thuong_dip_dac_biet_FK1 FOREIGN KEY (idNhap) REFERENCES thong_tin_dip_dac_biet (idNhap) ON DELETE CASCADE,
    CONSTRAINT trao_thuong_dip_dac_biet_FK2 FOREIGN KEY (maPhanThuong) REFERENCES phan_thuong (maPhanThuong),
    CONSTRAINT chk_soLuong CHECK (soLuong > 0)
);

-- Dumping data for table se07.trao_thuong_dip_dac_biet: -1 rows
/*!40000 ALTER TABLE trao_thuong_dip_dac_biet DISABLE KEYS */;
INSERT INTO trao_thuong_dip_dac_biet (idNhap, maPhanThuong, soLuong)
VALUES (1, 1, 2),
       (1, 4, 5),
       (2, 1, 2),
       (2, 4, 5),
       (4, 6, 1);
/*!40000 ALTER TABLE trao_thuong_dip_dac_biet ENABLE KEYS */;

-- Dumping structure for table se07.trao_thuong_thanh_tich
CREATE TABLE trao_thuong_thanh_tich
(
    idNhap       INT NOT NULL,
    maPhanThuong INT NOT NULL,
    soLuong      INT NOT NULL,

    PRIMARY KEY (idNhap, maPhanThuong),
    CONSTRAINT trao_thuong_thanh_tich_FK1 FOREIGN KEY (idNhap) REFERENCES thong_tin_thanh_tich (idNhap) ON DELETE CASCADE,
    CONSTRAINT trao_thuong_thanh_tich_FK2 FOREIGN KEY (maPhanThuong) REFERENCES phan_thuong (maPhanThuong),
    CONSTRAINT check_soLuong CHECK (soLuong > 0)
);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET VARCHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
