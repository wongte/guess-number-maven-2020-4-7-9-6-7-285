package com.oocl;

public class Game {
    private static final String SEPARATOR = " ";
    public static final int NUMBER_OF_TOTAL_ROUND = 6;
    public static final int LENGTH_OF_GAME = 4;
    public static final int UPPER_BOUND_OF_INPUT_NUMBER = 9; // User can only input [0, UPPER_BOUND_OF_INPUT_NUMBER]

    private int[] answer;
    private GameIO gameIO;
    private GameProcess gameProcess;
    private GameInputFormatter validator;

    public Game(GameIO gameIO, GameInputFormatter validator, AnswerGenerator answerGenerator) {
        this.gameIO = gameIO;
        this.validator = validator;
        this.answer = answerGenerator.generateAnswer();
        this.gameProcess = new GameProcess(NUMBER_OF_TOTAL_ROUND);
    }

    private int getNumberOfCorrectNumber(int[] guess) {
        int numberOfCorrectNumber = 0;
        for (int eachGuess : guess) {
            for (int eachAnswer : this.answer) {
                numberOfCorrectNumber += eachGuess == eachAnswer ? 1 : 0;
            }
        }
        return numberOfCorrectNumber;
    }

    private int getNumberOfCorrectPosition(int[] guess) {
        int numberOfCorrectPosition = 0;
        for (int index = 0; index < answer.length; index++) {
            numberOfCorrectPosition += answer[index] == guess[index] ? 1 : 0;
        }
        return numberOfCorrectPosition;
    }

    private String generateResultOutput(int numberOfCorrectNumber, int numberOfCorrectPosition) {
        int b = numberOfCorrectNumber - numberOfCorrectPosition;
        return String.format("%dA%dB", numberOfCorrectPosition, b);
    }

    public String checkResult(int[] guess) {
        int numberOfCorrectNumber = this.getNumberOfCorrectNumber(guess);
        int numberOfCorrectPosition = this.getNumberOfCorrectPosition(guess);
        return this.generateResultOutput(numberOfCorrectNumber, numberOfCorrectPosition);
    }

    public void startGame() {
        String victoryResult = String.format("%dA0B", LENGTH_OF_GAME);
        boolean isVictory = false;
        gameIO.displayResultToConsole("Start Game");
        while (!isVictory && !gameProcess.isGameOver()) {
            String inputFromConsole = gameIO.readInputFromConsole();
            try {
                int[] guess = validator.validateAndConvertIntegerArray(inputFromConsole);
                String result = this.checkResult(guess);
                gameIO.displayResultToConsole(result);
                isVictory = result.equals(victoryResult);
            } catch (InvalidInputException e) {
                gameIO.displayResultToConsole(e.getMessage());
            }
            gameProcess.nextRound();
        }
        gameIO.displayResultToConsole("End Game");
    }
}
