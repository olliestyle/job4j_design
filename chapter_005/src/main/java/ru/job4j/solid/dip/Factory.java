package ru.job4j.solid.dip;

public class Factory {

    /**
     * Здесь нарушается DIP в возвращаемом значении и в параметре метода, т.к. уволить можно кого угодно и необходимо
     * создать интерфейс Employee и от этого интерфейса реализовывать всех сотрудников.
     */
    public Worker fire(Worker worker) {
        return null;
    }
}
