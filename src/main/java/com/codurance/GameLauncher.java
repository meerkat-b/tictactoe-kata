package com.codurance;

import com.codurance.io.Console;
import com.codurance.io.InputHandler;
import com.codurance.gameEngine.Board;
import com.codurance.players.ComputerPlayer;
import com.codurance.players.HumanPlayer;
import com.codurance.players.Player;

public class GameLauncher {

	public static void main(String[] args) {
		Console console = new Console();
		InputHandler inputHandler = new InputHandler(console);
		Board board = new Board(console);
		HumanPlayer humanPlayer = new HumanPlayer(inputHandler);
		HumanPlayer secondHumanPlayer = new HumanPlayer(inputHandler);
		ComputerPlayer computerPlayer = new ComputerPlayer(console);
		Player[] players = new Player[2];

		if (inputHandler.getGameType().equals("s")) {
			if(inputHandler.getTurnOrder() == 1) {
				players[0] = humanPlayer;
				players[1] = computerPlayer;
			} else {
				players[1] = humanPlayer;
				players[0] = computerPlayer;
			}
		} else {
			players[0] = humanPlayer;
			players[1] = secondHumanPlayer;
		}

		new Game(board, players[0], players[1]).runGame();
	}
}
