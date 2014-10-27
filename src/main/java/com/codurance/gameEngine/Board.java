package com.codurance.gameEngine;

import com.codurance.IO.Console;
import org.apache.commons.lang.NotImplementedException;

public class Board {
	private Console console;

	public Board(Console console) {
		this.console = console;
	}

	public boolean isInPlay() {
		throw new NotImplementedException();
	}

	public void print() {

	}

	public void declareWinner() {

	}
}
