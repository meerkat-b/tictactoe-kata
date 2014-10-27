package com.codurance;

import com.codurance.IO.Console;
import com.codurance.gameEngine.Board;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BoardShould {

	private static final int TOP_RIGHT = 3;
	private static final int TOP_LEFT = 1;
	private static final int X = 1;
	private static final int O = 2;
	private Console console = mock(Console.class);
	private Board board;

	@Before
	public void initialise() {
		board = new Board(console);
	}

	@Test public void
	print_itself() {
		board.print();

		verify(console).print( "\n" +
				" - - -\n" +
				" - - -\n" +
				" - - -\n"
		);
	}

	@Test public void
	print_itself_after_a_play_on_the_board() {
		board.mark(TOP_RIGHT, X);
		board.print();
		verify(console).print("\n" +
						" - - x\n" +
						" - - -\n" +
						" - - -\n"
		);
	}

	@Test public void
	print_itself_after_two_plays_on_the_board() {
		board.mark(TOP_RIGHT, X);
		board.mark(TOP_LEFT, O);
		board.print();

		verify(console).print("\n" +
						" o - x\n" +
						" - - -\n" +
						" - - -\n"
		);
	}

	
}
