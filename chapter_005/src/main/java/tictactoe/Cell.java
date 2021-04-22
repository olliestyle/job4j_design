package tictactoe;

public class Cell {
    private int row;
    private int column;
    private Mark mark = Mark.MARK1;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Mark getMark() {
        return this.mark;
    }

    public boolean isOccupied() {
        return mark != Mark.MARK1;
    }
}
