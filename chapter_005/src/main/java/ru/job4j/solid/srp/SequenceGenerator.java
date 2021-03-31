package ru.job4j.solid.srp;

import java.util.List;

public interface SequenceGenerator<T> {
    List<T> generate(int size);
}
