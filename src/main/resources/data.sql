INSERT INTO "star"
(id, mass, radius, luminosity, effective_temperature) VALUES
(0, '1.989e30', '6.955e8', '3.839e26', '5777');

INSERT INTO "star_designation"
(id, star_id, designation, source) VALUES
(0, 0, 'Sol', 'Latin name'),
(1, 0, 'Sun', 'English name');

INSERT INTO "planet"
(id, star_id, common_name, classification_type, mass, radius, density, orbit_semimajor_axis, orbit_eccentricity, orbit_period, orbit_inclination, rotational_period, surface_temperature) VALUES
(0, 0, 'Earth', 'TERRESTRIAL', '5.974e24', '6.378e6',  null, null, null, null, null, null, null),
(1, 0, 'Mercury', 'TERRESTRIAL', null, null, null, null, null, null, null, null, null),
(2, 0, 'Venus', 'TERRESTRIAL', null, null, null, null, null, null, null, null, null),
(3, 0, 'Mars', 'TERRESTRIAL', null, null, null, null, null, null, null, null, null),
(4, 0, 'Ceres', 'DWARF', null, null, null, null, null, null, null, null, null),
(5, 0, 'Jupiter', 'GAS_GIANT', null, null, null, null, null, null, null, null, null),
(6, 0, 'Saturn', 'GAS_GIANT', null, null, null, null, null, null, null, null, null),
(7, 0, 'Uranus', 'NEPTUNE_LIKE', null, null, null, null, null, null, null, null, null),
(8, 0, 'Neptune', 'NEPTUNE_LIKE', null, null, null, null, null, null, null, null, null),
(9, 0, 'Pluto', 'DWARF', null, null, null, null, null, null, null, null, null),
(10, 0, 'Haumea', 'DWARF', null, null, null, null, null, null, null, null, null),
(11, 0, 'Makemake', 'DWARF', null, null, null, null, null, null, null, null, null),
(12, 0, 'Eris', 'DWARF', '1.6608276e22', '1163', '2.52', '1.01523099e13', '0.43605', '1.764322301e10', '44.0393', '9.324e4', '43');
