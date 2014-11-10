package com.codurance.gameEngine.strategy;

import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.Position;
import com.codurance.gameEngine.markers.Marker;

import java.util.ArrayList;

public class ComputerStrategies {

	private ArrayList<ComputerStrategy> strategies;

	public ComputerStrategies(Board board, Marker marker, Marker opposingMarker) {
		strategies = new ArrayList<ComputerStrategy>(){{
			add(new WinningMoveStrategy(board, marker));
			add(new InterceptWinningMoveStrategy(board, opposingMarker));
			add(new RandomPlayStrategy(board));
		}};
	}

	public Position execute() {
		Position position = null;
		for (ComputerStrategy strategy : strategies) {
			if (hasNotYetFound(position)) {
				position = strategy.execute();
			}
		}
		return position;
	}

	private boolean hasNotYetFound(Position position) {
		return position == null;
	}
}
