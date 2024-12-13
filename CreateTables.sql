USE WarehouseManagement;

--Remove tables if they exist
DROP TABLE IF EXISTS Warehouse, Location, ItemLine, Product, Shipment, Party, Address, ZipCity, Employee, ZipCountry;
GO

--Create all tables
CREATE TABLE Employee (
    name VARCHAR(128) NOT NULL,
    employeeNo INT NOT NULL PRIMARY KEY
);

CREATE TABLE ZipCountry (
    zip VARCHAR(128) NOT NULL PRIMARY KEY,
    country VARCHAR(128) NOT NULL
);

CREATE TABLE Address (
    addressId INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    streetName VARCHAR(128) NOT NULL,
    houseNo INT NOT NULL,
    zip VARCHAR(128) NOT NULL FOREIGN KEY REFERENCES ZipCountry(zip)
);

CREATE TABLE Party (
    name VARCHAR(128),
    phoneNo VARCHAR(128) NOT NULL PRIMARY KEY,
    addressId INT NOT NULL FOREIGN KEY REFERENCES Address(addressId)
);

CREATE TABLE Shipment (
    phoneNo VARCHAR(128) NOT NULL FOREIGN KEY REFERENCES Party(phoneNo),
    shipmentNo INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    date DATE NOT NULL,
    employeeNo INT NOT NULL FOREIGN KEY REFERENCES Employee(employeeNo)
);

CREATE TABLE Product (
    barcode VARCHAR(128) NOT NULL PRIMARY KEY,
    quantityInStock INT NOT NULL,
    minStock INT NOT NULL,
    colour VARCHAR(128) NOT NULL,
    type VARCHAR(128) NOT NULL,
    length INT NOT NULL,
    amount INT NOT NULL
);

CREATE TABLE ItemLine (
    quantity INT NOT NULL,
    shipmentNo INT NOT NULL FOREIGN KEY REFERENCES Shipment(shipmentNo),
    barcode VARCHAR(128) NOT NULL FOREIGN KEY REFERENCES Product(barcode),
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY
);

CREATE TABLE Location (
    locationCode VARCHAR(128) NOT NULL PRIMARY KEY,
    barcode VARCHAR(128) NOT NULL FOREIGN KEY REFERENCES Product(barcode)
);

CREATE TABLE Warehouse (
    addressId INT NOT NULL FOREIGN KEY REFERENCES Address(addressId),
    warehouseId VARCHAR(128) NOT NULL PRIMARY KEY,
    locationCode VARCHAR(128) NOT NULL FOREIGN KEY REFERENCES Location(locationCode)
);

--Insert some test data
INSERT INTO ZipCountry VALUES ('9300', 'Denmark');
INSERT INTO ZipCountry VALUES ('4000', 'Denmark');

INSERT INTO Address (streetName, houseNo, zip) VALUES ('Gl. Aalborgvej', 55, '9300');
INSERT INTO Address (streetName, houseNo, zip) VALUES ('Lagervej', 12, '4000');
