package com.codurance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameShould {

    private static final String GREETING = "Hello!";

    @Mock
    Console console;

    @InjectMocks
    Game game = new Game();

    @Test public void
    greet_the_player_when_started() {
        game.start();
        verify(console).println("Hello!");
    }
}