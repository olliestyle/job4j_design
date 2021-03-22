package ru.job4j.softstore;

import java.util.Scanner;
import java.util.StringJoiner;

public class SoftStorageUsage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в хранилище текста. Введите имя файла, текст которого Вы хотите получить.");
        SoftStorage softStorage = new SoftStorage();
        String condition = scanner.next();
        while(!condition.equals("exit")) {
            StringJoiner text = softStorage.get(condition);
            System.out.println("Содержимое файла:");
            System.out.println(text);
            System.out.println("_______________________________________________________________________________________________");
            System.out.println("Для выхода введите 'exit', для продолжения введите имя файла, текст которого Вы хотите получить.");
            condition = scanner.next();
        }
    }
}
