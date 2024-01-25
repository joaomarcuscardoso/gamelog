package com.udesc.padroesdeprojeto.gamelog.state;

public class FinishingState implements IGameState {
    @Override
    public void draw() {
        System.out.println("Cannot draw the game in the finishing state");
    }

    @Override
    public void reviewStage() {
        System.out.println("Cannot move to the review stage from the finishing state");
    }

    @Override
    public void reviewComplete() {
        System.out.println("Cannot move to the review complete state from the finishing state");
    }

    @Override
    public void finishing() {
        System.out.println("Finishing the game");
    }

    @Override
    public String getGameState() {
        return "Finishing State";
    }
}