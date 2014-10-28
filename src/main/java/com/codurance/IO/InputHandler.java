package com.codurance.IO;

import org.apache.commons.lang.NotImplementedException;

public class InputHandler {
	private Console console;

	public InputHandler(Console console) {
		this.console = console;
	}

	public String nextInput() {
		throw new NotImplementedException();
	}

	public String getGameType() {
		console.println("Please Select : [S]ingle-player or [M]ulti-player?");
		String gameType = console.nextLine();
		return (gameType.equalsIgnoreCase("m") | gameType.equalsIgnoreCase("s")) ?
				gameType : getGameType();
	}

	public int getTurnOrder() {
		throw new NotImplementedException();
	}
}
