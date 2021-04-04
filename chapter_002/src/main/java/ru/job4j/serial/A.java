package ru.job4j.serial;

import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

/**
 * Пример рекурсивного зацикливания.
 *
 * При преобразовании объектов в json-строку возможно рекурсивное зацикливание, простейший пример,
 * когда объект A содержит ссылку на объект B, а он в свою очередь ссылается на первоначальный объект A.
 * При выполнении будут осуществляться переходы по ссылке и сериализация до возникновения исключения StackOverflowError.
 * Чтобы избежать исключения необходимо разорвать цепочку, как правило это делается в момент перехода по ссылке на объект,
 * который уже сериализован. В библиотеке JSON-Java (org.json) для этого существует аннотация @JSONPropertyIgnore:
 */

public class A {
    private B b;

    @JSONPropertyIgnore
    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello";
    }
}
