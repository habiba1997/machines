--liquibase formatted sql
--changeset habiba:insert-into-material
-- splitStatements:true endDelimiter:;
INSERT INTO material (id,name,percentage_color) VALUES (1,'Plastic',false);
INSERT INTO material (id,name,percentage_color) VALUES (2,'Almunium',false);
INSERT INTO material (id,name,percentage_color) VALUES (3,'Cashmere',true);
INSERT INTO material (id,name,percentage_color) VALUES (4,'Paper',false);