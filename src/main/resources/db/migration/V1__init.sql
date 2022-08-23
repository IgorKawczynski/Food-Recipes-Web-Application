CREATE TYPE public.Difficulty AS ENUM('EASY', 'MEDIUM', 'HARD');
CREATE TYPE public.Meal_Type AS ENUM('BREAKFAST', 'DESSERT', 'DINNER', 'SUPPER');
CREATE TYPE public.Cuisine_Type AS ENUM('MEXICAN', 'TAI', 'POLISH', 'AMERICAN', 'FAST FOOD', 'OTHERS');
CREATE TYPE public.Unit AS ENUM('LITRES', 'MILILITERS', 'GRAMS', 'KILOGRAMS', 'PIECE');

CREATE SEQUENCE IF NOT EXISTS id_seq
AS BIGINT
INCREMENT BY 1
START WITH 10000;

CREATE TABLE IF NOT EXISTS public.USERS(
    ID BIGINT NOT NULL DEFAULT nextval('id_seq')PRIMARY KEY,
    email VARCHAR(50),
    password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS public.RECIPES(
    ID BIGINT NOT NULL DEFAULT nextval('id_seq')PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(40),
    description VARCHAR(255),
    instruction VARCHAR(255),
    preparation_time int,
    difficulty Difficulty,
    meal_type Meal_Type,
    cuisine_type Cuisine_Type,
    CONSTRAINT user_id_primary_key FOREIGN KEY(user_id) REFERENCES USERS(ID)
    );

CREATE TABLE IF NOT EXISTS public.INGREDIENTS(
    ID BIGINT NOT NULL DEFAULT nextval('id_seq')PRIMARY KEY,
    recipe_id BIGINT NOT NULL,
    name VARCHAR(40),
    quantity int,
    unit Unit,
    CONSTRAINT recipe_id_primary_key FOREIGN KEY(recipe_id) REFERENCES RECIPES(ID)
    );

CREATE TABLE IF NOT EXISTS public.FAVORITE_RECIPES(
    ID BIGINT NOT NULL DEFAULT nextval('id_seq')PRIMARY KEY,
    user_id BIGINT NOT NULL,
    recipe_id BIGINT NOT NULL,
    CONSTRAINT user_id_pkey FOREIGN KEY(user_id) REFERENCES USERS(ID),
    CONSTRAINT recipe_id_pkey FOREIGN KEY(recipe_id) REFERENCES RECIPES(ID)
    );
