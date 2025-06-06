DROP TABLE IF EXISTS "star";
CREATE TABLE "star"
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    mass                    VARCHAR(255) NOT NULL,
    radius                  VARCHAR(255) NOT NULL,
    luminosity              VARCHAR(255) NOT NULL,
    effective_temperature   VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS "star_designation";
CREATE TABLE "star_designation"
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    star_id         BIGINT NOT NULL REFERENCES "star"(id),
    designation     VARCHAR(255) NOT NULL,
    source          VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS "planet";
CREATE TABLE "planet"
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    star_id                 BIGINT NOT NULL REFERENCES "star"(id),
    common_name             VARCHAR(255),
    classification_type     VARCHAR(32),
    mass                    VARCHAR(255),
    radius                  VARCHAR(255),
    density                 VARCHAR(255),
    orbit_semimajor_axis    VARCHAR(255),
    orbit_eccentricity      VARCHAR(255),
    orbit_period            VARCHAR(255),
    orbit_inclination       VARCHAR(255),
    rotational_period       VARCHAR(255),
    surface_temperature     VARCHAR(255)
);

DROP TABLE IF EXISTS "moon";
CREATE TABLE "moon"
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    planet_id               BIGINT NOT NULL REFERENCES "planet"(id),
    common_name             VARCHAR(255),
    mass                    VARCHAR(255),
    orbit_semimajor_axis    VARCHAR(255),
    orbit_period            VARCHAR(255)
);
