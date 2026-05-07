CREATE DATABASE IF NOT EXISTS car_rental;
USE car_rental;
CREATE TABLE customers (
    Cust_ID INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    DOB DATE NOT NULL,
    Age INT NOT NULL,
    Cust_Address VARCHAR(50) NOT NULL
);

CREATE TABLE car_details (
    Car_Type VARCHAR(50) PRIMARY KEY,
    Base_Rent DECIMAL(10,2) NOT NULL
);

CREATE TABLE service_details (
    Service VARCHAR(50) PRIMARY KEY,
    Service_Charge DECIMAL(10,2) NOT NULL
);

CREATE TABLE car_orders (
    Booking_ID INT PRIMARY KEY ,
    Car_Type VARCHAR(50) NOT NULL,
    Cust_ID INT NOT NULL,
    Date_Of_Booking DATE NOT NULL,
    Return_Date DATE NOT NULL,
    FOREIGN KEY (Car_Type) REFERENCES car_details(Car_Type),
    FOREIGN KEY (Cust_ID) REFERENCES customers(Cust_ID)
);

CREATE TABLE service_bookings (
    Booking_ID INT NOT NULL,
    Service VARCHAR(50) NOT NULL,
    PRIMARY KEY (Booking_ID, Service),
    FOREIGN KEY (Booking_ID) REFERENCES car_orders(Booking_ID),
    FOREIGN KEY (Service) REFERENCES service_details(Service)
);

INSERT INTO customers (Cust_ID, Name, DOB, Age, Cust_Address) VALUES
(101, 'John', '1990-01-01', 36, '123 Main St'),
(102, 'Jane', '1985-05-15', 40, '456 Elm St'),
(103, 'Alice', '1992-09-30', 33, '789 Oak St'),
(104, 'Bob', '1988-12-20', 37, '321 Pine St'),
(105, 'Charlie', '1995-03-10', 31, '654 Maple St'),
(106, 'Diana', '1991-07-25', 31, '987 Cedar St'),
(107, 'Ethan', '1987-11-05', 38, '246 Birch St'),
(108, 'Fiona', '1993-02-14', 33, '135 Spruce St'),
(109, 'George', '1989-06-18', 34, '864 Willow St'),
(110, 'Hannah', '1994-10-22', 31, '753 Aspen St'),
(111, 'Ian', '1990-04-05', 36, '159 Chestnut St'),
(112, 'Jessica', '1986-08-30', 39, '852 Walnut St'),
(113, 'Kevin', '1992-12-12', 33, '951 Poplar St'),
(114, 'Laura', '1988-03-18', 38, '753 Fir St'),
(115, 'Michael', '1991-09-25', 34, '357 Redwood St'),
(116, 'Nina', '1987-01-10', 39, '258 Cypress St'),
(117, 'Oscar', '1993-05-20', 33, '654 Pine St'),
(118, 'Paula', '1989-11-15', 37, '951 Maple St'),
(119, 'Quentin', '1990-02-28', 36, '753 Oak St'),
(120, 'Rachel', '1988-07-12', 38, '159 Cedar St'),
(121, 'Steve', '1992-10-05', 33, '246 Spruce St'),
(122, 'Tina', '1986-04-18', 39, '135 Birch St'),
(123, 'Uma', '1991-12-30', 34, '864 Willow St'),
(124, 'Victor', '1987-06-25', 38, '753 Aspen St'),
(125, 'Wendy', '1993-03-15', 33, '159 Chestnut St');


INSERT INTO car_details (Car_Type, Base_Rent) VALUES
('Sedan', 50.00),
('SUV', 80.00),
('Truck', 90.00),
('Hatchback', 40.00),
('Convertible', 100.00),
('Minivan', 70.00),
('Coupe', 60.00),
('Wagon', 55.00),
('Sports Car', 120.00),
('Electric', 75.00);

INSERT INTO service_details (Service, Service_Charge) VALUES
('GPS', 5.00),
('Child Seat', 7.00),
('Extra Driver', 15.00),
('Wash & Vacuum', 20.00),
('Insurance', 15.00),
('Roadside Assistance', 8.00),
('Wi-Fi', 6.00),
('Ski Rack', 4.00),
('Bike Rack', 4.00),
('Car Seat', 7.00),
('Pet Friendly', 5.00);

INSERT INTO car_orders (Booking_ID, Car_Type, Cust_ID, Date_Of_Booking, Return_Date) VALUES
(1, 'SUV', 101, '2026-01-01', '2026-01-07'),
(2, 'Sedan', 102, '2026-02-15', '2026-02-20'),
(3, 'Truck', 103, '2026-03-10', '2026-03-15'),
(4, 'Hatchback', 104, '2026-04-05', '2026-04-10'),
(5, 'Convertible', 105, '2026-05-20', '2026-05-25'),
(6, 'Minivan', 106, '2026-06-01', '2026-06-07'),
(7, 'Coupe', 107, '2026-07-15', '2026-07-20'), 
(8, 'Wagon', 108, '2026-08-10', '2026-08-15'),
(9, 'Sports Car', 109, '2026-09-05', '2026-09-10'),
(10, 'Electric', 110, '2026-10-01', '2026-10-07'),
(11, 'Convertible', 111, '2026-11-10', '2026-11-15'),
(12, 'Sedan', 112, '2026-12-05', '2026-12-10'),
(13, 'Truck', 113, '2026-01-20', '2026-01-25'),
(14, 'Wagon', 114, '2026-02-10', '2026-02-15'),
(15, 'SUV', 115, '2026-03-05', '2026-09-10'),
(16, 'Minivan', 116, '2026-04-01', '2026-04-07'),
(17, 'Coupe', 117, '2026-05-15', '2026-05-20'),
(18, 'Wagon', 118, '2026-06-10', '2026-06-15'),
(19, 'Sports Car', 119, '2026-07-01', '2026-07-07'),
(20, 'Electric', 120, '2026-08-05', '2026-08-10'),
(21, 'Sedan', 101, '2026-09-01', '2026-09-07'),
(22, 'SUV', 102, '2026-10-15', '2026-10-20'),
(23, 'Truck', 103, '2026-11-10', '2026-11-15'),
(24, 'Hatchback', 104, '2026-12-05', '2026-12-10'),
(25, 'Convertible', 105, '2026-01-20', '2026-01-25');

INSERT INTO service_bookings (Booking_ID, Service) VALUES
(1, 'GPS'),
(1, 'Child Seat'),
(2, 'Extra Driver'),
(3, 'Wash & Vacuum'),
(3, 'GPS'),
(3, 'Insurance'),
(4, 'Insurance'),
(5, 'Roadside Assistance'),
(5, 'Wi-Fi'),
(6, 'Wi-Fi'),
(7, 'Ski Rack'),
(8, 'Bike Rack'),
(9, 'Car Seat'),
(10, 'Pet Friendly'),
(10, 'Ski Rack'),
(11, 'GPS'),
(12, 'Child Seat'),
(13, 'Extra Driver'),
(14, 'Wash & Vacuum'),
(15, 'Insurance'),
(15, 'Extra Driver'),
(16, 'Roadside Assistance'),
(17, 'Wi-Fi'),
(18, 'Ski Rack'),
(19, 'Bike Rack'),
(20, 'Car Seat'),
(21, 'Pet Friendly'),
(22, 'GPS'),
(23, 'Child Seat'),
(23, 'Insurance'),
(24, 'Extra Driver'),
(25, 'Wash & Vacuum'),
(25, 'Roadside Assistance');

