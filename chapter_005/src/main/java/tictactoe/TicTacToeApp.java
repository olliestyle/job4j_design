package tictactoe;

import java.util.Scanner;

public class TicTacToeApp {
    public static void main(String[] args) {
        TicTacToeApp ticTacToeApp = new TicTacToeApp();
        System.out.println("Добро пожаловать в игру Крестики-Нолики");
        System.out.println("Игру начинают крестики");
        System.out.println("Обратите внимание, что верхний левый угол имеет координаты [0, 0]");
        System.out.print ("Установите размер поля - ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        ticTacToeApp.init(new Board(size),
                          new Logic(size),
                          new HumanPlayer[]{new HumanPlayer("X"), new HumanPlayer("O")},
                          scanner);
    }

    public void init(BoardDrawer boardDrawer, Logic logic, Player[] players, Scanner scanner) {
        boolean continueGame = true;
        System.out.println("");
        while (continueGame) {
            for (Player player : players) {
                player.makeMove(scanner, logic);
                boardDrawer.draw(logic.getCells());
                continueGame = logic.isWin();
            }
        }
    }
}
