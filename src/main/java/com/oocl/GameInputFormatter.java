package com.oocl;

import com.oocl.exception.InvalidInputException;

import java.util.Arrays;

public class GameInputFormatter {
    private static final String DIGIT_AND_SPACE_PATTERN = "[\\d\\s]+";
    private String separator;
    private int lengthOfGame, upperBoundOfInputNumber;

    public GameInputFormatter(String separator, int lengthOfGame, int upperBoundOfInputNumber) {
        this.separator = separator;
        this.lengthOfGame = lengthOfGame;
        this.upperBoundOfInputNumber = upperBoundOfInputNumber;
    }

    private void validateRawInputFormat(String rawInput) throws InvalidInputException {
        if (!rawInput.matches(DIGIT_AND_SPACE_PATTERN)) {
            throw new InvalidInputException();
        }
        if (rawInput.split(separator).length != lengthOfGame) {
            throw new InvalidInputException();
        }
    }

    private int[] convertToIntegerArray(String rawInput) {
        String[] stringArray = rawInput.split(separator);
        return Arrays.stream(stringArray).mapToInt(Integer::parseInt).toArray();
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
            if (eachGuess > upperBoundOfInputNumber || eachGuess < 0) throw new InvalidInputException();
        }
    }

    public int[] convert(String rawInput) throws InvalidInputException {
        validateRawInputFormat(rawInput);
        int[] guess = convertToIntegerArray(rawInput);
        validateUniqueNumberInGuess(guess);
        validateNumbersInRange(guess);
        return guess;
    }
}
