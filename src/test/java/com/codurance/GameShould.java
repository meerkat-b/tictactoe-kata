package com.codurance;

import com.codurance.IO.Console;
import com.codurance.IO.InputHandler;
import com.codurance.gameEngine.MultiPlayerGameEngine;
import com.codurance.gameEngine.SinglePlayerGameEngine;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class GameShould {

	public static final String REQUEST_GAMETYPE = "[S]ingle-player or [M]ulti-player?";
	public static final String SINGLE_PLAYER = "s";
	public static final String MULTI_PLAYER = "m";

	private SinglePlayerGameEngine singlePlayerGameEngine;
	private Console console;
	private InputHandler inputHandler;
	private MultiPlayerGameEngine multiPlayerGameEngine;
	private Game game;

	@Before
	public void initialise() {
		singlePlayerGameEngine = mock(SinglePlayerGameEngine.class);
		console = mock(Console.class);
		inputHandler = mock(InputHandler.class);
		multiPlayerGameEngine = mock(MultiPlayerGameEngine.class);

		game = new Game(singlePlayerGameEngine, multiPlayerGameEngine, console, inputHandler);
	}

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