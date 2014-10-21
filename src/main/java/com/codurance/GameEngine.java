package com.codurance;

public class GameEngine {

	public static final String MOVE_REQUEST = "Please type the number of the place to mark";

	private Console console;
	private InputHandler inputHandler;
	private Board board;
	private ComputerPlayer computerPlayer;

	public void runGame(Board newBoard) {
		this.board = newBoard;
		board.print();

		console.println(MOVE_REQUEST);
		board.mark(inputHandler.getNextMove());

		computerPlayer.getNextMove();
	}
}
