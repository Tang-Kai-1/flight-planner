--liquibase formatted sql

--changeset toms:1

CREATE TABLE flight
(
    id             INT AUTO_INCREMENT,
    airport_from   VARCHAR(255) NOT NULL,
    airport_to     VARCHAR(255) NOT NULL,
    carrier        VARCHAR(255) NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time   TIMESTAMP NOT NULL,
    FOREIGN KEY (airport_from) REFERENCES airport (airport) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (airport_to) REFERENCES airport (airport) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (id)
);
