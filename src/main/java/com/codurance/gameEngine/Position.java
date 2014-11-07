package com.codurance.gameEngine;

public class Position {

	public Position(int position) {
		value = position;
	}

	public int value;

	public void applyOffsetOf(int offset) {
		value += offset;
	}
}
