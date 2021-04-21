package tictactoe;

public class Board implements BoardDrawer {
    private int size;

    public Board(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void draw(Cell[][] cells) {
        for (int i = 0; i < size; i++) {
            System.out.println();
            for (int j = 0; j < size; j++) {
                System.out.print(cells[i][j].getMark() + "  ");
            }
        }
        System.out.println();
    }
}
