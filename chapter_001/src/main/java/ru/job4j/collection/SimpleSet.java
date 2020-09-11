package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {

    private SimpleArray<T> simpleArray = new SimpleArray<>(10);

    public void add(T t) {
        if (contains(t)) {
            return;
        }
        simpleArray.add(t);
    }

    private boolean contains(T t) {
        for (T fromArray: simpleArray) {
            if (Objects.equals(fromArray, t)) {
                return true;
            }
        }
        return false;
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
