package com.codurance;

public class GameEngine {

	public static final String MOVE_REQUEST = "Please type the number of the place to mark";

	private Console console;
	private InputHandler inputHandler;
	private Board board;

	public void runGame(Board board) {
		board.print();
		console.println(MOVE_REQUEST);
		int positionToMark = inputHandler.getNextMove();
		board.mark(positionToMark);
	}
}
