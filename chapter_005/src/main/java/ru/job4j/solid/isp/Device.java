package ru.job4j.solid.isp;

/**
 * При больших интерфейсах программа становится сильносвязной.
 * Т.к. при изменении интерфейса придется внести изменения в реализации.
 * Пример, попробуем спроектировать интерфейс устройства, вот что получим.
 * Обратите внимание, что у сервера нет устройств ввода/вывода. Но ему приходиться поддерживать
 * интерфейс Device.
 */

public interface Device {
    void in(String data);
    void calculate();
    void output();
}

class Computer implements Device {

    private String buffer;

    @Override
    public void in(String data) {
        this.buffer = data;
    }

    @Override
    public void calculate() {
        this.buffer = "Calc by computer: " + buffer;
    }

    @Override
    public void output() {
        System.out.println(buffer);
    }

}

class Server implements Device {

    @Override
    public void in(String data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void calculate() {
        System.out.println("Do some work!");
    }

    @Override
    public void output() {
        throw new UnsupportedOperationException();
    }
}

class TV implements Device {

    private String command;

    @Override
    public void in(String data) {
        this.command = data;
    }

    @Override
    public void calculate() {
        System.out.println("Execute: " + command);
    }

    @Override
    public void output() {
        System.out.println("Show TV program");
    }
}

/**
 А что если нам нужно добавить метод подключения к интернету connect().
 Во-первых, нам придется реализовать его в существующих классах
 Во-вторых, опять может возникнуть ситуация, что не все устройства поддерживают подключение к интернету
 Какое здесь решение?
 Разделение интерфейсов, причем нам нужно подписать под нужные интерфейсы только нужные реализации.
 Например, для сервера только Calculator, Internet
 */

interface Input {
    void in(String data);
}

interface Output {
    void output();
}

interface Calculator {
    void calculate();
}

interface Internet {
    void connect();
}

class GoodServer implements Calculator, Internet {

    @Override
    public void calculate() {
        System.out.println("Do work!");
    }

    @Override
    public void connect() {
        System.out.println("Try connect ...");
    }
}