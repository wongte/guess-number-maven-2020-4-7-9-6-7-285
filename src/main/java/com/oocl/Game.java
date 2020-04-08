package com.oocl;

import java.util.Arrays;

public class Game {
    private int[] answer = new int[4];
    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

    public int getNumberOfCorrectNumber(int[] guess) {
        int numberOfCorrectNumber = 0;
        for (int eachGuess : guess) {
            for (int eachAnswer : this.answer) {
                numberOfCorrectNumber += eachGuess == eachAnswer ? 1 : 0;
            }
        }
        return numberOfCorrectNumber;
    }
}
