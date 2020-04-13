package com.oocl;

public class GameProcess {
    private int remainingRound;

    public GameProcess(int numberOfRound) {
        this.remainingRound = numberOfRound;
    }

    public boolean isGameOver() {
        return this.remainingRound <= 0;
    }

    public void nextRound() {
        this.remainingRound--;
    }
}
