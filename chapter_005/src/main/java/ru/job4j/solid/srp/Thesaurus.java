package ru.job4j.solid.srp;

public interface Thesaurus {
    String interpretation(String word);
    void addWord(); // нарушает принцип, словарь не должен знать, как добавлять новые слова
}
