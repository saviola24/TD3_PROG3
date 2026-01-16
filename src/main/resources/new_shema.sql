CREATE TYPE dish_type AS ENUM ('STARTER', 'MAIN', 'DESSERT');
CREATE TYPE unit_type AS ENUM ('PCS', 'KG', 'L');
CREATE TYPE ingredient_category AS ENUM ('VEGETABLE', 'MEAT', 'FRUIT', 'DAIRY', 'OTHER');

CREATE TABLE Dish (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    dish_type dish_type,
    selling_price NUMERIC(10, 2)
);

CREATE TABLE Ingredient (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    category ingredient_category [cite: 40, 41],
);  

--table de jointure DishIngredient
CREATE TABLE DishIngredient (
    id SERIAL PRIMARY KEY,
    id_dish INT REFERENCES Dish(id),
    id_ingredient INT REFERENCES Ingredient(id),
    quantity_required NUMERIC(10, 2) NOT NULL,
    unit unit_type NOT NULL [cite: 31, 32, 33, 34, 35]
);

-- Insertion dans Dish
INSERT INTO Dish (id, name, dish_type, selling_price) VALUES
(1, 'Salade fraîche', 'STARTER', 3500.00),
(2, 'Poulet grillé', 'MAIN', 12000.00),
(3, 'Riz aux légumes', 'MAIN', NULL),
(4, 'Gâteau au chocolat', 'DESSERT', 8000.00),
(5, 'Salade de fruits', 'DESSERT', NULL);

-- Insertion dans DishIngredient
INSERT INTO DishIngredient (id, id_dish, id_ingredient, quantity_required, unit) VALUES
(1, 1, 1, 0.20, 'KG'),
(2, 1, 2, 0.15, 'KG'),
(3, 2, 3, 1.00, 'KG'),
(4, 4, 4, 0.30, 'KG'),
(5, 4, 5, 0.20, 'KG');