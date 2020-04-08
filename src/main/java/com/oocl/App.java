package com.oocl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Console console = new Console();
        Game game = new Game(console);
        game.startGame();
    }
}
