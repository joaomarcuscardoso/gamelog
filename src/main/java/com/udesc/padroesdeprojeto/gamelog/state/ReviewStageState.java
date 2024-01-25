package com.udesc.padroesdeprojeto.gamelog.state;

public class ReviewStageState implements IGameState {
    @Override
    public void draw() {
        System.out.println("Cannot draw the game in the review stage");
    }

    @Override
    public void reviewStage() {
        System.out.println("Moving to the next review stage");
    }

    @Override
    public void reviewComplete() {
        System.out.println("Cannot move to the review complete state from the review stage");
    }

    @Override
    public void finishing() {
        System.out.println("Cannot finish the game from the review stage");
    }

    @Override
    public String getGameState() {
        return "Review Stage State";
    }
}