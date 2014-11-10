package com.codurance.gameEngine;

import com.codurance.io.BoardPrinter;
import com.codurance.io.Console;
import com.codurance.gameEngine.markers.Cross;
import com.codurance.gameEngine.markers.Marker;
import com.codurance.gameEngine.markers.Naught;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Board {
	private final int OFFSET = 1;
	private final int EMPTY = 0;

	private final Marker X = new Cross();
	private final Marker O = new Naught();
	private Marker currentMarker = X;

	private Console console;
	private final BoardPrinter boardPrinter;

	private int[] board = new int[9];

	public Board(Console console) {
		this.console = console;
		this.boardPrinter = new BoardPrinter(console);
	}

	public void play(Position position) {
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

	public ArrayList<Position> remainingSpaces() {
		return new ArrayList<Position>() {{
			for (int index = 0; index < board.length; index++) {
				if (board[index] == EMPTY) add(new Position(index + OFFSET));
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

	public Marker getCurrentMarker() {
		return currentMarker;
	}

	private boolean isSatisfied(WinCondition winCondition) {
		return X_hasSatisfied(winCondition) || O_hasSatisfied(winCondition);
	}

	private boolean X_hasSatisfied(WinCondition winCondition) {
		AtomicInteger sum = new AtomicInteger(0);
		winCondition.positions.forEach((pos) -> sum.addAndGet(board[pos.index]));
		return sum.get() == winCondition.THREE_Xs;
	}

	private boolean O_hasSatisfied(WinCondition winCondition) {
		AtomicInteger sum = new AtomicInteger(0);
		winCondition.positions.forEach((pos) -> sum.addAndGet(board[pos.index]));
		return sum.get() == winCondition.THREE_Os;
	}

	private boolean hasNoWinner() {
		return !hasWinner();
	}

	private boolean hasSpacesRemaining() {
		return !remainingSpaces().isEmpty();
	}

	private void markBoardAt(Position position) {
		board[position.index - OFFSET]=currentMarker.value();
	}

	private void switchMarker() {
		currentMarker = (currentMarker.equals(X)) ? O : X;
	}
}
