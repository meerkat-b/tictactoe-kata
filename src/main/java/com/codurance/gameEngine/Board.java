package com.codurance.gameEngine;

import com.codurance.IO.Console;

public class Board {
	private final int ROW_SIZE = 3;
	private final int EMPTY = 0;
	private final int X = 1;
	private final int O = 10;
	private Console console;
	private int[] board;

	public Board(Console console) {
		this.console = console;
		board = new int[9];
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
		return false;
	}

	public void mark(int position, int marker) {
		board[position-1]=marker;
	}

	public void declareWinner() {
		for (WinCondition condition : WinCondition.values()) {
			if (board[condition.p1] + board[condition.p2] + board[condition.p3] == 30) {
				console.println("O Wins!");
			}
			if (board[condition.p1] + board[condition.p2] + board[condition.p3] == 3) {
				console.println("X Wins!");
			}
		}
	}

	private boolean isEndOfRow(int position) {
		return position % ROW_SIZE == 2;
	}

}
