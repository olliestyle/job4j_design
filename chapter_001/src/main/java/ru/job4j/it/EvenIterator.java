package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {

    private int[] numbers;
    private int evenPointer;

    public EvenIterator(final int[] numbers) {
        this.numbers = numbers;
        evenPointer = 0;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = evenPointer; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                result = true;
                evenPointer = i;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[evenPointer++];
    }
}
