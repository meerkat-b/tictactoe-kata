package com.codurance.gameEngine;

import com.codurance.players.HumanPlayer;

public class MultiPlayerGameEngine implements GameEngine {

	private Board board;
	private HumanPlayer humanPlayer;
	private HumanPlayer secondHumanPlayer;
	private HumanPlayer currentPlayer;

	public MultiPlayerGameEngine(Board board, HumanPlayer humanPlayer, HumanPlayer secondHumanPlayer) {
		this.board = board;
		this.humanPlayer = humanPlayer;
		this.secondHumanPlayer = secondHumanPlayer;
		currentPlayer = humanPlayer;
	}

	@Override
	public void runGame() {
		while(board.isInPlay()) {
			board.printBoard();
			currentPlayer.play(board);
			switchPlayers();
		}

		board.declareWinner();
	}

	private void switchPlayers() {
		currentPlayer = (currentPlayer == humanPlayer) ?
				secondHumanPlayer : humanPlayer;
	}
}
