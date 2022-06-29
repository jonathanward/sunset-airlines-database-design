DROP TABLE IF EXISTS flight_passenger, plane, airport, flight, passenger CASCADE;

-- Create plane, airport, flight, passenger and flight_passenger tables

CREATE TABLE plane (
        plane_id serial,
        plane_name varchar(30) NOT NULL,
        plane_type varchar(30) NOT NULL,
        passenger_capacity int NOT NULL,
        
        CONSTRAINT PK_plane PRIMARY KEY(plane_id)
);

CREATE TABLE airport (
        airport_id serial,
        iata_code varchar(3) UNIQUE NOT NULL,
        airport_name varchar(60) NOT NULL,
        city varchar(60) NOT NULL,
        state varchar(20),
        province varchar(40),
        territory varchar(40),
        country varchar(70) NOT NULL,
        
        CONSTRAINT PK_airport PRIMARY KEY (airport_id)
);

CREATE TABLE flight (
        flight_id serial,
        flight_number varchar(8) NOT NULL,
        departure_airport varchar(3) NOT NULL,
        arrival_airport varchar(3) NOT NULL,
        departure_date DATE NOT NULL, 
        departure_time TIME NOT NULL,
        arrival_date DATE NOT NULL,
        arrival_time TIME NOT NULL,
        plane_id int NOT NULL,
        
        CONSTRAINT PK_flight PRIMARY KEY(flight_id),
        CONSTRAINT FK_flight_departure_airport FOREIGN KEY(departure_airport) REFERENCES airport(iata_code),
        CONSTRAINT FK_flight_arrival_airport FOREIGN KEY(arrival_airport) REFERENCES airport(iata_code),
        CONSTRAINT FK_flight_plane FOREIGN KEY(plane_id) REFERENCES plane(plane_id)
);

CREATE TABLE passenger (
        passenger_id serial,
        flyer_number varchar(10) UNIQUE NOT NULL,
        first_name varchar(50) NOT NULL,
        last_name varchar(50) NOT NULL,
        birth_date DATE NOT NULL,
        status varchar(40),
        
        CONSTRAINT PK_passenger PRIMARY KEY(passenger_id)
);

CREATE TABLE flight_passenger (
        flight_id int NOT NULL,
        passenger_id int NOT NULL,
        date_booked TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
        
        CONSTRAINT PK_flight_passenger PRIMARY KEY(flight_id, passenger_id),
        CONSTRAINT FK_flight_passenger_passenger FOREIGN KEY(flight_id) REFERENCES flight(flight_id),
        CONSTRAINT FK_flight_passenger_flight FOREIGN KEY(passenger_id) REFERENCES passenger(passenger_id)
);

-- Populate all tables with values

INSERT INTO plane(plane_name, plane_type, passenger_capacity)
VALUES('Boeing 737-900', 'Twin narrow-body airliner', 177),
        ('Airbus A321-200', 'Twin narrow-body airliner', 208),
        ('Boeing B747-8', 'Long-range wide-body airliner', 416);

INSERT INTO airport(iata_code, airport_name, city, state, country)
VALUES('LGA', 'LaGuardia Airport', 'New York City', 'New York', 'United States'),
        ('DFW', 'Dallas-Fort Worth International Airport', 'Dallas', 'Texas', 'United States'),
        ('CLT', 'Charlotte', 'Charlotte Douglas International Airport', 'North Carolina', 'United States'),
        ('DEN', 'Denver', 'Denver International Airport', 'Colorado', 'United States'),
        ('ORD', 'Chicago', 'O''Hare International Airport', 'Illinois', 'United States'),
        ('FRA', 'Frankfurt', 'Frankfurt Airport', 'Hessen', 'Germany');

INSERT INTO flight(flight_number, departure_airport, arrival_airport, departure_date, departure_time, arrival_date, arrival_time, plane_id)
VALUES('SA 347', 'LGA', 'DFW', '7-2-22', '5:45 PM', '7-2-22', '8:30 PM', (SELECT plane_id FROM plane WHERE plane_name = 'Boeing 737-900')),
        ('SA 791', 'CLT', 'DEN', '7-9-22', '12:59 PM', '7-9-22', '2:08 PM', (SELECT plane_id FROM plane WHERE plane_name = 'Airbus A321-200')),
        ('SA 1442', 'FRA', 'ORD', '7-12-22', '10:54 AM', '7-9-22', '8:14 PM', (SELECT plane_id FROM plane WHERE plane_name = 'Boeing B747-8'));
        
INSERT INTO passenger(flyer_number, first_name, last_name, birth_date, status)
VALUES('AF22349', 'Aarin', 'Karlin', '4-21-92', 'Platinum'),
        ('DB213OS', 'Enzo', 'Frazier', '7-3-84', 'Gold'),
        ('C102KL4', 'Damari', 'Kent', '6-18-93', 'Platinum'),
        ('67XT3B1', 'Adrienne', 'Jones', '10-7-94', 'Executive'),
        ('LAEE193', 'Julia', 'Liu', '11-28-91', null),
        ('SDFO12A', 'Gloria', 'Henson', '8-14-76', null),
        ('33SDFXL', 'Parker', 'Durham', '1-25-96', 'Silver'),
        ('DK64MP2', 'Charles', 'Mendez', '9-9-83', 'Gold'),
        ('TDDUP25', 'Lynn', 'Evans', '4-13-84', 'Gold'),
        ('TDIMU04', 'Irving', 'Reeves', '6-14-90', 'Gold'),
        ('M5P13BP', 'Webb', 'Riley', '10-7-89', 'Platinum'),
        ('SX1TGFX', 'Wade', 'Fields', '9-9-87', 'Silver'),
        ('N09Y2KL', 'Ada', 'Watkins', '3-31-88', 'Executive'),
        ('SM64MKW', 'Tiffany', 'Maxwell', '12-3-81', 'Platinum'),
        ('YSSFX64', 'Jacqueline', 'Cannon', '5-2-02', null),
        ('CMYK3JB', 'Jill', 'McCarthy', '9-9-97', 'Gold'),
        ('IU0RTL3', 'Penny', 'McCarthy', '10-24-99', 'Silver'),
        ('HNBN912', 'Lacey', 'Collins', '11-23-94', 'Gold');
        
INSERT INTO flight_passenger(flight_id, passenger_id)
VALUES(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 347'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'AF22349')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 347'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = '67XT3B1')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 347'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'C102KL4')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 347'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'SDFO12A')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 347'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'TDDUP25')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 347'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'TDIMU04')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 347'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'SX1TGFX')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 347'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'SM64MKW')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 347'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'M5P13BP')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 791'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'AF22349')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 791'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = '67XT3B1')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 791'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'TDIMU04')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 791'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'DB213OS')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 791'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'LAEE193')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 791'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = '33SDFXL')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 791'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'C102KL4')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 791'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'SDFO12A')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'AF22349')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'DB213OS')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = '67XT3B1')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'LAEE193')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'SDFO12A')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = '33SDFXL')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'DK64MP2')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'TDDUP25')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'TDIMU04')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'M5P13BP')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'SX1TGFX')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'N09Y2KL')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'SM64MKW')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'YSSFX64')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'CMYK3JB')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'IU0RTL3')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 1442'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'HNBN912')
);

-- Show airport values

SELECT airport_name, city, state, country, iata_code AS airport_code
FROM airport
ORDER BY city;

-- Show plane values

SELECT * 
FROM plane;

-- Show flight information with plane information

SELECT f.flight_number, f.departure_airport, f.arrival_airport, f.departure_date, f.departure_time, f.arrival_date, f.arrival_time, p.plane_name, p.plane_type, p.passenger_capacity
FROM flight f
JOIN plane p ON f.plane_id = p.plane_id
ORDER BY f.departure_date;

SELECT last_name, first_name, birth_date, flyer_number, status 
FROM passenger;

-- Show all passengers for Flight SA 791

SELECT f.flight_number, p.last_name, p.first_name, f.departure_date, f.departure_time, f.arrival_time
FROM passenger p
JOIN flight_passenger fp ON p.passenger_id = fp.passenger_id
JOIN flight f ON fp.flight_id = f.flight_id
WHERE f.flight_number = 'SA 791'
ORDER BY p.last_name;

-- Get seating capacity for Flight SA 791

SELECT f.flight_number, p.passenger_capacity
FROM flight f
JOIN plane p ON f.plane_id = p.plane_id
WHERE f.flight_number = 'SA 791';

-- Show all passengers arriving in Texas

SELECT p.last_name, p.first_name, a.state, f.arrival_date, f.flight_number
FROM passenger p
JOIN flight_passenger fp ON p.passenger_id = fp.passenger_id
JOIN flight f ON fp.flight_id = f.flight_id
JOIN airport a ON f.arrival_airport = a.iata_code
WHERE a.state = 'Texas';

-- Show Damari's flight info
SELECT p.last_name, p.first_name, f.departure_airport, f.arrival_airport, f.arrival_date, f.departure_time, f.arrival_time, f.flight_number
FROM passenger p
JOIN flight_passenger fp ON p.passenger_id = fp.passenger_id
JOIN flight f ON fp.flight_id = f.flight_id
WHERE p.flyer_number = 'C102KL4';

-- Show how many passengers who have membership status were born before 1990
SELECT COUNT(*)
FROM passenger
WHERE birth_date < '1-1-1990' AND status IS NOT NULL;