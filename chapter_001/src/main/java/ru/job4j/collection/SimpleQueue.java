package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private int inSize = 0;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        while (inSize != 0) {
            out.push(in.pop());
            inSize--;
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }
}
