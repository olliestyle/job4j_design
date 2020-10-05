package ru.job4j.io;

import java.io.*;

public class ResultFile {
    public static void main(String[] args) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream("result.txt")))) {
            pw.write("Hello, world");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
