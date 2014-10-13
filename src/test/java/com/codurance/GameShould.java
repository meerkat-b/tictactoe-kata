package com.codurance;

import org.junit.Ignore;
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

    @Test public void
    print_an_empty_board_when_started() {
        game.start();
        verify(console).print( "\n" +
                "- - - \n" +
                "- - - \n" +
                "- - - \n"
        );
    }

    @Test public void
    mark_x_to_top_left_of_board() {
        game.start();
        game.makeMove("x", new Coordinate(1,3));
        verify(console).print( "\n" +
                        "x - - \n" +
                        "- - - \n" +
                        "- - - \n"
        );
    }

    @Ignore
    @Test public void
    mark_x_to_top_left_and_o_to_top_right (){
        game.start();
        game.makeMove("x", new Coordinate(1,3));
        game.makeMove("o", new Coordinate(3,3));
        verify(console).print("\n" +
                        "x - o \n" +
                        "- - - \n" +
                        "- - - \n"
        );
    }


}