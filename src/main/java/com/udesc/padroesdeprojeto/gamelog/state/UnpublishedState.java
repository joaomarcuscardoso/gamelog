package com.udesc.padroesdeprojeto.gamelog.state;

import com.udesc.padroesdeprojeto.gamelog.model.Game;

// com.udesc.padroesdeprojeto.gamelog.state.UnpublishedState
public class UnpublishedState implements IGameState {
    @Override
    public void finishReview() {
        System.out.println("Finishing the review and moving to the published state.");
    }

    @Override
    public void archiveGame() {
        System.out.println("Archiving the game.");
    }

    @Override
    public String getGameState() {
        return "Unpublished State";
    }
}
