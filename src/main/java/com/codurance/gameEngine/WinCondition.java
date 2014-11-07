package com.codurance.gameEngine;

import com.codurance.gameEngine.markers.Cross;
import com.codurance.gameEngine.markers.Naught;

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

	public final int THREE_Xs = 3 * new Cross().get();
	public final int THREE_Os = 3 * new Naught().get();

	private WinCondition(int position1, int position2, int position3) {
		this.pos1 = position1;
		this.pos2 = position2;
		this.pos3 = position3;
	}
}
