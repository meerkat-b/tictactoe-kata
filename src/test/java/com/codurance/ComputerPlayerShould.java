package com.codurance;

import com.codurance.io.Console;
import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.Position;
import com.codurance.players.ComputerPlayer;
import org.junit.Before;
import org.junit.Test;


import static com.codurance.BoardBuilder.aBoardThatUses;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ComputerPlayerShould {

	private final int TOP_LEFT = 1;
	private final int TOP_CENTER = 2;
	private final int TOP_RIGHT = 3;
	private final int MIDDLE_LEFT = 4;
	private final int MIDDLE_CENTER = 5;
	private final int BOTTOM_RIGHT = 9;

	private Board board;
	private ComputerPlayer computerPlayer;
	private Console console;

	@Before
	public void initialise() {
		console = mock(Console.class);

 		board = new Board(console);

		computerPlayer = new ComputerPlayer(console);
	}

	@Test public void
	complete_a_row_of_x_when_playing_as_x() {
		board = aBoardThatUses(console)
				.withXMarksAt(TOP_LEFT, TOP_CENTER)
				.withOMarksAt(MIDDLE_LEFT, BOTTOM_RIGHT)
				.build();
		computerPlayer.play(board);
		verify(console).println("Computer has chosen position [3]");
	}

	@Test public void
	complete_a_column_of_o_when_playing_as_o() {
		board = aBoardThatUses(console)
				.withXMarksAt(BOTTOM_RIGHT, MIDDLE_CENTER, TOP_RIGHT)
				.withOMarksAt(TOP_LEFT, MIDDLE_LEFT)
				.build();
		computerPlayer.play(board);
		verify(console).println("Computer has chosen position [7]");
	}

	@Test public void
	intercept_an_opponents_win_condition_when_playing_as_x() {
		board = aBoardThatUses(console)
				.withXMarksAt(MIDDLE_LEFT, BOTTOM_RIGHT)
				.withOMarksAt(TOP_LEFT, TOP_CENTER)
				.build();
		computerPlayer.play(board);
		verify(console).println("Computer has chosen position [3]");
	}

	@Test public void
	intercept_an_opponents_win_condition_whenplaying_as_o() {
		board = aBoardThatUses(console)
				.withXMarksAt(TOP_LEFT, MIDDLE_LEFT)
				.withOMarksAt(TOP_CENTER)
				.build();
		computerPlayer.play(board);
		verify(console).println("Computer has chosen position [7]");
	}


	@Test public void
	make_a_random_move_if_there_is_no_winning_or_intercepting_move() {
		board.play(new Position(TOP_LEFT));
		board.play(new Position(TOP_CENTER));
		computerPlayer.play(board);

		assertThat(board.remainingSpaces().size(), is(6));
	}

	@Test public void
	print_out_the_move_they_have_made() {
		board = aBoardThatUses(console)
				.withXMarksAt(TOP_LEFT, TOP_CENTER)
				.withOMarksAt(MIDDLE_CENTER, BOTTOM_RIGHT)
				.build();
		computerPlayer.play(board);

		verify(console).println("Computer has chosen position [3]");
	}
}
