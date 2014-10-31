package com.codurance;

import com.codurance.IO.Console;
import com.codurance.gameEngine.Board;
import com.codurance.players.ComputerPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ComputerPlayerShould {

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
		board.play(1);
		board.play(4);
		board.play(2);
		board.play(9);
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
		board.play(9);
		board.play(1);
		board.play(5);
		board.play(4);
		board.play(3);
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
		board.play(1);
		board.play(2);
		board.play(4);
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
		board.play(1);
		board.play(5);
		board.play(2);
		board.play(9);
		computerPlayer.play(board);

		verify(console).println("Computer has chosen position [3]");
	}
}
