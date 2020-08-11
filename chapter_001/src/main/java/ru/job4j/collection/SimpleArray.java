package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container;
    private int index = 0;
    private int size;
    private int modCount = 0;

    public SimpleArray(int size) {
        this.size = size;
        container = new Object[size];
    }

    public T get(int index) {
        return (T) container[Objects.checkIndex(index, this.index)];
    }

    public void add(T model) {
        modCount++;
        if (index == size) {
            increaseSize();
        }
        container[index++] = model;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int point = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return point < index;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                    return (T) container[point++];
            }
        };
    }

    private void increaseSize() {
        size = size * 2;
        Object[] tempContainer = container;
        container = new Object[size];
        System.arraycopy(tempContainer, 0, container, 0, size / 2);
    }

    public int getSize() {
        return this.size;
    }

}
