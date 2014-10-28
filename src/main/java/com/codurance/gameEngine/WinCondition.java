package com.codurance.gameEngine;

public enum WinCondition {
	R1 (0,1,2),
	R2 (3,4,5),
	R3 (6,7,8),
	C1 (0,3,6),
	C2 (1,4,7),
	C3 (2,5,8),
	D1 (0,4,8),
	D2 (6,4,2);
	public int p1;
	public int p2;
	public int p3;

	WinCondition(int position1, int position2, int position3) {
		this.p1 = position1;
		this.p2 = position2;
		this.p3 = position3;
	}
}
