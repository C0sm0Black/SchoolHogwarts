CREATE TABLE cars
(

    id    INTEGER PRIMARY KEY,
    brand TETX,
    model TEXT,
    price NUMERIC(10, 2)

);

CREATE TABLE persons
(

    id       INTEGER PRIMARY KEY,
    name     TEXT,
    age      INTEGER,
    canDrive BOOLEAN,
    car_id   INTEGER REFERENCES cars (id)

);