INSERT INTO workers
VALUES (2345, 'Fiodor', 'Dostojewski', 223, 'xhtml', FALSE),
       (2246, 'Albert', 'Camus', 223, 'xhtml', TRUE),
       (2247, 'Monika', 'Szwaja', 222, 'xhtml', FALSE);

INSERT INTO parkingspots
VALUES (2444, FALSE, 223),
       (2445, FALSE, 223),
       (2333, FALSE, 222);

INSERT INTO parkingmeters
VALUES (223, 'Złotopolska 2', 2.50),
       (222, 'Powstańców 4', 2.00);

INSERT INTO tickets
VALUES (1, 2444, 22.50, current_timestamp() - interval 3 HOUR, '02:00:00', 'POL 22 333'),
       (2, 2445, 12.00, current_timestamp() - interval 5 HOUR, '07:00:00', 'POL 28 345');

