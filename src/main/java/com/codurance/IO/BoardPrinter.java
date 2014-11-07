package com.codurance.io;

import com.codurance.gameEngine.markers.Cross;
import com.codurance.gameEngine.markers.Marker;
import com.codurance.gameEngine.markers.Naught;

public class BoardPrinter {
	private final int EMPTY = 0;
	private final int OFFSET = 1;
	private final int ROW_SIZE = 3;
	private final Marker X = new Cross();
	private final Marker O = new Naught();
	private Console console;

	public BoardPrinter(Console console) {
		this.console = console;
	}

	public void print(int[] board) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n");

		for (int position = 0; position < board.length; position++) {
			if (board[position] == EMPTY) {
				stringBuilder.append(" -");
			} else if (board[position] == X.get()) {
				stringBuilder.append(" x");
			} else if (board[position] == O.get()) {
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
		StringBuilder stringBuilder = new StringBuilder();
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