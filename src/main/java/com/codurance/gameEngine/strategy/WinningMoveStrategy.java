package com.codurance.gameEngine.strategy;

import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.Position;
import com.codurance.gameEngine.WinCondition;
import com.codurance.gameEngine.markers.Marker;

import java.util.concurrent.atomic.AtomicInteger;

public class WinningMoveStrategy implements ComputerStrategy {
	private final int EMPTY = 0;
	private int[] board;
	private Marker marker;

	public WinningMoveStrategy(Board board, Marker marker) {
		this.board = board.state();
		this.marker = marker;
	}

	@Override
	public Position execute() {
		for(WinCondition winCondition : WinCondition.values()) {
			if(canPotentiallyFinish(winCondition)) {
				return emptySpaceOf(winCondition);
			}
		}
		return null;
	}

	private Position emptySpaceOf(WinCondition winCondition) {
		for(Position position : winCondition.positions) {
			if (board[position.value] == EMPTY) {
				return new Position(position.value);
			}
		}
		throw new RuntimeException("Failed to find an empty space of the current Win Condition");
	}

	private boolean canPotentiallyFinish(WinCondition winCondition) {
		AtomicInteger sum = new AtomicInteger(0);
		winCondition.positions.forEach((pos) -> sum.addAndGet(board[pos.value]));
		return sum.get() == 2*marker.value();
	}
}
