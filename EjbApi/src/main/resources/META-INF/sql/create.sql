CREATE TABLE workers
(
    worker_id   BIGINT(20) PRIMARY KEY NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    meter_id BIGINT(20) REFERENCES parkingmeters ON DELETE CASCADE,
    password VARCHAR(50) NOT NULL,
    admin_credentials BOOLEAN DEFAULT FALSE
);


CREATE TABLE tickets
(
    ticket_id   BIGINT(20) PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    spot_id BIGINT(20) NOT NULL REFERENCES parkingspots ON DELETE CASCADE,
    price DOUBLE,
    start_time TIMESTAMP,
    duration TIME,
    registration__number VARCHAR(9) NOT NULL
);

CREATE TABLE parkingmeters
(
    meter_id   BIGINT(20) PRIMARY KEY NOT NULL UNIQUE,
    address VARCHAR(50) NOT NULL,
    hour_price double
);

CREATE TABLE parkingspots
(
    spot_id   BIGINT(20) PRIMARY KEY NOT NULL UNIQUE,
    reserved BOOLEAN DEFAULT FALSE,
    meter_id BIGINT(20) REFERENCES parkingmeters ON DELETE CASCADE
);
