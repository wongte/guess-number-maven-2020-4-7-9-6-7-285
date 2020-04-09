package com.oocl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Console console = new Console();
        RandomAnswerGenerator answerGenerator = new RandomAnswerGenerator(Game.LENGTH_OF_GAME, Game.UPPER_BOUND_OF_INPUT_NUMBER);
        Game game = new Game(console, answerGenerator);
        game.startGame();
    }
}
