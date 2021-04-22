package tictactoe;

public class ConsoleBoardDrawer implements BoardDrawer {

    private int size;

    public ConsoleBoardDrawer(int size) {
        this.size = size;
    }

    @Override
    public void draw(Board board) {
        for (int i = 0; i < size; i++) {
            System.out.println();
            for (int j = 0; j < size; j++) {
                System.out.print(board.getCells()[i][j].getMark().getMark() + "  ");
            }
        }
        System.out.println();
    }
}
