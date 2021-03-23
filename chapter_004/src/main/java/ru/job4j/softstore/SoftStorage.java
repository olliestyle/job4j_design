package ru.job4j.softstore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class SoftStorage {

    private Map<String, SoftReference<StringJoiner>> softStorage = new HashMap<>();

    public SoftStorage() {

    }

    private StringJoiner put(String fileName) {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String s = "";
            while ((s = br.readLine()) != null) {
                stringJoiner.add(s);
            }
            softStorage.put(fileName, new SoftReference<>(stringJoiner));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringJoiner;
    }

    public StringJoiner get(String fileName) {
        if (!softStorage.containsKey(fileName)) {
            put(fileName);
        }
        StringJoiner toReturn = softStorage.get(fileName).get();
        if (toReturn == null) {
            System.out.println("Текст файла был удален из кэша, загружаем снова");
            toReturn = put(fileName);
        }
        return toReturn;
    }
}
