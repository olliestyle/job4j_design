package tictactoe;

public class Cell {
    private int row;
    private int column;
    private String mark = "1";

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return this.mark;
    }

    public boolean isOccupied() {
        return mark != "1";
    }
}
