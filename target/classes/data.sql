INSERT INTO backfill_wells (id, active, object_type, well, stage, resource)
VALUES (1, 'true', 'AREA', 6, 1, 10);
INSERT INTO backfill_wells (id, active, object_type, well, stage, resource)
VALUES (2, 'true', 'AREA', 12, 1, 13);
INSERT INTO backfill_wells (id, active, object_type, well, stage, resource)
VALUES (3, 'true', 'AREA', 18, 1, 17);
INSERT INTO backfill_wells (id, active, object_type, well, stage, resource)
VALUES (4, 'true', 'AREA', 24, 1, 20);

INSERT INTO roads (id, active, object_type, bridge_exist, bridge_road_count, bridge_road_length, category, length, stage, resource)
VALUES (1, 'true', 'LINEAR', 'false', 0, 0, '4', 1, 1, 10);
INSERT INTO roads (id, active, object_type, bridge_exist, bridge_road_count, bridge_road_length, category, length, stage, resource)
VALUES (2, 'true', 'LINEAR', 'false', 0, 0, '4', 5, 1, 12);
INSERT INTO roads (id, active, object_type, bridge_exist, bridge_road_count, bridge_road_length, category, length, stage, resource)
VALUES (3, 'true', 'LINEAR', 'false', 0, 0, '4', 10, 1, 16);
INSERT INTO roads (id, active, object_type, bridge_exist, bridge_road_count, bridge_road_length, category, length, stage, resource)
VALUES (4, 'true', 'LINEAR', 'false', 0, 0, '4', 15, 1, 20);
INSERT INTO roads (id, active, object_type, bridge_exist, bridge_road_count, bridge_road_length, category, length, stage, resource)
VALUES (5, 'true', 'LINEAR', 'false', 0, 0, '4', 20, 1, 24);

INSERT INTO lines (id, active, object_type, power, length, stage, resource)
VALUES (1, 'true', 'LINEAR', 10, 1, 1, 10);
INSERT INTO lines (id, active, object_type, power, length, stage, resource)
VALUES (2, 'true', 'LINEAR', 10, 5, 1, 12);
INSERT INTO lines (id, active, object_type, power, length, stage, resource)
VALUES (3, 'true', 'LINEAR', 10, 10, 1, 16);
INSERT INTO lines (id, active, object_type, power, length, stage, resource)
VALUES (4, 'true', 'LINEAR', 10, 15, 1, 20);
INSERT INTO lines (id, active, object_type, power, length, stage, resource)
VALUES (5, 'true', 'LINEAR', 10, 20, 1, 24);

INSERT INTO mupns (id, active, object_type, square, stage, resource)
VALUES (1, 'true', 'AREA', 1, 1, 5);
INSERT INTO mupns (id, active, object_type, square, stage, resource)
VALUES (2, 'true', 'AREA', 2, 1, 7);
INSERT INTO mupns (id, active, object_type, square, stage, resource)
VALUES (3, 'true', 'AREA', 3, 1, 9);
INSERT INTO mupns (id, active, object_type, square, stage, resource)
VALUES (4, 'true', 'AREA', 4, 1, 11);

INSERT INTO vecs (id, active, object_type, power, stock_exist, square, stage, resource)
VALUES (1, 'true', 'AREA', 0, 'false', 1, 1, 5);
INSERT INTO vecs (id, active, object_type, power, stock_exist, square, stage, resource)
VALUES (2, 'true', 'AREA', 0, 'false', 2, 1, 7);
INSERT INTO vecs (id, active, object_type, power, stock_exist, square, stage, resource)
VALUES (3, 'true', 'AREA', 0, 'false', 3, 1, 9);
INSERT INTO vecs (id, active, object_type, power, stock_exist, square, stage, resource)
VALUES (4, 'true', 'AREA', 0, 'false', 4, 1, 11);

INSERT INTO vvps (id, active, object_type, helicopter_model, hall_exist, square, stage, resource)
VALUES (1, 'true', 'AREA', 'MI-8', 'false', 1, 1, 5);
INSERT INTO vvps (id, active, object_type, helicopter_model, hall_exist, square, stage, resource)
VALUES (2, 'true', 'AREA', 'MI-8', 'false', 2, 1, 7);
INSERT INTO vvps (id, active, object_type, helicopter_model, hall_exist, square, stage, resource)
VALUES (3, 'true', 'AREA', 'MI-16', 'false', 3, 1, 9);
INSERT INTO vvps (id, active, object_type, helicopter_model, hall_exist, square, stage, resource)
VALUES (4, 'true', 'AREA', 'MI-16', 'false', 4, 1, 11);

INSERT INTO cable_rack (id, active, object_type, length, stage, resource)
VALUES (1, 'true', 'LINEAR', 100, 1, 8);
INSERT INTO cable_rack (id, active, object_type, length, stage, resource)
VALUES (2, 'true', 'LINEAR', 500, 1, 12);
INSERT INTO cable_rack (id, active, object_type, length, stage, resource)
VALUES (3, 'true', 'LINEAR', 1000, 1, 16);
INSERT INTO cable_rack (id, active, object_type, length, stage, resource)
VALUES (4, 'true', 'LINEAR', 1500, 1, 20);
INSERT INTO cable_rack (id, active, object_type, length, stage, resource)
VALUES (5, 'true', 'LINEAR', 2000, 1, 24);

INSERT INTO vjks (id, active, object_type, capacity, square, stage, resource)
VALUES (1, 'true', 'AREA', 50, 1, 1, 5);
INSERT INTO vjks (id, active, object_type, capacity, square, stage, resource)
VALUES (2, 'true', 'AREA', 100, 2, 1, 7);
INSERT INTO vjks (id, active, object_type, capacity, square, stage, resource)
VALUES (3, 'true', 'AREA', 150, 3, 1, 9);
INSERT INTO vjks (id, active, object_type, capacity, square, stage, resource)
VALUES (4, 'true', 'AREA', 200, 4, 1, 11);