package com.codurance;

import com.codurance.io.Console;
import com.codurance.io.InputHandler;
import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.MultiPlayerGameEngine;
import com.codurance.gameEngine.SinglePlayerGameEngine;
import com.codurance.players.ComputerPlayer;
import com.codurance.players.HumanPlayer;

public class GameLauncher {

	public static void main(String[] args) {
		Console console = new Console();
		InputHandler inputHandler = new InputHandler(console);
		Board board = new Board(console);
		HumanPlayer humanPlayer = new HumanPlayer(inputHandler);
		HumanPlayer secondHumanPlayer = new HumanPlayer(inputHandler);
		ComputerPlayer computerPlayer = new ComputerPlayer(console);

		SinglePlayerGameEngine singlePlayerGameEngine = new SinglePlayerGameEngine
				(board, humanPlayer, computerPlayer, console, inputHandler);
		MultiPlayerGameEngine multiPlayerGameEngine = new MultiPlayerGameEngine
				(board, humanPlayer, secondHumanPlayer);

		new Game(singlePlayerGameEngine, multiPlayerGameEngine, console, inputHandler).start();
	}
}
