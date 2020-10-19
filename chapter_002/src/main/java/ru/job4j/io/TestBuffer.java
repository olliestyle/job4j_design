package ru.job4j.io;

import java.io.*;

public class TestBuffer {
    public static void main(String[] args) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("input.txt"));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("output.hex"));
             OutputStreamWriter w = new OutputStreamWriter(out)) {

            for (int i = in.read(); i != -1; i = in.read()) {
                w.write(i);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
