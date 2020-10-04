package ru.job4j.io;

import java.io.*;

public class ResultFile {
    public static void main(String[] args) throws IOException {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write("1*2=2\n".getBytes());
            out.write("1*3=3\n".getBytes());
            out.write("1*4=4\n".getBytes());
            out.write("1*5=5\n".getBytes());
            out.write("1*6=6\n".getBytes());
            out.write("1*7=7\n".getBytes());
            out.write("1*8=8\n".getBytes());
            out.write("1*9=9".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
