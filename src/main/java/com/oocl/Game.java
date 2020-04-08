package com.oocl;

public class Game {
    private int[] answer = new int[4];
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

    public void validateRawInputFormat(String rawInput) throws Exception {
        if  (!rawInput.matches("[0-9] [0-9] [0-9] [0-9]")) {
            throw new Exception("Wrong Input，Input again");
        }
    }

    public int[] convertToIntegerArray(String rawInput) {
        String[] stringArray = rawInput.split(" ");
        int[] integerArray = new int[stringArray.length];
        for (int index = 0; index < stringArray.length; index++) {
            integerArray[index] = Integer.parseInt(stringArray[index]);
        }
        return integerArray;
    }
}
