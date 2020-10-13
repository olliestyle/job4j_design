package ru.job4j.io;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnalyseTest {

    List<String> list = new ArrayList<>();

    @Test
    public void whenLog() {
        Analyse analyse = new Analyse();
        analyse.unavailable("./data/server1.log", "./data/unavailable1.csv");
        try (BufferedReader in = new BufferedReader(new FileReader("./data/unavailable1.csv"))) {
            in.lines().forEach(list::add);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(list.get(0), is("10:58:01;10:59:01"));
        assertThat(list.get(1), is("11:01:02;11:02:02"));
    }

    @Test
    public void whenLog2() {
        Analyse analyse = new Analyse();
        analyse.unavailable("./data/server2.log", "./data/unavailable1.csv");
        try (BufferedReader in = new BufferedReader(new FileReader("./data/unavailable1.csv"))) {
            in.lines().forEach(list::add);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(list.get(0), is("10:58:01;10:59:01"));
        assertThat(list.get(1), is("11:01:02;11:02:02"));
        assertThat(list.get(2), is("12:15:15;13:00:00"));
    }
}