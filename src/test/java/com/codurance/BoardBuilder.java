package com.codurance;

import com.codurance.io.Console;
import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.Position;

import java.util.ArrayList;

public class BoardBuilder {

	private static Board board;
	private static ArrayList<Position> xList = new ArrayList<>();
	private static ArrayList<Position> oList = new ArrayList<>();

	public BoardBuilder(Console console) {
		board = new Board(console);
	}

	public static Board build() {
		while(thereAreStillMarkersToPlay()) {
			playARemainingX();
			playARemainingO();
		}
		return board;
	}

	public static BoardBuilder aBoardThatUses(Console console) {
		return new BoardBuilder(console);
	}

	public BoardBuilder withXMarksAt(Integer... XsToAdd) {
		for (int i : XsToAdd) {
			xList.add(new Position(i));
		}
		return this;
	}

	public BoardBuilder withOMarksAt(Integer... OsToAdd) {
		for (int i : OsToAdd) {
			oList.add(new Position(i));
		}
		return this;
	}

	private static boolean thereAreStillMarkersToPlay() {
		return !xList.isEmpty() || !oList.isEmpty();
	}

	private static void playARemainingO() {
		if (!oList.isEmpty()) {
			board.play(oList.remove(0));
		}
	}

	private static void playARemainingX() {
		if (!xList.isEmpty()) {
			board.play(xList.remove(0));
		}
	}
}
