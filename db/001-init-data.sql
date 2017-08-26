SET FOREIGN_KEY_CHECKS = 0; 
DELETE FROM `rank`; 
DELETE FROM `word`; 
DELETE FROM `dictionary`; 
DELETE FROM `language`; 
DELETE FROM `user`; 
SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO `language` (`id`, `code`) VALUES
(1, 'en'),
(2, 'pl');

INSERT INTO `dictionary` (`id`, `source_language_id`, `target_language_id`) VALUES
(1, 1, 2);

INSERT INTO `user` (`id`, `login`, `pass`) VALUES
(1, 'igrek', 'dupa');


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
(130, 1, 1, 'cater', 'zaspokajać'),
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
(215, 1, 1, 'prior to', 'przed');

INSERT INTO `rank` (`id`, `word_id`, `reversed_dictionary`, `last_use`, `rank_value`) VALUES
(1, 1, false, NULL, 1),
(2, 2, false, NULL, 0),
(3, 3, false, NULL, -1),
(4, 4, false, NULL, -1),
(5, 5, false, NULL, -1),
(6, 6, false, NULL, -1),
(7, 7, false, NULL, -1),
(8, 8, false, NULL, -1),
(9, 9, false, NULL, -1),
(10, 10, false, NULL, 1),
(11, 11, false, NULL, 0),
(12, 12, false, NULL, -1),
(13, 13, false, NULL, 0),
(14, 14, false, NULL, -1),
(15, 15, false, NULL, -1),
(16, 16, false, NULL, 0),
(17, 17, false, NULL, -1),
(18, 18, false, NULL, 0),
(19, 19, false, NULL, 3),
(20, 20, false, NULL, 1),
(21, 21, false, NULL, 1),
(22, 22, false, NULL, -1),
(23, 23, false, NULL, 0),
(24, 24, false, NULL, 0),
(25, 25, false, NULL, 5),
(26, 26, false, NULL, 0),
(27, 27, false, NULL, 5),
(28, 28, false, NULL, 6),
(29, 29, false, NULL, 2),
(30, 30, false, NULL, 0),
(31, 31, false, NULL, 0),
(32, 32, false, NULL, -1),
(33, 33, false, NULL, 0),
(34, 34, false, NULL, 1),
(35, 35, false, NULL, 8),
(36, 36, false, NULL, -1),
(37, 37, false, NULL, 0),
(38, 38, false, NULL, 5),
(39, 39, false, NULL, 1),
(40, 40, false, NULL, 0),
(41, 41, false, NULL, 0),
(42, 42, false, NULL, -1),
(43, 43, false, NULL, 1),
(44, 44, false, NULL, 1),
(45, 45, false, NULL, 0),
(46, 46, false, NULL, -1),
(47, 47, false, NULL, -1),
(48, 48, false, NULL, -1),
(49, 49, false, NULL, -1),
(50, 50, false, NULL, -1),
(51, 51, false, NULL, 0),
(52, 52, false, NULL, -1),
(53, 53, false, NULL, 0),
(54, 54, false, NULL, -1),
(55, 55, false, NULL, 0),
(56, 56, false, NULL, 0),
(57, 57, false, NULL, 1),
(58, 58, false, NULL, 1),
(59, 59, false, NULL, 0),
(60, 60, false, NULL, -1),
(61, 61, false, NULL, -1),
(62, 62, false, NULL, -1),
(63, 63, false, NULL, 0),
(64, 64, false, NULL, 0),
(65, 65, false, NULL, 0),
(66, 66, false, NULL, 0),
(67, 67, false, NULL, -1),
(68, 68, false, NULL, -1),
(69, 69, false, NULL, 0),
(70, 70, false, NULL, 0),
(71, 71, false, NULL, -1),
(72, 72, false, NULL, -1),
(73, 73, false, NULL, -1),
(74, 74, false, NULL, -1),
(75, 75, false, NULL, -1),
(76, 76, false, NULL, 1),
(77, 77, false, NULL, 0),
(78, 78, false, NULL, -1),
(79, 79, false, NULL, -1),
(80, 80, false, NULL, 3),
(81, 81, false, NULL, 0),
(82, 82, false, NULL, 0),
(83, 83, false, NULL, -1),
(84, 84, false, NULL, 0),
(85, 85, false, NULL, 1),
(86, 86, false, NULL, 0),
(87, 87, false, NULL, 0),
(88, 88, false, NULL, 0),
(89, 89, false, NULL, 0),
(90, 90, false, NULL, 1),
(91, 91, false, NULL, -1),
(92, 92, false, NULL, 0),
(93, 93, false, NULL, -1),
(94, 94, false, NULL, 0),
(95, 95, false, NULL, 0),
(96, 96, false, NULL, 0),
(97, 97, false, NULL, 1),
(98, 98, false, NULL, 0),
(99, 99, false, NULL, -1),
(100, 100, false, NULL, -1),
(101, 101, false, NULL, -1),
(102, 102, false, NULL, 0),
(103, 103, false, NULL, 1),
(104, 104, false, NULL, -1),
(105, 105, false, NULL, -1),
(106, 106, false, NULL, 0),
(107, 107, false, NULL, 0),
(108, 108, false, NULL, -1),
(109, 109, false, NULL, 0),
(110, 110, false, NULL, -1),
(111, 111, false, NULL, 0),
(112, 112, false, NULL, 0),
(113, 113, false, NULL, 0),
(114, 114, false, NULL, 1),
(115, 115, false, NULL, 0),
(116, 116, false, NULL, 0),
(117, 117, false, NULL, 0),
(118, 118, false, NULL, -1),
(119, 119, false, NULL, -1),
(120, 120, false, NULL, -1),
(121, 121, false, NULL, 0),
(122, 122, false, NULL, -1),
(123, 123, false, NULL, -1),
(124, 124, false, NULL, 0),
(125, 125, false, NULL, 1),
(126, 126, false, NULL, 1),
(127, 127, false, NULL, -1),
(128, 128, false, NULL, 2),
(129, 129, false, NULL, 0),
(130, 130, false, NULL, 1),
(131, 131, false, NULL, 0),
(132, 132, false, NULL, 0),
(133, 133, false, NULL, -1),
(134, 134, false, NULL, 2),
(135, 135, false, NULL, 1),
(136, 136, false, NULL, 0),
(137, 137, false, NULL, -1),
(138, 138, false, NULL, 1),
(139, 139, false, NULL, 0),
(140, 140, false, NULL, 0),
(141, 141, false, NULL, 0),
(142, 142, false, NULL, 0),
(143, 143, false, NULL, 1),
(144, 144, false, NULL, -1),
(145, 145, false, NULL, 0),
(146, 146, false, NULL, 0),
(147, 147, false, NULL, 0),
(148, 148, false, NULL, -1),
(149, 149, false, NULL, -1),
(150, 150, false, NULL, 1),
(151, 151, false, NULL, -1),
(152, 152, false, NULL, 0),
(153, 153, false, NULL, -1),
(154, 154, false, NULL, 1),
(155, 155, false, NULL, 0),
(156, 156, false, NULL, 5),
(157, 157, false, NULL, -1),
(158, 158, false, NULL, 1),
(159, 159, false, NULL, -1),
(160, 160, false, NULL, 0),
(161, 161, false, NULL, 2),
(162, 162, false, NULL, -1),
(163, 163, false, NULL, -1),
(164, 164, false, NULL, 0),
(165, 165, false, NULL, 1),
(166, 166, false, NULL, 0),
(167, 167, false, NULL, 0),
(168, 168, false, NULL, 0),
(169, 169, false, NULL, 0),
(170, 170, false, NULL, -1),
(171, 171, false, NULL, -1),
(172, 172, false, NULL, -1),
(173, 173, false, NULL, 1),
(174, 174, false, NULL, 0),
(175, 175, false, NULL, -1),
(176, 176, false, NULL, 0),
(177, 177, false, NULL, -1),
(178, 178, false, NULL, 0),
(179, 179, false, NULL, 2),
(180, 180, false, NULL, 3),
(181, 181, false, NULL, -1),
(182, 182, false, NULL, 1),
(183, 183, false, NULL, 0),
(184, 184, false, NULL, 0),
(185, 185, false, NULL, -1),
(186, 186, false, NULL, 0),
(187, 187, false, NULL, 1),
(188, 188, false, NULL, -1),
(189, 189, false, NULL, -1),
(190, 190, false, NULL, 1),
(191, 191, false, NULL, -1),
(192, 192, false, NULL, 0),
(193, 193, false, NULL, -1),
(194, 194, false, NULL, 0),
(195, 195, false, NULL, 0),
(196, 196, false, NULL, -1),
(197, 197, false, NULL, 0),
(198, 198, false, NULL, 0),
(199, 199, false, NULL, -1),
(200, 200, false, NULL, 0),
(201, 201, false, NULL, 0),
(202, 202, false, NULL, 0),
(203, 203, false, NULL, -1),
(204, 204, false, NULL, 0),
(205, 205, false, NULL, 2),
(206, 206, false, NULL, 1),
(207, 207, false, NULL, 0),
(208, 208, false, NULL, 1),
(209, 209, false, NULL, 0),
(210, 210, false, NULL, -1),
(211, 211, false, NULL, 1),
(212, 212, false, NULL, 0),
(213, 213, false, NULL, 0),
(214, 214, false, NULL, 0),
(215, 215, false, NULL, 0);