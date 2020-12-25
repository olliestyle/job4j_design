/* 1. Написать запрос получение всех продуктов с типом "СЫР" */
select product.id, product.name, expired_data, price from product join type on product.type_id = type.id where type.name = 'Сыр';

/* 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное" */
select * from product where name like '%мороженное%';

/* 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце. */
select * from product where extract(month from current_timestamp + interval '1 month') = extract(month from expired_data);

/* 4. Написать запрос, который выводит самый дорогой продукт. */
select * from product order by price desc limit 1;

/* 5. Написать запрос, который выводит количество всех продуктов определенного типа. */
select type.name, count(*) from product join type on product.type_id = type.id where type.name = 'Сыр' group by type.name;
select count(*), type.name from product join type on product.type_id = type.id where type.name = 'Колбаса' group by type.name;

/* 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО" */
select product.id, product.name, type_id, expired_data, price from product join type on product.type_id = type.id where type.name = 'Сыр' or type.name = 'Колбаса';

/* 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.   */
select type.name, count(type_id) from product join type on product.type_id = type.id group by type.name having count(type_id) < 10;

/* 8. Вывести все продукты и их тип. */
select product.id, product.name, product.type_id, product.expired_data, product.price, t.name from product join type t on product.type_id = t.id;
