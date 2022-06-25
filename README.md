<div align="center">
  <h1>:airplane: Sunset Airlines Database Design :airplane:</h1>
</div>
<br>

A database project using PostgreSQL that features data modeling, logical design, physical design and physical implementation. The project employs two .sql files: one for creating the initial Sunset Airlines database, and the other for future DAO testing.

## Design and Table References

### flight
|               | Field             | Type       | 
| ------------- | ----------------- | ---------- |
| PK            | flight_id         | serial     |
|               | flight_number     | varchar(8) |
| FK (airport)  | departure_airport | varchar(3) |
| FK (airport)  | departure_airport | varchar(3) |
|               | departure_date    | date       |
|               | departure_time    | time       |
|               | arrival_date      | date       |
|               | arrival_time      | time       |
| FK (plane)    | plane_id          | int        |

### airport
|               | Field             | Type        | 
| ------------- | ----------------- | ----------- |
| PK            | airport_id        | serial      |
|               | iata_code         | varchar(3)  |
|               | airport_name      | varchar(60) |
|               | city              | varchar(60) |
|               | state             | varchar(20) |
|               | province          | varchar(40) |
|               | territory         | varchar(40) |
|               | country           | varchar(70) |

### plane
|               | Field              | Type        | 
| ------------- | ------------------ | ----------- |
| PK            | plane_id           | serial      |
|               | plane_name         | varchar(30) |
|               | plane_type         | varchar(30) |
|               | passenger_capacity | int         |

### passenger
|               | Field             | Type        | 
| ------------- | ----------------- | ----------- |
| PK            | passenger_id      | serial      |
|               | flyer_number      | varchar(10) |
|               | first_name        | varchar(50) |
|               | last_name         | varchar(50) |
|               | birth_date        | date        |
|               | status            | varchar(40) |

### flight_passenger
|                    | Field          | Type       | 
| ------------------ | -------------- | ---------- |
| PK, FK (flight)    | flight_id      | int        |
| PK, FK (passenger) | passenger_id   | int        |
|                    | date_booked    | timestamp  |
