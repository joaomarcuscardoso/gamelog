package com.udesc.padroesdeprojeto.gamelog.state;

import com.udesc.padroesdeprojeto.gamelog.model.Game;

public interface IGameState {

    void unpublished();
    void published();
    void archiveGame();
    String getGameState();
}
