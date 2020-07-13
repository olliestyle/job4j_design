package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] objects;
    private int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    private boolean checkIndex(int index) {
        int result = Objects.checkIndex(index, this.index);
        return result < 0 || result > this.index - 1;
    }

    public void add(T model) {
        this.objects[index++] = model;
    }

    public T get(int index) {
        if (checkIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        return (T) this.objects[index]; //downcasting - приводим Object к <T>
    }

    /**
     * Если элемент находится в середине списка, удаляем его, сдвигая ячейки влево.
     * Если элемент последний в массиве - присваиваем null.
      * @param index
     */
    public void remove(int index) {
        if (checkIndex(index)) {
            throw new IndexOutOfBoundsException();
        } else if (index == this.index - 1) {
            this.objects[index] = null;
        } else {
            for (int i = index; i < this.index; i++) {
                this.objects[i] = this.objects[i + 1];
            }
        }
    }

    public void set(int index, T model) {
        if (checkIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        this.objects[index] = model;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<>(this.objects, index);
    }
}
