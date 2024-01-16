package com.udesc.padroesdeprojeto.gamelog.factory;

import com.udesc.padroesdeprojeto.gamelog.model.Game;

public class GameFactory extends GamesFactory{

    private static GameFactory instance;

    private GameFactory(){}

    public static GameFactory getInstance(){
        if(instance == null){
            instance = new GameFactory();
        }
        return instance;
    }
    @Override
    public Game createGames() {
        return new Game();
    }
}
