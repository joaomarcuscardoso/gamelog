package com.udesc.padroesdeprojeto.gamelog.state;

import com.udesc.padroesdeprojeto.gamelog.model.Game;

public class PublishedState implements IGameState {
    @Override
    public void finishReview() {
        System.out.println("Game is already published.");
    }

    @Override
    public void archiveGame() {
        System.out.println("Archiving the game.");
    }

    @Override
    public String getGameState() {
        return "Published State";
    }
}