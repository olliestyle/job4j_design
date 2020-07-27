package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MemStoreTest {

    MemStore<User> memStoreTest;

    @Before
    public void init() {
        memStoreTest = new MemStore<>();
        memStoreTest.add(new User("12", "Ivan"));
        memStoreTest.add(new User("34", "Petr"));
        memStoreTest.add(new User("56", "Gleb"));
    }

    @Test
    public void add() {
        MemStore<User> mustBe = new MemStore<>();
        mustBe.add(new User("12", "Ivan"));
        mustBe.add(new User("34", "Petr"));
        mustBe.add(new User("56", "Gleb"));
        mustBe.add(new User("78", "Vlad"));

        memStoreTest.add(new User("78", "Vlad"));
        assertThat(memStoreTest, is(mustBe));
    }

    @Test
    public void replace() {
        MemStore<User> mustBe = new MemStore<>();
        mustBe.add(new User("12", "Ivan"));
        mustBe.add(new User("78", "Vlad"));
        mustBe.add(new User("56", "Gleb"));

        memStoreTest.replace("34", new User("78", "Vlad"));

        assertThat(memStoreTest, is(mustBe));


    }

    @Test
    public void delete() {
        MemStore<User> mustBe = new MemStore<>();
        mustBe.add(new User("12", "Ivan"));

        memStoreTest.delete("56");
        memStoreTest.delete("34");

        assertThat(memStoreTest, is(mustBe));
    }

    @Test
    public void findById() {
        assertThat(new User("34", "Petr"), is(memStoreTest.findById("34")));
    }
}