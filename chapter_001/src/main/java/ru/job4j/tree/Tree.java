package ru.job4j.tree;

import java.util.*;
import java.util.stream.Collectors;

public class Tree<E> implements SimpleTree<E> {

    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> par = findBy(parent);
        if (par.isPresent()) {
            ArrayList<E> elements = (ArrayList<E>) par.get().children.stream().map(n -> n.value).collect(Collectors.toList());
            if (!elements.contains(child)) {
                Node<E> toAdd = new Node<>(child);
                par.get().children.add(toAdd);
                result = true;
            }
        }
        return result;
    }

    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.children.size() > 2) {
                result = false;
                break;
            }
            data.addAll(el.children);
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
