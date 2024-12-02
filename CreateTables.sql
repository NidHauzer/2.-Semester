USE WarehouseManagement

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

CREATE TABLE Supplier (
    name VARCHAR(128),
    cvrNumber VARCHAR(128) NOT NULL PRIMARY KEY,
    addressId int NOT NULL FOREIGN KEY REFERENCES Address(addressId)
)

CREATE TABLE Party (
    name VARCHAR(128),
    phoneNo VARCHAR(128) NOT NULL PRIMARY KEY,
    addressId int NOT NULL FOREIGN KEY REFERENCES Address(addressId)
)

CREATE TABLE Warehouse (
    addressId int NOT NULL FOREIGN KEY REFERENCES Address(addressId),
    warehouseId int NOT NULL PRIMARY KEY
)

CREATE TABLE Shipment (
    phoneNo VARCHAR(128) NOT NULL FOREIGN KEY REFERENCES Party(phoneNo),
    shipmentNo int NOT NULL IDENTITY(1,1) PRIMARY KEY,
    date DATE NOT NULL,
    employeeNo int NOT NULL FOREIGN KEY REFERENCES Employee(employeeNo)
)

CREATE TABLE SupplyOrder (
    orderNo int NOT NULL IDENTITY(1,1) PRIMARY KEY,
    date DATE NOT NULL,
    cvrNumber VARCHAR(128) NOT NULL FOREIGN KEY REFERENCES Supplier(cvrNumber),
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
    shipmentNo int FOREIGN KEY REFERENCES Shipment(shipmentNo),
    orderNo int FOREIGN KEY REFERENCES SupplyOrder(orderNo),
    barcode VARCHAR(128) NOT NULL FOREIGN KEY REFERENCES Product(barcode),
    id int NOT NULL IDENTITY(1,1) PRIMARY KEY
)

CREATE TABLE Location (
    locationCode VARCHAR(128) NOT NULL PRIMARY KEY,
    barcode VARCHAR(128) NOT NULL FOREIGN KEY REFERENCES Product(barcode),
    warehouseId int NOT NULL FOREIGN KEY REFERENCES Warehouse(warehouseId)
)