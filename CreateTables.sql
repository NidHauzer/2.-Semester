USE WarehouseManagement;

--Remove tables if they exist
DROP TABLE IF EXISTS Warehouse, Location, ItemLine, Product, Shipment, Receiver, Address, ZipCity, Country, Employee;

GO

--Create all tables
CREATE TABLE Employee (
    name VARCHAR(128) NOT NULL,
    employeeNo INT NOT NULL PRIMARY KEY
);

CREATE TABLE Country (
    countryID INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    country VARCHAR(128) NOT NULL
)

CREATE TABLE ZipCity (
    zip VARCHAR(128) NOT NULL PRIMARY KEY,
    city VARCHAR(128) NOT NULL,
    countryID int NOT NULL FOREIGN KEY REFERENCES Country(countryID)
)

CREATE TABLE Address (
    streetName VARCHAR(128) NOT NULL,
    houseNo int NOT NULL,
    zip VARCHAR(128) NOT NULL FOREIGN KEY REFERENCES ZipCity(zip),
    addressId int NOT NULL PRIMARY KEY IDENTITY(1,1)
)

CREATE TABLE Receiver (
    name VARCHAR(128),
    phoneNo VARCHAR(128) NOT NULL PRIMARY KEY,
    addressId INT NOT NULL FOREIGN KEY REFERENCES Address(addressId)
);

CREATE TABLE Shipment (
    phoneNo VARCHAR(128) NOT NULL FOREIGN KEY REFERENCES Receiver(phoneNo),
    date DATE NOT NULL,
    employeeNo INT NOT NULL FOREIGN KEY REFERENCES Employee(employeeNo),
    shipmentNo INT NOT NULL IDENTITY(1,1) PRIMARY KEY
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
INSERT INTO Country VALUES ('Denmark');

INSERT INTO ZipCity VALUES ('9300', 'Saeby', '1');
INSERT INTO ZipCity VALUES ('4000', 'Roskilde', '1');

INSERT INTO Address VALUES ('Gl. Aalborgvej', 55, '9300')
INSERT INTO Address VALUES ('Lagervej', '12', '4000')

INSERT INTO Receiver VALUES ('Saeby Lager', '11223344', 1)
INSERT INTO Receiver VALUES ('Roskilde Lager', '12345678', 2);

INSERT INTO Product VALUES ('AAA123', '100', '10', 'Light Beige Blonde Mix 16B/60B', 'Tape Extension', '50', '50')

INSERT INTO Employee VALUES ('Thea', '1')
INSERT INTO Employee VALUES('Niels Christian', '2');
