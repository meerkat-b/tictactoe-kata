package com.codurance.players;

import com.codurance.io.InputHandler;
import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.Position;

public class HumanPlayer implements Player {
	private InputHandler inputHandler;

	public HumanPlayer(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	@Override
	public void play(Board board) {
		board.play(new Position(inputHandler.getPlayFor(board)));
		board.printRemainingSpaces();
	}
}
