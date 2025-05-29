INSERT INTO "star"
(id, mass, radius, luminosity, effective_temperature) VALUES
(0, '1.989e30', '6.955e8', '3.839e26', 5777);

INSERT INTO "star_designation"
(id, star_id, designation, source) VALUES
(0, 0, 'Sol', 'Latin name'),
(1, 0, 'Sun', 'English name');

INSERT INTO "planet"
(id, star_id, common_name, classification_type, mass, radius) VALUES
(0, 0, 'Mercury', 'TERRESTRIAL', null, null),
(1, 0, 'Venus', 'TERRESTRIAL', null, null),
(2, 0, 'Earth', 'TERRESTRIAL', '5.974e24', '6.378e6'),
(3, 0, 'Mars', 'TERRESTRIAL', null, null),
(4, 0, 'Ceres', 'DWARF', null, null),
(5, 0, 'Jupiter', 'GAS_GIANT', null, null),
(6, 0, 'Saturn', 'GAS_GIANT', null, null),
(7, 0, 'Uranus', 'NEPTUNE_LIKE', null, null),
(8, 0, 'Neptune', 'NEPTUNE_LIKE', null, null),
(9, 0, 'Pluto', 'DWARF', null, null),
(10, 0, 'Haumea', 'DWARF', null, null),
(11, 0, 'Makemake', 'DWARF', null, null),
(12, 0, 'Eris', 'DWARF', null, null);