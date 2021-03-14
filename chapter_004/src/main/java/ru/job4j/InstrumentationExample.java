package ru.job4j;

public class InstrumentationExample {
    public static void printObjSize(Object object) {
        System.out.println("Object type: " + object.getClass() + ", size: "
                + InstrumentationAgent.getObjectSize(object) + " bytes");
    }

    public static void main(String[] args) {
        String emptyString = "";
        printObjSize(emptyString);
    }
}
