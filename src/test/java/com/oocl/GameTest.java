package com.oocl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

public class GameTest {
    GameInputFormatter gameInputValidator = new GameInputFormatter(" ", 4, 9);

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
        Game game = new Game(console, gameInputValidator, mockAnswerGenerator);

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
        Game game = new Game(console, gameInputValidator, mockAnswerGenerator);

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
        Game game = new Game(console, gameInputValidator, mockAnswerGenerator);

        String actualResult = game.checkResult(guess);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_game_end_after_6_incorrect_tries() {
        int[] answer = {1, 2, 3, 4};
        RandomAnswerGenerator answerGenerator = Mockito.mock(RandomAnswerGenerator.class);
        Console console = Mockito.mock(Console.class);

        Mockito.when(answerGenerator.generateAnswer()).thenReturn( answer);
        Mockito.when(console.readInputFromConsole()).thenReturn( "1 2 3 5");
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        Game game = new Game(console, gameInputValidator, answerGenerator);
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
        Game game = new Game(console, gameInputValidator, answerGenerator);
        game.startGame();
        Mockito.verify(console, Mockito.times(4)).displayResultToConsole(stringArgumentCaptor.capture());

        List<String> outputResult = stringArgumentCaptor.getAllValues();
        Assert.assertEquals("Start Game", outputResult.get(0));
        Assert.assertEquals("3A0B", outputResult.get(1));
        Assert.assertEquals("4A0B", outputResult.get(2));
        Assert.assertEquals("End Game", outputResult.get(3));
    }
}
