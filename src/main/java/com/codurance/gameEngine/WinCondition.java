package com.codurance.gameEngine;

import com.codurance.gameEngine.markers.Cross;
import com.codurance.gameEngine.markers.Naught;

import java.util.ArrayList;

public enum WinCondition {
	ROW1 (0,1,2),
	ROW2 (3,4,5),
	ROW3 (6,7,8),
	COL1 (0,3,6),
	COL2 (1,4,7),
	COL3 (2,5,8),
	DIA1 (0,4,8),
	DIA2 (6,4,2);

	public int pos1;
	public int pos2;
	public int pos3;

	public ArrayList<Position> positions = new ArrayList<>();

	public final int THREE_Xs = 3 * new Cross().value();
	public final int THREE_Os = 3 * new Naught().value();

	private WinCondition(int pos1, int pos2, int pos3) {
		positions.add(new Position(pos1));
		positions.add(new Position(pos2));
		positions.add(new Position(pos3));
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.pos3 = pos3;

		//things to add:
		//list of positions instead of 3 variables
		//sum of 3
		//sum of 2
	}
}
