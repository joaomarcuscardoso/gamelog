package com.udesc.padroesdeprojeto.gamelog.state;

import com.udesc.padroesdeprojeto.gamelog.model.Game;

public class ReviewCompleteState implements IGameState {
    private final Game game;

    public ReviewCompleteState(Game game) {
        this.game = game;
    }

    @Override
    public void draw() {
        System.out.println("Cannot draw the game in the review complete state.");
    }

    @Override
    public void reviewStage() {
        System.out.println("Cannot move to the review stage from the review complete state.");
    }

    @Override
    public void reviewComplete() {
        System.out.println("Game is already in the review complete state.");
    }

    @Override
    public void finishing() {
        System.out.println("Moving to the finishing state.");
        game.setState(new FinishingState(game));
    }

    @Override
    public String getGameState() {
        return "Review Complete State";
    }
}