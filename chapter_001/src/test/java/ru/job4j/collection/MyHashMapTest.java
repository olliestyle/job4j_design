package ru.job4j.collection;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MyHashMapTest {

    private MyHashMap<Integer, String> myHashMap = new MyHashMap<>(16);

    @Test
    public void whenInsertIsGood() {

        myHashMap.insert(3, "abc");
        myHashMap.insert(10, "def");
        myHashMap.insert(15, "ghi");
        myHashMap.insert(19, "zxc");
        myHashMap.insert(10, "new");
        assertThat(myHashMap.get(10), is("new"));
        assertNull(myHashMap.get(28));
    }

    @Test
    public void whenInsertIsFalse() {
        myHashMap.insert(0, "abc");
        myHashMap.insert(10912378, "def");
        myHashMap.insert(3, "ghi"); // index bucket is 3
        myHashMap.insert(1981293, "jkl"); // 1981293 - index bucket is 3
        assertFalse(myHashMap.insert(1981293, "jkl"));
        assertThat(myHashMap.get(3), is("ghi"));
    }

    @Test
    public void whenDeleteIsGood() {
        myHashMap.insert(0, "abc");
        myHashMap.insert(10912378, "def");
        myHashMap.insert(3, "ghi"); // index bucket is 3
        assertFalse(myHashMap.delete(18)); // index bucket is 3
        assertTrue(myHashMap.delete(3));
        assertThat(myHashMap.getCurrentSize(), is(2));
    }

    @Test
    public void whenChangeCapacityAndSize() {
        myHashMap.insert(18923789, "a");
        myHashMap.insert(1981293, "b");
        myHashMap.insert(2182379, "c");
        myHashMap.insert(31928309, "d");
        myHashMap.insert(1, "e");
        myHashMap.insert(59120, "f");
        myHashMap.insert(61827639, "g");
        myHashMap.insert(71829379, "h");
        myHashMap.insert(891872839, "i");
        myHashMap.insert(12, "j");
        myHashMap.insert(9, "k");
        myHashMap.insert(11910283, "l");
        myHashMap.insert(5, "m");
        myHashMap.insert(6, "m");
        assertThat(myHashMap.getCurrentCapacity(), is(32));
    }

    @Test
    public void whenIteratorNextIsGood() {
        for (int i = 0; i < 10; i++) {
            myHashMap.insert(i, "zzz");
        }
        Iterator<MyHashMap.Node> it = myHashMap.iterator();
        for (int i = 0; i < 10; i++) {
            assertThat(it.next().getValue(), is("zzz"));
        }
    }

    @Test
    public void whenIteratorDiffCellIsGood() {
        myHashMap.insert(0, "aaa");
        myHashMap.insert(20, "bbb");
        myHashMap.insert(23, "ccc");
        myHashMap.insert(28, "ddd");
        myHashMap.insert(31, "eee");
        Iterator<MyHashMap.Node> it = myHashMap.iterator();
        assertThat(it.next().getValue(), is("aaa"));
        assertThat(it.next().getValue(), is("bbb"));
        assertThat(it.next().getValue(), is("ccc"));
        assertThat(it.next().getValue(), is("ddd"));
        assertThat(it.next().getValue(), is("eee"));
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNextException() {
        myHashMap.insert(0, "aaa");
        myHashMap.insert(20, "bbb");
        myHashMap.insert(23, "ccc");
        Iterator<MyHashMap.Node> it = myHashMap.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorModException() {
        myHashMap.insert(0, "aaa");
        myHashMap.insert(20, "bbb");
        myHashMap.insert(23, "ccc");
        Iterator<MyHashMap.Node> it = myHashMap.iterator();
        myHashMap.delete(20);
        it.next();
    }

}