package ru.job4j.solid.lsp;

public class Actor {

    protected int honorarium;

    protected Actor (int honorarium) {
        this.honorarium = honorarium;
    }

    public void play(int honorarium) {
        if (honorarium < this.honorarium + 1000) {
            throw new IllegalArgumentException("Need more money...");
        } else {
            System.out.println("playing...");
        }
    }
}

class CinemaActor extends Actor {

    protected CinemaActor(int honorarium) {
        super(honorarium);
    }
    // усиливаем предусловие
    public void play(int honorarium) {
        if (honorarium < this.honorarium + 5000) {
            throw new IllegalArgumentException("Need more money...");
        } else {
            System.out.println("playing...");
        }
    }
}

class FirstRuleExample {
    public static void main(String[] args) {
        Actor actor = new CinemaActor(999);
        actor.play(5998);
    }
}