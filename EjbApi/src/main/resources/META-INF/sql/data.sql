INSERT INTO workers
VALUES (2345, 'Fiodor', 'Dostojewski', 223, 'fiodor13', 'xhtml', FALSE),
       (2246, 'Albert', 'Camus', 223, 'albert22', 'xhtml', TRUE),
       (2247, 'Monika', 'Szwaja', 222, 'moniaa','xhtml', FALSE),
       (2248, 'Teodor', 'Kowalski', 222, 'teodorr', 'xhtml', FALSE),
       (2249, 'Milan', 'Nowak', 220, 'nowak14','xhtml', TRUE),
       (2250, 'Maria', 'Achmatowa', 222, 'achmat','xhtml', FALSE),
       (2251, 'Ola', 'Makota', 224, 'makko','xhtml', FALSE),
       (2252, 'Alicja', 'Machomika', 225, 'chomik1','xhtml', TRUE),
       (2253, 'Zenon', 'Lipiec', 225, 'latowiec','xhtml', FALSE),
       (2354, 'Damian', 'Mienny', 224, 'imiennik1','xhtml', FALSE);

INSERT INTO parkingspots
VALUES (2444, FALSE, 223),
       (2445, FALSE, 223),
       (2333, FALSE, 222),
       (2446, FALSE, 220),
       (2447, FALSE, 220),
       (2387, FALSE, 224),
       (2448, FALSE, 225),
       (2388, FALSE, 224),
       (2389, FALSE, 224),
       (2449, FALSE, 225);

INSERT INTO parkingmeters
VALUES (223, 'Złotopolska 2', 2.50),
       (222, 'Powstańców 4', 2.00),
       (220, 'Powstańców 18', 2.50),
       (224, 'Ryjówek 5', 2.00),
       (225, 'Ryjówek 18', 2.50);

INSERT INTO tickets
VALUES (1, 2444, 22.50, current_timestamp() - interval 3 HOUR, '02:00:00', 'POL 22 333'),
       (2, 2445, 12.00, current_timestamp() - interval 5 HOUR, '07:00:00', 'POL 28 345'),
       (3, 2333, 22.50, current_timestamp() - interval 8 HOUR, '05:00:00', 'POL 22 333'),
       (4, 2333, 12.00, current_timestamp() - interval 2 HOUR, '03:00:00', 'POL 77 222'),
       (5, 2446, 22.50, current_timestamp() - interval 1 HOUR, '02:00:00', 'POL 25 338'),
       (6, 2447, 12.00, current_timestamp() - interval 6 HOUR, '07:00:00', 'POL 44 123'),
       (7, 2446, 22.50, current_timestamp() - interval 4 HOUR, '02:00:00', 'POL 25 338'),
       (8, 2448, 12.00, current_timestamp() - interval 3 HOUR, '07:00:00', 'POL 99 012'),
       (9, 2388, 22.50, current_timestamp() - interval 3 HOUR, '02:00:00', 'POL 13 333'),
       (10, 2445, 12.00, current_timestamp() - interval 3 HOUR, '07:00:00', 'POL 28 367');

