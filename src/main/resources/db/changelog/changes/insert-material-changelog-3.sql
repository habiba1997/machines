--liquibase formatted sql
--changeset habiba:insert-into-material
-- splitStatements:true endDelimiter:;
INSERT INTO material (name,percentage_color) VALUES ('Plastic',false);
INSERT INTO material (name,percentage_color) VALUES ('Almunium',false);
INSERT INTO material (name,percentage_color) VALUES ('Cashmere',true);
INSERT INTO material (name,percentage_color) VALUES ('Paper',false);