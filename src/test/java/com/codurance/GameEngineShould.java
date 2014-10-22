package com.codurance;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
		verify(board).mark(anyString(), any(POSITION));
	}

	@Test public void
	ask_the_player_for_their_move() {
		given(board.isInPlay()).willReturn(true, false);

		gameEngine.runGame(board);
		verify(console).println("Please type the number of the place to mark");
	}

	@Test public void
	mark_the_top_left_of_the_board_with_the_players_move() {
		given(inputHandler.getNextPosition()).willReturn(TOP_LEFT);
		given(board.isInPlay()).willReturn(true, false);

		gameEngine.runGame(board);
		verify(board).mark(anyString(), eq(TOP_LEFT));
	}

	@Test public void
	mark_the_top_right_of_the_board_with_the_players_move() {
		given(inputHandler.getNextPosition()).willReturn(TOP_RIGHT);
		given(board.isInPlay()).willReturn(true, false);

		gameEngine.runGame(board);
		verify(board).mark(anyString(), eq(TOP_RIGHT));
	}

	@Test public void
	take_the_computers_turn_after_the_players_turn() {
		given(board.isInPlay()).willReturn(true, true, true, false);
		given(computerPlayer.getNextPosition()).willReturn(TOP_LEFT);

		gameEngine.runGame(board);
		verify(board).mark(anyString(), eq(TOP_LEFT));
	}

	@Test public void
	keep_playing_turns_until_the_board_is_no_longer_in_play() {
		given(board.isInPlay()).willReturn(true,true,true,true,true,true,false);

		gameEngine.runGame(board);
		verify(board, times(6)).print();
		verify(computerPlayer, times(3)).getNextPosition();
		verify(inputHandler, times(3)).getNextPosition();
	}

	@Test public void
	mark_an_x_for_one_player_and_an_o_for_the_other() {
		given(board.isInPlay()).willReturn(true,true,true,true,true,true,true,true,false);
		gameEngine.runGame(board);
		verify(board, times(4)).mark(eq("X"), any(POSITION));
		verify(board, times(4)).mark(eq("O"), any(POSITION));
	}
}
