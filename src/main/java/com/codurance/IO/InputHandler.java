package com.codurance.IO;

import com.codurance.gameEngine.Board;

public class InputHandler {
	private Console console;

	public InputHandler(Console console) {
		this.console = console;
	}

	public String getGameType() {
		console.println("Please Select : [S]ingle-player or [M]ulti-player?");
		String gameType = console.nextLine();
		return (gameType.equalsIgnoreCase("m") | gameType.equalsIgnoreCase("s")) ?
				gameType : getGameType();
	}

	public int getTurnOrder() {
		console.println("Please Select : Would you like to go [1]st or [2]nd?");
		String turnOrder = console.nextLine();

		return (turnOrder.equals("1") | turnOrder.equals("2")) ?
				Integer.parseInt(turnOrder) : getTurnOrder();
	}

	public int getPlayFor(Board board) {
		board.printRemainingSpaces();
		console.println("Please select a space to mark");
		int desiredPosition = intOfPlayFor(console.nextLine(), board);

		return board.remainingSpaces().contains((desiredPosition)) ?
		desiredPosition : getPlayFor(board);
	}

	private int intOfPlayFor(String string, Board board) {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			console.println("That's not a valid input D:<");
			return getPlayFor(board);
		}
	}
}
