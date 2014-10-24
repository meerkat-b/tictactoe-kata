package com.codurance.gameEngine;

import com.codurance.IO.Console;
import com.codurance.IO.InputHandler;
import com.codurance.players.Player;

public class SinglePlayerGameEngine implements GameEngine {

	public static final String REQUEST_TURN_ORDER = "Would you like to go [1]st or [2]nd?";

	private Console console;
	private InputHandler inputHandler;
	private Player[] players;
	private Board board;

	@Override
	public void runGame() {
		requestTurnOrder();
	}

	private void requestTurnOrder() {
		console.println(REQUEST_TURN_ORDER);
	}
}
