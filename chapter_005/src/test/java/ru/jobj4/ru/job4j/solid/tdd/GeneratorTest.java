package ru.jobj4.ru.job4j.solid.tdd;

import org.junit.Test;

import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void whenProduceIsGood() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("Pavel", "you");
        assertEquals("I am a Pavel, Who are you?", new SimpleGenerator().produce(template, map));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMapContainsExtraKeys() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("Pavel", "you");
        map.put("Egor", "Oleg");
        new SimpleGenerator().produce(template, map);
    }

    @Test(expected = IllegalFormatException.class)
    public void whenTemplateContainsExtraKeys() {
        String template = "I am a ${name} ${surname}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("Pavel", "you");
        new SimpleGenerator().produce(template, map);
    }
}