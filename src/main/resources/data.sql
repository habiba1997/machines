INSERT INTO machine (name) VALUES ('Manlift');
INSERT INTO machine (name) VALUES ('Telehandlers');
INSERT INTO machine (name) VALUES ('Forklift');
INSERT INTO machine (name) VALUES ('Skid Steer Loader');

INSERT INTO material (name,percentage_color) VALUES ('Plastic',false);
INSERT INTO material (name,percentage_color) VALUES ('Almunium',false);
INSERT INTO material (name,percentage_color) VALUES ('Cashmere',true);
INSERT INTO material (name,percentage_color) VALUES ('Paper',false);


INSERT INTO measured_value (value,unit,material_id) VALUES (2,'kilo',1);
INSERT INTO measured_value (value,unit,material_id) VALUES (20,'kilo',2);
INSERT INTO measured_value (value,unit,material_id) VALUES (2,'meter',3);
INSERT INTO measured_value (value,unit,material_id) VALUES (100,'kilo',4);

INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Shaping',1,1,6);
INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Labeling',1,1,5);
INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Storing',1,2,5);

INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Shaping',2,2,1);
INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Labeling',2,3,3);

INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Storing',3,4,3);

INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Shaping',4,3,6);
INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Labeling',4,4,0);
INSERT INTO operation (name,machine_id,material_id,status) VALUES ('Storing',4,4,4);




