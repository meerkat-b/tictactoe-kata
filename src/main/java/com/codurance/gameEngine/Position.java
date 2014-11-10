package com.codurance.gameEngine;

public class Position {

	public Position(int position) {
		value = position;
	}

	public int value;

	public void applyOffset() {
		value += 1;
	}
}
