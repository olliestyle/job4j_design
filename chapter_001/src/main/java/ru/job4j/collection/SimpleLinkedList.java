package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<T> implements Iterable<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;
//    private int index = 0;
    private int modCount = 0;

    public SimpleLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public void add(T item) {
        modCount++;
        Node newNode = new Node(item);
        if (first == null) {
            first = newNode;
            last = first;
//            first.setIndex(index++);
            size++;
        } else {
            last.setNext(newNode);
            last = newNode;
//            last.setIndex(index++);
            size++;
        }
    }

    public T get(int index) {
        Objects.checkIndex(index, this.size);
        Node<T> nodeToFind = first;
        T itemToFound = null;

        for (int i = 0; i != index; i++) {
            nodeToFind = nodeToFind.getNext();
        }
        itemToFound = nodeToFind.getItem();

//        while (nodeToFind != null) {
//            if (nodeToFind.getIndex() == index) {
//                itemToFound = nodeToFind.getItem();
//            }
//            nodeToFind = nodeToFind.getNext();
//        }
        return itemToFound;
    }

    public static void main(String[] args) {
        SimpleLinkedList<Integer> list = new SimpleLinkedList();

        list.add(2);
        list.add(3);
        list.add(4);
        for (int i = 0; i < list.size; i++) {
            System.out.println("Index " + i + " is "  + list.get(i));
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator() {

            private int point = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return get(point++);
            }
        };
    }
}
