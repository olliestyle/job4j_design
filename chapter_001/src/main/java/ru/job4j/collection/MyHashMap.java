package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashMap<K, V> implements Iterable<V> {

    private int currentSize = 0;
    private int currentCapacity;
    private Object[] hashArray;
    private int modCount = 0;

    public int getCurrentCapacity() {
        return this.currentCapacity;
    }

    public int getCurrentSize() {
        return this.currentSize;
    }

    public MyHashMap(int initialCapacity) {
        this.currentCapacity = initialCapacity;
        hashArray = new Object[currentCapacity];
    }

    /**
     * Return value, that lie between 0 and currentCapacity (inclusively) by % operator
     * @param key
     * @return
     */
    private int hash(K key) {
        return key.hashCode() % currentCapacity;
    }

    /**
     * Increase array capacity after it is half full
     * @param key
     * @param value
     * @return
     */
    public boolean insert(K key, V value) {
        if (currentSize > currentCapacity / 2) {
            currentCapacity = currentCapacity * 2;
            hashArray = Arrays.copyOf(hashArray, currentCapacity);
        }
        int h = hash(key);
        boolean result = false;
        if (hashArray[h] == null) {
            hashArray[h] = value;
            currentSize++;
            modCount++;
            result = true;
        }
        return result;
    }

    public V get(K key) {
        int h = hash(key);
        if (hashArray[h] == null) {
            throw new NoSuchElementException();
        } else {
            return (V) hashArray[h];
        }
    }

    public boolean delete(K key) {
        int h = hash(key);
        boolean result = false;
        if (hashArray[h] != null) {
            hashArray[h] = null;
            currentSize--;
            modCount--;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator() {

            private int hasNextSize = currentSize;
            private int iteratorCapacity = currentCapacity;
            private int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return hasNextSize > 0;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = index; i < iteratorCapacity; i++) {
                    index++;
                    if (hashArray[i] != null) {
                        hasNextSize--;
                        return (V) hashArray[i];
                    }
                }
                return null;
            }
        };
    }
}
