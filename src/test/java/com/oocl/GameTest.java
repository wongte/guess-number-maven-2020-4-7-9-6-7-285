package com.oocl;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {
    @Test
    public void test_get_number_of_correct_number_with_0_correct() {
        Game game = new Game();
        int[] answer = {1, 2, 3, 4};
        int[] guess = {6, 7, 8, 9};
        int expectedResult = 0;
        game.setAnswer(answer);

        int result = game.getNumberOfCorrectNumber(guess);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void test_get_number_of_correct_number_with_2_correct() {
        Game game = new Game();
        int[] answer = {1, 2, 3, 4};
        int[] guess = {6 , 7, 2, 1};
        int expectedResult = 2;
        game.setAnswer(answer);

        int result = game.getNumberOfCorrectNumber(guess);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void test_get_number_of_correct_position_with_0_correct() {
        Game game = new Game();
        int[] answer = {1, 2, 3, 4};
        int[] guess = {4, 3, 2, 1};
        int expectedResult = 0;
        game.setAnswer(answer);

        int result = game.getNumberOfCorrectPosition(guess);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void test_get_number_of_correct_position_with_2_correct() {
        Game game = new Game();
        int[] answer = {1, 2, 3, 4};
        int[] guess = {1, 2, 5, 6};
        int expectedResult = 2;
        game.setAnswer(answer);

        int result = game.getNumberOfCorrectPosition(guess);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void test_generate_result_output() {
        Game game = new Game();
        String expectedResult = "1A2B";
        String actualResult = game.generateResultOutput(3, 1);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_check_result_with_0a0b() {
        Game game = new Game();
        int[] answer = {1, 2, 3, 4};
        int[] guess = {5, 6, 7, 8};
        String expectedResult = "0A0B";
        game.setAnswer(answer);
        String actualResult = game.checkResult(guess);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_check_result_with_2a2b() {
        Game game = new Game();
        int[] answer = {1, 2, 3, 4};
        int[] guess = {1, 2, 4, 3};
        String expectedResult = "2A2B";
        game.setAnswer(answer);
        String actualResult = game.checkResult(guess);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_check_result_with_4a0b() {
        Game game = new Game();
        int[] answer = {1, 2, 3, 4};
        int[] guess = {1, 2, 3, 4};
        String expectedResult = "4A0B";
        game.setAnswer(answer);
        String actualResult = game.checkResult(guess);
        Assert.assertEquals(expectedResult, actualResult);
    }
}
