package ru.job4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Pop {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        int[] arr = new int[10];
        System.out.println(arr[3]);
        List<Integer> integerList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        Class clazz = integerList.getClass();
        Field field = clazz.getDeclaredField("elementData"); // getDeclaredField to access private field
        field.setAccessible(true); // without this string IAE
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        stringList.add("a");
        stringList.add("b");
        Object[] objectsInteger = (Object[]) field.get(integerList); // get field from specific object, and cast it
        System.out.println(objectsInteger.length);
        System.out.println(integerList.size());
        Object[] objectsString = (Object[]) field.get(stringList); // get field from another specific object, and cast it
        System.out.println(objectsString.length);
        System.out.println(stringList.size());
        Thread.sleep(3000);
        System.out.println("hello");
    }
}
