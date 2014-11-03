package com.codurance;

import com.codurance.IO.Console;
import com.codurance.gameEngine.Board;
import com.codurance.players.ComputerPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
	complete_a_row_of_x_when_computer_is_going_first() {
		board = aBoardThatUses(console)
				.withXMarksAt(TOP_LEFT, TOP_CENTER)
				.withOMarksAt(MIDDLE_LEFT, BOTTOM_RIGHT)
				.build();
		computerPlayer.play(board);
		ArrayList expectedSpaces = new ArrayList<Integer>(){{
			add(5);
			add(6);
			add(7);
			add(8);
		}};

		assertThat(board.remainingSpaces(), is(expectedSpaces));
	}

	@Test public void
	complete_a_column_of_o_when_computers_is_going_second() {
		board = aBoardThatUses(console)
				.withXMarksAt(BOTTOM_RIGHT, MIDDLE_CENTER, TOP_RIGHT)
				.withOMarksAt(TOP_LEFT, MIDDLE_LEFT)
				.build();
		computerPlayer.play(board);

		ArrayList expectedSpaces = new ArrayList<Integer>(){{
			add(2);
			add(6);
			add(8);
		}};

		assertThat(board.remainingSpaces(), is(expectedSpaces));
	}

	@Test public void
	intercept_an_opponents_win_condition_if_there_is_no_immediate_winning_move() {
		board = aBoardThatUses(console)
				.withXMarksAt(TOP_LEFT, MIDDLE_LEFT)
				.withOMarksAt(TOP_CENTER)
				.build();
		computerPlayer.play(board);
		ArrayList expectedSpaces = new ArrayList<Integer>() {{
			add(3);
			add(5);
			add(6);
			add(8);
			add(9);
		}};

		assertThat(board.remainingSpaces(), is(expectedSpaces));
	}

	@Test public void
	make_a_random_move_if_there_is_no_winning_or_intercepting_move() {
		board.play(1);
		board.play(2);
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
