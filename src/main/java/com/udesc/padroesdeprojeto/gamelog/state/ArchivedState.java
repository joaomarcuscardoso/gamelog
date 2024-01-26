package com.udesc.padroesdeprojeto.gamelog.state;

import com.udesc.padroesdeprojeto.gamelog.model.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArchivedState implements IGameState {
    private Game game;

    @Override
    public void unpublished() {
        System.out.println("Cannot unpublished a game in the archived state");
        this.game.setState("Arquivado");
    }

    @Override
    public void published() {
        System.out.println("Cannot finish review in the archived state.");
    }

    @Override
    public void archiveGame() {
        System.out.println("Game is already archived.");
        this.game.setState("Arquivado");
    }

    @Override
    public String getGameState() {
        return this.game.getState();
    }
}