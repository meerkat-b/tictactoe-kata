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
		given(console.nextLine()).willReturn("h", "poo", "Batman", "s");

		inputHandler.getGameType();

		verify(console, times(4)).println("Please Select : [S]ingle-player or [M]ulti-player?");
		verify(console, times(4)).nextLine();
	}
}
