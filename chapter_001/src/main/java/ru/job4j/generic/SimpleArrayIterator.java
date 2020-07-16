package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {

    private SimpleArray<T> simpleArray;
    private Object[] objects;
    private int point = 0;
    private int currentLength;

    public SimpleArrayIterator(SimpleArray<T> simpleArray, Object[] objects, int currentLength) {
        this.simpleArray = simpleArray;
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
        if (currentLength != simpleArray.getIndex()) {
            throw new RuntimeException();
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return (T) objects[point++];
    }
}
