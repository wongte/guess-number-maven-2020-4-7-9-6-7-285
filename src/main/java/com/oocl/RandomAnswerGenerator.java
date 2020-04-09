package com.oocl;

import java.util.*;

public class RandomAnswerGenerator implements AnswerGenerator {
    private int lengthOfGame;
    private int upperBoundOfInputNumber;

    public RandomAnswerGenerator(int lengthOfGame, int upperBoundOfInputNumber) {
        this.lengthOfGame = lengthOfGame;
        this.upperBoundOfInputNumber = upperBoundOfInputNumber;
    }

    @Override
    public int[] generateAnswer() {
        int[] answer;
        Set<Integer> randomNumbers = new HashSet<>();
        while (randomNumbers.size() < lengthOfGame) {
            int generatedNumber = new Random().nextInt(upperBoundOfInputNumber + 1);
            randomNumbers.add(generatedNumber);
        }
        answer = randomNumbers.stream().mapToInt(number -> number).toArray();
        return answer;
    }
}
