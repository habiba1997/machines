--liquibase formatted sql
--changeset habiba:insert-into-machines
-- splitStatements:true endDelimiter:;
INSERT INTO machine (id,name) VALUES (1,'Manlift');
INSERT INTO machine (id,name) VALUES (2,'Telehandlers');
INSERT INTO machine (id,name) VALUES (3,'Forklift');
INSERT INTO machine (id,name) VALUES (4,'Skid Steer Loader');