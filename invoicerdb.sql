-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2021. Ápr 03. 13:59
-- Kiszolgáló verziója: 10.4.18-MariaDB
-- PHP verzió: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `invoicerdb`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `customers`
--

CREATE TABLE `customers` (
  `customerId` varchar(38) NOT NULL,
  `billingName` varchar(300) NOT NULL,
  `billingCity` varchar(128) NOT NULL,
  `billingPostalCode` varchar(5) NOT NULL,
  `billingAddress` varchar(128) NOT NULL,
  `billingAddressType` varchar(50) NOT NULL,
  `billingHouseNumber` varchar(5) NOT NULL,
  `billingStairway` varchar(5) NOT NULL,
  `billingFloor` varchar(5) NOT NULL,
  `deliveryCity` varchar(128) NOT NULL,
  `deliveryPostalCode` varchar(5) NOT NULL,
  `deliveryAddress` varchar(128) NOT NULL,
  `deliveryAddressType` varchar(50) NOT NULL,
  `deliveryHouseNumber` varchar(12) NOT NULL,
  `deliveryStairway` varchar(12) NOT NULL,
  `deliveryFloor` varchar(12) NOT NULL,
  `customerVAT` varchar(18) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(128) NOT NULL,
  `webPage` varchar(128) NOT NULL,
  `bankAccount` varchar(30) NOT NULL,
  `contactComment` varchar(300) NOT NULL,
  `hasSameAddress` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `customers`
--

INSERT INTO `customers` (`customerId`, `billingName`, `billingCity`, `billingPostalCode`, `billingAddress`, `billingAddressType`, `billingHouseNumber`, `billingStairway`, `billingFloor`, `deliveryCity`, `deliveryPostalCode`, `deliveryAddress`, `deliveryAddressType`, `deliveryHouseNumber`, `deliveryStairway`, `deliveryFloor`, `customerVAT`, `phone`, `email`, `webPage`, `bankAccount`, `contactComment`, `hasSameAddress`) VALUES
('8270df9b-2980-4d8d-a4cd-4360697fed01', 'Partner 1', 'Ökörapáti', '0123', 'Kossuth Lajos', 'utca', '7', '', '', 'Ökörapáti', '0123', 'Kossuth Lajos', 'utca', '7', '', '', '123456-1-15', '+36301234567', 'email@gmail.com', 'www.google.hu', '12345678-01234567-87654321', 'nincs megjegyzés', 1);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `invoices`
--

CREATE TABLE `invoices` (
  `invoiceId` varchar(38) NOT NULL,
  `customerInvoiceName` varchar(128) NOT NULL,
  `customerInvoiceAddress` varchar(300) NOT NULL,
  `sumNetPrice` varchar(10) NOT NULL,
  `sumGrossPrice` varchar(10) NOT NULL,
  `invoiceDate` varchar(12) NOT NULL,
  `buyerId` varchar(38) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `products`
--

CREATE TABLE `products` (
  `productID` varchar(38) NOT NULL,
  `teszor` varchar(25) NOT NULL,
  `isservice` int(2) NOT NULL,
  `name` varchar(300) NOT NULL,
  `comment` varchar(300) NOT NULL,
  `productnumber` varchar(100) NOT NULL,
  `netprice` varchar(25) NOT NULL,
  `grossprice` varchar(25) NOT NULL,
  `purchasenetprice` varchar(25) NOT NULL,
  `purchasegrossprice` varchar(25) NOT NULL,
  `discountnetprice` varchar(25) NOT NULL,
  `dicountgrossprice` varchar(25) NOT NULL,
  `isdiscounted` int(2) NOT NULL,
  `stock` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `products`
--

INSERT INTO `products` (`productID`, `teszor`, `isservice`, `name`, `comment`, `productnumber`, `netprice`, `grossprice`, `purchasenetprice`, `purchasegrossprice`, `discountnetprice`, `dicountgrossprice`, `isdiscounted`, `stock`) VALUES
('da527773-51a7-40b0-97d5-f54172fdf822', 'teszor1', 0, 'Termék 1', 'megjegyzés1', 'cikkszám1', '5555', '5555', '4000', '4000', '4250', '4250', 1, NULL);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `receivenotes`
--

CREATE TABLE `receivenotes` (
  `receivenoteid` varchar(38) NOT NULL,
  `sellerid` varchar(38) NOT NULL,
  `receivenotename` varchar(128) NOT NULL,
  `fulladdress` varchar(300) NOT NULL,
  `sumnetprice` varchar(12) NOT NULL,
  `sumgrossprice` varchar(12) NOT NULL,
  `receivenotedate` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customerId`);

--
-- A tábla indexei `invoices`
--
ALTER TABLE `invoices`
  ADD PRIMARY KEY (`invoiceId`);

--
-- A tábla indexei `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`productID`);

--
-- A tábla indexei `receivenotes`
--
ALTER TABLE `receivenotes`
  ADD PRIMARY KEY (`receivenoteid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
