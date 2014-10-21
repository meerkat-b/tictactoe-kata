package com.codurance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameShould {

    @Mock
    private Console console;
	@Mock
	private GameEngine gameEngine;

    @InjectMocks
    Game game = new Game();

	@Test public void
	run_the_game_engine_with_a_board() {
		game.start();
		verify(gameEngine).runGame(new Board());
	}
}