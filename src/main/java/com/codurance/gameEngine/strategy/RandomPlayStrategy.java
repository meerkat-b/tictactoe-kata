package com.codurance.gameEngine.strategy;

import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.Position;

import java.util.Random;

public class RandomPlayStrategy implements ComputerStrategy {
	private Board board;

	public RandomPlayStrategy(Board board) {
		this.board = board;
	}

	@Override
	public Position execute() {
		return board.remainingSpaces().get(randomRemainingSpace());
	}

	private int randomRemainingSpace() {
		Random rng = new Random();
		return rng.nextInt(board.remainingSpaces().size());
	}
}
