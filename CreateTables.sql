USE WarehouseManagement;

--Remove tables if they exist
DROP TABLE IF EXISTS Warehouse, Location, ItemLine, Product, Shipment, Party, Address, ZipCity, Employee
GO

CREATE TABLE Employee (
    name VARCHAR(128) NOT NULL,
    employeeNo int NOT NULL PRIMARY KEY
);

CREATE TABLE ZipCity (
    zip VARCHAR(128) NOT NULL PRIMARY KEY,
    city VARCHAR(128)
)

CREATE TABLE Address (
    addressId int NOT NULL PRIMARY KEY IDENTITY(1,1),
    streetName VARCHAR(128) NOT NULL,
    houseNo int NOT NULL,
    zip VARCHAR(128) FOREIGN KEY REFERENCES ZipCity(zip),
    country VARCHAR(128)
)

CREATE TABLE Party (
    name VARCHAR(128),
    phoneNo VARCHAR(128) NOT NULL PRIMARY KEY,
    addressId int NOT NULL FOREIGN KEY REFERENCES Address(addressId)
)

CREATE TABLE Shipment (
    phoneNo VARCHAR(128) NOT NULL FOREIGN KEY REFERENCES Party(phoneNo),
    shipmentNo int NOT NULL IDENTITY(1,1) PRIMARY KEY,
    date DATE NOT NULL,
    employeeNo int NOT NULL FOREIGN KEY REFERENCES Employee(employeeNo)
)

CREATE TABLE Product (
    barcode VARCHAR(128) NOT NULL PRIMARY KEY,
    quantityInStock int NOT NULL,
    minStock int NOT NULL,
    colour VARCHAR(128) NOT NULL,
    type VARCHAR(128) NOT NULL,
    length int NOT NULL,
    amount int NOT NULL
)

CREATE TABLE ItemLine (
    quantity int NOT NULL,
    shipmentNo int NOT NULL FOREIGN KEY REFERENCES Shipment(shipmentNo),
    barcode VARCHAR(128) NOT NULL FOREIGN KEY REFERENCES Product(barcode),
    id int NOT NULL IDENTITY(1,1) PRIMARY KEY
)

CREATE TABLE Location (
    locationCode VARCHAR(128) NOT NULL PRIMARY KEY,
    barcode VARCHAR(128) NOT NULL FOREIGN KEY REFERENCES Product(barcode),
)

CREATE TABLE Warehouse (
    addressId int NOT NULL FOREIGN KEY REFERENCES Address(addressId),
    warehouseId varchar(128) NOT NULL PRIMARY KEY,
    locationCode varchar(128) NOT NULL FOREIGN KEY REFERENCES Location(locationCode)
)

--Insert some test data
INSERT INTO ZipCity VALUES ('9300', 'Sæby');

INSERT INTO Address VALUES ('Gl. Aalborgvej', 55, '9300', 'Denmark')

INSERT INTO Party VALUES ('Jonas Vittrup Biegel', '11223344', 1)

INSERT INTO Product VALUES ('AAA123', '100', '10', 'Light Beige Blonde Mix 16B/60B', 'Tape Extension', '50', '50')

INSERT INTO Location VALUES ('A1', 'AAA123')

INSERT INTO Employee VALUES ('Thea', '1')