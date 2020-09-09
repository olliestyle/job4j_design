package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private int inSize = 0;
    private int outSize = 0;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T result;
        while (inSize != 0) {
            out.push(in.pop());
            outSize++;
            inSize--;
        }
        result = out.pop();
        outSize--;
        while (outSize != 0) {
            in.push(out.pop());
            outSize--;
            inSize++;
        }
        return result;
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }
}
