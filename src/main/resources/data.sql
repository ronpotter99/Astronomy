INSERT INTO "star"
(id, mass, radius, luminosity, effective_temperature) VALUES
(0, '1.989e30', '6.955e8', '3.839e26', '5777');

INSERT INTO "star_designation"
(id, star_id, designation, designation_origin) VALUES
(0, 0, 'Sol', 'Latin name'),
(1, 0, 'Sun', 'English name');

INSERT INTO "planet"
(id, star_id, common_name, classification_type, mass, radius, density, orbit_semimajor_axis, orbit_eccentricity, orbit_period, orbit_inclination, rotational_period, surface_temperature, source) VALUES
(0, 0, 'Earth', 'TERRESTRIAL', '5.974e24', '6.378e6',  '5.2513', '1.495983195e11', '0.01671', '31558150', '0.00', '86163', '288', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Earth/'),
(1, 0, 'Mercury', 'TERRESTRIAL', '3.30194034e23', '2440285', '5.427', '5.7900923103e10', '0.20564', '7600544', '7.005', '5067032', '403u300', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Mercury/'),
(2, 0, 'Venus', 'TERRESTRIAL', '4.868973e24', '6051893', '5.243', '1.082095254e11', '0.00678', '19414149', '3.3947', '20996798', '738', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Venus/'),
(3, 0, 'Mars', 'TERRESTRIAL', '6.4192779e23', '3393998', '3.9335', '2.279437716e11', '0.09339', '5.9350727', '1.850', '88643', '213', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Mars/'),
(4, 0, 'Ceres', 'DWARF', '9.498978e20', '487840', '2.1616u0.0025', '4.137577908e11', '0.078', '145159911', '10.607', '32667', '133', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Ceres/'),
(5, 0, 'Jupiter', 'GAS_GIANT', '1.898779986e27', '71492526', '1.326', '7.783427614e11', '0.0484', '374163410', '1.304', '35618', null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(6, 0, 'Saturn', 'GAS_GIANT', '5.684988978e26', '60268283', '0.687', '1.426714893e12', '0.0539', '928535643', '2.486', '38844', null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(7, 0, 'Uranus', 'NEPTUNE_LIKE', '8.66259e25', '25559104', '1.27', '2.870633541e12', '0.04726', '2642867171', '0.773', '62064', null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(8, 0, 'Neptune', 'NEPTUNE_LIKE', '1.027801368e26', '24766302', '1.638', '4.498393012e12', '0.00859', '5166711572', '1.7700', '57492', null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(9, 0, 'Pluto', 'DWARF', '1.314324e22', '1151254', '1.853u0.004', '5.90643809e12', '0.24883', '7826941198', '17.14001', '551867', '73', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Pluto/'),
(10, 0, 'Haumea', 'DWARF', '4.18194e21', '765', '2.018u0.66', '6.459965172e12', '0.19489', '8955100152', '28.213545', '14095', '32', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Haumea/'),
(11, 0, 'Makemake', 'DWARF', '3.106584e21', '702', '1.7u0.3', '6.796335984e12', '0.161250', '9662568272', '28.98347', '214272', '33', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Makemake/'),
(12, 0, 'Eris', 'DWARF', '1.6608276e22', '1163', '2.52u0.07', '1.01523099e13', '0.43605', '1.764322301e10', '44.0393', '9.324e4', '43', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Eris/');

INSERT INTO "moon"
(id, planet_id, common_name, mass, orbit_semimajor_axis, orbit_period, source) VALUES
(0, 0, 'Luna', '7.2349e22', '384400', '2360585', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Earth/'),
(1, 3, 'Phobos', '1.08e16', '9377', '27554', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Mars/'),
(2, 3, 'Deimos', '1.80e15', '23463', '109075', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Mars/'),
(3, 5, 'Metis', null, '127960', '25469', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(4, 5, 'Adrastea', null, '128980', '25770', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(5, 5, 'Amalthea', null, '181300', '43043', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(6, 5, 'Thebe', null, '221900', '58277', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(7, 5, 'Io', '8.93e22', '421600', '152854', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(8, 5, 'Europa', '4.80e22', '670900', '306876', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(9, 5, 'Ganymede', '1.482e23', '1.070e6', '618153', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(10, 5, 'Callisto', '1.076e23', '1.883e6', '1441931', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(11, 5, 'Themisto', null, '7.5040e6', '11233728', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(12, 5, 'Leda', null, '1.1164e7', '20816352', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(13, 5, 'Ersa', null, '1.1455e7', '21634560', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(14, 5, 'Himalia', null, '1.1460e7', '21648920', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(15, 5, 'Pandia', null, '1.1494e7', '21752928', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(16, 5, 'Lysithea', null, '1.1717e7', '22394880', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(17, 5, 'Elara', null, '1.1740e7', '22432896', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(18, 5, 'Dia', null, '1.2297e7', '24037344', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(19, 5, 'Carpo', null, '1.7056e7', '39422592', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(20, 5, 'S/2003 J 12', null, '1.7830e7', '42307488', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(21, 5, 'S/2016 J 2', null, '1.8870e7', '45965664', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(22, 5, 'Euporia', null, '1.9336e7', '47579616', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(23, 5, 'Eupheme', null, '2.0221e7', '50446368', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(24, 5, 'S/2003 J 18', null, '2.0508e7', '51678432', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(25, 5, 'S/2017 J 7', null, '2.0614e7', '52079328', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(26, 5, 'S/2016 J 1', null, '2.0638e7', '52169184', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(27, 5, 'S/2017 J 3', null, '2.0620e7', '52337664', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(28, 5, 'S/2010 J 2', null, '2.1004e7', '52367776', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(29, 5, 'Mneme', null, '2.1033e7', '53572620', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(30, 5, 'Euanthe', null, '2.1039e7', '53606880', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(31, 5, 'Helike', null, '2.1065e7', '54114912', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(32, 5, 'S/2003 J 16', null, '2.197e7', '53816832', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(33, 5, 'Harpalyke', null, '2.1106e7', '53854848', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(34, 5, 'Praxidike', null, '2.1148e7', '54033696', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(35, 5, 'Orthosie', null, '2.1158e7', '53790912', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(36, 5, 'Thelxinoe', null, '2.11460e7', '54261792', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(37, 5, 'Thyone', null, '2.1197e7', '54189216', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(38, 5, 'Ananke', null, '2.1254e7', '54414720', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(39, 5, 'Iocaste', null, '2.1272e7', '54570240', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(40, 5, 'Hermippe', null, '2.1297e7', '54769824', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(41, 5, 'S/2017 J 9', null, '2.1453e7', '55373760', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(42, 5, 'S/2017 J 6', null, '2.2419e7', '59154624', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(43, 5, 'Philophrosyne', null, '2.2627e7', '59596992', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(44, 5, 'S/2003 J 24', null, '2.2715e7', '59945184', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(45, 5, 'S/2003 J 10', null, '2.3042e7', '61884000', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(46, 5, 'Pasithee', null, '2.3091e7', '62162208', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(47, 5, 'S/2011 J 2', null, '2.3124e7', '62067168', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(48, 5, 'Eurydome', null, '2.3146e7', '61975584', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(49, 5, 'S/2017 J 5', null, '2.3151e7', '62250336', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(50, 5, 'S/2017 J 8', null, '2.3166e7', '62271072', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(51, 5, 'Chaldene', null, '2.3181e7', '62530272', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(52, 5, 'S/2017 J 2', null, '2.3181e7', '62538912', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(53, 5, 'Isonoe', null, '2.3231e7', '62748864', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(54, 5, 'Kallichore', null, '2.3276e7', '62919072', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(55, 5, 'Erinome', null, '2.3286e7', '62941536', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(56, 5, 'Kale', null, '2.3306e7', '63038304', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(57, 5, 'Aitne', null, '2.3317e7', '630082368', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(58, 5, 'Eukelade', null, '2.3323e7', '6.3100512', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(59, 5, 'Arche', null, '2.3352e7', '63236160', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(60, 5, 'Taygete', null, '2.3363e7', '63281088', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(61, 5, 'S/2003 J 9', null, '2.3385e7', '63358848', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(62, 5, 'Carme', null, '2.3401e7', '63432288', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(63, 5, 'Herse', null, '2.3408e7', '63462528', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(64, 5, 'S/2011 J 1', null, '2.3446e7', '63620640', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(65, 5, 'S/2010 J 1', null, '2.3449e7', '63633600', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(66, 5, 'Eirene', null, '2.395e7', '63828000', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(67, 5, 'S/2003 J 19', null, '2.3533e7', '63971424', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(68, 5, 'Kalyke', null, '2.3565e7', '64112256', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(69, 5, 'S/2003 J 23', null, '2.3567e7', '63284544', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(70, 5, 'Hegemone', null, '2.3575e7', '63920448', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(71, 5, 'Pasiphae', null, '2.3629e7', '64247904', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(72, 5, 'Sponde', null, '2.3790e7', '64654848', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(73, 5, 'Cyllene', null, '2.3800e7', '64971072', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(74, 5, 'Megaclite', null, '2.3814e7', '65048832', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(75, 5, 'S/2003 J 4', null, '2.3929e7', '65253600', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(76, 5, 'Sinope', null, '2.3942e7', '65568096', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(77, 5, 'Aoede', null, '2.3974e7', '65785824', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(78, 5, 'Autonoe', null, '2.4037e7', '65751264', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(79, 5, 'Callirrhoe', null, '2.4099e7', '65562048', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(80, 5, 'Kore', null, '2.4482e7', '67118976', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(81, 5, 'S/2017 J 1', null, '2.4456e7', '67446432', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(82, 5, 'S/2003 J 2', null, '2.8347e7', '84717792', 'https://www.princeton.edu/~willman/planetary_systems/Sol/Jupiter/'),
(83, 6, 'S/2009 S 1', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(84, 6, 'Pan', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(85, 6, 'Daphnis', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(86, 6, 'Atlas', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(87, 6, 'Prometheus', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(88, 6, 'Pandora', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(89, 6, 'Epimetheus', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(90, 6, 'Janus', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(91, 6, 'Aegaeon', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(92, 6, 'Mimas', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(93, 6, 'Methone', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(94, 6, 'Anthe', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(95, 6, 'Pallene', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(96, 6, 'Enceladus', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(97, 6, 'Tethys', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(98, 6, 'Telesto', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(99, 6, 'Calypso', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(100, 6, 'Polydeuces', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(101, 6, 'Dione', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(102, 6, 'Helene', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(103, 6, 'Rhea', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(104, 6, 'Titan', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(105, 6, 'Hyperion', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(106, 6, 'Iapetus', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(107, 6, 'Kiviuq', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(108, 6, 'Ijiraq', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(109, 6, 'Phoebe', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(110, 6, 'Paaliaq', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(111, 6, 'Skathi', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(112, 6, 'S/2004 S 37', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(113, 6, 'Albiorix', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(114, 6, 'S/2007 S 2', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(115, 6, 'S/2004 S 29', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(116, 6, 'Bebhionn', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(117, 6, 'S/2004 S 31', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(118, 6, 'Erriapus', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(119, 6, 'Skoll', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(120, 6, 'Tarqeq', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(121, 6, 'Siarnaq', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(122, 6, 'Tarvos', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(123, 6, 'Hyrrokkin', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(124, 6, 'S/2004 S 13', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(125, 6, 'Greip', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(126, 6, 'Mundilfari', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(127, 6, 'S/2006 S 1', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(128, 6, 'S/2007 S 3', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(129, 6, 'Narvi', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(130, 6, 'Bergelmir', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(131, 6, 'Jarnsaxa', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(132, 6, 'S/2004 S 20', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(133, 6, 'S/2004 S 17', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(134, 6, 'Suttungr', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(135, 6, 'Hati', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(136, 6, 'S/2004 S 12', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(137, 6, 'S/2004 S 27', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(138, 6, 'Farbauti', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(139, 6, 'Bestla', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(140, 6, 'Thrymr', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(141, 6, 'S/2004 S 22', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(142, 6, 'Aegir', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(143, 6, 'S/2004 S 30', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(144, 6, 'S/2004 S 7', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(145, 6, 'S/2004 S 23', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(146, 6, 'S/2004 S 25', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(147, 6, 'S/2004 S 32', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(148, 6, 'S/2004 S 38', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(149, 6, 'S/2004 S 28', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(150, 6, 'Kari', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(151, 6, 'S/2004 S 35', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(152, 6, 'S/2006 S 3', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(153, 6, 'Fenrir', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(154, 6, 'S/2004 S 21', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(155, 6, 'S/2004 S 24', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(156, 6, 'Surtur', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(157, 6, 'Loge', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(158, 6, 'Ymir', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(159, 6, 'S/2004 S 36', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(160, 6, 'S/2004 S 39', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(161, 6, 'S/2004 S 33', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(162, 6, 'S/2004 S 34', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(163, 6, 'Fornjot', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(164, 6, 'S/2004 S 26', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Saturn/'),
(165, 7, 'Cordelia', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(166, 7, 'Ophelia', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(167, 7, 'Bianca', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(168, 7, 'Cressida', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(169, 7, 'Desdemona', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(170, 7, 'Juliet', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(171, 7, 'Portia', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(172, 7, 'Rosalind', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(173, 7, 'Cupid', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(174, 7, 'Belinda', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(175, 7, 'Perdita', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(176, 7, 'Puck', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(177, 7, 'Mab', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(178, 7, 'Miranda', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(179, 7, 'Ariel', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(180, 7, 'Umbriel', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(181, 7, 'Titania', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(182, 7, 'Oberon', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(183, 7, 'Francisco', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(184, 7, 'Caliban', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(185, 7, 'Stephano', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(186, 7, 'Triculo', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(187, 7, 'Sycorax', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(188, 7, 'Margaret', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(189, 7, 'Prospero', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(190, 7, 'Setebos', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(191, 7, 'Ferdinand', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Uranus/'),
(192, 8, 'Naiad', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(193, 8, 'Thalassa', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(194, 8, 'Despina', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(195, 8, 'Galatea', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(196, 8, 'Larissa', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(197, 8, 'Hippocamp', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(198, 8, 'Proteus', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(199, 8, 'Triton', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(200, 8, 'Nereid', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(201, 8, 'Halimede', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(202, 8, 'Sao', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(203, 8, 'Laomedeia', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(204, 8, 'Psamathe', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(205, 8, 'Neso', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Neptune/'),
(206, 9, 'Charon', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Pluto/'),
(207, 9, 'Styx', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Pluto/'),
(208, 9, 'Nix', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Pluto/'),
(209, 9, 'Kerberos', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Pluto/'),
(210, 9, 'Hydra', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Pluto/'),
(211, 10, 'Hi''iaka', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Haumea/'),
(212, 10, 'Namaka', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Haumea/'),
(213, 11, 'S/2016 (136472) 1', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Makemake/'),
(214, 12, 'Dysnomia', null, null, null, 'https://www.princeton.edu/~willman/planetary_systems/Sol/Eris/');