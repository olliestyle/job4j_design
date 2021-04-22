package tictactoe;

public class ConsolePlayer implements Player{

    private Mark mark;

    public ConsolePlayer(Mark mark) {
        this.mark = mark;
    }

    private boolean setMark(Cell cell) {
        boolean rsl = false;
        if (!cell.isOccupied()) {
            rsl = true;
            cell.setMark(this.mark);
        }
        return rsl;
    }

    @Override
    public void makeMove(Input input, Board board) {
        Cell cell = null;
        boolean flag = false;
        do {
            input.setMarkCoordinates(mark);
            int row = input.getRow();
            int column = input.getColumn();
            if (fitBorders(row, column, board.getCells().length)) {
                cell = board.getCells()[row][column];
                flag = setMark(cell);
                if (!flag) {
                    System.out.println("Клетка занята, попробуйте поставить в другую");
                }
            } else {
                System.out.println("Такой клетки не существует, попробуйте еще раз");
            }
        } while (!flag);
    }

    private boolean fitBorders(int row, int column, int border) {
        return row < border && column < border;
    }
}
