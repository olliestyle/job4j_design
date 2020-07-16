package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

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

    /**
     * Сравниваем длину массива, переданного изначально, с текущей длинной массива, если не равны кидаем эксепшн
     * @return
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return (T) objects[point++];
    }
}
