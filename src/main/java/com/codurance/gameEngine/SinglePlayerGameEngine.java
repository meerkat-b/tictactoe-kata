package com.codurance.gameEngine;

import com.codurance.IO.Console;
import com.codurance.IO.InputHandler;
import com.codurance.players.Player;

public class SinglePlayerGameEngine implements GameEngine {

	public static final String REQUEST_TURN_ORDER = "Would you like to go [1]st or [2]nd?";
	private static final int HUMAN_GOES_FIRST = 1;

	private Console console;
	private InputHandler inputHandler;
	private Board board;
	private Player humanPlayer;
	private Player computerPlayer;
	private Player currentPlayer;

	public SinglePlayerGameEngine(Board board, Player humanPlayer, Player computerPlayer,
								Console console, InputHandler inputHandler) {
		this.board = board;
		this.humanPlayer = humanPlayer;
		this.computerPlayer = computerPlayer;
		this.console = console;
		this.inputHandler = inputHandler;
	}

	@Override
	public void runGame() {
		setUpPlayers();

		while(board.isInPlay()) {
			board.print();
			currentPlayer.play(board);
			switchPlayers();
		}

		board.declareWinner();
	}

	private void setUpPlayers() {
		requestTurnOrder();
		setTurnOrder();
	}

	private void requestTurnOrder() {
		console.println(REQUEST_TURN_ORDER);
	}

	private void setTurnOrder() {
		currentPlayer = inputHandler.getTurnOrder() == HUMAN_GOES_FIRST ?
						humanPlayer : computerPlayer;
	}

	private void switchPlayers() {
		currentPlayer = (currentPlayer == humanPlayer) ? computerPlayer : humanPlayer;
	}
}
