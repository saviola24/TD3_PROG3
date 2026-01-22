CREATE TYPE dish_type AS ENUM ('STARTER', 'MAIN', 'DESSERT');
CREATE TYPE ingredient_category AS ENUM ('VEGETABLE', 'MEAT', 'FRUIT', 'DAIRY', 'OTHER');
CREATE TYPE unit_type AS ENUM ('PCS', 'KG', 'L');
CREATE TYPE movement_type AS ENUM ('IN', 'OUT');

CREATE TABLE Dish (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      dish_type dish_type,
                      selling_price NUMERIC
);

CREATE TABLE Ingredient (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            price NUMERIC NOT NULL,
                            category ingredient_category
);

CREATE TABLE DishIngredient (
                                id SERIAL PRIMARY KEY,
                                id_dish INT REFERENCES Dish(id),
                                id_ingredient INT REFERENCES Ingredient(id),
                                quantity_required NUMERIC NOT NULL,
                                unit unit_type NOT NULL
);

CREATE TABLE StockMovement (
                               id SERIAL PRIMARY KEY,
                               id_ingredient INT REFERENCES Ingredient(id),
                               quantity NUMERIC NOT NULL,
                               type movement_type NOT NULL,
                               unit unit_type NOT NULL,
                               creation_datetime TIMESTAMP NOT NULL
);