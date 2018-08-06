# --- !Ups

CREATE TABLE Category (
  "catId" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "name" VARCHAR NOT NULL
);

CREATE TABLE Products (
 "prodId" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
 "title" VARCHAR  NOT NULL,
 "description" TEXT NOT NULL,
 "quantity" INTEGER NOT NULL,
 "price" FLOAT NOT NULL,
 "catId" int not null,
 foreign key("catId") references Category(catId) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE PaymentMethods (
  "paymentId" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "name" VARCHAR NOT NULL
);


CREATE TABLE Shipping (
  "shippingId" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "name" VARCHAR NOT NULL,
  "price" INT NOT NULL
);

# --- !Downs

DROP TABLE Products;
drop table Category;
DROP TABLE PaymentMethods;
DROP TABLE Shipping;