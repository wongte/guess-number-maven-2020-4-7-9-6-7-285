package com.oocl;

public class Game {
    public static final int NUMBER_OF_TOTAL_ROUND = 6;
    public static final int LENGTH_OF_GAME = 4;

    private int[] answer = new int[LENGTH_OF_GAME];
    private int remainingRound;

    public Game() {
        this.remainingRound = NUMBER_OF_TOTAL_ROUND;
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

    private void validateRawInputFormat(String rawInput) throws Exception {
        if (!rawInput.matches("[0-9] [0-9] [0-9] [0-9]")) {
            throw new Exception("Wrong Input，Input again");
        }
    }

    private int[] convertToIntegerArray(String rawInput) {
        String[] stringArray = rawInput.split(" ");
        int[] integerArray = new int[stringArray.length];
        for (int index = 0; index < stringArray.length; index++) {
            integerArray[index] = Integer.parseInt(stringArray[index]);
        }
        return integerArray;
    }

    private void validateUniqueNumberInGuess(int[] guess) throws Exception {
        for (int sourceIndex = 0; sourceIndex < guess.length; sourceIndex++) {
            for (int targetIndex = sourceIndex + 1; targetIndex < guess.length; targetIndex++) {
                if (guess[sourceIndex] == guess[targetIndex]) {
                    throw new Exception("Wrong Input，Input again");
                }
            }
        }

    }

    public int[] validateAndConvertIntgerArray(String rawInput) throws Exception {
        validateRawInputFormat(rawInput);
        int[] guess = convertToIntegerArray(rawInput);
        validateUniqueNumberInGuess(guess);
        return guess;
    }

    public boolean isGameOver() {
        return this.remainingRound <= 0;
    }

    public void setRemainingRound(int remainingRound) {
        this.remainingRound = remainingRound;
    }
}
