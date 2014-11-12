package com.codurance;

import com.codurance.io.Console;
import com.codurance.io.InputHandler;
import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.SinglePlayerGameEngine;
import com.codurance.players.ComputerPlayer;
import com.codurance.players.HumanPlayer;
import com.codurance.players.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class SinglePlayerGameEngineShould {

	private Board board;
	private Player humanPlayer;
	private Player computerPlayer;
	private SinglePlayerGameEngine singlePlayerGameEngine;

	@Before
	public void initialise() {
		board = mock(Board.class);
		humanPlayer = mock(HumanPlayer.class);
		computerPlayer = mock(ComputerPlayer.class);

		singlePlayerGameEngine = new SinglePlayerGameEngine
				(board, humanPlayer, computerPlayer);
	}

	@Test public void
	set_the_player_turn_order_with_player_going_first() {
		given(humanPlayer.wantToGoFirst()).willReturn(true);
		given(board.isInPlay()).willReturn(true, true, false);
		singlePlayerGameEngine.runGame();

		InOrder turnOrder = inOrder(humanPlayer, computerPlayer);

		turnOrder.verify(humanPlayer).play(board);
		turnOrder.verify(computerPlayer).play(board);
	}

	@Test public void
	set_the_player_turn_order_with_player_going_second() {
		given(humanPlayer.wantToGoFirst()).willReturn(false);
		given(board.isInPlay()).willReturn(true, true, false);
		singlePlayerGameEngine.runGame();

		InOrder turnOrder = inOrder(computerPlayer, humanPlayer);

		turnOrder.verify(computerPlayer).play(board);
		turnOrder.verify(humanPlayer).play(board);
	}

	@Test public void
	print_the_board_before_each_turn() {
		given(board.isInPlay()).willReturn(true, true, true, false);
		singlePlayerGameEngine.runGame();

		verify(board, times(3)).printBoard();
	}

	@Test public void
	alternate_between_players_turns_while_the_board_is_in_play() {
		given(board.isInPlay()).willReturn(true, true, true, true, false);
		singlePlayerGameEngine.runGame();

		verify(humanPlayer, times(2)).play(board);
		verify(computerPlayer, times(2)).play(board);
	}

	@Test public void
	declare_the_winner_of_a_game() {
		given(board.isInPlay()).willReturn(true,true,true,true,true, false);
		singlePlayerGameEngine.runGame();

		verify(board, times(1)).declareWinner();
	}
}