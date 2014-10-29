package com.codurance;

import com.codurance.IO.InputHandler;
import com.codurance.gameEngine.Board;
import com.codurance.players.HumanPlayer;
import com.codurance.players.Player;
import org.junit.Before;
import org.junit.Test;

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
		board = mock(Board.class);
		humanPlayer = new HumanPlayer(inputHandler);
	}

	@Test public void
	play_the_users_play_onto_the_board() {
		given(inputHandler.getPlayFor(board)).willReturn(FIVE);
		humanPlayer.play(board);
		verify(board).play(FIVE);
	}
}
