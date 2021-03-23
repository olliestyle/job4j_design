package ru.job4j;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MaxMin {

    public <T> T find(List<T> values, Comparator<T> comparator) {
        Iterator<T> iterator = values.iterator();
        T toReturn = iterator.next();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (comparator.compare(toReturn, current) < 0) {
                toReturn = current;
            }
        }
        return toReturn;
    }

    public <T> T max(List<T> values, Comparator<T> comparator) {
        return find(values, comparator);
    }

    public <T> T min(List<T> values, Comparator<T> comparator) {
        return find(values, comparator.reversed());
    }
}
