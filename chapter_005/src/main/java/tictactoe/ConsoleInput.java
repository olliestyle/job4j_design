package tictactoe;

import java.util.Scanner;

public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);
    private int row;
    private int column;

    @Override
    public void setMarkCoordinates(Mark mark) {
        System.out.println("В какую строку игрок хочет поставить " + mark.getMark() + " ?");
        row = scanner.nextInt();
        System.out.println("В какую колонку игрок хочет поставить " + mark.getMark() + " ?");
        column = scanner.nextInt();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
