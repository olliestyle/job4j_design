package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    SimpleArray<T> simpleArray = new SimpleArray<>(10);

    public void add(T t) {
        for (T fromArray: simpleArray) {
            if (fromArray.equals(t)) {
                return;
            }
        }
        simpleArray.add(t);
    }

    @Override
    public String toString() {
        return simpleArray.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }
}
