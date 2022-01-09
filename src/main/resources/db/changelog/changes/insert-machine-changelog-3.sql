--liquibase formatted sql
--changeset habiba:insert-into-machines
-- splitStatements:true endDelimiter:;
INSERT INTO machine (id,name,assembly, press,location_key) VALUES (1,'Manlift',1,0,null);
INSERT INTO machine (id,name,assembly, press,location_key) VALUES (2,'Telehandlers',0,1,4);
INSERT INTO machine (id,name,assembly, press,location_key) VALUES (3,'Forklift',0,1,null);
INSERT INTO machine (id,name,assembly, press,location_key) VALUES (4,'Skid Steer Loader',1,0,1);
