--liquibase formatted sql
--changeset habiba:insert-into-materialEntity
-- splitStatements:true endDelimiter:;
INSERT INTO material (id,name,measure_value,base_uom) VALUES (1,'Plastic','25 PC', 'PC');
INSERT INTO material (id,name,measure_value,base_uom) VALUES (2,'Silk','30 m', 'm');
INSERT INTO material (id,name,measure_value,base_uom) VALUES (3,'Time','100 S', 'S');
INSERT INTO material (id,name,measure_value,base_uom) VALUES (4,'Paper','37 kg', 'g');
