package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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
            game.validateAndConvertIntegerArray(rawInput);
        } catch (Exception exception) {
            errorMessage = exception.getMessage();
        }
        Assert.assertNull(errorMessage);
    }

    @Test
    public void test_validate_raw_input_format_with_incorrect_format() {
        Game game = new Game();
        String rawInput = "1 2";
        String expectedErrorMessage = InvalidInputException.INVALID_INPUT_MESSAGE;

        String errorMessage = null;
        try {
            game.validateAndConvertIntegerArray(rawInput);
        } catch (Exception exception) {
            errorMessage = exception.getMessage();
        }
        Assert.assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
    public void test_validate_unique_number_in_guess_with_unique_guess() {
        Game game = new Game();
        String duplicatedGuess = "1 2 3 4";
        String errorMessage = null;
        try {
            game.validateAndConvertIntegerArray(duplicatedGuess);
        } catch (Exception exception) {
            errorMessage = exception.getMessage();
        }
        Assert.assertNull(errorMessage);
    }

    @Test
    public void test_validate_unique_number_in_guess_with_duplicated_guess() {
        Game game = new Game();
        String uniqueGuess = "1 2 2 4";
        String expectedErrorMessage = InvalidInputException.INVALID_INPUT_MESSAGE;
        String errorMessage = null;
        try {
            game.validateAndConvertIntegerArray(uniqueGuess);
        } catch (Exception exception) {
            errorMessage = exception.getMessage();
        }
        Assert.assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
    public void test_validate_numbers_in_range_with_out_range_value() {
        Game game = new Game();
        String uniqueGuess = "1 2 10 4";
        String expectedErrorMessage = InvalidInputException.INVALID_INPUT_MESSAGE;
        String errorMessage = null;
        try {
            game.validateAndConvertIntegerArray(uniqueGuess);
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
        try {
            int[] result = game.validateAndConvertIntegerArray(rawInput);
            Assert.assertArrayEquals(expectedArray, result);
        } catch (Exception exception) {
            Assert.fail();
        }
    }

    @Test
    public void test_is_game_over_with_over() {
        Game game = new Game();
        game.setRemainingRound(0);
        Assert.assertTrue(game.isGameOver());
    }

    @Test
    public void test_is_game_over_with_not_over() {
        Game game = new Game();
        game.setRemainingRound(1);
        boolean result = game.isGameOver();
        Assert.assertFalse(result);
    }

    @Test
    public void test_initialize_data() {
        Console console = new Console();
        RandomAnswerGenerator answerGenerator = new RandomAnswerGenerator(Game.LENGTH_OF_GAME, Game.UPPER_BOUND_OF_INPUT_NUMBER);
        Game game = new Game(console, answerGenerator);
        game.initializeGameData();
        Assert.assertEquals(Game.NUMBER_OF_TOTAL_ROUND, game.getRemainingRound());
    }

    @Test
    public void test_game() {
        RandomAnswerGenerator answerGenerator = new RandomAnswerGenerator(Game.LENGTH_OF_GAME, Game.UPPER_BOUND_OF_INPUT_NUMBER);        ManualConsole console = new ManualConsole();
        console.addInputLine("1 2 3 4");
        console.addInputLine("1 2 3 4");
        console.addInputLine("1 2 3 4");
        console.addInputLine("1 2 3 4");
        console.addInputLine("1 2 3 4");
        console.addInputLine("1 2 3 4");
        Game game = new Game(console, answerGenerator);
        game.startGame();

        List<String> outputResult = console.getOutput();
        String firstMessage = outputResult.get(0);
        Assert.assertEquals("Start Game", firstMessage);
        String lastMessage = outputResult.get(outputResult.size() - 1);
        Assert.assertEquals("End Game", lastMessage);
    }
}
