package com.oocl;

import com.oocl.answer.RandomAnswerGenerator;
import com.oocl.io.Console;

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
