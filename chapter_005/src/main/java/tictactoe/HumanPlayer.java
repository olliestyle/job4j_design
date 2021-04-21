package tictactoe;

import java.util.Scanner;

public class HumanPlayer implements Player{

    private String mark;

    public HumanPlayer(String mark) {
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
    public void makeMove(Scanner scanner, Logic logic) {
        Cell cell = null;
        boolean flag = false;
        do {
            System.out.println("В какую строку игрок хочет поставить " + this.mark + " ?");
            int row = scanner.nextInt();
            System.out.println("В какую колонку игрок хочет поставить " + this.mark + " ?");
            int column = scanner.nextInt();
            if (fitBorders(row, column, logic.getCells().length)) {
                cell = logic.getCells()[row][column];
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
