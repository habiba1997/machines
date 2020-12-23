--liquibase formatted sql
--changeset habiba:insert-into-machines
-- splitStatements:true endDelimiter:;
INSERT INTO machine (id,name) VALUES (1,'Manlift');
INSERT INTO machine (name) VALUES ('Telehandlers');
INSERT INTO machine (name) VALUES ('Forklift');
INSERT INTO machine (name) VALUES ('Skid Steer Loader');