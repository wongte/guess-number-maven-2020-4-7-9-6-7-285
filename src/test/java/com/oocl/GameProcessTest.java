package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameProcessTest {
    private GameProcess gameProcess;

    @Before
    public void setUp() {
        gameProcess = new GameProcess(2);
    }

    @Test
    public void test_is_game_over_with_over() {
        gameProcess.nextRound();
        gameProcess.nextRound();
        Assert.assertTrue(gameProcess.isGameOver());
    }

    @Test
    public void test_is_game_over_with_not_over() {
        gameProcess.nextRound();
        boolean result = gameProcess.isGameOver();
        Assert.assertFalse(result);
    }
}
