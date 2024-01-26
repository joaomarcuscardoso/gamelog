package com.udesc.padroesdeprojeto.gamelog.state;

import com.udesc.padroesdeprojeto.gamelog.model.Game;

public class FinishingState implements IGameState {
    private final Game game;

    public FinishingState(Game game) {
        this.game = game;
    }

    @Override
    public void draw() {
        System.out.println("Cannot draw the game in the finishing state.");
    }

    @Override
    public void reviewStage() {
        System.out.println("Cannot move to the review stage from the finishing state.");
    }

    @Override
    public void reviewComplete() {
        System.out.println("Cannot move to the review complete state from the finishing state.");
    }

    @Override
    public void finishing() {
        System.out.println("Game is already in the finishing state.");
    }

    @Override
    public String getGameState() {
        return "Finishing State";
    }
}