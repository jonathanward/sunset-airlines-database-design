BEGIN TRANSACTION;

DROP TABLE IF EXISTS flight_passenger, plane, airport, flight, passenger;

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

INSERT INTO plane(plane_name, plane_type, passenger_capacity)
VALUES('plane-1', 'type-1', 10),
        ('plane-2', 'type-2', 20);

INSERT INTO airport(iata_code, airport_name, city, state, country)
VALUES('AAA', 'A City Airport', 'A City', 'A State', 'A Country'),
        ('BBB', 'B City Airport', 'B City', 'B State', 'B Country'),
        ('CCC', 'C City Airport', 'C City', 'C State', 'C Country'),
        ('DDD', 'D City Airport', 'D City', 'D State', 'D County');

INSERT INTO flight(flight_number, departure_airport, arrival_airport, departure_date, departure_time, arrival_date, arrival_time, plane_id)
VALUES('SA 100', 'AAA', 'BBB', '1-1-23', '11:00 AM', '1-2-23', '1:30 AM', (SELECT plane_id FROM plane WHERE plane_name = 'plane-1')),
        ('SA 200', 'CCC', 'DDD', '1-2-23', '12:00 PM', '1-2-23', '10:00 PM', (SELECT plane_id FROM plane WHERE plane_name = 'plane-2'));
        
INSERT INTO passenger(flyer_number, first_name, last_name, birth_date, status)
VALUES('ABCDEFG', 'first1', 'last1', '1-1-92', 'Platinum'),
        ('BCDEFGH', 'first2', 'last2', '1-1-93', 'Gold'),
        ('CDEFGHI', 'first3', 'last3', '1-1-98', 'Gold'),
        ('DEFGHIJ', 'first4', 'last4', '1-1-00', 'Executive');
        
INSERT INTO flight_passenger(flight_id, passenger_id)
VALUES(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 100'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'ABCDEFG')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 100'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'CDEFGHI')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 100'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'DEFGHIJ')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 200'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'ABCDEFG')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 200'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'BCDEFGH')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 200'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'CDEFGHI')
),
(
        (SELECT flight_id FROM flight WHERE flight_number = 'SA 200'),
        (SELECT passenger_id FROM passenger WHERE flyer_number = 'DEFGHIJ')
);

COMMIT;