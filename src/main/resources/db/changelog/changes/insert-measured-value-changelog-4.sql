--liquibase formatted sql
--changeset habiba:insert-into-measured-value
-- splitStatements:true endDelimiter:;
INSERT INTO measured_value (value,unit,material_id) VALUES (2,'kilo',1);
INSERT INTO measured_value (value,unit,material_id) VALUES (20,'kilo',2);
INSERT INTO measured_value (value,unit,material_id) VALUES (2,'meter',3);
INSERT INTO measured_value (value,unit,material_id) VALUES (100,'kilo',4);