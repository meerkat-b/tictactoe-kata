package com.codurance.gameEngine;

import com.codurance.IO.Console;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class Board {
	private final int OFFSET = 1;

	private final int X = 1;
	private final int O = 10;
	private final int ROW_SIZE = 3;
	private final int EMPTY = 0;
	private Console console;
	private int[] board = new int[9];
	private int currentMarker = X;

	public Board(Console console) {
		this.console = console;
	}

	public void play(int position) {
		markBoardAt(position);
		switchMarker();
	}

	public boolean isInPlay() {
		return hasNoWinner() && hasSpacesRemaining();
	}

	public void printBoardState() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n");

		for(int position = 0; position < board.length; position++) {
			if(board[position]==EMPTY)  {
				stringBuilder.append(" -");
			} else if (board[position]==X) {
				stringBuilder.append(" x");
			} else if (board[position]==O) {
				stringBuilder.append(" o");
			}
			if (isEndOfRow(position)) {
				stringBuilder.append("\n");
			}
		}
		stringBuilder.append("\n");
		console.print(stringBuilder.toString());
	}

	public void printRemainingSpaces() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Remaining spaces : ");
		for (int position : remainingSpaces()) {
			stringBuilder.append("[" + position + "] ");
		}
		stringBuilder.append("\n");
		console.print(stringBuilder.toString());
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

	private boolean isEndOfRow(int position) {
		return position % ROW_SIZE == 2;
	}

	private boolean hasSpacesRemaining() {
		return !remainingSpaces().isEmpty();
	}

	public void declareWinner() {
		throw new NotImplementedException();
	}

	private void markBoardAt(int position) {
		board[position-OFFSET]=currentMarker;
	}

	private void switchMarker() {
		currentMarker = (currentMarker == X) ? O : X;
	}

	public int[] state() {
		return board;
	}

	public int getCurrentMarker() {
		return currentMarker;
	}


}
