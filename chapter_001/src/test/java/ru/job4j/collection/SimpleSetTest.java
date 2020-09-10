package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class SimpleSetTest {

    SimpleSet<Integer> simpleSet = new SimpleSet<>();

    @Before
    public void init() {
        simpleSet.add(1);
        simpleSet.add(2);
        simpleSet.add(2);
        simpleSet.add(3);
        simpleSet.add(3);
        simpleSet.add(4);
        simpleSet.add(5);
        simpleSet.add(6);
        simpleSet.add(6);
    }

    @Test
    public void whenAddThreeEqualsObject() {
        String res = simpleSet.toString();
        assertThat(res, is("1 2 3 4 5 6 "));
    }

    @Test
    public void whenSetIterator() {
        Iterator<Integer> it = simpleSet.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
    }

}