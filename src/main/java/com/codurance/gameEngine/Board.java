package com.codurance.gameEngine;

import com.codurance.IO.Console;

public class Board {
	private final int OFFSET_ONE = 1;

	private final int X = 1;
	private final int O = 10;
	private final int ROW_SIZE = 3;
	private final int EMPTY = 0;
	private Console console;
	private int[] board;
	private int currentMarker = X;

	public Board(Console console) {
		this.console = console;
		board = new int[9];
	}

	public void play(int position) {
		markBoardAt(position);
		switchMarker();
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
		for (int position = 0; position < board.length; position++) {
			if(board[position]==0) {
				return true;
			}
		}
		return false;
	}

	public void declareWinner() {

	}

	private void markBoardAt(int position) {
		board[position-OFFSET_ONE]=currentMarker;
	}

	private void switchMarker() {
		currentMarker = (currentMarker == X) ? O : X;
	}

	public void printRemainingSpaces() {
		
	}
}
