package com.codurance;

import com.codurance.IO.Console;
import com.codurance.IO.InputHandler;
import com.codurance.gameEngine.Board;
import com.codurance.gameEngine.SinglePlayerGameEngine;
import com.codurance.players.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SinglePlayerGameEngineShould {

	public static final String REQUEST_TURN_ORDER = "Would you like to go [1]st or [2]nd?";

	@Mock Console console;
	@Mock InputHandler inputHandler;
	@Mock Board board;
	@Mock Player playerOne;
	@Mock Player playerTwo;

	@InjectMocks
	SinglePlayerGameEngine singlePlayerGameEngine = new SinglePlayerGameEngine();

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

		verify(playerOne, times(2)).play(board);
		verify(playerTwo, times(2)).play(board);
	}
}