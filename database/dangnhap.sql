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
CREATE DATABASE IF NOT EXISTS "se07";
USE "se07";

-- Dumping structure for table se07.dangnhap
CREATE TABLE IF NOT EXISTS "dangnhap" (
	"passwordd" VARCHAR(50) NOT NULL COLLATE 'SQL_Latin1_General_CP1_CI_AS',
	"userd" VARCHAR(50) NOT NULL COLLATE 'SQL_Latin1_General_CP1_CI_AS'
);

-- Dumping data for table se07.dangnhap: -1 rows
/*!40000 ALTER TABLE "dangnhap" DISABLE KEYS */;
INSERT INTO "dangnhap" ("passwordd", "userd") VALUES
	('111111', 'user'),
	('123456', 'admin'),
	('ngocdiep', 'treasurer');
/*!40000 ALTER TABLE "dangnhap" ENABLE KEYS */;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
