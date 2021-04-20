package ru.job4j.solid.dip;

public class Travel {
    /**
     * Здесь нарушается DIP, т.к. используем только поезд для путешествия.
     * И нарушаем SRP, т.к. создаем объект в методе.
     * Чтобы не нарушать эти принципы, необходимо реализовать интерфейс Transport и
     * передавать его в класс Travel через конструктор
     */
    public void startTravel() {
        Train train = new Train();
    }
}
