package ru.job4j.collection;

public class SimpleQueue<T> {
    private int inSize = 0;
    private int outSize = 0;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (outSize == 0) {
            while (inSize != 0) {
                out.push(in.pop());
                outSize++;
                inSize--;
            }
        }
        outSize--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }
}
