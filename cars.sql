create database CarManagement;

create table Body(
    id serial primary key,
    type char(50)
);
insert into Body (type) values ('sedan'), ('hatchback'), ('kupe'), ('pickup'), ('jeep'), ('cabriolet');
select * from Body;

create table Engine(
    id serial primary key,
    type char(50)
);
insert into Engine (type) values ('V6'), ('V8'), ('W12'), ('W16'), ('R4');
select * from Engine;

create table Transmission(
    id serial primary key,
    type char(50)
);
insert into Transmission (type) values ('Mech'), ('Auto'), ('Robot'), ('Variator');
select * from Transmission;

create table Car(
    id serial primary key,
    model char(50),
    body_id int references body(id) NOT NULL ,
    engine_id int references engine(id) NOT NULL,
    transmission_id int references transmission(id) NOT NULL 
);

insert into Car(model, body_id, engine_id, transmission_id)
VALUES ('audi_a6', 1, 2, 2), ('audi_a7', 3, 3, 2), ('lada_vesta', 1, 5, 1), ('peugeot_206', 2, 5, 2),
       ('bmw_x5', 5, 2, 2), ('renault_sandero', 2, 1, 1), ('mazda_3', 1, 3, 4), ('fiat', 1, 1, 1);

-- 1. Вывести список всех машин и все привязанные к ним детали.
select Car.model, Body.type, Engine.type, Transmission.type from Car
    join Body on Car.body_id = Body.id
    join Engine on Car.engine_id = Engine.id
    join Transmission on Car.transmission_id = Transmission.id;
-- 2. Вывести отдельно детали, которые не используются в машине - кузов
select Body.type as body_not_used from Body left join Car on Body.id = Car.body_id where Car.model is null;
-- 3. Вывести отдельно детали, которые не используются в машине - двигатель
select Engine.type as engine_not_used from Engine left join Car on Engine.id = Car.engine_id where Car.model is null;
-- 4. Вывести отдельно детали, которые не используются в машине - коробка передач
select Transmission.type as transmission_not_used from Car right join Transmission on Car.transmission_id = Transmission.id where Car.model is null;
