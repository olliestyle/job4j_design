package ru.job4j.serial;

import java.io.*;
import java.nio.file.Files;

/**
 * Поле serialVersionUID - уникальный идентификатор версии сериализованного класса,
 * необходим для обеспечения механизмов версионности, т.е. нужен JVM для понимания,
 * что сериализованный объект при десериализации имеет те же члены класса, методы и пр.
 * Если значения не совпадают, будет выброшено исключение java.io.InvalidClassException.
 * Для наглядности в примере мы задаем значение поля вручную,
 * в реальной разработке лучше использовать штатный механизм Java генерации serialVersionUID или разработать свой.
 * При сериализации объекта сериализуются все объекты, на которые он ссылается в своих полях,
 * поэтому вложенные объекты тоже должны быть Serializable.
 * Для исключения полей из сериализации используется ключевое слово transient.
 * С помощью интерфейса Externalizable можно реализовать собственный алгоритм сериализации/десериализации,
 * для этого нужно переопределить два обязательных метода — writeExternal() и readExternal().
 */
public class ContactJSON implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public ContactJSON(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "zipCode=" + zipCode +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final ContactJSON contactJSON = new ContactJSON(123456, "+7 (111) 111-11-11");

        /* Запись объекта в файл */
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contactJSON);
        }

        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final ContactJSON contactJSONFromFile = (ContactJSON) ois.readObject();
            System.out.println(contactJSONFromFile);
        }
    }
}
