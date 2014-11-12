package com.codurance.gameEngine;

import com.codurance.players.Player;

public class MultiPlayerGameEngine implements GameEngine {

	private Board board;
	private Player humanPlayer;
	private Player secondHumanPlayer;
	private Player currentPlayer;

	public MultiPlayerGameEngine(Board board, Player humanPlayer, Player secondHumanPlayer) {
		this.board = board;
		this.humanPlayer = humanPlayer;
		this.secondHumanPlayer = secondHumanPlayer;
		currentPlayer = humanPlayer;
	}

	@Override
	public void runGame() {
		while(board.isInPlay()) {
			board.printBoard();
			nextPlayer().play(board);
		}

		board.declareWinner();
	}

	private Player nextPlayer() {
		currentPlayer = (currentPlayer == humanPlayer) ?
				secondHumanPlayer : humanPlayer;
		return currentPlayer;
	}
}
