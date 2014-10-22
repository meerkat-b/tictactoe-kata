package com.codurance;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameEngineShould {

	private static final int TOP_LEFT = 1;
	private static final int TOP_RIGHT = 3;
	private static final Class<Integer> POSITION = Integer.TYPE;

	@Mock private Board board;
	@Mock private Console console;
	@Mock private InputHandler inputHandler;
	@Mock private ComputerPlayer computerPlayer;

	@InjectMocks
	GameEngine gameEngine = new GameEngine();

	@Test public void
	print_the_game_board() {
		given(board.isInPlay()).willReturn(true, false);

		gameEngine.runGame(board);
		verify(board).print();
	}

	@Test public void
	make_a_mark_on_a_board() {
		given(board.isInPlay()).willReturn(true, false);

		gameEngine.runGame(board);
		verify(board).mark(any(POSITION));
	}

	@Test public void
	ask_the_player_for_their_move() {
		given(board.isInPlay()).willReturn(true, false);

		gameEngine.runGame(board);
		verify(console).println("Please type the number of the place to mark");
	}

	@Test public void
	mark_the_top_left_of_the_board_with_the_players_move() {
		given(inputHandler.getNextMove()).willReturn(TOP_LEFT);
		given(board.isInPlay()).willReturn(true, false);

		gameEngine.runGame(board);
		verify(board).mark(inputHandler.getNextMove());
	}

	@Test public void
	mark_the_top_right_of_the_board_with_the_players_move() {
		given(inputHandler.getNextMove()).willReturn(TOP_RIGHT);
		given(board.isInPlay()).willReturn(true, false);

		gameEngine.runGame(board);
		verify(board).mark(inputHandler.getNextMove());
	}

	@Test public void
	take_the_computers_turn_after_the_players_turn() {
		given(board.isInPlay()).willReturn(true, true, true, false);
		given(computerPlayer.getNextMove()).willReturn(TOP_LEFT);

		gameEngine.runGame(board);
		verify(board).mark(TOP_LEFT);
	}

	@Test public void
	keep_playing_turns_until_the_board_is_no_longer_in_play() {
		given(board.isInPlay()).willReturn(true,true,true,true,true,true,false);

		gameEngine.runGame(board);
		verify(board, times(6)).print();
		verify(computerPlayer, times(3)).getNextMove();
		verify(inputHandler, times(3)).getNextMove();
	}

	@Ignore //this may not be the responsibility of the game engine but rather the board
	@Test public void
	not_make_more_than_nine_marks_on_a_board() {
		given(board.isInPlay()).willReturn
				(true, true, true, true, true, true, true, true, true, true, false);
		gameEngine.runGame(board);
		verify(board, atMost(9)).mark(any(POSITION));
	}
}
