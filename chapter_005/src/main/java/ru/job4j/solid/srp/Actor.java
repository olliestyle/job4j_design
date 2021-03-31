package ru.job4j.solid.srp;

public interface Actor {
    void playInTheatre();
    void playInMovie();
    void searchAuditions(); // нарушает принци, актер не должен заниматься поиском проб
}
