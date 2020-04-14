package com.oocl;

import com.oocl.answer.AnswerGenerator;
import com.oocl.exception.InvalidInputException;
import com.oocl.io.GameIO;

public class Game {
    private static final String SEPARATOR = " ";
    private static final String START_GAME_MESSAGE = "Start Game";
    private static final String END_GAME_MESSAGE = "End Game";

    public static final int NUMBER_OF_TOTAL_ROUND = 6;
    public static final int LENGTH_OF_GAME = 4;
    public static final int UPPER_BOUND_OF_INPUT_NUMBER = 9; // User can only input [0, UPPER_BOUND_OF_INPUT_NUMBER]

    private GameIO gameIO;
    private GameProcess gameProcess;
    private GameInputFormatter gameInputFormatter;
    private GameResultChecker gameResultChecker;

    public Game(GameIO gameIO, AnswerGenerator answerGenerator) {
        this.gameIO = gameIO;
        this.gameInputFormatter = new GameInputFormatter(SEPARATOR, LENGTH_OF_GAME, UPPER_BOUND_OF_INPUT_NUMBER);
        this.gameProcess = new GameProcess(NUMBER_OF_TOTAL_ROUND);
        this.gameResultChecker = new GameResultChecker(answerGenerator.generateAnswer());
    }

    public void startGame() {
        gameIO.displayResultToConsole(START_GAME_MESSAGE);
        String lastRoundResult = null;
        while (!isVictory(lastRoundResult) && !gameProcess.isGameOver()) {
            String inputFromConsole = gameIO.readInputFromConsole();
            try {
                int[] guess = gameInputFormatter.convert(inputFromConsole);
                lastRoundResult = gameResultChecker.checkResult(guess);
                gameIO.displayResultToConsole(lastRoundResult);
            } catch (InvalidInputException e) {
                gameIO.displayResultToConsole(e.getMessage());
            }
            gameProcess.nextRound();
        }
        gameIO.displayResultToConsole(END_GAME_MESSAGE);
    }

    private boolean isVictory(String actualResult) {
        String victoryResult = String.format("%dA0B", LENGTH_OF_GAME);
        return victoryResult.equals(actualResult);
    }
}
