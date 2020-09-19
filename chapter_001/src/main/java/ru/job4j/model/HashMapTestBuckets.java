package ru.job4j.model;

import java.lang.reflect.Field;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashMapTestBuckets {

    public static void main(String[] args) throws Exception {
        //Test data
        HashMap<User, String> map = new HashMap<User, String>();
        User user1 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
        User user2 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
        User user3 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
        User user4 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
        User user5 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
        User user6 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
        User user7 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
        User user8 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
        User user9 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
        User user10 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
        User user11 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
        User user12 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
        User user13 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
        User user14 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
        map.put(user1, "abc");
        map.put(user2, "def");
        map.put(user3, "yop");
        map.put(user4, "yop");
        map.put(user5, "yop");
        map.put(user6, "yop");
        map.put(user7, "yop");
        map.put(user8, "yop");
        map.put(user9, "yop");
        map.put(user10, "yop");
        map.put(user11, "yop");
        map.put(user12, "yop");
        map.put(null, "hello");
//        map.put(user13, "yop");
//        map.put(user14, "yop");

        //Access to the internal table
        Class clazz = map.getClass();
        Field table = clazz.getDeclaredField("table");
        table.setAccessible(true);
        Map.Entry<Integer, String>[] realTable = (Map.Entry<Integer, String>[]) table.get(map);

        //Iterate and do pretty printing
        for (int i = 0; i < realTable.length; i++) {
            System.out.println(String.format("Bucket : %d, Entry: %s", i, bucketToString(realTable[i])));
        }
    }

    private static String bucketToString(Map.Entry<Integer, String> entry) throws Exception {
        if (entry == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();

        //Access to the "next" filed of HashMap$Node
        Class clazz = entry.getClass();
        Field next = clazz.getDeclaredField("next");
        next.setAccessible(true);

        //going through the bucket
        while (entry != null) {
            sb.append(entry);
            entry = (Map.Entry<Integer, String>) next.get(entry);
            if (null != entry) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }
}
