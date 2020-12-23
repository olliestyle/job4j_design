create table Rules(
    id serial primary key,
    rule char(50) unique
);
create table Role(
    id serial primary key,
    role char(50) unique
);
create table RoleRules(
    id serial primary key,
    id_role int references role(id),
    id_rule int references rules(id)
);
create table Users(
    id serial primary key,
    name char(50),
    surname char(50),
    id_role int references role(id)
);
create table Category(
    id serial primary key,
    name char(50) unique
);
create table State(
    id serial primary key,
    state char(50) unique
);
create table Items(
    id serial primary key,
    description char(2000),
    user_id int references users(id),
    category_id int references category(id),
    state_id int references state(id)
);
create table Comments(
    id serial primary key,
    comment char(2000),
    item_id int references items(id)
);
create table Attaches(
    id serial primary key,
    attach char(2000),
    item_id int references items(id)
);

