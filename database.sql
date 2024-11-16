CREATE database Ecommerce;
use ecommerce;



SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";




CREATE TABLE IF NOT EXISTS `optiongroups` (
  `OptionGroupID` int(11) NOT NULL AUTO_INCREMENT,
  `OptionGroupName` varchar(50) COLLATE latin1_german2_ci DEFAULT NULL,
  PRIMARY KEY (`OptionGroupID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci AUTO_INCREMENT=4 ;

--
-- Dumping data for table `optiongroups`
--

INSERT INTO `optiongroups` (`OptionGroupID`, `OptionGroupName`) VALUES
(1, 'color'),
(2, 'size');

-- --------------------------------------------------------

--
-- Table structure for table `options`
--

CREATE TABLE IF NOT EXISTS `options` (
  `OptionID` int(11) NOT NULL AUTO_INCREMENT,
  `OptionGroupID` int(11) DEFAULT NULL,
  `OptionName` varchar(50) COLLATE latin1_german2_ci DEFAULT NULL,
  PRIMARY KEY (`OptionID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci AUTO_INCREMENT=9 ;

--
-- Dumping data for table `options`
--

INSERT INTO `options` (`OptionID`, `OptionGroupID`, `OptionName`) VALUES
(1, 1, 'red'),
(2, 1, 'blue'),
(3, 1, 'green'),
(4, 2, 'S'),
(5, 2, 'M'),
(6, 2, 'L'),
(7, 2, 'XL'),
(8, 2, 'XXL');

-- --------------------------------------------------------

--
-- Table structure for table `orderdetails`
--

CREATE TABLE IF NOT EXISTS `orderdetails` (
  `DetailID` int(11) NOT NULL AUTO_INCREMENT,
  `DetailOrderID` int(11) NOT NULL,
  `DetailProductID` int(11) NOT NULL,
  `DetailName` varchar(250) COLLATE latin1_german2_ci NOT NULL,
  `DetailPrice` float NOT NULL,
  `DetailSKU` varchar(50) COLLATE latin1_german2_ci NOT NULL,
  `DetailQuantity` int(11) NOT NULL,
  PRIMARY KEY (`DetailID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `OrderID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderUserID` int(11) NOT NULL,
  `OrderAmount` float NOT NULL,
  `OrderShipName` varchar(100) COLLATE latin1_german2_ci NOT NULL,
  `OrderShipAddress` varchar(100) COLLATE latin1_german2_ci NOT NULL,
  `OrderShipAddress2` varchar(100) COLLATE latin1_german2_ci NOT NULL,
  `OrderCity` varchar(50) COLLATE latin1_german2_ci NOT NULL,
  `OrderState` varchar(50) COLLATE latin1_german2_ci NOT NULL,
  `OrderZip` varchar(20) COLLATE latin1_german2_ci NOT NULL,
  `OrderCountry` varchar(50) COLLATE latin1_german2_ci NOT NULL,
  `OrderPhone` varchar(20) COLLATE latin1_german2_ci NOT NULL,
  `OrderFax` varchar(20) COLLATE latin1_german2_ci NOT NULL,
  `OrderShipping` float NOT NULL,
  `OrderTax` float NOT NULL,
  `OrderEmail` varchar(100) COLLATE latin1_german2_ci NOT NULL,
  `OrderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `OrderShipped` tinyint(1) NOT NULL DEFAULT '0',
  `OrderTrackingNumber` varchar(80) COLLATE latin1_german2_ci DEFAULT NULL,
  PRIMARY KEY (`OrderID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `productcategories`
--

CREATE TABLE IF NOT EXISTS `productcategories` (
  `CategoryID` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(50) COLLATE latin1_german2_ci NOT NULL,
  `CategoryImgUrl` VARCHAR(255),
  PRIMARY KEY (`CategoryID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci AUTO_INCREMENT=7 ;

ALTER TABLE productcategories
ADD COLUMN thumbnailurl VARCHAR(255);

DROP TABLE productcategories;
--
-- Dumping data for table `productcategories`
--

INSERT INTO `productcategories` (`CategoryID`, `CategoryName`) VALUES
(1, 'Running'),
(2, 'Walking'),
(3, 'HIking'),
(4, 'Track and Trail'),
(5, 'Short Sleave'),
(6, 'Long Sleave');

-- --------------------------------------------------------

--
-- Table structure for table `productoptions`
--

CREATE TABLE IF NOT EXISTS `productoptions` (
  `ProductOptionID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ProductID` int(10) unsigned NOT NULL,
  `OptionID` int(10) unsigned NOT NULL,
  `OptionPriceIncrement` double DEFAULT NULL,
  `OptionGroupID` int(11) NOT NULL,
  PRIMARY KEY (`ProductOptionID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci AUTO_INCREMENT=9 ;

--
-- Dumping data for table `productoptions`
--

INSERT INTO `productoptions` (`ProductOptionID`, `ProductID`, `OptionID`, `OptionPriceIncrement`, `OptionGroupID`) VALUES
(1, 1, 1, 0, 1),
(2, 1, 2, 0, 1),
(3, 1, 3, 0, 1),
(4, 1, 4, 0, 2),
(5, 1, 5, 0, 2),
(6, 1, 6, 0, 2),
(7, 1, 7, 2, 2),
(8, 1, 8, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--


DROP TABLE products;

CREATE TABLE IF NOT EXISTS `products` (
  `ProductID` int(12) NOT NULL AUTO_INCREMENT,
  `ProductSKU` varchar(50) COLLATE latin1_german2_ci NOT NULL,
  `ProductName` varchar(100) COLLATE latin1_german2_ci NOT NULL,
  `ProductPrice` float NOT NULL,
  `ProductWeight` float NOT NULL,
  `ProductCartDesc` varchar(250) COLLATE latin1_german2_ci NOT NULL,
  `ProductShortDesc` varchar(1000) COLLATE latin1_german2_ci NOT NULL,
  `ProductLongDesc` text COLLATE latin1_german2_ci NOT NULL,
  `ProductThumb` varchar(100) COLLATE latin1_german2_ci NOT NULL,
  `ProductImage` varchar(100) COLLATE latin1_german2_ci NOT NULL,
  `ProductCategoryID` int(11) DEFAULT NULL,
  `ProductStock` float DEFAULT NULL,
  `ProductLive` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ProductID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci AUTO_INCREMENT=991 ;


--
-- Dumping data for table `products`
--
INSERT INTO `products` 
(`ProductSKU`, `ProductName`, `ProductPrice`, `ProductWeight`, `ProductCartDesc`, `ProductShortDesc`, `ProductLongDesc`, `ProductThumb`, `ProductImage`, `ProductCategoryID`, `ProductStock`, `ProductLive`)
VALUES
('SKU001', 'Product 1', 100.50, 0.5, 'Short description 1', 'Short description 1', 'Long description for product 1', 'thumb1.jpg', 'image1.jpg', 1, 100, 1),
('SKU002', 'Product 2', 200.00, 0.8, 'Short description 2', 'Short description 2', 'Long description for product 2', 'thumb2.jpg', 'image2.jpg', 2, 150, 1),
('SKU003', 'Product 3', 150.00, 0.6, 'Short description 3', 'Short description 3', 'Long description for product 3', 'thumb3.jpg', 'image3.jpg', 3, 200, 1),
('SKU004', 'Product 4', 300.00, 0.9, 'Short description 4', 'Short description 4', 'Long description for product 4', 'thumb4.jpg', 'image4.jpg', 4, 250, 1),
('SKU005', 'Product 5', 120.00, 0.4, 'Short description 5', 'Short description 5', 'Long description for product 5', 'thumb5.jpg', 'image5.jpg', 5, 300, 1),
('SKU006', 'Product 6', 180.00, 0.7, 'Short description 6', 'Short description 6', 'Long description for product 6', 'thumb6.jpg', 'image6.jpg', 6, 350, 1),
('SKU007', 'Product 7', 250.00, 1.0, 'Short description 7', 'Short description 7', 'Long description for product 7', 'thumb7.jpg', 'image7.jpg', 7, 400, 1),
('SKU008', 'Product 8', 90.00, 0.3, 'Short description 8', 'Short description 8', 'Long description for product 8', 'thumb8.jpg', 'image8.jpg', 8, 450, 1),
('SKU009', 'Product 9', 110.00, 0.5, 'Short description 9', 'Short description 9', 'Long description for product 9', 'thumb9.jpg', 'image9.jpg', 9, 500, 1),
('SKU010', 'Product 10', 130.00, 0.6, 'Short description 10', 'Short description 10', 'Long description for product 10', 'thumb10.jpg', 'image10.jpg', 10, 550, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

drop table users;
CREATE TABLE IF NOT EXISTS `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserEmail` varchar(500) COLLATE latin1_german2_ci DEFAULT NULL,
  `UserPassword` varchar(500) COLLATE latin1_german2_ci DEFAULT NULL,
  `UserFirstName` varchar(50) COLLATE latin1_german2_ci DEFAULT NULL,
  `UserLastName` varchar(50) COLLATE latin1_german2_ci DEFAULT NULL,
  `UserPhone` varchar(20) COLLATE latin1_german2_ci DEFAULT NULL,
  `UserAddress` varchar(100) COLLATE latin1_german2_ci DEFAULT NULL,
  `UserAddress2` varchar(50) COLLATE latin1_german2_ci DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci AUTO_INCREMENT=1 ;




CREATE TABLE IF NOT EXISTS `district` (
  `DistrictId` int(11) NOT NULL AUTO_INCREMENT,
  `CityId` int(11),
  `DistrictName` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`DistrictId`),
  FOREIGN KEY (CityId) REFERENCES City(CityID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
  );

CREATE TABLE IF NOT EXISTS `City` (
  `CityID` INT(11) NOT NULL AUTO_INCREMENT,
  `CityName` VARCHAR(100) NOT NULL,
  `CityType` VARCHAR(50) DEFAULT NULL,  -- Thêm loại (thành phố, tỉnh)
  PRIMARY KEY (`CityID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `City` (`CityName`, `CityType`) VALUES
('Hà Nội', 'Thành phố'),
('TP Hồ Chí Minh', 'Thành phố'),
('Hải Phòng', 'Thành phố'),
('Đà Nẵng', 'Thành phố'),
('Cần Thơ', 'Thành phố'),
('Hạ Long', 'Thành phố'),
('Bình Dương', 'Tỉnh'),
('Bắc Ninh', 'Tỉnh'),
('Hải Dương', 'Tỉnh'),
('Quảng Ninh', 'Tỉnh'),
('Nam Định', 'Tỉnh'),
('Thái Bình', 'Tỉnh'),
('Vĩnh Phúc', 'Tỉnh'),
('Long An', 'Tỉnh'),
('An Giang', 'Tỉnh'),
('Kiên Giang', 'Tỉnh'),
('Bến Tre', 'Tỉnh'),
('Tiền Giang', 'Tỉnh'),
('Bắc Giang', 'Tỉnh'),
('Đắk Lắk', 'Tỉnh'),
('Phú Thọ', 'Tỉnh'),
('Thừa Thiên Huế', 'Tỉnh'),
('Nghệ An', 'Tỉnh'),
('Hà Nam', 'Tỉnh'),
('Tuyên Quang', 'Tỉnh'),
('Lào Cai', 'Tỉnh');


ALTER TABLE city
DROP COLUMN city_name,
DROP COLUMN city_type;
