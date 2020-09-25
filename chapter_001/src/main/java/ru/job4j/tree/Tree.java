package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class Tree<E> implements SimpleTree<E> {

    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> par = findBy(parent);
        Optional<Node<E>> chi = findBy(child);
        if (par.isPresent() && !chi.isPresent()) {
            Node<E> toAdd = new Node<>(child);
            par.get().children.add(toAdd);
            result = true;
        }
        return result;
    }

    public Optional<Node<E>> searchForCondition(Predicate<Node<E>> p) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (p.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    public boolean isBinary() {
        boolean result = false;
        if (!searchForCondition(p -> p.children.size() > 2).isPresent()) {
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return searchForCondition(p -> Objects.equals(p.value, value));
    }
}
