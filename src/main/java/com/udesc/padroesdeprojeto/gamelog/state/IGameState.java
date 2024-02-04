package com.udesc.padroesdeprojeto.gamelog.state;

import com.udesc.padroesdeprojeto.gamelog.model.Game;

public abstract class IGameState {
    Game game;

    public IGameState(Game game) {
        this.game = game;
    }

    public abstract void unpublished();
    public abstract void published();
    public abstract void archiveGame();
    public abstract  String getGameState();
}
