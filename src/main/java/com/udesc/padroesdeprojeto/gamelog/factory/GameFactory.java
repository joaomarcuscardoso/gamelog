package com.udesc.padroesdeprojeto.gamelog.factory;

import com.udesc.padroesdeprojeto.gamelog.model.Game;

public class GameFactory extends GamesFactory{
    @Override
    protected Games createGames() {
        return new Game();
    }
}
