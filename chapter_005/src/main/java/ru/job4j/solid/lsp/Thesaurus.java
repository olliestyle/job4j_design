package ru.job4j.solid.lsp;

import java.util.ArrayList;
import java.util.List;

public class Thesaurus {
    protected String language;
    protected List<Word> thesaurus = new ArrayList<>();

    public Thesaurus(String language) {
        this.language = language;
    }

    private boolean validate(Word word) {
        boolean res = false;
        if (this.language.equals(word.getLanguage())) {
            res = true;
        }
        return res;
    }

    public void insertWord(Word word) {
        if (validate(word)) {
            thesaurus.add(word);
        }
    }
}

class RussianThesaurus extends Thesaurus {

    public RussianThesaurus(String language) {
        super(language);
    }

    public void insertWord(Word word) {
        thesaurus.add(word);
    }
}

class Word {
    private String language;
    private String word;

    public Word(String language, String word) {
        this.language = language;
        this.word = word;
    }

    public String getLanguage() {
        return this.language;
    }
}

class ThirdRuleExample {

    public static void main(String[] args) {
        Thesaurus thesaurus = new RussianThesaurus("ru");
        Word word = new Word("eng", "hello");
        thesaurus.insertWord(word);
    }
}
