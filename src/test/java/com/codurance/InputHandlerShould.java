package com.codurance;

import com.codurance.io.Console;
import com.codurance.io.InputHandler;
import com.codurance.gameEngine.Board;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class InputHandlerShould {

	private final String VALID_REMAINING_SPACE = "8";
	private final ArrayList EIGHT_AND_NINE = new ArrayList<Integer>() {{
		add(8);
		add(9);
	}};

	private Console console;
	private InputHandler inputHandler;
	private Board board;

	@Before
	public void initialise() {
		console = mock(Console.class);
		board = mock(Board.class);

		given(board.remainingSpaces()).willReturn(EIGHT_AND_NINE);
		given(console.nextLine()).willReturn(VALID_REMAINING_SPACE);

		inputHandler = new InputHandler(console);
	}

	@Test public void
	return_single_player_as_a_game_type() {
		given(console.nextLine()).willReturn("s");

		assertThat(inputHandler.getGameType(), is("s"));
	}

	@Test public void
	return_multi_player_as_a_game_type() {
		given(console.nextLine()).willReturn("m");

		assertThat(inputHandler.getGameType(), is("m"));
	}

	@Test public void
	keep_asking_for_gametypes_until_a_valid_input_is_provided() {
		given(console.nextLine()).willReturn("h", "poo", "Bat man", "s");

		inputHandler.getGameType();

		verify(console, times(4)).println("Please Select : [S]ingle-player or [M]ulti-player?");
		verify(console, times(4)).nextLine();
	}

	@Test public void
	allow_the_player_to_pick_themselves_going_first() {
		given(console.nextLine()).willReturn("1");

		assertThat(inputHandler.getTurnOrder(), is(1));
	}

	@Test public void
	allow_the_player_to_pick_themselves_going_second() {
		given(console.nextLine()).willReturn("2");

		assertThat(inputHandler.getTurnOrder(), is(2));
	}

	@Test public void
	keep_asking_for_the_turn_order_until_a_valid_input_is_provided() {
		given(console.nextLine()).willReturn("farts", "Green arrow", "1");

		inputHandler.getTurnOrder();

		verify(console, times(3)).println("Please Select : Would you like to go [1]st or [2]nd?");
		verify(console, times(3)).nextLine();
	}

	@Test public void
	print_the_remaining_board_spaces_when_asking_for_a_play() {
		inputHandler.getPlayFor(board);

		verify(board).printRemainingSpaces();
	}

	@Test public void
	ask_the_player_to_select_a_remaining_space() {
		inputHandler.getPlayFor(board);

		verify(console).println("Please select a space to mark");
	}

	@Test public void
	get_the_players_desired_position_for_the_turn() {
		assertThat(inputHandler.getPlayFor(board), is(8));
	}

	@Test public void
	keep_asking_for_plays_until_the_player_picks_a_valid_remaining_space() {
		given(console.nextLine()).willReturn("1","3","7", VALID_REMAINING_SPACE);

		inputHandler.getPlayFor(board);
		verify(board, times(4)).printRemainingSpaces();
	}

	@Test public void
	not_allow_you_to_input_a_move_other_than_a_number() {
		given(console.nextLine()).willReturn("two", "aquaman","8");
		inputHandler.getPlayFor(board);

		verify(console, times(2)).println("That's not a valid input D:<");
		verify(console, times(3)).nextLine();
	}
}
