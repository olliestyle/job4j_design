package tictactoe;

public enum Mark {
    MARK1("1"), MARKO("O"), MARKX("X");

    String mark;

    Mark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }
}
