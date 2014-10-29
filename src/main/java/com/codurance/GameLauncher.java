package com.codurance;

import com.codurance.IO.Console;
import com.codurance.IO.InputHandler;
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

		SinglePlayerGameEngine singlePlayerGameEngine = new SinglePlayerGameEngine(board, humanPlayer, new ComputerPlayer(), console, inputHandler);
		MultiPlayerGameEngine multiPlayerGameEngine = new MultiPlayerGameEngine();

		new Game(singlePlayerGameEngine, multiPlayerGameEngine, console, inputHandler).start();
	}
}
