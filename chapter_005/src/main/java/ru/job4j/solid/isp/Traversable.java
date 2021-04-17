package ru.job4j.solid.isp;

import java.util.Iterator;

/**
 * Пример,
 * Деревья поддерживают различные обходы, ошибкой ISP было написание подобного интерфейса
 */
public interface Traversable<T> {
    Iterator<T> preOrder();
    Iterator<T> inOrder();
    Iterator<T> postOrder();

    Iterator<T> bfs();
    Iterator<T> dfs();
}

/**
 * Всем деревьям придется реализовывать все обходы (либо наследоваться от абстрактного дерева которое это делает).
 * Но опять же другие программные сущности попросту не будут их использовать, а будут зависеть.
 * Решить это можно было так, последние два обхода на самом деле обходы для графов (дерево частный случай графа),
 * а первые три именно для деревьев.
 *
 * interface TreeTraversable<T> {
 *     Iterator<T> preOrder();
 *     Iterator<T> inOrder();
 *     Iterator<T> postOrder();
 * }
 *
 * interface GraphTraversable<T> {
 *     Iterator<T> bfs();
 *     Iterator<T> dfs();
 * }
 */
