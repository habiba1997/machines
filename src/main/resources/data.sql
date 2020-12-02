INSERT INTO machine (name) VALUES ('Manlift');
INSERT INTO machine (name) VALUES ('Telehandlers');
INSERT INTO machine (name) VALUES ('Forklift');
INSERT INTO machine (name) VALUES ('Skid Steer Loader');


INSERT INTO operation (name,machine_id) VALUES ('Shaping',1);
INSERT INTO operation (name,machine_id) VALUES ('Labeling',1);
INSERT INTO operation (name,machine_id) VALUES ('Storing',1);

INSERT INTO operation (name,machine_id) VALUES ('Shaping',2);
INSERT INTO operation (name,machine_id) VALUES ('Labeling',2);

INSERT INTO operation (name,machine_id) VALUES ('Storing',3);

INSERT INTO operation (name,machine_id) VALUES ('Shaping',4);
INSERT INTO operation (name,machine_id) VALUES ('Labeling',4);
INSERT INTO operation (name,machine_id) VALUES ('Storing',4);


INSERT INTO material (name) VALUES ('Plastic');
INSERT INTO material (name) VALUES ('Almunium');
INSERT INTO material (name) VALUES ('Cashmere');
INSERT INTO material (name) VALUES ('Paper');

INSERT  INTO materials_operations (material_id,operation_id) VALUES (4,2);
INSERT  INTO materials_operations (material_id,operation_id) VALUES (2,7);
INSERT  INTO materials_operations (material_id,operation_id) VALUES (1,4);
INSERT  INTO materials_operations (material_id,operation_id) VALUES (1,1);
INSERT  INTO materials_operations (material_id,operation_id) VALUES (3,6);


INSERT INTO measured_value (value,unit,material_id) VALUES (2,'kilo',1);
INSERT INTO measured_value (value,unit,material_id) VALUES (20,'kilo',2);
INSERT INTO measured_value (value,unit,material_id) VALUES (2,'meter',3);
INSERT INTO measured_value (value,unit,material_id) VALUES (100,'kilo',4);
