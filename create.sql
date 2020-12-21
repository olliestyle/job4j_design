create table Rules(
    rule char(50) primary key
);
create table Role(
    role char(50) primary key
);
create table RoleRules(
                          id serial primary key,
                          char_role char(50) references role(role),
                          char_rule char(50) references rules(rule)
);
create table Users(
                      id serial primary key,
                      name char(50),
                      surname char(50),
                      role_name char(50) references role(role)
);
create table Category(
    name char(50) primary key
);
create table State(
    state char(50) primary key
);
create table Items(
                      id serial primary key,
                      description char(2000),
                      user_id int references users(id),
                      category_name char(50) references category(name),
                      state_name char(50) references state(state)
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

