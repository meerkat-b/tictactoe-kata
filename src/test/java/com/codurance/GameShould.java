package com.codurance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameShould {

	public static final Class<HumanPlayer> HUMAN_PLAYER = HumanPlayer.class;
	public static final Class<ComputerPlayer> COMPUTER_PLAYER = ComputerPlayer.class;

	@Mock SinglePlayerGameEngine singlePlayerGameEngine;
	@Mock Console console;
	@Mock InputHandler inputHandler;
	@Mock MultiPlayerGameEngine multiPlayerGameEngine;

	@InjectMocks
	Game game = new Game();

	@Test public void
	ask_if_the_game_is_single_or_multi_player() {
		game.start();

		verify(console).println("[S]ingle Player or [M]ulti-player?");
	}

	@Test public void
	run_a_single_player_game() {
		given(inputHandler.nextInput()).willReturn("s");

		game.start();
		verify(inputHandler).nextInput();
		verify(singlePlayerGameEngine).runGame();
	}

	@Test public void
	run_a_multi_player_game() {
		given(inputHandler.nextInput()).willReturn("m");

		game.start();
		verify(inputHandler).nextInput();
		verify(singlePlayerGameEngine, never()).runGame();
		verify(multiPlayerGameEngine).runGame();
	}
}