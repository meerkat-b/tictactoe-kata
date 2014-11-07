package com.codurance;

import com.codurance.io.Console;
import com.codurance.io.InputHandler;
import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.Position;
import com.codurance.players.HumanPlayer;
import com.codurance.players.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HumanPlayerShould {

	public static final int FIVE = 5;
	private Player humanPlayer;
	private Board board;
	private InputHandler inputHandler;

	@Before
	public void initialise() {
		inputHandler = mock(InputHandler.class);
		board = new Board(new Console());
		humanPlayer = new HumanPlayer(inputHandler);
	}

	@Test public void
	play_the_users_play_onto_the_board() {
		given(inputHandler.getPlayFor(board)).willReturn(FIVE);
		humanPlayer.play(board);

		ArrayList expectedSpaces = new ArrayList<Integer>() {{
			add(1);
			add(2);
			add(3);
			add(4);
			add(6);
			add(7);
			add(8);
			add(9);
		}};

		ArrayList<Integer> remainingSpaces = new ArrayList();
		for (Position position : board.remainingSpaces()) {
			remainingSpaces.add(position.value);
		}

		assertThat(remainingSpaces, is(expectedSpaces));
	}
}
