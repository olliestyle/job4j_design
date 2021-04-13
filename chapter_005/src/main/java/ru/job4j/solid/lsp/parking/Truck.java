package ru.job4j.solid.lsp.parking;

public class Truck implements Vehicle {

    private int size;
    private String owner;

    public Truck(int size, String owner) {
        this.size = size;
        this.owner = owner;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String getOwner() {
        return null;
    }
}
