package com.codurance.gameEngine;

import com.codurance.IO.Console;

public class BoardPrinter {
	private final int EMPTY = 0;
	private final int X = 1;
	private final int O = 10;
	private final int ROW_SIZE = 3;
	private final int OFFSET = 1;
	private Console console;
	private StringBuilder stringBuilder;

	public BoardPrinter(Console console) {
		this.console = console;
		stringBuilder = new StringBuilder();
	}

	public void print(int[] board) {
		stringBuilder.append("\n");

		for (int position = 0; position < board.length; position++) {
			if (board[position] == EMPTY) {
				stringBuilder.append(" -");
			} else if (board[position] == X) {
				stringBuilder.append(" x");
			} else if (board[position] == O) {
				stringBuilder.append(" o");
			}
			if (isEndOfRow(position)) {
				stringBuilder.append("\n");
			}
		}
		stringBuilder.append("\n");
		console.print(stringBuilder.toString());
	}

	private boolean isEndOfRow(int position) {
		return position % ROW_SIZE == 2;
	}

	public void printRemainingSpacesOf(int[] board) {
		stringBuilder.append("Remaining spaces : ");

		for (int position = 0; position < board.length; position++) {
			if(board[position]==EMPTY) {
				stringBuilder.append("[" + (position+OFFSET) + "] ");
			}
		}

		stringBuilder.append("\n");
		console.print(stringBuilder.toString());
	}
}