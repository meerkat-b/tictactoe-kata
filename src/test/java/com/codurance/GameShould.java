package com.codurance;

import com.codurance.IO.Console;
import com.codurance.IO.InputHandler;
import com.codurance.gameEngine.MultiPlayerGameEngine;
import com.codurance.gameEngine.SinglePlayerGameEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameShould {

	public static final String REQUEST_GAMETYPE = "[S]ingle Player or [M]ulti-player?";
	public static final String SINGLE_PLAYER = "s";
	public static final String MULTI_PLAYER = "m";

	@Mock
	SinglePlayerGameEngine singlePlayerGameEngine;
	@Mock
	Console console;
	@Mock
	InputHandler inputHandler;
	@Mock
	MultiPlayerGameEngine multiPlayerGameEngine;

	@InjectMocks
	Game game = new Game();

	@Test public void
	ask_if_the_game_is_single_or_multi_player() {
		game.start();

		verify(console).println(REQUEST_GAMETYPE);
	}

	@Test public void
	run_a_single_player_game() {
		given(inputHandler.getGameType()).willReturn(SINGLE_PLAYER);

		game.start();
		verify(inputHandler).getGameType();
		verify(multiPlayerGameEngine, never()).runGame();
		verify(singlePlayerGameEngine).runGame();
	}

	@Test public void
	run_a_multi_player_game() {
		given(inputHandler.getGameType()).willReturn(MULTI_PLAYER);

		game.start();
		verify(inputHandler).getGameType();
		verify(singlePlayerGameEngine, never()).runGame();
		verify(multiPlayerGameEngine).runGame();
	}
}