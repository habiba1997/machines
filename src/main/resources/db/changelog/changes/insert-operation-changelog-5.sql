--liquibase formatted sql
--changeset habiba:insert-into-operation
--splitStatements:true endDelimiter:;
INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Shaping',1,1,6);
INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Labeling',1,1,5);
INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Storing',1,2,5);

INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Shaping',2,2,1);
INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Labeling',2,3,3);

INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Storing',3,4,3);

INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Shaping',4,3,6);
INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Labeling',4,4,0);
INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Storing',4,4,4);