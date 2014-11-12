package com.codurance;

import com.codurance.gameEngine.Board;
import com.codurance.io.InputHandler;
import com.codurance.players.Player;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class GameShould {

	private Board board;
	private Player player1;
	private Player player2;
	private Game game;
	private InputHandler inputHandler;

	@Before
	public void initialise() {
		inputHandler = mock(InputHandler.class);
		board = mock(Board.class);
		player1 = mock(Player.class);
		player2 = mock(Player.class);

		game = new Game
				(board, player1, player2);
	}

	@Test public void
	make_player_ones_move_before_player_twos_move() {
		given(board.isInPlay()).willReturn(true, true, false);
		game.runGame();

		InOrder turnOrder = inOrder(player1, player2);

		turnOrder.verify(player1).play(board);
		turnOrder.verify(player2).play(board);
	}

	@Test public void
	print_the_board_before_each_turn() {
		given(board.isInPlay()).willReturn(true, true, true, false);
		game.runGame();

		verify(board, times(3)).printBoard();
	}

	@Test public void
	alternate_between_players_turns_while_the_board_is_in_play() {
		given(board.isInPlay()).willReturn(true, true, true, true, false);
		game.runGame();

		verify(player1, times(2)).play(board);
		verify(player2, times(2)).play(board);
	}

	@Test public void
	declare_the_winner_of_a_game() {
		given(board.isInPlay()).willReturn(true,true,true,true,true, false);
		game.runGame();

		verify(board, times(1)).declareWinner();
	}
}