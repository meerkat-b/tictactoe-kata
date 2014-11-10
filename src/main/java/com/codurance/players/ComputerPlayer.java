package com.codurance.players;

import com.codurance.io.Console;
import com.codurance.gameEngine.*;
import com.codurance.gameEngine.markers.Cross;
import com.codurance.gameEngine.markers.Marker;
import com.codurance.gameEngine.markers.Naught;

public class ComputerPlayer implements Player {

	private final Marker O = new Naught();
	private final Marker X = new Cross();
	private final int OFFSET = 1;
	private Marker marker;
	private Marker opposingMarker;
	private Console console;

	public ComputerPlayer(Console console) {
		this.console = console;
	}

	@Override
	public void play(Board board) {
		getMarkersFrom(board);

		Position position = new ComputerStrategies(board, marker,  opposingMarker).execute();
		position.applyOffsetOf(1);
		board.play(position);
		printChosenMove(position);
	}

	private void printChosenMove(Position positionToPlay) {
		console.println("Computer has chosen position ["+(positionToPlay.value)+"]");
	}

	private void getMarkersFrom(Board board) {
		marker = board.getCurrentMarker();
		opposingMarker = (marker == X) ? O : X;
	}
}
