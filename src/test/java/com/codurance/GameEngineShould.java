package com.codurance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameEngineShould {

	@Mock private Board board;

	@InjectMocks
	GameEngine gameEngine = new GameEngine();

	@Test public void
	make_a_mark_on_a_board() {
		gameEngine.runGame(board);
		verify(board).mark();
	}

	@Test public void
	not_make_more_than_nine_marks_on_a_board() {
		gameEngine.runGame(board);
		verify(board, atMost(9)).mark();
	}
}
