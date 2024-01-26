package com.udesc.padroesdeprojeto.gamelog.state;

import com.udesc.padroesdeprojeto.gamelog.model.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UnpublishedState implements IGameState {
    private Game game;

    @Override
    public void unpublished() {
        System.out.println("Cannot unpublished a game in the archived state");
        this.game.setState("Rascunho");
    }
    @Override
    public void published() {
        System.out.println("Finishing the review and moving to the published state.");
        this.game.setState("Publicado");
        this.game.setIstate(new PublishedState(game));
    }

    @Override
    public void archiveGame() {
        System.out.println("Archiving the game is not allowing for unpublished game.");
    }

    @Override
    public String getGameState() {
        return this.game.getState();
    }
}
