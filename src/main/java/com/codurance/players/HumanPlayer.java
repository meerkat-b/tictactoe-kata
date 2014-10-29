package com.codurance.players;

import com.codurance.IO.InputHandler;
import com.codurance.gameEngine.Board;

public class HumanPlayer implements Player {
	private InputHandler inputHandler;

	public HumanPlayer(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	@Override
	public void play(Board board) {
		board.play(inputHandler.getPlayFor(board));
	}
}
