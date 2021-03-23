package ru.job4j;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {

    private MaxMin maxMin = new MaxMin();

    @Test
    public void whenMax() {
        int actual = maxMin.max(List.of(10, 227, 5, 61, 9), (el, el2) -> el < el2 ? -1 : el > el2 ? 1 : 0);
        assertEquals(227, actual);
    }

    @Test
    public void whenMin() {
        String actual = maxMin.min(List.of("anna", "oleg", "alma", "egor", "aaa"), String::compareTo);
        assertEquals("aaa", actual);
    }
}