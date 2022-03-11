--liquibase formatted sql
--changeset rmartikan:create_meals_table splitStatements:true endDelimiter:;

create table MEALS (
    id bigserial primary key,
    name varchar(255) unique not null,
    price real not null check (price > 0),
    spicy integer default 0 check (spicy >= 0 and spicy <= 3),
    vegan boolean default false,
    gluten_free boolean default false,
    kcal integer not null check (kcal > 0)
);

--
-- Insert pre-defined meals.
--

insert into MEALS (name, price, spicy, vegan, gluten_free, kcal) values ('Fish & chips', 4.99, 0, false, false, 350); -- 1
insert into MEALS (name, price, spicy, vegan, gluten_free, kcal) values ('Chili con carne', 3.99, 3, false, false, 460); -- 2
insert into MEALS (name, price, spicy, vegan, gluten_free, kcal) values ('Pasta', 4.50, 0, false, false, 400); -- 3
insert into MEALS (name, price, spicy, vegan, gluten_free, kcal) values ('Potato with paprika', 2.15, 1, false, false, 395); -- 4
insert into MEALS (name, price, spicy, vegan, gluten_free, kcal) values ('Eggs & bacon', 1.99, 0, false, false, 200); -- 5