package com.codurance.gameEngine;

public class Position {

	public Position(int position) {
		index = position;
	}

	public int index;

	public void applyOffset() {
		index += 1;
	}

	@Override
	public boolean equals(Object object) {
		if(object instanceof Position){
			Position position = (Position) object;
			return index == position.index;
		}
		return false;
	}
}
