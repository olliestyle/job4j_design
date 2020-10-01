package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class AnalyzeTest {

    List<Analyze.User> previous = new ArrayList<>();
    List<Analyze.User> current = new ArrayList<>();

    @Test
    public void whenNoDifference() {
        previous.add(new Analyze.User(1, "Murat"));
        previous.add(new Analyze.User(2, "Pavel"));
        previous.add(new Analyze.User(3, "Egor"));
        current.add(new Analyze.User(1, "Murat"));
        current.add(new Analyze.User(2, "Pavel"));
        current.add(new Analyze.User(3, "Egor"));

        Analyze.Info info = Analyze.diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenDifferenceAdd() {
        previous.add(new Analyze.User(1, "Murat"));
        previous.add(new Analyze.User(2, "Pavel"));
        previous.add(new Analyze.User(3, "Egor"));
        current.add(new Analyze.User(1, "Murat"));
        current.add(new Analyze.User(2, "Pavel"));
        current.add(new Analyze.User(3, "Egor"));
        current.add(new Analyze.User(4, "Dima"));

        Analyze.Info info = Analyze.diff(previous, current);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenDifferenceChanged() {
        previous.add(new Analyze.User(1, "Murat"));
        previous.add(new Analyze.User(2, "Pavel"));
        previous.add(new Analyze.User(3, "Egor"));
        current.add(new Analyze.User(1, "Murat"));
        current.add(new Analyze.User(2, "Petr"));
        current.add(new Analyze.User(3, "Emelia"));

        Analyze.Info info = Analyze.diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(2));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenDifferenceDeleted() {
        previous.add(new Analyze.User(1, "Murat"));
        previous.add(new Analyze.User(2, "Pavel"));
        previous.add(new Analyze.User(3, "Egor"));
        current.add(new Analyze.User(3, "Egor"));

        Analyze.Info info = Analyze.diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(2));
    }

    @Test
    public void whenDifferenceAddedAndChanged() {
        previous.add(new Analyze.User(1, "Murat"));
        previous.add(new Analyze.User(2, "Pavel"));
        previous.add(new Analyze.User(3, "Egor"));
        current.add(new Analyze.User(1, "Murat"));
        current.add(new Analyze.User(2, "Petr"));
        current.add(new Analyze.User(3, "Emelia"));
        current.add(new Analyze.User(5, "Semen"));

        Analyze.Info info = Analyze.diff(previous, current);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getChanged(), is(2));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenDifferenceAddedAndDeleted() {
        previous.add(new Analyze.User(1, "Murat"));
        previous.add(new Analyze.User(2, "Pavel"));
        previous.add(new Analyze.User(3, "Egor"));
        current.add(new Analyze.User(2, "Pavel"));
        current.add(new Analyze.User(3, "Egor"));
        current.add(new Analyze.User(5, "Semen"));

        Analyze.Info info = Analyze.diff(previous, current);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(1));
    }

    @Test
    public void whenDifferenceChangedAndDeleted() {
        previous.add(new Analyze.User(1, "Murat"));
        previous.add(new Analyze.User(2, "Pavel"));
        previous.add(new Analyze.User(3, "Egor"));
        current.add(new Analyze.User(1, "Murat"));
        current.add(new Analyze.User(3, "Emelia"));

        Analyze.Info info = Analyze.diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getDeleted(), is(1));
    }

    @Test
    public void whenDifferenceAddedAndChangedAndDeleted() {
        previous.add(new Analyze.User(1, "Murat"));
        previous.add(new Analyze.User(2, "Pavel"));
        previous.add(new Analyze.User(3, "Egor"));
        current.add(new Analyze.User(1, "Murat"));
        current.add(new Analyze.User(3, "Emelia"));
        current.add(new Analyze.User(5, "Semen"));

        Analyze.Info info = Analyze.diff(previous, current);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getDeleted(), is(1));
    }
}