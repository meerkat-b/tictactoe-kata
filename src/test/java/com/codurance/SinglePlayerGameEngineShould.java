package com.codurance;

import com.codurance.IO.Console;
import com.codurance.IO.InputHandler;
import com.codurance.gameEngine.SinglePlayerGameEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SinglePlayerGameEngineShould {

	public static final String REQUEST_TURN_ORDER = "Would you like to go [1]st or [2]nd?";

	@Mock Console console;
	@Mock InputHandler inputHandler;
	@Mock

	@InjectMocks
	SinglePlayerGameEngine singlePlayerGameEngine = new SinglePlayerGameEngine();

	@Test public void
	ask_if_the_player_would_like_to_go_first() {
		singlePlayerGameEngine.runGame();

		verify(console).println(REQUEST_TURN_ORDER);
	}
}