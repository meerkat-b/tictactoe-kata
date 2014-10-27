package com.codurance.gameEngine;

import com.codurance.IO.Console;
import org.apache.commons.lang.NotImplementedException;

public class Board {
	public static final int ROW_SIZE = 3;
	private final int EMPTY = 0;
	private final int X = 1;
	private final int O = 2;
	private Console console;
	private int[] board;


	public Board(Console console) {
		this.console = console;
		board = new int[9];
	}

	public boolean isInPlay() {
		throw new NotImplementedException();
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
			if (position % ROW_SIZE == 2) {
				stringBuilder.append("\n");
			}
		}
		console.print(stringBuilder.toString());
	}

	public void declareWinner() {
	}

	public void mark(int position, int marker) {
		board[position-1]=marker;
	}
}
