package com.udesc.padroesdeprojeto.gamelog.state;

import com.udesc.padroesdeprojeto.gamelog.model.Game;

public interface IGameState {
    void finishReview();
    void archiveGame();
    String getGameState();
}
