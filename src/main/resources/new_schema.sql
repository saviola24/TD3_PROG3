CREATE TYPE dish_type AS ENUM ('STARTER', 'MAIN', 'DESSERT');
CREATE TYPE ingredient_category AS ENUM ('VEGETABLE', 'MEAT', 'FRUIT', 'DAIRY', 'OTHER');
CREATE TYPE unit_type AS ENUM ('PCS', 'KG', 'L');
CREATE TYPE movement_type AS ENUM ('IN', 'OUT');

CREATE TABLE Dish (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      dish_type dish_type NOT NULL,
                      selling_price NUMERIC(12,2)
);

CREATE TABLE Ingredient (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL UNIQUE,
                            price NUMERIC(12,2) NOT NULL CHECK ( price >= 0 ),
                            category ingredient_category
);

CREATE TABLE DishIngredient (
                                id SERIAL PRIMARY KEY,
                                id_dish INT REFERENCES Dish(id),
                                id_ingredient INT REFERENCES Ingredient(id),
                                quantity_required NUMERIC NOT NULL,
                                unit unit_type NOT NULL,
    UNIQUE (id_dish, id_ingredient)
);

CREATE TABLE StockMovement (
                               id SERIAL PRIMARY KEY,
                               id_ingredient INT REFERENCES Ingredient(id),
                               quantity NUMERIC NOT NULL,
                               type movement_type NOT NULL,
                               unit unit_type NOT NULL,
                               creation_datetime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (id)
);

INSERT INTO Ingredient (id, name, price, category) VALUES
                                                       (1, 'Laitue',   800.00, 'VEGETABLE'),
                                                       (2, 'Tomate',   600.00, 'VEGETABLE'),
                                                       (3, 'Poulet',  4500.00, 'MEAT'),
                                                       (4, 'Chocolat', 4666.67,'OTHER'),
                                                       (5, 'Beurre',   1000.00,'DAIRY');

INSERT INTO Dish (id, name, dish_type, selling_price) VALUES
                          ps.executeUpdate();                                (1, 'Salade fraîche',     'STARTER',  3500.00),
                                                          (2, 'Poulet grillé',      'MAIN',    12000.00),
                                                          (3, 'Riz aux légumes',    'MAIN',    NULL),
                                                          (4, 'Gâteau au chocolat', 'DESSERT',  8000.00),
                                                          (5, 'Salade de fruits',   'DESSERT',  NULL);

INSERT INTO DishIngredient (id, id_dish, id_ingredient, quantity_required, unit) VALUES
                                                                                     (1, 1, 1, 0.20, 'KG'),
                                                                                     (2, 1, 2, 0.15, 'KG'),
                                                                                     (3, 2, 3, 1.00, 'KG'),
                                                                                     (4, 4, 4, 0.30, 'KG'),
                                                                                     (5, 4, 5, 0.20, 'KG');

INSERT INTO StockMovement (id, id_ingredient, quantity, type, unit, creation_datetime) VALUES
( 1, 1,  5.0, 'IN',  'KG', '2024-01-05 08:00:00'),
( 2, 1,  0.2, 'OUT', 'KG', '2024-01-06 12:00:00'),
( 3, 2,  4.0, 'IN',  'KG', '2024-01-05 08:00:00'),
( 4, 2, 0.15, 'OUT', 'KG', '2024-01-06 12:00:00'),
( 5, 3, 10.0, 'IN',  'KG', '2024-01-04 09:00:00'),
( 6, 3,  1.0, 'OUT', 'KG', '2024-01-06 12:00:00'),
( 7, 4,  3.0, 'IN',  'KG', '2024-01-06 13:00:00'),
( 8, 4,  0.3, 'OUT', 'KG', '2024-01-06 12:00:00'),
( 9, 5,  2.5, 'IN',  'KG', '2024-01-05 10:00:00'),
(10, 5,  0.2, 'OUT', 'KG', '2024-01-06 12:00:00');

CREATE TABLE IF NOT EXISTS "order" (
                                       id SERIAL PRIMARY KEY,
                                       reference VARCHAR(50) UNIQUE NOT NULL,
                                       total_ht NUMERIC(10, 2) NOT NULL,
                                       total_ttc NUMERIC(10, 2) NOT NULL,
                                       creation_datetime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS dish_order (
                                          id SERIAL PRIMARY KEY,
                                          id_order INTEGER NOT NULL REFERENCES "order"(id) ON DELETE CASCADE,
                                          id_dish INTEGER NOT NULL REFERENCES dish(id) ON DELETE RESTRICT,
                                          quantity INTEGER NOT NULL CHECK (quantity > 0)
);
CREATE INDEX IF NOT EXISTS idx_order_reference ON "order"(reference);

CREATE TABLE restaurant_table (
                                  id SERIAL PRIMARY KEY,
                                  number INT NOT NULL UNIQUE
);
ALTER TABLE "order"
    ADD COLUMN id_table INT NOT NULL REFERENCES restaurant_table(id),
    ADD COLUMN arrival_datetime TIMESTAMP NOT NULL,
    ADD COLUMN departure_datetime TIMESTAMP NOT NULL;