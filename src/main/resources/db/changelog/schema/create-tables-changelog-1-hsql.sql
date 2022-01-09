CREATE TABLE location (
  id INT PRIMARY KEY,
  name varchar(50) UNIQUE NULL,
  temporary BIT NULL,
  type varchar(50) NULL
);
CREATE TABLE machine (
  id INT PRIMARY KEY,
  name varchar(255) NULL,
  assembly BIT NULL,
  press BIT NULL,
  location_key INT NULL,
  FOREIGN KEY (location_key) REFERENCES location(id)
);
CREATE TABLE material (
  id INT PRIMARY KEY,
  name varchar(255) UNIQUE NULL,
  measure_value  varchar(100) NULL,
  base_uom  varchar(100) NULL
);
CREATE TABLE production_order (
  id INT PRIMARY KEY,
  name varchar(100) UNIQUE NULL,
  start_date varchar(100) NULL,
  planned_quantity varchar(100) NULL,
  material_key int NULL,
  FOREIGN KEY (material_key) REFERENCES material (id)
);
CREATE TABLE operation (
  id INT PRIMARY KEY,
  name varchar(255) NULL,
  status varchar(50) NULL,
  machine_key int NULL,
  material_key int NULL,
  production_order_key int NULL,
  FOREIGN KEY (material_key) REFERENCES material (id),
  FOREIGN KEY (machine_key) REFERENCES machine (id),
  FOREIGN KEY (production_order_key) REFERENCES production_order (id)
);