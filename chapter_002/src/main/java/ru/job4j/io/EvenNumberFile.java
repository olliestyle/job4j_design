package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
        StringBuilder text = new StringBuilder();
        int read;
        while ((read = in.read()) != -1) {
            text.append((char) read);
            }
        String[] numbers = text.toString().split(System.lineSeparator());
        text = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            Integer temp = Integer.valueOf(numbers[i]);
            if (temp % 2 == 0) {
                text.append(temp + " ");
            }
        }
        System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
