package com.udesc.padroesdeprojeto.gamelog.state;

import com.udesc.padroesdeprojeto.gamelog.model.Game;

public class DrawState implements IGameState {
    private final Game game;

    public DrawState(Game game) {
        this.game = game;
    }

    @Override
    public void draw() {
        System.out.println("GAME DRAW: is already in the draw state.");
    }

    @Override
    public void reviewStage() {
        System.out.println("Moving to the review stage.");
        game.setState(new ReviewStageState(game));
    }

    @Override
    public void reviewComplete() {
        System.out.println("Cannot move to the review complete state from draw state.");
    }

    @Override
    public void finishing() {
        System.out.println("Cannot finish the game from draw state.");
    }

    @Override
    public String getGameState() {
        return "Draw State";
    }
}