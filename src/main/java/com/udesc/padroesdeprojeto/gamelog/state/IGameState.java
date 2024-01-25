package com.udesc.padroesdeprojeto.gamelog.state;

public interface IGameState {
    void draw();
    void reviewStage();
    void reviewComplete();
    void finishing();

    String getGameState();
}
