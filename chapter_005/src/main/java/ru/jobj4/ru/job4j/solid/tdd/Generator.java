package ru.jobj4.ru.job4j.solid.tdd;

import java.util.Map;

public interface Generator {
    String produce(String template, Map<String, String> args);
}
