package com.udesc.padroesdeprojeto.gamelog.state;

public class DrawState implements IGameState {
    @Override
    public void draw() {
        System.out.println("Drawing the game");
    }

    @Override
    public void reviewStage() {
        System.out.println("Cannot move to the review stage from the draw state");
    }

    @Override
    public void reviewComplete() {
        System.out.println("Cannot move to the review complete state from the draw state");
    }

    @Override
    public void finishing() {
        System.out.println("Cannot finish the game from the draw state");
    }

    @Override
    public String getGameState() {
        return "Draw State";
    }
}