package ru.job4j.solid.srp;

public interface WristWatch {
    String showTime();
    void changeStrap(); // нарушает принцип, часы не должны знать о замене ремешка
}
