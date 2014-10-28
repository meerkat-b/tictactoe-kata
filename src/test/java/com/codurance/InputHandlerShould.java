package com.codurance;

import com.codurance.IO.Console;
import com.codurance.IO.InputHandler;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class InputHandlerShould {

	private Console console;
	private InputHandler inputHandler;

	@Before
	public void initialise() {
		console = mock(Console.class);

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
}
