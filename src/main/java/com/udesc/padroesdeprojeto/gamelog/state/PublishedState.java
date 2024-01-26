package com.udesc.padroesdeprojeto.gamelog.state;

import com.udesc.padroesdeprojeto.gamelog.model.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PublishedState implements IGameState {
    private Game game;

    @Override
    public void unpublished() {
        System.out.println("Cannot unpublished a game in the archived state");
    }
    @Override
    public void published() {
        System.out.println("Game is already published.");
        game.setState("Publicado");
    }

    @Override
    public void archiveGame() {
        System.out.println("Archiving the game.");
        game.setState("Arquivado");
        this.game.setIstate(new ArchivedState(game));
    }

    @Override
    public String getGameState() {
        return this.game.getState();
    }
}