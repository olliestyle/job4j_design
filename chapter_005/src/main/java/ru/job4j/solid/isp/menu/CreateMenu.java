package ru.job4j.solid.isp.menu;

public interface CreateMenu<T> {
    void addTask(T t);
    void addSubTask(T parent, T child);
}
