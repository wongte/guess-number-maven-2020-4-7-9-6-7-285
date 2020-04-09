package com.oocl;

import java.util.*;

public class Game {
    private static final String SEPARATOR = " ";
    private GameIO gameIO;
    public static final int NUMBER_OF_TOTAL_ROUND = 6;
    public static final int LENGTH_OF_GAME = 4;
    public static final int UPPER_BOUND_OF_INPUT_NUMBER = 9; // User can only input [0, UPPER_BOUND_OF_INPUT_NUMBER]

    private int[] answer = new int[LENGTH_OF_GAME];
    private int remainingRound;

    public Game(GameIO gameIO) {
        this.gameIO = gameIO;
    }

    public Game() {
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
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

    private void validateRawInputFormat(String rawInput) throws InvalidInputException {
        if (!rawInput.matches("[\\d\\s]+")) {
            throw new InvalidInputException();
        }
        if (rawInput.split(SEPARATOR).length < LENGTH_OF_GAME) {
            throw new InvalidInputException();
        }
    }

    private int[] convertToIntegerArray(String rawInput) {
        String[] stringArray = rawInput.split(SEPARATOR);
        int[] integerArray = new int[stringArray.length];
        for (int index = 0; index < stringArray.length; index++) {
            integerArray[index] = Integer.parseInt(stringArray[index]);
        }
        return integerArray;
    }

    private void validateUniqueNumberInGuess(int[] guess) throws InvalidInputException {
        for (int sourceIndex = 0; sourceIndex < guess.length; sourceIndex++) {
            for (int targetIndex = sourceIndex + 1; targetIndex < guess.length; targetIndex++) {
                if (guess[sourceIndex] == guess[targetIndex]) {
                    throw new InvalidInputException();
                }
            }
        }
    }

    private void validateNumbersInRange(int[] guess) throws InvalidInputException {
        for (int eachGuess : guess) {
            if (eachGuess > UPPER_BOUND_OF_INPUT_NUMBER || eachGuess < 0) throw new InvalidInputException();
        }
    }

    public int[] validateAndConvertIntegerArray(String rawInput) throws InvalidInputException {
        validateRawInputFormat(rawInput);
        int[] guess = convertToIntegerArray(rawInput);
        validateUniqueNumberInGuess(guess);
        validateNumbersInRange(guess);
        return guess;
    }

    public boolean isGameOver() {
        return this.remainingRound <= 0;
    }

    public void setRemainingRound(int remainingRound) {
        this.remainingRound = remainingRound;
    }

    private void generateAnswer() {
        List<Integer> randomNumbers = new ArrayList<>();
        while (randomNumbers.size() < LENGTH_OF_GAME) {
            int generatedNumber = new Random().nextInt(UPPER_BOUND_OF_INPUT_NUMBER + 1);
            if (!randomNumbers.contains(generatedNumber)) {
                randomNumbers.add(generatedNumber);
            }
        }
        for (int index = 0; index < randomNumbers.size(); index++) {
            this.answer[index] = randomNumbers.get(index);
        }
    }

    public void initializeGameData() {
        this.generateAnswer();
        this.remainingRound = NUMBER_OF_TOTAL_ROUND;
    }

    public int getRemainingRound() {
        return remainingRound;
    }

    private void nextRound() {
        this.remainingRound--;
    }

    public void startGame() {
        String victoryResult = String.format("%dA0B", LENGTH_OF_GAME);
        boolean isVictory = false;
        this.initializeGameData();
        gameIO.displayResultToConsole("Start Game");
        while (!isVictory && !this.isGameOver()) {
            String inputFromConsole = gameIO.readInputFromConsole();
            try {
                int[] guess = this.validateAndConvertIntegerArray(inputFromConsole);
                String result = this.checkResult(guess);
                gameIO.displayResultToConsole(result);
                isVictory = result.equals(victoryResult);
            } catch (InvalidInputException e) {
                gameIO.displayResultToConsole(e.getMessage());
            }
            this.nextRound();
        }
        gameIO.displayResultToConsole("End Game");
    }
}
