package com.codurance.gameEngine;

import com.codurance.IO.Console;
import org.apache.commons.lang.NotImplementedException;

public class Board {
	private final int TOP = 0, LEFT = 0, EMPTY = 0;
	private final int CENTER = 1, MIDDLE = 1, X = 1;
	private final int RIGHT = 2, BOTTOM = 2, O = 2;
	private Console console;
	private int[][] board;


	public Board(Console console) {
		this.console = console;
		board = new int[3][3];
	}

	public boolean isInPlay() {
		throw new NotImplementedException();
	}

	public void print() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n");

		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				if(board[row][column]==EMPTY)  {
					stringBuilder.append(" -");
				} else if (board[row][column]==X) {
					stringBuilder.append(" x");
				} else if (board[row][column]==O) {
					stringBuilder.append(" o");
				}
			}
			stringBuilder.append("\n");
		}
		console.print(stringBuilder.toString());
	}

	public void declareWinner() {
	}

	public void mark(int position, int marker) {
		switch(position) {
			case 1:
				board[TOP][LEFT]=marker;
				break;
			case 2:
				board[TOP][CENTER]=marker;
				break;
			case 3:
				board[TOP][RIGHT]=marker;
				break;
			case 4:
				board[MIDDLE][LEFT]=marker;
				break;
			case 5:
				board[MIDDLE][CENTER]=marker;
				break;
			case 6:
				board[MIDDLE][RIGHT]=marker;
				break;
			case 7:
				board[BOTTOM][LEFT]=marker;
				break;
			case 8:
				board[BOTTOM][CENTER]=marker;
				break;
			case 9:
				board[BOTTOM][RIGHT]=marker;
				break;
		}
	}
}
