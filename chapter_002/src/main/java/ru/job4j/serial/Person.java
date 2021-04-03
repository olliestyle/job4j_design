package ru.job4j.serial;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

/**
 * JSON (JavaScript Object Notation) – текстовый формат обмена данными, основан на синтаксисе JavaScript,
 * удобен для написания и чтения человеком. людьми.
 * Несмотря на происхождение от JavaScript формат независим от него и может использоваться практически
 * с любым языком программирования, для многих из которых существуют готовые библиотеки для создания и
 * обработки данных в формате JSON.
 *
 * Применение:
 *
 * 1. наиболее часто применяется для пересылки информации между браузером и сервером (загрузка контента
 * по технологии Ajax) или между веб-сайтами (различные HTTP-соединения).
 * 2. можно использовать везде, где требуется обмен данных между программами;
 * 3. хранение локальной информации (например, настроек);
 * 4. за счёт лаконичности может быть выбран для сериализации сложных структур вместо XML.
 *
 *  Примитивные типы данных в JSON:
 *
 * число (целое или вещественное).
 * литералы true, false и null.
 * строка — символы юникода, заключённые в двойные кавычки.
 * Ссылочные типы данных:
 *
 * Объект - заключается в фигурные скобки ({ и }) и содержит разделенный запятой список пар имя/значение.
 * Массив - заключается в квадратные скобки ([ и ]) и содержит разделенный запятой список значений.
 */

public class Person {

    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String[] statuses;

    public Person(boolean sex, int age, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("112-111"), "Worker", "Married");

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /* Модифицируем json-строку */
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
//        final String personJson = gson.toJson(person);
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }

    public static class Contact {
        private final String phone;

        public Contact(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{"
                    + "phone='" + phone + '\''
                    + '}';
        }
    }
}
