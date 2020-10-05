package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader bfr = new BufferedReader(new FileReader("log.txt"))) {
        result = bfr.lines().filter(s -> s.contains("404")).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String s: log) {
                pw.write(s + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String s: log) {
            System.out.println(s);
        }
        save(log, "404.txt");
    }
}
