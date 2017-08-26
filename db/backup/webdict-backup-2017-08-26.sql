-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 26, 2017 at 02:42 PM
-- Server version: 5.5.57-0+deb8u1
-- PHP Version: 5.6.30-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `webdict`
--

-- --------------------------------------------------------

--
-- Table structure for table `dictionary`
--

CREATE TABLE IF NOT EXISTS `dictionary` (
`id` bigint(20) NOT NULL,
  `source_language_id` bigint(20) DEFAULT NULL,
  `target_language_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `dictionary`
--

INSERT INTO `dictionary` (`id`, `source_language_id`, `target_language_id`) VALUES
(1, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `language`
--

CREATE TABLE IF NOT EXISTS `language` (
`id` bigint(20) NOT NULL,
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `language`
--

INSERT INTO `language` (`id`, `code`) VALUES
(1, 'en'),
(2, 'pl');

-- --------------------------------------------------------

--
-- Table structure for table `rank`
--

CREATE TABLE IF NOT EXISTS `rank` (
`id` bigint(20) NOT NULL,
  `word_id` bigint(20) DEFAULT NULL,
  `reversed_dictionary` bit(1) NOT NULL,
  `last_use` datetime DEFAULT NULL,
  `rank_value` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=442 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `rank`
--

INSERT INTO `rank` (`id`, `word_id`, `reversed_dictionary`, `last_use`, `rank_value`) VALUES
(1, 1, b'0', '2017-08-25 10:10:43', 0),
(2, 2, b'0', NULL, 0),
(3, 3, b'0', NULL, -1),
(4, 4, b'0', NULL, -1),
(5, 5, b'0', NULL, -1),
(6, 6, b'0', NULL, -1),
(7, 7, b'0', NULL, -1),
(8, 8, b'0', NULL, -1),
(9, 9, b'0', NULL, -1),
(10, 10, b'0', '2017-08-21 13:54:47', 0),
(11, 11, b'0', '2017-08-25 10:10:45', 0),
(12, 12, b'0', NULL, -1),
(13, 13, b'0', NULL, 0),
(14, 14, b'0', NULL, -1),
(15, 15, b'0', NULL, -1),
(16, 16, b'0', NULL, 0),
(17, 17, b'0', NULL, -1),
(18, 18, b'0', '2017-08-25 14:30:11', 1),
(19, 19, b'0', '2017-08-23 21:42:48', 0),
(20, 20, b'0', '2017-08-21 13:54:33', 0),
(21, 21, b'0', '2017-08-23 21:43:42', 0),
(22, 22, b'0', NULL, -1),
(23, 23, b'0', NULL, 0),
(24, 24, b'0', NULL, 0),
(25, 25, b'0', '2017-08-24 16:53:34', 0),
(26, 26, b'0', NULL, 0),
(27, 27, b'0', '2017-08-23 21:44:17', 0),
(28, 28, b'0', '2017-08-23 13:57:05', 0),
(29, 29, b'0', '2017-08-23 21:43:47', 0),
(30, 30, b'0', NULL, 0),
(31, 31, b'0', NULL, 0),
(32, 32, b'0', NULL, -1),
(33, 33, b'0', NULL, 0),
(34, 34, b'0', '2017-08-24 16:46:58', 0),
(35, 35, b'0', '2017-08-23 21:44:23', 0),
(36, 36, b'0', NULL, -1),
(37, 37, b'0', NULL, 0),
(38, 38, b'0', '2017-08-23 21:43:54', 0),
(39, 39, b'0', '2017-08-24 16:53:36', 0),
(40, 40, b'0', NULL, 0),
(41, 41, b'0', NULL, 0),
(42, 42, b'0', NULL, -1),
(43, 43, b'0', '2017-08-23 13:55:46', 0),
(44, 44, b'0', '2017-08-23 09:49:07', 0),
(45, 45, b'0', '2017-08-25 14:30:47', 0),
(46, 46, b'0', NULL, -1),
(47, 47, b'0', NULL, -1),
(48, 48, b'0', NULL, -1),
(49, 49, b'0', NULL, -1),
(50, 50, b'0', NULL, -1),
(51, 51, b'0', NULL, 0),
(52, 52, b'0', NULL, -1),
(53, 53, b'0', '2017-08-25 10:11:29', -1),
(54, 54, b'0', NULL, -1),
(55, 55, b'0', NULL, 0),
(56, 56, b'0', NULL, 0),
(57, 57, b'0', '2017-08-21 13:55:19', 0),
(58, 58, b'0', '2017-08-25 10:10:17', 1),
(59, 59, b'0', '2017-08-24 16:54:32', -1),
(60, 60, b'0', NULL, -1),
(61, 61, b'0', NULL, -1),
(62, 62, b'0', NULL, -1),
(63, 63, b'0', NULL, 0),
(64, 64, b'0', NULL, 0),
(65, 65, b'0', NULL, 0),
(66, 66, b'0', '2017-08-25 10:10:30', 0),
(67, 67, b'0', NULL, -1),
(68, 68, b'0', NULL, -1),
(69, 69, b'0', NULL, 0),
(70, 70, b'0', NULL, 0),
(71, 71, b'0', NULL, -1),
(72, 72, b'0', NULL, -1),
(73, 73, b'0', NULL, -1),
(74, 74, b'0', NULL, -1),
(75, 75, b'0', NULL, -1),
(76, 76, b'0', '2017-08-23 21:44:03', 0),
(77, 77, b'0', NULL, 0),
(78, 78, b'0', NULL, -1),
(79, 79, b'0', NULL, -1),
(80, 80, b'0', '2017-08-23 21:42:40', 0),
(81, 81, b'0', NULL, 0),
(82, 82, b'0', NULL, 0),
(83, 83, b'0', NULL, -1),
(84, 84, b'0', NULL, 0),
(85, 85, b'0', '2017-08-21 13:54:59', 0),
(86, 86, b'0', NULL, 0),
(87, 87, b'0', NULL, 0),
(88, 88, b'0', NULL, 0),
(89, 89, b'0', NULL, 0),
(90, 90, b'0', '2017-08-21 13:55:22', 0),
(91, 91, b'0', NULL, -1),
(92, 92, b'0', NULL, 0),
(93, 93, b'0', NULL, -1),
(94, 94, b'0', NULL, 0),
(95, 95, b'0', NULL, 0),
(96, 96, b'0', NULL, 0),
(97, 97, b'0', '2017-08-24 16:53:20', 0),
(98, 98, b'0', '2017-08-25 10:11:23', 0),
(99, 99, b'0', NULL, -1),
(100, 100, b'0', NULL, -1),
(101, 101, b'0', NULL, -1),
(102, 102, b'0', '2017-08-23 21:44:52', 0),
(103, 103, b'0', '2017-08-23 12:29:04', 0),
(104, 104, b'0', NULL, -1),
(105, 105, b'0', NULL, -1),
(106, 106, b'0', NULL, 0),
(107, 107, b'0', NULL, 0),
(108, 108, b'0', NULL, -1),
(109, 109, b'0', NULL, 0),
(110, 110, b'0', NULL, -1),
(111, 111, b'0', NULL, 0),
(112, 112, b'0', NULL, 0),
(113, 113, b'0', '2017-08-23 21:45:00', 0),
(114, 114, b'0', '2017-08-23 21:43:06', 0),
(115, 115, b'0', NULL, 0),
(116, 116, b'0', NULL, 0),
(117, 117, b'0', NULL, 0),
(118, 118, b'0', NULL, -1),
(119, 119, b'0', NULL, -1),
(120, 120, b'0', NULL, -1),
(121, 121, b'0', NULL, 0),
(122, 122, b'0', NULL, -1),
(123, 123, b'0', NULL, -1),
(124, 124, b'0', NULL, 0),
(125, 125, b'0', '2017-08-25 14:30:00', 3),
(126, 126, b'0', '2017-08-25 14:29:50', 5),
(127, 127, b'0', NULL, -1),
(128, 128, b'0', '2017-08-23 21:43:33', 0),
(129, 129, b'0', NULL, 0),
(130, 130, b'0', '2017-08-25 14:30:07', 2),
(131, 131, b'0', '2017-08-23 21:44:42', -1),
(132, 132, b'0', NULL, 0),
(133, 133, b'0', NULL, -1),
(134, 134, b'0', '2017-08-23 21:43:09', 0),
(135, 135, b'0', '2017-08-23 21:43:30', 0),
(136, 136, b'0', NULL, 0),
(137, 137, b'0', NULL, -1),
(138, 138, b'0', '2017-08-25 14:30:51', 2),
(139, 139, b'0', NULL, 0),
(140, 140, b'0', NULL, 0),
(141, 141, b'0', NULL, 0),
(142, 142, b'0', NULL, 0),
(143, 143, b'0', '2017-08-25 10:10:34', 0),
(144, 144, b'0', NULL, -1),
(145, 145, b'0', NULL, 0),
(146, 146, b'0', '2017-08-25 10:11:09', -1),
(147, 147, b'0', NULL, 0),
(148, 148, b'0', NULL, -1),
(149, 149, b'0', NULL, -1),
(150, 150, b'0', '2017-08-25 14:29:56', 3),
(151, 151, b'0', NULL, -1),
(152, 152, b'0', '2017-08-25 10:11:00', -1),
(153, 153, b'0', NULL, -1),
(154, 154, b'0', '2017-08-24 16:47:38', 0),
(155, 155, b'0', NULL, 0),
(156, 156, b'0', '2017-08-25 10:10:48', 0),
(157, 157, b'0', NULL, -1),
(158, 158, b'0', '2017-08-25 10:10:22', 0),
(159, 159, b'0', NULL, -1),
(160, 160, b'0', NULL, 0),
(161, 161, b'0', '2017-08-23 21:42:55', 0),
(162, 162, b'0', NULL, -1),
(163, 163, b'0', NULL, -1),
(164, 164, b'0', NULL, 0),
(165, 165, b'0', '2017-08-23 13:55:53', 0),
(166, 166, b'0', NULL, 0),
(167, 167, b'0', NULL, 0),
(168, 168, b'0', NULL, 0),
(169, 169, b'0', '2017-08-23 21:44:33', 0),
(170, 170, b'0', NULL, -1),
(171, 171, b'0', NULL, -1),
(172, 172, b'0', NULL, -1),
(173, 173, b'0', '2017-08-23 13:56:04', 0),
(174, 174, b'0', NULL, 0),
(175, 175, b'0', NULL, -1),
(176, 176, b'0', NULL, 0),
(177, 177, b'0', NULL, -1),
(178, 178, b'0', NULL, 0),
(179, 179, b'0', '2017-08-23 21:42:51', 0),
(180, 180, b'0', '2017-08-23 13:57:02', 0),
(181, 181, b'0', NULL, -1),
(182, 182, b'0', '2017-08-23 21:43:51', 0),
(183, 183, b'0', NULL, 0),
(184, 184, b'0', NULL, 0),
(185, 185, b'0', NULL, -1),
(186, 186, b'0', NULL, 0),
(187, 187, b'0', '2017-08-23 13:57:36', 0),
(188, 188, b'0', NULL, -1),
(189, 189, b'0', NULL, -1),
(190, 190, b'0', '2017-08-21 13:55:14', 0),
(191, 191, b'0', NULL, -1),
(192, 192, b'0', NULL, 0),
(193, 193, b'0', NULL, -1),
(194, 194, b'0', NULL, 0),
(195, 195, b'0', NULL, 0),
(196, 196, b'0', NULL, -1),
(197, 197, b'0', '2017-08-25 10:11:16', -1),
(198, 198, b'0', NULL, 0),
(199, 199, b'0', NULL, -1),
(200, 200, b'0', NULL, 0),
(201, 201, b'0', '2017-08-25 10:11:04', 1),
(202, 202, b'0', NULL, 0),
(203, 203, b'0', NULL, -1),
(204, 204, b'0', NULL, 0),
(205, 205, b'0', '2017-08-23 21:43:58', 0),
(206, 206, b'0', '2017-08-23 21:44:20', 0),
(207, 207, b'0', NULL, 0),
(208, 208, b'0', '2017-08-23 21:42:44', 0),
(209, 209, b'0', NULL, 0),
(210, 210, b'0', NULL, -1),
(211, 211, b'0', '2017-08-24 15:31:23', 0),
(212, 212, b'0', '2017-08-24 16:53:52', -1),
(213, 213, b'0', NULL, 0),
(214, 214, b'0', NULL, 0),
(215, 215, b'0', NULL, 0),
(216, 1, b'1', NULL, 0),
(217, 2, b'1', '2017-08-21 17:03:07', 0),
(218, 3, b'1', '2017-08-25 14:31:08', 2),
(219, 4, b'1', NULL, 0),
(220, 5, b'1', '2017-08-23 14:01:14', -1),
(221, 6, b'1', NULL, 0),
(222, 7, b'1', '2017-08-21 17:02:46', 0),
(223, 8, b'1', '2017-08-23 14:00:41', 0),
(224, 9, b'1', '2017-08-23 15:26:37', 0),
(225, 10, b'1', NULL, 0),
(226, 11, b'1', '2017-08-18 11:32:40', 0),
(227, 12, b'1', '2017-08-25 14:31:04', 0),
(228, 13, b'1', NULL, 0),
(229, 14, b'1', NULL, 0),
(230, 15, b'1', NULL, 0),
(231, 16, b'1', '2017-08-25 14:31:54', 1),
(232, 17, b'1', NULL, 0),
(233, 18, b'1', '2017-08-25 14:31:10', 0),
(234, 19, b'1', '2017-08-21 16:04:39', -1),
(235, 20, b'1', NULL, 0),
(236, 21, b'1', NULL, 0),
(237, 22, b'1', '2017-08-25 14:33:37', 1),
(238, 23, b'1', '2017-08-18 11:32:48', 0),
(239, 24, b'1', NULL, 0),
(240, 25, b'1', '2017-08-21 16:05:17', -1),
(241, 26, b'1', NULL, 0),
(242, 27, b'1', NULL, 0),
(243, 28, b'1', NULL, 0),
(244, 29, b'1', '2017-08-23 21:48:29', -1),
(245, 30, b'1', '2017-08-21 16:05:11', -1),
(246, 31, b'1', '2017-08-25 14:31:48', 0),
(247, 32, b'1', NULL, 0),
(248, 33, b'1', NULL, 0),
(249, 34, b'1', '2017-08-21 16:05:30', -1),
(250, 35, b'1', '2017-08-21 16:06:40', -1),
(251, 36, b'1', NULL, 0),
(252, 37, b'1', NULL, 0),
(253, 38, b'1', NULL, 0),
(254, 39, b'1', NULL, 0),
(255, 40, b'1', '2017-08-24 15:33:13', 0),
(256, 41, b'1', '2017-08-25 14:32:21', -1),
(257, 42, b'1', NULL, 0),
(258, 43, b'1', '2017-08-21 16:05:08', -1),
(259, 44, b'1', NULL, 0),
(260, 45, b'1', '2017-08-25 14:32:49', -1),
(261, 46, b'1', NULL, 0),
(262, 47, b'1', '2017-08-23 21:47:03', -1),
(263, 48, b'1', '2017-08-23 14:01:27', -1),
(264, 49, b'1', NULL, 0),
(265, 50, b'1', NULL, 0),
(266, 51, b'1', '2017-08-21 17:02:42', 0),
(267, 52, b'1', NULL, 0),
(268, 53, b'1', '2017-08-25 14:33:29', -1),
(269, 54, b'1', '2017-08-23 21:47:44', -1),
(270, 55, b'1', NULL, 0),
(271, 56, b'1', '2017-08-25 14:32:33', -1),
(272, 57, b'1', NULL, 0),
(273, 58, b'1', NULL, 0),
(274, 59, b'1', '2017-08-21 17:04:22', -1),
(275, 60, b'1', NULL, 0),
(276, 61, b'1', NULL, 0),
(277, 62, b'1', NULL, 0),
(278, 63, b'1', '2017-08-21 16:07:11', -1),
(279, 64, b'1', NULL, 0),
(280, 65, b'1', '2017-08-25 14:33:34', 1),
(281, 66, b'1', NULL, 0),
(282, 67, b'1', NULL, 0),
(283, 68, b'1', NULL, 0),
(284, 69, b'1', NULL, 0),
(285, 70, b'1', NULL, 0),
(286, 71, b'1', NULL, 0),
(287, 72, b'1', NULL, 0),
(288, 73, b'1', NULL, 0),
(289, 74, b'1', NULL, 0),
(290, 75, b'1', NULL, 0),
(291, 76, b'1', NULL, 0),
(292, 77, b'1', NULL, 0),
(293, 78, b'1', '2017-08-23 21:47:14', 0),
(294, 79, b'1', NULL, 0),
(295, 80, b'1', '2017-08-23 21:47:05', -1),
(296, 81, b'1', NULL, 0),
(297, 82, b'1', '2017-08-23 22:33:35', 0),
(298, 83, b'1', NULL, 0),
(299, 84, b'1', '2017-08-23 14:00:52', 0),
(300, 85, b'1', NULL, 0),
(301, 86, b'1', NULL, 0),
(302, 87, b'1', NULL, 0),
(303, 88, b'1', '2017-08-21 17:02:58', 0),
(304, 89, b'1', '2017-08-23 14:00:57', -1),
(305, 90, b'1', NULL, 0),
(306, 91, b'1', '2017-08-25 14:32:07', 1),
(307, 92, b'1', NULL, 0),
(308, 93, b'1', '2017-08-25 14:32:23', -1),
(309, 94, b'1', NULL, 0),
(310, 95, b'1', NULL, 0),
(311, 96, b'1', '2017-08-23 21:48:25', -1),
(312, 97, b'1', '2017-08-25 14:32:52', -1),
(313, 98, b'1', NULL, 0),
(314, 99, b'1', '2017-08-23 22:33:10', 0),
(315, 100, b'1', NULL, 0),
(316, 101, b'1', '2017-08-21 16:06:03', -1),
(317, 102, b'1', NULL, 0),
(318, 103, b'1', '2017-08-23 14:00:50', 0),
(319, 104, b'1', NULL, 0),
(320, 105, b'1', '2017-08-23 21:46:37', 0),
(321, 106, b'1', '2017-08-23 14:01:16', -1),
(322, 107, b'1', NULL, 0),
(323, 108, b'1', NULL, 0),
(324, 109, b'1', NULL, 0),
(325, 110, b'1', '2017-08-23 21:47:33', 0),
(326, 111, b'1', NULL, 0),
(327, 112, b'1', NULL, 0),
(328, 113, b'1', NULL, 0),
(329, 114, b'1', '2017-08-25 14:33:25', 1),
(330, 115, b'1', '2017-08-21 17:03:26', 0),
(331, 116, b'1', '2017-08-21 17:04:24', -1),
(332, 117, b'1', '2017-08-25 14:32:35', -1),
(333, 118, b'1', NULL, 0),
(334, 119, b'1', '2017-08-21 16:08:10', -1),
(335, 120, b'1', NULL, 0),
(336, 121, b'1', NULL, 0),
(337, 122, b'1', NULL, 0),
(338, 123, b'1', '2017-08-21 16:04:53', -1),
(339, 124, b'1', NULL, 0),
(340, 125, b'1', NULL, 0),
(341, 126, b'1', NULL, 0),
(342, 127, b'1', '2017-08-21 16:04:56', -1),
(343, 128, b'1', NULL, 0),
(344, 129, b'1', '2017-08-25 14:32:13', 1),
(345, 130, b'1', NULL, 0),
(346, 131, b'1', NULL, 0),
(347, 132, b'1', NULL, 0),
(348, 133, b'1', NULL, 0),
(349, 134, b'1', NULL, 0),
(350, 135, b'1', '2017-08-21 17:03:29', 0),
(351, 136, b'1', NULL, 0),
(352, 137, b'1', '2017-08-21 16:04:42', -1),
(353, 138, b'1', NULL, 0),
(354, 139, b'1', NULL, 0),
(355, 140, b'1', NULL, 0),
(356, 141, b'1', NULL, 0),
(357, 142, b'1', NULL, 0),
(358, 143, b'1', NULL, 0),
(359, 144, b'1', '2017-08-21 16:04:21', -1),
(360, 145, b'1', '2017-08-21 16:08:21', -1),
(361, 146, b'1', NULL, 0),
(362, 147, b'1', NULL, 0),
(363, 148, b'1', '2017-08-23 21:48:01', -1),
(364, 149, b'1', NULL, 0),
(365, 150, b'1', '2017-08-25 14:31:01', 2),
(366, 151, b'1', NULL, 0),
(367, 152, b'1', '2017-08-25 14:32:55', -1),
(368, 153, b'1', NULL, 0),
(369, 154, b'1', '2017-08-21 16:04:50', -1),
(370, 155, b'1', NULL, 0),
(371, 156, b'1', NULL, 0),
(372, 157, b'1', NULL, 0),
(373, 158, b'1', NULL, 0),
(374, 159, b'1', '2017-08-25 14:32:28', -1),
(375, 160, b'1', NULL, 0),
(376, 161, b'1', '2017-08-21 16:06:00', -1),
(377, 162, b'1', NULL, 0),
(378, 163, b'1', NULL, 0),
(379, 164, b'1', '2017-08-25 14:31:15', 0),
(380, 165, b'1', NULL, 0),
(381, 166, b'1', NULL, 0),
(382, 167, b'1', NULL, 0),
(383, 168, b'1', '2017-08-21 17:03:20', 0),
(384, 169, b'1', NULL, 0),
(385, 170, b'1', '2017-08-21 17:02:38', 0),
(386, 171, b'1', '2017-08-25 14:32:39', 1),
(387, 172, b'1', NULL, 0),
(388, 173, b'1', '2017-08-25 14:31:17', 0),
(389, 174, b'1', NULL, 0),
(390, 175, b'1', '2017-08-18 11:32:43', 0),
(391, 176, b'1', '2017-08-25 14:31:31', 2),
(392, 177, b'1', NULL, 0),
(393, 178, b'1', '2017-08-25 14:32:46', -1),
(394, 179, b'1', '2017-08-21 16:07:33', -1),
(395, 180, b'1', '2017-08-23 21:47:52', -1),
(396, 181, b'1', NULL, 0),
(397, 182, b'1', '2017-08-25 14:33:27', -1),
(398, 183, b'1', NULL, 0),
(399, 184, b'1', NULL, 0),
(400, 185, b'1', '2017-08-21 16:08:23', -1),
(401, 186, b'1', NULL, 0),
(402, 187, b'1', '2017-08-23 21:46:59', -1),
(403, 188, b'1', NULL, 0),
(404, 189, b'1', NULL, 0),
(405, 190, b'1', NULL, 0),
(406, 191, b'1', '2017-08-21 17:04:39', -1),
(407, 192, b'1', '2017-08-18 11:33:00', 0),
(408, 193, b'1', '2017-08-21 16:07:36', -1),
(409, 194, b'1', NULL, 0),
(410, 195, b'1', '2017-08-25 14:31:42', 0),
(411, 196, b'1', NULL, 0),
(412, 197, b'1', NULL, 0),
(413, 198, b'1', NULL, 0),
(414, 199, b'1', NULL, 0),
(415, 200, b'1', NULL, 0),
(416, 201, b'1', '2017-08-25 14:31:13', 0),
(417, 202, b'1', NULL, 0),
(418, 203, b'1', '2017-08-21 16:12:28', -1),
(419, 204, b'1', '2017-08-25 14:31:51', 0),
(420, 205, b'1', NULL, 0),
(421, 206, b'1', NULL, 0),
(422, 207, b'1', NULL, 0),
(423, 208, b'1', '2017-08-21 17:04:17', 0),
(424, 209, b'1', NULL, 0),
(425, 210, b'1', NULL, 0),
(426, 211, b'1', '2017-08-25 14:31:35', 0),
(427, 212, b'1', '2017-08-21 17:03:23', 0),
(428, 213, b'1', '2017-08-23 14:00:44', 0),
(429, 214, b'1', '2017-08-21 16:06:43', -1),
(430, 215, b'1', NULL, 0),
(431, 216, b'0', NULL, 0),
(432, 217, b'0', NULL, 0),
(433, 218, b'0', NULL, 0),
(434, 219, b'0', '2017-08-23 21:45:25', 0),
(435, 216, b'1', '2017-08-23 14:01:00', -1),
(436, 217, b'1', NULL, 0),
(437, 218, b'1', '2017-08-21 16:06:07', -1),
(438, 219, b'1', '2017-08-21 16:06:09', -1),
(439, 220, b'0', '2017-08-25 10:10:37', 0),
(440, 220, b'1', NULL, 0),
(441, 221, b'1', NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` bigint(20) NOT NULL,
  `login` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pass` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `login`, `pass`) VALUES
(1, 'igrek', 'dupa');

-- --------------------------------------------------------

--
-- Table structure for table `word`
--

CREATE TABLE IF NOT EXISTS `word` (
`id` bigint(20) NOT NULL,
  `dictionary_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `definition` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=222 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `word`
--

INSERT INTO `word` (`id`, `dictionary_id`, `user_id`, `name`, `definition`) VALUES
(1, 1, 1, 'associate', 'wspólnik, kojarzyć'),
(2, 1, 1, 'assess', 'oceniać'),
(3, 1, 1, 'legitimate', 'uzasadniony'),
(4, 1, 1, 'admittedly', 'umyślnie'),
(5, 1, 1, 'contemporary', 'współczesny'),
(6, 1, 1, 'ubiquitous', 'wszechobecny'),
(7, 1, 1, 'brevity', 'zwięzłość'),
(8, 1, 1, 'comprise', 'zawierać, składać się'),
(9, 1, 1, 'devour', 'pożerać'),
(10, 1, 1, 'strive', 'starać się'),
(11, 1, 1, 'utter', 'kompletny'),
(12, 1, 1, 'defer', 'opóźniać, odraczać'),
(13, 1, 1, 'undergo', 'zostać poddanym'),
(14, 1, 1, 'tedious', 'nudny'),
(15, 1, 1, 'in turn', 'z kolei'),
(16, 1, 1, 'immanent', 'nieodłączny'),
(17, 1, 1, 'merit', 'zasługa'),
(18, 1, 1, 'severe', 'silny, surowy'),
(19, 1, 1, 'thorough', 'gruntowny, dokładny'),
(20, 1, 1, 'throughout', 'w całym'),
(21, 1, 1, 'eminent', 'wybitny'),
(22, 1, 1, 'decoupled', 'oddzielony'),
(23, 1, 1, 'composite', 'łączny'),
(24, 1, 1, 'detour', 'objazd'),
(25, 1, 1, 'admit', 'przyznawać się, wpuszczać'),
(26, 1, 1, 'accordingly', 'w związku z tym'),
(27, 1, 1, 'depict', 'przedstawiać'),
(28, 1, 1, 'conceive', 'pojąć'),
(29, 1, 1, 'reminiscent', 'pamiętny'),
(30, 1, 1, 'superficial', 'powierzchowny'),
(31, 1, 1, 'robust', 'solidny'),
(32, 1, 1, 'elaborate', 'opracować, rozwijać, dopracowany, zawiły'),
(33, 1, 1, 'leap', 'skok'),
(34, 1, 1, 'intricate', 'zawiły'),
(35, 1, 1, 'contrive', 'obmyślać'),
(36, 1, 1, 'notion', 'pojęcie'),
(37, 1, 1, 'compliant', 'zgodny'),
(38, 1, 1, 'intimidate', 'zastraszać'),
(39, 1, 1, 'prominent', 'wybitny, sławny'),
(40, 1, 1, 'protuberant', 'wypukły'),
(41, 1, 1, 'in terms of', 'jeśli chodzi o, pod względem, z punktu widzenia'),
(42, 1, 1, 'vulnerable', 'podatny'),
(43, 1, 1, 'leverage', 'wpływ, wykorzystać'),
(44, 1, 1, 'eligible', 'nadający się'),
(45, 1, 1, 'deliberate', 'celowy, przemyślany, rozważać'),
(46, 1, 1, 'substantial', 'znaczny'),
(47, 1, 1, 'compartment', 'przegroda'),
(48, 1, 1, 'concise', 'zwięzły'),
(49, 1, 1, 'oath', 'przysięga, przekleństwo'),
(50, 1, 1, 'derelict', 'porzucony'),
(51, 1, 1, 'endow', 'obdarzyć'),
(52, 1, 1, 'nudge', 'trącić'),
(53, 1, 1, 'thaw', 'odwilż, rozmrażać'),
(54, 1, 1, 'bifurcate', 'rozwidlać się'),
(55, 1, 1, 'dispatch', 'wysyłać'),
(56, 1, 1, 'summit', 'wierzchołek'),
(57, 1, 1, 'retain', 'zachować'),
(58, 1, 1, 'prone', 'skłonny'),
(59, 1, 1, 'prune', 'redukować, przycinać'),
(60, 1, 1, 'implicit', 'domniemany'),
(61, 1, 1, 'explicit', 'wyraźny'),
(62, 1, 1, 'distinct', 'oddzielny, wyrazisty'),
(63, 1, 1, 'rupture', 'pęknięcie'),
(64, 1, 1, 'reciprocal', 'odwrotność, wzajemny, obustronny'),
(65, 1, 1, 'obsolete', 'przestarzały'),
(66, 1, 1, 'constraint', 'przymus'),
(67, 1, 1, 'insolence', 'zuchwałość'),
(68, 1, 1, 'comprehend', 'zrozumieć'),
(69, 1, 1, 'comprehensible', 'zrozumiały'),
(70, 1, 1, 'comprehensive', 'wszechstronny, pełny'),
(71, 1, 1, 'auxiliary', 'pomocniczy'),
(72, 1, 1, 'forsake', 'porzucic'),
(73, 1, 1, 'ancestry', 'pochodzenie'),
(74, 1, 1, 'recipient', 'odbiorca'),
(75, 1, 1, 'sake', 'wzgląd'),
(76, 1, 1, 'endeavour', 'usiłować, dążenie'),
(77, 1, 1, 'retrieve', 'odzyskać'),
(78, 1, 1, 'retract', 'wycofać'),
(79, 1, 1, 'commit', 'powierzać, oddać'),
(80, 1, 1, 'commitment', 'zobowiązanie'),
(81, 1, 1, 'aggregate', 'sumaryczny'),
(82, 1, 1, 'stash', 'schować na potem'),
(83, 1, 1, 'amend', 'poprawić'),
(84, 1, 1, 'vicious', 'zły, złośliwy'),
(85, 1, 1, 'loom', 'wynurzać się (z mgły)'),
(86, 1, 1, 'twine', 'wykręcać'),
(87, 1, 1, 'bundle', 'wiązka'),
(88, 1, 1, 'invoke', 'odwołać się, wezwać'),
(89, 1, 1, 'scaffolding', 'rusztowanie'),
(90, 1, 1, 'tenant', 'lokator'),
(91, 1, 1, 'rampart', 'wał'),
(92, 1, 1, 'decent', 'przyzwoity'),
(93, 1, 1, 'precede', 'poprzedzać'),
(94, 1, 1, 'obliterate', 'wymazać'),
(95, 1, 1, 'latter', 'drugi'),
(96, 1, 1, 'encounter', 'natknąć się, starcie'),
(97, 1, 1, 'clutter', 'nieład'),
(98, 1, 1, 'cluster', 'grupa'),
(99, 1, 1, 'superfluous', 'zbędny'),
(100, 1, 1, 'unobtrusive', 'dyskretny'),
(101, 1, 1, 'descendant', 'potomek'),
(102, 1, 1, 'unlikely', 'mało prawdopodobne'),
(103, 1, 1, 'repel', 'odpychać'),
(104, 1, 1, 'barley', 'jęczmień'),
(105, 1, 1, 'wheat', 'pszenica'),
(106, 1, 1, 'yeast', 'drożdże'),
(107, 1, 1, 'maize', 'kukurydza'),
(108, 1, 1, 'ripen', 'dojrzewać'),
(109, 1, 1, 'outweigh', 'przeważać'),
(110, 1, 1, 'perimeter', 'obwód'),
(111, 1, 1, 'despicable', 'podły'),
(112, 1, 1, 'synopsis', 'streszczenie'),
(113, 1, 1, 'concurrent', 'współbieżny'),
(114, 1, 1, 'coincident', 'zbieżny, zgodny, równoczesny'),
(115, 1, 1, 'plausible', 'wiarygodny'),
(116, 1, 1, 'parcel', 'paczka'),
(117, 1, 1, 'dignity', 'godność'),
(118, 1, 1, 'persistent', 'trwały'),
(119, 1, 1, 'ellipsis', 'wielokropek'),
(120, 1, 1, 'seal off', 'zamykać dostęp do'),
(121, 1, 1, 'breach', 'wyłom, przełamać'),
(122, 1, 1, 'spurious', 'nieprawdziwy'),
(123, 1, 1, 'ambiguous', 'dwuznaczny'),
(124, 1, 1, 'ostensibly', 'rzekomo'),
(125, 1, 1, 'propriety', 'stosowność'),
(126, 1, 1, 'proprietary', 'własny'),
(127, 1, 1, 'scarce', 'ograniczony'),
(128, 1, 1, 'quotas', 'przydział'),
(129, 1, 1, 'bias', 'odchyłka, stronniczość, uprzedzenie'),
(130, 1, 1, 'cater', 'zaopatrywać'),
(131, 1, 1, 'cumbersome', 'niewygodny'),
(132, 1, 1, 'payload', 'ładunek'),
(133, 1, 1, 'sentry', 'wartownik'),
(134, 1, 1, 'suffice', 'wystarczać'),
(135, 1, 1, 'designate', 'wyznaczać'),
(136, 1, 1, 'denote', 'oznaczyć'),
(137, 1, 1, 'tier', 'poziom, warstwa'),
(138, 1, 1, 'subsequent', 'późniejszy'),
(139, 1, 1, 'frontier', 'granica'),
(140, 1, 1, 'depiction', 'opis'),
(141, 1, 1, 'invoice', 'faktura'),
(142, 1, 1, 'inference', 'wnioskowanie'),
(143, 1, 1, 'crucial', 'rozstrzygający'),
(144, 1, 1, 'suppress', 'stłumić'),
(145, 1, 1, 'affinity', 'podobieństwo'),
(146, 1, 1, 'augmented', 'rozszerzony'),
(147, 1, 1, 'inhabitant', 'mieszkaniec'),
(148, 1, 1, 'mock', 'próbny'),
(149, 1, 1, 'intercept', 'przechwytywać'),
(150, 1, 1, 'decline', 'spadek, podupadać'),
(151, 1, 1, 'cognitive', 'poznawczy'),
(152, 1, 1, 'contiguous', 'przyległy'),
(153, 1, 1, 'collate', 'zestawić'),
(154, 1, 1, 'verbatim', 'dosłownie'),
(155, 1, 1, 'coercion', 'wymuszenie, przymus'),
(156, 1, 1, 'thoroughly', 'całkowicie'),
(157, 1, 1, 'unattended', 'bez nadzoru'),
(158, 1, 1, 'nonetheless', 'pomimo to'),
(159, 1, 1, 'precedence', 'pierwszeństwo'),
(160, 1, 1, 'pedigree', 'rodowód'),
(161, 1, 1, 'solely', 'wyłącznie'),
(162, 1, 1, 'merely', 'jedynie'),
(163, 1, 1, 'conform', 'dostosowywać się'),
(164, 1, 1, 'contradiction', 'sprzeczność'),
(165, 1, 1, 'compel', 'zmuszać'),
(166, 1, 1, 'inhibit', 'powstrzymywać'),
(167, 1, 1, 'verity', 'prawda'),
(168, 1, 1, 'recede', 'cofać się'),
(169, 1, 1, 'scatter', 'rozrzucać, rozproszyć się'),
(170, 1, 1, 'bulk', 'hurt'),
(171, 1, 1, 'evade', 'unikać'),
(172, 1, 1, 'assessment', 'oszacowanie'),
(173, 1, 1, 'abruptly', 'nagle'),
(174, 1, 1, 'perseverance', 'wytrwałość'),
(175, 1, 1, 'hence', 'w związku z tym'),
(176, 1, 1, 'resort', 'uciekać się, uciekanie się'),
(177, 1, 1, 'as a last resort', 'w ostateczności'),
(178, 1, 1, 'invariant', 'niezmienny'),
(179, 1, 1, 'sophisticated', 'wyrafinowany'),
(180, 1, 1, 'refer to', 'oznaczać, odnosić się do, korzystać z'),
(181, 1, 1, 'due to', 'z powodu'),
(182, 1, 1, 'tread', 'chód, bieżnik'),
(183, 1, 1, 'reluctant', 'niechętny'),
(184, 1, 1, 'consecutive', 'kolejny'),
(185, 1, 1, 'stare', 'gapić się'),
(186, 1, 1, 'viral', 'wirusowy'),
(187, 1, 1, 'subvert', 'obalać'),
(188, 1, 1, 'dormant', 'bezczynny, śpiący'),
(189, 1, 1, 'fragrance', 'zapach'),
(190, 1, 1, 'stake', 'kołek, stawka, obstawiać'),
(191, 1, 1, 'streak', 'pasmo, smuga'),
(192, 1, 1, 'sustain', 'podtrzymywać'),
(193, 1, 1, 'spree', 'hulanka, szał'),
(194, 1, 1, 'perpetual', 'wieczny'),
(195, 1, 1, 'lurk', 'czaić się'),
(196, 1, 1, 'peek', 'zerkać'),
(197, 1, 1, 'peak', 'szczyt'),
(198, 1, 1, 'compulsory', 'przymusowy'),
(199, 1, 1, 'bizarre', 'dziwaczny'),
(200, 1, 1, 'ultimate', 'ostateczny, największy'),
(201, 1, 1, 'latch', 'zatrzask'),
(202, 1, 1, 'grieve', 'smucić się'),
(203, 1, 1, 'mutually', 'wzajemnie'),
(204, 1, 1, 'cohesion', 'spójność'),
(205, 1, 1, 'revoke', 'unieważniać'),
(206, 1, 1, 'endurance', 'wytrzymałość'),
(207, 1, 1, 'diminish', 'pomniejszać'),
(208, 1, 1, 'disrupt', 'zakłócać, krzyżować'),
(209, 1, 1, 'altogether', 'całkowicie, generalnie'),
(210, 1, 1, 'yield', 'zysk, przynosić plon, ustępować'),
(211, 1, 1, 'negligible', 'nieistotny'),
(212, 1, 1, 'in turns', 'z kolei'),
(213, 1, 1, 'albeit', 'aczkolwiek'),
(214, 1, 1, 'thyme', 'tymianek'),
(215, 1, 1, 'prior to', 'przed'),
(216, 1, 1, 'extent', 'zakres, zasięg'),
(217, 1, 1, 'surplus', 'nadmiar, nadmiarowy'),
(218, 1, 1, 'breed', 'hodować'),
(219, 1, 1, 'thus', 'a zatem'),
(220, 1, 1, 'remarkable', 'nadzwyczajny'),
(221, 1, 1, 'mediocre', 'przeciętny, mierny');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dictionary`
--
ALTER TABLE `dictionary`
 ADD PRIMARY KEY (`id`), ADD KEY `FKdfyihlofn3rpj7g0kpue2nbv9` (`source_language_id`), ADD KEY `FKpuelx1rs0c5w9oiirfkp73plw` (`target_language_id`);

--
-- Indexes for table `language`
--
ALTER TABLE `language`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rank`
--
ALTER TABLE `rank`
 ADD PRIMARY KEY (`id`), ADD KEY `FKmlpxkhycyxrxwn21ow3705iig` (`word_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `word`
--
ALTER TABLE `word`
 ADD PRIMARY KEY (`id`), ADD KEY `FKpevq98n5q80gkuhdf3yik5sba` (`dictionary_id`), ADD KEY `FKq6vj4o70ro4p1mdi1kiuv98iv` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dictionary`
--
ALTER TABLE `dictionary`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `language`
--
ALTER TABLE `language`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `rank`
--
ALTER TABLE `rank`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=442;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `word`
--
ALTER TABLE `word`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=222;
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
