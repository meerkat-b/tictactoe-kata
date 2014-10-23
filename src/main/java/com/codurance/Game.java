package com.codurance;

public class Game {

	private static final String SINGLE_PLAYER = "s";
	private static final String MULTI_PLAYER = "m";
	private static final String REQUEST_GAMETYPE = "[S]ingle Player or [M]ulti-player?";

	private SinglePlayerGameEngine singlePlayerGameEngine;
	private MultiPlayerGameEngine multiPlayerGameEngine;
	private Console console;
	private InputHandler inputHandler;

	public Game() {
		singlePlayerGameEngine = new SinglePlayerGameEngine();
		multiPlayerGameEngine = new MultiPlayerGameEngine();
		console = new Console();
		inputHandler = new InputHandler();
	}

	public void start() {
		requestGameType();
		runGame();
	}

	private void runGame() {
		String gameType = inputHandler.nextInput();
		if (gameType == SINGLE_PLAYER) {
			singlePlayerGameEngine.runGame();
		}
		if (gameType == MULTI_PLAYER) {
			multiPlayerGameEngine.runGame();
		}
	}

	private void requestGameType() {
		console.println(REQUEST_GAMETYPE);
	}
}
