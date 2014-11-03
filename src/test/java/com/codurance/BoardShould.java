package com.codurance;

import com.codurance.IO.Console;
import com.codurance.gameEngine.Board;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.codurance.BoardBuilder.aBoardThatUses;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BoardShould {

	@Test public void
	print_itself() {
		board.printBoardState();

		verify(console).print( "\n" +
				" - - -\n" +
				" - - -\n" +
				" - - -\n" +
						"\n"
		);
	}

	@Test public void
	print_itself_after_a_play_on_the_board() {
		board.play(TOP_RIGHT);
		board.printBoardState();
		verify(console).print("\n" +
						" - - x\n" +
						" - - -\n" +
						" - - -\n" +
						"\n"
		);
	}

	@Test public void
	print_a_fully_played_board() {
		board = aFullBoardWithNoWinner();
		board.printBoardState();

		verify(console).print("\n" +
						" x o x\n" +
						" o x x\n" +
						" o x o\n" +
						"\n"
		);
	}

	@Test public void
	alternate_between_x_and_o_between_plays() {
		board = aBoardThatUses(console)
				.withXMarksAt(TOP_LEFT, TOP_RIGHT, MIDDLE_CENTER)
				.withOMarksAt(TOP_CENTER, MIDDLE_LEFT)
				.build();
		board.printBoardState();

		verify(console).print("\n" +
						" x o x\n" +
						" o x -\n" +
						" - - -\n" +
						"\n"
		);
	}

	@Test public void
	inform_if_there_is_a_row_win_condition() {
		board = aBoardThatUses(console)
				.withXMarksAt(TOP_LEFT, TOP_CENTER, TOP_RIGHT)
				.withOMarksAt(BOTTOM_RIGHT, MIDDLE_LEFT)
				.build();

		assertThat(board.hasWinner(), is(true));
	}

	@Test public void
	inform_if_there_is_a_column_win_condition() {
		board = aBoardThatUses(console)
				.withXMarksAt(TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT)
				.withOMarksAt(TOP_CENTER, BOTTOM_CENTER)
				.build();

		assertThat(board.hasWinner(), is(true));
	}

	@Test public void
	inform_if_there_is_a_diagonal_win_condition() {
		board = aBoardThatUses(console)
				.withXMarksAt(TOP_LEFT, MIDDLE_CENTER, BOTTOM_RIGHT)
				.withOMarksAt(TOP_CENTER, BOTTOM_CENTER)
				.build();

		assertThat(board.hasWinner(), is(true));
	}

	@Test public void
	inform_a_tie_situation_with_no_winner() {
		board = aFullBoardWithNoWinner();

		assertThat(board.hasWinner(), is(false));
	}

	@Test public void
	be_in_play_if_there_is_not_yet_a_winner() {
		board = aBoardThatUses(console)
				.withXMarksAt(TOP_LEFT, TOP_RIGHT)
				.withOMarksAt(TOP_CENTER)
				.build();

		assertThat(board.isInPlay(), is(true));
	}

	@Test public void
	not_be_in_play_if_there_are_no_more_spaces_on_the_board() {
		board = aFullBoardWithNoWinner();

		assertThat(board.isInPlay(), is(false));
	}

	@Test public void
	inform_of_its_remaining_spaces() {
		board = aBoardThatUses(console)
				.withXMarksAt(TOP_LEFT, TOP_RIGHT)
				.withOMarksAt(TOP_CENTER, MIDDLE_LEFT)
				.build();
		ArrayList positions = new ArrayList() {{
			add(5);
			add(6);
			add(7);
			add(8);
			add(9);
		}};

		assertThat(board.remainingSpaces(), is(positions));
	}

	@Test public void
	print_its_remaining_spaces() {
		board = aBoardThatUses(console)
				.withXMarksAt(TOP_RIGHT, MIDDLE_CENTER)
				.withOMarksAt(MIDDLE_LEFT, MIDDLE_RIGHT)
				.build();

		board.printRemainingSpaces();
		verify(console).print("Remaining spaces : [1] [2] [7] [8] [9] \n");
	}
	
	@Test public void
	declare_a_tie_if_there_is_no_winner_at_the_end_of_a_game() {
		board = aFullBoardWithNoWinner();

		board.declareWinner();
		verify(console).println("Tie Game! There is no Winner");
	}

	@Test public void
	declare_the_correct_winner_when_X_wins() {
		board = aBoardThatUses(console)
				.withXMarksAt(TOP_LEFT, TOP_CENTER, TOP_RIGHT)
				.withOMarksAt(MIDDLE_CENTER, BOTTOM_RIGHT)
				.build();

		board.declareWinner();
		verify(console).println("The winner is X!");
	}

	@Test public void
	declare_the_correct_winner_when_O_wins() {
		board = aBoardThatUses(console)
				.withXMarksAt(MIDDLE_CENTER, BOTTOM_RIGHT, BOTTOM_LEFT)
				.withOMarksAt(TOP_LEFT, TOP_CENTER, TOP_RIGHT)
				.build();

		board.declareWinner();
		verify(console).println("The winner is O!");
	}

	@Test public void
	print_the_end_board_when_declaring_the_winner() {
		board = aBoardThatUses(console)
				.withXMarksAt(1, 2, 3)
				.withOMarksAt(5, 9)
				.build();

		board.declareWinner();
		verify(console).print( "\n" +
						" x x x\n" +
						" - o -\n" +
						" - - o\n" +
						"\n"
		);
	}

	private Board aFullBoardWithNoWinner() {
		return aBoardThatUses(console)
				.withXMarksAt(TOP_LEFT, TOP_RIGHT, MIDDLE_CENTER, MIDDLE_RIGHT, BOTTOM_CENTER)
				.withOMarksAt(TOP_CENTER, MIDDLE_LEFT, BOTTOM_LEFT, BOTTOM_RIGHT)
				.build();
	}

	private final int TOP_LEFT = 1;
	private final int TOP_CENTER = 2;
	private final int TOP_RIGHT = 3;
	private final int MIDDLE_LEFT = 4;
	private final int MIDDLE_CENTER = 5;
	private final int MIDDLE_RIGHT = 6;
	private final int BOTTOM_LEFT = 7;
	private final int BOTTOM_CENTER = 8;
	private final int BOTTOM_RIGHT = 9;

	private Console console = mock(Console.class);
	private Board board;

	@Before
	public void initialise() {
		board = new Board(console);
	}

}
