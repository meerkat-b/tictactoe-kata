package com.codurance;

import com.codurance.IO.Console;
import com.codurance.gameEngine.Board;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BoardShould {

	private static final int TOP_RIGHT = 3;
	private Console console = mock(Console.class);
	private Board board;

	@Before
	public void initialise() {
		board = new Board(console);
	}

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
		playFullBoardWithNoWinner();
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
		board.play(1);
		board.play(2);
		board.play(3);
		board.play(4);
		board.play(5);
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
		board.play(1);
		board.play(4);
		board.play(2);
		board.play(9);
		board.play(3);

		assertThat(board.hasWinner(), is(true));
	}

	@Test public void
	inform_if_there_is_a_column_win_condition() {
		board.play(1);
		board.play(2);
		board.play(4);
		board.play(8);
		board.play(7);

		assertThat(board.hasWinner(), is(true));
	}

	@Test public void
	inform_if_there_is_a_diagonal_win_condition() {
		board.play(1);
		board.play(2);
		board.play(5);
		board.play(8);
		board.play(9);

		assertThat(board.hasWinner(), is(true));
	}

	@Test public void
	inform_a_tie_situation_with_no_winner() {
		playFullBoardWithNoWinner();

		assertThat(board.hasWinner(), is(false));
	}

	@Test public void
	be_in_play_if_there_is_not_yet_a_winner() {
		board.play(1);
		board.play(2);
		board.play(3);

		assertThat(board.isInPlay(), is(true));
	}

	@Test public void
	not_be_in_play_if_there_are_no_more_spaces_on_the_board() {
		playFullBoardWithNoWinner();

		assertThat(board.isInPlay(), is(false));
	}

	@Test public void
	inform_of_its_remaining_spaces() {
		board.play(1);
		board.play(2);
		board.play(3);
		ArrayList positions = new ArrayList() {{
			add(4);
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
		board.play(3);
		board.play(4);
		board.play(5);
		board.play(6);

		board.printRemainingSpaces();
		verify(console).print("Remaining spaces : [1] [2] [7] [8] [9] \n");
	}
	
	@Test public void
	declare_a_tie_if_there_is_no_winner_at_the_end_of_a_game() {
		playFullBoardWithNoWinner();

		board.declareWinner();
		verify(console).println("Tie Game! There is no Winner");
	}

	@Test public void
	declare_the_correct_winner_when_X_wins() {
		board.play(1);
		board.play(5);
		board.play(2);
		board.play(9);
		board.play(3);

		board.declareWinner();

		verify(console).println("The winner is X!");
	}

	@Test public void
	declare_the_correct_winner_when_O_wins() {
		board.play(5);
		board.play(1);
		board.play(9);
		board.play(2);
		board.play(7);
		board.play(3);

		board.declareWinner();

		verify(console).println("The winner is O!");
	}

	@Test public void
	print_the_end_board_when_declaring_the_winner() {
		board.play(1);
		board.play(5);
		board.play(2);
		board.play(9);
		board.play(3);

		board.declareWinner();
		verify(console).print( "\n" +
						" x x x\n" +
						" - o -\n" +
						" - - o\n" +
						"\n"
		);
	}

	private void playFullBoardWithNoWinner() {
		board.play(1);
		board.play(2);
		board.play(3);
		board.play(4);
		board.play(5);
		board.play(7);
		board.play(6);
		board.play(9);
		board.play(8);
	}
}
