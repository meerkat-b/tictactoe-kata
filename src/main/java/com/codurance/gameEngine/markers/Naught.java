package com.codurance.gameEngine.markers;

public class Naught implements Marker {
	private int value;

	public Naught() {
		value = 10;
	}

	@Override
	public int value() {
		return value;
	}
}
