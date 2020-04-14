package com.oocl;

import com.oocl.answer.RandomAnswerGenerator;
import org.junit.Assert;
import org.junit.Test;

public class RandomAnswerGeneratorTest {
    @Test
    public void test_generateAnswer() {
        int lengthOfGame = 4;
        int upperBoundOfInputNumber = 9;
        RandomAnswerGenerator generator = new RandomAnswerGenerator(lengthOfGame, upperBoundOfInputNumber);
        int[] answer = generator.generateAnswer();
        Assert.assertEquals(lengthOfGame, answer.length);
        for (int eachAnswer : answer) {
            Assert.assertTrue(eachAnswer >= 0 && eachAnswer <= upperBoundOfInputNumber);
        }
    }
}