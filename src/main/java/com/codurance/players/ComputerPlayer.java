package com.codurance.players;

import com.codurance.IO.Console;
import com.codurance.gameEngine.*;
import com.codurance.gameEngine.markers.Cross;
import com.codurance.gameEngine.markers.Marker;
import com.codurance.gameEngine.markers.Naught;

import java.util.Random;

public class ComputerPlayer implements Player {

	private final Marker O = new Naught();
	private final Marker X = new Cross();
	private final int OFFSET = 1;
	private final int EMPTY = 0;
	private final int TWO = 2;
	private int[] board;
	private Marker marker;
	private Marker opposingMarker;
	private Console console;

	public ComputerPlayer(Console console) {
		this.console = console;
	}

	@Override
	public void play(Board board) {
		this.board = board.state();
		getMarkersFrom(board);

		int positionToPlay = finishWinConditionOn(marker);
		if (positionToPlay == -1) {
			positionToPlay = finishWinConditionOn(opposingMarker);
		}

		if (positionToPlay == -1) {
			Random rng = new Random();
			positionToPlay = board.remainingSpaces().get(rng.nextInt(board.remainingSpaces().size())) - OFFSET;
		}
		board.play(positionToPlay + OFFSET);
		console.println("Computer has chosen position ["+(positionToPlay+OFFSET)+"]");
	}

	private int finishWinConditionOn(Marker marker) {
		for(WinCondition winCondition : WinCondition.values()) {
			if (canPotentiallyFinish(winCondition, marker)) {
				if (board[winCondition.pos1]==EMPTY) {
					return winCondition.pos1;
				} else if (board[winCondition.pos2]==EMPTY) {
					return winCondition.pos2;
				} else if (board[winCondition.pos3]==EMPTY) {
					return winCondition.pos3;
				}
			}
		}
		return -1;
	}

	private void getMarkersFrom(Board board) {
		marker = board.getCurrentMarker();
		opposingMarker = (marker == X) ? O : X;
	}

	private boolean canPotentiallyFinish(WinCondition winCondition, Marker marker) {
		return board[winCondition.pos1] + board[winCondition.pos2] + board[winCondition.pos3]
				== TWO * marker.get();
	}
}
