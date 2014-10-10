package com.codurance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameLauncherShould {

    @Mock
    Console console;
    @Mock
    Game game;

    @InjectMocks
    GameLauncher gameLauncher = new GameLauncher();

    @Test public void
    print_a_greeting_to_the_player() {
        gameLauncher.start();
        verify(console).printGreeting();
    }

    @Test public void
    ask_a_player_to_start_a_new_game() {
        gameLauncher.start();
        verify(console).promptNewGame();
    }

    @Test public void
    start_a_new_game() {
        gameLauncher.start();
        verify(game).start();
    }
}
