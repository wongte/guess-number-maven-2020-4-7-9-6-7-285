package com.oocl;

public class GameInputFormatter {
    private String separator;
    private int lengthOfGame, upperBoundOfInputNumber;

    public GameInputFormatter(String separator, int lengthOfGame, int upperBoundOfInputNumber) {
        this.separator = separator;
        this.lengthOfGame = lengthOfGame;
        this.upperBoundOfInputNumber = upperBoundOfInputNumber;
    }

    private void validateRawInputFormat(String rawInput) throws InvalidInputException {
        if (!rawInput.matches("[\\d\\s]+")) {
            throw new InvalidInputException();
        }
        if (rawInput.split(separator).length < lengthOfGame) {
            throw new InvalidInputException();
        }
    }

    private int[] convertToIntegerArray(String rawInput) {
        String[] stringArray = rawInput.split(separator);
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
            if (eachGuess > upperBoundOfInputNumber || eachGuess < 0) throw new InvalidInputException();
        }
    }

    public int[] validateAndConvertIntegerArray(String rawInput) throws InvalidInputException {
        validateRawInputFormat(rawInput);
        int[] guess = convertToIntegerArray(rawInput);
        validateUniqueNumberInGuess(guess);
        validateNumbersInRange(guess);
        return guess;
    }
}
