package com.oocl;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {
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

    @Test
    public void test_validate_raw_input_format_with_correct_format() {
        Game game = new Game();
        String rawInput = "1 2 3 4";
        String errorMessage = null;
        try {
            game.validateRawInputFormat(rawInput);
        } catch (Exception exception) {
            errorMessage = exception.getMessage();
        }
        Assert.assertNull(errorMessage);
    }

    @Test
    public void test_validate_raw_input_format_with_incorrect_format() {
        Game game = new Game();
        String rawInput = "1 2";
        String expectedErrorMessage = "Wrong Input，Input again";

        String errorMessage = null;
        try {
            game.validateRawInputFormat(rawInput);
        } catch (Exception exception) {
            errorMessage = exception.getMessage();
        }
        Assert.assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
    public void test_convert_to_integer_array() {
        Game game = new Game();
        String rawInput = "1 2 3 4";
        int[] expectedArray = {1, 2, 3, 4};
        int[] result = game.convertToIntegerArray(rawInput);
        Assert.assertArrayEquals(expectedArray, result);
    }
}
