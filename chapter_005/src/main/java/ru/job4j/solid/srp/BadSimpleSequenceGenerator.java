package ru.job4j.solid.srp;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Даже после разделения интерфейсов этот класс нарушает SRP, он знает, КАК создать генератор случайных чисел,
// как его настроить, как генерировать (какой метод нужно вызвать).

// В этом проявляется связь с принципом DIP – классы должны зависеть от абстракций, а нет от реализаций.
// Чтобы решить эту проблему нужно выделить еще одну абстракция – генератор чисел.
// И сделать так чтобы генератор последовательности зависел от генерации числа

public class BadSimpleSequenceGenerator implements SequenceGenerator<Integer> {

    @Override
    public List<Integer> generate(int size) {
        Random random = new Random();
        return IntStream.range(0, size)
                .map(i -> random.nextInt()).boxed()
                .collect(Collectors.toList());
    }
}
