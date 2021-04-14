package ru.job4j.solid.lsp.parking;

public class Truck implements Vehicle {

    private int size;

    public Truck(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException();
        }
        this.size = size;
    }

    @Override
    public int size() {
        return this.size;
    }
}
