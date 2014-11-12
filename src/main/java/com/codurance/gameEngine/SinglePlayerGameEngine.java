package com.codurance.gameEngine;

import com.codurance.players.Player;

public class SinglePlayerGameEngine implements GameEngine {


	private Board board;
	private Player humanPlayer;
	private Player computerPlayer;
	private Player currentPlayer;

	public SinglePlayerGameEngine(Board board, Player humanPlayer, Player computerPlayer) {
		this.board = board;
		this.humanPlayer = humanPlayer;
		this.computerPlayer = computerPlayer;
	}

	@Override
	public void runGame() {
		setTurnOrder();

		while(board.isInPlay()) {
			board.printBoard();
			nextPlayer().play(board);
		}
		board.declareWinner();
	}

	private void setTurnOrder() {
		currentPlayer = humanPlayer.wantToGoFirst()
									? computerPlayer
									: humanPlayer;
	}

	private Player nextPlayer() {
		currentPlayer = (currentPlayer == humanPlayer)
															? computerPlayer
															: humanPlayer;
		return currentPlayer;
	}
}
