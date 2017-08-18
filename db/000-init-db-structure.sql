-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 18, 2017 at 10:25 AM
-- Server version: 10.1.23-MariaDB-9+deb9u1
-- PHP Version: 5.6.30-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `webdict`
--

-- --------------------------------------------------------

--
-- Table structure for table `dictionary`
--

CREATE TABLE `dictionary` (
  `id` bigint(20) NOT NULL,
  `source_language_id` bigint(20) DEFAULT NULL,
  `target_language_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `language`
--

CREATE TABLE `language` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `rank`
--

CREATE TABLE `rank` (
  `id` bigint(20) NOT NULL,
  `word_id` bigint(20) DEFAULT NULL,
  `reversed_dictionary` bit(1) NOT NULL,
  `last_use` datetime DEFAULT NULL,
  `rank_value` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `login` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pass` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `word`
--

CREATE TABLE `word` (
  `id` bigint(20) NOT NULL,
  `dictionary_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `definition` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dictionary`
--
ALTER TABLE `dictionary`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdfyihlofn3rpj7g0kpue2nbv9` (`source_language_id`),
  ADD KEY `FKpuelx1rs0c5w9oiirfkp73plw` (`target_language_id`);

--
-- Indexes for table `language`
--
ALTER TABLE `language`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rank`
--
ALTER TABLE `rank`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmlpxkhycyxrxwn21ow3705iig` (`word_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `word`
--
ALTER TABLE `word`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpevq98n5q80gkuhdf3yik5sba` (`dictionary_id`),
  ADD KEY `FKq6vj4o70ro4p1mdi1kiuv98iv` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dictionary`
--
ALTER TABLE `dictionary`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `language`
--
ALTER TABLE `language`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `rank`
--
ALTER TABLE `rank`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `word`
--
ALTER TABLE `word`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `dictionary`
--
ALTER TABLE `dictionary`
  ADD CONSTRAINT `FKdfyihlofn3rpj7g0kpue2nbv9` FOREIGN KEY (`source_language_id`) REFERENCES `language` (`id`),
  ADD CONSTRAINT `FKpuelx1rs0c5w9oiirfkp73plw` FOREIGN KEY (`target_language_id`) REFERENCES `language` (`id`);

--
-- Constraints for table `rank`
--
ALTER TABLE `rank`
  ADD CONSTRAINT `FKmlpxkhycyxrxwn21ow3705iig` FOREIGN KEY (`word_id`) REFERENCES `word` (`id`);

--
-- Constraints for table `word`
--
ALTER TABLE `word`
  ADD CONSTRAINT `FKpevq98n5q80gkuhdf3yik5sba` FOREIGN KEY (`dictionary_id`) REFERENCES `dictionary` (`id`),
  ADD CONSTRAINT `FKq6vj4o70ro4p1mdi1kiuv98iv` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
