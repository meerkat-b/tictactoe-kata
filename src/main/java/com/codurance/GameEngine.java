package com.codurance;

public class GameEngine {

	private static final boolean PLAYER_TWO = false;
	private static final boolean PLAYER_ONE = true;
	private static final String X = "X";
	private static final String O = "O";
	private static final String MOVE_REQUEST = "Please type the number of the place to mark";
	private static final String TIE_GAME = "Tie game!";

	private boolean currentPlayer = PLAYER_ONE;

	private Console console;
	private InputHandler inputHandler;
	private ComputerPlayer computerPlayer;

	public void runGame(Board board) {
		while(board.isInPlay()) {
			board.print();
			takeATurn(board);
			switchPlayers();
		}
		if(board.hasNoWinner()) {
			declareTie();
		}
	}

	private void takeATurn(Board board) {
		if(isPlayerOnesTurn()) {
			console.println(MOVE_REQUEST);
			board.mark(X, inputHandler.getNextPosition());
		}
		if(isPlayerTwosTurn()) {
			board.mark(O, computerPlayer.getNextPosition());
		}
	}

	private void declareTie() {
		console.println(TIE_GAME);
	}


	private boolean isPlayerOnesTurn() {
		return currentPlayer == PLAYER_ONE;
	}

	private boolean isPlayerTwosTurn() {
		return currentPlayer == PLAYER_TWO;
	}

	private void switchPlayers() {
		currentPlayer = !currentPlayer;
	}
}
