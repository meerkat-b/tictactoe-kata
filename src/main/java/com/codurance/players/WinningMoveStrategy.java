package com.codurance.players;

import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.Position;
import com.codurance.gameEngine.WinCondition;
import com.codurance.gameEngine.markers.Marker;

public class WinningMoveStrategy implements ComputerStrategy {
	private int[] board;
	private Marker marker;
	private int EMPTY = 0;

	public WinningMoveStrategy(Board board, Marker marker) {
		this.board = board.state();
		this.marker = marker;
	}

	@Override
	public Position execute() {
		for(WinCondition winCondition : WinCondition.values()) {
			if (canPotentiallyFinish(winCondition)) {
				if (board[winCondition.pos1]==EMPTY) {
					return new Position(winCondition.pos1);
				} else if (board[winCondition.pos2]==EMPTY) {
					return new Position(winCondition.pos2);
				} else if (board[winCondition.pos3]==EMPTY) {
					return new Position(winCondition.pos3);
				}
			}
		}
		return null;
	}

	private boolean canPotentiallyFinish(WinCondition winCondition) {
		return board[winCondition.pos1] + board[winCondition.pos2] + board[winCondition.pos3]
				== 2 * marker.get();
	}
}
