--liquibase formatted sql
--changeset habiba:create-tables
-- splitStatements:true endDelimiter:;
CREATE TABLE location (
  id bigint NOT NULL,
  name varchar(50) UNIQUE,
  temporary BIT NULL,
  type varchar(50) NULL,
  PRIMARY KEY (id)
);
CREATE TABLE machine (
  id bigint  NOT NULL,
  name varchar(255) NULL,
  assembly BIT NULL,
  press BIT NULL,
  location_key bigint NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (location_key) REFERENCES location(id)
);
CREATE TABLE material (
  id bigint  NOT NULL,
  name varchar(255) UNIQUE NULL,
  measure_value  varchar(100) NULL,
  base_uom  varchar(100) NULL,
  PRIMARY KEY (id)
);
CREATE TABLE production_order (
  id bigint  NOT NULL,
  name varchar(100) UNIQUE NULL,
  start_date varchar(100) NULL,
  planned_quantity varchar(100) NULL,
  material_key bigint NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (material_key) REFERENCES material (id)
);
CREATE TABLE operation (
  id bigint  NOT NULL,
  name varchar(255) NULL,
  status varchar(50) NULL,
  machine_key bigint NULL,
  material_key bigint NULL,
  production_order_key bigint NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (material_key) REFERENCES material (id),
  FOREIGN KEY (machine_key) REFERENCES machine (id),
  FOREIGN KEY (production_order_key) REFERENCES production_order (id)
);