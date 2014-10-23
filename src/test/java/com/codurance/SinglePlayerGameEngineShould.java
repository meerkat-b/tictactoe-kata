package com.codurance;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SinglePlayerGameEngineShould {

	@Mock Console console;

	@InjectMocks
	SinglePlayerGameEngine singlePlayerGameEngine = new SinglePlayerGameEngine();

	@Test public void
	ask_if_the_player_would_like_to_go_first() {
		singlePlayerGameEngine.runGame();

		verify(console).println("Would you like to go [1]st or [2]nd?");
	}
}