package com.codurance.gameEngine.markers;

public class Cross implements Marker {
	private int value;

	public Cross() {
		value = 1;
	}

	@Override
	public int value() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		Marker marker = (Marker) o ;
		return marker.value() == value();
	}
}
