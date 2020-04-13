package com.oocl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

public class GameTest {
    private AnswerGenerator createMockAnswerGenerator(int[] expectedAnswer) {
        AnswerGenerator answerGenerator = Mockito.mock(RandomAnswerGenerator.class);
        Mockito.when(answerGenerator.generateAnswer()).thenReturn(expectedAnswer);
        return answerGenerator;
    }

    @Test
    public void test_check_result_with_0a0b() {
        int[] answer = {1, 2, 3, 4};
        int[] guess = {5, 6, 7, 8};
        String expectedResult = "0A0B";

        Console console = new Console();
        AnswerGenerator mockAnswerGenerator = createMockAnswerGenerator(answer);
        Game game = new Game(console, mockAnswerGenerator);

        String actualResult = game.checkResult(guess);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_check_result_with_2a2b() {
        int[] answer = {1, 2, 3, 4};
        int[] guess = {1, 2, 4, 3};
        String expectedResult = "2A2B";

        Console console = new Console();
        AnswerGenerator mockAnswerGenerator = createMockAnswerGenerator(answer);
        Game game = new Game(console, mockAnswerGenerator);

        String actualResult = game.checkResult(guess);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_check_result_with_4a0b() {
        int[] answer = {1, 2, 3, 4};
        int[] guess = {1, 2, 3, 4};
        String expectedResult = "4A0B";

        Console console = new Console();
        AnswerGenerator mockAnswerGenerator = createMockAnswerGenerator(answer);
        Game game = new Game(console, mockAnswerGenerator);

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
    public void test_game_end_after_6_incorrect_tries() {
        int[] answer = {1, 2, 3, 4};
        RandomAnswerGenerator answerGenerator = Mockito.mock(RandomAnswerGenerator.class);
        Console console = Mockito.mock(Console.class);

        Mockito.when(answerGenerator.generateAnswer()).thenReturn( answer);
        Mockito.when(console.readInputFromConsole()).thenReturn( "1 2 3 5");
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        Game game = new Game(console, answerGenerator);
        game.startGame();
        Mockito.verify(console, Mockito.times(8)).displayResultToConsole(stringArgumentCaptor.capture());

        List<String> outputResult = stringArgumentCaptor.getAllValues();
        Assert.assertEquals("Start Game", outputResult.get(0));
        for (int index = 1; index < outputResult.size() - 1 -1; index++) {
            Assert.assertEquals("3A0B", outputResult.get(index));
        }
        Assert.assertEquals("End Game", outputResult.get(outputResult.size() - 1));
    }

    @Test
    public void test_game_end_after_1_incorrect_and_1_correct_try() {
        int[] answer = {1, 2, 3, 4};
        RandomAnswerGenerator answerGenerator = Mockito.mock(RandomAnswerGenerator.class);
        Console console = Mockito.mock(Console.class);

        Mockito.when(answerGenerator.generateAnswer())
                .thenReturn( answer);
        Mockito.when(console.readInputFromConsole())
                .thenReturn( "1 2 3 5")
                .thenReturn("1 2 3 4");
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        Game game = new Game(console, answerGenerator);
        game.startGame();
        Mockito.verify(console, Mockito.times(4)).displayResultToConsole(stringArgumentCaptor.capture());

        List<String> outputResult = stringArgumentCaptor.getAllValues();
        Assert.assertEquals("Start Game", outputResult.get(0));
        Assert.assertEquals("3A0B", outputResult.get(1));
        Assert.assertEquals("4A0B", outputResult.get(2));
        Assert.assertEquals("End Game", outputResult.get(3));
    }
}
