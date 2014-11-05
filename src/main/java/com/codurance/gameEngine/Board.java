package com.codurance.gameEngine;

import com.codurance.IO.Console;

import java.util.ArrayList;

public class Board {
	private final int OFFSET = 1;

	private final int X = 1;
	private final int O = 10;
	private final int EMPTY = 0;
	private Console console;
	private final BoardPrinter boardPrinter;


	private int[] board = new int[9];
	private int currentMarker = X;

	public Board(Console console) {
		this.console = console;
		this.boardPrinter = new BoardPrinter(console);
	}

	public void play(int position) {
		markBoardAt(position);
		switchMarker();
	}

	public boolean isInPlay() {
		return hasNoWinner() && hasSpacesRemaining();
	}

	public void printBoard() {
		boardPrinter.print(board);
	}

	public void printRemainingSpaces() {
		boardPrinter.printRemainingSpacesOf(board);

	}

	public ArrayList<Integer> remainingSpaces() {
		return new ArrayList<Integer>() {{
			for (int position = 0; position < board.length; position++) {
				if(board[position]==EMPTY) {
					add(position+OFFSET);
				}
			}
		}};
	}

	public boolean hasWinner() {
		for(WinCondition winCondition : WinCondition.values()) {
			if (isSatisfied(winCondition)) {
				return true;
			}
		}
		return false;
	}

	public void declareWinner() {
		boardPrinter.print(board);
		if (hasNoWinner()) {
			console.println("Tie Game! There is no Winner");
		} else  {
			for (WinCondition winCondition : WinCondition.values()) {
				if (X_hasSatisfied(winCondition)) {
					console.println("The winner is X!");
				} else if (O_hasSatisfied(winCondition)) {
					console.println("The winner is O!");
				}
			}
		}
	}

	public int[] state() {
		return board;
	}

	public int getCurrentMarker() {
		return currentMarker;
	}

	private boolean isSatisfied(WinCondition winCondition) {
		return X_hasSatisfied(winCondition) || O_hasSatisfied(winCondition);
	}

	private boolean X_hasSatisfied(WinCondition winCondition) {
		return board[winCondition.pos1] + board[winCondition.pos2] + board[winCondition.pos3]
				== winCondition.THREE_Xs;
	}

	private boolean O_hasSatisfied(WinCondition winCondition) {
		return board[winCondition.pos1] + board[winCondition.pos2] + board[winCondition.pos3]
				== winCondition.THREE_Os;
	}

	private boolean hasNoWinner() {
		return !hasWinner();
	}

	private boolean hasSpacesRemaining() {
		return !remainingSpaces().isEmpty();
	}

	private void markBoardAt(int position) {
		board[position-OFFSET]=currentMarker;
	}

	private void switchMarker() {
		currentMarker = (currentMarker == X) ? O : X;
	}
}
