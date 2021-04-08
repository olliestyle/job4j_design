package ru.job4j.solid.lsp;

import java.util.function.BiFunction;

public class AutoTransport {
    protected float fuel;

    public AutoTransport(float fuel) {
        this.fuel = fuel;
    }

    public void move(float km) {
        if (km < 0) {
            throw new IllegalArgumentException("Invalid distance!");
        }
        if (fuel < 0) { // <= предусловие
            throw new IllegalArgumentException("Need more fuel!");
        }
        // other logic
    }
}

/**
 * 1. Предусловия (Preconditions) не могут быть усилены в подклассе
 * Предусловие - это условие, которое проверяет корректность аргументов конструктора или метода.
 */
class Bus extends AutoTransport {

    public Bus(float fuel) {
        super(fuel);
    }

    public void move(float km) {
        if (km < 0) {
            throw new IllegalArgumentException("Invalid distance!");
        }
        /**
         * От AutoTransport мы ожидаем, что машина сдвинется, но нет.
         * Автобус не сдвигается, т.к. в нем усилино предусловие. Ожидаем мы одно поведение, а получаем другое.
         * Чтобы сдвинуть автобус на придеться дописать доп. условие, чтобы проверить является ли траспорт автобусом.
         * Далее уже скормить ему больше топлива.
         */
        if (fuel < 5) { // условие усилено
            throw new IllegalArgumentException("Need more fuel!");
        }
        // other logic
    }

}

class FirstRule {
    public static void main(String[] args) {
        AutoTransport bus = new Bus(3);
        bus.move(2);
    }
}
