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

	public boolean equals(Object o) {
		if (o instanceof Marker) {
			Marker marker = (Marker) o;
			return marker.value() == value();
		}
		return false;
	}
}
