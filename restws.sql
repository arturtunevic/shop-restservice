-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 03, 2019 at 05:07 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restws`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `Cart_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Quantity` int(11) NOT NULL,
  `Item_ID` int(11) NOT NULL,
  `Sale_ID` int(11) NOT NULL,
  PRIMARY KEY (`Cart_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`Cart_ID`, `Quantity`, `Item_ID`, `Sale_ID`) VALUES
(1, 2, 1, 1),
(2, 45, 5, 1),
(3, 50, 3, 2),
(4, 499, 6, 2),
(5, 151, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Surname` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`ID`, `Name`, `Surname`) VALUES
(1, 'Tomas', 'Dubininkas'),
(2, 'Artur', 'Tunevic'),
(3, 'Gintautas', 'Cepas'),
(5, 'I CHANGEDIT', 'OJJ');

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE IF NOT EXISTS `item` (
  `Item_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Item_name` varchar(255) NOT NULL,
  `item_price` double NOT NULL,
  `Item_quantity` int(11) NOT NULL,
  PRIMARY KEY (`Item_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`Item_ID`, `Item_name`, `item_price`, `Item_quantity`) VALUES
(1, 'Computer case', 27.99, 420),
(2, 'Computer fan Rx3000', 11.42, 155),
(3, 'APPLE MacBook Pro 13', 3420.9, 50),
(4, 'Apple IIe', 5600.5, 5),
(6, 'Makintosh', 999.9, 240);

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
CREATE TABLE IF NOT EXISTS `sales` (
  `Sale_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Sales_Date` date NOT NULL,
  `Customer_ID` int(11) NOT NULL,
  PRIMARY KEY (`Sale_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`Sale_ID`, `Sales_Date`, `Customer_ID`) VALUES
(1, '2019-05-09', 1),
(2, '2019-06-01', 2),
(3, '2019-04-10', 3);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
