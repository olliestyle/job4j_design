/* 1. Создать таблицы и заполнить их начальными данными */

create table departments(
    id serial primary key,
    name char(50)
);

create table employees(
    id serial primary key,
    name char(50),
    depart_id int references departments(id)
);

select * from employees;
select * from departments;

insert into departments (name) values ('IT'), ('Legal'), ('Finance'), ('Science'), ('Management'), ('Sport');
insert into employees(name, depart_id) VALUES ('Petr', 1), ('Ivan', 1), ('Denis', 1);
insert into employees(name, depart_id) VALUES ('Semen', 2), ('Oleg', 2), ('Egor', 2), ('Pavel', 2);
insert into employees(name, depart_id) VALUES ('Vlad', 3), ('Sergey', 3), ('Anna', 3), ('Svetlana', 3);
insert into employees(name, depart_id) VALUES ('Alexey', 3), ('Valera', 4), ('Andrey', 4), ('Lubov', 4);
insert into employees(name, depart_id) VALUES ('Larisa', 5), ('Gleb', 5), ('Nataly', 5), ('Irina', 5);
insert into employees(name) values ('Alex'), ('Lera');

/* 2. Выполнить запросы с left, rigth, full, cross соединениями */

select * from employees e left join departments d on e.depart_id = d.id;
select * from departments d right join employees e on d.id = e.depart_id;

select * from departments d left join employees e on d.id = e.depart_id;
select * from employees e right join departments d on e.depart_id = d.id;

select * from departments d full join employees e on d.id = e.depart_id;
select * from employees e full join departments d on e.depart_id = d.id;

select * from departments as d cross join employees as e;
select * from employees e cross join departments d;

/* 3. Используя left join найти работников, которые не относятся ни к одну из департаментов */

select * from employees e left join departments d on e.depart_id = d.id where e.depart_id is null;

/* 4. Используя left и right join написать запросы, которые давали бы одинаковый результат */

select * from employees e left join departments d on e.depart_id = d.id;
select * from departments d right join employees e on d.id = e.depart_id;

select * from employees e right join departments d on e.depart_id = d.id;
select * from departments d left join employees e on d.id = e.depart_id;

/* 5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
   Используя cross join составить все возможные разнополые пары */

create table teens(
    id serial primary key,
    name char(50),
    sex char(6)
);

insert into teens (name, sex) VALUES ('Petr', 'Male'), ('Ivan', 'Male'), ('Denis', 'Male')
                                   , ('Semen', 'Male'), ('Oleg', 'Male'), ('Egor', 'Male')
                                   , ('Pavel', 'Male'), ('Vlad', 'Male'), ('Sergey', 'Male')
                                   , ('Anna', 'Female'), ('Svetlana', 'Female'), ('Alexey', 'Male')
                                   , ('Valera', 'Male'), ('Andrey', 'Male'), ('Lubov', 'Female')
                                   , ('Larisa', 'Female'), ('Gleb', 'Male'), ('Nataly', 'Female')
                                   , ('Irina', 'Female'), ('Alex', 'Male'), ('Lera', 'Female');

select t1.name, t2.name from teens t1 cross join teens t2 where t1.sex != t2.sex;
