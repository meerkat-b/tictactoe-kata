package com.codurance;

import com.codurance.io.InputHandler;
import com.codurance.gameEngine.GameEngine;
import com.codurance.gameEngine.MultiPlayerGameEngine;
import com.codurance.gameEngine.SinglePlayerGameEngine;

public class Game {

	private static final String SINGLE_PLAYER = "s";

	private SinglePlayerGameEngine singlePlayerGameEngine;
	private MultiPlayerGameEngine multiPlayerGameEngine;
	private InputHandler inputHandler;

	public Game(SinglePlayerGameEngine singlePlayerGameEngine, MultiPlayerGameEngine multiPlayerGameEngine, InputHandler inputHandler) {
		this.singlePlayerGameEngine = singlePlayerGameEngine;
		this.multiPlayerGameEngine = multiPlayerGameEngine;
		this.inputHandler = inputHandler;
	}

	public void start() {
		gameType().runGame();
	}

	private GameEngine gameType() {
		return inputHandler.getGameType().equals(SINGLE_PLAYER)
																? singlePlayerGameEngine
																: multiPlayerGameEngine;
	}

}
