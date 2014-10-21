package com.codurance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
		gameEngine.runGame(board);
		verify(board).print();
	}

	@Test public void
	make_a_mark_on_a_board() {
		gameEngine.runGame(board);
		verify(board).mark(any(POSITION));
	}

	@Test public void
	not_make_more_than_nine_marks_on_a_board() {
		gameEngine.runGame(board);
		verify(board, atMost(9)).mark(any(POSITION));
	}

	@Test public void
	ask_the_player_for_their_move() {
		gameEngine.runGame(board);
		verify(console).println("Please type the number of the place to mark");
	}

	@Test public void
	mark_the_top_left_of_the_board_with_the_players_move() {
		given(inputHandler.getNextMove()).willReturn(TOP_LEFT);

		gameEngine.runGame(board);
		verify(board).mark(inputHandler.getNextMove());
	}

	@Test public void
	mark_the_top_right_of_the_board_with_the_players_move() {
		given(inputHandler.getNextMove()).willReturn(TOP_RIGHT);

		gameEngine.runGame(board);
		verify(board).mark(inputHandler.getNextMove());
	}

	@Test public void
	allow_the_computer_to_take_a_turn() {
		gameEngine.runGame(board);
		verify(computerPlayer).getNextMove();
	}
}
