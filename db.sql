-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 21 Jun 2023 pada 11.07
-- Versi server: 10.4.11-MariaDB
-- Versi PHP: 7.3.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flowergallery`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `customer_name` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `mobile` varchar(12) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `customer`
--

INSERT INTO `customer` (`customer_id`, `customer_name`, `location`, `mobile`, `email`) VALUES
(1, 'Rizky Pradana', 'Surabaya', '0714331372', 'rizky1998@gmail.com'),
(2, 'Dianita Putri', 'Bogor', '0714345672', 'dianita.putri@gmail.com'),
(3, 'Fahmi Nugraha', 'Jakarta', '0659856589', 'fahmi.n@yahoo.com'),
(4, 'Anisa Maulida', 'Bekasi', '0564987456', 'anisa@gmail.com');

-- --------------------------------------------------------

--
-- Struktur dari tabel `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `salary` int(11) NOT NULL DEFAULT 10000,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `employee`
--

INSERT INTO `employee` (`employee_id`, `gender`, `location`, `salary`, `fname`, `lname`) VALUES
(6, 'Male', 'Jakarta', 100000000, 'Ade Guntur', 'Ramadhan'),
(7, 'Male', 'Jakarta', 9000000, 'Ridho', 'Roma'),
(8, 'Male', 'Surabaya', 10000000, 'Budi', 'Hartono'),
(10, 'Male', 'Jakarta', 10000, 'tEST', 'ttest');

-- --------------------------------------------------------

--
-- Struktur dari tabel `flower`
--

CREATE TABLE `flower` (
  `flower_id` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `origin` varchar(50) DEFAULT NULL,
  `flower_description` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `flower`
--

INSERT INTO `flower` (`flower_id`, `category_id`, `name`, `color`, `origin`, `flower_description`) VALUES
(1, 1, 'Mawar Merah', 'Merah', 'Asia', 'Mawar merah adalah salah satu bunga yang paling populer dan melambangkan cinta.'),
(2, 1, 'Mawar Putih', 'Putih', 'Eropa', 'Mawar putih melambangkan kebersihan dan keindahan.'),
(3, 2, 'Melati Putih', 'Putih', 'Asia', 'Melati putih memiliki aroma yang harum dan sering digunakan dalam upacara pernikahan.'),
(4, 3, 'Anggrek Biru', 'Biru', 'Amerika', 'Anggrek biru adalah anggrek langka dengan warna yang menawan.'),
(5, 4, 'Tulip Kuning', 'Kuning', 'Belanda', 'Tulip kuning adalah simbol kegembiraan dan kebahagiaan.'),
(6, 5, 'Lily Merah', 'Merah', 'Asia', 'Lily merah melambangkan keberanian dan keberanian.'),
(7, 6, 'Bunga Matahari', 'Kuning', 'Amerika', 'Bunga matahari memiliki bunga yang besar dengan warna kuning cerah.'),
(8, 7, 'Kembang Sepatu Merah', 'Merah', 'Amerika Selatan', 'Kembang sepatu merah memiliki bentuk unik dan biasa digunakan sebagai hiasan.'),
(9, 8, 'Bunga Kamboja Putih', 'Putih', 'Asia Tenggara', 'Bunga kamboja putih memiliki aroma yang harum dan digunakan dalam upacara keagamaan.'),
(10, 9, 'Bunga Seruni Kuning', 'Kuning', 'Amerika Utara', 'Bunga seruni kuning tumbuh di daerah pegunungan dan memiliki bau yang khas.'),
(11, 10, 'Bunga Mawar Air Merah', 'Merah', 'Amerika Selatan', 'Bunga mawar air merah adalah bunga air yang tumbuh di rawa-rawa dan berwarna merah cerah.');

-- --------------------------------------------------------

--
-- Struktur dari tabel `flower_category`
--

CREATE TABLE `flower_category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `flower_category`
--

INSERT INTO `flower_category` (`category_id`, `category_name`) VALUES
(1, 'Mawar'),
(2, 'Melati'),
(3, 'Anggrek'),
(4, 'Tulip'),
(5, 'Lily'),
(6, 'Bunga Matahari'),
(7, 'Kembang Sepatu'),
(8, 'Bunga Kamboja'),
(9, 'Bunga Seruni'),
(10, 'Bunga Mawar Air');

-- --------------------------------------------------------

--
-- Struktur dari tabel `inventory_flower`
--

CREATE TABLE `inventory_flower` (
  `flower_id` int(11) NOT NULL,
  `list_price` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `min_qty` int(11) DEFAULT 1
) ;

--
-- Dumping data untuk tabel `inventory_flower`
--

INSERT INTO `inventory_flower` (`flower_id`, `list_price`, `qty`, `min_qty`) VALUES
(1, 50000, 97, 1),
(2, 35000, 142, 1),
(3, 15000, 298, 5);

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `telnum` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(512) NOT NULL,
  `ques` varchar(75) NOT NULL,
  `ans` varchar(512) DEFAULT NULL,
  `isManager` tinyint(1) DEFAULT 0,
  `emp_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`id`, `telnum`, `email`, `password`, `ques`, `ans`, `isManager`, `emp_id`) VALUES
(20, '0755555555', 'ade@admin.com', '1000:561b49eee368e99d6e1c918d78135567:7d55ffce58e2db38903901cf27b6fc5a452e2e70d70807ae1594a2260bb871f0546ed931c616ffcae82677f27fade5e726cbc461b1f831fba6f900debc847606', 'What\'s your favorite food?', '1000:10e31fcbafd1e1309f61fa48ed432bc3:1aafc8c94301ae3e6812619ff4f0ec432ae62f38ee5e35d52e2f828b4b269d20df37df659b61756ec47c2ca271269f76bf8885f62ce0d0a172bc40cb5e3d9546', 1, 6),
(21, '0766666666', 'jay@mail.com', '1000:425daee4a621298c9f1a32124548ebf3:ed09ced6e53b2832ab5908888b2eb2dcf3328e37ca01a552d2135d45142485590f3ce780fccd477b53cf139eb9b93acb8994cc43dc545187d78fd07febf9230b', 'Who was your childhood hero?', '1000:3cbc3f77c446376679a6feae304622a5:dc306d1be70bc0b805d09c39f5913d1e6db2842e18c06e1bf0ebfd81da05d348a21bbcf66eba158409c32cc01da41d1774170adc71c1b0ffe7808de3dcbb02f2', 0, 7),
(22, '0451234567', 'employee1@mail.com', '1000:12218b0a2eab084fd1282cfbf59a9f46:2a382aab03d938c1252d1cf4330980f67ff64d78b9497908feaabd62d650814409d29cfa412d358d9592267635324777d77159fb172fafe924f4f7867189e62b', 'What\'s your favorite food?', '1000:911d50cfd6fdf9a99938605d88f19033:458eede03e0f269dd4999ae5948545c3dffc5524ecafa647f0c5e16f0850621ca519a8dfc61bb12bf163f032253e5ac3cde47259fb8c4c9c38469cea31278c6d', 0, 8),
(24, '123123123', 'test@mail.com', '1000:1bf84dac571eb52865b2ba0a2e934353:0334d91f70694f3e92651ea41426cc98f6233578bd319764e75af5aba9f40a3421a33149b549656e736257e516580922c2e222e663939c7409c7f75a2e7b6ea8', 'What\'s your pet\'s name?', '1000:4178f8975e7abdfc4c16da97ba1d3efe:84502aefba5c17b36fbabd74e3dd90ee4bbafcf64877ec6770a9af09f1369ac58ffb9e7b375b8878f29bedef5a2e82fbf289a764bfa0b275568657b45a004a47', 0, 10);

-- --------------------------------------------------------

--
-- Struktur dari tabel `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `order_date` date NOT NULL,
  `total_quantity` int(11) NOT NULL,
  `total_price` int(11) DEFAULT NULL,
  `total_discount` int(11) DEFAULT 0,
  `discount_perc` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `orders`
--

INSERT INTO `orders` (`order_id`, `customer_id`, `employee_id`, `order_date`, `total_quantity`, `total_price`, `total_discount`, `discount_perc`) VALUES
(64, 4, 6, '2023-06-21', 3, 100000, 0, 0),
(65, 3, 6, '2023-05-22', 5, 166250, 8750, 5),
(66, 2, 6, '2023-06-21', 4, 170000, 0, 0),
(67, 1, 6, '2023-06-21', 1, 15000, 0, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `order_details`
--

CREATE TABLE `order_details` (
  `flower_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `order_details`
--

INSERT INTO `order_details` (`flower_id`, `order_id`, `quantity`, `unit_price`) VALUES
(1, 64, 1, 50000),
(1, 66, 2, 50000),
(2, 64, 1, 35000),
(2, 65, 5, 35000),
(2, 66, 2, 35000),
(3, 64, 1, 15000),
(3, 67, 1, 15000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indeks untuk tabel `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indeks untuk tabel `flower`
--
ALTER TABLE `flower`
  ADD PRIMARY KEY (`flower_id`),
  ADD KEY `flower_FK` (`category_id`);

--
-- Indeks untuk tabel `flower_category`
--
ALTER TABLE `flower_category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indeks untuk tabel `inventory_flower`
--
ALTER TABLE `inventory_flower`
  ADD PRIMARY KEY (`flower_id`);

--
-- Indeks untuk tabel `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `telnum` (`telnum`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `FK_emp` (`emp_id`);

--
-- Indeks untuk tabel `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `FK_Cus` (`customer_id`),
  ADD KEY `FK_Employee` (`employee_id`);

--
-- Indeks untuk tabel `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`flower_id`,`order_id`),
  ADD KEY `FK_order_del` (`order_id`),
  ADD KEY `FK_Books` (`flower_id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT untuk tabel `flower`
--
ALTER TABLE `flower`
  MODIFY `flower_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT untuk tabel `flower_category`
--
ALTER TABLE `flower_category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT untuk tabel `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT untuk tabel `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `flower`
--
ALTER TABLE `flower`
  ADD CONSTRAINT `flower_FK` FOREIGN KEY (`category_id`) REFERENCES `flower_category` (`category_id`);

--
-- Ketidakleluasaan untuk tabel `inventory_flower`
--
ALTER TABLE `inventory_flower`
  ADD CONSTRAINT `FK_flower` FOREIGN KEY (`flower_id`) REFERENCES `flower` (`flower_id`);

--
-- Ketidakleluasaan untuk tabel `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `FK_emp` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`employee_id`);

--
-- Ketidakleluasaan untuk tabel `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK_Cus` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  ADD CONSTRAINT `FK_Employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`);

--
-- Ketidakleluasaan untuk tabel `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `FK_order_del` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `order_details_FK` FOREIGN KEY (`flower_id`) REFERENCES `flower` (`flower_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
