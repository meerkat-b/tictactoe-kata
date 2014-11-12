package com.codurance.gameEngine;

public class Position {

	public Position(int position) {
		index = position;
	}

	public int index;

	@Override
	public boolean equals(Object object) {
		Position position = (Position) object;
		return index == position.index;
	}
}
