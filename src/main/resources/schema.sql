DROP TABLE IF EXISTS "star";
CREATE TABLE "star"
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    mass                    VARCHAR(255) NOT NULL,
    radius                  VARCHAR(255) NOT NULL,
    luminosity              VARCHAR(255) NOT NULL,
    effective_temperature   BIGINT NOT NULL
);

DROP TABLE IF EXISTS "star_designation";
CREATE TABLE "star_designation"
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    star_id         BIGINT NOT NULL REFERENCES "star"(id),
    designation     VARCHAR(255) NOT NULL,
    source          VARCHAR(255) NOT NULL
);