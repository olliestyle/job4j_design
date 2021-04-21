package tictactoe;

public class Logic {
    private Cell[][] cells;

    public Logic(int size) {
        cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean isWin() {
        return true;
    }

    public Cell[][] getCells() {
        return this.cells;
    }
}
