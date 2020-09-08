package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> last;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            last = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        last = node;
    }

    public void deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Object toReturn;
        if (head == last) {
            toReturn = head.value;
            head = null;
            return (T) toReturn;
        }
        Node<T> beforeLast = head;
        while (beforeLast.next != last) {
            beforeLast = beforeLast.next;
        }
        toReturn = last.value;
        last = beforeLast;
        return (T) toReturn;
    }

    public void revert() {
        Node<T> temp = null;
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.next = temp;
            temp = current;
            current = next;
        }
        head = temp;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}