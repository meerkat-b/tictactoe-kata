package com.codurance.players;

import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.WinCondition;

public class ComputerPlayer implements Player {

	public static final int OFFSET = 1;
	public static final int EMPTY = 0;
	int[] board;

	@Override
	public void play(Board boardToPlay) {
		this.board = boardToPlay.getBoard();


		//priority HIGH
		for(WinCondition winCondition : WinCondition.values()) {
			if (isSatisfied(winCondition)) {
				if (board[winCondition.pos1]== EMPTY) {
					boardToPlay.play(winCondition.pos1 + OFFSET);
				}
				if (board[winCondition.pos2]== EMPTY) {
					boardToPlay.play(winCondition.pos2 + OFFSET);
				}
				if (board[winCondition.pos3]== EMPTY) {
					boardToPlay.play(winCondition.pos3 + OFFSET);
				}
			}
		}
	}

	private boolean isSatisfied(WinCondition winCondition) {
		return board[winCondition.pos1] + board[winCondition.pos2] + board[winCondition.pos3]
				== winCondition.TWO_Xs;
	}
}
