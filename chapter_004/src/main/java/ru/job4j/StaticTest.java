package ru.job4j;

import java.util.ArrayList;
import java.util.List;

public class StaticTest {
    public static List<Double> list = new ArrayList<>();

    public void populateList() {
        for (int i = 0; i < 10_000_000; i++) {
            list.add(Math.random());
        }
        System.out.println("debug point 2");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Debug Point 1");
        StaticTest staticTest = new StaticTest();
        staticTest.populateList();
        list = null;
        System.out.println("Debug Point 3");
//        Thread.sleep(10000);
//        System.gc();
        while (true) {

        }
    }
}
