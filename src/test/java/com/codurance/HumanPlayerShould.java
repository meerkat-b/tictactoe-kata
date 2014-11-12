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
	private Console console;

	@Before
	public void initialise() {
		inputHandler = mock(InputHandler.class);
		console = mock(Console.class);
		board = new Board(console);
		humanPlayer = new HumanPlayer(inputHandler);
	}

	@Test public void
	play_the_users_play_onto_the_board() {
		given(inputHandler.getPlayFor(board)).willReturn(FIVE);
		humanPlayer.play(board);
		verify(console).println("Move played at position [5]");
	}
}
