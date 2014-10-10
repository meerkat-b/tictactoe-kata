package com.codurance;

public class GameLauncher {

    private Console console = new Console();
    private Game game = new Game();

    public void start() {
        console.printGreeting();
        console.promptNewGame();
        game.start();
    }
}
