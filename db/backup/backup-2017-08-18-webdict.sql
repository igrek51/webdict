-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 18, 2017 at 07:37 AM
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
-- Table structure for table `dict_entry`
--

CREATE TABLE IF NOT EXISTS `dict_entry` (
`id` bigint(20) NOT NULL,
  `definition` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `dictionary_id` bigint(20) NOT NULL,
  `last_use` datetime DEFAULT NULL,
  `rank` double NOT NULL,
  `word` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=216 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `dict_entry`
--

INSERT INTO `dict_entry` (`id`, `definition`, `dictionary_id`, `last_use`, `rank`, `word`) VALUES
(1, 'wspólnik, kojarzyć', 1, '2017-08-14 16:47:34', 1, 'associate'),
(2, 'oceniać', 1, '2017-07-19 00:19:38', 0, 'assess'),
(3, 'uzasadniony', 1, '2017-07-17 22:49:07', -1, 'legitimate'),
(4, 'umyślnie', 1, '2017-07-19 00:21:51', -1, 'admittedly'),
(5, 'współczesny', 1, '2017-07-17 22:49:16', -1, 'contemporary'),
(6, 'wszechobecny', 1, '2017-07-17 22:49:20', -1, 'ubiquitous'),
(7, 'zwięzłość', 1, '2017-07-17 22:49:23', -1, 'brevity'),
(8, 'zawierać, składać się', 1, '2017-08-14 16:51:27', -1, 'comprise'),
(9, 'pożerać', 1, '2017-07-17 22:49:34', -1, 'devour'),
(10, 'starać się', 1, '2017-08-14 16:50:34', 1, 'strive'),
(11, 'kompletny', 1, '2017-08-11 14:11:07', 0, 'utter'),
(12, 'opóźniać, odraczać', 1, '2017-07-17 22:49:45', -1, 'defer'),
(13, 'zostać poddanym', 1, '2017-08-14 09:58:28', 0, 'undergo'),
(14, 'nudny', 1, '2017-07-17 22:49:54', -1, 'tedious'),
(15, 'z kolei', 1, '2017-08-14 16:51:17', -1, 'in turn'),
(16, 'nieodłączny', 1, '2017-08-14 16:45:44', 0, 'immanent'),
(17, 'zasługa', 1, '2017-07-19 00:24:22', -1, 'merit'),
(18, 'silny, surowy', 1, NULL, 0, 'severe'),
(19, 'gruntowny, dokładny', 1, '2017-08-16 16:51:01', 3, 'thorough'),
(20, 'w całym', 1, '2017-08-14 16:50:54', 1, 'throughout'),
(21, 'wybitny', 1, '2017-08-14 16:48:08', 1, 'eminent'),
(22, 'oddzielony', 1, '2017-08-14 10:00:25', -1, 'decoupled'),
(23, 'łączny', 1, '2017-07-19 00:24:28', 0, 'composite'),
(24, 'objazd', 1, '2017-07-21 10:50:52', 0, 'detour'),
(25, 'przyznawać się, wpuszczać', 1, '2017-08-16 16:51:19', 5, 'admit'),
(26, 'w związku z tym', 1, '2017-08-14 16:48:44', 0, 'accordingly'),
(27, 'przedstawiać', 1, '2017-08-16 16:50:56', 5, 'depict'),
(28, 'pojąć', 1, '2017-08-16 16:50:48', 6, 'conceive'),
(29, 'pamiętny', 1, '2017-08-14 16:44:36', 2, 'reminiscent'),
(30, 'powierzchowny', 1, '2017-08-11 14:11:12', 0, 'superficial'),
(31, 'solidny', 1, NULL, 0, 'robust'),
(32, 'opracować, rozwijać, dopracowany, zawiły', 1, '2017-07-19 00:22:23', -1, 'elaborate'),
(33, 'skok', 1, NULL, 0, 'leap'),
(34, 'zawiły', 1, '2017-08-14 16:47:09', 1, 'intricate'),
(35, 'obmyślać', 1, '2017-08-16 16:50:40', 8, 'contrive'),
(36, 'pojęcie', 1, '2017-08-14 16:46:00', -1, 'notion'),
(37, 'zgodny', 1, NULL, 0, 'compliant'),
(38, 'zastraszać', 1, '2017-08-16 16:51:29', 5, 'intimidate'),
(39, 'wybitny, sławny', 1, '2017-08-14 16:49:18', 1, 'prominent'),
(40, 'wypukły', 1, NULL, 0, 'protuberant'),
(41, 'jeśli chodzi o, pod względem, z punktu widzenia', 1, '2017-07-19 00:23:48', 0, 'in terms of'),
(42, 'podatny', 1, '2017-07-19 00:25:00', -1, 'vulnerable'),
(43, 'wpływ, wykorzystać', 1, '2017-08-14 16:49:35', 1, 'leverage'),
(44, 'nadający się', 1, '2017-08-14 16:47:16', 1, 'eligible'),
(45, 'celowy, przemyślany, rozważać', 1, NULL, 0, 'deliberate'),
(46, 'znaczny', 1, '2017-07-19 00:24:12', -1, 'substantial'),
(47, 'przegroda', 1, '2017-08-14 10:00:11', -1, 'compartment'),
(48, 'zwięzły', 1, '2017-07-17 23:03:10', -1, 'concise'),
(49, 'przysięga, przekleństwo', 1, '2017-08-14 16:47:24', -1, 'oath'),
(50, 'porzucony', 1, '2017-07-19 00:19:50', -1, 'derelict'),
(51, 'obdarzyć', 1, '2017-07-17 23:03:49', 0, 'endow'),
(52, 'trącić', 1, '2017-08-14 16:48:56', -1, 'nudge'),
(53, 'odwilż, rozmrażać', 1, NULL, 0, 'thaw'),
(54, 'rozwidlać się', 1, '2017-07-17 23:03:58', -1, 'bifurcate'),
(55, 'wysyłać', 1, '2017-07-19 00:23:11', 0, 'dispatch'),
(56, 'wierzchołek', 1, NULL, 0, 'summit'),
(57, 'zachować', 1, '2017-08-14 16:46:38', 1, 'retain'),
(58, 'skłonny', 1, '2017-08-14 16:48:20', 1, 'prone'),
(59, 'redukować, przycinać', 1, '2017-08-10 14:43:03', 0, 'prune'),
(60, 'domniemany', 1, '2017-08-14 16:48:32', -1, 'implicit'),
(61, 'wyraźny', 1, '2017-07-17 23:03:45', -1, 'explicit'),
(62, 'oddzielny, wyrazisty', 1, '2017-07-19 00:25:32', -1, 'distinct'),
(63, 'pęknięcie', 1, NULL, 0, 'rupture'),
(64, 'odwrotność, wzajemny, obustronny', 1, NULL, 0, 'reciprocal'),
(65, 'przestarzały', 1, NULL, 0, 'obsolete'),
(66, 'przymus', 1, '2017-07-19 00:22:35', 0, 'constraint'),
(67, 'zuchwałość', 1, '2017-08-14 16:47:59', -1, 'insolence'),
(68, 'zrozumieć', 1, '2017-07-19 00:23:42', -1, 'comprehend'),
(69, 'zrozumiały', 1, NULL, 0, 'comprehensible'),
(70, 'wszechstronny, pełny', 1, '2017-08-14 16:45:37', 0, 'comprehensive'),
(71, 'pomocniczy', 1, '2017-07-19 00:20:29', -1, 'auxiliary'),
(72, 'porzucic', 1, '2017-07-17 23:03:20', -1, 'forsake'),
(73, 'pochodzenie', 1, '2017-08-14 09:58:53', -1, 'ancestry'),
(74, 'odbiorca', 1, '2017-08-14 10:00:39', -1, 'recipient'),
(75, 'wzgląd', 1, '2017-08-14 16:46:24', -1, 'sake'),
(76, 'usiłować, dążenie', 1, '2017-08-14 16:49:08', 1, 'endeavour'),
(77, 'odzyskać', 1, '2017-07-21 10:50:38', 0, 'retrieve'),
(78, 'wycofać', 1, '2017-08-14 10:00:14', -1, 'retract'),
(79, 'powierzać, oddać', 1, '2017-08-14 16:46:34', -1, 'commit'),
(80, 'zobowiązanie', 1, '2017-08-16 16:52:21', 3, 'commitment'),
(81, 'sumaryczny', 1, NULL, 0, 'aggregate'),
(82, 'schować na potem', 1, '2017-08-14 16:48:03', 0, 'stash'),
(83, 'poprawić', 1, '2017-07-19 00:23:56', -1, 'amend'),
(84, 'zły, złośliwy', 1, NULL, 0, 'vicious'),
(85, 'wynurzać się (z mgły)', 1, '2017-08-14 16:45:12', 1, 'loom'),
(86, 'wykręcać', 1, NULL, 0, 'twine'),
(87, 'wiązka', 1, NULL, 0, 'bundle'),
(88, 'odwołać się, wezwać', 1, NULL, 0, 'invoke'),
(89, 'rusztowanie', 1, NULL, 0, 'scaffolding'),
(90, 'lokator', 1, '2017-08-14 16:49:20', 1, 'tenant'),
(91, 'wał', 1, '2017-08-14 16:46:27', -1, 'rampart'),
(92, 'przyzwoity', 1, '2017-07-17 23:00:13', 0, 'decent'),
(93, 'poprzedzać', 1, '2017-07-19 00:24:52', -1, 'precede'),
(94, 'wymazać', 1, NULL, 0, 'obliterate'),
(95, 'drugi', 1, NULL, 0, 'latter'),
(96, 'natknąć się, starcie', 1, '2017-07-17 22:59:48', 0, 'encounter'),
(97, 'nieład', 1, '2017-08-14 16:50:21', 1, 'clutter'),
(98, 'grupa', 1, '2017-07-17 23:04:14', 0, 'cluster'),
(99, 'zbędny', 1, '2017-07-19 00:20:33', -1, 'superfluous'),
(100, 'dyskretny', 1, '2017-08-14 16:49:14', -1, 'unobtrusive'),
(101, 'potomek', 1, '2017-08-14 16:48:24', -1, 'descendant'),
(102, 'mało prawdopodobne', 1, NULL, 0, 'unlikely'),
(103, 'odpychać', 1, '2017-08-14 16:46:43', 1, 'repel'),
(104, 'jęczmień', 1, '2017-07-19 00:26:07', -1, 'barley'),
(105, 'pszenica', 1, '2017-08-14 16:48:26', -1, 'wheat'),
(106, 'drożdże', 1, '2017-07-21 10:51:11', 0, 'yeast'),
(107, 'kukurydza', 1, NULL, 0, 'maize'),
(108, 'dojrzewać', 1, '2017-08-14 16:49:04', -1, 'ripen'),
(109, 'przeważać', 1, NULL, 0, 'outweigh'),
(110, 'obwód', 1, '2017-07-19 00:22:47', -1, 'perimeter'),
(111, 'podły', 1, NULL, 0, 'despicable'),
(112, 'streszczenie', 1, '2017-08-11 16:46:49', 0, 'synopsis'),
(113, 'współbieżny', 1, '2017-08-14 16:46:56', 0, 'concurrent'),
(114, 'zbieżny, zgodny, równoczesny', 1, '2017-08-16 16:51:48', 1, 'coincident'),
(115, 'wiarygodny', 1, '2017-08-14 16:45:25', 0, 'plausible'),
(116, 'paczka', 1, '2017-08-14 16:45:58', 0, 'parcel'),
(117, 'godność', 1, NULL, 0, 'dignity'),
(118, 'trwały', 1, '2017-08-14 16:50:28', -1, 'persistent'),
(119, 'wielokropek', 1, '2017-07-19 00:25:16', -1, 'ellipsis'),
(120, 'zamykać dostęp do', 1, '2017-08-14 16:49:27', -1, 'seal off'),
(121, 'wyłom, przełamać', 1, '2017-07-19 00:23:04', 0, 'breach'),
(122, 'nieprawdziwy', 1, '2017-07-17 23:03:28', -1, 'spurious'),
(123, 'dwuznaczny', 1, '2017-07-17 23:03:24', -1, 'ambiguous'),
(124, 'rzekomo', 1, NULL, 0, 'ostensibly'),
(125, 'stosowność', 1, '2017-08-14 16:47:48', 1, 'propriety'),
(126, 'własny', 1, '2017-08-14 16:46:20', 1, 'proprietary'),
(127, 'ograniczony', 1, '2017-08-14 16:46:32', -1, 'scarce'),
(128, 'przydział', 1, '2017-08-16 16:51:32', 2, 'quotas'),
(129, 'odchyłka, stronniczość, uprzedzenie', 1, NULL, 0, 'bias'),
(130, 'zaspokajać', 1, '2017-08-14 16:47:30', 1, 'cater'),
(131, 'niewygodny', 1, '2017-08-11 14:11:15', 0, 'cumbersome'),
(132, 'ładunek', 1, '2017-07-17 23:02:59', 0, 'payload'),
(133, 'wartownik', 1, '2017-08-14 16:50:31', -1, 'sentry'),
(134, 'wystarczać', 1, '2017-08-14 16:44:51', 2, 'suffice'),
(135, 'wyznaczać', 1, '2017-08-14 16:50:38', 1, 'designate'),
(136, 'oznaczyć', 1, NULL, 0, 'denote'),
(137, 'poziom, warstwa', 1, '2017-07-19 00:25:35', -1, 'tier'),
(138, 'późniejszy', 1, '2017-08-14 16:47:42', 1, 'subsequent'),
(139, 'granica', 1, '2017-07-21 10:50:56', 0, 'frontier'),
(140, 'opis', 1, NULL, 0, 'depiction'),
(141, 'faktura', 1, NULL, 0, 'invoice'),
(142, 'wnioskowanie', 1, NULL, 0, 'inference'),
(143, 'rozstrzygający', 1, '2017-08-14 16:49:24', 1, 'crucial'),
(144, 'stłumić', 1, '2017-07-19 00:25:25', -1, 'suppress'),
(145, 'podobieństwo', 1, '2017-08-11 16:46:47', 0, 'affinity'),
(146, 'rozszerzony', 1, NULL, 0, 'augmented'),
(147, 'mieszkaniec', 1, '2017-08-10 14:45:02', 0, 'inhabitant'),
(148, 'próbny', 1, '2017-08-14 16:46:11', -1, 'mock'),
(149, 'przechwytywać', 1, '2017-08-14 09:59:51', -1, 'intercept'),
(150, 'spadek, podupadać', 1, '2017-08-14 16:48:29', 1, 'decline'),
(151, 'poznawczy', 1, '2017-07-19 00:24:04', -1, 'cognitive'),
(152, 'przyległy', 1, '2017-08-14 16:45:55', 0, 'contiguous'),
(153, 'zestawić', 1, '2017-08-14 16:48:54', -1, 'collate'),
(154, 'dosłownie', 1, '2017-08-14 16:50:45', 1, 'verbatim'),
(155, 'wymuszenie, przymus', 1, '2017-08-10 14:43:21', 0, 'coercion'),
(156, 'całkowicie', 1, '2017-08-16 16:51:23', 5, 'thoroughly'),
(157, 'bez nadzoru', 1, '2017-07-17 23:03:55', -1, 'unattended'),
(158, 'pomimo to', 1, '2017-08-14 16:48:35', 1, 'nonetheless'),
(159, 'pierwszeństwo', 1, '2017-07-17 23:03:03', -1, 'precedence'),
(160, 'rodowód', 1, '2017-07-21 10:51:08', 0, 'pedigree'),
(161, 'wyłącznie', 1, '2017-08-16 16:52:15', 2, 'solely'),
(162, 'jedynie', 1, '2017-08-11 14:11:21', -1, 'merely'),
(163, 'dostosowywać się', 1, '2017-07-19 00:26:03', -1, 'conform'),
(164, 'sprzeczność', 1, NULL, 0, 'contradiction'),
(165, 'zmuszać', 1, '2017-08-14 16:50:17', 1, 'compel'),
(166, 'powstrzymywać', 1, NULL, 0, 'inhibit'),
(167, 'prawda', 1, '2017-07-21 10:51:05', 0, 'verity'),
(168, 'cofać się', 1, '2017-07-24 09:42:59', 0, 'recede'),
(169, 'rozrzucać, rozproszyć się', 1, NULL, 0, 'scatter'),
(170, 'hurt', 1, '2017-07-19 00:24:44', -1, 'bulk'),
(171, 'unikać', 1, '2017-08-14 09:58:56', -1, 'evade'),
(172, 'oszacowanie', 1, '2017-07-17 22:59:36', -1, 'assessment'),
(173, 'nagle', 1, '2017-08-14 16:49:32', 1, 'abruptly'),
(174, 'wytrwałość', 1, '2017-08-10 14:44:24', 0, 'perseverance'),
(175, 'w związku z tym', 1, '2017-07-19 00:25:58', -1, 'hence'),
(176, 'uciekać się, uciekanie się', 1, '2017-07-20 11:03:58', 0, 'resort'),
(177, 'w ostateczności', 1, '2017-08-14 09:59:01', -1, 'as a last resort'),
(178, 'niezmienny', 1, '2017-07-20 11:05:20', 0, 'invariant'),
(179, 'wyrafinowany', 1, '2017-08-16 16:51:35', 2, 'sophisticated'),
(180, 'oznaczać, odnosić się do, korzystać z', 1, '2017-08-16 16:51:40', 3, 'refer to'),
(181, 'z powodu', 1, '2017-08-14 16:49:11', -1, 'due to'),
(182, 'chód, bieżnik', 1, '2017-08-14 16:51:00', 1, 'tread'),
(183, 'niechętny', 1, '2017-07-21 12:49:54', 0, 'reluctant'),
(184, 'kolejny', 1, '2017-08-14 16:51:09', 0, 'consecutive'),
(185, 'gapić się', 1, '2017-08-14 09:59:40', -1, 'stare'),
(186, 'wirusowy', 1, '2017-08-14 09:58:30', 0, 'viral'),
(187, 'obalać', 1, '2017-08-14 16:47:13', 1, 'subvert'),
(188, 'bezczynny, śpiący', 1, '2017-08-14 16:48:39', -1, 'dormant'),
(189, 'zapach', 1, '2017-08-14 16:46:45', -1, 'fragrance'),
(190, 'kołek, stawka, obstawiać', 1, '2017-08-16 16:51:55', 1, 'stake'),
(191, 'pasmo, smuga', 1, '2017-08-14 16:49:00', -1, 'streak'),
(192, 'podtrzymywać', 1, '2017-07-22 10:07:06', 0, 'sustain'),
(193, 'hulanka, szał', 1, '2017-08-14 16:46:09', -1, 'spree'),
(194, 'wieczny', 1, '2017-08-14 16:45:53', 0, 'perpetual'),
(195, 'czaić się', 1, '2017-07-24 09:15:22', 0, 'lurk'),
(196, 'zerkać', 1, '2017-08-14 16:46:04', -1, 'peek'),
(197, 'szczyt', 1, '2017-08-14 16:47:05', 0, 'peak'),
(198, 'przymusowy', 1, '2017-07-28 01:19:38', 0, 'compulsory'),
(199, 'dziwaczny', 1, '2017-08-14 10:00:36', -1, 'bizarre'),
(200, 'ostateczny, największy', 1, '2017-08-02 10:52:26', 0, 'ultimate'),
(201, 'zatrzask', 1, '2017-08-06 02:26:24', 0, 'latch'),
(202, 'smucić się', 1, '2017-08-07 00:04:55', 0, 'grieve'),
(203, 'wzajemnie', 1, '2017-08-14 16:49:42', -1, 'mutually'),
(204, 'spójność', 1, '2017-08-09 09:21:01', 0, 'cohesion'),
(205, 'unieważniać', 1, '2017-08-14 16:45:47', 2, 'revoke'),
(206, 'wytrzymałość', 1, '2017-08-14 16:46:49', 1, 'endurance'),
(207, 'pomniejszać', 1, '2017-08-10 09:29:52', 0, 'diminish'),
(208, 'zakłócać, krzyżować', 1, '2017-08-14 16:46:17', 1, 'disrupt'),
(209, 'całkowicie, generalnie', 1, '2017-08-10 12:41:56', 0, 'altogether'),
(210, 'zysk, przynosić plon, ustępować', 1, '2017-08-14 16:48:16', -1, 'yield'),
(211, 'nieistotny', 1, '2017-08-14 16:48:51', 1, 'negligible'),
(212, 'z kolei', 1, '2017-08-14 14:00:12', 0, 'in turns'),
(213, 'aczkolwiek', 1, '2017-08-16 16:07:26', 0, 'albeit'),
(214, 'tymianek', 1, '2017-08-17 09:23:02', 0, 'thyme'),
(215, 'przed', 1, '2017-08-17 09:43:03', 0, 'prior to');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dict_entry`
--
ALTER TABLE `dict_entry`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dict_entry`
--
ALTER TABLE `dict_entry`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=216;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
