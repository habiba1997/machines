--liquibase formatted sql
--changeset habiba:create-tables
-- splitStatements:true endDelimiter:;
CREATE TABLE `machine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `material` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `percentage_color` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `measured_value` (
  `id` int NOT NULL AUTO_INCREMENT,
  `unit` varchar(255) DEFAULT NULL,
  `value` int DEFAULT NULL,
  `material_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`material_id`) REFERENCES `material` (`id`)
);


CREATE TABLE `operation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `machine_id` int DEFAULT NULL,
  `material_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`material_id`) REFERENCES `material` (`id`),
  FOREIGN KEY (`machine_id`) REFERENCES `machine` (`id`)
);