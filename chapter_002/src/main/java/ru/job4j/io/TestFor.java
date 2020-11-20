package ru.job4j.io;

import java.io.*;

public class TestFor {

    public static void main(String[] args) throws IOException {
        PrintStream out = System.out;
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("bos.txt"));
        PrintStream ps = new PrintStream(bos);
        System.setOut(ps);
        System.out.println("hello");
        System.out.println("hello1");
        System.out.println("hello2");
        ps.close();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("bos.txt"));
        System.setIn(bis);
        System.setOut(out);
        int b;
        while ((b = bis.read()) != -1) {
            System.out.print((char) b);
        }
    }
}
