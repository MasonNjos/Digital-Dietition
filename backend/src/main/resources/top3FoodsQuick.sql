-- -------------------------------------------------------
-- FOOD inserts
-- -------------------------------------------------------

INSERT INTO food (id, name, serving_size) VALUES (1,  'Sweet Potato (Canned Mashed)',       '1 cup (255g)');
INSERT INTO food (id, name, serving_size) VALUES (2,  'Brown Rice (Cooked Medium-Grain)',   '1 cup (195g)');
INSERT INTO food (id, name, serving_size) VALUES (3,  'Quinoa',                             '1 cup (185g)');
INSERT INTO food (id, name, serving_size) VALUES (4,  'Split Peas',                         '1 cup (196g)');
INSERT INTO food (id, name, serving_size) VALUES (5,  'Lentils',                            '1 cup (198g)');
INSERT INTO food (id, name, serving_size) VALUES (6,  'Black Beans',                        '1 cup (172g)');
INSERT INTO food (id, name, serving_size) VALUES (7,  'Avocado',                            '1 medium (150g)');
INSERT INTO food (id, name, serving_size) VALUES (8,  'Macadamia Nuts',                     '1 oz (28g)');
INSERT INTO food (id, name, serving_size) VALUES (9,  'Breakfast Sausage',                  '1 patty (35g)');
INSERT INTO food (id, name, serving_size) VALUES (10, 'Salmon',                           '1/2 fillet (154g)');
INSERT INTO food (id, name, serving_size) VALUES (11, 'Sardines',                         '1 can (84g)');
INSERT INTO food (id, name, serving_size) VALUES (12, 'Chia Seeds',                      '1 tbsp (10g)');
INSERT INTO food (id, name, serving_size) VALUES (13, 'Sunflower Seeds',                   '1 tbsp (9g)');
INSERT INTO food (id, name, serving_size) VALUES (14, 'tofu',                        '1 block (244g)');
INSERT INTO food (id, name, serving_size) VALUES (15, 'cashews',                      '1 oz (28g)');

-- -------------------------------------------------------
-- FOOD_NUTRIENTS inserts
-- (nutrient_name, nutrient_value, unit, pct, food_id)
-- pct left null — add later if you have daily value % data
-- -------------------------------------------------------

-- Carbs (g per serving)
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (1,  'Carbs', 59.1, 'g', NULL);
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (2,  'Carbs', 51.7, 'g', NULL);
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (3,  'Carbs', 39.4, 'g', NULL);

-- Fiber (g per serving)
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (4,  'Fiber', 16.0, 'g', NULL);
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (5,  'Fiber', 15.5, 'g', NULL);
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (6,  'Fiber', 15.0, 'g', NULL);

-- Fat (g per serving)
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (7,  'Fat', 21.0,  'g', NULL);
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (8,  'Fat', 18.0,  'g', NULL);
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (9,  'Fat', 14.6,  'g', NULL);

-- Omega-3 (g per serving)
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (10, 'Omega-3', 4.15, 'g', NULL);
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (11, 'Omega-3', 2.18, 'g', NULL);
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (12, 'Omega-3', 1.78, 'g', NULL);

-- Omega-6 (g per serving)
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (13, 'Omega-6', 2.02, 'g', NULL);
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (14, 'Omega-6', 4.67, 'g', NULL);
INSERT INTO food_nutrients (food_id, nutrient_name, nutrient_value, unit, pct) VALUES (15, 'Omega-6', 1.95, 'g', NULL);
