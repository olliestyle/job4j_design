package ru.job4j.solid.srp;

import java.util.List;

// Нарушение принципа SRP по причине наличия разной функциональности:
// 1. Генерация последовательности
// 2. Вывод
// Необходимо разделить на разные интерфейсы

public interface BadSequenceGenerator<T> {
    List<T> generate(int size);
    void print(List<T> numbers);
}
