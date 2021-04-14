package ru.job4j.solid.lsp.parking;

public class Car implements Vehicle {

    public final static int size = 1;

    public Car() {

    }

    @Override
    public int size() {
        return size;
    }
}
