package com.codurance;

public class Game {

    public static final String HELLO = "Hello!";
    private Console console;

    public void start() {
        greetPlayer();
    }

    private void greetPlayer() {
        console.println(HELLO);
    }
}
