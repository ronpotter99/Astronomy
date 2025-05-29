INSERT INTO "star"
(id, mass, radius, luminosity, effective_temperature) VALUES
(0, '1.989e30', '6.955e8', '3.839e26', 5777);

INSERT INTO "star_designation"
(id, star_id, designation, source) VALUES
(0, 0, 'Sol', 'Latin name'),
(1, 0, 'Sun', 'English name');

INSERT INTO "planet"
(id, star_id, common_name, mass, radius) VALUES
(0, 0, 'Mercury', null, null),
(1, 0, 'Venus', null, null),
(2, 0, 'Earth', '5.974e24', '6.378e6'),
(3, 0, 'Mars', null, null),
(4, 0, 'Ceres', null, null),
(5, 0, 'Jupiter', null, null),
(6, 0, 'Saturn', null, null),
(7, 0, 'Uranus', null, null),
(8, 0, 'Neptune', null, null),
(9, 0, 'Pluto', null, null),
(10, 0, 'Haumea', null, null),
(11, 0, 'Makemake', null, null),
(12, 0, 'Eris', null, null);