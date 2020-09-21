package ru.job4j.collection;

import java.util.*;

public class MyHashMap<K, V> implements Iterable<MyHashMap.Node> {

    private int currentSize = 0;
    private int currentCapacity;
    private float loadFactor = 0.75f;
    private Node[] hashArray;
    private int modCount = 0;

    public static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key) && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    public int getCurrentCapacity() {
        return this.currentCapacity;
    }

    public int getCurrentSize() {
        return this.currentSize;
    }

    public MyHashMap(int initialCapacity) {
        this.currentCapacity = initialCapacity;
        hashArray = new Node[currentCapacity];
    }

    private int hash(K key) {
        int h = key.hashCode();
        return (key == null) ? 0 : h ^ (h >>> 16);
    }

    private int indexBucket(int hash, int currentCapacity) {
        return hash & (currentCapacity - 1);
    }

    private void resize(int newCapacity) {
        Node[] temp = new Node[newCapacity];
        transfer(temp);
        hashArray = temp;
    }

    private void transfer(Node[] nodes) {
        for (Node node: hashArray) {
            if (node != null) {
                int hash = hash((K) node.key);
                int index = indexBucket(hash, currentCapacity);
                nodes[index] = node;
            }
        }
    }

    public boolean insert(K key, V value) {
        if (currentSize > currentCapacity * loadFactor) {
            currentCapacity *= 2;
            resize(currentCapacity);
        }
        boolean result = false;
        int hash = hash(key);
        int index = indexBucket(hash, currentCapacity);
        if (hashArray[index] == null) {
            Node toInsert = new Node(key, value);
            hashArray[index] = toInsert;
            currentSize++;
            modCount++;
            result = true;
        } else if (hashArray[index].key.equals(key)) {
            hashArray[index].value = value;
            result = true;
        }
        return result;
    }

    public V get(K key) {
        int hash = hash(key);
        int index = indexBucket(hash, currentCapacity);
        V toReturn = null;
        if (hashArray[index] != null && hashArray[index].key.equals(key)) {
            toReturn = (V) hashArray[index].value;
        }
        return toReturn;
    }

    public boolean delete(K key) {
        int hash = hash(key);
        int index = indexBucket(hash, currentCapacity);
        boolean result = false;
        if (hashArray[index] != null && hashArray[index].key.equals(key)) {
            hashArray[index] = null;
            currentSize--;
            modCount--;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<Node> iterator() {
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
            public Node next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = index; i < iteratorCapacity; i++) {
                    index++;
                    if (hashArray[i] != null) {
                        hasNextSize--;
                        return hashArray[i];
                    }
                }
                return null;
            }
        };
    }
}
