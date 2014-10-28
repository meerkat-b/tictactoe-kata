package com.codurance;

import com.codurance.IO.Console;
import com.codurance.IO.InputHandler;
import com.codurance.gameEngine.GameEngine;
import com.codurance.gameEngine.MultiPlayerGameEngine;
import com.codurance.gameEngine.SinglePlayerGameEngine;

public class Game {

	private static final String SINGLE_PLAYER = "s";

	private SinglePlayerGameEngine singlePlayerGameEngine;
	private MultiPlayerGameEngine multiPlayerGameEngine;
	private Console console;
	private InputHandler inputHandler;

	public Game(SinglePlayerGameEngine singlePlayerGameEngine, MultiPlayerGameEngine multiPlayerGameEngine, Console console, InputHandler inputHandler) {
		this.singlePlayerGameEngine = singlePlayerGameEngine;
		this.multiPlayerGameEngine = multiPlayerGameEngine;
		this.console = console;
		this.inputHandler = inputHandler;
	}

	public void start() {
		gameType().runGame();
	}

	private GameEngine gameType() {
		return inputHandler.getGameType() == SINGLE_PLAYER ?
				singlePlayerGameEngine :
				multiPlayerGameEngine;
	}

}
