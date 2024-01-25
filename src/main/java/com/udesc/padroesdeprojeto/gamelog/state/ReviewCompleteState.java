package com.udesc.padroesdeprojeto.gamelog.state;

public class ReviewCompleteState implements IGameState {
    @Override
    public void draw() {
        System.out.println("Cannot draw the game in the review complete state");
    }

    @Override
    public void reviewStage() {
        System.out.println("Cannot move to the review stage from the review complete state");
    }

    @Override
    public void reviewComplete() {
        System.out.println("Moving to the next review complete state");
    }

    @Override
    public void finishing() {
        System.out.println("Cannot finish the game from the review complete state");
    }

    @Override
    public String getGameState() {
        return "Review Complete State";
    }
}