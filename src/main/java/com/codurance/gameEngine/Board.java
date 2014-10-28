package com.codurance.gameEngine;

import com.codurance.IO.Console;

public class Board {
	private final int OFFSET_ONE = 1;
	private final int THREE_Xs = 3;
	private final int THREE_Os = 30;
	private final int X = 1;
	private final int O = 10;
	private final int ROW_SIZE = 3;
	private final int EMPTY = 0;
	private Console console;
	private int[] board;

	public Board(Console console) {
		this.console = console;
		board = new int[9];
	}

	public void mark(int position, int marker) {
		board[position-OFFSET_ONE]=marker;
	}

	public void print() {
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
		console.print(stringBuilder.toString());
	}

	public boolean isInPlay() {
		return hasNoWinner() && hasSpacesRemaining();
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
				== THREE_Xs;
	}

	private boolean O_hasSatisfied(WinCondition condition) {
		return board[condition.pos1] + board[condition.pos2] + board[condition.pos3]
				== THREE_Os;
	}

	private boolean hasNoWinner() {
		return !hasWinner();
	}

	private boolean isEndOfRow(int position) {
		return position % ROW_SIZE == 2;
	}

	private boolean hasSpacesRemaining() {
		for (int position = 0; position < board.length; position++) {
			if(board[position]==0) {
				return true;
			}
		}
		return false;
	}

	public void declareWinner() {

	}
}
