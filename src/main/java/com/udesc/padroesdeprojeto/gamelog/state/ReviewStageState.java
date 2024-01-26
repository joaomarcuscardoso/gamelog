package com.udesc.padroesdeprojeto.gamelog.state;

import com.udesc.padroesdeprojeto.gamelog.model.Game;

public class ReviewStageState implements IGameState {
    private final Game game;

    public ReviewStageState(Game game) {
        this.game = game;
    }

    @Override
    public void draw() {
        System.out.println("Cannot draw the game in the review stage.");
    }

    @Override
    public void reviewStage() {
        System.out.println("GAME: is already in the review stage.");
    }

    @Override
    public void reviewComplete() {
        System.out.println("Moving to the review complete state.");
        game.setState(new ReviewCompleteState(game));
    }

    @Override
    public void finishing() {
        System.out.println("Cannot finish the game from the review stage.");
    }

    @Override
    public String getGameState() {
        return "Review Stage State";
    }
}