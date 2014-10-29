package com.codurance.gameEngine;

import com.codurance.IO.Console;
import com.codurance.IO.InputHandler;
import com.codurance.players.Player;

public class SinglePlayerGameEngine implements GameEngine {

	private final int HUMAN_GOES_FIRST = 1;

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
		setTurnOrder();

		while(board.isInPlay()) {
			board.printBoardState();
			currentPlayer.play(board);
			switchPlayers();
		}
		board.declareWinner();
	}

	private void setTurnOrder() {
		currentPlayer = inputHandler.getTurnOrder() == HUMAN_GOES_FIRST ?
						humanPlayer : computerPlayer;
	}

	private void switchPlayers() {
		currentPlayer = (currentPlayer == humanPlayer) ? computerPlayer : humanPlayer;
	}
}
