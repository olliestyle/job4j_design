package ru.job4j;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SoftDemo {
    public static void main(String[] args) throws InterruptedException {
//        example1();
//        example2();
//        example3();
        example4();
    }

    private static void example1() {
        Object strong = new Object();
        SoftReference<Object> soft = new SoftReference<>(strong);
        strong = null;
        System.out.println(soft.get());
    }

    private static void example2() {
        List<SoftReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100_000_000; i++) {
            objects.add(new SoftReference<Object>(new Object() {
                String value = String.valueOf(System.currentTimeMillis());

                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!");
                }
            }));
        }
        System.gc();
        int liveObject = 0;
        for (SoftReference<Object> ref : objects) {
            Object object = ref.get();
            if (object != null) {
                liveObject++;
            }
        }
        System.out.println(liveObject);
    }

    private static void example3() throws InterruptedException {
        // VMOptions -Xms400m -Xmx400m
        Thread.sleep(5000);
        List<Integer> integerList = new ArrayList<>();
        SoftReference<List<Integer>> listSoftReference = new SoftReference<>(integerList);
        for (int i = 0; i < 10_000_000; i++) {
            integerList.add(i);
        }
        integerList = null;
        // integerList now eligible for GC, but GC will not collect it,
        // because listSoftReference has a link (aggregate) integerList
        List<Integer> integerList2 = new ArrayList<>();
        try {
            for (int i = 0; i < 10_000_000; i++) {
                List<Integer> innerList = listSoftReference.get(); // here we can get null at some point, when JVM will run out of memory and GC will collect integerList
                innerList.get(new Random().nextInt(10_000_000)); // here we can get NPE, when GC collect integerList, which now eligible for GC and JVM run out of memory
                integerList2.add(i);
            }
        } catch (NullPointerException npe) {
            System.out.println("NPE CATCH");
        }
        System.out.println(integerList2.size());
        System.out.println(listSoftReference); // Exist
        System.out.println(listSoftReference.get()); // Allready null
        while (true) {

        }
    }

    private static void example4() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        SoftReference<Object> soft = new SoftReference<>(object, queue);
        object = null;

        System.gc();

        TimeUnit.SECONDS.sleep(3);
        System.out.println("from link " + soft.get()); // сама ссылка еще существует
        System.out.println("from queue " + queue.poll()); // тут естественно null, т.к. ссылка еще не попала в ReferenceQueue
    }

}
