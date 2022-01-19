--liquibase formatted sql
--changeset habiba:create-tables
-- splitStatements:true endDelimiter:;
CREATE TABLE location (
  id INT NOT NULL,
  name varchar(50) UNIQUE,
  temporary BIT NULL,
  type varchar(50) NULL,
  PRIMARY KEY (id)
);
CREATE TABLE machine (
  id int  NOT NULL,
  name varchar(255) NULL,
  assembly BIT NULL,
  press BIT NULL,
  location_key INT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (location_key) REFERENCES location(id)
);
CREATE TABLE material (
  id int  NOT NULL,
  name varchar(255) UNIQUE NULL,
  measure_value  varchar(100) NULL,
  base_uom  varchar(100) NULL,
  PRIMARY KEY (id)
);
CREATE TABLE production_order (
  id int  NOT NULL,
  name varchar(100) UNIQUE NULL,
  start_date varchar(100) NULL,
  planned_quantity varchar(100) NULL,
  material_key int NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (material_key) REFERENCES material (id)
);
CREATE TABLE operation (
  id int  NOT NULL,
  name varchar(255) NULL,
  status varchar(50) NULL,
  machine_key int NULL,
  material_key int NULL,
  production_order_key int NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (material_key) REFERENCES material (id),
  FOREIGN KEY (machine_key) REFERENCES machine (id),
  FOREIGN KEY (production_order_key) REFERENCES production_order (id)
);