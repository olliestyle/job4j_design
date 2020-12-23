insert into rules(rule) values ('create');
insert into rules(rule) values ('read');
insert into rules(rule) values ('update');
insert into rules(rule) values ('delete');

insert into role(role) values ('admin');
insert into role(role) values ('root');
insert into role(role) values ('client');

insert into rolerules(id_role, id_rule) values (1, 1);
insert into rolerules(id_role, id_rule) values (1, 2);
insert into rolerules(id_role, id_rule) values (1, 3);
insert into rolerules(id_role, id_rule) values (3, 2);
insert into rolerules(id_role, id_rule) values (2, 1);
insert into rolerules(id_role, id_rule) values (2, 2);
insert into rolerules(id_role, id_rule) values (2, 3);
insert into rolerules(id_role, id_rule) values (2, 4);

insert into users(name, surname, id_role) values ('Murat', 'Baibolatov', 1);
insert into users(name, surname, id_role) values ('Egor', 'Egorov', 3);
insert into users(name, surname, id_role) values ('Pavel', 'Pavlov', 3);
insert into users(name, surname, id_role) values ('Ivan', 'Ivanov', 3);
insert into users(name, surname, id_role) values ('Denis', 'Denisov', 2);

insert into category(name) values ('Business');
insert into category(name) values ('Sport');
insert into category(name) values ('Food');

insert into state(state) values ('Active');
insert into state(state) values ('Done');
insert into state(state) values ('Queued');

insert into items(description, user_id, category_id, state_id)
VALUES ('Have a breakfast', 1, 3, 3);
insert into items(description, user_id, category_id, state_id)
VALUES ('Run 6 kilometers', 1, 2, 2);
insert into items(description, user_id, category_id, state_id)
VALUES ('Call the boss', 1, 1, 1);

insert into comments(comment, item_id) VALUES ('scrambled eggs with bacon', 1);
insert into comments(comment, item_id) VALUES ('at the stadium, 5 o''clock', 2);
insert into comments(comment, item_id) VALUES ('89111112122', 3);

insert into attaches(attach, item_id) VALUES ('http:\\localhost\food\eda.png', 1);
insert into attaches(attach, item_id) VALUES ('http:\\localhost\sport\krossovki.png', 2);
insert into attaches(attach, item_id) VALUES ('http:\\localhost\business\boss.png', 3);
select * from rules;
select * from role;
select * from rolerules;
select * from users;
select * from category;
select * from state;
select * from items;
select * from comments;
select * from attaches;
