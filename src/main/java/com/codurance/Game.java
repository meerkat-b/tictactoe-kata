package com.codurance;

public class Game {

    public static final String HELLO = "Hello!";
    private static final String X = "x";
    private Console console;
    private boolean xMarked = false;
    private int[][] board = new int[2][2];

    public void start() {
        greetPlayer();
        printBoard();
    }

    private void printBoard() {
        console.print("\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j]==0) {
                    console.print("- ");
                } else if (board[i][j]==1) {
                    console.print("x ");
                } else if (board[i][j]==2) {
                    console.print("o ");
                }
            }
            console.print("\n");
        }
//        if (xMarked) {
//            console.print("\n" +
//                            "x - -\n" +
//                            "- - -\n" +
//                            "- - -\n"
//            );
//        } else {
//            console.print("\n" +
//                            "- - -\n" +
//                            "- - -\n" +
//                            "- - -\n"
//            );
//        }
    }

    private void greetPlayer() {
        console.println(HELLO);
    }

    public void makeMove(String mark, Coordinate coordinate) {
        xMarked = true;
    }
}
