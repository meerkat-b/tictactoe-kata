package com.codurance;

import com.codurance.IO.Console;
import com.codurance.gameEngine.Board;
import com.codurance.players.ComputerPlayer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
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

		computerPlayer = new ComputerPlayer();
	}

	@Test public void
	complete_a_row_when_possible() {
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
	complete_a_column_when_possible() {
		board.play(1);
		board.play(9);
		board.play(4);
		board.play(2);
		computerPlayer.play(board);

		ArrayList expectedSpaces = new ArrayList<Integer>(){{
			add(3);
			add(5);
			add(6);
			add(8);
		}};
		assertThat(board.remainingSpaces(), is(expectedSpaces));
	}

//	@Test public void
//	intercept_the_opponent_winning_when_no_winning_move_is_available() {
//		board.play(1); //X
//		board.play(3); //O
//		board.play(2); //X
//		board.play()
//	}
}
