--liquibase formatted sql
--changeset rmartikan:create_meals_ingredients_table splitStatements:true endDelimiter:;

create table MEALS_INGREDIENTS (
    meals_id bigint not null,
    ingredients_id bigint not null,
    primary key(meals_id, ingredients_id),
    constraint FK_MEALS_INGREDIENTS_MEALS foreign key(meals_id)
        references MEALS(id)
        on delete cascade,
    constraint FK_MEALS_INGREDIENTS_INGREDIENTS foreign key(ingredients_id)
        references INGREDIENTS(id)
);

--
-- Insert pre-defined connections.
--

-- Fish & chips
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (1, 10);
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (1, 12);

-- Chili con carne
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (2, 1);
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (2, 2);
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (2, 6);
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (2, 7);
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (2, 8);
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (2, 9);

-- Pasta
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (3, 1);
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (3, 2);
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (3, 3);
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (3, 4);
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (3, 7);

-- Potato with paprika
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (4, 6);
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (4, 9);
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (4, 10);
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (4, 11);

-- Eggs & bacon
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (5, 11);
insert into MEALS_INGREDIENTS (meals_id, ingredients_id) values (5, 13);