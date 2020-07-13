package ru.job4j.generic;

import java.util.Iterator;

public class SimpleArrayIterator<T> implements Iterator<T> {

    private Object[] objects;
    private int point = 0;
    private int currentLength;

    public SimpleArrayIterator(Object[] objects, int currentLength) {
        this.objects = objects;
        this.currentLength = currentLength;
    }

    @Override
    public boolean hasNext() {
        return point < currentLength;
    }

    @Override
    public T next() {
        return (T) objects[point++];
    }
}
