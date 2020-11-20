package ru.job4j.io;

import java.io.*;
import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner("121231234ansjkd712123412343asndj81234123123amskld 123  32 12 321");
        scanner.useDelimiter("[a-z]+");
        System.out.println(scanner.findInLine("ansj"));
        System.out.println(scanner.next());
        System.out.println(scanner.next());
        System.out.println(scanner.next());
        scanner.reset();
        System.out.println(scanner.next());
        System.out.println(scanner.next());
        System.out.println(scanner.next());
        FileInputStream fos = new FileInputStream("data.txt");
        Scanner scanner1 = new Scanner(fos);
        System.out.println(scanner1.next());
        System.out.println(scanner1.next());
        System.out.println(scanner1.next());
        System.out.println(scanner1.next());
        scanner1.useDelimiter("[a-z]+");
        System.out.println(scanner1.next());
        System.out.println(scanner1.next());
        System.out.println(scanner1.next());
        System.out.println(scanner1.next());
        Scanner scanner2 = new Scanner(System.in);
        String s = scanner2.nextLine();
        System.out.println(s);
    }
}
