package com.oocl;

public class GameResultChecker {
    public static final String DISPLAY_FORMAT = "%dA%dB";
    int[] answer;

    public GameResultChecker(int[] answer) {
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

    private String getDisplayResult(int valueOfA, int valueOfB) {
        return String.format(DISPLAY_FORMAT, valueOfA, valueOfB);
    }

    public String checkResult(int[] guess) {
        int numberOfCorrectNumber = this.getNumberOfCorrectNumber(guess);
        int numberOfCorrectPosition = this.getNumberOfCorrectPosition(guess);
        return this.getDisplayResult(numberOfCorrectNumber, numberOfCorrectPosition - numberOfCorrectNumber);
    }

    public boolean isVictory(String actualResult) {
        String victoryResult = getDisplayResult(answer.length, 0);
        return victoryResult.equals(actualResult);
    }
}
