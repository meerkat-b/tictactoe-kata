package com.codurance.players;

import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.WinCondition;

public class ComputerPlayer implements Player {

	private final int OFFSET = 1;
	private final int EMPTY = 0;
	private final int TWO = 2;
	int[] board;
	private int marker;

	@Override
	public void play(Board boardToPlay) {
		this.board = boardToPlay.getBoard();
		getMarkerFrom(boardToPlay);
		//priority HIGH
		for(WinCondition winCondition : WinCondition.values()) {
			if (isSatisfied(winCondition)) {
				if (board[winCondition.pos1]== EMPTY) {
					boardToPlay.play(winCondition.pos1 + OFFSET);
					break;
				} else if (board[winCondition.pos2]== EMPTY) {
					boardToPlay.play(winCondition.pos2 + OFFSET);
					break;
				} else if (board[winCondition.pos3]== EMPTY) {
					boardToPlay.play(winCondition.pos3 + OFFSET);
					break;
				}
			}
		}
	}

	private void getMarkerFrom(Board board) {
		marker = board.getCurrentMarker();
	}

	private boolean isSatisfied(WinCondition winCondition) {
		return board[winCondition.pos1] + board[winCondition.pos2] + board[winCondition.pos3]
				== TWO * marker;
	}
}
