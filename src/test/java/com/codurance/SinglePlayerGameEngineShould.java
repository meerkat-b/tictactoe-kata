package com.codurance;

import com.codurance.IO.Console;
import com.codurance.IO.InputHandler;
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

	public static final String REQUEST_TURN_ORDER = "Would you like to go [1]st or [2]nd?";

	private Console console;
	private InputHandler inputHandler;
	private Board board;
	private Player humanPlayer;
	private Player computerPlayer;
	private SinglePlayerGameEngine singlePlayerGameEngine;

	@Before
	public void initialise() {
		console = mock(Console.class);
		inputHandler = mock(InputHandler.class);
		board = mock(Board.class);
		humanPlayer = mock(HumanPlayer.class);
		computerPlayer = mock(ComputerPlayer.class);

		singlePlayerGameEngine = new SinglePlayerGameEngine
				(board, humanPlayer, computerPlayer, console, inputHandler);
	}

	@Test public void
	ask_if_the_player_wants_to_go_first() {
		singlePlayerGameEngine.runGame();

		verify(console).println(REQUEST_TURN_ORDER);
	}

	@Test public void
	set_the_player_turn_order_with_player_going_first() {
		given(inputHandler.getTurnOrder()).willReturn(1);
		given(board.isInPlay()).willReturn(true, true, false);
		singlePlayerGameEngine.runGame();

		InOrder turnOrder = inOrder(humanPlayer, computerPlayer);

		turnOrder.verify(humanPlayer).play(board);
		turnOrder.verify(computerPlayer).play(board);
	}

	@Test public void
	set_the_player_turn_order_with_player_going_second() {
		given(inputHandler.getTurnOrder()).willReturn(2);
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

		verify(board, times(3)).print();
	}

	@Test public void
	alternate_between_players_turns_while_the_board_is_in_play() {
		given(board.isInPlay()).willReturn(true, true, true, true, false);
		singlePlayerGameEngine.runGame();

		verify(humanPlayer, times(2)).play(board);
		verify(computerPlayer, times(2)).play(board);
	}
}