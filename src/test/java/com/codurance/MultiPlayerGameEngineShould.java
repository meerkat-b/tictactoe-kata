package com.codurance;

import com.codurance.IO.Console;
import com.codurance.IO.InputHandler;
import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.MultiPlayerGameEngine;
import com.codurance.players.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MultiPlayerGameEngineShould {

	private Board board;
	private MultiPlayerGameEngine multiPlayerGameEngine;
	private HumanPlayer humanPlayer;
	private HumanPlayer anotherHumanPlayer;
	private Console console;
	private InputHandler inputHandler;

	@Before
	public void initialise() {
		board = mock(Board.class);
		humanPlayer = mock(HumanPlayer.class);
		anotherHumanPlayer = mock(HumanPlayer.class);

		multiPlayerGameEngine = new MultiPlayerGameEngine
				(board, humanPlayer, anotherHumanPlayer);
	}

	@Test public void
	print_the_board_before_each_turn() {
		given(board.isInPlay()).willReturn(true, true, true, true, false);
		multiPlayerGameEngine.runGame();

		verify(board, times(4)).printBoard();
	}

	@Test public void
	alternate_between_players_turns_while_the_board_is_in_play() {
		given(board.isInPlay()).willReturn(true, true, true, true, true, true, false);
		multiPlayerGameEngine.runGame();

		verify(humanPlayer, times(3)).play(board);
		verify(anotherHumanPlayer, times(3)).play(board);
	}

	@Test public void
	declare_the_winner_of_a_game() {
		given(board.isInPlay()).willReturn(true,true,true,true,true, false);
		multiPlayerGameEngine.runGame();

		verify(board, times(1)).declareWinner();
	}
}
