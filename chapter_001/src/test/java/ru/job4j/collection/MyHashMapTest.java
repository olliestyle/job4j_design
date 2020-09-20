package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class MyHashMapTest {

    private MyHashMap<Integer, String> myHashMap = new MyHashMap<>(16);

    @Test
    public void whenInsertIsGood() {

        myHashMap.insert(0, "abc");
        myHashMap.insert(1, "def");
        myHashMap.insert(2, "ghi");
        assertThat(myHashMap.get(2), is("ghi"));
    }

    @Test
    public void whenInsertIsFalse() {
        myHashMap.insert(0, "abc");
        myHashMap.insert(1, "def");
        myHashMap.insert(2, "ghi");
        myHashMap.insert(2, "jkl");
        assertFalse(myHashMap.insert(2, "jkl"));
        assertThat(myHashMap.get(2), is("ghi"));
    }

    @Test
    public void whenChangeCapacityAndSize() {
        for (int i = 0; i < 50; i++) {
            myHashMap.insert(i, "zzz");
        }
        assertThat(myHashMap.getCurrentSize(), is(50));
        assertThat(myHashMap.getCurrentCapacity(), is(128));
    }

    @Test
    public void whenIteratorNextIsGood() {
        for (int i = 0; i < 10; i++) {
            myHashMap.insert(i, "zzz");
        }
        Iterator<String> it = myHashMap.iterator();
        for (int i = 0; i < 10; i++) {
            assertThat(it.next(), is("zzz"));
        }
    }

    @Test
    public void whenIteratorDiffCellIsGood() {
        myHashMap.insert(0, "aaa");
        myHashMap.insert(20, "bbb");
        myHashMap.insert(23, "ccc");
        myHashMap.insert(28, "ddd");
        myHashMap.insert(31, "eee");
        Iterator<String> it = myHashMap.iterator();
        assertThat(it.next(), is("aaa"));
        assertThat(it.next(), is("bbb"));
        assertThat(it.next(), is("ccc"));
        assertThat(it.next(), is("ddd"));
        assertThat(it.next(), is("eee"));
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNextException() {
        myHashMap.insert(0, "aaa");
        myHashMap.insert(20, "bbb");
        myHashMap.insert(23, "ccc");
        Iterator<String> it = myHashMap.iterator();
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
        Iterator<String> it = myHashMap.iterator();
        myHashMap.delete(20);
        it.next();
    }

}