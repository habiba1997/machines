--liquibase formatted sql
--changeset habiba:create-tables
-- splitStatements:true endDelimiter:;
CREATE TABLE location (
  id int AUTO_INCREMENT UNIQUE NOT NULL,
  name varchar(50) UNIQUE,
  temporary BIT DEFAULT NULL,
  type varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE machine (
  id int  AUTO_INCREMENT UNIQUE NOT NULL,
  name varchar(255) DEFAULT NULL,
  assembly BIT DEFAULT NULL,
  press BIT DEFAULT NULL,
  location_key INT DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (location_key) REFERENCES location(id)
);
CREATE TABLE material (
  id int  AUTO_INCREMENT UNIQUE NOT NULL,
  name varchar(255) UNIQUE DEFAULT NULL,
  measure_value  varchar(100) DEFAULT NULL,
  base_uom  varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE production_order (
  id int  AUTO_INCREMENT UNIQUE NOT NULL,
  name varchar(100) UNIQUE DEFAULT NULL,
  start_date varchar(100) DEFAULT NULL,
  planned_quantity varchar(100) DEFAULT NULL,
  material_key int DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (material_key) REFERENCES material (id)
);
CREATE TABLE operation (
  id int  AUTO_INCREMENT UNIQUE NOT NULL,
  name varchar(255) DEFAULT NULL,
  status varchar(50) DEFAULT NULL,
  machine_key int DEFAULT NULL,
  material_key int DEFAULT NULL,
  production_order_key int DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (material_key) REFERENCES material (id),
  FOREIGN KEY (machine_key) REFERENCES machine (id),
  FOREIGN KEY (production_order_key) REFERENCES production_order (id)
);