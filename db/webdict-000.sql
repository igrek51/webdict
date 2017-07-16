-- phpMyAdmin SQL Dump
-- version 4.7.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 16, 2017 at 01:38 PM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
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
-- Table structure for table `dict_entry`
--

CREATE TABLE `dict_entry` (
  `id` bigint(20) NOT NULL,
  `definition` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `dictionary_id` bigint(20) NOT NULL,
  `last_use` datetime DEFAULT NULL,
  `rank` double NOT NULL,
  `word` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `dict_entry`
--

INSERT INTO `dict_entry` (`id`, `definition`, `dictionary_id`, `last_use`, `rank`, `word`) VALUES
(1, 'wspólnik, kojarzyć', 1, '2017-07-16 15:27:58', 1, 'associate'),
(2, 'oceniać', 1, '2017-07-16 15:28:05', 1, 'assess'),
(3, 'uzasadniony', 1, NULL, 0, 'legitimate'),
(4, 'umyślnie', 1, NULL, 0, 'admittedly'),
(5, 'współczesny', 1, NULL, 0, 'contemporary'),
(6, 'wszechobecny', 1, NULL, 0, 'ubiquitous'),
(7, 'zwięzłość', 1, NULL, 0, 'brevity'),
(8, 'zawierać', 1, NULL, 0, 'comprise'),
(9, 'pożerać', 1, NULL, 0, 'devour'),
(10, 'starać się', 1, NULL, 0, 'strive'),
(11, 'kompletny', 1, NULL, 0, 'utter'),
(12, 'opóźniać, odraczać', 1, NULL, 0, 'defer'),
(13, 'zostać poddanym', 1, NULL, 0, 'undergo'),
(14, 'nudny', 1, NULL, 0, 'tedious'),
(15, 'warstwa', 1, NULL, 0, 'tier'),
(16, 'z kolei', 1, NULL, 0, 'in turn'),
(17, 'nieodłączny', 1, NULL, 0, 'immanent'),
(18, 'zasługa', 1, NULL, 0, 'merit'),
(19, 'silny, surowy', 1, NULL, 0, 'severe'),
(20, 'gruntowny, dokładny', 1, NULL, 0, 'thorough'),
(21, 'w całym', 1, NULL, 0, 'throughout'),
(22, 'wybitny', 1, NULL, 0, 'eminent'),
(23, 'oddzielony', 1, NULL, 0, 'decoupled'),
(24, 'łączny', 1, NULL, 0, 'composite'),
(25, 'objazd', 1, NULL, 0, 'detour'),
(26, 'przyznawać się, wpuszczać', 1, NULL, 0, 'admit'),
(27, 'w związku z tym', 1, NULL, 0, 'accordingly'),
(28, 'przedstawiać', 1, NULL, 0, 'depict'),
(29, 'pojąć', 1, NULL, 0, 'conceive'),
(30, 'zawierać', 1, NULL, 0, 'comprise'),
(31, 'pamiętny', 1, NULL, 0, 'reminiscent'),
(32, 'powierzchowny', 1, NULL, 0, 'superficial'),
(33, 'solidny', 1, NULL, 0, 'robust'),
(34, 'opracować, dopracowany', 1, NULL, 0, 'elaborate'),
(35, 'skok', 1, NULL, 0, 'leap'),
(36, 'zawiły', 1, NULL, 0, 'intricate'),
(37, 'obmyślać', 1, NULL, 0, 'contrive'),
(38, 'pojęcie', 1, NULL, 0, 'notion'),
(39, 'zgodny', 1, NULL, 0, 'compliant'),
(40, 'zastraszać', 1, NULL, 0, 'intimidate'),
(41, 'wybitny, sławny', 1, NULL, 0, 'prominent'),
(42, 'wypukły', 1, NULL, 0, 'protuberant'),
(43, 'jeśli chodzi o, pod względem, z punktu widzenia', 1, NULL, 0, 'in terms of'),
(44, 'podatny', 1, NULL, 0, 'vulnerable'),
(45, 'wykorzystać', 1, NULL, 0, 'leverage'),
(46, 'nadający się', 1, NULL, 0, 'eligible'),
(47, 'celowy, przemyślany, rozważać', 1, NULL, 0, 'deliberate'),
(48, 'znaczny', 1, NULL, 0, 'substantial'),
(49, 'przegroda', 1, NULL, 0, 'compartment'),
(50, 'zwięzły', 1, NULL, 0, 'concise'),
(51, 'przysięga, przekleństwo', 1, NULL, 0, 'oath'),
(52, 'porzucony', 1, NULL, 0, 'derelict'),
(53, 'obdarzyć', 1, NULL, 0, 'endow'),
(54, 'trącić', 1, NULL, 0, 'nudge'),
(55, 'odwilż, rozmrażać', 1, NULL, 0, 'thaw'),
(56, 'rozwidlać się', 1, NULL, 0, 'bifurcate'),
(57, 'wysyłać', 1, NULL, 0, 'dispatch'),
(58, 'wierzchołek', 1, NULL, 0, 'summit'),
(59, 'zachować', 1, NULL, 0, 'retain'),
(60, 'skłonny', 1, NULL, 0, 'prone'),
(61, 'redukować, przycinać', 1, NULL, 0, 'prune'),
(62, 'domniemany', 1, NULL, 0, 'implicit'),
(63, 'wyraźny', 1, NULL, 0, 'explicit'),
(64, 'oddzielny, wyrazisty', 1, NULL, 0, 'distinct'),
(65, 'pęknięcie', 1, NULL, 0, 'rupture'),
(66, 'odwrotność, wzajemny, obustronny', 1, NULL, 0, 'reciprocal'),
(67, 'przestarzały', 1, NULL, 0, 'obsolete'),
(68, 'przymus', 1, NULL, 0, 'constraint'),
(69, 'zuchwałość', 1, NULL, 0, 'insolence'),
(70, 'zrozumieć', 1, NULL, 0, 'comprehend'),
(71, 'zrozumiały', 1, NULL, 0, 'comprehensible'),
(72, 'wszechstronny', 1, NULL, 0, 'comprehensive'),
(73, 'pomocniczy', 1, NULL, 0, 'auxiliary'),
(74, 'porzucic', 1, NULL, 0, 'forsake'),
(75, 'pochodzenie', 1, NULL, 0, 'ancestry'),
(76, 'odbiorca', 1, NULL, 0, 'recipient'),
(77, 'wzgląd', 1, NULL, 0, 'sake'),
(78, 'usiłować, dążenie', 1, NULL, 0, 'endeavour'),
(79, 'odzyskać', 1, NULL, 0, 'retrieve'),
(80, 'wycofać', 1, NULL, 0, 'retract'),
(81, 'powierzać, oddać', 1, NULL, 0, 'commit'),
(82, 'zobowiązanie', 1, NULL, 0, 'commitment'),
(83, 'sumaryczny', 1, NULL, 0, 'aggregate'),
(84, 'schować na potem', 1, NULL, 0, 'stash'),
(85, 'poprawić', 1, NULL, 0, 'amend'),
(86, 'zły, złośliwy', 1, NULL, 0, 'vicious'),
(87, 'wynurzać się (z mgły)', 1, NULL, 0, 'loom'),
(88, 'wykręcać', 1, NULL, 0, 'twine'),
(89, 'wiązka', 1, NULL, 0, 'bundle'),
(90, 'odwołać się, wezwać', 1, NULL, 0, 'invoke'),
(91, 'rusztowanie', 1, NULL, 0, 'scaffolding'),
(92, 'lokator', 1, NULL, 0, 'tenant'),
(93, 'wał', 1, NULL, 0, 'rampart'),
(94, 'przyzwoity', 1, NULL, 0, 'decent'),
(95, 'poprzedzać', 1, NULL, 0, 'precede'),
(96, 'wymazać', 1, NULL, 0, 'obliterate'),
(97, 'drugi', 1, NULL, 0, 'latter'),
(98, 'natknąć się, starcie', 1, NULL, 0, 'encounter'),
(99, 'nieład', 1, NULL, 0, 'clutter'),
(100, 'grupa', 1, NULL, 0, 'cluster'),
(101, 'zbędny', 1, NULL, 0, 'superfluous'),
(102, 'dyskretny', 1, NULL, 0, 'unobtrusive'),
(103, 'potomek', 1, NULL, 0, 'descendant'),
(104, 'mało prawdopodobne', 1, NULL, 0, 'unlikely'),
(105, 'odpychać', 1, NULL, 0, 'repel'),
(106, 'jęczmień', 1, NULL, 0, 'barley'),
(107, 'pszenica', 1, NULL, 0, 'wheat'),
(108, 'drożdże', 1, NULL, 0, 'yeast'),
(109, 'kukurydza', 1, NULL, 0, 'maize'),
(110, 'dojrzewać', 1, NULL, 0, 'ripen'),
(111, 'przeważać', 1, NULL, 0, 'outweigh'),
(112, 'obwód', 1, NULL, 0, 'perimeter'),
(113, 'podły', 1, NULL, 0, 'despicable'),
(114, 'streszczenie', 1, NULL, 0, 'synopsis'),
(115, 'współbieżny', 1, NULL, 0, 'concurrent'),
(116, 'zbieżny, zgodny, równoczesny', 1, NULL, 0, 'coincident'),
(117, 'wiarygodny', 1, NULL, 0, 'plausible'),
(118, 'paczka', 1, NULL, 0, 'parcel'),
(119, 'godność', 1, NULL, 0, 'dignity'),
(120, 'trwały', 1, NULL, 0, 'persistent'),
(121, 'wielokropek', 1, NULL, 0, 'ellipsis'),
(122, 'zamykać dostęp do', 1, NULL, 0, 'seal off'),
(123, 'wyłom, przełamać', 1, NULL, 0, 'breach'),
(124, 'nieprawdziwy', 1, NULL, 0, 'spurious'),
(125, 'dwuznaczny', 1, NULL, 0, 'ambiguous'),
(126, 'rzekomo', 1, NULL, 0, 'ostensibly'),
(127, 'stosowność', 1, NULL, 0, 'propriety'),
(128, 'własny', 1, NULL, 0, 'proprietary'),
(129, 'ograniczony', 1, NULL, 0, 'scarce'),
(130, 'przydział', 1, NULL, 0, 'quotas'),
(131, 'odchyłka, stronniczość, uprzedzenie', 1, NULL, 0, 'bias'),
(132, 'wpływ', 1, NULL, 0, 'leverage'),
(133, 'zaspokajać', 1, NULL, 0, 'cater'),
(134, 'niewygodny', 1, NULL, 0, 'cumbersome'),
(135, 'ładunek', 1, NULL, 0, 'payload'),
(136, 'wartownik', 1, NULL, 0, 'sentry'),
(137, 'wystarczać', 1, NULL, 0, 'suffice'),
(138, 'wyznaczać', 1, NULL, 0, 'designate'),
(139, 'oznaczyć', 1, NULL, 0, 'denote'),
(140, 'poziom', 1, NULL, 0, 'tier'),
(141, 'opracować, skomplikowany', 1, NULL, 0, 'elaborate'),
(142, 'późniejszy', 1, NULL, 0, 'subsequent'),
(143, 'granica', 1, NULL, 0, 'frontier'),
(144, 'opis', 1, NULL, 0, 'depiction'),
(145, 'faktura', 1, NULL, 0, 'invoice'),
(146, 'wnioskowanie', 1, NULL, 0, 'inference'),
(147, 'rozstrzygający', 1, NULL, 0, 'crucial'),
(148, 'stłumić', 1, NULL, 0, 'suppress'),
(149, 'podobieństwo', 1, NULL, 0, 'affinity'),
(150, 'rozszerzony', 1, NULL, 0, 'augmented'),
(151, 'mieszkaniec', 1, NULL, 0, 'inhabitant'),
(152, 'próbny', 1, NULL, 0, 'mock'),
(153, 'przechwytywać', 1, NULL, 0, 'intercept'),
(154, 'spadek, podupadać', 1, NULL, 0, 'decline'),
(155, 'poznawczy', 1, NULL, 0, 'cognitive'),
(156, 'przyległy', 1, NULL, 0, 'contiguous'),
(157, 'zestawić', 1, NULL, 0, 'collate'),
(158, 'dosłownie', 1, NULL, 0, 'verbatim'),
(159, 'wymuszenie', 1, NULL, 0, 'coercion'),
(160, 'całkowicie', 1, NULL, 0, 'thoroughly'),
(161, 'bez nadzoru', 1, NULL, 0, 'unattended'),
(162, 'pomimo to', 1, NULL, 0, 'nonetheless'),
(163, 'pierwszeństwo', 1, NULL, 0, 'precedence'),
(164, 'rodowód', 1, NULL, 0, 'pedigree'),
(165, 'wyłącznie', 1, NULL, 0, 'solely'),
(166, 'jedynie', 1, NULL, 0, 'merely'),
(167, 'dostosowywać się', 1, NULL, 0, 'conform'),
(168, 'sprzeczność', 1, NULL, 0, 'contradiction'),
(169, 'zmuszać', 1, NULL, 0, 'compel'),
(170, 'powstrzymywać', 1, NULL, 0, 'inhibit'),
(171, 'prawda', 1, NULL, 0, 'verity'),
(172, 'składać się', 1, NULL, 0, 'comprise'),
(173, 'cofać się', 1, NULL, 0, 'recede'),
(174, 'rozrzucać, rozproszyć się', 1, NULL, 0, 'scatter'),
(175, 'hurt', 1, NULL, 0, 'bulk'),
(176, 'unikać', 1, NULL, 0, 'evade'),
(177, 'oszacowanie', 1, NULL, 0, 'assessment'),
(178, 'zawierać', 1, NULL, 0, 'comprise'),
(179, 'pojmować', 1, NULL, 0, 'comprehend'),
(180, 'pełny', 1, NULL, 0, 'comprehensive'),
(181, 'przymus', 1, NULL, 0, 'coercion'),
(182, 'nagle', 1, NULL, 0, 'abruptly'),
(183, 'wytrwałość', 1, NULL, 0, 'perseverance'),
(184, 'zawiły, rozwijać', 1, NULL, 0, 'elaborate'),
(185, 'w związku z tym', 1, NULL, 0, 'hence');

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=186;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
