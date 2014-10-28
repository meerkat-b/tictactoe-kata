package com.codurance.IO;

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

		return (turnOrder == "1" | turnOrder == "2") ?
				Integer.parseInt(turnOrder) : getTurnOrder();
	}
}
