--liquibase formatted sql
--changeset rmartikan:create_ingredients_table splitStatements:true endDelimiter:;

create table INGREDIENTS (
     id bigserial primary key,
     name varchar(100) unique not null
);

--
-- Insert pre-defined ingredients.
--

insert into INGREDIENTS (name) values ('tomato'); -- 1
insert into INGREDIENTS (name) values ('pork'); -- 2
insert into INGREDIENTS (name) values ('cheese'); -- 3
insert into INGREDIENTS (name) values ('spaghetti'); -- 4
insert into INGREDIENTS (name) values ('chicken'); -- 5
insert into INGREDIENTS (name) values ('onion'); -- 6
insert into INGREDIENTS (name) values ('garlic'); -- 7
insert into INGREDIENTS (name) values ('beans'); -- 8
insert into INGREDIENTS (name) values ('paprika'); -- 9
insert into INGREDIENTS (name) values ('potato'); -- 10
insert into INGREDIENTS (name) values ('bacon'); -- 11
insert into INGREDIENTS (name) values ('fish'); -- 12
insert into INGREDIENTS (name) values ('egg'); -- 13