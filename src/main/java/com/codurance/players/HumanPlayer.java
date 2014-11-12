package com.codurance.players;

import com.codurance.io.InputHandler;
import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.Position;

public class HumanPlayer implements Player {
	private InputHandler inputHandler;
	private final int HUMAN_GOES_FIRST = 1;

	public HumanPlayer(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	@Override
	public void play(Board board) {
		board.play(new Position(inputHandler.getPlayFor(board)));
	}

	@Override
	public boolean wantToGoFirst() {
		return (inputHandler.getTurnOrder() == HUMAN_GOES_FIRST)
													? true
													: false;
	}
}
