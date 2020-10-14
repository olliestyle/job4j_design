package ru.job4j.io;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnalyseTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    List<String> list = new ArrayList<>();

    @Test
    public void whenLog() throws IOException {
        File source = folder.newFile("source.log");
        File target = folder.newFile("target.csv");
        Analyse analyse = new Analyse();
        String s;
        try (PrintWriter out = new PrintWriter(source);
        BufferedReader in = new BufferedReader(new FileReader("./data/server1.log"))) {
            while ((s = in.readLine()) != null) {
                out.write(s + "\n");
            }
        }
        analyse.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
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
    public void whenLog2() throws IOException {
        File source = folder.newFile("source.log");
        File target = folder.newFile("target.csv");
        Analyse analyse = new Analyse();
        String s;
        try (PrintWriter out = new PrintWriter(source);
             BufferedReader in = new BufferedReader(new FileReader("./data/server2.log"))) {
            while ((s = in.readLine()) != null) {
                out.write(s + "\n");
            }
        }
        analyse.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
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