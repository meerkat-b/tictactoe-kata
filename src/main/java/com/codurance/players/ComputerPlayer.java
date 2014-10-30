package com.codurance.players;

import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.WinCondition;

public class ComputerPlayer implements Player {

	private final int O = 10;
	private final int X = 1;
	private final int OFFSET = 1;
	private final int EMPTY = 0;
	private final int TWO = 2;
	int[] board;
	private int marker;
	private int opposingMarker;

	@Override
	public void play(Board board) {
		this.board = board.getBoard();
		getMarkersFrom(board);

//		for(WinCondition winCondition : WinCondition.values()) {
//			if(isSatisfied(winCondition)) {
//
//			}
//		}
		//priority HIGH
		int positionToPlay = finishWinConditionOn();
		if (positionToPlay == -1) {
			positionToPlay = interceptOpposingWinConditionOn();
		}

		board.play(positionToPlay + OFFSET);

	}

	private int interceptOpposingWinConditionOn() {
		for(WinCondition winCondition : WinCondition.values()) {
			if (isSatisfied(winCondition, opposingMarker)) {
				if (board[winCondition.pos1]== EMPTY) {
//					boardToPlay.play(winCondition.pos1 + OFFSET);
					return winCondition.pos1;
				} else if (board[winCondition.pos2]== EMPTY) {
//					boardToPlay.play(winCondition.pos2 + OFFSET);
					return winCondition.pos2;
				} else if (board[winCondition.pos3]== EMPTY) {
//					boardToPlay.play(winCondition.pos3 + OFFSET);
					return winCondition.pos3;
				}
			}
		}
		return -1;
	}

	private int finishWinConditionOn() {
		for(WinCondition winCondition : WinCondition.values()) {
			if (isSatisfied(winCondition, marker)) {
				if (board[winCondition.pos1]== EMPTY) {
//					boardToPlay.play(winCondition.pos1 + OFFSET);
					return winCondition.pos1;
				} else if (board[winCondition.pos2]== EMPTY) {
//					boardToPlay.play(winCondition.pos2 + OFFSET);
					return winCondition.pos2;
				} else if (board[winCondition.pos3]== EMPTY) {
//					boardToPlay.play(winCondition.pos3 + OFFSET);
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

	private boolean isSatisfied(WinCondition winCondition, int marker) {
		return board[winCondition.pos1] + board[winCondition.pos2] + board[winCondition.pos3]
				== TWO * marker;
	}
}
