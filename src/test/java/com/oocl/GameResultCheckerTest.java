package com.oocl;

import org.junit.Assert;
import org.junit.Test;

public class GameResultCheckerTest {
    @Test
    public void test_check_result_with_0a0b() {
        int[] answer = {1, 2, 3, 4};
        int[] guess = {5, 6, 7, 8};
        String expectedResult = "0A0B";
        GameResultChecker gameResultChecker = new GameResultChecker(answer);

        String actualResult = gameResultChecker.checkResult(guess);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_check_result_with_2a2b() {
        int[] answer = {1, 2, 3, 4};
        int[] guess = {1, 2, 4, 3};
        String expectedResult = "2A2B";
        GameResultChecker gameResultChecker = new GameResultChecker(answer);

        String actualResult = gameResultChecker.checkResult(guess);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_check_result_with_4a0b() {
        int[] answer = {1, 2, 3, 4};
        int[] guess = {1, 2, 3, 4};
        String expectedResult = "4A0B";
        GameResultChecker gameResultChecker = new GameResultChecker(answer);

        String actualResult = gameResultChecker.checkResult(guess);
        Assert.assertEquals(expectedResult, actualResult);
    }

}
