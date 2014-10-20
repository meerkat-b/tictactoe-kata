package com.codurance;

public class Game {

	private GameEngine gameEngine;

	public void start() {
	    gameEngine.runGame(new Board());
    }
}
