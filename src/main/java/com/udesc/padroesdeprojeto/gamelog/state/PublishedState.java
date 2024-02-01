package com.udesc.padroesdeprojeto.gamelog.state;

import com.udesc.padroesdeprojeto.gamelog.model.Game;
import lombok.AllArgsConstructor;

public class PublishedState extends IGameState {
    public PublishedState(Game game) {
        super(game);
    }

    @Override
    public void unpublished() {
        System.out.println("Cannot unpublished a game in the archived state");
    }
    @Override
    public void published() {
        System.out.println("Game is already published.");
    }

    @Override
    public void archiveGame() {
        System.out.println("Archiving the game.");
        this.game.setState("Arquivado");
        this.game.setIstate(new ArchivedState(this.game));
    }

    @Override
    public String getGameState() {
        return this.game.getState();
    }
}