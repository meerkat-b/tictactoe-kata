package com.codurance;

import com.codurance.gameEngine.Board;
import com.codurance.players.Player;

public class Game {

	private final Board board;
	private Player player1;
	private Player player2;
	private Player currentPlayer;

	public Game(Board board, Player aPlayer, Player aSecondPlayer) {
		this.board = board;
		this.player1 = aPlayer;
		this.player2 = aSecondPlayer;
	}

	public void runGame() {
		while(board.isInPlay()) {
			board.printBoard();
			nextPlayer().play(board);
		}
		board.declareWinner();
	}

	private Player nextPlayer() {
		currentPlayer = (currentPlayer == player1)
				? player2
				: player1;
		return currentPlayer;
	}
}
