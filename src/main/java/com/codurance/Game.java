package com.codurance;

import com.codurance.IO.Console;
import com.codurance.IO.InputHandler;
import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.MultiPlayerGameEngine;
import com.codurance.gameEngine.SinglePlayerGameEngine;
import com.codurance.players.ComputerPlayer;
import com.codurance.players.HumanPlayer;

public class Game {

	private static final String SINGLE_PLAYER = "s";
	private static final String MULTI_PLAYER = "m";
	private static final String REQUEST_GAMETYPE = "[S]ingle Player or [M]ulti-player?";

	private SinglePlayerGameEngine singlePlayerGameEngine;
	private MultiPlayerGameEngine multiPlayerGameEngine;
	private Console console;
	private InputHandler inputHandler;

	public Game() {
		singlePlayerGameEngine = new SinglePlayerGameEngine
				(new Board(), new HumanPlayer(), new ComputerPlayer(), console, inputHandler);
		multiPlayerGameEngine = new MultiPlayerGameEngine();
		console = new Console();
		inputHandler = new InputHandler();
	}

	public void start() {
		run(requestGameType());
	}

	private void run(String gameType) {
		if (gameType == SINGLE_PLAYER) {
			singlePlayerGameEngine.runGame();
		} else if (gameType == MULTI_PLAYER) {
			multiPlayerGameEngine.runGame();
		}
	}

	private String requestGameType() {
		console.println(REQUEST_GAMETYPE);
		return inputHandler.getGameType();
	}
}
