package com.udesc.padroesdeprojeto.gamelog.state;

import com.udesc.padroesdeprojeto.gamelog.model.Game;

public class ArchivedState implements IGameState {
    @Override
    public void finishReview() {
        System.out.println("Cannot finish review in the archived state.");
    }

    @Override
    public void archiveGame() {
        System.out.println("Game is already archived.");
    }

    @Override
    public String getGameState() {
        return "Archived State";
    }
}