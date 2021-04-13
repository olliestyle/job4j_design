package ru.job4j.solid.lsp.parking;

public class Car implements Vehicle {

    public final static int size = 1;
    private String owner;

    public Car(String owner) {
        this.owner = owner;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String getOwner() {
        return null;
    }
}
